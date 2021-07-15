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

//Leetcode 674 -> Longest Continuous Increasing Subsequence
public int findLengthOfLCIS(int[] arr) 
{
    int n = arr.length;
    int[] dp = new int[n];
    Arrays.fill(dp,1);

    int maxLen = 1;
    for(int i = 1; i < n; i++)
    {
        if(arr[i] > arr[i-1])
        {
            dp[i] = dp[i-1] + 1;
        }
        maxLen = Math.max(dp[i],maxLen);
    }     
    return maxLen;
}

//Leetcode 1671 -> Minimum number of removals to make Mountain Array -> Arr.length - LBS
public int[] LIS_LR(int[] arr)
{
    int n = arr.length;
    int[] dp = new int[n];
    for(int i = 0; i < n; i++)
    {
        dp[i] = 1;
        for(int j = i - 1; j >= 0; j--)
        {
            if(arr[j] < arr[i])
                dp[i] = Math.max(dp[i],dp[j] + 1);
        }
    }
    return dp;
}
    
public int[] LIS_RL(int[] arr)
{
    int n = arr.length;
    int[] dp = new int[n];
    for(int i = n-1; i >= 0; i--)
    {
        dp[i] = 1;
        for(int j = i + 1; j < n; j++)
        {
            if(arr[j] < arr[i])
                dp[i] = Math.max(dp[i],dp[j] + 1);
        }
    }
    return dp;
}

//Leetcode function  
public int minimumMountainRemovals(int[] nums) 
{
    int[] LIS = LIS_LR(nums);
    int[] LDS = LIS_RL(nums);
    int LBS = 0;
    for(int i = 0; i < nums.length; i++)
    {
        if(LIS[i] == 1 || LDS[i] == 1) // taaki only increasing and only decreasing subseq ke time LBS calculate naa ho.
            continue;
            
        LBS = Math.max(LBS,LIS[i] + LDS[i] - 1);
    }
    return nums.length - LBS;
}

//Leetcode 845 -> Longest Mountain in Array -> LBCS -> Longest Bitonic Longest Continuous Subsequence
public int[] LCIS_LR(int[] arr)
{
    int n = arr.length;
    int[] dp = new int[n];
    dp[0] = 1;
    for(int i = 1; i < n; i++)
    {
        dp[i] = 1;
        if(arr[i] > arr[i-1])
            dp[i] = dp[i-1] + 1;
    }
    return dp;
}
    
public int[] LCIS_RL(int[] arr) 
{
    int n = arr.length;
    int[] dp = new int[n];
    dp[n-1] = 1;
    for(int i = n-2; i >= 0; i--)
    {
        dp[i] = 1;
        if(arr[i] > arr[i+1])
            dp[i] = dp[i+1] + 1;
    }
    return dp;
}

public int longestMountain(int[] nums) 
{
    int[] LCIS = LCIS_LR(nums);
    int[] LCDS = LCIS_RL(nums);
    int LCBS = 0;
    for(int i = 0; i < nums.length; i++)
    {
        if(LCIS[i] == 1 || LCDS[i] == 1)  // sirf increasing ya sirf decreasing ho to LCBS calculate nhi krna
            continue;
            
        LCBS = Math.max(LCBS,LCIS[i] + LCDS[i] - 1);
    }
    return LCBS;
}

//Leetcode 940 -> Distinct Subsequences II
public int distinctSubseqII(String s) 
{
    s = '$' + s;
    int n = s.length();
    int[] dp = new int[n];
    int[] lastOcc = new int[26];
    Arrays.fill(lastOcc,-1);
    int mod = (int)1e9 + 7;
        
    dp[0] = 1; // for empty string
    for(int i = 1; i < n; i++)
    {
        dp[i] = (dp[i-1] * 2) % mod;
        int idx = s.charAt(i) - 'a';
        if(lastOcc[idx] != -1)
            dp[i] = (dp[i] - dp[lastOcc[idx]-1] + mod) % mod;
        
        lastOcc[idx] = i;
    }
    return dp[n-1] - 1;
}

//Leetcode 1278 -> Palindrome Partiotioning III
// Ek string ko kitne changes krke palindrome mei convert kr skte hai iski dp hai minChangeDP
public int[][] minChanges(String str)
{
    int n = str.length();
    int[][] dp = new int[n][n];
        
    for(int gap = 1; gap < n; gap++)
    {
        for(int i = 0, j = gap; j < n; i++,j++)
        {
            if(gap == 1)
                dp[i][j] = str.charAt(i) == str.charAt(j) ? 0 : 1;
            else
                dp[i][j] = str.charAt(i) == str.charAt(j) ? dp[i+1][j-1] : dp[i+1][j-1] + 1;
        }
    }
    return dp;
}

//Present set mei string cut krke store kr rhe hai and usme minChanges calculate kr rhe hai through 
//minChangeDP and baaki ki string ko k-1 sets mei divide hokr min changes btaane ko bol rhe hai
public int palindromePartition_memo(String str,int k,int si,int[][] dp,int[][] minChangeDP)
{
    if(str.length() - si <= k)
        return dp[si][k] = (str.length() - si == k) ? 0 : (int)1e9;
        
    if(k == 1)
        return dp[si][k] = minChangeDP[si][str.length()-1];
        
    if(dp[si][k] != -1)
        return dp[si][k];
        
    int minAns = (int)1e9;
    for(int cut = si; cut < str.length()-1; cut++)
    {
        int minChangesInMySet = minChangeDP[si][cut];
        int minChangesInRecSet = palindromePartition_memo(str,k-1,cut+1,dp,minChangeDP);
            
        if(minChangesInRecSet != (int)1e9)
            minAns = Math.min(minAns,minChangesInMySet + minChangesInRecSet);
    }
    return dp[si][k] = minAns;
}

//Leetcode function
public int palindromePartition(String s, int k) 
{
    int[][] minChangeDP = minChanges(s);
    int[][] dp = new int[s.length()][k+1];
    for(int[] d : dp)
        Arrays.fill(d,-1);

    return palindromePartition_memo(s,k,0,dp,minChangeDP);        
}
}