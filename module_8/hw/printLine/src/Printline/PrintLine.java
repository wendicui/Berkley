package Printline;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;



public class PrintLine {

    private int currentLine = 1;

    public PrintLine(String address, int num)
            throws IOException, ToolargeNumber {   //get inputstream
        URL url = new URL(address);
        URLConnection conne = url.openConnection();
        InputStream input = conne.getInputStream();

        //Files.copy(input, "./transit.txt", StandardCopyOption.REPLACE_EXISTING);


        //print out
        String line = null;
        LineNumberReader lnr = new LineNumberReader(new InputStreamReader(input));
        //  BufferedReader br = new BufferedReader(new InputStreamReader(input));

            //check num
            if (num == 0) {

                while ((line = lnr.readLine()) != null) {
                    System.out.println(line);
                    currentLine++;
                }

                System.out.println("________________________________________");
            } else if (num > 0) {
                //check which line it is now;

                while (currentLine <= num) {
                    line = lnr.readLine();
                    currentLine++;
                    if(line == null){throw new ToolargeNumber(); }
                    System.out.println(line);
                }
                System.out.println("+++++++++++++++++++++++++++++++++++++++++");
            } else {

                //create a file to write the url information in, to get the total line number
                BufferedWriter writer = new BufferedWriter(new FileWriter("files.txt"));

                while ((line = lnr.readLine()) != null) {
                    //System.out.println(line);
                    writer.write(line);
                    writer.write("\n");
                   // writer.write("here");
                    currentLine++;
                }
                writer.close();

                //read from the file to get the last few lines.
                if((num + currentLine) < 0){
                    throw new ToolargeNumber();
                }
//
                BufferedReader br = new BufferedReader(new FileReader("files.txt"));

               // System.out.println(numberskipped);

//
                int currentFileLine = 1;
                while ((line = br.readLine()) != null) {

                    currentFileLine ++;
                    if(currentFileLine >= (currentLine + num) ) {
                        System.out.println(line);
                    }
                }

                br.close();
            }


            lnr.close();


    }
}

