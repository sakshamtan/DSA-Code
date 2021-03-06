#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> par;
int findPar(int u)
{
    return par[u] == u ? u : par[u] = findPar(par[u]);
}
//Hackerearth Question
int mrPresident(int n,vector<vector<int>>& Edges,long k)
{
    for(int i = 0; i <= n; i++)
    par.push_back(i);

    vector<int> mstEdgesWeights;
    long overallWeightSum = 0;
    for(vector<int> &e : Edges)
    {
        int p1 = findPar(e[0]);
        int p2 = findPar(e[1]);

        if(p1 != p2)
        {
            par[p1] = p2;
            mstEdgesWeights.push_back(e[2]);
            overallWeightSum += e[2];
            n--;
        }
    }

    if(n > 1)
    return -1;

    if(overallWeightSum <= k)
    return 0;

    int transform = 0;
    for(int i = mstEdgesWeights.size()-1; i >= 0; i--)
    {
        overallWeightSum = overallWeightSum - mstEdgesWeights[i] + 1;
        transform++;
        if(overallWeightSum <= k)
        break;
    }

    return overallWeightSum <= k ? transform : -1;
}

void mrPresident()
{
    ios_base::sync_with_stdio(false); // for fast I/O in cpp
    long n, m, k;
    cin >> n >> m >> k;

    vector<vector<int>> Edges;
    for(int i = 0; i < m; i++)
    {
        int u, v, w;
        cin >> u >> v >> w;
        Edges.push_back({u,v,w});
    }

    sort(Edges.begin(),Edges.end(),[](vector<int>& a,vector<int>& b){
        return a[2] < b[2];
    });

    cout << mrPresident(n,Edges,k) << endl;
}

int main()
{
    mrPresident();
    return 0;
}
