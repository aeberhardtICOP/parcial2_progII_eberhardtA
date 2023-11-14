import java.util.Scanner;

import model.Estado;
import model.Liberada;
import model.Mesa;
import model.Resto;

public class Principal {

	public static void main(String[] args) {
		Resto paucke = new Resto("Paucke ATENEO Inmaculada", "lopez 2545", "Santa FE");
		
		paucke.cargarMesas(4, 2);
		paucke.cargarMesas(4, 4);
		paucke.cargarMesas(3, 6);
		
		
		 Scanner scanner = new Scanner(System.in);

	        int opcion;
	        do {
	            System.out.println("Menú:");
	            System.out.println("1. Mostrar todas las mesas");
	            System.out.println("2. Mostrar información del restaurante para una fecha");
	            System.out.println("3. Listar mesas por estado");
	            System.out.println("4. Listar mesas por estado y fecha");
	            System.out.println("5. Mostrar información de capacidad");
	            System.out.println("6. Dar de alta una nueva mesa");
	            System.out.println("7. Dar de baja una mesa");
	            System.out.println("8. Dar de alta paquete de mesas");
	            System.out.println("9. Cambiar estado de mesa");
	            System.out.println("10. Generar una reserva");
	            System.out.println("-1. Salir");
	            System.out.print("Seleccione una opción: ");
	            opcion = scanner.nextInt();

	            switch (opcion) {
	                case 1:
	                	paucke.mostrarMesas();
	                    break;
	                case 2:
	                	System.out.println("Ingrese opcion: \n1)Fecha Actual\n2)Fecha Especifica");
	                	int op=scanner.nextInt();
	                    switch(op) {
	                    case 1:
	                    	paucke.mostrarInformacion();
	                    	break;
	                    case 2:
	                    	System.out.println("Ingrese fecha: (FORMATO dd/mm/aaaa");
	                    	scanner.nextLine();
	                    	String fecha = scanner.nextLine();
	                    	paucke.mostrarInformacion(fecha);
	                    	
	                    	break;
	                    	default:
	                    		System.out.println("No ingreso opcion valida..");
	                    }
	                    break;
	                case 3:
	                   System.out.println("Ingrese estado por el cual desea listar: "
	                + "\n1)Liberada"
	                + "\n2)Reservada"
	                + "\n3)Ocupada");
	                   
	                   int o = scanner.nextInt();
	                   
	                   switch(o) {
	                   case 1:
	                	   paucke.listarMesas("Liberada");
	                	   break;
	                   case 2:
	                	   paucke.listarMesas("Reservada");
	                	   break;
	                   case 3:
	                	   paucke.listarMesas("Ocupada");
	                	   break;
	                	   default:
	                		   System.out.println("No ingreso opcion valida...");
	                   }
	                    break;
	                case 4:
	                    
	                    break;
	                case 5:
	                    
	                    break;
	                case 6:
	                   
	                    break;
	                case 7:
	                    
	                    break;
				    case 8:
	                    System.out.println("Ingrese nro de mesas:");
	                    int nroMesas=scanner.nextInt();
	                    System.out.println("Ingrese nro de comensales:");
	                    int nroComensales = scanner.nextInt();
	                    
	                    paucke.cargarMesas(nroMesas, nroComensales);
	                    break;
				    case 9:
				    	paucke.cambiarEstadoMesa();
				    	break;
				    case 10:
				    	
				    	break;
	                case -1:
	                    System.out.println("Saliendo del programa.");
	                    break;
	                default:
	                    System.out.println("Opción no válida. Intente de nuevo.");
	                    break;
	            }
	        } while (opcion != -1);
	    }
}
