import java.util.ArrayList;
import java.util.List;

public class MyTree {
    private static class Node{
        private char element;
        private Node parent,left,right;
        private final List<Node> children;

        public Node(char element) {
            this.element = element;
            children=new ArrayList<>();
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
        parent.getChildren().add(child);
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


}
