package checker.ifrs.edu.checker.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringUtils {

    public static boolean isNullOrEmpty(String str){
        return str == null || str.length() == 0;
    }

    public static boolean isNegativeOrZero(int number){
        return number <= 0;
    }

    public static String getDataAtual(){
        Locale localeBR = new Locale("pt", "BR");
        SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", localeBR);
        //Log.i("MeuTeste", sdf.format(new Date()));
        return sdf.format(new Date());
    }
}
