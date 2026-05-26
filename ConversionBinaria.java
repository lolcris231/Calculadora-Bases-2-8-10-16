
public class ConversionBinaria {

    // Convierte de Decimal a Binario (Base 10 a Base 2)
    public static String convertirDecimalABinario(int numeroDecimal) {
        String numeroBinario = "";
        int residuo = 0;

        if (numeroDecimal == 0) {
            return "0";
        }

        while (numeroDecimal > 0) {
            residuo = numeroDecimal % 2;
            numeroBinario = residuo + numeroBinario;
            numeroDecimal = numeroDecimal / 2;
        }

        return numeroBinario;
    }

    // Convierte de Binario a Decimal (Base 2 a Base 10)
    public static int convertirBinarioADecimal(String numeroBinario) {
        int numeroDecimal = 0;
        int valorPosicional = 1;
        int digito = 0;

        for (int pos = numeroBinario.length() - 1; pos >= 0; pos--) {
            digito = numeroBinario.charAt(pos) - '0';
            numeroDecimal = numeroDecimal + (digito * valorPosicional);
            valorPosicional = valorPosicional * 2;
        }

        return numeroDecimal;
    }
}