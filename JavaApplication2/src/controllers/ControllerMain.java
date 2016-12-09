package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import views.ViewMain;

public class ControllerMain implements ActionListener {

    ViewMain viewMain;
    JPanel views[];

    public ControllerMain(ViewMain viewMain, JPanel[] views) {

        this.viewMain = viewMain;
        this.views = views;
        this.viewMain.JfMenu.addActionListener(this);
        init_View();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewMain.JfMenu) {
            jmi_productosActionPerformed();
        }else if (e.getSource()== viewMain.JfMenu){
            jMIReportesActionPerformed();
        }

    }

    private void jmi_productosActionPerformed() {
        this.viewMain.setContentPane(views[0]);
        this.viewMain.revalidate();
        this.viewMain.repaint();
    }

    private void jMIReportesActionPerformed() {
     this.viewMain.setContentPane(views[0]);
        this.viewMain.revalidate();
        this.viewMain.repaint();
    }

    private void init_View() {
        this.viewMain.setTitle("Tienda");
        this.viewMain.setLocationRelativeTo(null);
        this.viewMain.setVisible(true);
        
    }

}
