#include <iostream>
#include <vector>

using namespace std;

class Edge{
    public:
    int v = 0;
    int w = 0;

    Edge(int v,int w)
    {
        this->v = v;
        this->w = w;
    }
};

const int N = 7;
vector<vector<Edge> graph(N);

void addEdge(int u,int v,int w)
{
    graph[u].push_back(Edge(v,w));
    graph[v].push_back(Edge(u,w));
}

void display()
{
    for(int i = 0; i < N; i++)
    {
        cout << i << " -> ";
        for(Edge e : graph[i])
        {
            cout <<" ( " << e.v << " , " << e.w << " ) ";
        }
        cout << endl;
    }
}

int findEdge(int u,int v)
{
    int idx = -1;
    for(int i = 0; i < graph[u].size();i++)
    {
        if(graph[u][i].v == v)
        {
            idx = i;
            break;
        }
    }
    return idx;
}

void removeEdge(int u,int v)
{
    int idx1 = findEdge(u,v);
    int idx2 = findEdge(v,u);

    graph[u].erase(graph[u].begin() + idx1);
    graph[v].erase(graph[v].begin() + idx2);
}

void removeVtx(int u)
{
    for(int i = graph[u].size()-1; i >= 0; i--)
    {
        int v = graph[u][i].v;
        removeEdge(u,v);
    }
}

bool hasPath(int src,int dest,vector<bool>& vis)
{
    if(src == dest)
    return true;
    vis[src] = true;
    bool res = false;
    for(Edge e : graph[src])
    {
        if(!vis[e.v])
        res = res || hasPath(e.v,dest,vis);
    }
    return res;
}

int printAllPaths(int src,int dest,string psf,vector<bool>& vis)
{
    if(src == dest)
    {
        cout << psf << dest << endl;
        return 1;
    }
    int count = 0;
    vis[src] = true;
    for(Edge e : graph[src])
    {
        if(!vis[e.v])
        count += printAllPaths(e.v,dest,psf + to_string(src),vis);
    }
    vis[src] = false;
    return count;
}

class heavyPair{
    int w = 0;
    string psf = "";
    heavyPath(int w,string psf)
    {
        this->w = w;
        this->psf = psf;
    }
};

heavyPair heaviestPath(int src,int dest,vector<bool>& vis)
{
    if(src == dest)
    {
        heavyPair base(0,to_string(dest));
        return base;
    }
    vis[src] = true;
    heavyPair myAns(-1e8,"");
    for(Edge e : graph[src])
    {
        if(!vis[e.v])
        {
            heavyPair recAns = heaviestPath(e.v,dest,vis);
            if(recAns.w != 1e8 && recAns.w + e.w > myAns.w)
            {
                myAns.w = recAns.w + e.w;
                myAns.psf = to_string(src) + recAns.psf; 
            }
        }
    }
    vis[src] = false;
    return myAns;
}

int hamiltonianPathAndCycle(int src,int osrc,int edges,vector<bool>& vis,string psf)
{
    if(edges == N-1)
    {
        int idx = findEdge(src,osrc)
        if(idx == -1)
        cout << "path : " << psf + to_string(src) << endl;
        else 
        cout << "Cycle : " << psf + to_string(src) << endl;
        return 1; 
    }
    int count = 0;
    vis[src] = true;
    for(Edge e : graph[src])
    {
        if(!vis[e.v])
        count += hamiltonianPathAndCycle(e.v,osrc,edges+1,vis,psf + to_string(src));
    }
    vis[src] = false;
    return count;
}

void hamitonionPathAndCycle(int src)
{
    vector<bool> vis(N,false);
    cout << hamiltonianPathAndCycle(src,src,0,vis,"") << endl;
}

void printShortestPath_BFS(int src,vector<bool>& vis)
{
    queue<int> que;
    que.push(src);
    vis[src] = true;

    int dest = 6;
    int atLevel = -1;
    vector<int> par(N,-1);
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
                    vis[e.v] = true;
                    par[e.v] = rvtx;
                    que.push(e.v); 
                }
                if(atLevel == -1 && e.v == dest)
                    atLevel = level;

            }

        }
        level++;
    }

    int idx = dest;
    while(idx != -1)
    {
        cout << idx << " ";
        idx = par[idx];
    }
}

void kahsnAlgo()
{
    queue<int> que;
    vector<int> indegree(N,0);
    vector<int> ans;

    for(int i = 0; i < N; i++)
    {
        for(Edge e : graph[i])
        indegree[e.v]++;
    }

    for(int i = 0; i < N;i++)
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
    for(int ele : ans)
    {
        cout << ele << " ";
    }
}

void constructGraph()
{
    addEdge(0,1,10);
    addEdge(0,3,10);
    addEdge(1,2,10);
    addEdge(2,3,10);
    addEdge(3,4,10);
    addEdge(4,5,10);
    addEdge(4,6,10);
    addEdge(5,6,10);
}