import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Parser {

    public static ArrayList<ArrayList> getAllData(String fileName) throws IOException, CsvException{
        ArrayList<ArrayList> result = new ArrayList<ArrayList>();
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<String[]> r = reader.readAll();
            for(String[] array : r){
                ArrayList<String> list = new ArrayList<String>();
                Collections.addAll(list, array);
                result.add(list);
            }
        }
        return result;
    }

    public static ArrayList<ArrayList> getSpecificData(int x, String fileName) throws IOException, CsvException{
        ArrayList<ArrayList> result = new ArrayList<ArrayList>();
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<String[]> r = reader.readAll();
            for(String[] array : r){
                ArrayList<String> list = new ArrayList<String>();
                Collections.addAll(list, array);
                if(list.get(x).equals("0") == false) {
                    result.add(list);
                }
            }
        }
        return result;
    }

    public static int getRows(String fileName) throws IOException, CsvException{
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<String[]> r = reader.readAll();
            return r.get(0).length;
        }
    }

}
