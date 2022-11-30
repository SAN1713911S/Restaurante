package Cocina;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import cuentanouso.CuentaRestaurante;
import Html.BuildHTML;
import Servicio.Domicilio;
//import Menu.Menu;
import Servicio.Mesa;
import Servicio.Ordenes;

public class Restaurante {
	
	public static String opB = "";
	public static String opF = "";
	CuentaRestaurante c =new CuentaRestaurante();
	
	public void menu(Scanner sc) {
		if(Ordenes.mesas.isEmpty()) {
			System.out.println("\n  No hay ordenes para cocinar");
		}else {
			System.out.println("\nEntrando a cocina\n");
			for (Map.Entry<Integer,Mesa> entry : Ordenes.mesas.entrySet()) {
				ArrayList<String> orden = entry.getValue().getOrden();
				if(orden != null) {
					if(orden.get(0).equals("Desayuno")) {
						servDesayuno(sc, orden);
						facturacion(sc,"");
					}else if(orden.get(0).equals("Almuerzo")) {
						servAlmuerzo(sc, orden);
						facturacion(sc,"");
					}
				}
			}
			Ordenes.mesas.clear();
		}
	}
	public void menuDomicilios(Scanner sc) {
		if(Ordenes.domicilios.isEmpty()) {
			System.out.println("\n  No hay domicilios para entregar");
		}else {
			System.out.println("\nEntrando a cocina\n");
			for (Map.Entry<Integer,Domicilio> entry : Ordenes.domicilios.entrySet()) {
				ArrayList<String> orden = entry.getValue().getOrden();
				if(orden != null) {
					if(orden.get(0).equals("Desayuno")) {
						servDesayuno(sc, orden);
						facturacion(sc,entry.getValue().getDomiliciario().getNombre());
					}else if(orden.get(0).equals("Almuerzo")) {
						servAlmuerzo(sc, orden);
						facturacion(sc,entry.getValue().getDomiliciario().getNombre());
					}
				}
			}
			Ordenes.domicilios.clear();
		}
	}
	
	public void facturacion(Scanner sc, String Domiciliario) {
		System.out.println("---Bienvenido Cliente-----");
		System.out.println("-----Facturacion-----");
		boolean valido = false;
		while(valido != true) {
			System.out.println("Tiene cuenta en el restaurante?  Si(Y) No(N):");
			String opcion = sc.next();
			if(opcion.equals("Y") || opcion.equals("y")) {
				if(CuentaRestaurante.cuentasClientes.size() == 0){
					System.out.println("No existen cuentas registradas");
					System.out.println("---Registro---");
					c.crearCuenta();
					System.out.println("---Cuenta registrada---");
					serLogin(sc, Domiciliario);
					valido = false;
				}
			}else if(opcion.equals("N") || opcion.equals("n")){
				System.out.println("---Registro---");
				c.crearCuenta();
				System.out.println("---Cuenta registrada---");
				serLogin(sc, Domiciliario);
			}else {
				System.out.println("Ingrese una opcion valida");
			}
		}
	}
	public void serLogin(Scanner sc, String Domiciliario) {
		boolean valido = false;
		while(valido != true) {
			System.out.println("---Login---");
			System.out.println("Ingrese su id:");
			int id = sc.nextInt();
			for (int i = 0; i < CuentaRestaurante.cuentasClientes.size(); i++) {
				if (id == CuentaRestaurante.cuentasClientes.get(i).getIdCliente()) {
					System.out.println("Saldo actual: " + c.mostrarSaldo(CuentaRestaurante.cuentasClientes.get(i)));
					System.out.println("Desea recargar su cuenta?  Si(Y) No(N):");
					String op = sc.next();
					if(op.equals("Y") || op.equals("y")) {
						System.out.println("Cuanto dinero quiere recargar?");
						int recarga = sc.nextInt();
						c.recargarCuenta(CuentaRestaurante.cuentasClientes.get(i), recarga);
						System.out.println("---Recarga completada---\n---Procesando pago automatico---");
						if (BuildHTML.getInstance().precioTotal <= c.mostrarSaldo(CuentaRestaurante.cuentasClientes.get(i))) {
							c.pagarPedido(CuentaRestaurante.cuentasClientes.get(i), BuildHTML.getInstance().precioTotal);
							System.out.println("---Pago realizado---");
							System.out.println(" ");
							facturacion(sc, Domiciliario);
							if(!Domiciliario.equals("")){
								System.out.println("---Su pago lo recibe "+Domiciliario+"---");
							}
							valido=true;
						}else {
							System.out.println("Saldo insuficiente, recargue su cuenta");
						}
					}else if(op.equals("N") || op.equals("n")){
						if (BuildHTML.getInstance().precioTotal <= c.mostrarSaldo(CuentaRestaurante.cuentasClientes.get(i))) {
							c.pagarPedido(CuentaRestaurante.cuentasClientes.get(i), BuildHTML.getInstance().precioTotal);
							System.out.println("---Pago realizado---");
							System.out.println(" ");
							facturacion(sc, Domiciliario);
							if(!Domiciliario.equals("")){
								System.out.println("---Su pago lo recibe "+Domiciliario+"---");
							}
							valido=true;
						}else {
							System.out.println("Saldo insuficiente, recargue su cuenta");
							facturacion(sc,Domiciliario);
						}
					}else {
						System.out.println("Ingrese una opcion valida");
					}
				}else if(id != CuentaRestaurante.cuentasClientes.get(i).getIdCliente()){
					System.out.println("El usuario no se encuentra en el sistema");
					facturacion(sc,Domiciliario);
				}
			}
		}
	}
	public void servDesayuno(Scanner sc, ArrayList<String> orden) {
        Plato plato = new Plato();
        DecConcreteA d1 = new DecConcreteA(plato);
        DecConcreteB d2 = new DecConcreteB(d1);
        DecConcreteC d3 = new DecConcreteC(d2);
        opB = orden.get(2);
        opF = orden.get(3);
        switch(Integer.valueOf(orden.get(1))){
            case 1:
            	genOpcional(d3, "desayuno", 1);
                break;
            case 2:
            	genOpcional(d3, "desayuno", 2);
                break;
            case 3:
            	genOpcional(d3, "desayuno", 3);
                break;
        }
	}
	
	public void servAlmuerzo(Scanner sc, ArrayList<String> orden) {
		Plato plato = new Plato();
        DecConcreteA d1 = new DecConcreteA(plato);
        DecConcreteB d2 = new DecConcreteB(d1);
        DecConcreteC d3 = new DecConcreteC(d2);
        opB = orden.get(2);
        opF = orden.get(3);
        switch(Integer.valueOf(orden.get(1))){
            case 1:
            	genOpcional(d3, "almuerzo", 1);
                break;
            case 2:
            	genOpcional(d3, "almuerzo", 2);
                break;
            case 3:
            	genOpcional(d3, "almuerzo", 3);
                break;
        }
    }

	public void genOpcional(DecConcreteC d3, String comida ,int caso) {
		//Si pidio bebida
		if(!opB.equals("N")) {
			DecConcreteD d4 = new DecConcreteD(d3);
			//Si pidio bebida y fruta
			if(!opF.equals("N")) {
				DecConcreteE d5 = new DecConcreteE(d4);
				if(comida.equals("desayuno")) {
					if(caso == 1) {
						d5.menuDesayuno1();
					}else if(caso == 2) {
						d5.menuDesayuno2();
					}else {
						d5.menuDesayuno3();
					}
				}else {
					if(caso == 1) {
						d5.menuAlmuerzo1();
					}else if(caso == 2) {
						d5.menuAlmuerzo2();
					}else {
						d5.menuAlmuerzo3();
					}
				}
				d5.getPrecio();
			}
			//Si pidio bebida pero no fruta
			else {
				if(comida.equals("desayuno")) {
					if(caso == 1) {
						d4.menuDesayuno1();
					}else if(caso == 2) {
						d4.menuDesayuno2();
					}else {
						d4.menuDesayuno3();
					}
				}else {
					if(caso == 1) {
						d4.menuAlmuerzo1();
					}else if(caso == 2) {
						d4.menuAlmuerzo2();
					}else {
						d4.menuAlmuerzo3();
					}
				}
				d4.getPrecio();
			}
		}
		//Si no pidio bebida
		else {
			//Si no pidio bebida pero si fruta
			if(!opF.equals("N")) {
				DecConcreteE d5 = new DecConcreteE(d3);
				if(comida.equals("desayuno")) {
					if(caso == 1) {
						d5.menuDesayuno1();
					}else if(caso == 2) {
						d5.menuDesayuno2();
					}else {
						d5.menuDesayuno3();
					}
				}else {
					if(caso == 1) {
						d5.menuAlmuerzo1();
					}else if(caso == 2) {
						d5.menuAlmuerzo2();
					}else {
						d5.menuAlmuerzo3();
					}
				}
				d5.getPrecio();
			}
			//Si no pidio bebida ni fruta
			else {
				if(comida.equals("desayuno")) {
					if(caso == 1) {
						d3.menuDesayuno1();
					}else if(caso == 2) {
						d3.menuDesayuno2();
					}else {
						d3.menuDesayuno3();
					}
				}else {
					if(caso == 1) {
						d3.menuAlmuerzo1();
					}else if(caso == 2) {
						d3.menuAlmuerzo2();
					}else {
						d3.menuAlmuerzo3();
					}
				}
				d3.getPrecio();
			}
		}
		opB = "";
		opF = "";
	}
}
