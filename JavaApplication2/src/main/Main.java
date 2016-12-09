package main;

import models.*;
import views.*;
import controllers.*;
import javax.swing.JPanel;

public class Main {
    
    ModelProductos modelProductos;
    ViewProductos viewProductos;
    ControllerProductos controllerProductos; 
    
    public static void main ( String [] akma) {
        
        ModelProductos modelProductos = new ModelProductos();
        ViewProductos viewProductos = new ViewProductos();
        ControllerProductos controllerProductos = new ControllerProductos(viewProductos,modelProductos);
                      
        JPanel views [] = new JPanel [1];
        
        views [0] = viewProductos;
        
        ViewMain viewMain = new ViewMain();
        ControllerMain controllerMain = new ControllerMain(viewMain, views);
    }
    
}