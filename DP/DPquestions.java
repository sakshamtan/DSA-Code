public class DPquestions{

public int uniquePaths_DP(int SR,int SC,int er,int ec,int[][] dp,int[][] grid) 
{
    for(int sr = er; sr >= 0; sr--)
    {
        for(int sc = ec; sc >= 0; sc--)
        {
            if(grid[sr][sc] == 1) // agr present cell pr hi obstacle ho(aate hi check krlenge)
            {
                dp[sr][sc] = 0;
                continue;
            }
           
            if(sr == er && sc == ec)
            {
               dp[sr][sc] = 1;
               continue;
            }
           
            int count = 0;
            if(sr + 1 <= er) 
                count += dp[sr+1][sc];
            if(sc + 1 <= ec)
                count += dp[sr][sc+1];
           
            dp[sr][sc] = count;
        } 
    }     
    return dp[SR][SC];    
}

//Leetcode function
public int uniquePathsWithObstacles(int[][] obstacleGrid) 
{
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;

    int[][] dp = new int[m][n];
        
    return uniquePaths_DP(0,0,m-1,n-1,dp,obstacleGrid);
}

//Leetcode 583 -> Delete Operations for Two Strings -> Tell no of deletions to be done to make both strings same
// uses Longest Common Subsequnce
public int lcs_DP(String s,String t,int N,int M,int[][] dp)
{
    for(int n = 0; n <= N; n++)
    {
        for(int m = 0; m <= M; m++)
        {
            if(n == 0 || m == 0)
            {
                dp[n][m] = 0;
                continue;
            }

            if(s.charAt(n-1) == t.charAt(m-1))
            dp[n][m] = dp[n-1][m-1] + 1;
            else
                dp[n][m] = Math.max(dp[n-1][m],dp[n][m-1]);
        }
    }
    return dp[N][M];
}

//Leetcode function
public int minDistance(String word1, String word2) 
{
    int n = word1.length();
    int m = word2.length();
    int[][] dp = new int[n+1][m+1];
        
    int lcs = lcs_DP(word1,word2,n,m,dp);
        
    return (n - lcs) + (m - lcs); // s1.length - lcs + s2.length - lcs (length - common Subseq = No of deletions)  
}

//Leetcode 712 -> Minimum ASCII Delete Sum for Two Strings 
// Now return the min Ascii sum of the deleted characters to make both words same 
// Now lcs_DP se max ASCII sum mangwa lenge lcs ka
public int lcs_ASCII(String s,String t,int N,int M,int[][] dp)
{
    for(int n = 0; n <= N; n++)
    {
        for(int m = 0; m <= M; m++)
        {
            if(n == 0 || m == 0)
            {
                dp[n][m] = 0;
                continue;
            }

            if(s.charAt(n-1) == t.charAt(m-1))
            dp[n][m] = dp[n-1][m-1] + (int)(s.charAt(n-1)); // +1(of length) ki jagah yaha ascii value add kr rhe hai
            else
                dp[n][m] = Math.max(dp[n][m-1],dp[n-1][m]);
        }
    }
    return dp[N][M];   
}

//Leetcode function
public int minimumDeleteSum(String s1, String s2) 
{
    int n = s1.length();
    int m = s2.length();
        
    int[][] dp = new int[n+1][m+1];
        
    int lcs_ASCII_sum = lcs_ASCII(s1,s2,n,m,dp);
        
    int totalASCII_sum = 0;
    for(int i = 0; i < n; i++)
        totalASCII_sum += (int)(s1.charAt(i));
    for(int j = 0; j < m; j++)
        totalASCII_sum += (int)(s2.charAt(j));
        
    return totalASCII_sum - 2 * lcs_ASCII_sum;  // lcs vaale characters do baar add hogye totalASCII_sum mei
}
}