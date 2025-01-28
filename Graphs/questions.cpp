#include <iostream>
#include <vector>
#include <unordered_map>
#include <unordered_set>

using namespae std;

//Leetcode 200 -> Number of Islands
void dfsIslands(int i,int j,int n,int m,vector<vector<char>>& grid,vector<vector<int>>& dir)
{
    grid[i][j] = '0'; // same as marking vis[src] = true.

    for(int d = 0; d < dir.size();d++)
    {
        int r = i + dir[d][0];
        int c = j + dir[d][1];

        if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == '1')
        {
            dfsIslands(r,c,n,m,grid,dir);
        }
    }
}

//Leetcode function
int numIslands(vector<vector<char>>& grid)
{
    if(grid.size() == 0 || grid[0].size() == 0)
    return 0;

    int numOfIslands = 0;
    vector<vector<int>> dir{{-1,0},{0,1},{1,0},{0,-1}};

    int n = grid.size();
    int m = grid[0].size();
    for(int i = 0; i < n;i++)
    {
        for(int j = 0;j < m;j++)
        {   
            if(grid[i][j] == '1')
            {
                numOfIslands++;
                dfsIslands(i,j,n,m,grid,dir);
            }
        }
    }
    return numOfIslands;
}

//Leetcode 695 -> max Area of Island -> No of 1's in a component = area of that component/island
int dfsArea(int i,int j,int n,int m,vector<vector<int>>& grid,vector<vector<int>>& dir)
{
    grid[i][j] = 0;
    int area = 1;
    for(int d = 0; d < dir.size(); d++)
    {
        int r = i + dir[d][0];
        int c = j + dir[d][1];
        if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1)
        area += dfsArea(r,c,n,m,grid,dir);
    }
    return area;
}

//Leetcode function
int maxAreaOfIsland(vector<vector<int>>& grid)
{
    if(grid.size() == 0 || grid[0].size() == 0)
    return 0;

    int maxArea = 0;
    vector<vector<int>> dir{{0,1},{0,-1},{1,0},{-1,0}};
    int n = grid.size();
    int m = grid[0].size();

    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < m; j++)
        {
            if(grid[i][j] == 1)
            maxArea = max(maxArea,dfsArea(i,j,n,m,grid,dir));
        }
    }
    return maxArea;
}

// Leetcode 2658 -> Maximum Number of Fish in a Grid
int dfsFish(int i,int j,int n,int m,vector<vector<int>>& grid,vector<vector<int>>& dir)
{
    int fish = grid[i][j];
    grid[i][j] = 0;
    for(int d = 0; d < dir.size(); d++)
    {
        int r = i + dir[d][0];
        int c = j + dir[d][1];
        if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] != 0)
        fish += dfsFish(r,c,n,m,grid,dir);
    }
    return fish;
}

// Leetcode function
int findMaxFish(vector<vector<int>>& grid) {
    if(grid.size() == 0 || grid[0].size() == 0)
    return 0;

    int maxFish = 0;
    vector<vector<int>> dir{{0,1},{0,-1},{1,0},{-1,0}};
    int n = grid.size();
    int m = grid[0].size();

    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < m; j++)
        {
            if(grid[i][j] != 0)
            maxFish = max(maxFish,dfsFish(i,j,n,m,grid,dir));
        }
    }
    return maxFish;
}

//Leetcode 463 -> Island Perimeter
//Peri = 4 * (totoal no of 1's) - (total no of neibhours)
int islandPerimeter(vector<vector<int>>& grid) 
{
    if(grid.size() == 0 || grid[0].size() == 0)
    return 0;

    vector<vector<int>> dir{{0,1},{0,-1},{1,0},{-1,0}};

    int n = grid.size();
    int m = grid[0].size();
    
    int nbrs = 0 , count = 0;
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < m; j++)
        {
            if(grid[i][j] == 1)
            {
                count++;
                for(int d = 0; d < dir.size(); d++)
                {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];

                    if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1)
                    nbrs++;
                }
            }
        }
    }
    return 4 * count - nbrs;
}

//Leetcode 130 -> Surrounded Regions -> Jonse 'O's 'X' se surrounded hai sirf unhe 'X' mei convert krna hai board mei.
// Ye function saare border se connected(Non surrounded by 'X') vaale 'O's ko '$' mei convert kr aayega to differentiate between surrounded 'O's and non surrounded 'O's
void dfsSurroundedRegion(int i,int j,int n,int m,vector<vector<char>>& board,vector<vector<int>>& dir)
{
    board[i][j] = '$';
    for(int d = 0; d < dir.size(); d++)
    {
        int r = i + dir[d][0];
        int c = j + dir[d][1];

        if(r >= 0 && c >= 0 && r < n && c < m && board[r][c] == 'O')
        dfsSurroundedRegion(r,c,n,m,board,dir);
    }
}

//Leetcode function
void solve(vector<vector<char>>& board) 
{
    if(board.size() == 0 || board[0].size() == 0)
    return;

    int n = board.size();
    int m = board[0].size();

    vector<vector<int>> dir{{0,-1},{0,1},{1,0},{-1,0}};

    for(int i = 0; i < n; i++)
    {
        if(board[i][0] == 'O')
        dfsSurroundedRegion(i,0,n,m,board,dir);
        if(board[i][m-1] == 'O')
        dfsSurroundedRegion(i,m-1,n,m,board,dir);
    }

    for(int i = 0; i < m; i++) //Border pr traverse kr rhe hai to find any 'O' so that we can call dfs on that location.`
    {
        if(board[0][i] == 'O')
        dfsSurroundedRegion(0,i,n,m,board,dir);
        if(board[n-1][i] == 'O')
        dfsSurroundedRegion(n-1,i,n,m,board,dir);
    }

    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < m; j++)
        {
            if(board[i][j] == 'O')
            board[i][j] = 'X';
            else if(board[i][j] == '$')
            board[i][j] = 'O';
        }
    }
}

//Leetcode 1091 -> Shortest Path in a binary Matrix -> using BFS
int shortestPathBinaryMatrix(vector<vector<int>>& grid) 
{
    if(grid.size() == 0 || grid[0].size() == 0)
    return -1;

    int n = grid.size();
    int m = n; // in this question n = m.

    if(grid[0][0] == 1 || grid[n-1][n-1] == 1) // agr src ya dest blocked hai toh no possible path.
    return -1;

    queue<int> que;
    grid[0][0] = 1; // push krte hi true mark same as BFS_02.
    que.push(0); // (r,c) ki jagah idx(of 1D arr) put kr rhe hai idx = r * m + c
    
    vector<vector<int>> dir {{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{-1,1},{1,-1}}; // we need all 8 directions.

    int level = 1; // in terms of no. of nodes dena hai path.
    while(que.size() != 0)
    {
        int size = que.size();
        while(size-- > 0)
        {
            int idx = que.front();
            que.pop();

            int r = idx / m;  // converting idx to (r,c) 1D to 2D.
            int c = idx % m;

            if(r == n-1 && c == m-1)
            return level;

            for(int d = 0; d < dir.size(); d++) // unvisited nbrs visit krne hai to 8 directions mei visit krne hai.
            {
                int x = r + dir[d][0];
                int y = c + dir[d][1];

                if(x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 0)
                {
                    grid[x][y] = 1;
                    que.push(x * m + y); // 2D coordinates ko 1D idx mei convert krke push.
                }
            }
        }
        level++;
    }
    return -1;
}

//Leetcode 785 -> is Graph BiPartite? -> Using BFS_01 as cycle se related question hai.
// EVEN Cycle(cycle with even no. of nodes) implies graph is a bipartite one.
// ODD cycle implies graph is not a bipartite graph.
// All acyclic graphs are bipartite by default.
bool isBipartite_(vector<vector<int>>& graph,vector<int>& vis,int src)
{
    queue<int> que;
    que.push(src);
    bool isCycle = false;

    int color = 0;

    while(que.size() != 0)
    {
        int size = que.size();
        while(size-- > 0)
        {
            int rvtx = que.front();
            que.pop();

            if(vis[rvtx] != -1)
            {
                isCycle = true;
                if(vis[rvtx] != color) // agr same color aata dusri baar mtlb even cycle.
                return false;

                continue;
            }
            
            vis[rvtx] = color; // Same as BFS_01 pop krte hi mark krna vis mei.
            for(int v : graph[rvtx])
            {
                if(vis[v] == -1)
                que.push(v);
            }
        }
        color = (color + 1) % 2;
    }
    return true;
}

//Leetcode function.
bool isBipartite(vector<vector<int>>& graph) 
{
    int n = graph.size();
    
    vector<int> vis(n,-1);
    bool res = true;
    for(int i = 0; i < n; i++) // if we get a disconnected graph so here loop of GCC.
    {
        if(vis[i] == -1)
        res = res && isBipartite_(graph,vis,i); // ek bhi component bipartite nhi to poora graph bipartite nhi.
    }
    return res;
}

//Leetcode 994 -> Rotting Oranges -> uses multi-point bfs.
int orangesRotting(vector<vector<int>>& grid) 
{
    int n = grid.size();
    int m = grid[0].size();
    int time = 0, freshOranges = 0;

    queue<int> que;

    vector<vector<int>> dir {{0,1},{0,-1},{1,0},{-1,0}};

    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < m; j++)
        {
            if(grid[i][j] == 2) // All rotten oranges pushed in the que(multiple roots for bfs so multi point bfs).
            que.push(i * m + j);
            else if(grid[i][j] == 1) // No of fresh Oranges present in the matrix initially.
            freshOranges++;
        }
    }

    if(freshOranges == 0)
    return 0;

    while(que.size() != 0)
    {
        int size = que.size();
        while(size-- > 0)
        {
            int idx = que.front();
            que.pop();

            int r = idx / m;
            int c = idx % m;

            for(int d = 0; d < dir.size(); d++)
            {
                int x = r + dir[d][0];
                int y = c + dir[d][1];

                if(x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1)
                {
                    freshOranges--;
                    grid[x][y] = 2;
                    que.push(x * m + y);
                    
                    if(freshOranges == 0) // hum mark krte hi -- krte hai freshOranges but vo rot to agle level mei jaakr honge.
                    return time+1;
                }
            }
        }
        time++;
    }
    return -1;
}

//Leetcode 286 -> Walls and Gates
void wallsAndGates(vector<vector<int>> &rooms) 
{
    int n = rooms.size();
    int m = rooms[0].size();

    queue<int> que;

    vector<vector<int>> dir {{0,1},{0,-1},{1,0},{-1,0}};

    int emptyRooms = 0, distance = 0;
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < m; j++)
        {
            if(rooms[i][j] == 0)
            que.push(i * m + j);
            else if(rooms[i][j] == 2147483647)
            emptyRooms++;
        }
    }

    while(que.size() != 0)
    {
        int size = que.size();
        while(size-- > 0)
        {
            int idx = que.front();
            que.pop();

            int r = idx / m;
            int c = idx % m;

            for(int d = 0; d < dir.size(); d++)
            {
                int x = r + dir[d][0];
                int y = c + dir[d][1];

                if(x >= 0 && y >= 0 && x < n && y < m && rooms[x][y] == 2147483647)
                {
                    emptyRooms--;
                    rooms[x][y] = distance + 1; // khud se 1 distance plus krke milga next empty room.
                    que.push(x * m + y);

                    if(emptyRooms == 0)
                    return;
                }
            }
        }
        distance++;
    }
}

//Cycle in Directed graph so using kahns algo.
bool kahnsAlgo(vector<vector<int>>& graph,int N)
{
    queue<int> que;
    vector<int>ans;

    vector<int> indegree(N,0);
    for(int i = 0; i < N; i++)
    {
        for(int e : graph[i])
            indegree[e]++;
    }

    for(int i = 0; i < N;i++)
    {
        if(indegree[i] == 0)
            que.push(i);
    }

    while(que.size() != 0)
    {
        int size = que.size();
        while(size-- > 0)
        {
            int rvtx = que.front();
            que.pop();
            
            ans.push_back(rvtx);
                
            for(int e : graph[rvtx])
            {
                if(--indegree[e] == 0)
                    que.push(e);
            }
                
        }
    }
    return ans;      
}

//Leetcode 207 -> course schedule -> based on topological sort -> using kahns algo
bool canFinish(int numCourses, vector<vector<int>>& prerequisites) 
{
    vector<vector<int>> graph(numCourses); //numCourses = 'N'
    for(vector<int> arr : prerequisites)
    {
        graph[arr[0]].push_back(arr[1]); // as given prerequistes[0].length == 2(constraint).
    }
        
    return kahnsAlgo(graph,numCourses).size == N;        
}

//Leetcode 210 -> course schedule II -> using kahns algo
vector<int> findOrder(int numCourses, vector<vector<int>>& prerequisites) 
{
    vector<vector<int>> graph(numCourses);
    for(vector<int> arr : prerequisites)
    {
        graph[arr[0]].push_back(arr[1]);
    }

    vector<int> ans = kahnsAlgo(graph,numCourses);
    if(ans.size() != numCourses)
        return {};
        
    reverse(ans.begin(),ans.end());
    return ans;
}

//Leetcode 210 -> course schedule II -> using DFS(based on topological sorting)
bool isCyclePresent_DFSTopo(int src,vector<vector<int>>& graph,int N,vector<int>& ans,vector<int>& vis)
{
    vis[src] = 0;
    bool res = false;
    for(int e : graph[src])
    {
        if(vis[e] == -1)
            res = res || isCyclePresent_DFSTopo(e,graph,N,ans,vis);
        else if(vis[e] == 0)
            return true;
    }
    vis[src] = 1;
    ans.push_back(src);
    return res;
}

// Leetcode function
vector<int> findOrder(int N, vector<vector<int>>& prerequisites) 
{
    vector<vector<int>> graph(N);
    for(vector<int> arr : prerequisites)
    {
        graph[arr[0]].push_back(arr[1]);
    }
    vector<int> ans;
    vector<int> vis(N,-1);
    bool res = false;
    for(int i = 0; i < N; i++)
    {
        if(vis[i] == -1)
            res = res || isCyclePresent_DFSTopo(i,graph,N,ans,vis);
    }
    if(res)
        return {};
    return ans;
}

//Leetcode 329 -> Longest Increasing Path in a Matrix
//By using kahn's algorithm. dependency of dir graph -> chotte vtx apne se bade apne saare nbrs ko point/direct krenge.
int longestIncreasingPath(vector<vector<int>>& matrix) 
{
    int n = matrix.size();
    int m = matrix[0].size();

    vector<vector<int>> indegree(n,vector<int>(m,0)); // 2D indegree matrix maintain

    vector<vector<int>> dir{{0,-1},{0,1},{-1,0},{1,0}};
        
    for(int i = 0; i < n; i++)
        for(int j = 0; j < m; j++)
            for(int d = 0; d < dir.size();d++)
            {
                int x = i + dir[d][0];
                int y = j + dir[d][1];
                if(x >= 0 && y >= 0 && x < n && y < m && matrix[x][y] < matrix[i][j])
                    indegree[i][j]++; // filling indegree matrix as per our defined dependency
            }

    queue<int> que;

    for(int i = 0; i < n; i++)
        for(int j = 0; j < m; j++)
            if(indegree[i][j] == 0)
                que.push(i * m + j);

    int level = 0;
    while(que.size() != 0)
    {
        int size = que.size();
        while(size-- > 0)
        {
            int idx = que.front();
            que.pop();
            int r = idx / m;
            int c = idx % m;
                
            for(int d = 0; d < dir.size(); d++)
            {
                int x = r + dir[d][0];
                int y = c + dir[d][1];
                    
                if(x >= 0 && y >= 0 && x < n && y < m && matrix[x][y] > matrix[r][c] && --indegree[x][y] == 0)
                    que.push(x * m + y);
            }
        }
        level++;
    }
    return level;
}

//Leetcode 684 -> Redundant Connection
vector<int>par;
int findPar(int u)
{
    return par[u] == -1 ? u : par[u] = findPar(par[u]);
}

//Leetcode function
vector<int> findRedundantConnection(vector<vector<int>>& edges) 
{
    int N = edges.size();
    par.resize(N+1,-1);  // as 1 to N vertices given hai.
    
    for(vector<int>& edge : edges)
    {
        int p1 = findPar(edge[0]);
        int p2 = findPar(edge[1]);
            
        if(p1 != p2)
            par[p1] = p2;
        else
            return edge;
    }
    return {};
}

//Leetcode 1061 -> lexicographically smallest equivalent string(Locked)
vector<int>par;
int findPar(int u)
{
    return par[u] == u ? u : findPar(par[u]);
}

string smallestEquivalentString(string A,string B,string S)
{
    for(int i = 0; i < 26; i++)
    par.push_back(i);

    for(int i = 0; i < A.length();i++)
    {
        int p1 = findPar(A[i] - 'a');  //indexed a -> 0 b -> 1 and so on in parent array.
        int p2 = findPar(B[i] - 'a');

        if(p1 < p2)
        par[p2] = p1;
        else if(p1 > p2)
        par[p1] = p2;

        //OR
        //par[p1] = min(p1,p2);
        //par[p2] = min(p1,p2);
    }

    string ans = "";
    for(int i = 0; i < S.length(); i++)
    {
        ans += (char)(findPar(S[i] - 'a') + 'a'); // S ki saare char ki jagah unke respective parents ko replace krdia.
    }

    return ans;
}

//Leetcode 839 -> Similar String Groups
vector<int> par;
int findPar(int u)
{
    return par[u] == u ? u : par[u] = findPar(par[u]);
}

bool isSimilar(string A,string B)
{
    int count = 0;
    for(int i = 0; i < A.length(); i++)
    {
        if(A[i] != B[i] && ++count > 2) 
            return false;
    }
    return true;
}

//Leetcode function
int numSimilarGroups(vector<string>& strs) 
{
    int countofGroups = strs.size();
    int n = strs.size();
        
    for(int i = 0; i < n; i++)
        par.push_back(i);
        
    for(int i = 0; i < n; i++)
    {
        for(int j = i+1; j < n; j++) // to check for all combinations of strings to group.
        {
            if(isSimilar(strs[i],strs[j]))
            {
                int p1 = findPar(i); // strings mapped with idx of parent array -> 0th string in str arr
                int p2 = findPar(j); // mapped with 0th idx of par array and so on.
                    
                if(p1 != p2)
                {
                    par[p1] = p2; // Jitni baar merge kiya utni baar noOfgroups-- to get accurate count of groups in the end.
                    countofGroups--;
                }
            }
        }
    }
    return countofGroups;
}

//Leetcode 305 -> Number of Islands II
vector<int>par;
int findPar(int u)
{
    return par[u] == -1 ? u : par[u] = findPar(par[u]);
}

//Leetcode function
vector<int> numIslands2(int m, int n, vector<vector<int>> &postions)
{
    par.resize(m * n, -1);
    
    vector<vector<int>> grid(m,vector<int>(n,0));
    vector<int> ans;

    vector<vector<int>> dir{{0,1},{0,-1},{1,0},{-1,0}};
    int count = 0;
    for(vector<int>& pos : positions)
    {
        int i = pos[0];
        int j = pos[1];

        if(grid[i][j] != 1) // already land ho aur uspe hi addLand krne ko bol dia jaaye.
        {
            count++;
            grid[i][j] = 1;

            for(int d = 0; d < dir.size();d++)
            {
                int x = i + dir[d][0];
                int y = j + dir[d][1];

                if(x >= 0 && y >= 0 && x < m && y < n && grid[x][y] == 1)
                {
                    int p1 = findPar(i * n + j);  // apna and apne nbr ka parent find kia to merge.
                    int p2 = findPar(x * n + y);

                    if(p1 != p2)
                    {
                        count--;
                        par[p1] = p2;
                    }
                }
            }
        }
        ans.push_back(count);
    }
    return ans;
}

//Another method -> without creating v<v<I>> grid and only using parent array.
vector<int> numIslands2(int m,int n,vector<vector<int>>& positions)
{
    par.resize(m * n, -1); // initially -1 hai har jagah parent arr mei.

    vector<int> ans;

    vector<vector<int>> dir{{0,1},{0,-1},{1,0},{-1,0}};
    int count = 0;
    for(vector<int>& pos : positions)
    {
        int i = pos[0];
        int j = pos[1];

        if(par[i * n + j] == -1) 
        {
            count++;
            par[i * n + j] = i * n + j;  // khud ko khud ka parent bnaya jb ek baar aagye khud pr.

            for(int d = 0; d < dir.size(); d++)
            {
                int x = i + dir[d][0];
                int y = j + dir[d][1];

                if(x >= 0 && y >= 0 && x < m && y < n && par[x * n + y] != -1)  // mtlb nbr present hai.
                {
                    int p1 = findPar(i * n + j);
                    int p2 = findPar(x * n + y);

                    if(p1 != p2)
                    {
                        par[p1] = p2;
                        count--;
                    }
                }
            }
        }
        ans.push_back(count);
    }
    return ans;
}

//Leetcode 1168 -> Optimize water distribution in a village
vector<int> par;
int findPar(int u)
{
    return par[u] == u : u ? par[u] = findPar(par[u]); 
}

int minCostToSupplyWater_(int N,vector<vector<int>>& Edges)
{
    for(int i = 0; i < N; i++)
    par.push_back(i);

    int minCost = 0;
    for(vector<int>& arr : Edges)
    {
        int u = arr[0];
        int v = arr[1];
        int w = arr[2];

        int p1 = findPar(u);
        int p2 = findPar(v);

        if(p1 != p2)
        {
            par[p1] = p2;
            minCost += w;  // jin edges ko add kr rhe honge MST mei bs unke edges ka weight add krna hai ans mei.
        }
    }
    return minCost;
}

//Leetcode function
int minCostToSupplyWater(int n,vector<int>& wells,vector<vector<int>>& pipes)
{
    for(int i = 0; i < n; i++)
    {
        pipes.push_back({0, i+1, wells[i]}); // 0 yaani well vaale saare edge daale Edges vector mei.
    }

    //Sorting Edge vector on the basis of cost to apply Kruskal Algo.
    sort(pipes.begin(), pipes.end() [](vector<int>& a,vector<int>& b){
        return a[2] < b[2];
    });

    return minCostToSupplyWater_(n,pipes);
}

//Leetcode 200 -> Number of Islands using union find
vector<int> par;
int findPar(int u)
{
    return par[u] == -1 ? u : par[u] = findPar(par[u]);
}

//Leetcode function.
int numIslands(vector<vector<char>>& grid)
{
    int n = grid.size();
    int m = grid[0].size();

    par.resize(n * m,-1);
    int onesCount = 0;
    vector<vector<int>> dir {{0,1},{1,0}}; // sirf do directions mei check krne se hi kaam ho jaayega as hum apne neeche aur right vaale nbrs ko merge krte chl rhe hai saath saath.

    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < m; j++)
        {
            if(grid[i][j] == '1')
            {
                onesCount++;
                int p1  = findPar(i * m + j); // p1 ka par ek baar update ho rha hai and p2 ka par dir times update ho skta hai to p1 ko hi par bnaenge as poore island ka final par vohi bnna chahiye.
                for(int d = 0; d < dir.size();d++)
                {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];
                
                    if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == '1')
                    {
                        int p2 = findPar(r * m + c);
                        if(p1 != p2)
                        {
                            onesCount--;
                            par[p2] = p1;
                        }
                    }
                }
            }
        }
    }
    return onesCount;
}

//Leetcode 695 -> maximum Area of Island using union find
vector<int> par;
int findPar(int u)
{
    return par[u] == -1 ? u : par[u] = findPar(par[u]);
}

//Leetcode function
int maxAreaOfIsland(vector<vector<int>>& grid) 
{
    int n = grid.size();
    int m = grid[0].size();
        
    int maxArea = 0;
    par.resize(n * m,-1);
    vector<int> componentSize(n * m,1); // har island ka size saath rkh lia 0 ho ya 1 uska size 1 hi rkha initially.
        
    vector<vector<int>> dir {{0,1},{1,0}};
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < m; j++)
        {
            if(grid[i][j] == 1)
            {
                int p1  = findPar(i * m + j);
                
                for(int d = 0; d < dir.size();d++)
                {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];
                
                    if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1)
                    {
                        int p2 = findPar(r * m + c);
                        if(p1 != p2)
                        {
                            componentSize[p1] += componentSize[p2];
                            par[p2] = p1;
                        }
                    }
                }
                maxArea = max(maxArea,componentSize[p1]); // hamesha parent p1 bnega.
            }
        else
        componentSize[i * m + j] = 0; // jaha 0 mila uska size = 0 size arr mei(as 1 se initialize kra)
        }
    }
    return maxArea;
}

//Hackerrank -> journey to the Moon
vector<int> par;
vector<int> size;
int findPar(int u)
{
    return par[u] == u ? u : par[u] = findPar(par[u]);
}

//Hackerank function
long journeyToMoon(int n,vector<vector<int>>& astronaut)
{
    for(int i = 0; i < n; i++)
    {
        par.push_back(i);
        size.push_back(1);
    }

    for(vector<int>& ast : astronaut)
    {
        int p1 = findPar(ast[0]);
        int p2 = findPar(ast[1]);

        if(p1 != p2)
        {
            par[p1] = p2;
            size[p2] += size[p1];
        }
    }

    long sum = 0; 
    long totalPairs = 0;
    for(int i = 0; i < n; i++)  // calculation of pairs in O(N) ((O(N2) vaala TLE de deta)
    {
        if(par[i] == i)
        {
            totalPairs += size[i] * sum;
            sum += size[i];
        }
    }
    return totalPairs;
}

//Leetcode 815 -> Bus Routes
int numBusesToDestination(vector<vector<int>>& routes, int src, int dest)
{
    if(src == dest)
    return 0;

    int n = routes.size();
    vector<bool> isBusSeen(n,false); // vis for marking busNumber
    unordered_set<int> isBusStandSeen; // vis for marking busStand as busStand indexed and ordered nhi hai to hashSet rkhna pdega.

    unordered_map<int,vector<int>> busStandMapping; // BusStand vs BusNumber mapping(ek BusStand pr konsi konsi buses aati hai ye store krlia).
    int busNumber = 0;
    for(vector<int>& busStandList : routes)
    {
        for(int busStand : busStandList)
        {
            busStandMapping[busStand].push_back(busNumber);
        }    
        busNumber++;
    }

    queue<int> que;
    que.push(src);
    isBusStandSeen.insert(src);

    int level = 0;
    while(que.size() != 0)
    {
        int size = que.size();
        while(size-- > 0)
        {
            int busStand = que.front();
            que.pop();

            vector<int> allBuses = busStandMapping[busStand];

            for(int busNo : allBuses)
            {
                if(isBusSeen[busNo])
                continue;

                for(int bs : routes[busNo]) // bs is busStand
                {
                    if(isBusStandSeen.find(bs) == isBusStandSeen.end())
                    {
                        que.push(bs);
                        isBusStandSeen.insert(bs);

                        if(bs == dest)
                        return level + 1;
                    }
                }
                isBusSeen[busNo] = true;
            }
        }
        level++;
    }
    return -1;
}

//Leetcode 743 -> Network Delay Time
int networkDelayTime(vector<vector<int>>& times, int n, int k) 
{
    vector<vector<pair<int,int>>> graph(n+1);  // pair -> vtx , wsf rkha hai (class bhi bnaa skte the)
        
    for(vector<int>& ar : times)
    {
        graph[ar[0]].push_back({ar[1],ar[2]});
    }
        
    vector<int> dis(n+1,1e9);
    vector<bool> vis(n+1,false);
        
    priority_queue<pair<int,int>,vector<pair<int,int>>,greater<pair<int,int>>>que; // greater use krke minPQ mei convert krdia pq ko.
        
    que.push({0,k}); // que pair ke pehle ele pr by default sort krti hai to wsf ko pehla ele bnaakr pass kia que mei.
    dis[k] = 0;
        
    int noOfEdges = 0;
    int maxValue = 0;
        
    while(que.size() != 0)
    {
        pair p = que.top();
        que.pop();
            
        int vtx = p.second;
        int wsf = p.first;
            
        if(vis[vtx])
            continue;
        if(vtx != k)
            noOfEdges++;
        maxValue = max(maxValue,wsf);
        vis[vtx] = true;
        for(pair<int,int>& e : graph[vtx])
        {
            if(!vis[e.first] && wsf + e.second < dis[e.first])
            {
                dis[e.first] = wsf + e.second;
                que.push({wsf + e.second,e.first});
            }
        }
    }
    if(noOfEdges != n-1)
        return -1;
    return maxValue;        
}

//Leetcode 1192 -> Critical Connections in a network -> Articulation Bridges nikaalne hai graph ke
vector<int> dis, low;
vector<bool> vis;
int time = 0;
vector<vector<int>> res;
    
void dfs(int src,int n,int par,vector<vector<int>>& graph)
{
    dis[src] = low[src] = time++;
    vis[src] = true;
        
    for(int nbr : graph[src])
    {
        if(!vis[nbr])
        {
            dfs(nbr,n,src,graph);
                
            if(dis[src] < low[nbr])
                res.push_back({src,nbr});
                
            low[src] = min(low[src],low[nbr]);
        }
            
        else if(nbr != par)
            low[src] = min(low[src],dis[nbr]);
    }
}

//Leetcode function   
vector<vector<int>> criticalConnections(int n, vector<vector<int>>& connections) 
{
    vector<vector<int>> graph(n);
    for(vector<int>& ar : connections)
    {
        graph[ar[0]].push_back(ar[1]);
        graph[ar[1]].push_back(ar[0]);
    }
        
    dis.resize(n,0);
    low.resize(n,0);
    vis.resize(n,false);
            
    dfs(0,n,-1,graph);
            
    return res;            
}

//Leetcode 959 -> Regions Cut by Slashes
vector<int>par;
int findPar(int u)
{
    return par[u] == u ? u : par[u] = findPar(par[u]);
}

int regionsBySlashes(vector<string>& grid) 
{
    int n = grid.size();
    int countOfRegions = 1;

    for(int i = 0; i < (n+1)*(n+1);i++)
        par.push_back(i);
        
    for(int i = 0; i < n + 1; i++) // border vaale saare cells ka parent 0 ko bna dia
    {
        for(int j = 0; j < n + 1; j++)
        {
            if(i == 0)
                par[i * (n+1) + j] = 0;
            if(j == 0)
                par[i * (n+1) + j] = 0;
            if(i == n)
                par[i * (n+1) + j] = 0;
            if(j == n)
                par[i * (n+1) + j] = 0;
        }
    }

    for(int i = 0; i < n; i++)
    {
        string str = grid[i];
        for(int j = 0; j < n; j++)
        {
            if(str[j] == '/')
            {
                int p1 = findPar(i * (n + 1) + j+1); // Formula(remember)
                int p2 = findPar((i+1)* (n + 1) + j);
                if(p1 != p2)
                    par[p2] = p1;
                else
                    countOfRegions++;
            }
            else if(str[j] == '\\')
            {
                int p1 = findPar(i* (n+1) +j);
                int p2 = findPar((i+1)*(n+1) + j+1);
                if(p1 != p2)
                par[p2] = p1;
                else
                    countOfRegions++;     
            }
        }
    }
    return countOfRegions;   
}

//Leetcode 787 -> Cheapest Flights within K stops (TLE with newly added testcases) Bellman ford se pass ho rha hai done in java
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

int findCheapestPrice(int n, vector<vector<int>>& flights, int src, int dst, int k) 
{
    vector<vector<Edge>> graph(n);
    for(vector<int>& ar : flights)
        graph[ar[0]].push_back(Edge(ar[1],ar[2]));
        
    priority_queue<vector<int>,vector<vector<int>>,greater<vector<int>>> pq;
    pq.push({0,k+1,src});
        
    while(pq.size() != 0)
    {
        vector<int> rvtx = pq.top();
        pq.pop();
            
        int wsf = rvtx[0];
        int vtx = rvtx[2];
        int egdeCount = rvtx[1];
            
        if(vtx == dst)
            return wsf;
        if(egdeCount <= 0)
            continue;
            
        for(Edge e : graph[vtx])
            pq.push({e.w + wsf, egdeCount - 1,e.v});
    }
    return -1;
}

// Leetcode 841 -> Keys and Rooms -> Will keep a visited with hasPath and return false if even not one room is left unvisited
void hasPath(vector<vector<int>>& graph, int src,vector<bool>& vis)
{
    if(vis[src])
    return;

    vis[src] = true;
    for(int e : graph[src])
    {   
        if(e == src) continue;
        if(!vis[e])
        hasPath(graph,e, vis);
    }
}

// Leetcode function
bool canVisitAllRooms(vector<vector<int>>& rooms) {
    vector<bool> vis(rooms.size()+1, false);
    hasPath(rooms, 0, vis);  
    for(int i = 0; i < rooms.size(); i++) {
        if(vis[i] == false)
        return false;
    } 
    return true;
}