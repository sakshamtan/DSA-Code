import java.util.ArrayList;
import java.util.Arrays;

public class ArticulationPB{

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

// ==============================================================================

static int[] dis, low, AP; // dis -> discoveryTime ; AP -> count of AP's w.r.t to a vtx ; low -> low time
static boolean[] vis, isAP;
static int time = 0, noOfCallsFromRoot = 0; // to check if root is an AP or not as Algo hamesha hi root ko AP btaaega.

public static void dfs(int src,int par,int N,ArrayList<Integer>[] graph) // wt ki zrurat nhi hai graph mei
{
    dis[src] = low[src] = time++; // initially discTime = lowTime.
    vis[src] = true;

    for(Integer nbr : graph[src])
    {
        if(!vis[nbr])
        {
            if(par == -1)
            noOfCallsFromRoot++; // to count recursive calls from root.

            dfs(nbr,src,N,graph);

            if(dis[src] <= low[nbr]) // agr apna discTime chotta hai Backtrack krte huye nbr ke low se to vo AP hai.
            {
                AP[src]++;
                isAP[src] = true;
            }

            low[src] = Math.min(low[src],low[nbr]); // agr nbr ka low chotta hai to uske low se apne low ko update.
        }
        else if(nbr != par)
        {
            low[src] = Math.min(low[src],dis[nbr]);
            // Agr par ke alaava koi nbr pehle se vis hai to uska disTime chotta raha hoga apne se to us chotte discTime se apne low time ko update.
        }
    }
}

public static void ArticulationPointsAndBridges(int N,ArrayList<Integer>[] graph)
{
    low = new int[N];
    dis = new int[N];
    vis = new boolean[N];

    AP = new int[N];
    isAP = new boolean[N];

    int components = 0;
    for(int i = 0; i < N; i++) // Agr graph already components mei divided ho to gcc type call
    {
        if(!vis[i])
        {
            dfs(i,-1,N,graph);
            if(noOfCallsFromRoot == 1) // means root AP nhi hai but ye algo hamesha root ko AP btata hai
            {
                isAP[i] = false;
                AP[i] = 0;
            }
            noOfCallsFromRoot = 0; // as static hai
            components++;
        }
    }

    //Result 
    int countOfAP = 0;
    for(int i = 0; i < N; i++)
    {
        if(isAP[i])
        {
            countOfAP++;
            System.out.println("AP : " + i + " @ " + "Increase in No of components : " + AP[i]);
        }
    }
}
}