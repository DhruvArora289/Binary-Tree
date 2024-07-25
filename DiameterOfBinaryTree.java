import java.util.*;
public class DiameterOfBinaryTree {
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
    static int diaOfTree;
    public static int diameter(Node node){
        if(node==null){
            return -1;
        }
        int lht = diameter(node.left);
        int rht = diameter(node.right);

        int diaOfNode = lht+rht+2;
        if(diaOfNode>diaOfTree){
            diaOfTree = diaOfNode;
        }
        return Math.max(lht,rht)+1;
    }
    public static class DiaPair{
        int ht, dia;
        DiaPair(int ht, int dia){
            this.ht = ht;
            this.dia = dia;
        }
    }

    public static DiaPair diameter2(Node node){
        if(node==null){
            return new DiaPair(-1, 0);
        }
        DiaPair lpair = diameter2(node.left);
        DiaPair rpair = diameter2(node.right);

        int ht = Math.max(lpair.ht,rpair.ht)+1;
        int diaOfNode = lpair.ht+rpair.ht+2;
        int diaOfTree = Math.max(Math.max(lpair.dia,rpair.dia), diaOfNode);

        return new DiaPair(ht,diaOfTree);
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
    public static void main(String[] args){
        Integer input[] = {10,20,40,null,null,null,30,50,70,90,110,null,null,null,null,null,60,null,80,null,100,null,120,null,null};
        diaOfTree = 0;
        Node root = construct(input);
       DiaPair res = diameter2(root);
       System.out.println(res.dia);
    }
}


