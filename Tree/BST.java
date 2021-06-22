public class BST
{
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data)
        {
            this.data = data;
        }
    }

public static int size(Node node)
{
    return node == null ? 0 : size(node.left) + size(node.right) + 1;
}

//when node == null height = -1 in terms of edges and 0 in terms of nodes.
public static int height(Node node)
{
    return node == null ? -1 : Math.max(height(node.left),height(node.right)) + 1;
}

//RightMost node of BST hi sbse bdaa node hoga.
public static int MaximumInBST(Node node)
{
    Node curr = node;
    while(curr.right != null)
    {
        curr = curr.right;
    }
    return curr.data;
}

//LeftMost node of BST hi sbse chotta node hoga.
public static int MinimumInBST(Node node)
{
    Node curr = node;
    while(curr.left != null)
    {
        curr = curr.left;
    }
    return curr.data;
}

//Find in BST works similar to BinarySearch.
//Iterative solution.
public static boolean find_01(Node node,int data)
{
    Node curr = node;
    while(curr != null)
    {
        if(curr.data == data)
        return true;

        else if(curr.data > data) // agr data chotta hai root ke data se to left mei move kro else right mei move kro.
        curr = curr.left;

        else
        curr = curr.right;
    }
    return false;
}

//Recursive solution.
public static boolean find_02(Node node,int data)
{
    if(node == null)
    return false;

    if(node.data == data)
    return true;

    if(data < node.data)
    return find_02(node.left,data);

    else
    return find_02(node.right,data);
}

//insert a new node of data into BST such that it still remains BST.
public static Node insertIntoBST(Node node,int data)
{
    if(node == null)
    return new Node(data);

    if(val < root.data)
    node.left = insertIntoBST(node.left,data);
    else
    node.right = insertIntoBST(node.right,data);

    return node;
}

//Iterative solution(recursive is better as code chotta hai)
public static Node insertIntoBST_02(Node root,int data)
{
    Node curr = root;
    Node prev = null;

    while(curr != null)
    {
        prev = curr;

        if(data < curr.data)
        curr = curr.left;
        else
        curr = curr.right;
    }

    Node node = new Node(data);

    if(prev == null) // Agr tree hi null ho to prev bhi null hi rahega.
    return node;

    else if(data < prev.data)
    prev.left = node;
    else
    prev.right = node;

    return root;
}

//Delete node having 'data' data if found.
public static Node deleteNode(Node root,int data)
{
    if(root == null)
    return null;

    if(data < root.data)
    root.left = deleteNode(root.left,data); // find function vaala same recursion.

    else if(data > root.data)
    root.right = deleteNode(root.right,data);

    else{ // agr root.data == data
        if(root.left == null || root.right == null) // leaf ho yaa lst or rst ho node ka tbh ka case.
        return root.left == null ? root.right : root.left;

        int maxValue = MaximumInBST(root.left); // rst and lst dono present ho.
        root.data = maxValue; // root ki val ko maxIn leftSubtree se swap krdia.
 
        root.left = deleteNode(root.left,maxValue); // max in leftSubtree ko delete krdiaa.
    }
    return root;
}

//Lowest Common Ancestor in BST
public static Node LCAinBST(Node node,Node p,Node q)
{
    Node curr = node;
    while(curr != null)
    {
        if(p.data < curr.data && q.data < curr.data)
        curr = curr.left;

        else if(p.data > curr.data && q.data > curr.data)
        curr = curr.right;

        else
        return curr; // jab left ya right nhi jaa paaye to vohi LCA h humaara required.
    }
    return null;
}

//Convert Sorted arr into BST.
public static Node createBST(int [] arr,int si,int ei)
{
    if(si > ei)
    return null;

    int mid = (si + ei) / 2;
    Node node = new Node(arr[mid]); // mid ko root bnaaenge and left and right ki call maar denge.

    node.left = createBST(arr,si,mid-1);
    node.right = createBST(arr,mid+1,ei);

    return node;
}

//Inorder pred and succ in BST are equal to floor and ceil value in BST. (logn solution using O(1) space)
public static void predsuccInBST(Node node,int data)
{
    Node pred = null;
    Node succ = null;
    Node curr = node;

    while(curr != null)
    {
        if(curr.data == data)
        {
            if(curr.left != null) // if leftSubtree of 'data' exist pred = leftSubtree's rightMost node.
            {
                pred = curr.left;
                while(pred.right != null)
                pred = pred.right;
            }

            if(curr.right != null) // if rightSubtree of 'data' exist succ = rightSubtree's leftMost node.
            {
                succ = curr.right;
                while(succ.left != null)
                succ = succ.left;
            }
            break;
        }

        else if(data > curr.data)
        {
            pred = curr;       // if leftTree of 'data' doesnt exist pred = last updation while going right.
            curr = curr.right;
        }
        else{
            succ = curr;     // if rightTree of 'data' doesnt exist succ = last updation while going left.
            curr = curr.left;
        }
    }
}