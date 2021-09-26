import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class BinaryTree
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

//arr mei pre-order civilized form di hoti h tree ki.
static int idx = 0; // to traverse on the given array 
//static isliye taaki recursion call pr dependent na ho iteration and har baar idx aage hi move krta reh.
public static Node constructTree(int [] arr)
{
    if(idx >= arr.length || arr[idx] == -1)
    {
        idx++;
        return null;
    }

    Node node = new Node(arr[idx++]);

    node.left = constructTree(arr); // left ki call
    node.right = constructTree(arr); // right ki call

    return node;
}

public static void display(Node node)
{
    if(node == null)
    return;

    StringBuilder sb = new StringBuilder();
    sb.append(node.left == null ? " . " : node.left.data + " ");
    sb.append(" <- " + node.data + " -> ");
    sb.append(node.right == null ? " . " : node.right.data);
    System.out.println(sb);

    display(node.left);
    display(node.right);
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

public static int MaximumInTree(Node node)
{
    if(node == null)
    return -(int)1e8;

    int lmv = MaximumInTree(node.left);
    int rmv = MaximumInTree(node.right);

    return Math.max(Math.max(lmv,rmv),node.data);
}

public static boolean find(Node node,int data)
{
    if(node == null)
    return false;

    if(node.data == data)
    return true;

    return find(node.left,data) || find(node.right,data);
}

// give an arraylist containing path of that node(data given) to root.
public static boolean NodeToRootPath(Node node,int data,ArrayList<Node> ans)
{
    if(node == null)
    return false;

    if(node.data == data) // agr root ka data given data ke hi equal hai.
    {
        ans.add(node);
        return true;
    }

    boolean res = NodeToRootPath(node.left,data,ans) || NodeToRootPath(node.right,data,ans);// ek bhi call se true aaya to dusri call nhi chlegi.
    if(res)
    {
        ans.add(node);
    }
    return res;
}

// through return type - recursion.
public static ArrayList<Node> NodeToRootPath(Node node,int data)
{
    if(node == null)
    return null;

    if(node.data == data)
    {
        ArrayList<Node> base = new ArrayList<>();
        base.add(node);
        return base;
    }

    ArrayList<Node> left = NodeToRootPath(node.left,data);
    if(left != null) // means left se hi ans aarha hai bnkr.
    {
        left.add(node);
        return left;
    } 

    ArrayList<Node> right = NodeToRootPath(node.right,data);
    if(right != null)
    {
        right.add(node);
        return right;
    }

    return null;
}

//pre-order additon krenge.
public static boolean RootToNodePath(Node node,int data,ArrayList<Node> ans)
{
    if(node == null)
    return false;

    if(node.data == data)
    {
        ans.add(node);
        return true;
    }

    ans.add(node); //pehle hi node add kr denge ans mei.
    boolean res = RootToNodePath(node.left,data,ans) || RootToNodePath(node.right,data,ans);
    if(!res) // agr vo node ans nhi bnaayegi to usko remove krdenge.
    {
        ans.remove(ans.size()-1);
    }
    return res;
}

//Uses NodeToRootPath.
public static void lowestCommonAncestor(Node node,int a,int b)
{
    ArrayList<Node> list1 = new ArrayList<>();
    ArrayList<Node> list2 = new ArrayList<>();

    NodeToRootPath(node,a,list1); //list1 and list2 mei NodetoRootPath aa jaayega a aur b data vaali nodes ka.
    NodeToRootPath(node,b,list2);

    int i = list1.size()-1;
    int j = list2.size()-1;

    Node LCA = null;
    while(i >= 0 && j >= 0)
    {
        if(list1.get(i) == list2.get(j)) 
        LCA = list1.get(i);
        //jab tk last se loop chlaate huye dono lists mei same nodes chlte rahenge hum LCA update krte rhenge.
        i--;
        j--;
    }

    System.out.println(LCA.data);
}

//Kdown -> root se kth distance neeche saare nodes le aata hai.
public static void Kdown(Node node,Node block,int k,ArrayList<Integer> ans)
{
    if(node == null || node == block || k < 0)
    return;

    if(k == 0)
    {
        ans.add(node.data);
        return;
    }

    Kdown(node.left,block,k-1,ans);
    Kdown(node.right,block,k-1,ans);
}

//kFar = Kdown + NodetoRootPath.
public static ArrayList<Integer> kFar(Node node,int data,int k)
{
    ArrayList<Node> list = new ArrayList<>();
    NodeToRootPath(node,data,list);

    Node prev = null;
    ArrayList<Integer> ans = new ArrayList<>();
    for(int i = 0;i < list.size();i++)
    {
        Kdown(list.get(i),prev,k-i,ans);
        prev = list.get(i); // prev ko block bnaya h taaki iski call dobaara na lgaye Kdown.
    }
    return ans;
}

//Finds rootToNode distance similar to NodetoRoot path bs int return krwa rhe hai.
//It is applied in kFar_02
public static int rootToNodeDistance(Node node,int data)
{
    if(node == null)
    return -1;
    if(node.data == data)
    return 0; //in terms of edges so return 0 agr nodes ke terms mei distance hota to return 1.

    int lans = rootToNodeDistance(node.left,data);
    if(lans != -1)
    return lans+1;

    int rans = rootToNodeDistance(node.right,data);
    if(rans != -1)
    return rans+1;

    return -1;
}

//Optimized as it only uses Kdown(Doesn't uses NodetoRootPath) 
//It is a combination of kDown and rootToNodeDistance.
public static int kFar_02(Node node,int data,int k,ArrayList<Integer> ans)
{
    if(node == null)
    return -1;

    if(node.data == data)
    {
        Kdown(node,null,k,ans);
        return 1;
    }

    int ld = kFar_02(node.left,data,k,ans);
    if(ld != -1)
    {
        Kdown(node,node.left,k-ld,ans);
        return ld+1;
    }
    
    int rd = kFar_02(node.right,data,k,ans);
    if(rd != -1)
    {
        Kdown(node,node.right,data,k-rd,ans);
        return rd+1;
    }
    return -1;
}

//Burning Tree -> GFG 
public static void kDownForBurningTree(Node node,Node block,int time,ArrayList<ArrayList<Integer>> ans)
{
    if(node == null || node == block)
    return;
        
    if(ans.size() == time)
    ans.add(new ArrayList<>());
        
    ans.get(time).add(node.data);
        
    kDown(node.left,block,time+1,ans);
    kDown(node.right,block,time+1,ans);
}

//Similar to KFar bs K ki jagah time rakhna hai kDown mei.
public static ArrayList<ArrayList<Integer>> BurnTree(Node root, int target) 
{
    ArrayList<Node> list = new ArrayList<>();
    NodeToRootPath(root,target,list);
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    Node prev = null;
    for(int i = 0;i < list.size();i++)
    {
        kDown(list.get(i),prev,i,ans);
        prev = list.get(i);
    }
    return ans;
}

//Diameter yaa to left subTree dega yaa right subTree ya fir dono ki height + 2 teeno mei se maximum.
//har level pr height funct dobaara call ho rha so complexity -> n^2
public static int diameter_01(Node node)
{
    if(node == null)
    return 0;

    int ld = diameter_01(node.left);
    int rd = diameter_01(node.right);

    int lh = height(node.left);
    int rh = height(node.right);

    return Math.max(Math.max(ld,rd),lh+rh+2);
}

//Storing dia and height of previous levels in an array of 2 size.
//Complexity -> n
public static int[] diameter_02(Node node)
{
    if(node == null)
    return new int[]{0,-1} // diameter,height
    
    int [] lres = diameter_02(node.left);
    int [] rres = diameter_02(node.right);

    int dia = Math.max(Math.max(lres[0],rres[0]),lres[1]+rres[1]+2);
    int hei = Math.max(lres[1],rres[1]) + 1; // calculating hei on every level through previously stored values in the arr.

    return new int[]{dia,hei};
}

//Most optimized as diameter_02 mei space use ho rha tha extra
//int[] diaAns mei diameter store ho rha hai iski jagah static variable bhi use kr skte the.
//Normal height function vaali recursion chla rhe hai and har level pr diameter -> diaAns mei store kr rhe hai nikaal kr.
public static int diameter_03(Node node,int[] diaAns)
{
    if(node == null)
    return -1;

    int lh = diameter_03(node.left,diaAns);
    int rh = diameter_03(node.right,diaAns);

    diaAns[0] = Math.max(diaAns[0],lh+rh+2); // har level ka diameter yaha update ho rha hai max check krke.
    return Math.max(lh,rh) + 1; // height vaala function hai same.
}

//BFS -> level order 

public static void BFS_01(Node node) // generic bfs technique.
{
    LinkedList<Node> que = new LinkedList<>(); // que nhi hoti java mei so ll ka use krke uski functionality copy kri hai.
    que.addLast(node);

    while(que.size() != 0)
    {
        Node vtx = que.removeFirst();
        System.out.print(vtx.data +  " ");

        if(vtx.left != null) que.addLast(vtx.left);
        if(vtx.right != null) que.addLast(vtx.right);
    }
}

//har level ko print krne ke baad line break dena hai -> delimeter technique
public static void BFS_02(Node node) 
{
    LinkedList<Node> que = new LinkedList<>();
    que.addLast(node);
    que.addLast(null); // delimeter technique -> jab null encounter hoga to println krdenge.

    while(que.size() != 1) // last mei null bach jaayega que mei.
    {
        Node vtx = que.removeFirst();
        System.out.print(vtx.data + " ");

        if(vtx.left != null) que.addLast(vtx.left);
        if(vtx.right != null) que.addLast(vtx.right);

        if(que.getFirst() == null)
        {
            System.out.println();
            que.removeFirst(); // null starting se remove krke end mei insert krdia.
            que.addLast(null);
        }
    }
}

//Har level print krne ke baad line break krni hai. -> har level ko andar vaala while loop print krdega.
public static void BFS_03(Node node)
{
    LinkedList<Node> que = new LinkedList<>();
    que.addLast(node);

    int level = 0;
    while(que.size() != 0)
    {
        int size = que.size();
        System.out.print("Level : " + level + " -> ");
        while(size-- > 0)
        {
            Node vtx = que.removeFirst();
            System.out.print(vtx.data + " ");

            if(vtx.left != null) que.addLast(vtx.left);
            if(vtx.right != null) que.addLast(vtx.right);
        } // yaha ek complete level print ho jaayega.
        System.out.println();
        level++;
    }
}

//Using two queues technique -> root ko parent queue mei push krna hai and uske children ko dusri 
//que mei(childQue) and when size of parent que is 0 to println,swap the queues and level update krna hai.
public static void BFS_04(Node node)
{
    LinkedList<Node> que = new LinkedList<>();
    LinkedList<Node> childQue = new LinkedList<>();
    que.addLast(node);

    int level = 0;
    System.out.print("Level" + level + " : ");
    while(que.size() != 0)
    {
        Node vtx = que.removeFirst();
        System.out.print(vtx.data + " ");

        if(vtx.left != null) childQue.addLast(vtx.left);
        if(vtx.right != null) childQue.addLast(vtx.right);

        if(que.size() == 0)
        {
            level++;
            LinkedList<Node> temp = que; //swapping queues when parentQue becomes empty.
            que = childQue;
            childQue = temp;

            System.out.println();
            System.out.print("Level" + level + " : ");
        }
    }
}

//BFS mei har level ka first node apne ans mei add krte jaao.
//Geometry of tree is not considered generally in left and rightview of tree.
public static List<Integer> leftView(Node node)
{
    LinkedList<Node> que = new LinkedList<>();
    que.addLast(node);

    List<Integer> ans = new ArrayList<>();
    while(que.size() != 0)
    {
        ans.add(que.getFirst().data);
        int size = que.size();
        while(size-- > 0)
        {
            Node vtx = que.removeFirst();
            
            if(vtx.left != null) que.addLast(vtx.left);
            if(vtx.right != null) que.addLast(vtx.right);
        }

    }
    return ans;
}

//har level ka last node rightView mei hoga
public static List<Integer> rightView(Node node)
{
    LinkedList<Node> que = new LinkedList<>();
    que.addLast(node);

    List<Integer> ans = new ArrayList<>();
    while(que.size() != 0)
    {
        int size = que.size();
        ans.add(que.getLast().val);
        while(size-- > 0)
        {
            Node vtx = que.removeFirst();

            if(vtx.left != null) que.addLast(vtx.left);
            if(vtx.right != null) que.addLast(vtx.right);
        }
    }
    return ans;
}

public static class VerticalPair{
    Node node = null;
    int Hlevel = 0; // Horizontal Level -> x - coordinate
    
    VerticalPair(Node node,int val)
    {
        this.node = node;
        this.Hlevel = Hlevel;
    }
}

//Geometry consider krni hai. BFS with width(yaani x coordinates).
// Using HashMap
public static List<List<Integer>> VerticalOrderTraversal_01(Node node)
{
    LinkedList<VerticalPair> que = new LinkedList<>();
    HashMap<Integest<Inter,Liger>> map = new HashMap<>(); // HashMap of Hlevel vs ArrayList<Integer>

    int minHL = 0;
    int maxHL = 0;

    que.addLast(new VerticalPair(node,0));
    while(que.size() != 0)
    {
        int size = que.size();
        while(size-- > 0)
        {
            VertcialPair vtx = que.removeFirst();
            map.putIfAbsent(vtx.Hlevel,new ArrayList<>()); // First time hi new krni hai bs AL.
            //if(!map.containsKey(vtx.Hlevel))
            //map.put(vtx.Hlevel,new ArrayList<>());

            map.get(vtx.Hlevel).add(vtx.node.data);

            minHL = Math.min(minHL,vtx.Hlevel);
            maxHL = Math.max(maxHL,vtx.Hlevel);

            if(vtx.node.left != null)
            que.addLast(new VertcialPair(vtx.node.left,Hlevel-1));

            if(vtx.node.right != null)
            que.addLast(new VerticalPair(vtx.node.right,Hlevel+1));
        }
    }
    List<List<Integer>> ans = new ArrayList<>();
    while(minHL <= maxHL)
    {
        ans.add(map.get(minHL)); // shi order mei saari AL's present in HashMap add ho jaayengi ans mei.
        minHL++;
    }
    return ans;
}

public static void widthOfBinaryTree(Node node,int level,int [] maxMin)
{
    if(node == null)
    return;

    maxMin[0] = Math.max(maxMin[0],level);
    maxMin[1] = Math.min(maxMin[1],level);

    widthOfBinaryTree(node.left,level-1,maxMin);
    widthOfBinaryTree(node.right,level+1,maxMin);

    // widthofBinaryTree = maxMin[0] - maxMin[1] + 1
}

// Using widthOfBinaryTree 
public static void VerticalOrderTraversal_02(Node node)
{
    int [] maxMin = new int[2];
    widthOfBinaryTree(node,0,maxMin);
    int w = maxMin[0] - maxMin[1] + 1;

    ArrayList<Integer>[] ans = new ArrayList[w]; //array of arraylist
    for(int i = 0; i < w;i++)
    ans[i] = new ArrayList<>();

    LinkedList<VerticalPair> que = new LinkedList<>();
    que.addLast(new VertcialPair(node.data,-maxMin[1])); 

    while(que.size() != 0)
    {
        int size = que.size();
        while(size-- > 0)
        {
            pair vtx = que.removeFirst();

            ans[vtx.Hlevel].add(vtx.node.data);

            if(vtx.node.left != null)
            que.addLast(new pair(vtx.node.left,vtx.Hlevel-1));
            if(vtx.node.right != null)
            que.addLast(new pair(vtx.node.right,vtx.Hlevel+1));
        }
    }
}

public static void VerticalSum(Node node)
{
    int [] maxMin = new int[2];
    widthOfBinaryTree(node,0,maxMin);
    int w = maxMin[0] - maxMin[1] + 1;

    int [] ans = new int[w];

    LinkedList<VerticalPair> que = new LinkedList<>();
    que.addLast(new VerticalPair(node.data,-maxMin[1]));

    while(que.size() != 0)
    {
        int size = que.size();
        while(size-- > 0)
        {
            pair vtx = que.removeFirst();
            ans[vtx.Hlevel] += ans[vtx.node.data];

            if(vtx.node.left != null)
            que.addLast(new VerticalPair(vtx.node.left,vtx.Hlevel-1));
            if(vtx.node.right != null)
            que.addLast(new VerticalPair(vtx.node.right,vtx.Hlevel+1));
        }
    }
}

public static void BottomView(Node node)
{
    int maxMin = new int[2];
    widthOfBinaryTree(node,0,maxMin);
    int w = maxMin[0] - maxMin[1] + 1;

    int[] ans = new int[w];

    LinkedList<VerticalPair> que = new LinkedList<>();
    que.addLast(new VerticalPair(node,-maxMin[1]));

    while(que.size() != 0)
    {
        int size = que.size();
        while(size-- > 0)
        {
            VerticalPair vtx = que.removeFirst();
            
            ans[vtx.Hlevel] = vtx.node.data; //last updation hi bottomView dega.

            if(vtx.node.left != null)
            que.addLast(new VerticalPair(vtx.node.left,vtx.Hlevel-1));
            if(vtx.node.right != null)
            que.addLast(new VerticalPair(vtx.node.right,vtx.Hlevel+1));
        }
    }
}

public static void TopView(Node node)
{
    int maxMin = new int[2];
    widthOfBinaryTree(node,0,maxMin);
    int w = maxMin[0] - maxMin[1] + 1;

    Integer[] ans = new Integer[w]; //Integer taaki hum neeche ans[vtx.val] == null ka use kr paaye

    LinkedList<VerticalPair> que = new LinkedList<>();
    que.addLast(new VerticalPair(node,-maxMin[1]));

    while(que.size() != 0)
    {
        int size = que.size();
        while(size-- > 0)
        {
            pair vtx = que.removeFirst();
            
            if(ans[vtx.Hlevel] == null)
            ans[vtx.Hlevel] = vtx.node.data; //first updation TopView dega.

            if(vtx.node.left != null)
            que.addLast(new VerticalPair(vtx.node.left,vtx.Hlevel-1));
            if(vtx.node.right != null)
            que.addLast(new VerticalPair(vtx.node.right,vtx.Hlevel+1));
        }
    }

}

//Same as VerticalOrderTraversal bs left ya right jaate huye level same rkhna aur dusre level mei +1 ya -1 krna hai.
public static DiagonalOrderTraversal(Node node)
{
    LinkedList<VerticalPair> que = new LinkedList<>();
    HashMap<Integer,List<Integer>> map = new HashMap<>(); // HashMap of Hlevel vs ArrayList<Integer>

    int minHL = 0;
    int maxHL = 0;

    que.addLast(new VerticalPair(node,0));
    while(que.size() != 0)
    {
        int size = que.size();
        while(size-- > 0)
        {
            VertcialPair vtx = que.removeFirst();
            map.putIfAbsent(vtx.Hlevel,new ArrayList<>()); // First time hi new krni hai bs AL.
            //if(!map.containsKey(vtx.Hlevel))
            //map.put(vtx.Hlevel,new ArrayList<>());

            map.get(vtx.Hlevel).add(vtx.node.data);

            minHL = Math.min(minHL,vtx.Hlevel);
            maxHL = Math.max(maxHL,vtx.Hlevel);

            if(vtx.node.left != null)
            que.addLast(new VertcialPair(vtx.node.left,Hlevel-1)); //left jaate huye level-1 or level+1 depending on test case.

            if(vtx.node.right != null)
            que.addLast(new VerticalPair(vtx.node.right,Hlevel)); //right jaate huye same level
        }
    }
    List<List<Integer>> ans = new ArrayList<>();
    while(minHL <= maxHL)
    {
        ans.add(map.get(minHL)); // shi order mei saari AL's present in HashMap add ho jaayengi ans mei.
        minHL++;
    }
    return ans;
}

//Inorder Predeccessor and successor of BinaryTree.
public static class predsuccPair{
    Node prev = null;
    Node pred = null;
    Node succ = null;
}

public static void predsuccInBT(Node node,int data,predsuccPair pair)
{
    if(node == null)
    return;

    predsuccInBT(node.left,data,pair);

    if(node.data == data && pair.pred == null) // only first updation of pred to be considered incase of multiple 'data' nodes present in tree.
    pair.pred = pair.prev;

    if(pair.prev != null && pair.prev.data == data && pair.succ == null) // only first updation of succ to be considered.
    pair.succ = node;

    pair.prev = node;
    predsuccInBT(node.right,data,pair);
}

// Tell ceil and floor value in BT wrt 'data'.
public static class ceilfloorPair{
    int floor = -(int)1e9;
    int ceil = (int)1e9;
}

//In ceil and floor value it doesnt matter if we will find 'data' node in our tree.
public static void CeilandFloorValueInBT(Node node,int data,ceilfloorPair pair)
{
    if(node == null)
    return;

    if(node.data < data) // data se saare chotte nodes ka sbse bada node -> floor
    pair.floor = Math.max(pair.floor,node.data);

    if(node.data > data) // data se saare bade nodes ka sbse chotta node -> ceil
    pair.ceil = Math.min(pair.ceil,node.data);

    CeilandFloorValueInBT(node.left,data,pair);
    CeilandFloorValueInBT(node.right,data,pair);

}

//Traversals ===========================================================================================

//Morris In and PreOrder Traversal -> Traversal with O(n) time and O(1) space(iterative).
public static Node rightMostNode(Node next,Node curr)
{
    while(next.right != null || next.right != curr)
    next = next.right;

    return next;
}

public static void morrisInorderTraversal(Node root)
{
    Node curr = root;
    while(curr != null)
    {
        Node next = curr.left;

        if(next == null)
        {
            System.out.print(curr.data + " ");
            curr = curr.right;
        }
        else{
            Node rightMost = rightMostNode(next,curr);
            if(rightMost.right == null) // Create Thread
            {
                rightMost.right = curr;
                curr = curr.left;
            }
            else{ // Remove Thread
                rightMost.right = null;
                System.out.print(curr.data + " ");
                curr = curr.right;
            }
        }
    }
    
}

public static void morrisPreorderTraversal(Node root)
{
    Node curr = root;
    while(curr != null)
    {
        Node next = curr.left;
        
        if(next == null)
        {
            System.out.print(curr.data + " ");
            curr  = curr.right;
        }
        else{
            Node rightMost = rightMostNode(next,curr);
            if(rightMost.right == null)
            {
                rightMost.right = curr;
                System.out.print(curr.data + " "); // Jaha thread create kr rhe hai vaha print for inorder
                curr = curr.left;
            }
            else{
                rightMost.right = null;
                curr = curr.right;
            }
        }
    }
}

//Iterative Traversals using stack - O(n) space
public static class tPair{ // Traversal Pair
    Node node = null;
    boolean leftDone = false;
    boolean selfDone = false;
    boolean rightDone = false;

    tPair(Node node,boolean leftDone,boolean selfDone,boolean rightDone)
    {
        this.node = node;
        this.leftDone = leftDone;
        this.selfDone = selfDone;
        this.rightDone = rightDone;
    }
}

public static void InorderIterative(Node root)
{
    LinkedList<tPair> st = new LinkedList<>();
    st.addFirst(new tPair(root,false,false,false));
    
    while(st.size() != 0)
    {
        tPair pair = st.getFirst();
        if(!pair.leftDone) // equivalent to left call in recursion.
        {
            pair.leftDone = true;
            if(pair.node.left != null)
            st.addFirst(new tPair(pair.node.left,false,false,false));
        }
        
        else if(!pair.selfDone)
        {
            pair.selfDone = true;
            System.out.print(pair.node.data + " ");
        }

        else if(!pair.rightDone) // equivalent to right call in recursion.
        {
            pair.rightDone = true;
            if(pair.node.right != null)
            st.addFirst(new tPair(pair.node.right,false,false,false));
        }
        
        else
        st.removeFirst();
    }
    
}

public static void PreorderIterative(Node root)
{
    LinkedList<tPair> st = new LinkedList<>();
    st.addFirst(new tPair(root,false,false,false));
    
    while(st.size() != 0)
    {
        tPair pair = st.getFirst();

        if(!pair.selfDone)
        {
            pair.selfDone = true;
            System.out.print(pair.node.data + " ");
        }

        else if(!pair.leftDone) // equivalent to left call in recursion.
        {
            pair.leftDone = true;
            if(pair.node.left != null)
            st.addFirst(new tPair(pair.node.left,false,false,false));
        }

        else if(!pair.rightDone) // equivalent to right call in recursion.
        {
            pair.rightDone = true;
            if(pair.node.right != null)
            st.addFirst(new tPair(pair.node.right,false,false,false));
        }
        
        else
        st.removeFirst();
    } 
}

public static void PostorderIterative(Node root)
{
    LinkedList<tPair> st = new LinkedList<>();
    st.addFirst(new tPair(root,false,false,false));
    
    while(st.size() != 0)
    {
        tPair pair = st.getFirst();
        if(!pair.leftDone) // equivalent to left call in recursion.
        {
            pair.leftDone = true;
            if(pair.node.left != null)
            st.addFirst(new tPair(pair.node.left,false,false,false));
        }
        
        else if(!pair.rightDone) // equivalent to right call in recursion.
        {
            pair.rightDone = true;
            if(pair.node.right != null)
            st.addFirst(new tPair(pair.node.right,false,false,false));
        }

        else if(!pair.selfDone)
        {
            pair.selfDone = true;
            System.out.print(pair.node.data + " ");
        }
        
        else
        st.removeFirst();
    }
    
}

public static void solve()
{
    int[] arr={10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,-1,70,110,-1,-1,120,-1,-1};
    Node root = constructTree(arr);
    display(root);
}

public static void main(String[] args)
{
    solve();
} 
}