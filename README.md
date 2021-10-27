# Proyecto_II_AyED

## Introducción y Descripción de la Problemática

Este proyecto consiste en construir una calculadora que evalúa expresiones de longitud arbitraria. Con ese fin se utilizará un árbol de expresión binaria. La calculadora solo realizará operaciones algebraicas simples (+, -, *, /,%), pero podrá manejar expresiones de cualquier longitud correctamente colocando la expresión en un árbol de expresiones binarias y luego evaluando el árbol de expresiones.

Un árbol de expresión binaria es un árbol binario, que tiene como máximo dos hijos. Recuerde que existen dos tipos de nodos en un árbol binario, los nodos hoja que no tienen hijos y los nodos internos que tienen uno o más hijos (y forman el cuerpo del árbol). En un árbol de expresión binaria, los nodos internos contendrán los operadores de la expresión (+, -, *, /,%). Los nodos hoja contendrán los operandos de la expresión (en nuestro caso, valores enteros).

El algoritmo para construir un árbol de expresión requiere utilizar la notación postfija (también conocida como notación polaca inversa, una versión modificada de una notación matemática inventada por matemáticos polacos a principios del siglo XX). La notación de sufijo se usa ampliamente en los círculos de computación porque las expresiones anotadas en notación de sufijo son completamente inequívocas sin tener que recurrir a paréntesis. 

## Diagrama de Clases
![image](https://user-images.githubusercontent.com/85046754/138995068-546fc6aa-24ba-4e31-83ca-e5b38b3e72a2.png)


## Problemas encontrados
La mayoría de los problemas presentados en proyecto son en cuestiones de código en general, aunado a esto se presnetaron problemas con los commits en github.
Uno de los problemas presentados fue a la hora de integrar el servidor Tomcat a al proyecto, ya que se crearon problemas de compatibilidad e integración de las clases de Java con el servidor, en cuanto al manejo y proyección de datos en la pagina web mediante el servidor.

Otro de los problemas que se nos presentó, en la elaboración del proyecto, fue la instanciacion de una clase propia de Java la cual es llamada CSVWriter, la cual es una clase la cual contiene métodos para poder escribir y leer el contenido de el archivo que contiene el formato CSV, ya que a la hora de instanciarla hubieron conflictos con el código.

Uno de los problemas más significativos, fue que el árbol de expresión binaria utilizado para la el almacenamiento de la expresión que el usuario ingresa en la página, ya que dicho árbol se construía correctamente, pero no era capaz de calcular el resultado de la operación.

Otro de los problemas más significativos, fue que el datatable implementado en la página para mostrar el historial de expresiones ingresadas por cada cliente, no era capaz de leer el archivo que contiene el formato CSV con la información mencionada.
