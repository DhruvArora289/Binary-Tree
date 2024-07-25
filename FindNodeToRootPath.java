import java.util.*;
public class FindNodeToRootPath {
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
    public static boolean find(Node node,int data){
        if(node==null){
            return false;
        }

        if(node.data==data){
            return true;
        }
        boolean lres = find(node.left,data);
        if(lres){
            return true;
        }
        boolean rres = find(node.right,data);
        if(rres){
            return true;
        }
        return false;
    }
    public static ArrayList<Integer> FindNodeToRootPath(Node node,int data){
        if(node==null){
            return new ArrayList<>();
        }
        if(node.data==data){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(node.data);
            return list;
        }

        ArrayList<Integer> lres = FindNodeToRootPath(node.left,data); 
        if(lres.size()>0){
            lres.add(node.data);
            return lres;
        }
        ArrayList<Integer> rres = FindNodeToRootPath(node.right,data);
            if(rres.size()>0){
                rres.add(node.data);
                return rres;
            }
        return new ArrayList<>();
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
        Integer input[] = {50,25,12,null,null,37,null,null,75,62,null,null,87,null,null};
        Node root = construct(input);
        System.out.println(find(root,62));
        System.out.println(FindNodeToRootPath(root,62));
    }
}
