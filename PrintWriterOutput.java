package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintWriterOutput {
    public PrintWriterOutput() {

    }

    public static void main(String[] args) {
        try{
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File("login_activity.txt"), true));
            pw.append("Test");

            pw.close();
        }catch (FileNotFoundException var2){
            Logger.getLogger(PrintWriterOutput.class.getName()).log(Level.SEVERE, (String) null, var2);
        }
    }
}
