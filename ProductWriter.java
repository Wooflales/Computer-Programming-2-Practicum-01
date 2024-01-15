import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static java.nio.file.StandardOpenOption.CREATE;
public class ProductWriter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList <String>recs = new ArrayList<>();
        //Variables declared
        boolean stillgoing = true;
        do {
            String record = "";
            String fields[];
            //The user is asked for every input with SafeInput
            String id = SafeInput.getNonZeroLenString(in, "Input the product ID");
            String name = SafeInput.getNonZeroLenString(in, "Input the product name");
            String description = SafeInput.getNonZeroLenString(in, "Input the product description");
            int cost = SafeInput.getRangedInt(in, "Input the cost of the product in dollars (1-200)", 1, 200);
            //Record is ing title = SafeInput.getNonZeroLenString(in, "Input your title");saved
            record = id + "," + name + "," + description + "," + cost;
            fields = record.split(",");
            recs.add(record);
            //User is asked if they want to end the program
            boolean endprogram = SafeInput.getYNConfirm(in, "Would you like to keep going?");
            if (endprogram == false) {
                stillgoing = false;
            }
        }while(stillgoing == true);
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt");

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String rec : recs)
            {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }




    }
}

