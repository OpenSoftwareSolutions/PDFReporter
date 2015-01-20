package org.oss.pdfreporter.android;

import android.content.Context;
import android.support.annotation.NonNull;

import org.oss.pdfreporter.engine.JRExporterParameter;
import org.oss.pdfreporter.engine.JasperReport;
import org.oss.pdfreporter.engine.export.JRPdfExporterParameter;
import org.oss.pdfreporter.pdf.IDocument;
import org.oss.pdfreporter.repo.RepositoryManager;
import org.oss.pdfreporter.repo.SubreportUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportTestRunner {

    //JRXML file names
    private static final String DESIGN_REPORT_FONTS = "FontsReport.jrxml";
    private static final String DESIGN_REPORT_SHIPMENTS = "ShipmentsReportSqlJeval.jrxml";
    private static final String DESIGN_REPORT_PRODUCTS = "ProductsReport.jrxml";
    private static final String DESIGN_REPORT_ORDERS = "OrdersReport.jrxml";
    private static final String DESIGN_REPORT_LATE_ORDERS = "LateOrdersReport.jrxml";

    private static final String DESIGN_REPORT_IMAGE = "ImagesReport.jrxml";
    private static final String DESIGN_REPORT_SHAPES = "ShapesReport.jrxml";
    private static final String DESIGN_REPORT_PARAGRAPH = "ParagraphsReport.jrxml";
    private static final String DESIGN_REPORT_STYLEDTEXT = "StyledTextReport.jrxml";
    private static final String DESIGN_REPORT_CDBOOCKLET = "CDBooklet.jrxml";
    private static final String DESIGN_REPORT_ROTATION = "RotationReport.jrxml";
    private static final String DESIGN_REPORT_PDFCRYPT = "PdfEncryptReport.jrxml";
    private static final String DESIGN_REPORT_MASTER = "MasterReport.jrxml";
    private static final String DESIGN_REPORT_LANDSCAPE = "LandscapeReport.jrxml";
    private static final String DESIGN_REPORT_STRETCH = "StretchReport.jrxml";
    private static final String DESIGN_REPORT_TABULAR = "TabularReport.jrxml";

    //data source
    private static final String XML_DATA_NORTHWIND = "northwind.xml";
    private static final String XPATH_DATA_NORTHWIND_ORDERS = "/Northwind/Orders";
    private static final String XPATH_DATA_NORTHWIND_ORDERS_SHIPPED_NOT_NULL = "/Northwind/Orders[ShippedDate]";
    private static final String XML_DATA_CDBOOKLET = "CDBooklets.xml";
    private static final String XPATH_DATA_CDBOOKLET = "/CDBooklets";

    @NonNull
    private Context mContext;

    public ReportTestRunner(@NonNull Context context) {
        mContext = context.getApplicationContext();
    }

    public static class ReportPlist {
        public final String id;
        public final String name;

        public ReportPlist(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public List<ReportPlist> loadReportList() {
        final List<ReportPlist> list = new ArrayList<>(20);
        list.add(new ReportPlist(DESIGN_REPORT_FONTS, "Fonts"));
        list.add(new ReportPlist(DESIGN_REPORT_SHIPMENTS, "Shipments (SQL)"));
        list.add(new ReportPlist(DESIGN_REPORT_MASTER, "Master report"));
        list.add(new ReportPlist(DESIGN_REPORT_PRODUCTS, "Products"));
        list.add(new ReportPlist(DESIGN_REPORT_STRETCH, "Stretch"));
        list.add(new ReportPlist(DESIGN_REPORT_TABULAR, "Tabular"));
        list.add(new ReportPlist(DESIGN_REPORT_ORDERS, "Orders"));
        list.add(new ReportPlist(DESIGN_REPORT_LATE_ORDERS, "Late Orders"));
        list.add(new ReportPlist(DESIGN_REPORT_IMAGE, "Images"));
        list.add(new ReportPlist(DESIGN_REPORT_LANDSCAPE, "Landscape"));
        list.add(new ReportPlist(DESIGN_REPORT_SHAPES, "Shapes"));
        list.add(new ReportPlist(DESIGN_REPORT_ROTATION, "Rotation"));
        list.add(new ReportPlist(DESIGN_REPORT_PDFCRYPT, "Encrypted"));
        list.add(new ReportPlist(DESIGN_REPORT_PARAGRAPH, "Paragraph"));
        list.add(new ReportPlist(DESIGN_REPORT_STYLEDTEXT, "Styled text"));
        list.add(new ReportPlist(DESIGN_REPORT_CDBOOCKLET, "CD Booklet (XML)"));
        return list;
    }

    private String getRootFolder() {
        return mContext.getExternalFilesDir(null) + "/reports";
    }

    private String getResourcesFolder() {
        return getRootFolder() + "/resources";
    }

    private String getOuputPdfFolder() {
        return mContext.getExternalFilesDir(null).toString();
    }

    private ReportExporter getExporter(String reportFolder) {
        return getExporter(reportFolder, null);
    }

    public String exportReport(String identifier) throws Exception{
        if (identifier.equals(DESIGN_REPORT_FONTS)) {
            return exportFonts();
        } else if (identifier.equals(DESIGN_REPORT_SHIPMENTS)) {
            return exportShippment();
        } else if (identifier.equals(DESIGN_REPORT_MASTER)) {
            return exportMasterReport();
        } else if (identifier.equals(DESIGN_REPORT_PRODUCTS)) {
            return exportProducts();
        } else if (identifier.equals(DESIGN_REPORT_STRETCH)) {
            return exportStretch();
        } else if (identifier.equals(DESIGN_REPORT_TABULAR)) {
            return exportTabular();
        } else if (identifier.equals(DESIGN_REPORT_ORDERS)) {
            return exportOrders();
        } else if (identifier.equals(DESIGN_REPORT_LATE_ORDERS)) {
            return exportLateOrder();
        } else if (identifier.equals(DESIGN_REPORT_IMAGE)) {
            return exportImages();
        } else if (identifier.equals(DESIGN_REPORT_LANDSCAPE)) {
            return exportLandscape();
        } else if (identifier.equals(DESIGN_REPORT_SHAPES)) {
            return exportShapes();
        } else if (identifier.equals(DESIGN_REPORT_ROTATION)) {
            return exportRotation();
        } else if (identifier.equals(DESIGN_REPORT_PDFCRYPT)) {
            return exportEncrypt();
        } else if (identifier.equals(DESIGN_REPORT_PARAGRAPH)) {
            return exportParagraph();
        } else if (identifier.equals(DESIGN_REPORT_STYLEDTEXT)) {
            return exportStyledText();
        } else if (identifier.equals(DESIGN_REPORT_CDBOOCKLET)) {
            return exportCDBooklet();
        }

        return null;
    }

    private ReportExporter getExporter(String reportFolder, String extraFolder) {

        //path to root folder with all reports
        final String rootFolder = getRootFolder();
        final String resourceFolder = getResourcesFolder();

        RepositoryManager repo = RepositoryManager.getInstance();
        repo.setDefaultResourceFolder(resourceFolder);
        repo.setDefaulReportFolder(rootFolder + RepositoryManager.PATH_DELIMITER + "jrxml" + RepositoryManager.PATH_DELIMITER + reportFolder);
        if (null != extraFolder) {
            repo.addExtraReportFolder(resourceFolder + RepositoryManager.PATH_DELIMITER + extraFolder);
        }
        repo.addExtraReportFolder(resourceFolder);

        return new ReportExporter(getOuputPdfFolder(), getRootFolder() + RepositoryManager.PATH_DELIMITER + "data.db");
    }

    private String exportFonts() throws Exception {
        return getExporter("fonts", "extra-fonts").exportReport(DESIGN_REPORT_FONTS);
    }

    private String exportShippment() throws Exception {
        return getExporter("crosstabs", "extra-fonts").exportSqlReport(DESIGN_REPORT_SHIPMENTS);
    }

    private String exportMasterReport() throws Exception {
        ReportExporter exporter = getExporter("subreports", "extra-fonts"); // initialize Repository
        JasperReport subreport = SubreportUtil.loadSubreport("ProductReport.jasper");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ProductsSubreport", subreport);
        return exporter.exportSqlReport(DESIGN_REPORT_MASTER, parameters);
    }

    private String exportProducts() throws Exception {
        return getExporter("crosstabs", "extra-fonts").exportSqlReport(DESIGN_REPORT_PRODUCTS);
    }

    private String exportStretch() throws Exception {
        return getExporter("stretch").exportReport(DESIGN_REPORT_STRETCH);
    }

    private String exportTabular() throws Exception {
        return getExporter("tabular", "extra-fonts").exportReport(DESIGN_REPORT_TABULAR);
    }

    private String exportOrders() throws Exception {
        return getExporter("crosstabs", "extra-fonts").exportReport(DESIGN_REPORT_ORDERS, getResourcesFolder() + "/" + XML_DATA_NORTHWIND, XPATH_DATA_NORTHWIND_ORDERS);
    }

    private String exportLateOrder() throws Exception {
        return getExporter("crosstabs", "extra-fonts").exportReport(DESIGN_REPORT_LATE_ORDERS, getResourcesFolder() + "/" + XML_DATA_NORTHWIND, XPATH_DATA_NORTHWIND_ORDERS_SHIPPED_NOT_NULL);
    }

    private String exportImages() throws Exception {
        return getExporter("images").exportReport(DESIGN_REPORT_IMAGE);
    }

    private String exportLandscape() throws Exception {
        return getExporter("landscape").exportReport(DESIGN_REPORT_LANDSCAPE);
    }

    private String exportShapes() throws Exception {
        return getExporter("shapes").exportReport(DESIGN_REPORT_SHAPES);
    }

    private String exportRotation() throws Exception {
        return getExporter("misc").exportReport(DESIGN_REPORT_ROTATION);
    }

    private String exportEncrypt() throws Exception {
        Map<JRExporterParameter, Object> parameters = new HashMap<>();
        parameters.put(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
        parameters.put(JRPdfExporterParameter.IS_128_BIT_KEY, Boolean.TRUE);
        parameters.put(JRPdfExporterParameter.USER_PASSWORD, "jasper");
        parameters.put(JRPdfExporterParameter.OWNER_PASSWORD, "reports");
        parameters.put(JRPdfExporterParameter.PERMISSIONS, IDocument.PERMISSION_COPY | IDocument.PERMISSION_PRINT);
        return getExporter("misc").exportReport(DESIGN_REPORT_PDFCRYPT, parameters);
    }

    private String exportParagraph() throws Exception {
        return getExporter("text", "extra-fonts").exportReport(DESIGN_REPORT_PARAGRAPH);
    }

    private String exportStyledText() throws Exception {
        return getExporter("text", "extra-fonts").exportReport(DESIGN_REPORT_STYLEDTEXT);
    }

    private String exportCDBooklet() throws Exception {
        return getExporter("cdbooklet", "extra-fonts").exportReport(DESIGN_REPORT_CDBOOCKLET, getResourcesFolder() + "/" + XML_DATA_CDBOOKLET, XPATH_DATA_CDBOOKLET);
    }
}
