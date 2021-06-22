import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

public class dijikstra{

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

public static class dijikstraPair{
    int vtx = 0;
    int wsf = 0; // weight so far.

    //if we want to create graph we will keep par and wt also
    //int par = 0;
    //int wt = 0;

    dijikstraPair(int vtx,int wsf)
    {
        this.vtx = vtx;
        this.wsf = wsf;
    }
}

//Basic Algo less optimized as not using par and dis array.
public static void dijikstraAlgo_01(int src,int N,ArrayList<Edge>[] graph)
{
    PriorityQueue<dijikstraPair> que = new PriorityQueue<>((a,b) -> {
        return a.wsf - b.wsf;
    });

    que.add(new dijikstraPair(src,0));
    boolean[] vis = new boolean[N];

    int numOfEdges = 0;
    while(numOfEdges < N - 1)
    {
        dijikstraPair p = que.remove();

        if(vis[p.vtx])
        continue;

        if(p.vtx != src) // src jab aata hai to as an edge nhi aata(par == -1 hota hai)
        numOfEdges++;  // to stop the loop as soon as N - 1 edges are processed / created.

        vis[p.vtx] = true;
        for(Edge e : graph[p.vtx])
        {
            if(!vis[e.v])
            que.add(new dijikstraPair(e.v, p.wsf + e.w));
        }
    } 
}

// Better as using parent and dis array along with dijikAlgo_01
public static void dijikstraAlgo_02(int src,int N,ArrayList<Edge>[] graph)
{
    PriorityQueue<dijikstraPair> que = new PriorityQueue<>((a,b) -> {
        return a.wsf - b.wsf;
    });

    que.add(new dijikstraPair(src,0));

    boolean[] vis = new boolean[N];
    int[] dis = new int[N];
    int[] par = new int[N];

    Arrays.fill(dis,(int)1e8);
    Arrays.fill(par,-1);

    while(numOfEdges < N - 1)
    {
        dijikstraPair p = que.remove();

        if(vis[p.vtx])
        continue;

        if(p.vtx != src)
        numOfEdges++;

        vis[p.vtx] = true;
        for(Edge e : graph[p.vtx])
        {
            if(!vis[e.v] &&  p.wsf + e.w < dis[e.v])
            {
                dis[e.v] = p.wsf + e.w;
                par[e.v] = p.vtx;

                que.add(new dijikstraPair(e.v,p.wsf + e.w));
            }
        }
    }
}

//Bellmann Ford algorithm
public static void BellmannFord(int src,int N,int[][]edges)
{
    int[]dis = new int[N]; //previous column array
    Arrays.fill(dis,(int)1e9);

    dis[src] = 0;
    boolean isNegativeCyclePresent = false;
    for(int EdgeCount = 1; EdgeCount <= N; EdgeCount++)
    {
        int[] ndis = new int[N]; // current column array
        for(int i = 0; i < N; i++)
        ndis[i] = dis[i]; // curr column array mei previous arr ki saari values copied

        for(int[] e : edges)
        {
            int u = e[0], v = e[1], w = e[2];

            if(dis[u] != (int)1e9 && dis[u] + w < ndis[v])
            {
                if(EdgeCount == N)
                {
                    isNegativeCyclePresent = true;
                    break;
                }

                ndis[v] = dis[u] + w; // updation in current column array.
            } 
        }
        dis = ndis; // moving our arrays forward(ndis har baar new ho rha hai loop mei)
    }
    //Our required ans will be stored in dis array.
}
}


