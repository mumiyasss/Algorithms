import java.util.regex.*;

/*
    This app validates assignment operator in Java using automata.
    For example:
            x = 10; y = x;  -- correct
    
    Automata've been implemented by state method using two arrays.
*/
public class Automata {
    
    public static void main(String [] args) {
        /*
            BASH: to type ";", u have to type "\;"
        */
        String thisString = "";
        for(String arg : args) {
        	thisString += (arg + " ");
        }

                        // STATES:
        int [][]f =     // 0  1  2  3  4  5 (5 - это мусорка)
            /*  Char */ {{ 1, 1, 3, 3, 5, 5},
            /* Digit */  { 5, 1, 4, 3, 4, 5},
            /*     = */  { 5, 2, 5, 5, 5, 5},
            /*     ; */  { 5, 5, 5, 0, 0, 5}};

        int finalState = 0;
        int state = 0;
        
        for(char x : thisString.toCharArray()) {
            if(x == ' ')
                continue;
            if(isChar(x))
                state = f[0][state]; else
            if(isDigit(x))
                state = f[1][state]; else
            if(x == '=')
                state = f[2][state]; else
            if(x == ';')
                state = f[3][state];
            //System.out.println(x + " state: " + state);
        }
        System.out.println(isFinal(state, finalState));
    }

    public static boolean isFinal(int state, int finalState) {
        return finalState == state ? true : false;
    }

    public static boolean isDigit(char ch) {
        String c = "" + ch;
        Pattern p = Pattern.compile("[0-9]");  
        Matcher m = p.matcher(c);  
        return m.matches();
    }

    public static boolean isChar(char ch) {
        String c = "" + ch;
        Pattern p = Pattern.compile("[A-Za-z]");  
        Matcher m = p.matcher(c);  
        return m.matches();
    }

}    