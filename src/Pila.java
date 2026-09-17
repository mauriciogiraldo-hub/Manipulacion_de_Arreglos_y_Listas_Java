/*
 * CLASE Pila
 * ----------
 * Estructura de datos LIFO (Last In, First Out).
 * Se encarga de apilar y desapilar los pedidos de Pizza.
 */
public class Pila {

    // El "tope" es nuestra única puerta de entrada y salida de la pila.
    // Apunta siempre a la última pizza que se registró.
    private Pizza tope;

    // Constructor: cuando la pila nace, está vacía.
    public Pila() {
        this.tope = null;
    }

    // =========================================================
    // 1. PUSH: Agregar una pizza a la cima de la pila
    // =========================================================
    public void push(Pizza nuevaPizza) {
        // PASO CRÍTICO: La nueva pizza debe apuntar hacia abajo, 
        // al que era el tope anterior.
        nuevaPizza.siguiente = tope; 
        
        // Ahora sí, la nueva pizza se convierte en el nuevo tope oficial.
        tope = nuevaPizza; 
    }

    // =========================================================
    // 2. POP: Sacar y devolver la pizza de la cima
    // =========================================================
    public Pizza pop() {
        // Si la pila está vacía, no hay nada que sacar.
        if (isEmpty()) {
            return null;
        }

        // 1. Guardamos la pizza de arriba en una variable auxiliar.
        Pizza pizzaSacada = tope; 
        
        // 2. El tope baja al siguiente nivel (al que estaba debajo).
        tope = tope.siguiente; 
        
        // 3. Desconectamos la pizza que sacamos para que no arrastre 
        // el resto de la lista (súper importante para la memoria).
        pizzaSacada.siguiente = null; 

        // 4. Entregamos la pizza.
        return pizzaSacada;
    }

    // =========================================================
    // 3. PEEK: Mirar la pizza de la cima SIN sacarla
    // =========================================================
    public Pizza peek() {
        if (isEmpty()) {
            return null;
        }
        return tope; // Solo miramos, no movemos ningún puntero.
    }

    // =========================================================
    // 4. ISEMPTY: Preguntar si la pila está vacía
    // =========================================================
    public boolean isEmpty() {
        // Si el tope es null, significa que no hay nada en la pila.
        return tope == null;
    }
}