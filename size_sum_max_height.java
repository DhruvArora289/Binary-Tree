import java.util.*;
public class size_sum_max_height {
    public static class Node{
        int data;
        Node left,right;

        Node(int data){
            this.data=data;
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
        Stack<Pair> st = new Stack<>();
        Node root = new Node(input[0]);
        st.push(new Pair(root));
        int idx=1;
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
            }else if(top.state==1){
                Integer val = input[idx++];
                if(val!=null){
                    Node newNode = new Node(val);
                    top.node.right = newNode;
                    st.push(new Pair(newNode));
                }
                top.state++;
            }else{
                st.pop();
            }
        }
        return root;
    }
    public static int size(Node node){
        if(node==null){
            return 0;
        }
        int lsize = size(node.left);
        int rsize = size(node.right);

        return lsize+rsize+1;
    }
    public static int sum(Node node){
        if(node==null){
            return 0;
        }
        int lsum = sum(node.left);
        int rsum = sum(node.right);

        return lsum+rsum+node.data;
    }
    public static int max(Node node){
        if(node==null){
            return Integer.MIN_VALUE;
        }
        int lmax = max(node.left);
        int rmax = max(node.right);

        return Math.max(lmax,Math.max(rmax,node.data));
    }
    public static int height(Node node){
        if(node==null){
            return -1;
        }
        int lht = height(node.left);
        int rht = height(node.right);

        return Math.max(lht,rht)+1;
    }
    public static void main(String[] args){
        Integer input[] = {10,20,40,null,null,50,80,null,null,null,30,60,null,90,null,null,70,null,null};

        Node root = construct(input);
        System.out.println(size(root));
        System.out.println(sum(root));
        System.out.println(max(root));
        System.out.println(height(root));
    }
}
