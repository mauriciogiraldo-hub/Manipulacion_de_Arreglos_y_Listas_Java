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