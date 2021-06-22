# include <iostream>
# include <vector>
# include <queue>
#include <priority_queue>

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

void addEdge(vector<vector<Egde>>& graph,int u,int v,int w)
{
    graph[u].push_back(Edge(v,w));
    graph[v].push_back(Edge(u,w));  // for bi-directional graph
}

void display(int N,vector<vector<Edge>>& graph)
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

class primsPair{
    int vtx = 0;
    int par = 0;
    int wt = 0;

    primsPair(int vtx,int par,int wt)
    {
        this->vtx = vtx;
        this->par = par;
        this->wt = wt;
    }
};

struct comp  // cpp mei PQ ke liye comparator likhna pdhta hai lambda function nhi chlta.
{
    bool operator()(const primsPair &a,primsPair &b)
    {
        return a.wt > b.wt;
    }
};

void primsAlgo_01(int src,int N,vector<bool>& vis,vector<vector<Egde>>& graph)
{
    vector<vector<Egde>> MST(N);

    priority_queue<primsPair, vector<primsPair>, comp> que;  // MaxPQ hoti hai default in cpp so using greater function to convert it into MinPQ.
    que.push(primsPair(src,-1,0));
    int numOfEdges = 0;

    while(numOfEdges < N - 1)
    {
        primsPair p = que.top();
        que.pop();

        if(vis[p.vtx])
        continue;

        if(p.par != -1)
        {
            addEdge(MST,p.vtx,p.par,p.wt);
            numOfEdges++;
        }

        vis[p.vtx] = true;
        for(Edge e : graph[p.vtx])
        {
            if(!vis[e.v])
            que.push(primsPair(e.v,p.vtx,e.w));
        }
    }
    display(N,MST);
}

int main()
{
    return 0;
}
