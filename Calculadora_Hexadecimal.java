import java.util.Scanner;

public class Calculadora_Hexadecimal {

    // Funcion para convertir de decimal a hexadecimal.
    public static String convertirDecimalAHexadecimal(int numeroDecimal) {

        // Arreglo con los simbolos hexadecimales.
        char[] simbolosHex = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };

        String numeroHexadecimal = "";

        int residuo = 0;

        // Caso especial cuando el numero es 0.
        if (numeroDecimal == 0) {
            return "0";
        }

        // Conversion mediante divisiones sucesivas entre 16.
        while (numeroDecimal > 0) {

            residuo = numeroDecimal % 16;

            numeroHexadecimal = simbolosHex[residuo] + numeroHexadecimal;

            numeroDecimal = numeroDecimal / 16;
        }

        return numeroHexadecimal;
    }

    // Funcion para convertir de hexadecimal a decimal.
    public static int convertirHexadecimalADecimal(String numeroHexadecimal) {

        int numeroDecimal = 0;

        int valorPosicional = 1;

        int valorDigito = 0;

        // Convertimos todo a mayusculas para evitar problemas.
        numeroHexadecimal = numeroHexadecimal.toUpperCase();

        // Recorremos el hexadecimal de derecha a izquierda.
        for (int pos = numeroHexadecimal.length() - 1; pos >= 0; pos--) {

            char caracter = numeroHexadecimal.charAt(pos);

            // Si es un numero del 0 al 9.
            if (caracter >= '0' && caracter <= '9') {

                valorDigito = caracter - '0';
            }

            // Si es una letra de la A a la F.
            else if (caracter >= 'A' && caracter <= 'F') {

                valorDigito = caracter - 'A' + 10;
            }

            // Si el caracter no es valido.
            else {

                System.out.println("Numero hexadecimal invalido.");

                return -1;
            }

            numeroDecimal = numeroDecimal + (valorDigito * valorPosicional);

            valorPosicional = valorPosicional * 16;
        }

        return numeroDecimal;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int opcion = 0;

        int numeroDecimal = 0;

        String numeroHexadecimal = "";

        do {

            System.out.println("----------- MENU -----------");

            System.out.println("(1) Decimal a Hexadecimal");

            System.out.println("(2) Hexadecimal a Decimal");

            System.out.println("(3) Salir");

            System.out.println("----------------------------");

            System.out.println("Ingrese una opcion:");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1:

                    System.out.println("Ingrese un numero decimal:");

                    numeroDecimal = sc.nextInt();

                    System.out.println("Numero en hexadecimal:");

                    System.out.println(
                        convertirDecimalAHexadecimal(numeroDecimal)
                    );

                    break;

                case 2:

                    System.out.println("Ingrese un numero hexadecimal:");

                    numeroHexadecimal = sc.next();

                    System.out.println("Numero en decimal:");

                    System.out.println(
                        convertirHexadecimalADecimal(numeroHexadecimal)
                    );

                    break;

                case 3:

                    System.out.println("Gracias por usar el programa.");

                    break;

                default:

                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 3);

        sc.close();
    }
}