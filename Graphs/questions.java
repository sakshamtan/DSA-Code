import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;

public class questions{

//Leetcode 1168 -> Optimize water supply to a village
int[] par;
int findPar(int u)
{
    return par[u] == u ? u : par[u] = findPar(par[u]);
}

public int minCostToSupplyWater_(int N,ArrayList<int[]> Edges)
{
    par = new int[N+1];
    for(int i = 0; i < N; i++)
    {
        par[i] = i;
    }

    int minCost = 0;
    for(int[] egde : Edges)
    {
        int u = edge[0];
        int v = edge[1];
        int w = egde[2];

        int p1 = findPar(u);
        int p2 = findPar(v);

        if(p1 != p2)
        {
            par[p1] = p2;
            minCost += w;
        }
    }
    return minCost;
}

//Leetcode function
public int minCostToSupplyWater(int n,int[] wells,int[][] pipes)
{
 // 2D arr given tha to iska size change nhi kr skte to ek naya AL of Arr bnaanege isme saare edges(wells and pipes) vaale daalenge and ispr Kruskal call krenge.
    ArrayList<int[]> PIPES = new ArrayList<>();
    for(int i = 0; i < n; i++)
    {
        PIPES.add(new int[] {0, i+1, wells[i]});
    }

    for(int[] p : pipes)
    {
        PIPES.add(p);
    }

    Collections.sort(PIPES,(a,b)->{
        return a[2] - b[2];
    });

    return minCostToSupplyWater_(n,PIPES);
}

//Leetcode 743 -> Network Delay Time
public class Edge{
    int v = 0;
    int w = 0;
        
    Edge(int v,int w)
    {
        this.v = v;
        this.w = w;
    }
}
public class dijikstraPair{
    int vtx = 0;
    int wsf = 0;
        
    dijikstraPair(int vtx,int wsf)
    {
        this.vtx = vtx;
        this.wsf = wsf;
    }
}

//Leetcode function
public int networkDelayTime(int[][] times, int n, int k) 
{
    ArrayList<Edge>[] graph = new ArrayList[n+1]; // input edges ke terms mei hai so creating graph to apply dijikstra
    for(int i = 0; i <= n; i++)
        graph[i] = new ArrayList<>();

    for(int[] ar : times)
        {
            graph[ar[0]].add(new Edge(ar[1],ar[2]));
        }
        
    int[] dis = new int[n+1];
    Arrays.fill(dis,(int)1e9);
    boolean[] vis = new boolean[n+1];
        
    PriorityQueue<dijikstraPair> que = new PriorityQueue<>((a,b) -> {
        return a.wsf - b.wsf;
    });
        
    que.add(new dijikstraPair(k,0));
    dis[k] = 0;
    int noOfEdges = 0;
    int maxValue = 0;

    while(que.size() != 0)
    {
        dijikstraPair p = que.remove();
            
        if(vis[p.vtx])
            continue;
            
        if(p.vtx != k)
            noOfEdges++;

        maxValue = Math.max(maxValue,p.wsf);

        vis[p.vtx] = true;
        for(Edge e : graph[p.vtx])
        {
            if(!vis[e.v] && e.w + p.wsf < dis[e.v])
            {
                dis[e.v] = e.w + p.wsf;
                que.add(new dijikstraPair(e.v,e.w + p.wsf));
            }
        }
            
    }

    if(noOfEdges != n-1) // Agr poora graph traverse nhi ho skta to -1 hai ans
        return -1;
        
    return maxValue;
}

//Leetcode 787 -> Cheapest Flights within K stops using Bellman ford -> isme bellman ford lgaana better pdh gya dijikstra se.
public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) 
{
    int[] dis = new int[n];
    Arrays.fill(dis,(int)1e8);
    dis[src] = 0;
        
    for(int EgdeCount = 1; EgdeCount <= k + 1; EgdeCount++) // k+1 edges tk chlaana hai bs(k+1 out of bound ja skta hai aise questions mei to contraints check krne hai)
    {
        int[] ndis = new int[n];
        for(int i = 0; i < n; i++)
            ndis[i] = dis[i];
            
        for(int[] e : flights)
        {
            int u = e[0], v = e[1], w = e[2];
                
            if(dis[u] != (int)1e8 && dis[u] + w < ndis[v])
                ndis[v] = dis[u] + w;

        }
        dis = ndis;
    }
    return dis[dst] == (int)1e8 ? -1 : dis[dst];
}

//Leetcode 924 -> Minimize Malware Spread
int[] par,size;
public int findPar(int u)
{
    return par[u] == u ? u : (par[u] = findPar(par[u]));
}

//Leetcode function
public int minMalwareSpread(int[][] graph, int[] initial) 
{
    Arrays.sort(initial);  // sbse chotta index chahiye if multiple nodes are to be removed so sort krdia.
    int n = graph.length;
        
    size = new int[n];
    par = new int[n];
    for(int i = 0; i < n; i++)
    {
        par[i] = i;
        size[i] = 1;
    }
        
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < n; j++)
        {
            if(i != j && graph[i][j] == 1)
            {
                int p1 = findPar(i); // connected components ki grouping krdi (component == city)
                int p2 = findPar(j);
                    
                if(p1 != p2)
                {
                    par[p2] = p1;
                    size[p1] += size[p2];
                }
            }
        }
    }
        
    int[] noOfNodesInfectedInCity = new int[n]; // har city mei kitne infected nodes hai unka count us city ke parent pr pdha hoga
    for(int i : initial)
    {
        int leaderOfCity = findPar(i);
        noOfNodesInfectedInCity[leaderOfCity]++;
    }
        
    int maxPopulatedCity = 0;
    int ans = initial[0]; // ans by default sbse chotte vaala index if multiple nodes remove krne hai.
    for(int i : initial)
    {
        if(noOfNodesInfectedInCity[findPar(i)] == 1 && size[findPar(i)] > maxPopulatedCity) //Agr city mei ek hi node infected hai and uska size bda hai pichle vaali cities se to updation of ans.
        {
            maxPopulatedCity = size[findPar(i)]; // Ek hi infected person ho and us city ki popultion bhi sbse bdi hai to us ek node ko remove krne se sbse zyaada minimize hoga spread.
            ans = i;
        }
            
    }            
    return ans;
}

// Leetcode 1298 -> Maximum Candies you can get from Boxes -> Using BFS 
public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
    int n = status.length;
    boolean[] canOpen = new boolean[n];
    boolean[] hasBox = new boolean[n];
    boolean[] used = new boolean[n];

    for(int i = 0; i < n; i++) {
        canOpen[i] = (status[i] == 1);
    }

    LinkedList<Integer> que = new LinkedList<>();
    for(int initalBox : initialBoxes) {
        hasBox[initalBox] = true;
        if(canOpen[initalBox]) {
            used[initalBox] = true;
            que.addLast(initalBox);
        }
    }

    int totalCandies = 0;

    while(que.size() != 0) {
        int size = que.size();
        while(size-- > 0) {
            Integer vtx = que.removeFirst();
            totalCandies += candies[vtx];

            for(int avlKey : keys[vtx]) {
                canOpen[avlKey] = true;
                if(!used[avlKey] && hasBox[avlKey]) {
                    que.addLast(avlKey);
                    used[avlKey] = true;
                }
            }

            for(int avlBox : containedBoxes[vtx]) {
                hasBox[avlBox] = true;
                if(!used[avlBox] && canOpen[avlBox]) {
                    que.addLast(avlBox);
                    used[avlBox] = true;
                }
            }
        }
    }
    return totalCandies;
}
}