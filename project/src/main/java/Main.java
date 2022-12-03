import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String text="hamid has king";
        MyTree tree=new MyTree();
        tree.addRoot('s');
        String temp[]=text.split(" ");

        for(int i=0;i<temp.length;i++){
            tree.addWord(temp[i]);
        }

        List<String> values= (List<String>) tree.inOrderTraversal();
        for(String s:values){
            System.out.println(s);
        }
    }
}
