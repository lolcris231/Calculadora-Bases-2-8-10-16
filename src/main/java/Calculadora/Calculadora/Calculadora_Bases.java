package Calculadora.Calculadora;

import java.util.Scanner;

public class Calculadora_Bases {

    // Autor: Robinson Alessandro Tandazo Reyes

    // Funcion que sirve para convertir de decimal a octal (base 10 a base 8).
    public static String convertirDecimalAOctal(int numeroDecimal) {

        // Para poder convertir de decimal a octal, se debe dividir el numero decimal entre 8 y 
        // almacenar el residuo de cada division. El numero octal resultante se obtiene 
        // concatenando los residuos obtenidos en orden inverso.

        String numeroOctal = ""; // Variable que almacenara el numero octal resultante.

        int residuo = 0; // Variable que almacenara el residuo de la division entre el numero decimal y 8.

        if (numeroDecimal == 0) { // Si el numero decimal es 0, el numero octal resultante tambien sera 0.
            return "0";
        }

        // Mientras el numero decimal sea mayor que 0, se realizara la division entre el numero decimal y 8,
        // y se almacenara el residuo de cada division. El numero octal resultante se obtendra 
        // concatenando los residuos obtenidos en orden inverso.

        while (numeroDecimal > 0) { 

            residuo = numeroDecimal % 8; // Se obtiene el residuo de la division entre el numero decimal y 8.

            numeroOctal = residuo + numeroOctal; // Se concatena el residuo obtenido al numero octal resultante.

            numeroDecimal = numeroDecimal / 8; // Se actualiza el numero decimal dividiendolo entre 8 para la siguiente iteracion.
        }

        return numeroOctal; // Se retorna el numero octal resultante.
    }

    // Autor: Robinson Alessandro Tandazo Reyes

    // Funcion que sirve para convertir de octal a decimal (base 8 a base 10).
   
    public static int convertirOctalADecimal(String numeroOctal) {

        int numeroDecimal = 0; // Variable que almacenara el numero decimal resultante.

        int valorPosicional = 1; // Variable que almacenara el valor posicional de cada digito del numero octal. El valor posicional se calcula como 8 elevado a la posicion del digito, comenzando desde 0 para el digito mas a la derecha.

        int digito = 0; // Variable que almacenara el valor del digito actual del numero octal que se esta procesando. El valor del digito se obtiene restando el caracter '0' al caracter del digito, lo que convierte el caracter a su valor numerico correspondiente.
 
        // Para convertir de octal a decimal, se debe recorrer cada digito del numero octal 
        // desde la derecha hacia la izquierda, multiplicando el valor del digito por su valor 
        // posicional correspondiente y sumando el resultado al numero decimal resultante. El 
        // valor posicional se actualiza multiplicandolo por 8 en cada iteracion para reflejar 
        // el cambio de posicion del digito.

        for (int pos = numeroOctal.length() - 1; pos >= 0; pos--) { 

            // La funcion charAt(pos) se utiliza para obtener el caracter en la posicion especificada del numero octal, 
            // y al restar el caracter '0' al caracter del digito, se convierte el caracter a su valor numerico 
            // correspondiente. Por ejemplo, si el digito es '5', al restar '0' se obtiene el valor numerico 5.

            digito = numeroOctal.charAt(pos) - '0'; // Se obtiene el valor del digito actual del numero octal restando el caracter '0' al caracter del digito.

            numeroDecimal = numeroDecimal + (digito * valorPosicional); // Se multiplica el valor del digito por su valor posicional y se suma al numero decimal resultante.

            valorPosicional = valorPosicional * 8; // Se actualiza el valor posicional multiplicandolo por 8 para reflejar el cambio de posicion del digito en la siguiente iteracion.
        }

        return numeroDecimal; // Se retorna el numero decimal resultante.
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int opcion = 0; // Variable que almacenara la opcion seleccionada por el usuario en el menu.
 
        int numeroDecimal = 0; // Variable que almacenara el numero decimal ingresado por el usuario para convertirlo a octal.

        String numeroOctal = ""; // Variable que almacenara el numero octal ingresado por el usuario para convertirlo a decimal.

        // ESTE MENU ES POR AHORA, YA QUE AUN NO HAY INTERFAZ GRAFICA, PERO SE PUEDE USAR PARA PROBAR LAS FUNCIONES DE CONVERSION DE BASES.
        // ESTE MENU ES POR AHORA, YA QUE AUN NO HAY INTERFAZ GRAFICA, PERO SE PUEDE USAR PARA PROBAR LAS FUNCIONES DE CONVERSION DE BASES.
        // ESTE MENU ES POR AHORA, YA QUE AUN NO HAY INTERFAZ GRAFICA, PERO SE PUEDE USAR PARA PROBAR LAS FUNCIONES DE CONVERSION DE BASES.

        do { 
            System.out.println("-------------- MENU --------------");

            System.out.println("(1) Decimal a Octal");

            System.out.println("(2) Octal a Decimal");

            System.out.println("(3) Salir");

            System.out.println("----------------------------------");

            System.out.println("Ingrese una opcion:");

            opcion = sc.nextInt(); // Se lee la opcion seleccionada por el usuario.

            switch (opcion) {

                case 1:

                    System.out.println("Ingrese un numero decimal:");

                    numeroDecimal = sc.nextInt();

                    System.out.println("Numero en octal:");

                    System.out.println(convertirDecimalAOctal(numeroDecimal));

                    break;

                case 2:

                    System.out.println("Ingrese un numero octal:");

                    numeroOctal = sc.next();

                    System.out.println("Numero en decimal:");

                    System.out.println(convertirOctalADecimal(numeroOctal));

                    break;

                case 3:

                    System.out.println("Gracias por usar el programa.");

                    break;

                default:

                    System.out.println("Opcion invalida");

            }

        } while (opcion != 3);

    }

}