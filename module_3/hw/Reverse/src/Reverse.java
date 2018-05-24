public class Reverse {
    static String reverse(String s) {

        //basic cases where the string has only one character or does not exist

        if ( (s.length() <=1) || (s == null) ) {

            return s;

        } else {

            //get the substring without the first char, the (n-1)
            String sSub = s.substring(1);
            //recursive call
           return reverse(sSub) + s.charAt(0);

        }
    }

   // test

    public static void main (String[] args){
        // create test string
        String str = " this is working !";
        String end = reverse(str);

        System.out.println(str + " | " + end);

    }

}



