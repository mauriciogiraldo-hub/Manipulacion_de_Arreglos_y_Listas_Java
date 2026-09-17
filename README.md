# gestion_pizza_track — Pila y Sistema Undo/Redo en Java

Ejemplo sencillo de **pila (LIFO)** implementada a mano con listas ligadas para un simulador de pizzería (**Pizza-Track**), con sistema de **Deshacer (Undo)** y **Rehacer (Redo)** usando dos pilas independientes y un **arreglo fijo de 3** ingredientes.

No se usa `java.util.Stack`: todo está hecho con nodos enlazados. El enunciado está como comentario al inicio de [Pizza.java](src/Pizza.java).

## La pila

Estructura **LIFO** (*Last In, First Out*): el último pedido en entrar es el primero en salir o procesarse, como una pila de platos. Solo se toca un extremo: el **tope**.

| Método | Qué hace | Mueve punteros |
|---|---|---|
| `push()` | Ingresa una pizza en el tope | Sí |
| `pop()` | Saca la pizza del tope y la devuelve | Sí |
| `peek()` | Devuelve la pizza del tope **sin** sacarla | No |
| `isEmpty()` | Indica si la pila está vacía | No |

## Sistema Undo/Redo (Dos pilas)

El control de acciones se basa en mover referencias de objetos entre la **pila principal** (pedidos activos) y la **pila secundaria** (pedidos deshechos):

```java
// DESHACER (Undo)             // REHACER (Redo)
pizza = principal.pop();        pizza = secundaria.pop();
secundaria.push(pizza);        principal.push(pizza);
// pasa de activa a pausa       // recupera a activa
```

Si entran "Hawaiana", "Pepperoni" y "Mexicana" en ese orden:

```text
PILA PRINCIPAL                      PILA SECUNDARIA (Tras Undo)
  Mexicana -> Pepperoni -> Hawaiana    Mexicana -> null
  ^ tope = Mexicana                    ^ tope = Mexicana
    última en entrar, primera en salir   lista para ser rehecha
```

Si se registra un pedido **nuevo**, la pila secundaria se reinicia (`secundaria = new Pila()`) para descartar las acciones deshechas previamente y mantener la consistencia del historial.

## Estructura del proyecto

| Archivo | Rol |
|---|---|
| [Pizza.java](src/Pizza.java) | Datos (nombre + arreglo fijo de 3 ingredientes) + puntero `siguiente`: la clase **es** el nodo |
| [Pila.java](src/Pila.java) | `push`, `pop`, `peek`, `isEmpty` |
| [GestionPedidos.java](src/GestionPedidos.java) | Menú interactivo en consola y controlador de las pilas Undo/Redo |

## Cómo clonar

```bash
git clone [https://github.com/mauriciogiraldo-hub/Manipulacion_de_Arreglos_y_Listas_Java.git](https://github.com/mauriciogiraldo-hub/Manipulacion_de_Arreglos_y_Listas_Java.git)
```

```bash
cd gestion_pizza_track
```

Si no tienes Git instalado, en la página del repositorio está el botón verde **Code** y dentro la opción **Download ZIP**.

## Cómo ejecutar

Compilar (crea la carpeta `bin` con los archivos `.class`):

```bash
javac -encoding UTF-8 -d bin src/*.java
```

Ejecutar:

```bash
java -cp bin GestionPedidos
```

En Windows, si la consola muestra los acentos como símbolos raros, ejecutar esto antes de compilar:

```bash
chcp 65001
```

Desde **VS Code** basta con abrir la carpeta, abrir `src/GestionPedidos.java` y pulsar **Run** encima del método `main`. Requiere la extensión *Extension Pack for Java* y el JDK de Eclipse Temurin.

## Prueba sugerida

1. Opción `1` tres veces: ingresar las pizzas "Hawaiana", "Pepperoni" y "Mexicana".
2. Opción `4`: verificar que en el tope está "Mexicana", la última registrada.
3. Opción `2` (Undo): deshace "Mexicana". Al consultar con la opción `4`, el pedido actual pasa a ser "Pepperoni".
4. Opción `3` (Redo): recupera "Mexicana" y la devuelve al tope de la lista activa.