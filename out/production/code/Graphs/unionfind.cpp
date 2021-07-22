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

//To add Edge in the spanning tree.(jonsa Edge add krne pr cycle nhi milegi vohi add krenge).
void addEgde(vector<vector<Edge>>& graph,int u,int v,int w)
{
    graph[u].push_back(Edge(v,w));
    graph[v].push_back(Edge(u,w));  // Bi-directional graph.
}

// to display the spanning tree constructed from union find.
void display(int N,vector<vector<Edge>>& graph)
{
    for(int i = 0; i < N; i++)
    {
        cout << to_string(i) + " -> ";
        for(Edge e : graph[i])
        {
            cout << " ( " to_string(e.v) + " , " + to_string(e.w) + " ) ";
        }
        cout << endl;
    } 
}

vector<int> par;   // parent and size arrays.
vector<int> size;

//With Path Compression and size : alpha(n) <= 4  inverse ackermann function
//Without path compression but with size : O(logn)

//Finding parent and compressing path also recursively.
int findPar(int u)
{
    return par[u] == u ? u : par[u] = findPar(par[u]);  
}

void merge(int p1,int p2)
{
    if(size[p1] > size[p2])
    {
        par[p2] = p1;     // p2 ko final parent bna dia.
        size[p1] += size[p2];
    }
    else{
        par[p1] = p2;
        size[p2] += size[p1];
    }
}
//Input -> vector<vector<int>> Edges -> {{u,v,w}....}}
void unionfind(int N,vector<vector<int>>& Edges)
{
    vector<vector<Edge>> graph(N);
    
    for(int i = 0; i < N; i++)
    {
        size.push_back(1);  // initially sbke par vo khud and unke size = 1.
        par.push_back(i);
    }

    bool cycle = false;
    for(vector<int>& edge : Edges)
    {
        int u = edge[0], v = edge[1], w = edge[2];
        
        int p1 = findPar(u);
        int p2 = findPar(v);

        if(p1 != p2)
        {
            merge(p1,p2);
            addEdge(u,v,w);
        }
        else
        cycle = true;
    }

    display(N,graph);
    cout << (boolalpha) << cycle << endl;
}

void solve()
{
    int N = 9;
    vector<vector<int>> Edges{{0,1,4},{0,7,8},{1,2,8},{2,3,7},{3,4,9},{5,4,10},{6,5,2},{7,6,1},{7,8,7},{6,8,6},{1,7,11},{2,8,2}};

    unionfind(N,Edges);
}

int main()
{
    solve();
    return 0;
}
