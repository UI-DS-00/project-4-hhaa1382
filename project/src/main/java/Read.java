import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Read {
    private static String readWords(File readFile) throws FileNotFoundException {
        Scanner reader=new Scanner(readFile);
        StringBuilder temp=new StringBuilder();

        while (reader.hasNextLine()){
            temp.append(reader.nextLine());
        }

        reader.close();
        return temp.toString();
    }

    public static MyTree readData() throws FileNotFoundException {
        File file=new File("C:\\Users\\Salam\\Desktop\\Homework\\Data structure\\Tree Project\\data\\EnglishData");
        File[] filesReading=file.listFiles();

        if(filesReading!=null) {
//            MyTree[] temp=new MyTree[filesReading.length];
            MyTree temp=new MyTree();
            temp.addRoot('s');

            for (int i=0;i<1;i++) {
                String text=getTrueString(readWords(filesReading[i]));
                setValues(temp,text,filesReading[i].getName());
            }

            return temp;
        }
        else{
            return null;
        }
    }

    private static void setValues(MyTree tree,String text,String fileName){
        String temp[]=text.split(" ");

        for(int i=0;i<temp.length;i++){
            tree.addWord(temp[i],fileName);
        }
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
