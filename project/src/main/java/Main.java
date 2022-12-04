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
            tree.addWord(temp[i],"58043");
        }

        System.out.println(tree.searchWord("guaranteedDoug"));
        List<String> names=tree.getListsName("GuaranteedDoug");
        System.out.println(names.size());
    }

    private static String getTrueString(String text){
        StringBuilder temp=new StringBuilder();

        for(int i=0;i<text.length();i++){
            char c=text.charAt(i);
            if(Character.isLetter(c)){
                temp.append(c);
            }
            else {
                char tempChar=' ';
                if (c == '\'') {
                    tempChar=c;
                }

                if (i > 0 && Character.isLetter(text.charAt(i - 1))) {
                    temp.append(tempChar);
                }
            }
        }

        return temp.toString();
    }
}