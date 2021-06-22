#include <iostream>
#include <vector>
#include <algorithm>

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

void addEdge(vector<vector<Edge>>& graph,int u,int v,int w)
{
    graph[u].push_back(Edge(v,w));
    graph[v].push_back(Edge(u,w));
}

void display(int N,vector<vector<Edge>>& graph) // displays formed MST
{
    for(int i = 0; i < N; i++)
    {
        cout << i << " -> ";
        for(Edge e : graph[i])
        {
            cout << " ( " + to_string(e.v) + " , " + to_string(e.w) + " ) ";
        }
        cout << endl;
    }
}

vector<int> par;
vector<int> size;

int findPar(int u)
{
    return par[u] == u ? u : par[u] = findPar(par[u]);
}

void merge(int p1,int p2)
{
    if(size[p1] > size[p2])
    {
        par[p2] = p1;
        size[p1] += size[p2];
    }
    else{
        par[p1] = p2;
        size[p2] += size[p1];
    }
}

void kruskal(int N,vector<vector<int>>& Edges)  // union find + sorted Edges vector in terms of weight = Kruskal Algorithm.
{
    vector<vector<Edge>> graph(N);

    for(int i = 0; i < N; i++)
    {
        par.push_back(i);
        size.push_back(1);
    }

    for(vector<int>& edge : Edges)
    {
        int u = edge[0], v = edge[1], w = edge[2];

        int p1 = findPar(u);
        int p2 = findPar(v);

        if(p1 != p2)
        {
            merge(p1,p2);
            addEgde(graph,u,v,w);
        }
    }
    display(N,graph);
}

void solve()
{
    int N = 9;
    vector<vector<int>> Edges{{0,1,4},{0,7,8},{1,2,8},{2,3,7},{3,4,9},{5,4,10},{6,5,2},{7,6,1},{7,8,7},{6,8,6},{1,7,11},{2,8,2}};

    sort(Edges.begin(), Edges.end(), [](vector<int>& a,vector<int>& b) { // tempelate function.
        return a[2] < b[2]; // this - other for default behaviour.
    });

    kruskal(N,Edges);
}

int main()
{
    solve();
    return 0;
}