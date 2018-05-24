
import java.io.*;


public class Sort_Array {

    public static void main(String [] argv)throws IOException {
        // ask user for input
        System.out.println("Hello there, Please insert five strings divided by '/' ");

        //create reader for user inputs
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in),1);

        //divide user input into an array of five elements for future purpose
        String s  = stdin.readLine();
        String [] arrays = s.split("/");

        //check if there are five inputs
        if(arrays.length != 5 ){
            System.out.println(" We need 5 strings");
            main(new String[]{});

        }else {
            //print out each of the string
            printOut(arrays, "this is the ");

            System.out.println("________________________________________________________");

            //bubble sort array
            boolean swapped = true;
            int j = 0;
            String tmp;
            while (swapped) {
                swapped = false;
                j++;
                for (int i = 0; i < arrays.length - j; i++) {

                    if (arrays[i].compareTo(arrays[i+1]) > 0) {
                        tmp = arrays[i];
                        arrays[i] = arrays[i + 1];
                        arrays[i + 1] = tmp;
                        swapped = true;
                    }
                }
            }

            //print out arrays again
            printOut(arrays, "After sorting, this is the ");

        }

    }

    public static void printOut(String[] arrgv, String title){
        for (int i = 0; i < arrgv.length ; i++) {
            arrgv[i] = arrgv[i].trim();
            System.out.println(title + (i + 1) + "th: " + arrgv[i]);
        }
    }

}