#include <iostream>
#include <vector>
#include <climits>

#define vi vector <int>
#define vvi vector <vi>
#define vb vector <bool>
#define vvb vector <vb>

using namespace std;

void display (vi &dp)
{
    for (int ele : dp)
    cout<<ele<<" ";
    cout<<endl;
}
void display2D (vvi &dp)
{
    for (vi ar : dp)
    {
        for (int ele : ar)
        {
            cout<<ele<<" ";
        }  
    }
    cout<<endl;
}

int fibo_01 (int n , vi & dp)
{
    if (n <= 1)
    return dp[n] = n;

    if (dp[n] != 0)
    return dp[n];

    int ans = fibo_01(n-1,dp) + fibo_01(n-2,dp);
    return dp[n] = ans;
}
int fibo_02 (int n , vi & dp) // tabulation
{
    for (int i = 0 ; i<=n ; i++)
    {
        if (i <= 1)
        {                  
            dp[i] = i;
            continue;
        }
        dp[i] = dp[i-1] + dp[i-2];  
    }
     return dp[n];
}
int mazePathHVD_01(int sr,int sc,int er,int ec,vvi &dp)
{
    if (sr==er && sc==ec)
    {
        dp[sr][sc] = 1;
        return 1;
    }
    int count = 0;
    if (dp[sr][sc] != 0)
    return dp[sr][sc];
    if (er == ec && dp[sc][sr] != 0)
    return dp[sc][sr];

    if (sr+1 <= er)
    count+=mazePathHVD_01(sr+1,sc,er,ec,dp);
    if (sc+1 <= ec)
    count+=mazePathHVD_01(sr,sc+1,er,ec,dp);
    if (sr+1 <= er && sc+1 <=ec)
    count+=mazePathHVD_01(sr+1,sc+1,er,ec,dp)
    dp[sr][sc] = count;
    if (er == ec)
    dp[sc][sr] = count;
    return count;
}
int mazePathHVD_02(int er,int ec,vvi &dp) //tabulation
{
    for (int sr = er ; sr >= 0 ; sr--)
    {
        for (int sc = ec ; sc >= 0 ; sc--)
        {
    if (sr==er && sc==ec)
    {
        dp[sr][sc]=1;
        continue;
    }
    int count = 0;
    if (sr+1 <= er)
    count+=dp[sr+1][sc];
    if (sc+1 <= ec)
    count+=dp[sr][sc+1];
    if (sc+1 <= ec &&  sr+1 <= er)
    count+= dp[sr+1][sc+1];

    count = dp[sr][sc];
        }
    }
    
    return dp[0][0];
}
int mazePathMulti_01 (int sr,int sc,int er,int ec,vvi &dp)
{
    if (sr == er && sc == ec)
    {
        dp[sr][sc] = 1;
        return 1;
    }
    if (dp[sr][sc] !=0)
    return dp[sr][sc];

    int count = 0;
    for (int jump = 1 ; sr + jump <= er ; jump++)
    count+=mazePathMulti_01(sr+jump,sc,er,ec,dp);
    for (int jump = 1 ; sc + jump <= ec ; jump++)
    count+=mazePathMulti_01(sr,sc+jump,er,ec,dp);
    for (int jump = 1 ; sc + jump <= ec && sr + jump <= er ; jump++)
    count+=mazePathMulti_01(sr+jump,sc+jump,er,ec,dp);

    dp[sr][sc] = count;
    return count;

}

int mazePathMulti_02 (int er,int ec,vvi &dp) // tabulation
{
    for (int sr = er ; sr >= 0 ; sr--)
    {
        for (int sc = ec ; sc >= 0 ; sc--)
        {
        if (sr == er && sc == ec)
        {
        dp[sr][sc] = 1;
        continue;
        }

    int count = 0;
    for (int jump = 1 ; sr + jump <= er ; jump++)
    count+=dp[sr+jump][sc];
    for (int jump = 1 ; sc + jump <= ec ; jump++)
    count+=dp[sr][sc+jump];
    for (int jump = 1 ; sc + jump <= ec && sr + jump <= er ; jump++)
    count+=dp[sr+jump][sc+jump];

    dp[sr][sc] = count;
        }
    }
    return dp[0][0];
}
int boardPath_01 (int st , int end , vi &dp)
{
    if (st == end)
    {
    dp[st] = 1;
    return 1;
    }

    int count = 0;
    if (dp[st] != 0)
    return dp[st];
    for (int dice = 1; dice <= 6; dice++)
    {
        if (st + dice <= end)
        count += boardPath_01(st + dice , end , dp);
    }
    dp[st] = count;
    return count;
}
int boardPath_02 (int st , int end , vi &dp)
{
    for (int i = end;  i >= st ; i--)
    {
    if (i == end)
    {
    dp[i] = 1;
    continue;
    }

    int count = 0;
    for (int dice = 1; dice <= 6; dice++)
    {
        if (i + dice <= end)
        count += dp[i + dice];
    }
    
    dp[i] = count;
    }
    return dp[0];
}

int boardPath_01_givenOutcomes (int st , int end , vi &outcomes vi &dp)
{
    if (st == end)
    {
    dp[st] = 1;
    return 1;
    }

    int count = 0;
    if (dp[st] != 0)
    return dp[st];
    for (int i = 0; i <= outcomes.size(); i++)
    {
        if (st + outocmes[i] <= end)
        count += boardPath_01_givenOutcomes(st + outcomes[i] , end , outcomes , dp);
    }
    dp[st] = count;
    return count;
}
int boardPath_02_givenOutcomes (int stP , int end , vi &outcomes vi &dp)
{
    for  (int st = end ; st >= stP ; st--)
    {
    if (st == end)
    {
    dp[st] = 1;
    continue;
    }

    int count = 0;
    for (int i = 0; i <= outcomes.size(); i++)
    {
        if (st + outocmes[i] <= end)
        count += dp[st + outcomes[i]];
    }
    dp[st] = count;
    }
    return dp[0];
}
// SET2=============================================================================================

int pairAndSingle_01 (int n , vi &dp)
{
    if (n <= 1)
    return 1;

    if (dp[n] != 0)
    return dp[n];

    int singleWays = pairAndSingle_01(n-2,dp);
    int pairUpWays = pairAndSingle_01(n-2,dp)*(n-1);

    dp[n]= singleWays + pairUpWays;
    return dp[n];
}
int pairAndSingle_02 (int n , vi &dp) //  tabulation
{
    for (int i = 0 ; i <= n ; i++)
    {
    if (i <= 1)
    continue;
    int singleWays = dp[i-2];
    int pairUpWays = dp[i-2]*(i-1);

    dp[i]= singleWays + pairUpWays;
    return dp[i];
}
int pairAndSingle_03 (int n) // pointer optimized version.
{
    if (n<=1)
    return 1;
    int a = 1;
    int b = 1;
    for (int i = 2 ; i <= n ; i++)
    {
        ans = b + a * (i-1);
        a = b;
        b = ans;
    }
    return ans;
}
int MinJump_01 (int idx , vi &arr , vi &dp) // leetcode 45
{
    if (idx == arr.size()-1)
    return 0;

    if (arr[idx] == 0)
    return (int) 1e7;

    if (dp[idx] != 0)
    return dp[idx];
    int minAns = (int) 1e7;
    for (int jump = 1 ; jump <= arr[idx] && idx + jump < arr.size(); jump++)
    {
        int minJump = MinJump_01 (idx + jump , arr);
        if (minJump != (int)1e7 && minJump + 1 < minAns)
        minAns = minJump + 1;
    }
    dp[idx] = minAns;
    return minAns;
}
int divideNintoKsets (int n , int k , vi &dp)
{
    if (n==k)
    return 1;
    if (k==1)
    return 1;
    if (n < k)
    return 0;

    int a = divideNintoKsets (n-1 , k-1 , dp);
    int b = divideNintoksets (n-1 , k , dp)*k;
    int ans = a + b;
    return ans;

}
int maxSqaureAns=0;
int maxSquareof1s(int r , int c , vvi &arr , vvi &dp)
{
    if (r == arr.size () ||  c == arr[0].size())
    return 0;
    if (dp[r][c] != -1)
    return dp[r][c];

    int down =  maxSquareof1s(r+1,c,arr,dp);
    int diagonal = maxSquareof1s(r+1,c+1,arr,dp);
    int right = maxSquareof1s(r,c+1,arr,dp);

    int minPossibleSquare =  0;
    if (arr[r][c]==1)
    minPossibleSquare = min(down,min(diagonal,right))+1;
    dp[r][c] = minPossibleSquare;
    maxSquareAns = max(dp[r][c],maxSquareAns);
    return dp[r][c];
}
//String set======================================================================================
vbb isPalindromeSubstring (string str)
{
    vbb dp(str.length(),vb(str.length(),false));
    for (int i = 0 , j = gap ; j < str.length() ; j++ , i++)
    {
        if (gap==0) 
        dp[i][j] = true;
        else if  (str[i]==str[j])
        {
            if (gap == 1)
            dp[i][j] =  true;
            else if (dp[i+1][j-1])
            dp[i][j] = true;
        }
    }
    return dp;
}

int longestPalindromeSubstring (string str , vvi &dp)
{
    vbb isPalin = isPalindromeSubstring (str);
    for (int gap = 0 ; gap < str.length() ; gap++)
    {
    for (int i = 0 , j = gap ; j < str.length() ; j++ , i++)
    {
        if (gap==0) 
        dp[i][j] = 1;
        else if  (str[i]==str[j])
        {
            if (gap == 1)
            dp[i][j] =  2;
            else if (isPalin[i+1][j-1])
            dp[i][j] = dp[i+1][j-1]+2;
        }
        else {
            dp[i][j] = max(dp[i+1][j],dp[i][j-1]);
        }

    }
    }
    return dp[0][str.length()-1];
}


//LIS set============================================================================================
vi Lis_DP(vi &arr)
{
    vi dp (arr.size(),1);
    int max_ = 1;
    for (int i = 1 ; i < arr.size() ; i++)
    {
        for (int j = 0 ; j < i ; j++)
        {
            if (arr[i] > arr[j])
            dp[i] = max(dp[i],dp[j]+1);
        }
    }
    max_ = max (max_,dp[i]);
    cout<<max_<<endl;
    return dp;
}


//CUT Set==========================================================================================================
int MCM_memo(vi &arr,int si,int ei,vvi &dp)
{
    if (si+1==ei)
    return 0;
    int minAns = 1e7;
    if (dp[si][ei]!=0)
    return dp[si][ei];
    for (int cut = si+1 ; cut < ei ; cut++)
    {
        int left = MCM_memo(arr,si,cut,dp);
        int right = MCM_memo(arr,cut,ei,dp);
        int myCost = left + arr[si]*arr[cut]*arr[ei] + right;
        minAns = min(minAns,myCost);
    }
    dp[si][ei] = minAns;
    return minAns;
}
int MCM_dp(vi &arr,vvi &dp)
{
    for (int gap = 2 ; gap < arr.size() ; gap++)
    {
        for (int si = 0 , ei = gap ; ei < arr.size() ; si++ , ei++)
        {
    if (si+1==ei)
    continue;
    int minAns = 1e7;
    for (int cut = si+1 ; cut < ei ; cut++)
    {
        int left = dp[si,cut];
        int right =dp[cut,ei;
        int myCost = left + arr[si]*arr[cut]*arr[ei] + right;
        minAns = min(minAns,myCost);
    }
    dp[si][ei] = minAns;
    return dp[0][arr.size()-1];
}

void set1()
{
    int n = 3;
   // vi dp (n+1,0);
   // cout<<fibo_02(10,dp);
 // cout<<fibo_01(10,dp); 
 vvi dp(n,vi(n,0));
 cout<<mazePathHVD_01(0,0,n-1,n-1,dp)<<endl;
 display2D(dp);

}
void set2()
{
      int n = 3;
      int k = 2;
    vi dp (n+1,0);
    // vvi dp(n,vi(n,0));
    display(dp);
    //cout<<pairAndSingle_03(n)<<endl;
    cout<<divideNintoKsets(n,k,dp);
}
void solve ()
{
   // set1();
   set2();
}
int main ()
{
    solve();
}
