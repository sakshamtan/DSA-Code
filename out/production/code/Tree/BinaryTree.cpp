#include <iostream>
#include <vector>
#include <queue>
#include <unordered_map>
#include <priority_queue>

using namespace std;

struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

vector<TreeNode*> NodetoRootPath(TreeNode* node,int data)
{
    if(node == nullptr)
    return {}; // empty vector.

    if(node->val == data)
    {
        vector<TreeNode*> base;
        base.push_back(node);
        return base;
    }

    vector<TreeNode*> left = NodetoRootPath(node->left,data);
    if(left.size() != 0)
    {
        left.push_back(node);
        return left;
    }

    vector<TreeNode*> right = NodetoRootPath(node->right,data);
    if(right.size() != 0)
    {
        right.push_back(node);
        return right;
    }

    return {};
}

//Leetcode 236 -> uses NodetoRootPath.
TreeNode* lowestCommonAncestor(TreeNode* node,TreeNode* p,TreeNode* q)
{
    vector<TreeNode*> list1 = NodetoRootPath(node,p->val);
    vector<TreeNode*> list2 = NodetoRootPath(node,q->val);

    int i = list1.size()-1;
    int j = list2.size()-1;
    
    TreeNode* LCA = nullptr;
    while(i >= 0 && j >= 0)
    {
        if(list1[i] == list2[j])
        LCA = list1[i];

        i--;
        j--;
    }
    return LCA;
}

//Leetcode 113 -> nodeToLeafPaths
void pathSum(TreeNode* node,int tar,vector<vector<int>> &res,vector<int> &smallAns)
{
    if(node == nullptr)
    return;

    if(node->left == nullptr && node->right == nullptr && tar - node->val == 0)
    {
        smallAns.push_back(node->val);
        res.push_back(smallAns);
        smallAns.pop_back();
        return;
    }
    smallAns.push_back(node->val);
    pathSum(node->left,tar-node->val,res,smallAns);
    pathSum(node->right,tar-node->val,res,smallAns);
    smallAns.pop_back();
}
vector<vector<int>> pathSum(TreeNode* root, int targetSum)
{
    vector<vector<int>> res;
    vector<int> ans;
    pathSum(root,targetSum,res,ans);
    return res;
}

//Leetcode 124 -> maxPathSum NodeToNode
//Another approach similar to diameter_02 function without using static/global variable.
class maxNodeToNodePair{
    public:
    int NTN_sum = -(int)1e9; // NodeToNode maxSum -> our required ans jo hum static/global mei calculate kr rhe the.
    int NTR_sum = 0; // NodeToRoot maxSum -> jo hum recursion mei return krwa rhe the to calculate our ans at each level.
    
    maxNodeToNodePair(int NTN_sum,int NTR_sum)
    {
        this->NTN_sum = NTN_sum;
        this->NTR_sum = NTR_sum; 
    }
    maxNodeToNodePair(){} //empty constructor.
};

//Takes input as in array so jitni marzi quantities ka maximum de deta hai ye function.
int maxValue(vector<int> arr) 
{
    int max_ = arr[0];
    for(int ele : arr)
    {
        max_ = max(max_,ele);
    }
    return max_;
}

maxNodeToNodePair NodetoNode_02(TreeNode* node)
{
    if(node == nullptr)
    return maxNodeToNodePair(); //used as a local variable created on stack

    maxNodeToNodePair lpair = NodetoNode_02(node->left);
    maxNodeToNodePair rpair = NodetoNode_02(node->right);

    maxNodeToNodePair myAns;

    myAns.NTN_sum = maxValue({lpair.NTN_sum, rpair.NTN_sum, node->val, lpair.NTR_sum + node->val,
    rpair.NTR_sum + node->val, lpair.NTR_sum + node->val + rpair.NTR_sum});

    myAns.NTR_sum = maxValue({node->val, lpair.NTR_sum + node->val, rpair.NTR_sum + node->val});

    return myAns;
}
//Leetcode Function
int maxPathSum(TreeNode* root) 
{
    if(root == nullptr)
    return 0;

    return NodetoNode_02(root).NTN_sum;        
}

//Leetcode 199 -> rightView -> last node of every level or first node of every level if right is called is first.
vector<int> rightView(TreeNode* node)
{
    if(node == nullptr) return {};
    queue<TreeNode*> que;
    que.push(node);

    vector<int> ans;
    while(que.size() != 0)
    {
        int size = que.size();
        ans.push_back(que.front()->val);
        while(size-- > 0)
        {
            TreeNode* vtx = que.front();
            que.pop();

            if(vtx->right != nullptr) que.push(vtx->right);
            if(vtx->left != nullptr) que.push(vtx.left);
        }
    }
    return ans;
}

void rightView_Recursion(TreeNode* node,int level,vector<int>& ans)
{
    if(node == nullptr)
    return;

    if(level == ans.size()) ans.push_back(node->val);

    rightView_Recursion(node->right,level+1,ans);
    rightView_Recursion(node->left,level+1,ans);
}

vector<int> rightSideView(TreeNode* node)
{
    vector<int> ans;
    rightView_Recursion(node,0,ans);
    return ans;
}

//Vertical Order Traversal 
class VerticalPair{
    public:
    TreeNode* node = nullptr;
    int Hlevel = 0;

    VerticalPair(TreeNode* node,int Hlevel)
    {
        this->node = node;
        this->Hlevel = Hlevel;
    }
};

//Soltuion using unordered_map(HashMap).
vector<vector<int>> verticalOrderTraversal_01(TreeNode* node)
{
    queue<VerticalPair> que;
    que.push(VerticalPair(node,0));

    unordered_map<int,vector<int>> map;

    int minHL = 0;
    int maxHL = 0;

    while(que.size() != 0)
    {
        int size = que.size();
        while(size-- > 0)
        {
            VerticalPair vtx = que.front();
            que.pop();

            map[vtx.Hlevel].push_back(vtx.node->val);

            minHL = min(minHL,vtx.node->val);
            maxHL = max(maxHL,vtx.node->val);

            if(vtx.node->left != nullptr)
            que.push(VerticalPair(vtx.node->left,vtx.Hlevel-1));

            if(vtx.node->right != nullptr)
            que.push(VerticalPair(vtx.node->right,vtx.Hlevel+1));
        }
    }
    vector<vector<int>> res;
    while(minHL <= maxHL)
    {
        res.push_back(map[minHL]);
        minHL++;
    }
    return res;
}

void widthOfBinaryTree(TreeNode* node,int Hlevel,vector<int> maxMin)
{
    if(node == nullptr)
    return;

    maxMin[0] = min(maxMin[0],Hlevel);
    maxMin[1] = max(maxMin[1],Hlevel);

    widthOfBinaryTree(node->left,Hlevel-1,maxMin);
    widthOfBinaryTree(node->right,Hlevel+1,maxMin);
}

//Solution 2 -> using widthOfBinaryTree
vector<vector<int>> verticalOrderTraversal_02(TreeNode* node)
{
    vector<int> maxMin(2,0);
    widthOfBinaryTree(node,0,maxMin);
    int w = maxMin[1] - maxMin[0] + 1;
    
    vector<vector<int>> res(w);

    queue<VerticalPair> que;
    que.push(VerticalPair(node,-maxMin[0]));

    while(que.size() != 0)
    {
        int size = que.size();
        while(size-- > 0)
        {
            VerticalPair vtx = que.front();
            que.pop();

            res[vtx.Hlevel].push_back(vtx.node->val);

            if(vtx.left != nullptr)
            que.push(VerticalPair(vtx.node->left,vtx.Hlevel-1));

            if(vtx.right != nullptr)
            que.push(VerticalPair(vtx.node->right,vtx.Hlevel+1));
        }
    }
    return res;
}

vector<int> TopViewOfBinaryTree(TreeNode* node)
{
    vector<int> maxMin(2,0);
    widthOfBinaryTree(node,0,maxMin);
    int len = maxMin[1] - maxMin[0] + 1;

    vector<int> res(len,0);
    vector<bool> visRes(len,false); // pehla updation hi krna hai usko check krne ke liye.

    queue<VerticalPair> que;
    que.push(VerticalPair(node,-maxMin[0]));

    while(que.size() != 0)
    {
        int size = que.size();
        while(size-- > 0)
        {
            VerticalPair vtx = que.front();
            que.pop();

            if(!visRes[vtx.Hlevel])
            {
                res.push_back(vtx.node->val);
                visRes[vtx.Hlevel] = true;
            }

            if(vtx.node->left != nullptr)
            que.push(VerticalPair(vtx.node->left,vtx.Hlevel-1));

            if(vtx.node->right != nullptr)
            que.push(VerticalPair(vtx.node->right,vtx.Hlevel+1));
        }
    }
    return res;
}

//Leetcode 987 -> Vertical Order Traversal.
class VerticalPair_02{
    public:
    TreeNode* node = nullptr;
    int x = 0;
    int y = 0;

    VerticalPair_02(TreeNode* node,int x,int y)
    {
        this->node = node;
        this->x = x;
        this->y = y;
    }
};

struct comp{ // overriding greater function of default maxPQ to make it as minPQ on our non primitive data type.
    public:
    bool operator()(const VerticalPair_02 a, const VerticalPair_02 b) const
    {
        if(a.y != b.y)
        return a.y > b.y; // same as java this - other bs '-' ki jagah '>' as it is a greater function.
        else
        return a.node->val > b.node->val;
    }
};

//Using only single Priority Queue.
vector<vector<int>> verticalTraversal(TreeNode* root)
{
    priority_queue<VerticalPair_02,vector<VerticalPair_02>,comp> que;

    unordered_map<int,vector<int>> map;
    int minHL = 0,maxHL = 0;
    
    que.push(VerticalPair_02(root,0,0));

    while(que.size() != 0)
    {
        VerticalPair_02 vtx = que.top();
        que.pop();

        minHL = min(minHL,vtx.x);
        maxHL = max(maxHL,vtx.x);

        map[vtx.x].push_back(vtx.node->val);

        if(vtx.node->left != nullptr)
        que.push(VerticalPair_02(vtx.node->left,vtx.x-1,vtx.y+1));
        if(vtx.node->right != nullptr)
        que.push(VerticalPair_02(vtx.node->right,vtx.x+1,vtx.y+1));
    }

    vector<vector<int>> res;
    while(minHL <= maxHL)
    {
        res.push_back(map[minHL]);
        minHL++;
    }
    return res;
}




