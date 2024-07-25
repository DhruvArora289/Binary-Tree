import java.util.*;
public class PrintNodesKlevelAway {
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
    public static ArrayList<Node> NodeToRootPath(Node node,int data){
        if(node==null){
            return new ArrayList<>(); 
        }
        if(node.data==data){
            ArrayList<Node> list = new ArrayList<>();
            list.add(node);
            return list;
        }
        ArrayList<Node> lres = NodeToRootPath(node.left,data);
        if(lres.size()>0){
            lres.add(node);
            return lres;
        }
        ArrayList<Node> rres = NodeToRootPath(node.right,data);
        if(rres.size()>0){
            rres.add(node);
            return rres;
        }
        return new ArrayList<>();
    }
    public static void PrintNodesKlevelAway(Node node, int data){
        ArrayList<Node> list = NodeToRootPath(node,data);

        for(int idx=0;idx<list.size();idx++){
            Node curr = list.get(idx);
            int dist = 3;
            if(idx==0) PrintKlevelsDown(curr,dist);
            else{
                int dac = idx;
                int rDist = dist-dac;

                if(rDist==0){
                    System.out.println(curr.data);
                    break;
                }else{
                    Node prev = list.get(idx-1);

                    if(curr.left==prev){
                        PrintKlevelsDown(curr.right,rDist-1);
                    }else{
                        PrintKlevelsDown(curr.left,rDist-1);
                    }
                }
            }
        }
    }
    public static void PrintKlevelsDown(Node node, int k){
        if(node==null){
            return;
        }
        if(k==0){
            System.out.println(node.data);
            return;
        }
        PrintKlevelsDown(node.left, k-1);
        PrintKlevelsDown(node.right, k-1);
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
        Integer input[] = {10,20,40,null,null,50,80,null,null,null,30,60,null,90,null,null,70,null,null};

        Node root = construct(input);
        PrintNodesKlevelAway(root, 10);
    }
}
