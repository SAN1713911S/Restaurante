package Launcher;

//import java.util.ArrayList;
//import java.util.Map;
import java.util.Scanner;

import Cocina.Restaurante;
//import Productos.Component;
import Productos.Producto;
import Servicio.Ordenes;

public class Launcher {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        Producto productos = new Producto();
        productos.productos();
        //tienda.productRestaurante();
        menu();
    }
    
    public static void menu() {
	    boolean salir = false;
        while(!salir){
            System.out.println("\n\nRESTAURANTE EL BUENDIA:");
            System.out.println("  - Ordenes en la mesa(1)\n  - Cocinar(2)\n  - Ordenes pendientes(3)\n  - Salir(4)");
            System.out.print("¿Que servicio desea? ");
            int opc = sc.nextInt();
            switch(opc){
                case 1:
                	Ordenes.ordenes();
                    break;
                case 2:
                	Restaurante restaurante = new Restaurante();
                    //restaurante.servicios(sc);
                    restaurante.menu(sc);
                    break;
                case 3:
                	Ordenes.OrdenesPendientes();
                	break;
                case 4:
                	System.out.println("HAS SALIDO DEL RESTAURANTE");
                    salir = true;
                    break;
                default:
                    System.out.println("Digite una opcion valida");
            }
        }
    }
}