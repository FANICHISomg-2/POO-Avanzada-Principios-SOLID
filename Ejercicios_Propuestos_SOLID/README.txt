SOLUCIÓN - EJERCICIOS PROPUESTOS (SESIÓN N°03 - PRINCIPIOS SOLID)
==================================================================

CONTENIDO (carpeta src/):
  Ejercicio1_SRP_Empleado.java     -> Ejercicio 1 (SRP)
  Ejercicio2_OCP_Forma.java        -> Ejercicio 2 (OCP)
  Ejercicio3_LSP_Vehiculo.java     -> Ejercicio 3 (LSP)
  Ejercicio4_ISP_Imprimible.java   -> Ejercicio 4 (ISP)

CÓMO IMPORTAR EN ECLIPSE:
  1. File > New > Java Project (cualquier nombre, ej. "PracticaN03").
  2. Clic derecho en la carpeta "src" del proyecto > Import > General >
     File System > selecciona la carpeta "src" de este zip.
  3. Cada archivo .java es independiente y tiene su propio método main(),
     así que puedes correr cada ejercicio por separado:
     clic derecho sobre el archivo > Run As > Java Application.

CÓMO CORRER DESDE CONSOLA (sin Eclipse):
  cd src
  javac Ejercicio1_SRP_Empleado.java
  java Ejercicio1_SRP_Empleado
  (repetir cambiando el nombre para los otros 3 ejercicios)

NOTA IMPORTANTE PARA LA EXPOSICIÓN:
  Cada archivo tiene, en la parte de arriba, un bloque de comentarios que
  explica:
    - Cuál era el problema (qué principio se violaba).
    - Cómo se solucionó.
    - Un apartado "CÓMO MODIFICAR RÁPIDO EN CLASE" con ejemplos concretos
      de cambios que el ingeniero podría pedir en vivo (agregar una figura,
      un vehículo, una interfaz, etc.), para que el cambio se pueda hacer
      en 3-4 minutos sin romper el resto del código.

RESUMEN RÁPIDO DE CADA SOLUCIÓN:
  1) SRP: Empleado ahora SOLO guarda datos. CalculadoraPago es la única
     responsable de calcular el pago mensual.
  2) OCP: Forma es abstracta; se agregó Triangulo como una clase nueva,
     SIN tocar Forma, Circulo ni Rectangulo.
  3) LSP: Se analiza que Coche y Bicicleta SÍ cumplen LSP porque ambas
     aumentan la velocidad al acelerar (solo cambia el medio: motor o
     pedales), no lanzan excepciones. Se agregó un método de verificación
     que confirma que el contrato se respeta.
  4) ISP: Imprimible se dividió en Imprimible (imprimir) y Escaneable
     (escanear). Impresora solo implementa Imprimible; 
     ImpresoraMultifuncional implementa ambas.
