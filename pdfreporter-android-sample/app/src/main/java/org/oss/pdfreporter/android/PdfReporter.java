/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 *
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.android;

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
import org.oss.pdfreporter.engine.design.JasperDesign;
import org.oss.pdfreporter.engine.export.JRPdfExporterParameter;
import org.oss.pdfreporter.engine.query.JsonQueryExecuterFactory;
import org.oss.pdfreporter.engine.xml.JRXmlLoader;
import org.oss.pdfreporter.json.IJsonDataSource;
import org.oss.pdfreporter.pdf.IDocument;
import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.repo.FileResourceLoader;
import org.oss.pdfreporter.repo.RepositoryManager;
import org.oss.pdfreporter.repo.SubreportUtil;
import org.oss.pdfreporter.sql.IConnection;
import org.oss.pdfreporter.sql.factory.ISqlFactory;


public class PdfReporter {

    private final String mPdfOutputFolder;
    private final String mPdfOutputName;
    private final String mJrxmlFilePath;
    private Map<JRExporterParameter, Object> mExportParameters = new HashMap<>();
    private Map<String,Object> mFillParameters = new HashMap<>();

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

    public String exportWithoutDataSource() throws Exception{
        return exportFromXml(null, null);
    }

    public String exportFromXml(String xmlDataFile, String xmlXpath) throws Exception {

        try {
            JasperDesign design = loadReport(mJrxmlFilePath);
            JasperReport report = JasperCompileManager.compileReport(design);
            return exportReport(report, xmlDataFile, xmlXpath);
        } finally {
            ApiRegistry.dispose();
        }
    }

    public String exportSqlReport(String databasePath, String username, String password) throws Exception {

        IConnection sqlDataSource = null;
        try {

            JasperDesign design = loadReport(mJrxmlFilePath);
            JasperReport report = JasperCompileManager.compileReport(design);

            ISqlFactory sqlFactory = ApiRegistry.getSqlFactory();

            sqlDataSource = sqlFactory.newConnection(databasePath, username, password);
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
            JasperPrint printReport = JasperFillManager.fillReport(compiledReport, null, dataSource);
            String pathToPdfFile = mPdfOutputFolder + "/" + printReport.getName() + ".pdf";
            JasperExportManager.exportReportToPdfFile(printReport, pathToPdfFile, mExportParameters);
            return pathToPdfFile;
        } finally {
            close(isXmlData);
        }
    }

    public String exportJsonReport() throws Exception{
        JasperDesign design = loadReport(mJrxmlFilePath);
        JasperReport report = JasperCompileManager.compileReport(design);
        String pdfPath = exportJsonReport(report, null);
        ApiRegistry.dispose();
        return pdfPath;
    }

    public String exportJsonReport(JasperReport compiledReport, String jsonDataFile) throws Exception {

        IJsonDataSource jsonDataSource = null;
        try {
            JasperPrint printReport = null;
            if(jsonDataFile == null){
                printReport = JasperFillManager.fillReport(compiledReport, mFillParameters);
            }

            String pathToPdfFile = mPdfOutputFolder + "/" + printReport.getName() + ".pdf";
            JasperExportManager.exportReportToPdfFile(printReport, pathToPdfFile, mExportParameters);
            return pathToPdfFile;
        } finally {
            close(jsonDataSource);
        }
    }

    public void addSubreport(String subreportName, String location) throws JRException {
        JasperReport subreport = SubreportUtil.loadSubreport(location);
        mFillParameters.put(subreportName, subreport);
    }

    /**
     *
     * @param is128bitKey {@link JRPdfExporterParameter#IS_128_BIT_KEY}
     * @param userPassword {@link JRPdfExporterParameter#USER_PASSWORD}
     * @param ownerPassword {@link JRPdfExporterParameter#OWNER_PASSWORD}
     * @param permissions {@link JRPdfExporterParameter#PERMISSIONS}
     */
    public void addEncryption(boolean is128bitKey, String userPassword, String ownerPassword, int permissions) {
        mExportParameters.put(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
        mExportParameters.put(JRPdfExporterParameter.IS_128_BIT_KEY, is128bitKey ? Boolean.TRUE : Boolean.FALSE);
        mExportParameters.put(JRPdfExporterParameter.USER_PASSWORD, userPassword);
        mExportParameters.put(JRPdfExporterParameter.OWNER_PASSWORD, ownerPassword);
        mExportParameters.put(JRPdfExporterParameter.PERMISSIONS, permissions);
    }

    public void addJSONParams(String datePattern, String numberPattern, Locale jsonLocale, Locale country) {
        mFillParameters.put(JsonQueryExecuterFactory.JSON_DATE_PATTERN, datePattern);
        mFillParameters.put(JsonQueryExecuterFactory.JSON_NUMBER_PATTERN,numberPattern);
        mFillParameters.put(JsonQueryExecuterFactory.JSON_LOCALE, jsonLocale);
        mFillParameters.put(JRParameter.REPORT_LOCALE, country);
    }

    public void addMultiLanguageSupport(String classPath, Locale locale) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(JRParameter.REPORT_LOCALE, locale);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(classPath, locale);
        parameters.put(JRParameter.REPORT_RESOURCE_BUNDLE, resourceBundle);
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
