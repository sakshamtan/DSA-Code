import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GenericTree{

    public static class Node{
        int data = 0;
        ArrayList<Node> children;

        Node(int data)
        {
            this.data = data;
            this.children = new ArrayList<>();
        }
    }

public static int size(Node node)
{
    // if(node == null) // compulsory nhi hai base case as for loop kbhi node ko null tk jaane nhi dega GT mei.
    // return 0;

    int sz = 0;
    for(Node child : node.children)
    {
        sz += size(child);
    }

    return sz+1;
}

public static int height(Node node)
{
    int h = -1;
    for(Node child : node.children)
    {
        h = Math.max(h,height(child));
    }
    return h+1;
}

public static int maximum(Node node)
{
    int maxEle = node.data;

    for(Node child : node.children)
    {
        maxEle = Math.max(maxEle,maximum(child));
    }

    return maxEle;
}

public static int minimum(Node node)
{
    int minEle = node.data;

    for(Node child : node.children)
    {
        minEle = Math.min(minEle,minimum(child));
    }

    return minEle;
}

public static boolean find(Node node,int data)
{
    if(node.data == data)
    return true;

    for(Node child : node.children)
    {
        if(find(child, data))
        return true;
    }

    return false;
}

//rootToNode path in Generic Tree
public static boolean nodeToRootPath(Node node,int data,ArrayList<Node> ans)
{
    if(node.data == data)
    {
        ans.add(node);
        return true;
    }

    boolean res = false;
    for(Node child : node.children) // for loop equivalent to calls of left and right calls in BinaryTree.
    {
        res = res || nodeToRootPath(child,data,ans);
        // if(res) break;
    }

    if(res)
    ans.add(node);

    return res;
}

public static ArrayList<Node> nodeToRootPath(Node node,int data)
{
    ArrayList<Node> ans = new ArrayList<>();
    nodeToRootPath(node,data,ans);
    return ans;
}

//LCA in Generic Tree -> same as in BT -> uses nodeToRootPath
public static Node LCAinGT(Node node,int d1,int d2)
{
    ArrayList<Node> L1 = nodeToRootPath(node,d1);
    ArrayList<Node> L2 = nodeToRootPath(node,d2);

    int i = L1.size()-1;
    int j = L2.size()-1;

    Node LCA = null;
    while(i >= 0 && j >= 0)
    {
        if(L1.get(i) != L2.get(j))
        break;

        LCA = L1.get(i);
        i--;
        j--;
    }

    return LCA;
}

//Burning Tree in Generic Tree -> Similar to Kfar
public static void KdownBurnTree(Node node,Node blockNode,int time,List<List<Integer>> ans)
{
    if(node == blockNode)
    return;

    if(ans.size() == time)
    ans.add(new ArrayList<>());

    ans.get(time).add(node.data);

    for(Node child : node.children)
    {
        KdownBurnTree(child,blockNode,time+1,ans);
    }
}

//Burnig Generic Tree -> follow up question for Burning Tree which is based on kFar.
public static List<List<Integer>> BurningGT_01(Node node,int target)
{
    ArrayList<Integer> List = NodeToRootPath(node,target);

    List<List<Integer>> ans = new ArrayList<>();
    Node blockNode = null;

    for(int i = 0;i < List.size();i++)
    {
        KdownBurnTree(node,blockNode,i,ans);
        blockNode = List.get(i);
    }
    return ans;
}

//Same as Kfar_02 -> doesnt uses extra space in the form of nodeToRootPath.
public static int BurnigGT_02(Node node,int target,List<List<Integer>> ans)
{
    if(node.data == target)
    {
        KdownBurnTree(node,null,0,ans);
        return 1;
    }

    int time = -1; // here time is equivalent to 'ld or rd' in KFar Binary tree solution
    for(Node child : node.children)
    {
        time = BurningGT_02(child,target,ans);
        if(time != -1)
        {
            blockNode = child;
            break;
        }
    }
    if(time != -1)
    {
        KdownBurnTree(node,blockNode,time,ans);
        time++;
    }
    return time;
}

public static void linewiseLevelOrder(Node root)
{
    LinkedList<Node> que = new LinkedList<>();
    que.addLast(root);

    int level = 0;
    while(que.size() != 0)
    {
        int size = que.size();
        System.out.print(level + " -> ");
        while(size-- > 0)
        {
            Node vtx = que.removeFirst();
            System.out.print(vtx.data + " , ");

            for(Node child : vtx.children)
            que.addLast(child);
        }
        level++;
        System.out.println();
    }
}

//Check if two trees are mirror of each other.
public static boolean isMirror(Node node1,Node node2)
{
    if(node1.children.size() != node2.children.size())
    return false;

    if(node1.data != node2.data)
    return false;

    for(int i = 0, j = node2.children.size()-1; i < node1.children.size() && j <= 0; i++, j--)
    {
        Node child1 = node1.children.get(i);
        Node child2 = node2.children.get(j);

        if(!isMirror(child1, child2))
        return false;
    }
    return true;
}

//Flatten GT into a LinkedList -> peeche se for loop chlaana hai to save complexity of removing an ele present in b/w arr.
public static Node flattenGT(Node node)
{
    if(node.children.size() == 0) // node -> Tail hai.
    return node;

    int n = node.children.size();
    Node lastChild = node.children.get(n-1);

    Node gTail = flattenGT(lastChild);

    for(int i = n - 2; i >= 0; i--)
    {
        Node tempTail = flattenGT(node.child.get(i));

        tempTail.children.add(node.children.get(i+1));
        node.children.remove(i+1);
    }
    return gTail;
}
}