import java.util.*;
public class IsBalanced {
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
    public static void display(Node node){
        if(node==null){
            return;
        }
        System.out.print(node.left==null ? "." : node.left.data);
        System.out.print("<-"+node.data+"->");
        System.out.println(node.right==null ? "." : node.right.data);

        display(node.left);
        display(node.right);
    }
    public static class IBPair{
        boolean IsBalanced;
        int ht;
        IBPair(boolean IsBalanced, int ht){
            this.IsBalanced = IsBalanced;
            this.ht = ht;
        }
    }
    public static IBPair Isbalanced(Node node){
        if(node==null) return new IBPair(true, -1);
        IBPair lp = Isbalanced(node.left);
        IBPair rp = Isbalanced(node.right);

        int ht = Math.max(lp.ht,rp.ht)+1;
        int bf = Math.abs(lp.ht-rp.ht);
        boolean IsBalanced = lp.IsBalanced && rp.IsBalanced && bf<=1;

        return new IBPair(IsBalanced, ht);
    }
    public static void main(String[] args){
        Integer[] arr = {10,20,40,null,null,50,80,null,null,null,30,60,null,90,null,null,70,null,null};
        Node root = construct(arr);
        IBPair res = Isbalanced(root);
        System.out.println(res.IsBalanced);
    }
}
