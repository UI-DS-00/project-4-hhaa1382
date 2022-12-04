import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

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
        layout.putConstraint(SpringLayout.WEST,lblText,50,SpringLayout.WEST,this.getContentPane());

        text=new JTextField();
        text.setPreferredSize(new Dimension(100,30));
        layout.putConstraint(SpringLayout.NORTH,text,50,SpringLayout.NORTH,this.getContentPane());
        layout.putConstraint(SpringLayout.WEST,text,20,SpringLayout.EAST,lblText);

        JButton btnSearchWord=new JButton("Search");
        btnSearchWord.setPreferredSize(new Dimension(80,30));
        layout.putConstraint(SpringLayout.NORTH,btnSearchWord,50,SpringLayout.NORTH,this.getContentPane());
        layout.putConstraint(SpringLayout.WEST,btnSearchWord,40,SpringLayout.EAST,text);
        btnSearchWord.addActionListener(e->{
            searchButtonListener();
        });

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
        this.setVisible(true);
    }

    private void searchButtonListener(){
        String words=text.getText();
        text.setText("");
        result.setText(String.valueOf(tree.searchWord(words)));
    }
}
