/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import javax.swing.table.DefaultTableModel;
import sax.DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import views.ViewProductos;
import controllers.Conection;

/**
 *
 * @author akma
 */
public class ModelProductos {

    private DBConnection conection = new DBConnection(3306, "localhost", "acme", "root", "1234");

    private int id_producto;
    private String producto;
    private String descripcion;
    private int precio_compra;
    private int precio_venta;
    private int existencias;

    ViewProductos viewProductos = new ViewProductos();
    public DefaultTableModel tableModel = new DefaultTableModel(new String[]{"id_producto", "producto", "descripcion", "precio_compra", "precio_venta", "existencias"}, 0);

    public DBConnection getConection() {
        return conection;
    }

    public void setConection(DBConnection conection) {
        this.conection = conection;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(int precio_compra) {
        this.precio_compra = precio_compra;
    }

    public int getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(int precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public void moveNext() {
        getConection().moveNext();
        setValues();
    }

    public void movePrevious() {
        getConection().movePrevious();
        setValues();
    }

    public void moveFirst() {
        getConection().moveFirst();
        setValues();
    }

    public void moveLast() {
        getConection().moveLast();
        setValues();
    }

    public void initValues() {
        //getConection().executeQuery("SELECT id_producto, producto, descripcion,precio_compra,precio_venta,existencias FROM productos;");
        getConection().executeQuery("SELECT * FROM productos;");
        getConection().moveNext();
        setValues();
    }

    public void setValues() {
        this.setId_producto(conection.getInteger("id_producto"));
        this.setProducto(conection.getString("producto"));
        this.setDescripcion(conection.getString("descripcion"));
        this.setPrecio_compra(conection.getInteger("precio_compra"));
        this.setPrecio_venta(conection.getInteger("precio_venta"));
        this.setExistencias(conection.getInteger("existencias"));

    }

    public void eliminarValues() {
        conection.executeUpdate("delete from productos where id_producto=" + getId_producto());

        this.viewProductos.jtf_producto.setText("");
        this.viewProductos.jtf_descripcion.setText("");
        this.viewProductos.jtf_precio_compra.setText("");
        this.viewProductos.jtf_precio_venta.setText("");
        this.viewProductos.jtf_existencias.setText("");

        initValues();

    }

    public void Tabla() {
        while (conection.moveNext()) {
            setValues();
            tableModel.addRow(new Object[]{getId_producto(), getProducto(), getDescripcion(), getPrecio_compra(), getPrecio_venta(), getExistencias()});
        }
    }

}
