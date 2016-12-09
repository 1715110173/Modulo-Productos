/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import views.ViewProductos;
import models.ModelProductos;
import sax.DBConnection;

public class ControllerProductos implements ActionListener {

    private final ViewProductos viewProductos;
    private final ModelProductos modelProductos;
    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "1234");

    public ControllerProductos(ViewProductos view_productos, ModelProductos modelProductos) {
        this.viewProductos = view_productos;
        this.modelProductos = modelProductos;

        this.viewProductos.jbtnFirst.addActionListener(this);
        this.viewProductos.jbtnPrevious.addActionListener(this);
        this.viewProductos.jbtnNext.addActionListener(this);
        this.viewProductos.jbtnLast.addActionListener(this);
        this.viewProductos.jbtn_agregar.addActionListener(this);
        this.viewProductos.jbtn_editar.addActionListener(this);
        this.viewProductos.jbtn_eliminar.addActionListener(this);
        this.viewProductos.jbtn_guardar.addActionListener(this);

        initView();
        showData();
    }

    void Limpiar() {
        while (viewProductos.jTable.getRowCount() != 0) {
            ((DefaultTableModel) viewProductos.jTable.getModel()).removeRow(0);
        }
    }

    private void initView() {
        modelProductos.initValues();
        showValues();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewProductos.jbtnFirst) {
            jbtnFirstActionPerformed();
        } else if (e.getSource() == viewProductos.jbtnPrevious) {
            jbtnPreviousActionPerformed();
        } else if (e.getSource() == viewProductos.jbtnNext) {
            jbtnNextActionPerformed();
        } else if (e.getSource() == viewProductos.jbtnLast) {
            jbtnLastActionPerformed();
        } else if (e.getSource() == viewProductos.jbtn_agregar) {
            agregarRegistro();
        } else if (e.getSource() == viewProductos.jbtn_eliminar) {
            modelProductos.eliminarValues();
            initView();
            Limpiar();
            showData();
        } else if (e.getSource() == viewProductos.jbtn_editar) {
            editarValues();
            initView();
            Limpiar();
            showData();
        } else if (e.getSource() == viewProductos.jbtn_guardar) {
            guadarRegistro();
            initView();
            Limpiar();
            showData();
        }

    }

    private void jbtnFirstActionPerformed() {
        modelProductos.moveFirst();
        showValues();
    }

    private void jbtnPreviousActionPerformed() {
        modelProductos.movePrevious();
        showValues();
    }

    private void jbtnNextActionPerformed() {
        modelProductos.moveNext();
        showValues();
    }

    private void jbtnLastActionPerformed() {
        modelProductos.moveLast();
        showValues();
    }

    public void agregarRegistro() {
        this.viewProductos.jtf_id_productos.setText("");
        this.viewProductos.jtf_producto.setText("");
        this.viewProductos.jtf_descripcion.setText("");
        this.viewProductos.jtf_precio_compra.setText("");
        this.viewProductos.jtf_precio_venta.setText("");
        this.viewProductos.jtf_existencias.setText("");

    }

    private void showData() {
        viewProductos.jTable.setModel(modelProductos.tableModel);
        this.modelProductos.Tabla();
        this.modelProductos.setValues();
    }

    public void guadarRegistro() {

        String id_producto = this.viewProductos.jtf_id_productos.getText();
        String producto = this.viewProductos.jtf_producto.getText();
        String descripcion = this.viewProductos.jtf_descripcion.getText();
        Integer precio_compra = Integer.parseInt(viewProductos.jtf_precio_compra.getText());
        Integer precio_venta = Integer.parseInt(viewProductos.jtf_precio_venta.getText());
        Integer existencias = Integer.parseInt(viewProductos.jtf_existencias.getText());

        conection.executeUpdate("insert into productos (producto, descripcion, precio_compra, precio_venta, existencias)" + " values "
                + "('" + producto + "','" + descripcion + "','" + precio_compra + "','" + precio_venta + "','" + existencias + "');");

        this.modelProductos.setValues();
        modelProductos.initValues();
        showValues();

    }

    public void editarValues() {
        Integer id_producto = Integer.parseInt(viewProductos.jtf_id_productos.getText());
        String producto = this.viewProductos.jtf_producto.getText();
        String descripcion = this.viewProductos.jtf_descripcion.getText();
        Integer precio_compra = Integer.parseInt(viewProductos.jtf_precio_compra.getText());
        Integer precio_venta = Integer.parseInt(viewProductos.jtf_precio_venta.getText());
        Integer existencias = Integer.parseInt(viewProductos.jtf_existencias.getText());

        conection.executeUpdate("update productos set id_producto='" + id_producto + "',producto='" + producto + "',descripcion='" + descripcion + "',precio_compra='" + precio_compra + "',precio_venta='" + precio_venta + "',existencias='" + existencias + "' where id_producto='" + this.viewProductos.jtf_id_productos.getText() + "';");

        this.modelProductos.setValues();
        modelProductos.initValues();
        showValues();
    }

    private void showValues() {
        viewProductos.jtf_id_productos.setText("" + modelProductos.getId_producto());
        viewProductos.jtf_producto.setText(modelProductos.getProducto());
        viewProductos.jtf_descripcion.setText(modelProductos.getDescripcion());
        viewProductos.jtf_precio_compra.setText("" + modelProductos.getPrecio_compra());
        viewProductos.jtf_precio_venta.setText("" + modelProductos.getPrecio_venta());
        viewProductos.jtf_existencias.setText("" + modelProductos.getExistencias());

    }

}
