import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        String text="would";
        String text=getTrueString(Read.readWords());
        System.out.println(text+"\n\n");
        MyTree tree=new MyTree();
        tree.addRoot('s');
        String temp[]=text.split(" ");

        for(int i=0;i<temp.length;i++){
            tree.addWord(temp[i],"57111");
        }

        List<String> values= (List<String>) tree.inOrderTraversal();
        for(String s:values){
            System.out.println(s);
        }
    }

    private static String getTrueString(String text){
        StringBuilder temp=new StringBuilder();

        for(int i=0;i<text.length();i++){
            char c=text.charAt(i);
            if(Character.isLetter(c)){
                temp.append(c);
            }
            else if(c==' ' || c=='\''){
                if(i>0 && Character.isLetter(text.charAt(i-1))){
                    temp.append(c);
                }
            }
            else{
                if(i>0 && Character.isLetter(text.charAt(i-1))){
                    temp.append(' ');
                }
            }
        }

        return temp.toString();
    }
}