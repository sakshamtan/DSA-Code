import java.util.ArrayList;
import java.util.Arrays;

public class Kruskal{

    public static class Edge{
        int v = 0;
        int w = 0;

        Edge(int v,int w)
        {
            this.v = v;
            this.w = w;
        }
    }

public static void addEdge(ArrayList<Edge>[] graph,int u,int v,int w)
{
    graph[u].add(new Edge(v,w));
    graph[v].add(new Edge(u,w));
}

public static void display(int N,ArrayList<Edge>[] graph) // displays formed MST
{
    for(int i = 0; i < N; i++)
    {
        System.out.print(i + " -> ");
        for(Edge e : graph[i])
        {
            System.out.print(" ( " + to_string(e.v) + " , " + to_string(e.w) + " ) ");
        }
        System.out.println();
    }
}

int[] par;
int[] size;

public static int findPar(int u)
{
    return par[u] == u ? u : par[u] = findPar(par[u]);
}

public static void merge(int p1,int p2)
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

public static void kruskal(int N,int[][] Edges)  // union find + sorted Edges vector in terms of weight = Kruskal Algorithm.
{
    ArrayList<Edge>[] graph = new ArrayList[N];
    for(int i = 0; i < N; i++)
    graph[i] = new ArrayList<>();

    par = new int[N];
    size = new int[N];
    for(int i = 0; i < N; i++)
    {
        par[i] = i;
        size[i] = 1;
    }

    for(int[] egde : Edges)
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
    int [][] Edges = {{0,1,4},{0,7,8},{1,2,8},{2,3,7},{3,4,9},{5,4,10},{6,5,2},{7,6,1},{7,8,7},{6,8,6},{1,7,11},{2,8,2}};

    Arrays.sort(Edges,(a,b) -> {
        return a[2] - b[2];  // this - other for default behaviour.
    });

    kruskal(N,Edges);
}

public static void main(String[] args)
{
    solve();
}
}

