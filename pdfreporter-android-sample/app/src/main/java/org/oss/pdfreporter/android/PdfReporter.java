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
import java.util.Map;

import org.oss.pdfreporter.engine.JRDataSource;
import org.oss.pdfreporter.engine.JREmptyDataSource;
import org.oss.pdfreporter.engine.JRExporterParameter;
import org.oss.pdfreporter.engine.JasperCompileManager;
import org.oss.pdfreporter.engine.JasperExportManager;
import org.oss.pdfreporter.engine.JasperFillManager;
import org.oss.pdfreporter.engine.JasperPrint;
import org.oss.pdfreporter.engine.JasperReport;
import org.oss.pdfreporter.engine.data.JRXmlDataSource;
import org.oss.pdfreporter.engine.design.JasperDesign;
import org.oss.pdfreporter.engine.xml.JRXmlLoader;
import org.oss.pdfreporter.registry.ApiRegistry;
import org.oss.pdfreporter.repo.FileResourceLoader;
import org.oss.pdfreporter.repo.RepositoryManager;
import org.oss.pdfreporter.sql.IConnection;
import org.oss.pdfreporter.sql.factory.ISqlFactory;


public class PdfReporter {

    private final String mPdfOutputFolder;
    private final String mPdfOutputName;
    private final String mJrxmlFilePath;
    private Map<JRExporterParameter, Object> mExportParameters;
    private Map<String,Object> mFillParameters;

    public PdfReporter(String jrxmlFilePath, String outputFolder, String outputPdfName) {
        mPdfOutputFolder = outputFolder;
        mPdfOutputName = outputPdfName;
        mJrxmlFilePath = jrxmlFilePath;
    }

    public void setExportParameters(Map<JRExporterParameter, Object> exporterParameters) {
        mExportParameters = exporterParameters;
    }

    public void setFillParameters(Map<String,Object> fillParameters) {
        mFillParameters = fillParameters;
    }

    public static RepositoryManager getRepositoryManager() {
        return RepositoryManager.getInstance();
    }

    public String exportWithoutDataSource() throws Exception{
        return exportFromXml(null, null);
    }

    public String exportFromXml(String xmlDataFile, String xmlXpath) throws Exception {
        ApiRegistry.initSession();

        try {
            JasperDesign design = loadReport(mJrxmlFilePath);
            JasperReport report = JasperCompileManager.compileReport(design);
            return exportReport(report, xmlDataFile, xmlXpath);
        } finally {
            ApiRegistry.dispose();
        }
    }

    public String exportSqlReport(String databasePath, String username, String password) throws Exception {
        ApiRegistry.initSession();

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

    public JasperDesign loadReport(String reportFileName) throws Exception {
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
