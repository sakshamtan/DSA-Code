#include<iostream>
#include<vector>

using namespace std;

class Node{
    public:
    int data = 0;
    vector<Node*> children;

    Node(int data)
    {
        this->data = data;
    }
};

boolean nodeToRootPath(Node* node,int data,vector<Node*>& ans)
{
    if(node->data == data)
    {
        ans.push_back(node->data);
        return true;
    }
    boolean res = false;

    for(Node* child : node->children)
    {
        res = res || nodeToRootPath(child,data,ans);
    }
    if(res)
    ans.push_back(node);

    return res;
}

vector<Node*> nodeToRootPath(Node* node,int data)
{
    vector<Node*> ans;
    nodeToRootPath(node,data,ans);

    return ans;
}

void kDownBurnGT(Node* node,Node* blockNode,int time,vector<vector<int>> ans)
{
    if(node == blockNode)
    return;

    if(ans.size() == time)
    ans.push_back({});

    ans[time].push_back(node->data);

    for(Node* child : node->children)
    {
        kDownBurnGT(child,blockNode,target,time+1,ans);
    }
}

vector<vector<int>> BurningGT(Node* node,int target)
{
    vector<Node*> list = nodeToRootPath(node,target);

    vector<vector<int>> ans;
    Node* blockNode = nullptr;

    for(int i = 0;i < list.size();i++)
    {
        kDownBurnGT(node,blockNode,i,ans);
        blockNode = list[i];
    }
    return ans;
}