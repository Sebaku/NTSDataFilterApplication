import javax.swing.*;
import java.io.*;


public class FileSelector {

    public static String chooseFile() {
        File file;
        int result;
        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        result = chooser.showOpenDialog(null);

        if(result == JFileChooser.APPROVE_OPTION){
            file = chooser.getSelectedFile();
            return file.toString();
        }
        return null;
    }
}
