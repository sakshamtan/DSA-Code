import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

public class prims{

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

public static void display(int N,ArrayList<Edge>[] graph)
{
    for(int u = 0;u < N;u++)
    {
        System.out.print(u + " -> ");
        for(Edge e : graph[u])
        {
            System.out.print(" ( " + e.v + " , " + e.w + " ) ");
        }
        System.out.println();
    }
}

public static class primsPair{
    int vtx = 0;
    int par = 0;
    int wt = 0;

    primsPair(int vtx,int par,int wt)
    {
        this.vtx = vtx;
        this.par = par;
        this.wt = wt;
    }
}

//Simplest prims (mostly yahi use krna hai eventhough less optimized)
public static void primsAlgo_01(int src,int N,boolean[] vis,ArrayList<Edge>[] graph)
{
    ArrayList<Edge>[] MST = new ArrayList[N]; // newly created MST jisme addEdge call krenge.
    for(int i = 0; i < N; i++)
    MST[i] = new ArrayList<>();

    PriorityQueue<primsPair> que = new PriorityQueue<>((a,b) -> { // wt ke basis pr sorting
        return a.wt - b.wt;
    });
    
    que.add(new primsPair(src,-1,0));  // -1 par and wt = 0 initial vtx ki.
    int numOfEdges = 0;

    //while(que.size() != 0) // sbse generic hai and har type of graph mei kaam krega bs last mei thoda extra chlega ye loop even after creating MST.
    //we can also use while(numOfEdges < N - 1 && que.size() != 0) ye connected disconnected saare graph pr chlega.
    while(numOfEdges < N - 1) // ye vaali condition MST bnte hi while loop rok deti hai so most optimized loop but ye loop disconnected graph ya componenents vaale graph mei dikkat dega.
    {
        primsPair p = que.remove();

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
            que.add(new primsPair(e.v,p.vtx,e.w));     
        }
    }
    display(N,MST);
}

//More Optimized then primsAlgo_01 isme hum ek dis and par array saath rkh lenge same algo mei to avoid inserting some duplicate vertices into Que.
public static void primsAlgo_02(int src,int N,ArrayList<Edge>[] graph,boolean[] vis,int[] dis,int[] par)
{
    ArrayList<Edge>[] MST = new ArrayList[N];
    for(int i = 0; i < N; i++)
    MST[i] = new ArrayList<>();

    PriorityQueue<primsPair> que = new PriorityQueue<>((a,b) -> {
        return a.wt - b.wt;
    });

    que.add(new primsPair(src,-1,0));

    int numOfEdges = 0;
    while(numOfEdges < N - 1)
    {
        primsPair p = que.remove();

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
            if(!vis[e.v] && e.w < dis[e.v])
            {
                dis[e.v] = e.w;  // agr aaya hua wt previously stored wt se kam hai tbhi que mei add bhi krenge
                par[e.v] = p.vtx;// aur dis and par array mei update bhi krenge.

                que.add(new primsPair(e.v,p.vtx,e.w));
            }
        }
    }
    display(N,MST);
}

public static void prims(int N,ArrayList<Edge>[] graph)
{
    boolean[] vis = new boolean[N];
    int[] dis = new int[N];
    int[] par = new int[N];

    Arrays.fill(dis,(int)1e9); // initially sb vertices ke weight infinity and parents -1.
    Arrays.fill(par,-1);

    for(int i = 0; i < N; i++)  // for disconnected graph.
    {
        if(!vis[i])
        primsAlgo_02(i,N,vis,dis,par,graph);
    }
}

public static void main(String[] args)
{
    int N = 9;
    ArrayList<Edge>[] graph = new ArrayList[N];
    prims(N,graph);
}
}
