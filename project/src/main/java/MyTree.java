import java.util.ArrayList;
import java.util.List;

public class MyTree {
    private static class Node{
        private char element;
        private Node parent,left,right;
        private final List<Node> children;
        private final List<String> wordList;

        public Node(char element) {
            this.element = element;
            children=new ArrayList<>();
            wordList=new ArrayList<>();
        }

        public char getElement() {
            return element;
        }

        public void setElement(char element) {
            this.element = element;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public List<Node> getChildren(){
            return children;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public List<String> getWordList() {
            return wordList;
        }

        public void addWordList(String name){
            wordList.add(name);
        }
    }

    private Node root=null;
    private int size=0;

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }

    public Node getRoot(){
        return root;
    }

    public void addRoot(char element){
        if(root!=null){
            throw new IllegalStateException("Root already exist");
        }
        root=new Node(element);
        root.setParent(null);
        root.setLeft(null);
        root.setRight(null);
        size++;
    }

    public void addChild(Node parent,char element){
        Node child=new Node(element);
        child.setParent(parent);
        child.setRight(null);

        int index=parent.getChildren().size();

        if(index>0){
            Node leftNode=parent.getChildren().get(index-1);
            child.setLeft(leftNode);
            leftNode.setRight(child);
        }
        else{
            child.setLeft(null);
        }
        parent.getChildren().add(child);

        size++;
    }

    public void replaceChild(Node child,char element){
        Node parent=child.getParent();
        Node newChild=new Node(element);
        newChild.setRight(child.getRight());
        newChild.setLeft(child.getLeft());
        newChild.setParent(parent);

        List<Node> children=parent.getChildren();
        int index=children.indexOf(child);
        children.remove(child);

        children.add(index,newChild);
    }

    public void removeChild(Node child){
        List<Node> temp=child.getParent().getChildren();
        Node left=child.getLeft();
        Node right=child.getRight();

        if(left!=null){
            left.setRight(right);
        }
        if(right!=null){
            right.setLeft(left);
        }

        temp.remove(child);
        size--;
    }

    public Iterable<String> inOrderTraversal(){
        List<String> values=new ArrayList<>();
        StringBuilder tempReader=new StringBuilder();

        Node tempNode;

        if(root.getChildren().size()>0) {
            tempNode = root.getChildren().get(0);

            while (tempNode!=null){
                tempReader.append(tempNode.getElement());
                if(tempNode.getChildren().size()>0){
                    tempNode=tempNode.getChildren().get(0);
                }
                else{
                    values.add(tempReader.toString());
                    while (tempNode.getRight()==null){
                        tempReader.delete(tempReader.length()-1,tempReader.length());
                        tempNode=tempNode.getParent();

                        if(tempNode==root){
                            break;
                        }
                    }
                    if(tempReader.length()>0) {
                        tempReader.delete(tempReader.length() - 1, tempReader.length());
                    }
                    tempNode=tempNode.getRight();
                }
            }
        }

        return values;
    }

    public void addWord(String word,String listName){
        Node tempNode;
        int counter=0;

        if(root.getChildren().size()>0){
            tempNode= root.getChildren().get(0);

            while (true){
                if(tempNode.getElement()==word.charAt(counter)){
                    counter++;
                    if(counter==word.length()){
                        break;
                    }

                    if(tempNode.getChildren().size()>0){
                        tempNode=tempNode.getChildren().get(0);
                    }
                    else{
                        break;
                    }
                }
                else{
                    if(tempNode.getRight()==null){
                        tempNode=tempNode.getParent();
                        break;
                    }
                    tempNode=tempNode.getRight();
                }
            }
        }
        else{
            tempNode=root;
        }

        for(int i=counter;i<word.length();i++){
            this.addChild(tempNode,word.charAt(i));
            int index=tempNode.getChildren().size();
            tempNode= tempNode.getChildren().get(index-1);
        }

        tempNode.addWordList(listName);
    }

    private Node getSearchNode(String word){
        Node tempNode;
        int counter=0;

        if(root.getChildren().size()>0){
            tempNode=root.getChildren().get(0);
            while(tempNode!=null){
                char c=word.charAt(counter);
                if(tempNode.getElement()==c){
                    counter++;

                    if(tempNode.getChildren().size()>0){
                        if(counter==word.length()){
                            return tempNode;
                        }
                        tempNode=tempNode.getChildren().get(0);
                    }
                    else{
                        if(counter == word.length()){
                            return tempNode;
                        }
                        else{
                            return null;
                        }
                    }
                }
                else{
                    tempNode=tempNode.getRight();
                }
            }
        }

        return null;
    }

    public boolean searchWord(String word){
        Node temp=getSearchNode(word);
        return temp!=null;
    }

    public List<String> getListsName(String word){
        Node temp=getSearchNode(word);
        if(temp==null){
            return new ArrayList<>();
        }
        else{
            return temp.getWordList();
        }
    }
}
