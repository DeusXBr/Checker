package checker.ifrs.edu.checker.utils;

public class StringUtils {

    public static boolean isNullOrEmpty(String str){
        return str == null || str.length() == 0;
    }

    public static boolean isNegativeOrZero(int number){
        return number <= 0;
    }
}
