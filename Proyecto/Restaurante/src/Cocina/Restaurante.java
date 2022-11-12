package Cocina;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import Cuenta_nouso.CuentaRestaurante;
//import Menu.Menu;
import Servicio.Mesa;
import Servicio.Ordenes;

public class Restaurante {
	
	public static String opB = "";
	public static String opF = "";
	
	public void menu(Scanner sc) {
		//Menu menu = new Menu();
		//ArrayList<String> orden = menu.menu(sc);
		if(Ordenes.mesas.isEmpty()) {
			System.out.println("\n  No hay ordenes para cocinar");
		}else {
			System.out.println("\nEntrando a cocina\n");
			for (Map.Entry<Integer,Mesa> entry : Ordenes.mesas.entrySet()) {
				ArrayList<String> orden = entry.getValue().getOrden();
				if(orden != null) {
					if(orden.get(0).equals("Desayuno")) {
						servDesayuno(sc, orden);
					}else if(orden.get(0).equals("Almuerzo")) {
						servAlmuerzo(sc, orden);
					}
				}
			}
			Ordenes.mesas.clear();
		}
		
		
		/*ArrayList<String> orden = Ordenes.mesas.entrySet().stream().filter(entry -> id.equals(entry.getKey())).findFirst().map(Map.Entry::getValue);
		if(orden != null) {
			System.out.println("\nEntrando a cocina\n");
			if(orden.get(0).equals("Desayuno")) {
				servDesayuno(sc, orden);
			}else if(orden.get(0).equals("Almuerzo")) {
				servAlmuerzo(sc, orden);
			}
		}*/
	}
	
	public void facturacion(Scanner sc) {
		CuentaRestaurante c =new CuentaRestaurante();
		System.out.println("---Bienvenido CLiente-----");
		System.out.println("---Facturacion-----");
		System.out.println("Tiene cuenta en el restaurante?  Si(Y) No(N):");
		String opcion = sc.next();
		boolean valido = false;
		while(valido != true) {
			if(opcion.equals("Y") || opcion.equals("y")) {
				System.out.println("Ingrese su id:");
				int id = sc.nextInt();
				for (int i = 0; i < c.cuentasClientes.size(); i++) {
					if (id == c.cuentasClientes.get(i).getIdCliente()) {
						c.pagarPedido(c.cuentasClientes.get(i), 100);
						valido = true;
					}else {
						System.out.println("Su id no coincide en nuestros registros");
						valido = false;
					}
				}
			}else if(opcion.equals("N") || opcion.equals("n")){
				c.crearCuenta();
				valido = false;
			}else {
				System.out.println("Ingrese una opcion valida");
			}
		}
	}
	
	
	public void servDesayuno(Scanner sc, ArrayList<String> orden) {
        Plato plato = new Plato();
        DecConcreteA d1 = new DecConcreteA(plato);
        DecConcreteB d2 = new DecConcreteB(d1);
        DecConcreteC d3 = new DecConcreteC(d2);
        //DecConcreteD d4 = new DecConcreteD(d3);
        //DecConcreteE d5 = new DecConcreteE(d4);
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
        //DecConcreteD d4 = new DecConcreteD(d3);
        //DecConcreteE d5 = new DecConcreteE(d4);
        opB = orden.get(2);
        opF = orden.get(3);
        switch(Integer.valueOf(orden.get(1))){
            case 1:
            	//genOpcional(orden.get(2), orden.get(3), d3, "almuerzo", 1);
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
	
	
	
	/*public void servicios(Scanner sc) {
        boolean salir = false;
        while(!salir){
            System.out.println("\n\nRESTAURANTE");
            System.out.println("  - Desayuno(1)\n  - Almuerzo(2)\n  - Cancelar(3)");
            System.out.println("¿Que servicio desea? ");
            int opc = sc.nextInt();
            switch(opc){
            case 1:
                    servicioDesayuno(sc);
                    break;
            case 2:
                    servicioAlmuerzo(sc);
                    break;
            case 3:
                    System.out.println("SERVICIO RESTURANTE CANCELADO");
                    salir=true;
                    break;
            default:
                    System.out.println("Digite una opcion valida");
            }
	    }
	}
	
	public void servicioDesayuno(Scanner sc) {
    	boolean salir = false;
            while(!salir){
                System.out.println("\n\nDESAYUNO");
                System.out.println("  - Huevos revueltos(1)\n  - Caldo(2)\n  - Cereal(3)\n  - Cancelar(4)");
                System.out.println("¿Que plato desea? ");
                int opc = sc.nextInt();
                Plato plato = new Plato();
                DecConcreteA d1 = new DecConcreteA(plato);
                DecConcreteB d2 = new DecConcreteB(d1);
                DecConcreteC d3 = new DecConcreteC(d2);
                //DecConcreteD d4 = new DecConcreteD(d3);
                //DecConcreteE d5 = new DecConcreteE(d4);
                String opB = "";
                String opF = "";
                switch(opc){
	                case 1:
	                	opB = opcional(sc,"bebida");
	                	opF = opcional(sc,"fruta");
	                	genOpcional(opB, opF, d3, "desayuno", 1);
	                    break;
	                case 2:
	                	opB = opcional(sc,"bebida");
	                	opF = opcional(sc,"fruta");
	                	genOpcional(opB, opF, d3, "desayuno", 2);
	                    break;
	                case 3:
	                	opB = opcional(sc,"bebida");
	                	opF = opcional(sc,"fruta");
	                	genOpcional(opB, opF, d3, "desayuno", 3);
	                    break;
	                case 4:
	                    System.out.println("SERVICIO CANCELADO");
	                    salir=true;
	                    break;
	                }
            }
	}
	
	public void servicioAlmuerzo(Scanner sc) {
    	boolean salir = false;
            while(!salir){
                System.out.println("\n\nALMUERZO");
                System.out.println("  - Arroz con pollo(1)\n  - Pescado frito(2)\n  - Cerdo con garbanzo(3)\n  - Cancelar(4)");
                System.out.print("¿Que plato desea? ");
                int opc = sc.nextInt();
                Plato plato = new Plato();
                DecConcreteA d1 = new DecConcreteA(plato);
                DecConcreteB d2 = new DecConcreteB(d1);
                DecConcreteC d3 = new DecConcreteC(d2);
                //DecConcreteD d4 = new DecConcreteD(d3);
                //DecConcreteE d5 = new DecConcreteE(d4);
                String opB = "";
                String opF = "";
                switch(opc){
                case 1:
                	opB = opcional(sc,"bebida");
                	opF = opcional(sc,"fruta");
                	genOpcional(opB, opF, d3, "almuerzo", 1);
                    break;
                case 2:
                	opB = opcional(sc,"bebida");
                	opF = opcional(sc,"fruta");
                	genOpcional(opB, opF, d3, "almuerzo", 2);
                    break;
                case 3:
                	opB = opcional(sc,"bebida");
                	opF = opcional(sc,"fruta");
                	genOpcional(opB, opF, d3, "almuerzo", 3);
                    break;
                case 4:
                    System.out.println("SERVICIO CANCELADO");
                    salir=true;
                    break;
                default:
                    System.out.println("Digite una opcion valida");
            }
        }
    }
	
	public String opcional(Scanner sc, String op) {
		System.out.print("¿Desea alguna " + op + "? Si(Y) No(N): ");
		String opcion = sc.next();
		boolean valido = false;
		while(valido != true) {
			if(opcion.equals("Y") || opcion.equals("y")) {
				opcion = "Y";
				valido = true;
			}else if(opcion.equals("N") || opcion.equals("n")){
				valido = true;
			}else {
				System.out.println("Ingrese una opcion valida");
			}
		}
		return opcion;
	}
	*/
	
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
	
	/*public void genOpcional(String opB, String opF, DecConcreteC d3, String comida ,int caso) {
		if(opB.equals("Y") || opB.equals("y")) {
			DecConcreteD d4 = new DecConcreteD(d3);
			if(opF.equals("Y")) {
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
			}else {
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
		}else {
			if(opF.equals("Y")) {
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
			}else {
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
	}*/
}
