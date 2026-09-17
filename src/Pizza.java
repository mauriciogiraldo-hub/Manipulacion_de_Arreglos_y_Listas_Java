/* 
* ENUNCIADO DEL PROBLEMA
* ----------------------
*Desarrollarán una aplicación en Java que simule el sistema de gestión de pedidos de una pizzería (Pizza-Track), 
*la cual debe permitir las siguientes operaciones en consola:
*
*Registrar Pedido (Escribir): El usuario ingresa el nombre de la pizza y un arreglo fijo de 3 ingredientes. 
*Este objeto se almacena en la pila principal.
*
*Deshacer (Undo): Elimina el último pedido realizado (la última pizza registrada).
*
*Rehacer (Redo): Recupera la pizza que se acaba de deshacer y la devuelve a la lista de pedidos activos.
*
*Arquitectura del Proyecto: El sistema debe gestionar el flujo mediante el uso de dos pilas manuales basadas en listas ligadas:
*
*Pila Principal: Almacena los pedidos realizados para permitir la acción de "Deshacer".
*
*Pila Secundaria: Almacena temporalmente los pedidos deshechos para permitir su recuperación inmediata ("Rehacer"). 
*/



/*
 * CLASE Pizza
 * -----------
 * Representa un pedido de pizza y funciona como NODO para la pila.
 * Guarda los datos de la pizza y el puntero al siguiente elemento.
 */

public class Pizza {

    // --- Datos de la pizza ---
    String nombre;
    
    // Arreglo fijo de tamaño 3 para los ingredientes.
    String[] ingredientes = new String[3];

    // --- Puntero de la pila ---
    // Apunta a la pizza que quedó DEBAJO de esta en la pila.
    Pizza siguiente;

    /*
     * Constructor
     * -----------
     * Inicializa el nombre y hace una copia profunda (Deep Copy) del arreglo
     * de ingredientes para evitar que se crucen los datos en memoria.
     */
    public Pizza(String nombre, String[] ingredientes) {
        this.nombre = nombre;
        this.siguiente = null; // Toda pizza nueva entra sin vecino debajo

        // Copiamos los ingredientes uno por uno con el for
        for (int i = 0; i < 3; i++) {
            this.ingredientes[i] = ingredientes[i];
        }
    }

    /*
     * toString()
     * ----------
     * Sobreescribe el método por defecto de Java para mostrar los datos 
     * de la pizza como un texto entendible al imprimir el objeto.
     */
    @Override
    public String toString() {
        // Armamos un texto con los ingredientes separados por coma
        String lista = ingredientes[0] + ", " + ingredientes[1] + ", " + ingredientes[2];
        
        return "Pizza: " + nombre + " [Ingredientes: " + lista + "]";
    }
}