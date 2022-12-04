import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static MyTree tree;
    public static void main(String[] args) throws FileNotFoundException {
        new Page();
    }

    private static String checkSearchText(String text){
        text=checkText(text);
        List<String> tempSpace=spaceListCheck(checkSpace(text));
        minusListCheck(checkMinus(text),tempSpace);
        plusListCheck(checkPlus(text),tempSpace);
        return tempSpace.toString();
    }

    private static List<String> spaceListCheck(String text){
        String[] values=text.split(" ");
        if(values.length>0) {
            List<String> first = listCopy(tree.getListsName(values[0]));
            for (int i = 1; i < values.length; i++) {
                List<String> temp = tree.getListsName(values[i]);
                for (int j = 0; j < first.size(); j++) {
                    if (!temp.contains(first.get(j))) {
                        first.remove(first.get(j));
                        j--;
                    }
                }
            }
            return first;
        }
        return null;
    }

    private static void plusListCheck(String text,List<String> listName){
        if(!text.isBlank()) {
            String[] values = text.split(" ");
            if (values.length > 0) {
                List<String> first = listCopy(tree.getListsName(values[0]));
                for (int i = 1; i < values.length; i++) {
                    List<String> temp = tree.getListsName(values[i]);
                    for (int j = 0; j < temp.size(); j++) {
                        if (!first.contains(temp.get(j))) {
                            first.add(temp.get(i));
                        }
                    }
                }

                for (int i=0;i<listName.size();i++) {
                    if (!first.contains(listName.get(i))) {
                        listName.remove(i);
                        i--;
                    }
                }
            }
        }
    }

    private static void minusListCheck(String text,List<String> name){
        String[] values=text.split(" ");
        for(int i=0;i<values.length;i++){
            List<String> temp=tree.getListsName(values[i]);
            for(int j=0;j<temp.size();j++){
                name.remove(temp.get(j));
            }
        }
    }

    private static List<String> listCopy(List<String> values){
        List<String> temp=new ArrayList<>(values.size());
        for(String s:values){
            temp.add(s);
        }
        return temp;
    }

    public static String checkText(String text){
        StringBuilder temp=new StringBuilder();

        for(int i=0;i<text.length();i++){
            char c=text.charAt(i);
            if(Character.isLetter(c)){
                temp.append(c);
            }
            else{
                if(c==' '){
                    if(i>0 && i<text.length()-1 && Character.isLetter(text.charAt(i-1)) && Character.isLetter(text.charAt(i+1))){
                        temp.append(c);
                    }
                }
                else if(c=='+' || c=='-'){
                    temp.append(c);
                }
            }
        }

        return temp.toString();
    }

    private static String checkSpace(String text){
        StringBuilder temp=new StringBuilder();
        for(int i=0;i<text.length();i++){
            char c=text.charAt(i);
            if(c!='+' && c!='-'){
                temp.append(c);
            }
            else{
                return temp.toString();
            }
        }

        return temp.toString();
    }

    private static String checkPlus(String text){
        StringBuilder temp=new StringBuilder();
        String[] tempValues=text.split("\\+");

        for(int i=1;i<tempValues.length;i++){
            if(!tempValues[i].contains("-")){
                temp.append(tempValues[i]).append(" ");
            }
            else{
                String[] tempSplit=tempValues[i].split("-");
                temp.append(tempSplit[0]).append(" ");
            }
        }

        return temp.toString();
    }

    private static String checkMinus(String text){
        StringBuilder temp=new StringBuilder();
        String[] tempValues=text.split("-");

        for(int i=1;i<tempValues.length;i++){
            if(!tempValues[i].contains("+")){
                temp.append(tempValues[i]).append(" ");
            }
            else{
                String[] tempSplit=tempValues[i].split("\\+");
                temp.append(tempSplit[0]).append(" ");
            }
        }

        return temp.toString();
    }
}