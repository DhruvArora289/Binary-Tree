import java.util.*;
public class LevelOrderTraversalOfBinaryTree {
    public static class Node{
        int data;
        Node left,right;

        Node(int data){
            this.data = data;
        }
    }

    public static class Pair{
        Node node;
        int state;

        Pair(Node node){
            this.node = node;
            this.state = 0;
        }
    }
    public static Node construct(Integer input[]){
        if(input.length==0){
            return null;
        }
        Node root = new Node(input[0]);

        Stack<Pair> st = new Stack<>();
        st.push(new Pair(root));
        int idx = 1;
        while(st.size()>0){
            Pair top = st.peek();

            if(top.state==0){
                Integer val = input[idx++];
                if(val!=null){
                    Node newNode = new Node(val);
                    top.node.left = newNode;
                    st.push(new Pair(newNode));
                }
                top.state++;
            }
            else if(top.state==1){
                Integer val = input[idx++];
                if(val!=null){
                    Node newNode = new Node(val);
                    top.node.right = newNode;
                    st.push(new Pair(newNode));
                }
                top.state++;
            }
            else{
                st.pop();
            }
        }
        return root;
    }
    public static void levelorder(Node root){
        Queue<Node> mainq = new ArrayDeque<>();
        Queue<Node> helperq = new ArrayDeque<>();

        mainq.add(root);

        while(mainq.size()>0){
            Node fnode = mainq.remove();
            System.out.print(fnode.data+" ");

            if(fnode.left!=null) helperq.add(fnode.left);
            if(fnode.right!=null) helperq.add(fnode.right);

            if(mainq.size()==0){
                System.out.println();

                Queue<Node> tempq = mainq;
                mainq = helperq;
                helperq = tempq;
            }
        }
    }
    public static void main(String[] args){
        Integer input[] = {10,20,40,null,null,50,80,null,null,null,30,60,null,90,null,null,70,null,null};

        Node root = construct(input);
        levelorder(root);
    }
}
