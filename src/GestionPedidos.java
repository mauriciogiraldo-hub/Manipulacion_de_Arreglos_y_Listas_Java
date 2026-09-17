import java.util.Scanner;

/*
 * CLASE GestionPedidos (Main)
 * ---------------------------
 * Controlador del sistema Pizza-Track.
 * Implementa un sistema de Deshacer (Undo) y Rehacer (Redo) 
 * utilizando dos pilas manuales.
 */
public class GestionPedidos {

    public static void main(String[] args) {
        
        // --- INSTANCIAS DE LAS DOS PILAS ---
        // pilaPrincipal: Guarda la línea de tiempo real (Undo)
        Pila objPilaPrincipal = new Pila();
        
        // pilaSecundaria: Guarda el futuro alternativo / lo deshecho (Redo)
        Pila objPilaSecundaria = new Pila();
        
        // Lector seguro para el teclado
        Scanner objLector = new Scanner(System.in);
        int opcion = -1;

        System.out.println("=========================================");
        System.out.println(" 🍕 BIENVENIDO AL SISTEMA PIZZA-TRACK 🍕");
        System.out.println("=========================================");

        do {
            System.out.println("\n--- Menú de Gestión ---");
            System.out.println(" 1. Registrar Pizza (Nuevo pedido)");
            System.out.println(" 2. Deshacer (Undo)");
            System.out.println(" 3. Rehacer (Redo)");
            System.out.println(" 4. Mostrar Pedido Actual");
            System.out.println(" 0. Salir");
            System.out.print("Elija una opción: ");

            // Validación a prueba de balas: asegurar que sea un número
            if (objLector.hasNextInt()) {
                opcion = objLector.nextInt();
                objLector.nextLine(); // Limpiar el salto de línea del buffer

                switch (opcion) {
                    
                    case 1: // ---------- REGISTRAR PIZZA ----------
                        System.out.print("Ingrese el nombre de la pizza: ");
                        String nombre = objLector.nextLine();
                        
                        // Arreglo estricto de 3 posiciones para los ingredientes
                        String[] objIngredientes = new String[3];
                        System.out.println("Ingrese los 3 ingredientes principales:");
                        for (int i = 0; i < 3; i++) {
                            System.out.print("   Ingrediente " + (i + 1) + ": ");
                            objIngredientes[i] = objLector.nextLine();
                        }
                        
                        // Se crea el objeto Pizza y se apila en la principal
                        Pizza objPizzaNueva = new Pizza(nombre, objIngredientes);
                        objPilaPrincipal.push(objPizzaNueva);
                        
                        /*
                         * REGLA DE ORO DEL UNDO/REDO:
                         * Si el usuario deshace algo y luego escribe un pedido NUEVO, 
                         * el "futuro alternativo" se pierde. Hay que vaciar la secundaria.
                         */
                        objPilaSecundaria = new Pila(); 
                        
                        System.out.println("✅ ¡Pizza registrada y apilada con éxito!");
                        break;

                    case 2: // ---------- DESHACER (UNDO) ----------
                        if (objPilaPrincipal.isEmpty()) {
                            System.out.println("⚠️ No hay pedidos en la pila principal para deshacer.");
                        } else {
                            // 1. Sacar de la principal
                            Pizza objDeshecha = objPilaPrincipal.pop();
                            
                            // 2. Meter en la secundaria
                            objPilaSecundaria.push(objDeshecha);
                            
                            System.out.println("⏪ Deshecho: Se retiró la pizza '" + objDeshecha.nombre + "'.");
                        }
                        break;

                    case 3: // ---------- REHACER (REDO) ----------
                        if (objPilaSecundaria.isEmpty()) {
                            System.out.println("⚠️ No hay acciones deshechas para rehacer.");
                        } else {
                            // 1. Sacar de la secundaria
                            Pizza objRehecha = objPilaSecundaria.pop();
                            
                            // 2. Devolver a la principal
                            objPilaPrincipal.push(objRehecha);
                            
                            System.out.println("⏩ Rehizo: Se recuperó la pizza '" + objRehecha.nombre + "'.");
                        }
                        break;

                    case 4: // ---------- MOSTRAR PEDIDO ACTUAL ----------
                        Pizza objActual = objPilaPrincipal.peek(); // Solo mirar, sin sacar
                        
                        if (objActual == null) {
                            System.out.println("📭 La pila de producción está vacía. No hay pedidos activos.");
                        } else {
                            System.out.println("🍕 Pedido en el TOPE (listo para hacer):");
                            System.out.println("   " + objActual); // Automáticamente llama a toString()
                        }
                        break;

                    case 0: // ---------- SALIR ----------
                        System.out.println("Cerrando el sistema Pizza-Track. ¡Buen turno, parce!");
                        break;

                    default:
                        System.out.println("❌ Opción no válida. Seleccione un número del 0 al 4.");
                }
            } else {
                // Si el usuario escribe letras, evitamos que el programa se caiga
                System.out.println("❌ Error: Por favor ingrese un número entero válido.");
                objLector.next(); // Limpiamos la basura del teclado para evitar ciclo infinito
            }

        } while (opcion != 0);

        objLector.close(); // Buena práctica: liberar la memoria del escáner
    }
}