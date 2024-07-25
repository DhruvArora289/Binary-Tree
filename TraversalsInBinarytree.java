import java.util.*;
public class TraversalsInBinarytree {
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
    public static void traversals(Node node){
        if(node==null){
            return;
        }

        //pre
        System.out.println("PRE: "+node.data);
        
        traversals(node.left);
        
        //in
        System.out.println("IN: "+node.data);
        traversals(node.right);

        //post
        System.out.println("POST: "+node.data);
    }
    //iterative
    public static void traversalsI(Node root){
        Stack<Pair> st = new Stack<>();

        st.push(new Pair(root));
        String pre = "", in="", post="";

        while(st.size() > 0){
            Pair top = st.peek();

            if(top.state==0){
                //pre
                pre+=top.node.data+" ";
                if(top.node.left!=null){
                    st.push(new Pair(top.node.left));
                }
                top.state++;
            }else if(top.state==1){
                //in
                in+=top.node.data+" ";
                if(top.node.right!=null){
                    st.push(new Pair(top.node.right));
                }
                top.state++;
            }else if(top.state==2){
                //post
                post+=top.node.data+" ";
                st.pop();
            }
        }
        System.out.println(pre+"\n"+in+"\n"+post);
    }
    public static void main(String[] args){
        Integer input[] = {10,20,40,null,null,50,80,null,null,null,30,60,null,90,null,null,70,null,null};

        Node root = construct(input);
        traversalsI(root);
        traversals(root);
    }
}
