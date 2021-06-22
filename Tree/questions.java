import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Questions{
    public class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
    }

//ConstructTree from preorder traversal -> GFG
int idx = 0;
Node constructTree(int n, int pre[], char preLN[])
{
    if(idx >= n)
    return null;
        
    if(preLN[idx] == 'L')
    {
        Node node = new Node(pre[idx++]);
        return node;
    }
        
    Node node = new Node(pre[idx++]);
    node.left = constructTree(n,pre,preLN);
    node.right = constructTree(n,pre,preLN);
        
    return node;	  
}

//Leetcode 226 -> invert Binary Tree
public TreeNode invertTree(TreeNode root) 
{
    if(root == null)
        return null;
    if(root.left == null && root.right == null)
        return root;
        
    TreeNode temp = root.left; // left and right child ko har recursive call se pehle swap.
    root.left = root.right;
    root.right = temp;
        
    root.left = invertTree(root.left);
    root.right = invertTree(root.right);
        
    return root;
}

//Leetcode 111 -> MinDepth of Binary Tree
public int minDepth(TreeNode root) 
{
    if(root == null)
    return 0;
        
    if(root.left == null && root.right == null)
    return 1;
    if(root.left == null && root.right != null)//Isme test case fass rha tha leetcode ka agr saare 
    return minDepth(root.right)+1;            //nodes apne parent ke right mei ya left mei hi placed ho.

    if(root.left != null && root.right == null)
    return minDepth(root.left)+1;

    int lans = minDepth(root.left);
    int rans = minDepth(root.right);
        
    return Math.min(lans,rans) + 1;
}

public boolean NodetoRootPath(TreeNode root,int data,ArrayList<TreeNode> ans)
{
    if(root == null)
    return false;
        
    if(root.val == data)
    {
        ans.add(root);
        return true;
    }
    boolean res = NodetoRootPath(root.left,data,ans) || NodetoRootPath(root.right,data,ans);
    if(res)
    {
        ans.add(root);
    }
    return res;
}

//Leetcode 236 -> uses NodetoRootPath
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
{
    ArrayList<TreeNode> list1 = new ArrayList<>();
    ArrayList<TreeNode> list2 = new ArrayList<>();
    
    NodetoRootPath(root,p.val,list1);
    NodetoRootPath(root,q.val,list2);
        
    int i = list1.size()-1;
    int j = list2.size()-1;
    TreeNode LCA = null;
    while(i >= 0 && j >= 0)
    {
        if(list1.get(i) == list2.get(j))
            LCA = list1.get(i);
            
        i--;
        j--;
    }
    return LCA;    
}

public static void kDown(TreeNode root,TreeNode block,int k,ArrayList<Integer> ans)
{
    if(root == null || root == block || k < 0)
        return;
        
    if(k == 0)
    {
        ans.add(root.val);
        return;
    }
    kDown(root.left,block,k-1,ans);
    kDown(root.right,block,k-1,ans);
}

//Leetcode 863 -> uses Kdown and NodetoRootPath
public List<Integer> distanceK(TreeNode root, TreeNode target, int K) 
{
    ArrayList<TreeNode> list = new ArrayList<>();
    NodetoRootPath(root,target.val,list);
        
    TreeNode prev = null;
    ArrayList<Integer> ans = new ArrayList<>();
    for(int i = 0;i < list.size();i++)
    {
        kDown(list.get(i),prev,K-i,ans);
        prev = list.get(i);
    }
    return ans;
}

//Leetcode 863 -> optimized as it doesnt uses NodetoRootPath it just uses kDown.
public static int kFar(TreeNode root,int data,int k,ArrayList<Integer> ans)
{
    if(root == null)
        return -1;
    if(root.val == data)
    {
        kDown(root,null,k,ans);
        return 1;
    }
    int ld = kFar(root.left,data,k,ans);
    if(ld != -1)
    {
        kDown(root,root.left,k-ld,ans);
        return ld+1;
    }
    int rd = kFar(root.right,data,k,ans);
    if(rd != -1)
    {
        kDown(root,root.right,k-rd,ans);
        return rd+1;
    }
    return -1;
}
//Leetcode function
public List<Integer> distanceK(TreeNode root, TreeNode target, int K) 
{
    ArrayList<Integer> finAns = new ArrayList<>();
    int x = kFar(root,target.val,K,finAns);
    return finAns;      
}

//Leetcode 543 -> diameter of a binary tree.
public int[] diameter(TreeNode root)
{
    if(root == null)
    return new int[]{0,-1};

    int [] lres = diameter(root.left);
    int [] rres = diameter(root.right);
        
    int dia = Math.max(Math.max(lres[0],rres[0]),lres[1]+rres[1] + 2);
    int hei = Math.max(lres[1],rres[1]) + 1;
        
    return new int[]{dia,hei};
}
//Leetcode function.
public int diameterOfBinaryTree(TreeNode root) 
{
    int[] ans = diameter(root);
    return ans[0];
}

//Leetcode 112 -> PathSum
public boolean hasPathSum(TreeNode root, int targetSum) 
{
    if(root == null)
    return false;
        
    if(root.left == null && root.right == null && targetSum - root.val == 0)// leaf node ki condition
    return true;// jab leaf node pr targetSum 0 ho jaayega to return true.

    return hasPathSum(root.left,targetSum - root.val) || hasPathSum(root.right,targetSum - root.val);
       
}

//Leetcode 113 -> PathSum - II
public void Pathsum(TreeNode root,int sum,List<List<Integer>> res,List<Integer> ans)
{
    if(root == null)
    return;
        
    if(root.left == null && root.right == null && sum - root.val == 0)
    {
        List<Integer> base = new ArrayList<>(ans);
        base.add(root.val);
        res.add(base);
        return;
    }

    ans.add(root.val);
    Pathsum(root.left,sum-root.val,res,ans);
    Pathsum(root.right,sum-root.val,res,ans);
    ans.remove(ans.size()-1);       
}
//Leetcode function
public List<List<Integer>> pathSum(TreeNode root, int targetSum) 
{
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> ans = new ArrayList<>();
    Pathsum(root,targetSum,res,ans);
    return res;
}

// Maximum path sum between two leaf nodes -> GFG
//Question ki recursion hume node se leaf tak max path de degi left ya right subtree se.
int MaxLL = -(int)1e8; // MaxLL is our required ans.
public static int leafToleaf(Node node)
{
    if(node == null)
    return -(int)1e8;

    if(node.left == null && node.right == null)
    return node.data;

    int rootToleafLeft = leafToleaf(node.left);
    int rootToleafRight = leafToleaf(node.right);

    if(node.left != null && node.right != null) // leaf to leaf condition jis node ke dono child present hai vhi maxLL update krna hai.
    {
        MaxLL = Math.max(MaxLL,rootToleafLeft + rootToleafRight + node.data);
    }
    return Math.max(rootToleafLeft,rootToleafRight) + node.data;
}

//Leetcode 124 -> Max sum between any two Nodes.
int MaxNTN = -(int)1e9; // Our required ans.
public int NodetoNode_01(TreeNode node)    
{
    if(node == null)
    return 0;
   
    int NodetoLeft = NodetoNode(node.left);
    int NodetoRight = NodetoNode(node.right);
        
    int MaxSumtillRoot = Math.max(NodetoLeft,NodetoRight) + node.val;
    MaxNTN = Math.max(Math.max(MaxNTN,MaxSumtillRoot),Math.max(node.val,NodetoLeft+NodetoRight+node.val));
            
    return Math.max(Math.max(NodetoLeft,NodetoRight)+node.val,node.val);
}
//Leetcode Function
public int maxPathSum(TreeNode root) 
{
    int N = NodetoNode(root);
    return MaxNTN;        
}

//Another approach similar to diameter_02 function without using static/global variable.
public class maxNodeToNodePair{
    int NTN_sum = -(int)1e9; // NodeToNode maxSum -> our required ans jo hum static/global mei calculate kr rhe the.
    int NTR_sum = 0; // NodeToRoot maxSum -> jo hum recursion mei return krwa rhe the to calculate our ans at each level.
    
    maxNodeToNodePair(int NTN_sum,int NTR_sum)
    {
        this.NTN_sum = NTN_sum;
        this.NTR_sum = NTR_sum; 
    }
    maxNodeToNodePair(){} //empty constructor.
}

//Takes input as in array so jitni marzi quantities ka maximum de deta hai ye function.
public int maxValue(int... arr) 
{
    int max = arr[0];
    for(int ele : arr)
    {
        max = Math.max(max,ele);
    }
    return max;
}

public maxNodeToNodePair NodetoNode_02(TreeNode node)
{
    if(node == null)
    return new maxNodeToNodePair(); // default values already set hai class mei.

    maxNodeToNodePair lpair = NodetoNode_02(node.left);
    maxNodeToNodePair rpair = NodetoNode_02(node.right);

    maxNodeToNodePair myAns = new maxNodeToNodePair();

    myAns.NTN_sum = maxValue(lpair.NTN_sum, rpair.NTN_sum, node.val, lpair.NTR_sum + node.val,
    rpair.NTR_sum + node.val, lpair.NTR_sum + node.val + rpair.NTR_sum);
    myAns.NTR_sum = maxValue(node.val, lpair.NTR_sum + node.val, rpair.NTR_sum + node.val);

    return myAns;
}
//Leetcode Function
public int maxPathSum(TreeNode root) 
{
    if(root == null)
    return 0;

    return NodetoNode_02(root).NTN_sum;        
}

//Leetcode 129 -> Sum Root to Leaf Numbers 
public int sum(TreeNode node,int val)
{
    if(node == null)
        return 0;

    val = val * 10 + node.val;
    
    if(node.left == null && node.right == null)
        return val;
        
    return sum(node.left,val) + sum(node.right,val);
}
//Leetcode function
public int sumNumbers(TreeNode root) 
{
    return sum(root,0);
}

//Leetcode 102 -> Binary Tree Level order traversal 
public List<List<Integer>> levelOrder(TreeNode root)
{
    if(node == null) return new ArrayList<>();
    LinkedList<TreeNode> que = new LinkedList<>();
    List<List<Integer>> ans = new ArrayList<>();

    que.addLast(node);
    int level = 0;
    while(que.size() != 0)
    {
        int size = que.size();
        while(size-- > 0)
        {
            TreeNode vtx = que.removeFirst();
            if(level == ans.size())
            ans.add(new ArrayList<>());
            ans.get(level).add(vtx.val);
                
            if(vtx.left != null) que.addLast(vtx.left);
            if(vtx.right != null) que.addLast(vtx.right);
        }
        level++; 
    }
    return ans;
}

//Leetcode 103 -> ZigZag Level Order Traversal of Binary Trees -> Using two stacks.(without using Collections.reverse(arr)).
int level;
public void zigZag(TreeNode root,List<List<Integer>> res)
{
    if(root == null)
        return;
    LinkedList<TreeNode> st = new LinkedList<>();
    st.addFirst(root);
    LinkedList<TreeNode> cst = new LinkedList<>();
    ArrayList<Integer> ans = new ArrayList<>();
        
    while(st.size() != 0)
    {
        TreeNode vtx = st.removeFirst();
        ans.add(vtx.val);
            
        if(level % 2 == 0)
        {
            if(vtx.left != null) cst.addFirst(vtx.left);
            if(vtx.right != null) cst.addFirst(vtx.right);
                
        }else{
            if(vtx.right != null) cst.addFirst(vtx.right);
            if(vtx.left != null) cst.addFirst(vtx.left);
                
        }
        if(st.size() == 0)
        {
            st = cst;
            cst = new LinkedList<>();
            
            res.add(ans);
            ans = new ArrayList<>();
            level++;
                
        }
    }
}

//Leetcode function
public List<List<Integer>> zigzagLevelOrder(TreeNode root) 
{
    level = 0;
    List<List<Integer>> res = new ArrayList<>();
    for(int i = 0;i < level;i++)
        res.add(new ArrayList<>());
    zigZag(root,res);
    return res;        
}

//Leetcode 199 -> rightView of BinaryTree
public List<Integer> rightSideView(TreeNode root) 
{
    if(root == null) return new ArrayList<Integer>();
    LinkedList<TreeNode> que = new LinkedList<>();
    que.addLast(root);

    List<Integer> ans = new ArrayList<>();
    while(que.size() != 0)
    {
        ans.add(que.getFirst().val);
        int size = que.size();
        while(size-- > 0)
        {
            TreeNode vtx = que.removeFirst();

            if(vtx.right != null) que.addLast(vtx.right); // right pehle add krdia so har level ka first node ab rightView hoga
            if(vtx.left != null) que.addLast(vtx.left);
        }
        
    }
    return ans;
}

//Leetcode 1161 -> MaxLevelSum -> return the level with maximumSum.
public int maxLevelSum(TreeNode root) 
{
    if(root == null)
        return 0;
    LinkedList<TreeNode> que = new LinkedList<>();
    que.addLast(root);
    int level = 1,maxLevel = 0,MaxLevelSum = -(int)1e9;
    while(que.size() != 0)
    {
        int levelSum = 0;
        int size = que.size();
        while(size-- > 0)
        {
            TreeNode vtx = que.removeFirst();
            levelSum += vtx.val;
            
            if(vtx.left != null) que.addLast(vtx.left);
            if(vtx.right != null) que.addLast(vtx.right);
            
        }
            
        if(levelSum > MaxLevelSum)
        {
            maxLevel = level;
            MaxLevelSum = levelSum;
        }
        level++;
    }
    return maxLevel;
}

// Boundary Traversal of Binary Tree -> leftView + addLeafs + rightView
void leftView(Node node,ArrayList<Integer> ans)
{
    if(node == null)
    return;
    if(node.left != null)
    {
        ans.add(node.data);
        leftView(node.left,ans);
    }
    else if(node.right != null)
    {
        ans.add(node.data);
        leftView(node.right,ans);
    }
}

//Recursive better code pdha to recursive kre hai teeno funcitons.
void rightView(Node node,ArrayList<Integer> ans)
{
    if(node == null)
    return;
    if(node.right != null)
    {
        rightView(node.right,ans);
        ans.add(node.data);
    }
    else if(node.left != null)
    {
        rightView(node.left,ans);
        ans.add(node.data);
    }
}

void addLeafs(Node node,ArrayList<Integer> ans)
{
    if(node == null)
    return;
        
    addLeafs(node.left,ans);
    if(node.left == null && node.right == null)
    ans.add(node.data);
    addLeafs(node.right,ans);
}	
//GFG Function
ArrayList <Integer> printBoundary(Node node)
{
	if(node == null)
    return new ArrayList<>();
	ArrayList<Integer> ans = new ArrayList<>();
    ans.add(node.data);
	leftView(node.left,ans);
    addLeafs(node,ans);
	rightView(node.right,ans);
	    
    return ans;
}

//Leetcode 987 -> Vertical Order Traversal -> Important ================================================
    public class VerticalPair{
    TreeNode node = null;
    int Hlevel = 0;

    VerticalPair(TreeNode node,int Hlevel)
    {
        this.node = node;
        this.Hlevel = Hlevel;
    }
}
//Solution Using two PriorityQueues so comparison of y is nullified -> dono que se minimum node.val 
//vaala element pehle pop krwaenge.
public List<List<Integer>> verticalTraversal_01(TreeNode root)
{
    PriorityQueue<VerticalPair> que = new PriorityQueue<>((a,b) -> { 
        return a.node.val - b.node.val; // lambda function -> kind of virtual function.
    });

    PriorityQueue<VerticalPair> childQue = new PriorityQueue<>((a,b) -> {
        return a.node.val - b.node.val;
    });

    HashMap<Integer,List<Integer>> map = new HashMap<>();
    int maxHL = 0, minHL = 0;

    que.add(new VerticalPair(root,0));

    while(que.size() != 0)
    {
        VerticalPair vtx = que.remove();

        map.putIfAbsent(vtx.Hlevel,new ArrayList<>());
        map.get(vtx.Hlevel).add(vtx.node.val);

        minHL = Math.min(minHL,vtx.Hlevel);
        maxHL = Math.max(maxHL,vtx.Hlevel);

        if(vtx.node.left != null)
        childQue.add(new VerticalPair(vtx.node.left,vtx.Hlevel-1));
        if(vtx.node.right != null)
        childQue.add(new VerticalPair(vtx.node.right,vtx.Hlevel+1));

        if(que.size() == 0)
        {
            PriorityQueue<VerticalPair> temp = que;
            que = childQue;
            childQue = temp;
        }
    }
    List<List<Integer>> ans = new ArrayList<>();
    while(minHL <= maxHL)
    {
        ans.add(map.get(minHL));
        minHL++;
    }
    return ans;
}

//Solution using only one priority que so hume y ka bhi comparison krna pdega.
public class VerticalPair_02{
    TreeNode node = null;
    int x = 0; // horizontal level.
    int y = 0; // vertical height

    VerticalPair_02(TreeNode node,int x,int y)
    {
        this.node = node;
        this.x = x;
        this.y = y;
    }
}
//Solution using only a single priority queue.
public List<List<Integer>> verticalTraversal_02(TreeNode root)
{
    PriorityQueue<VerticalPair_02> que = new PriorityQueue<>((a,b) -> { // by default PQ in java is minPQ.
        if(a.y != b.y)
            return a.y - b.y; //this - other for default behaviour 
        else
            return a.node.val - b.node.val; // this yaani a and other yaani b.
    });

    HashMap<Integer,List<Integer>> map = new HashMap<>();
    int maxHL = 0,minHL = 0;

    que.add(new VerticalPair_02(root,0,0));

    while(que.size() != 0)
    {
        VerticalPair_02 vtx = que.remove();

        map.putIfAbsent(vtx.x,new ArrayList<>());
        map.get(vtx.x).add(vtx.node.val);

        minHL = Math.min(minHL,vtx.x);
        maxHL = Math.max(maxHL,vtx.x);

        if(vtx.node.left != null)
        que.add(new VerticalPair_02(vtx.node.left,vtx.x-1,vtx.y+1));
        if(vtx.node.right != null)
        que.add(new VerticalPair_02(vtx.node.right,vtx.x+1,vtx.y+1));

    }
    List<List<Integer>> ans = new ArrayList<>();
    while(minHL <= maxHL)
    {
        ans.add(map.get(minHL));
        minHL++;
    }
    return ans;
}

//BST questions ===================================================================================

//Leetcode 700 -> search in a BST.
public TreeNode searchBST(TreeNode root, int val) 
{
        
    if(root == null)
        return null;
    if(root.val == val)
        return root;
    if(val < root.val)
        return searchBST(root.left,val);
    else
        return searchBST(root.right,val);
        
}

//Leetcode 701 -> insert into Binary Search Tree.
public TreeNode insertIntoBST(TreeNode root, int val) 
{
    if(root == null)
        return new TreeNode(val);
    if(val < root.val)
        root.left = insertIntoBST(root.left,val);
    else
        root.right = insertIntoBST(root.right,val);
        
    return root;
}

public int maximumInBST(TreeNode root)
{
    TreeNode curr = root;
    while(curr.right != null)
    {
        curr = curr.right;
    }
    return curr.val;
}
//Leetcode 450 -> delete Node having key data in BST such that it still remains BST -> uses maximumInBST. 
public TreeNode deleteNode(TreeNode root, int key) 
{
    if(root == null)
    return null;

    if(key < root.val)
    root.left = deleteNode(root.left,key);

    else if(key > root.val)
    root.right = deleteNode(root.right,key);

    else{
        if(root.left == null || root.right == null)
        return root.left != null ? root.left : root.right;

        int maxValue = maximumInBST(root.left);
        root.val = maxValue;
            
        root.left = deleteNode(root.left,maxValue);
    }
    return root;
}

//Leetcode 235 -> Lowest Common Ancestor Of a Binary Search Tree.
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) 
{
    TreeNode curr = root;
    while(curr != null)
    {
        if(p.val < curr.val && q.val < curr.val)
        curr = curr.left;

        else if(p.val > curr.val && q.val > curr.val)
        curr = curr.right;

        else
        return curr; // jaha left ya right na jaa paaye vohi lca hoga.
    }
    return null;    
}

//Leetcode 98 -> Validate Binary Search Tree
public class BSTpair{
    boolean isBST = true;
    long max = -(long)1e13; // test cases mei infinity vgera bhi hai node.val mei.
    long min = (long)1e13;

    BSTpair(boolean isBST,long max,long min)
    {
        this.isBST = isBST;
        this.max = max;
        this.min = min;
    }
    BSTpair(){}
}

public BSTpair isValidBST_01(TreeNode node)
{
    if(node == null)
    return new BSTpair();

    BSTpair lres = isValidBST_01(node.left);
    BSTpair rres = isValidBST_01(node.right);

    BSTpair myRes = new BSTpair();

    myRes.isBST = lres.isBST && rres.isBST && lres.max < node.val && node.val < rres.min;

    if(!myRes.isBST) // agr false aata hai to seedha return bina max ya min ke updation ke.
    return myRes;

    myRes.max = Math.max(rres.max,node.val);
    myRes.min = Math.min(lres.min,node.val);

    return myRes;
}
//Leetcode function
public boolean isValidBST(TreeNode root) 
{
    return isValidBST_01(root).isBST;
}

//Another solution -> BST ka inorder hamesha sorted array deta hai to this is similar to checking if an array is sorted or not.
long prevBSTNode = -(long)1e13; //prev and curr chlaaenge (two pointer) curr hoga root.val.
public boolean isValidBST_02(TreeNode root)
{
    if(root == null)
    return true;

    if(!isValidBST_02(root.left))
    return false;

    if(root.val <= prevBSTNode)
    return false;
    //Saara kaam inorder mei kra hai as inorder of bst gives a sorted array
    prevBSTNode = root.val; // updation of prev = curr.

    if(!isValidBST_02(root.right))
    return false;

    return true;
}

//Leetcode 99 -> recover Binary Search Tree
//Inorder mei saara kaam krenge as inorder of bst is a sorted array to isko humne sorted array ki tarah visualize kra hai
TreeNode prev = null, a = null, b = null;
public boolean recoverBST(TreeNode root)
{
    if(root == null)
    return true;

    if(!recoverBST(root.left))
    return false;

    if(prev != null && prev.val > root.val)
    {
        b = root;
        if(a == null) // pehli mistake milli hai. 
        a = prev;
        else
        return false; // second mistake milli hai to b to apni shi jagah hoga to yahi recursion rok denge.
    }
    prev = root;

    if(!recoverBST(root.right))
    return false;

    return true;
}
//Leetcode function
public void recoverTree(TreeNode root) 
{
    recoverBST(root);
    if(a != null)
    {
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }
}

//Leetcode 173 -> Binary Search Tree Iterator
public class BSTITerator{
    LinkedList<TreeNode> st = new LinkedList<>() // stack implemented using ll using addFirst and removeFirst functions.

    public BSTITerator(TreeNode root) // constructor of class
    {
        addAllLeft(root);
    }

    public void addAllLeft(TreeNode node)
    {
        if(node == null)
        return;

        TreeNode curr = node;
        while(curr != null)
        {
            st.addFirst(curr);
            curr = curr.left;
        }
    }

    public int next()
    {
        TreeNode rn = st.removeFirst();
        addAllLeft(rn.right);
        return rn.val;
    }
    
    public boolean hasNext()
    {
        return st.size() == 0 ? false : true;
    }
}

//Leetcode 510 -> Inorder Successor in BST II (Locked) -> access to node is given and root is not given.
public Node inorderSuccessor(Node node)
{
    if(node.right != null)
    {
        node = node.right;
        while(node.left != null)
        node = node.left;

        return node;
    }
    else{
        while(node != null)
        {
            if(node.parent != null && node.parent.left == node) // jis pehle parent ka left child hoga us branch mei vohi succ as there parent.data > node.data
            return node.parent;

            node = node.parent;
        }
        return null; // jab node root given ho and uska rightTree na ho to null hoga succ.
    }

}

//psi -> preorder starting index, iei -> inorder ending index.
//Leetcode 105 -> construct Binary Tree from Inorder and preorder Traversal
public TreeNode PreInTree(int[] preorder,int psi,int pei,int[] inorder,int isi,int iei);
{
    if(psi > pei)  // or isi > iei -> same time pr hi dono saath traverse honge arrays.
    return null;

    TreeNode node = new TreeNode(preorder[psi]); // preorder ka first ele root hoga.

    int idx = isi;
    while(inorder[idx] != preorder[psi]) // idx pr root dhoondenge inorder mei
    idx++;

    int tne = idx - isi; // total no of ele before node in inorder (same ye left bnenge preorder mei).

    node.left = PreInTree(preorder,psi+1,psi+tne,inorder,isi,idx-1);
    node.right = PreInTree(preorder,psi+1+tne,pei,inorder,idx+1,iei);

    return node;

}

//Leetcode Function
public TreeNode buildTree(int[] preorder,int[] inorder)
{
    return PreInTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
}

//Leetcode 106 -> construct Binary Tree from PostOrder and InOrder Traversal
public TreeNode PostInTree(int[] postorder,int psi,int pei,int[] inorder,int isi,int iei)
{
    if(psi > isi)
    return null;

    TreeNode node = new TreeNode(postorder[pei]);

    int idx = isi;
    while(inorder[idx] != postorder[pei])
    idx++;

    int tne = idx - isi;

    node.left = PostInTree(postorder,psi,psi+tne-1,inorder,isi,idx-1);
    node.right = PostInTree(postorder,psi+tne,pei,inorder,idx+1,iei);

    return node;
}

//Leetcode function
public TreeNode buildTree(int[] postorder,int[] inorder)
{
    return PostInTree(postorder,0,postorder.length-1,inorder,0,inorder.length-1);
}

//Leetcode 889 -> construct Binary Tree from Preorder and Postorder Traversal
public TreeNode PrePostTree(int[] preorder,int psi,int pei,int[] postorder,int ppsi,int ppei)
{
    if(psi > pei)
    return null;

    TreeNode node = new TreeNode(preorder[psi]);

    if(psi == pei) // jab ek node bach jaaye to return krdo usse as hum psi +1 krke while loop mei ele nikaal rhe hai to arr ke out of bound chla jaayega.
    return node;

    int idx = ppsi;
    while(postorder[idx] != preorder[psi+1])
    idx++;

    int tne = idx - ppsi + 1; // as idx'th ele ko bhi include krna hai

    node.left = PrePostTree(preorder,psi+1,psi+tne,postorder,ppsi,idx);
    node.right = PrePostTree(preorder,psi+tne+1,pei,postorder,idx+1,pei-1);

    return node;
}

//Leetcode function
public TreeNode constructFromPrePost(int[] preorder,int[] postorder)
{
    return PrePostTree(preorder,0,preorder.length-1,postorder,0,postorder.length-1);
}

//Leetcode 114 -> Flatten Binary Tree to LinkedList
public TreeNode getTail(TreeNode node)
{
    if(node == null)
    return null;

    TreeNode curr = node;
    while(curr.right != null)
    curr = curr.right; // according to our faith root ka left child right oriented LL bn chuka hoga to bs uski hi tail nikaal rhe hai.
    return curr;
}

// T -> O(n^2) n for flatten and n for getTail
public void flatten(TreeNode node)
{
    if(node == null)
    return;

    flatten(node.left);
    flatten(node.right);
    
    //Structure change kr rhe hai so coded in PostOrder.
    TreeNode tail = getTail(node.left); // left tree ki tail nikalenge jo right oriented LL bn chukka hoga ab tk acc. to faith.
    if(tail != null)
    {
        tail.right = node.right;
        node.right = node.left;
        node.left = null;
    }
}

//Leetcode 114 -> better solution -> O(n) ek hi recursion mei tail bhi mangwa li and flatten bhi kr lia tree.
public TreeNode flatten_02(TreeNode node)
{
    if(node == null)
    return null;
    if(node.left == null && node.right == null) // leaf node hi tail bnta hai.
    return node;

    TreeNode leftTail = flatten_02(node.left);
    TreeNode rightTail = flatten_02(node.right);

    if(leftTail != null)
    {
        leftTail.right = node.right;
        node.right = node.left;
        node.left = null;
    }
    return rightTail != null ? rightTail : leftTail; // rightTail hi final tail hoti hai tree ki if present
}

//Leetcode function
public void flatten(TreeNode root)
{
    root = flatten_02(root);
}

//Leetcode 426 -> covert Binary Search Tree to sorted circular Doubly LinkedList(Locked)
Node dummy = new Node(-1);
Node prev = dummy;
public void treeToDoublyList_(Node root)
{
    if(root == null)
    return;

    treeToDoublyList_(root.left);

    prev.right = root;
    root.left = prev;

    prev = root;
    treeToDoublyList_(root.right);

}

//Leetcode function
public Node treeToDoublyList(Node root)
{
    if(root == null)
    return null;

    treeToDoublyList_(root);
    Node head = dummy.right; // dummy node se head extract krna hai apna.
    dummy.right = null;
    head.left = null;

    prev.right = head; //To make it circular
    head.left = prev;

    return head;
}

//Serialize and Deserialize Tree
int idx = 0; 
public TreeNode createTree(ArrayList<Integer> arr) // Deserialization -> String(here AL<I>) to Tree.
{
    if(idx > arr.size() || arr.get(idx) == -1)
    {
        idx++;
        return null;
    }
    TreeNode node = new TreeNode(arr.get(idx++));
    node.left = createTree(arr);
    node.right = createTree(arr);
    return node;
        
}

public void serial(TreeNode node,ArrayList<Integer> arr) // Serialization -> Tree to String(here AL<I>).
{
    if(node == null)
    {
        arr.add(-1);
        return;
    }
    arr.add(node.val);
    serial(node.left,arr);
    serial(node.right,arr);            
}

//Leetcode 297 -> Serialize and Deserialize Binary Tree -> usage of split function to create a String[].
StringBuilder str;
int idx ;
public void serial(TreeNode root)
{
    if(root == null)
    {
        str.append( "N#");
        return;
    }

    str.append(root.val);
    str.append("#");
    serial(root.left);
    serial(root.right);
}

public TreeNode deserial(String[] ch)
{
    if(idx >= ch.length || ch[idx].equals("N"))
    {
        idx++;
        return null;
    }
        
    TreeNode node = new TreeNode(Integer.parseInt(ch[idx++]));
    node.left = deserial(ch);
    node.right = deserial(ch); 
    return node;
}

//Leetcode function 1 -> Encodes a tree to a single string. 
public String serialize(TreeNode root) 
{
    str = new StringBuilder();
    idx = 0;
    serial(root);
    return str.toString();    
}
//Leetcode function 2 ->  Decodes your encoded data to tree.
public TreeNode deserialize(String data) 
{     
    String[] ch = data.split("#");
    return deserial(ch);   
}

//Leetcode 1008 -> construct BST from preorder Traversal
int idx = 0;
public TreeNode constructBSTformPre(int[] pre,int lr,int rr) // lr -> left range rr -> right range
{
    if(idx == pre.length || pre[idx] < lr || pre[idx] > rr)
    return null;

    TreeNode node = new TreeNode(pre[idx++]);

    node.left = constructBSTformPre(pre,lr,node.val);
    node.right = constructBSTformPre(pre,node.val,rr);

    return node;
}

//Leetcode function
public TreeNode bstFromPreorder(int[] preorder) 
{
    return constructBSTformPre(preorder,-(int)1e8,(int)1e8);
}

int idx;
public TreeNode constructBSTformPost(int[] post,int lr,int rr) // lr -> left range rr -> right range
{
    if(idx < 0 || post[idx] < lr || post[idx] > rr)
    return null;

    TreeNode node = new TreeNode(pre[idx--]);

    node.right = constructBSTformPost(pre,node.val,rr);
    node.left = constructBSTformPost(pre,lr,node.val);

    return node;
}

public TreeNode bstFromPostorder(int[] postorder) 
{
    idx = postorder.length-1
    return constructBSTformPre(postorder,-(int)1e8,(int)1e8);
}

//Construct BST from LevelOrder Traversal
public class levelPair{
    TreeNode par = null;
    int lr = -(int)1e8;
    int rr = (int)1e8;

    levelPair(){

    }
    levelPair(TreeNode par,int lr,int rr)
    {
        this.par = par;
        this.lr = lr;
        this.rr = rr;
    }

public TreeNode createBSTfromLevelOrder(int[] arr)
{
    int idx = 0;
    LinkedList<levelPair> que = new LinkedList<>();
    que.addLast(new levelPair());
    TreeNode root = null;

    while(que.size() != 0 && idx < arr.length)
    {
        levelPair pair = que.removeFirst();

        if(arr[idx] < pair.lr || arr[idx] > pair.rr)
        continue; // curr node shi range mei nhi hai to aage badd jaao.

        TreeNode node = new TreeNode(arr[idx++]);

        if(pair.par == null)
        root = node;
        else{
            if(node.val < pair.par.val)
            pair.par.left = node;
            else
            par.par.right = node;
        }
        que.addLast(new levelPair(node,pair.lr,node.val));
        que.addLast(new levelPair(node,node.val,pair.rr));
    }
    return root;
}

//Leetcode 968 -> Binary Tree Cameras -> Calculate min no. of cameras required to cover the whole tree
// -1 -> camera required
// 0 -> camera isn't required
// 1 -> I have a camera
int cameras = 0;
public int minCameraCover_(TreeNode root)
{
    if(root == null)
    return 0;

    int lans = minCameraCover_(root.left);
    int rans = minCameraCover_(root.right);

    if(lans == -1 || rans == -1)
    {
        cameras++;
        return 1;
    }
    if(lans == 1 || rans == 1)
    return 0;

    return -1; // third case jaha lans == 0 || rans == 0 means mai to covered hu but mere parent ko cam ki need pdh skti hai.
}

//Leetcode Function
public int minCameraCover(TreeNode root)
{
    int ans = minCameraCover_(root);

    if(ans == -1) return cameras+1; // jb sirf root hi present ho to usko akele ko bhi to camera ki need pdegi.

    return cameras;
}

//Leetcode 337 -> House Robber - III
public class robPair{
    int parRobbed = 0;  // parent value included -> house rob krlia parent node pr
    int parNotRobbed = 0; // parent value not included -> house rob nhi kra parent node pr

    robPair(int parRobbed,int parNotRobbed)
    {
        this.parRobbed = parRobbed;
        this.parNotRobbed = parNotRobbed;
    }
}

public robPair robHouse(TreeNode root)
{
    if(root == null)
    return new robPair(0,0);

    robPair lPair = robHouse(root.left);
    robPair rpair = robHouse(root.right);

    robPair myAns = new robPair(0,0);
    
    myAns.parRobbed = lPair.parNotRobbed + root.val + rpair.parNotRobbed;
    myAns.parNotRobbed = Math.max(lPair.parRobbed,lPair.parNotRobbed) + Math.max(rpair.parRobbed,rpair.parNotRobbed);
    // jab parent ko rob nhi krna to usse children nodes pr unko rob krna hai ya nhi dono options khule hai so dono options ka maximum.

    return myAns;
}

//Leetcode function
public int rob(TreeNode root)
{
    if(root == null)
    return 0;

    robPair ans = robHouse(root);
    return Math.max(ans.parRobbed,ans.parNotRobbed);
}

//Leetcode 230 -> Kth Smallest Element in BST
//Method 1 -> using log(n) extra space.
public void insertAllLeft(TreeNode root,LinkedList<TreeNode> st)
{
    while(root != null)
    {
        st.addFirst(root);
        root = root.left;
    }
}

//Leetcode Function.
public int kthSmallest_01(TreeNode root,int k)
{
    LinkedList<TreeNode> st = new LinkedList<>();
    insertAllLeft(root,st);

    while(k-- > 1)
    {
        TreeNode node = st.removeFirst();
        insertAllLeft(node.right,st);
    }

    return st.removeFirst().val;
}

//Method 2 -> using O(1) space -> so using morrisInorder Traversal
public TreeNode rightMostNode(TreeNode next,TreeNode curr)
{
    while(next.right != null && next.right != curr) 
    {
        next = next.right;
    }
    return next;
}

//Leetcode function
public int kthSmallest_02(TreeNode root,int k)
{
    TreeNode curr = root;
    while(curr != null)
    {
        TreeNode next = curr.left;
        if(next == null)
        {
            if(k == 1)
            return curr.val;
            k--;
            curr = curr.right;
        }
        else{
            TreeNode rightMost = rightMostNode(next,curr);

            if(rightMost.right == null)
            {
                rightMost.right = curr;
                curr = curr.left;
            }
            else{
                rightMost.right = null;
                if(k == 1)
                return curr.val;
                k--;
                curr = curr.right;
            }
        }
    }
    return -1;
}

//Leetcode 1372 -> Longest Zig-Zag path in a Binary Tree
int maxPath = 0;
public int[] longestZigZag_(TreeNode root)
{
    if(root == null)
    return new int[]{-1,-1};
    
    int[] lans = longestZigZag_(root.left);
    int[] rans = longestZigZag_(root.right);
        
    int[] myAns = new int[]{0,0};
    myAns[0] = lans[1] + 1;
    myAns[1] = rans[0] + 1;
        
    maxPath = Math.max(Math.max(maxPath,myAns[0]),myAns[1]);
        
    return myAns;
}

//Leetcode function
public int longestZigZag(TreeNode root) 
{    
    maxPath = 0;
    int[] ans = longestZigZag_(root);
    return maxPath;
}

//Leetcode 653 -> Two Sum IV - input in a BST -> Method using Two Stacks -> using O(log(n)) space and O(n) time 
public void insertAllLeft(LinkedList<TreeNode> st,TreeNode node)
{
    while(node != null)
    {
        st.addFirst(node);
        node = node.left;
    }
}

public void insertAllRight(LinkedList<TreeNode> st,TreeNode node)
{
    while(node != null)
    {
        st.addFirst(node);
        node = node.right;
    }
}

public boolean findTarget(TreeNode root, int k) 
{
    if(root == null)
    return false;
        
    LinkedList<TreeNode> lst = new LinkedList<>();
    LinkedList<TreeNode> rst = new LinkedList<>();
        
    insertAllLeft(lst,root);
    insertAllRight(rst,root);
        
    while(lst.getFirst().val < rst.getFirst().val)
    {
        int sum = lst.getFirst().val + rst.getFirst().val;
        if(sum == k)
        return true;
            
        else if(sum < k)
        {
            TreeNode node = lst.removeFirst();
            insertAllLeft(lst,node.right);
        }
        else{
            TreeNode node = rst.removeFirst();
            insertAllRight(rst,node.left);
        }
    }
    return false;
}

//Leetcode 437 -> PathSumIII -> using HashMap of int vs int -> prefixSum VS frequency
int ans = 0;
public void pathSumIII(TreeNode node,int tar,HashMap<Integer,Integer> map,int prefixSum)
{
    if(node = null)
    return;

    prefixSum += node.val;

    ans += map.getOrDefault(prefixSum - tar,0); // map mei agr prefixSum - tar present hai to uski freq ans mei add krdi
    map.put(prefixSum,map.getOrDefault(prefixSum,0) + 1); // present prefixSum map mei daal dia +1 freq krke 

    pathSumIII(node.left,tar,map,prefixSum);
    pathSumIII(node.right,tar,map,prefixSum);

    map.put(prefixSum,map.get(prefixSum) - 1); // prefixSum ki freq decrease krdi by 1
    if(map.get(prefixSum) == 0)
    map.remove(prefixSum);
    
}

//Leetcode function
public int pathSum(TreeNode root, int tar)
{
    HashMap<Integer,Integer> map = new HashMap<>();
    map.put(0,1); // for cases when prefixSum - tar = 0 or prefixSum = tar.
    pathSumIII(root,tar,map,0);
    return ans;
} 

//Leetcode 662 -> Maximum Width in a Binary Tree
public class wPair{
    TreeNode node = null;
    long index = 0;

    wPair(TreeNode node,long index)
    {
        this.node = node;
        this.index = index;
    }
}

public int widthOfBinaryTree(TreeNode root)
{
    if(root == null)
    return 0;

    LinkedList<wPair> que = new LinkedList<>();
    que.addLast(new wPair(root,1));
    int ans = 0;

    while(que.size() != 0)
    {
        int size = que.size();
        long fi = que.getFirst().index; // first index -> bas ek hi baar update hoga so we get first index of each level.
        long li = que.getFirst().index; // last index -> loop ke andar har baar update hoga se we get last index of each level.
        while(size-- > 0)
        {
            wPair pair = que.removeFirst();

            TreeNode node = pair.node;
            long index = pair.index;
            li = index;

            if(node.left != null)
            que.addLast(new wPair(node.left,index * 2));
            if(node.right != null)
            que.addLast(new wPair(node.right,index * 2 + 1));

        }
        ans = Math.max(ans,(int)(li - fi + 1)); // har level ki max width
    }
    return ans;
}

//Leetcode 1325 -> Delete leaves with a given value.
public TreeNode removeLeafNodes(TreeNode root, int target) 
{
    if(root == null)
        return null;
    if(root.val == target && root.left == null && root.right == null)
        return null;

    root.left = removeLeafNodes(root.left,target);
    root.right = removeLeafNodes(root.right,target);
// ab tree traverse and delete krne ke baad agr koi aur leaf generate huyi jo target ke equal hai to postorder mei usse bhi null return krwaakr delete krdenge.        
    if(root.val == target && root.left == null && root.right == null)
        return null;
        
    return root;
        
}

//Leetcode 101 -> Symmetric Tree -> A tree is symmetric if forms mirror images when cut through the centre.
//isMirror -> same as done in genericTree -> checking node1.left with node2.right and node1.right with node2.left
public boolean isMirror(TreeNode node1,TreeNode node2)
{
    if(node1 == null && node2 == null)
        return true;
    if(node1 == null || node2 == null)
        return false;
    if(node1.val != node2.val)
        return false;
    return isMirror(node1.left,node2.right) && isMirror(node1.right,node2.left);
}

//Leetcode function.
public boolean isSymmetric(TreeNode root) 
{
    return isMirror(root,root); 
}
}