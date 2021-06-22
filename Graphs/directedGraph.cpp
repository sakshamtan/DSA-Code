# include <iostream>
# include <vector>
# include <queue>

using namespace std;

class Edge{
    public:
    int v = 0;  // final vtx of edge. u -> initial vtx of edge jo hum vector ke index se nikaal lenge
    int w = 0;  // weight of the edge
    
    Edge(int v,int w)
    {
        this->v = v;
        this->w = w;
    }
};

const int N = 11; // Total No of vtx
vector<vector<Edge>> graph(N);

void addEdge(int u,int v,int w)
{
    graph[u].push_back(Edge(v,w));
}

void display()
{
    for(int u = 0;u < N;u++)
    {
        cout<<to_string(u) + " -> ";
        for(Edge e : graph[u])
        {
            cout<<" ( " +  to_string(e.v) + " , " + to_string(e.w) + " ) ";
        }
        cout<<endl;
    }
}

// Topological Order through DFS of a directed graph.
void dfs_topo(int src,vector<bool>& vis,vector<int>& ans)
{
    vis[src] = true;

    for(Edge e : graph[src])
    {
        if(!vis[e.v])
        dfs_topo(e.v,vis,ans);
    }
    ans.push_back(src); //postOrder mei arr mei store krlia node.
}

void topologicalOrder_DFS()
{
    vector<bool> vis(N,false);
    vector<int> ans;
    for(int i = 0; i < N;i++) // Directed graph mei ek call se ho skta hai ki koi segment visit na ho to gcc vaali call.
    {
        if(!vis[i])
        dfs_topo(i,vis,ans);
    }

    for(int ele : ans)
    cout << ele << " ";
}

//Topological order through BFS -> kahn's algorithm -> detects cycle in directed graph easily.
void kahnsAlgo()
{
    queue<int> que;
    vector<int> ans;
    bool isCycle = false;

    vector<int>indegree(N,0); // Indegree array to count dependencies
    for(int i = 0; i < N; i++)
    {
        for(Edge e : graph[i])
        indegree[e.v]++;
    }

    for(int i = 0; i < N; i++) // 0 indegree vaale saare vtx sbse pehle push hote hai que mei.
    {
        if(indegree[i] == 0)
        que.push(i);
    }

    int level = 0;
    while(que.size() != 0)
    {
        int size = que.size();
        while(size-- > 0)
        {
            int rvtx = que.front();
            que.pop();

            ans.push_back(rvtx);

            for(Edge e : graph[rvtx])
            {
                if(--indegree[e.v] == 0)
                que.push(e.v);
            }
        }
        level++;
    }

    if(ans.size() != N) //Agr cycle hogi to poora dfs nhi chlega and ans poora N vertices ke barabar nhi bnega.
    isCycle = true;

    for(int ele : ans)
    {
        cout << ele <<" ";
    }
}

//DFS se cycle detection in directed graph and also writing topological order if cycle not present.
bool isCyclePresent_DFS(int src,vector<int>& vis,vector<int>& ans)
{
    vis[src] = 0; // 0 is that src is visited for the same path.
    bool res = false;

    for(Edge e : graph[src])
    {
        if(vis[e.v] == -1) // -1 -> not visited.
        res = res || isCyclePresent_DFS(e.v,vis,ans);
        else if(vis[e.v] == 0)
        return true;
    }
    
    vis[src] = 1; // 1 -> Not a part of our path now(was visited by some other path)
    ans.push_back(src);
    return res;
}

vector<int> isCyclePresent_DFS()
{
    vector<int> vis(N,-1);
    vector<int> ans;

    bool res = false;
    for(int i = 0; i < N; i++)
    {
        if(vis[i] == -1)
        res = res || isCyclePresent_DFS(i,vis,ans);
    }

    if(res) // If cycle present then writing topological order is not possible.
    ans.clear();

    return ans;
}

//Used in kosaraju -> ngraph pr topoOrder ke according traverse krta hai so iska har path ab ek scc dega.
void dfs_SCC(int src,vector<vector<Edge>>& ngraph,vector<bool>& vis,vector<int>& scc)
{
    vis[src] = true;

    for(Edge e : graph[src])
    {
        if(!vis[e.v])
        dfs_SCC(e.v,ngraph,vis,scc);
    }
    scc.push_back(src);
}

//Kosaraju algorithm finds the no. of scc present in the directed graph
void Kosaraju_Algo()
{
    vector<bool> vis(N,false);
    vector<int> topoOrder;
    for(int i = 0; i < N; i++)
    {
        if(!vis[i])
        dfs_topo(i,vis,topoOrder); // topological order of original graph.
    }

    //Inverting graph(Creating new graph with inverted edges(same graph mei delete krte rehte edges to comp. bd jaati).
    vector<vector<Edge>> ngraph(N);
    for(int i = 0; i < N; i++)
    {
        for(Edge e : graph[i])
        ngraph[e.v].push_back(Edge(i,10));  // u -> v tha graph v -> u krdia.
    }

    int countOfSCC = 0;
    vis.clear();
    for(int i = topoOrder.size()-1; i >= 0; i--)
    { 
        int ele = topoOrder[i];
        if(!vis[ele])
        {
            vector<int> scc;
            dfs_SCC(ele,ngraph,vis,scc);
            countOfSCC++;

            for(int e : scc) // printing each scc as soon as it gets created.
            cout << e << " ";
            cout << endl;
        }
    }
}

void constructGraph()
{
    addEdge(0,6,10);
    addEdge(6,7,10);
    addEdge(7,3,10);
    addEdge(2,3,10);
    addEdge(1,2,10);
    addEdge(5,1,10);
    addEdge(5,0,10);
    addEdge(4,8,10);
    addEdge(4,0,10);
    addEdge(8,9,10);
    addEdge(9,10,10);
    addEdge(10,3,10);

}

int main()
{
    constructGraph();
    // vector<bool>ssz vis(N,false);
    // topologicalOrder_DFS();
    kahnsAlgo();
    // display();
    return 0;
}