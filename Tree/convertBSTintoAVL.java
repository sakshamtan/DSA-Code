public class convertBSTintoAVL{
    public static class Node{
        Node left = null;
        Node right = null;
        int data = 0;

        int bal = 0;
        int height = 0;

        Node(int data)
        {
            this.data = data;
        }
    }

public static int height(Node node)
{
    return node == null ? -1 : Math.max(height(node.left),height(node.right))+1;
}

public static int getBalance(Node node)
{
    return height(node.left) - height(node.right);
}

public static void updateBalanceAndHeight(Node node)
{
    if(node == null)
    return;

    int lh = -1;
    int rh = -1;

    if(node.left != null)
    lh = height(node.left);

    if(node.right != null)
    rh = height(node.right);

    node.bal = lh - rh;
    node.height = Math.max(lh,rh) + 1;

}

public static Node rotateRight(Node A)
{
    Node B = A.left;
    Node BkaRight = B.right;

    B.right = A;
    A.left = BkaRight;

    B.right = getRotation(A);
    return getRotation(B);
}

public static Node rotateLeft(Node A)
{
    Node B = A.right;
    Node BkaLeft = B.left;

    B.left = A;
    A.right = BkaLeft;

    B.left = getRotation(A);
    return getRotation(B);
}

public static Node getRotation(Node node)
{
    updateBalanceAndHeight(node);

    if(node.bal >= 2) // ll or lr
    {
        if(node.left.bal >= 1) // ll
        return rotateRight(node);
        else{ //lr
        node.left = rotateLeft(node.left);
        return rotateRight(node);
        }
    }
    else if(node.bal <= -2)
    {
        if(node.right.bal <= -1)
        return rotateLeft(node);
        else{ //rl
        node.right = rotateRight(node.right);
        return rotateLeft(node);
        }
    }
    return node;
}

public static Node insertIntoBST(Node node,int data)
{
    if(node == null)
    return new Node(data);

    if(data < node.data)
    node.left = insertIntoBST(node.left,data);
    else
    node.right = insertIntoBST(node.right,data);

    updateBalanceAndHeight(node); // to create unbalanced BST
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

public static Node postOrder(Node node)
{
    if(node == null)
    return null;

    node.left = postOrder(node.left);
    node.right = postOrder(node.right);

    return getRotation(node); // to balance the created unbalanced bst.
}

public static void main(String[] args)
{
    Node root = null;
    for(int i = 0;i <= 7;i++)
    {
        root = insertIntoBST(root,i * 10);
        
    }
    Display(root);

    System.out.println("===================================");

    root = postOrder(root);
    Display(root);
}

//Leetcode 1382 -> Balance a BinarySearchTree ========================================================
public int height(TreeNode node)
{
    return node == null ? -1 : Math.max(height(node.left),height(node.right))+1; 
}

public int getBal(TreeNode node)    
{
    return height(node.left) - height(node.right);
}

public TreeNode rotateRight(TreeNode A)
{
    TreeNode B = A.left;
    TreeNode BkaRight = B.right;
        
    B.right = A;
    A.left = BkaRight;
        
    B.right = getRotation(A);
    return getRotation(B);
}

public TreeNode rotateLeft(TreeNode A)
{
    TreeNode B = A.right;
    TreeNode BkaLeft = B.left;
        
    B.left = A;
    A.right = BkaLeft;
        
    B.left = getRotation(A);
    return getRotation(B);
}

public TreeNode getRotation(TreeNode node)
{
    if(getBal(node) >= 2)
    {
        if(getBal(node.left) >= 1)
        return rotateRight(node);
        else{
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
    }
    else if(getBal(node) <= -2)
    {
        if(getBal(node.right) <= -1)
        return rotateLeft(node);
        else{
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
    }
    return node;
}

public TreeNode postOrder(TreeNode node)
{
    if(node == null)
    return null;
        
    node.left = postOrder(node.left);
    node.right = postOrder(node.right);
        
    return getRotation(node);
}

//Leetcode Function
public TreeNode balanceBST(TreeNode root) 
{
    return postOrder(root);
}

//Leetcode 110 -> Balanced Binary Tree -> tell if a binary Tree is balanced ========================
public int height(TreeNode root)
{
    return root == null ? -1 : Math.max(height(root.left),height(root.right)) + 1;
}

public int getBal(TreeNode root)    
{
    return height(root.left) - height(root.right);
}

//Leetcode Function
public boolean isBalanced(TreeNode root) 
{
    if(root == null)
    return true;
        
    if(getBal(root) > 1 || getBal(root) < -1)
    return false;
    
    return isBalanced(root.left) && isBalanced(root.right);        
}


}