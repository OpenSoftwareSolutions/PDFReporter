/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 *
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
package org.oss.pdfreporter;

import java.io.Closeable;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.oss.pdfreporter.engine.JRDataSource;
import org.oss.pdfreporter.engine.JREmptyDataSource;
import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRExporterParameter;
import org.oss.pdfreporter.engine.JRParameter;
import org.oss.pdfreporter.engine.JasperCompileManager;
import org.oss.pdfreporter.engine.JasperExportManager;
import org.oss.pdfreporter.engine.JasperFillManager;
import org.oss.pdfreporter.engine.JasperPrint;
import org.oss.pdfreporter.engine.JasperReport;
import org.oss.pdfreporter.engine.data.JRXmlDataSource;
import org.oss.pdfreporter.engine.data.JsonDataSource;
import org.oss.pdfreporter.engine.design.JasperDesign;
import org.oss.pdfreporter.engine.export.JRPdfExporterParameter;
import org.oss.pdfreporter.engine.query.JRXPathQueryExecuterFactory;
import org.oss.pdfreporter.engine.query.JsonQueryExecuterFactory;
import org.oss.pdfreporter.engine.util.JRLoader;
import org.oss.pdfreporter.engine.util.JRXmlUtils;
import org.oss.pdfreporter.engine.xml.JRXmlLoader;
import org.oss.pdfreporter.json.IJsonDataSource;
import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.repo.FileResourceLoader;
import org.oss.pdfreporter.repo.RepositoryManager;
import org.oss.pdfreporter.repo.SubreportUtil;
import org.oss.pdfreporter.sql.IConnection;
import org.oss.pdfreporter.sql.factory.ISqlFactory;
import org.oss.pdfreporter.uses.org.w3c.dom.Document;


public class PdfReporter {

    private final String mPdfOutputFolder;
    private final String mPdfOutputName;
    private final String mJrxmlFilePath;
    private Map<JRExporterParameter, Object> mExportParameters = new HashMap<JRExporterParameter, Object>();
    private Map<String,Object> mFillParameters = new HashMap<String,Object>();
    private String mSubreportName;
    private String mSubreportLocation;

    private boolean mXmlReport;
    private boolean mSqlReport;
    private boolean mJsonReport;

    //xml report
    private String mXmlDataFile;
    private String mXmlXPath;

    //sql report
    private String mSqlPath;
    private String mSqlUsername;
    private String mSqlPassword;

    //json report
    private String mJsonDataFile;
    private String mJsonExpression;

    public PdfReporter(String jrxmlFilePath, String outputFolder, String outputPdfName) {
        mPdfOutputFolder = outputFolder;
        mPdfOutputName = outputPdfName;
        mJrxmlFilePath = jrxmlFilePath;
    }

    public void setExportParameters(Map<JRExporterParameter, Object> exporterParameters) {
        mExportParameters.putAll(exporterParameters);
    }

    public void setFillParameters(Map<String,Object> fillParameters) {
        mFillParameters.putAll(fillParameters);
    }

    public static RepositoryManager getRepositoryManager() {
        return RepositoryManager.getInstance();
    }

    private String exportWithoutDataSource() throws Exception{
        return exportFromXml(null, null);
    }

    public PdfReporter setXmlSource(String xmlDataFile, String xmlXpath) {
        if (mSqlReport || mJsonReport) {
            throw new RuntimeException("Can't change report type, data source already set");
        }
        mXmlReport = true;

        this.mXmlDataFile = xmlDataFile;
        this.mXmlXPath = xmlXpath;

        return this;
    }

    public PdfReporter setSqlSource(String databasePath, String username, String password) {
        if (mXmlReport || mJsonReport) {
            throw new RuntimeException("Can't change report type, data source already set");
        }
        mSqlReport = true;

        this.mSqlPath = databasePath;
        this.mSqlUsername = username;
        this.mSqlPassword = password;

        return this;
    }

    /**
     * Use this in case your JSON datasource is defined as a property (net.sf.jasperreports.json.source)
     *  inside the JRXML file.
     * @return
     */
    public PdfReporter setJsonSource() {
        return setJsonSource(null, null);
    }

    public PdfReporter setJsonSource(String jsonDataFile) {
        return setJsonSource(jsonDataFile, null);
    }

    public PdfReporter setJsonSource(String jsonDataFile, String selectExpression) {
        if (mXmlReport || mSqlReport) {
            throw new RuntimeException("Can't change report type, data source already set");
        }
        mJsonReport = true;

        mJsonDataFile = jsonDataFile;
        mJsonExpression = selectExpression;

        return this;
    }

    public String exportPdf() throws Exception {
        if (mXmlReport) {
            return exportFromXml(mXmlDataFile, mXmlXPath);
        } else if (mSqlReport) {
            return exportSqlReport(mSqlPath, mSqlUsername, mSqlPassword);
        } else  if (mJsonReport) {
            return exportJsonReport();
        } else {
            return exportWithoutDataSource();
        }
    }

    private String exportFromXml(String xmlDataFile, String xmlXpath) throws Exception {
        ApiRegistry.initSession();
        try {
            JasperDesign design = loadReport(mJrxmlFilePath);
            JasperReport report = JasperCompileManager.compileReport(design);
            return exportReport(report, xmlDataFile, xmlXpath);
        } finally {
            ApiRegistry.dispose();
        }
    }

    private String exportSqlReport(String databasePath, String username, String password) throws Exception {
        ApiRegistry.initSession();
        IConnection sqlDataSource = null;
        try {

            JasperDesign design = loadReport(mJrxmlFilePath);
            JasperReport report = JasperCompileManager.compileReport(design);

            ISqlFactory sqlFactory = ApiRegistry.getSqlFactory();

            sqlDataSource = sqlFactory.newConnection(databasePath, username, password);
            processSubreport();
            JasperPrint printReport = JasperFillManager.fillReport(report, mFillParameters, sqlDataSource);
            String pathToPdfFile = mPdfOutputFolder + "/" + mPdfOutputName + ".pdf";
            JasperExportManager.exportReportToPdfFile(printReport, pathToPdfFile, mExportParameters);
            return pathToPdfFile;
        } finally {
            close(sqlDataSource);
            ApiRegistry.dispose();
        }
    }

    private String exportReport(JasperReport compiledReport, String xmlDataFile, String xmlXpath) throws Exception {
        ApiRegistry.initSession();
        JRDataSource dataSource = null;
        InputStream isXmlData = null;
        try {
            if (xmlDataFile == null) {
                dataSource = new JREmptyDataSource();
            } else {
                isXmlData = FileResourceLoader.getInputStream(xmlDataFile);
                JRXmlDataSource xmlDataSource = new JRXmlDataSource(isXmlData, xmlXpath);
                xmlDataSource.setDatePattern("yyyy-MM-dd");
                dataSource = xmlDataSource;
            }
            processSubreport();
            JasperPrint printReport = JasperFillManager.fillReport(compiledReport, mFillParameters, dataSource);
            String pathToPdfFile = mPdfOutputFolder + "/" + printReport.getName() + ".pdf";
            JasperExportManager.exportReportToPdfFile(printReport, pathToPdfFile, mExportParameters);
            return pathToPdfFile;
        } finally {
            close(isXmlData);
            ApiRegistry.dispose();
        }
    }

    private String exportJsonReport() throws Exception {
        ApiRegistry.initSession();
        IJsonDataSource jsonDataSource = null;
        try {

            JasperDesign design = loadReport(mJrxmlFilePath);
            JasperReport report = JasperCompileManager.compileReport(design);

            JasperPrint printReport = null;
            processSubreport();
            if(mJsonDataFile == null){
                printReport = JasperFillManager.fillReport(report, mFillParameters);
            } else {

                InputStream jsonData = FileResourceLoader.getInputStream(mJsonDataFile);
                jsonDataSource = new JsonDataSource(jsonData, mJsonExpression);
                printReport = JasperFillManager.fillReport(report, mFillParameters, jsonDataSource);
            }

            String pathToPdfFile = mPdfOutputFolder + "/" + printReport.getName() + ".pdf";
            JasperExportManager.exportReportToPdfFile(printReport, pathToPdfFile, mExportParameters);
            return pathToPdfFile;
        } finally {
            close(jsonDataSource);
            ApiRegistry.dispose();
        }
    }

    public PdfReporter addSubreport(String subreportName, String location) throws JRException {
        mSubreportName = subreportName;
        mSubreportLocation = location;

        return this;
    }

    private void processSubreport() throws JRException{
        if (mSubreportLocation != null && mSubreportName != null) {
            JasperReport subreport = SubreportUtil.loadSubreport(mSubreportLocation);
            mFillParameters.put(mSubreportName, subreport);
        }
    }

    /**
     *
     * @param is128bitKey {@link JRPdfExporterParameter#IS_128_BIT_KEY}
     * @param userPassword {@link JRPdfExporterParameter#USER_PASSWORD}
     * @param ownerPassword {@link JRPdfExporterParameter#OWNER_PASSWORD}
     * @param permissions {@link JRPdfExporterParameter#PERMISSIONS}
     */
    public PdfReporter addEncryption(boolean is128bitKey, String userPassword, String ownerPassword, int permissions) {
        mExportParameters.put(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
        mExportParameters.put(JRPdfExporterParameter.IS_128_BIT_KEY, is128bitKey ? Boolean.TRUE : Boolean.FALSE);
        mExportParameters.put(JRPdfExporterParameter.USER_PASSWORD, userPassword);
        mExportParameters.put(JRPdfExporterParameter.OWNER_PASSWORD, ownerPassword);
        mExportParameters.put(JRPdfExporterParameter.PERMISSIONS, permissions);

        return this;
    }

    public PdfReporter addJSONParams(String datePattern, String numberPattern, Locale jsonLocale, Locale country) {
        mFillParameters.put(JsonQueryExecuterFactory.JSON_DATE_PATTERN, datePattern);
        mFillParameters.put(JsonQueryExecuterFactory.JSON_NUMBER_PATTERN,numberPattern);
        mFillParameters.put(JsonQueryExecuterFactory.JSON_LOCALE, jsonLocale);
        mFillParameters.put(JRParameter.REPORT_LOCALE, country);

        return this;
    }

    public PdfReporter addXMLParams(String datePattern, String numberPattern, Locale xmlLocale, Locale country) {
        mFillParameters.put(JRXPathQueryExecuterFactory.XML_DATE_PATTERN, datePattern);
        mFillParameters.put(JRXPathQueryExecuterFactory.XML_NUMBER_PATTERN, numberPattern);
        mFillParameters.put(JRXPathQueryExecuterFactory.XML_LOCALE, xmlLocale);
        mFillParameters.put(JRParameter.REPORT_LOCALE, country);
        return this;
    }

    public PdfReporter addSubReportXMLDocument(String xmlDataFile) throws JRException {
    	Document document = JRXmlUtils.parse(JRLoader.getLocationInputStream(xmlDataFile));
        mFillParameters.put(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT, document);
        return this;
    }

    public PdfReporter newResourceBundle(String classPath, Locale locale) {
        mFillParameters.put(JRParameter.REPORT_LOCALE, locale);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(classPath, locale);
        mFillParameters.put(JRParameter.REPORT_RESOURCE_BUNDLE, resourceBundle);
        return this;
    }

    public PdfReporter addFillParameter(String key, Object value) {
    	mFillParameters.put(key, value);
    	return this;
    }

    private JasperDesign loadReport(String reportFileName) throws Exception {
        InputStream isReport = null;
        try {
            isReport = FileResourceLoader.getInputStream(reportFileName);
            return JRXmlLoader.load(isReport);
        } finally {
            close(isReport);
        }
    }

    private void close(Closeable stream) throws Exception {
        if (stream!=null) {
            stream.close();
        }
    }

}
