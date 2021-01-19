import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {
    public ArrayList<String> read () throws IOException {
        File file = new File("dictionary.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        ArrayList<String> dictionary=new ArrayList<>();
        int i=0;

        while ((st = br.readLine()) != null) {
            dictionary.add(st);
            i++;
        }
        return  dictionary;
    }

}
