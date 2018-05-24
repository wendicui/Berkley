package Printline;
import java.io.IOException;
import java.net.URL;

public class test {
    test() {
    }

    public static void main(String[] args)
                            throws IOException
    {
        String testAddress = "http://www.greens.org/about/software/editor.txt";
        try{
         //  PrintLine pl = new PrintLine(testAddress, 0);
//            PrintLine pl1 = new PrintLine(testAddress, 200);
            PrintLine pl2 = new PrintLine(testAddress, -158);
        }catch(ToolargeNumber tl){}

    }
}
