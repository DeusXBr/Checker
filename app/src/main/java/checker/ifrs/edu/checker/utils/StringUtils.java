package checker.ifrs.edu.checker.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringUtils
{

    private static final String DATE_NORMAL = "dd/MM/yyyy";
    private static final String DATE_ESCRITO = "dd 'de' MMMM 'de' yyyy";

    /**
     * Método verifica se uma string está nulla ou vazia
     * @param str
     * @return true = está nulo ou vazio, false = está preenchido
     */
    public static boolean isNullOrEmpty(String str)
    {
        return str == null || str.length() == 0;
    }

    /**
     *
     * @param number
     * @return true = é negativo ou zero, false = numero positivo
     */
    public static boolean isNegativeOrZero(int number)
    {
        return number <= 0;
    }

    /**
     * Método retorna data atual no formato brasileiro
     * @return data atual
     */
    public static String getDataAtual()
    {
        Locale localeBR = new Locale("pt", "BR");
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_NORMAL, localeBR);
        return sdf.format(new Date());
    }

    /**
     * Método limita o comprimento de uma string
     * @param texto
     * @param quantidade
     * @return texto
     */
    public static String mostrarQuantosCaracteres(String texto, int quantidade)
    {
        if ( texto.length() > quantidade )
        {
            texto = texto.substring(0,quantidade) + " ...";
        }

        return texto;
    }

    /**
     * Metodo coloca o primeiro caracter da string em maiusculo
     * @param string texto/palavra qualquer
     * @return string modificada
     */
    public static String primeiroCaracterMaiusculo(String string)
    {
        if (!string.isEmpty())
        {
            return string.substring(0, 1).toUpperCase() + string.substring(1);
        }

        return string;
    }
}
