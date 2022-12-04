import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Page extends JFrame {
    private final JTextArea result;
    private final JTextField text;
    private final MyTree tree;

    public Page() throws FileNotFoundException {
        tree=Read.readData();

        this.setTitle("Search");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        SpringLayout layout=new SpringLayout();
        this.setLayout(layout);

        JLabel lblText=new JLabel("Enter word : ");
        lblText.setPreferredSize(new Dimension(80,30));
        layout.putConstraint(SpringLayout.NORTH,lblText,50,SpringLayout.NORTH,this.getContentPane());
        layout.putConstraint(SpringLayout.WEST,lblText,20,SpringLayout.WEST,this.getContentPane());

        text=new JTextField();
        text.setPreferredSize(new Dimension(100,30));
        layout.putConstraint(SpringLayout.NORTH,text,50,SpringLayout.NORTH,this.getContentPane());
        layout.putConstraint(SpringLayout.WEST,text,20,SpringLayout.EAST,lblText);

        JButton btnSearchWord=new JButton("Search word/text");
        btnSearchWord.setPreferredSize(new Dimension(150,30));
        layout.putConstraint(SpringLayout.NORTH,btnSearchWord,25,SpringLayout.NORTH,this.getContentPane());
        layout.putConstraint(SpringLayout.WEST,btnSearchWord,20,SpringLayout.EAST,text);
        btnSearchWord.addActionListener(e-> searchWordListener());

        JButton btnSearchLists=new JButton("Search files name");
        btnSearchLists.setPreferredSize(new Dimension(150,30));
        layout.putConstraint(SpringLayout.NORTH,btnSearchLists,75,SpringLayout.NORTH,this.getContentPane());
        layout.putConstraint(SpringLayout.WEST,btnSearchLists,20,SpringLayout.EAST,text);
        btnSearchLists.addActionListener(e-> searchListListener());

        result=new JTextArea();
        result.setEditable(false);
        result.setPreferredSize(new Dimension(500,200));
        result.setFont(new Font(Font.SANS_SERIF,Font.BOLD,24));
        layout.putConstraint(SpringLayout.NORTH,result,100,SpringLayout.SOUTH,btnSearchWord);
        layout.putConstraint(SpringLayout.WEST,result,0,SpringLayout.WEST,this.getContentPane());

        this.add(lblText);
        this.add(text);
        this.add(btnSearchWord);
        this.add(result);
        this.add(btnSearchLists);
        this.setVisible(true);
    }

    private void searchWordListener(){
        String words=text.getText();
        if(!words.isBlank()) {
            text.setText("");
            result.setText(String.valueOf(tree.searchWord(words)));
        }
    }

    private void searchListListener(){
        String words=text.getText();
        if(!words.isBlank()) {
            text.setText("");
            result.setText(checkSearchText(words));
        }
    }

    private String checkSearchText(String text){
        text=checkText(text);
        List<String> tempSpace=spaceListCheck(checkSpace(text));
        System.out.println(tempSpace);
        minusListCheck(checkMinus(text),tempSpace);
        plusListCheck(checkPlus(text),tempSpace);
        return tempSpace.toString();
    }

    private List<String> spaceListCheck(String text){
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

    private void plusListCheck(String text,List<String> listName){
        if(!text.isBlank()) {
            String[] values = text.split(" ");
            if (values.length > 0) {
                List<String> first = listCopy(tree.getListsName(values[0]));
                for (int i = 1; i < values.length - 1; i++) {
                    List<String> temp = tree.getListsName(values[i]);
                    for (int j = 0; j < temp.size(); j++) {
                        if (!first.contains(temp.get(j))) {
                            first.add(temp.get(i));
                        }
                    }
                }

                for (int i = 0; i < listName.size(); i++) {
                    if (!first.contains(listName.get(i))) {
                        first.remove(listName.get(i));
                        i--;
                    }
                }
            }
        }
    }

    private void minusListCheck(String text,List<String> name){
        String[] values=text.split(" ");
        for(int i=1;i<values.length-1;i++){
            List<String> temp=tree.getListsName(values[i]);
            for(int j=0;j<temp.size();j++){
                if(!name.contains(temp.get(j))){
                    name.remove(name.get(j));
                }
            }
        }
    }

    private List<String> listCopy(List<String> values){
        List<String> temp=new ArrayList<>(values.size());
        for(String s:values){
            temp.add(s);
        }
        return temp;
    }

    public String checkText(String text){
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

    private String checkSpace(String text){
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

    private String checkPlus(String text){
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

    private String checkMinus(String text){
        StringBuilder temp=new StringBuilder();
        String[] tempValues=text.split("-");

        for(int i=1;i<tempValues.length;i++){
            if(!tempValues[i].contains("+")){
                temp.append(tempValues[i]).append(",");
            }
            else{
                String[] tempSplit=tempValues[i].split("\\+");
                temp.append(tempSplit[0]).append(",");
            }
        }

        return temp.toString();
    }
}
