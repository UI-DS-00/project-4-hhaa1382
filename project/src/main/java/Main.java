import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        MyTree values=Read.readData();
        System.out.println(values.getListsName("the"));
    }
}