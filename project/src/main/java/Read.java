import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Read {
    public static String readWords() throws FileNotFoundException {
        Scanner reader=new Scanner(new File("C:\\Users\\Salam\\Desktop\\Homework\\Data structure\\Tree Project\\data\\EnglishData\\58043"));
        StringBuilder temp=new StringBuilder();

        while (reader.hasNextLine()){
            temp.append(reader.nextLine());
        }

        reader.close();
        return temp.toString();
    }
}
