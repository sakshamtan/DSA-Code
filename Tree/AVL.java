// Ensure to make the diagrams and code along with diagrams in the notebook
public class AVL{

public static class Node{
    Node left;
    Node right;
    int data;

    int bal = 0; // Balancing Factor
    int height = 0;

    Node(int data)
    {
        this.data = data;
    }
    
}

// return hote huye har level pr apne neeche ke nodes ki bal and hei se apni bal and hei calculate krta rhega whenever called.
public static void updateBalanceAndHeight(Node node)
{
    if(node == null)
    return;

    int lh = -1;
    int rh = -1;

    if(node.left != null)
    lh = node.left.height;

    if(node.right != null)
    rh = node.right.height;

    node.bal = lh - rh;
    node.height = Math.max(lh,rh)+1;

}

//for ll structure
public static Node rotateRight(Node A)
{
    Node B = A.left;
    Node BkaRight = B.right;

    B.right = A;
    A.left = BkaRight;

    updateBalanceAndHeight(A); // A ka bal and hei pehle update hoga as B is the new root so it will                            
    updateBalanceAndHeight(B); // require A's bal and hei for its bal and hei's calculation

    return B; // new root
} 

//for rr structure
public static Node rotateLeft(Node A)
{
    Node B =  A.right;
    Node BkaLeft = B.left;

    B.left = A;
    A.right = BkaLeft;

    updateBalanceAndHeight(A);
    updateBalanceAndHeight(B);

    return B; 
}

public static Node getRotation(Node node) // O(1)
{
    updateBalanceAndHeight(node);

    if(node.bal == 2) // ll or lr structure
    {
        if(node.left.bal == 1) // ll structure so only right rotation
        return rotateRight(node);

        else{ // lr structure -> first rotate left(node ke left child pr) then right(node pr).
        node.left = rotateLeft(node.left);
        return rotateRight(node);
        }
    }

    else if(node.bal == -2) // rr or rl structure.
    {
        if(node.right.bal == -1) //rr structure so only left rotation
        return rotateLeft(node);

        else{ // rl structure -> first rotate right(node ke right child ko) then left(node ko)
        node.right = rotateRight(node.right);
        return rotateLeft(node.left);
        }
    }
    return node;
}

//Basic BST ==========================================================================================

public static Node insertIntoAVL(Node node,int data)
{
    if(node == null)
    return new Node(data);

    if(data < node.data)
    node.left = insertIntoAVL(node.left,data);
    else
    node.right = insertIntoAVL(node.right,data);

    node = getRotation(node);
    return node;
}

public static int MaximumInBST(Node node)
{
    Node curr = node;
    while(curr != null)
    {
        curr = curr.right;
    }
    return curr.data;
}

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
        return root.left != null ? root.left : root.right;

        int maxValue = MaximumInBST(root.left); // rst and lst dono present ho.
        root.data = maxValue; // root ki val ko maxIn leftSubtree se swap krdia.
 
        root.left = deleteNode(root.left,maxValue); // max in leftSubtree ko delete krdiaa.
    }
    return getRotation(root);
}

public static void Display(Node node)
{
    if(node == null)
    return;

    StringBuilder sb = new StringBuilder();
    sb.append(node.left == null ? "." : node.left.data);
    sb.append(" -> " + node.data + "(" + node.bal + ")" + " <- ");
    sb.append(node.right == null ? "." : node.right.data);

    System.out.println(sb.toString());

    Display(node.left);
    Display(node.right);
}

public static void main(String[] args)
{
    Node root = null;

    for(int i = 1;i <= 7;i++)
    {
        root = insertIntoBST(root,i * 10);
        // Display(root);
    }
    Display(root);
}



}