package report;

import java.awt.Dialog;
import java.util.Map;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import util.DBConnection;

public class ReportUtil {

    public static void showReport(String jrxmlPath, Map<String, Object> params) {
        try {
            JasperReport jasper = JasperCompileManager.compileReport(jrxmlPath);

            JasperPrint print = JasperFillManager.fillReport(
                    jasper,
                    params,
                    DBConnection.getConnection()
            );

            JasperViewer viewer = new JasperViewer(print, false);

            // Biar ga muncul di taskbar
            viewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            viewer.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);

            viewer.setTitle("Preview Laporan");
            viewer.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
