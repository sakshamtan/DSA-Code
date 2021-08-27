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

const int N = 8; // Total No of vtx
vector<vector<Edge>> graph(N);

void addEdge(int u,int v,int w)
{
    graph[u].push_back(Edge(v,w));
    graph[v].push_back(Edge(u,w));  // for bi-directional graph
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

//u pr jaakr v find krta hai and uska index return krta hai.
int findEdge(int u,int v)
{
    int idx = -1;
    for(int i = 0;i < graph[u].size();i++)
    {
        if(graph[u][i].v == v)
        {
            idx = i;
            break;
        }
    }
    return idx;
}

// u -> v vaala edge delete krna hai. for bi-directional graph v -> u edge bhi delete krna pdega
void removeEdge(int u,int v)
{
    int idx1 = findEdge(u,v);
    int idx2 = findEdge(v,u);

    graph[u].erase(graph[u].begin() + idx1);  // u -> v and v -> u deleted (bi-directional graph)
    graph[v].erase(graph[v].begin() + idx2); // erase() -> begin() se idx2th index door delete krdeta hai ele.

}

//By removing all the edges coming out of u we will eventually delete the Vtx u from the graph.
void removeVtx(int u)
{
    for(int i = graph[u].size()-1; i >= 0; i--) // peeche se remove krte hai hmesha array mei.
    {
        int v = graph[u][i].v;
        removeEdge(u,v);
    }
}

//Using DFS
bool hasPath(int src,int dest,vector<bool>& vis)
{
    if(src == dest)
    return true;

    vis[src] = true; // Ek hi path mil jaaye to kaam chl jaayega hence backtracking krte huye false mark nhi kra
    bool res = false;
    for(Edge e : graph[src])
    {   
        if(!vis[e.v])
            res = res || hasPath(e.v,dest,vis);
    }
    return res;
}

int printAllPaths(int src,int dest,vector<bool>& vis,string psf)
{
    if(src == dest)
    {
        cout<< psf + to_string(dest)<< endl;
        return 1;
    }

    int count = 0;
    vis[src] = true;
    for(Edge e : graph[src])
    {
        if(!vis[e.v])
        count += printAllPaths(e.v,dest,vis,psf + to_string(src) + " ");
    }
    vis[src] = false;
    return count;
}

//Weight and path 
class heavyPair{
    public:
    int w = 0;
    string path = "";

    heavyPair(int w,string path)
    {
        this->w = w;
        this->path = path;
    }
};

heavyPair heaviestPath(int src,int dest,vector<bool>& vis)
{
    if(src == dest)
    {
        heavyPair base(0,to_string(dest)); 
        return base;
        // dest se dest tk ka weight = 0 and last ka node add nhi hota string mei as recursion hum rok dete hai so adding dest in base case.
    }

    heavyPair myAns(-1e8,""); 

    vis[src] = true;
    for(Edge e : graph[src])
    {
        if(!vis[e.v])
        {
            heavyPair recAns = heaviestPath(e.v,dest,vis);
        
            if(recAns.w != -1e8 && recAns.w + e.w > myAns.w)
            {
                myAns.w = recAns.w + e.w;
                myAns.path = to_string(src) + " " + recAns.path;
            }
        }
    }
    vis[src] = false;
    return myAns;
}

//Each vtx should be traversed and exactly once so N-1 edges traverse krenge kisi path mei to vo hamiltonian path.
int hamiltonianPathAndCycle(int src,int osrc,int totalNoEdges,vector<bool>& vis,string psf)
{
    if(totalNoEdges == N-1)
    {
        int idx = findEdge(src,osrc);
// src last jaha tk jaa skte hai hamiltonian path mei vaha hoga (through recursion) if an edge from that Node to our original source exists then we have found a hamiltonion cycle. 
        if(idx != -1)
        cout<< "Cycle : " + psf + to_string(src) << endl;
        else
        cout<< "Path : " + psf + to_string(src) << endl;
        return 1;
    }

    int count = 0;
    vis[src] = true;
    for(Edge e : graph[src])
    {
        if(!vis[e.v])
        count += hamiltonianPathAndCycle(e.v,osrc,totalNoEdges+1,vis,psf + to_string(src));
    }
    vis[src] = false;
    return count;
}

int hamiltonianPathAndCycle(int src)
{
    vector<bool> vis(N,false);
    return hamiltonianPathAndCycle(src,src,0,vis,"");
}

//DFS of hasPath Type -> src se saare connected nodes visit kr aata hai bs.
void dfs(int src,vector<bool>& vis,vector<int>& ans)
{
    vis[src] = true;
    for(Edge e : graph[src])
    {
        if(!vis[e.v])
            dfs(e.v,vis,ans);
    }
    ans.push_back(src);
}

//Get Connected Components
int gcc()
{
    vector<bool> vis(N,false);
    vector<vector<int>> res;
    int components = 0;

    for(int i = 0; i < N; i++)
    {
        if(!vis[i])
        {
            vector<int>ans;
            dfs(vis[i],vis,ans); // jitni baar dfs chlega utne no of components honge.
            components++;
            res.push_back(ans);
        }
    }
    return components;
}

//BFS techniques =================================================================================

//Simpler for detecting cycle eventhough a little less optimized
//BFS_01 should be used for cycle related problems. (No of cycles nhi bta skta hai accurately ye vaala code)
//Here pop() krte huye vis mei true mark.
void BFS_01(int src,vector<bool>& vis)
{
    queue<int> que;
    que.push(src);

    bool isCycle = false;
    int dest = 6;
    int foundAtLevel = -1;
    int level = 0;

    while(que.size() != 0)
    {
        int size = que.size();
        cout<< "Level " << level << " -> ";
        while(size-- > 0)
        {
            int rvtx = que.front();
            que.pop();

            cout<< rvtx << " ";
            
            if(vis[rvtx])
            {
                isCycle = true;
                continue;
            }

            if(foundAtLevel == -1 && rvtx == dest)
            foundAtLevel = level;
            
            vis[rvtx] = true; // pop krte huye mark
            for(Edge e : graph[rvtx])
            {
                if(!(vis[e.v]))
                que.push(e.v);
            }
        }
        level++;
        cout << endl;
    }

    cout<< dest << " found At : " << foundAtLevel << endl;
    cout<< "IsCycle : " << (boolalpha) << isCycle << endl;
}

//Better than BFS_01 but this method is complex for detecting cycle.
//BFS_02 should be used for all general purposes except for problems revovling around cycle detection.
//Here que mei push krte huye vtx ko vis mei true mark.
void BFS_02(int src,vector<bool>& vis)
{
    queue<int> que;
    que.push(src);
    vis[src] = true; // que mei push krte hi mark krna hai.

    int dest = 6;
    int atLevel = -1;
    
    int level = 0;
    while(que.size() != 0)
    {
        int size = que.size();
        while(size-- > 0)
        {
            int rvtx = que.front();
            que.pop();

            for(Edge e : graph[rvtx])
            {
                if(!vis[e.v])
                {
                    vis[e.v] = true; // push krte huye mark
                    que.push(e.v);
                }
            }

            if(e.v == dest)
            atLevel = level+1;
        }
        level++;
    }
    cout << dest << "Present At : " << atLevel << endl;
}

//when we have to print the path of the shortest path as well -> use Parent array with BFS_02.
void BFS_printShortestPath(int src,vector<bool>& vis)
{
    queue<int> que;
    que.push(src);
    vis[src] = true;

    int dest = 6;
    int atLevel = -1;
    int level = 0;
    vector<int> par(N,-1);

    while(que.size() != 0)
    {
        int size = que.size();
        while(size-- > 0)
        {
            int rvtx = que.front();
            que.pop();

            for(Edge e : graph[rvtx])
            {
                if(!vis[e.v])
                {
                    vis[e.v] = true;
                    que.push(e.v);
                    par[e.v] = rvtx;  // rvtx ne e.v ko push kia que mie to rvtx parent hai e.v ka.
                }
                if(atLevel == -1 && e.v == dest) // to ensure first updation.
                atLevel = level+1;
            }
        }
        level++;
    }
    cout << dest << "Present At : " << atLevel << endl;

    int idx = dest;
    while(idx != -1)
    {
        cout << idx << " -> ";
        idx = par[idx];
    }
}

//Get connected components through BFS
void BFS_GCC()
{
    vector<bool> vis(N,false);
    int components = 0;
    for(int i = 0; i < N; i++)
    {
        if(!vis[i])
        {   
            components++;
            BFS_02(i,vis);
        }
    }
}

void constructGraph()
{
    addEdge(0,1,10);
    addEdge(0,3,10);
    addEdge(1,2,10);
    addEdge(2,3,40);
    addEdge(3,4,2);
    addEdge(4,5,2);
    addEdge(4,6,8);
    addEdge(5,6,3);

    // addEdge(0,6,3);
    addEdge(7,2,3);
    addEdge(6,2,3);
    addEdge(6,7,3);

}

int main()
{
    constructGraph();
    // removeEdge(3,4);
    // removeVtx(4);
    vector<bool> vis(N,false);
    // cout<<boolalpha << hasPath(0,6,vis) <<endl;
    // cout<<printAllPaths(0,6,vis,"") <<endl;
    // heavyPair ans = heaviestPath(0,6,vis);
    // cout<< ans.path << " -> " << ans.w <<endl;
    // cout << hamiltonianPathAndCycle(0) << endl;

    BFS_01(0,vis);


    // display();

    return 0;
}