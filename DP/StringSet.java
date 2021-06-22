import java.util.Arrays;
public class StringSet{

//Leetcode 516 -> Longest Palindromic Subsequence
public int longestPalind_memo(String s,int i,int j,int[][] dp)
{
    if(i > j)
        return dp[i][j] = 0;
    if(i == j)
        return dp[i][j] = 1;
        
    if(dp[i][j] != -1)
        return dp[i][j];
        
    if(s.charAt(i) == s.charAt(j))
        return dp[i][j] = longestPalind_memo(s,i+1,j-1,dp)+2; // ith and jth char ko palindrome ka part bnana hai so +2.
        
    return dp[i][j] = Math.max(longestPalind_memo(s,i+1,j,dp),longestPalind_memo(s,i,j-1,dp));
}

public int longestPalindromeSubseq_DP(String s,int I,int J,int[][] dp)
{
    int n = s.length();
    for(int gap = 0; gap < n; gap++) // diagonally fill kr rhe hai dp so gap method
    {
        for(int i = 0, j = gap; j < n; i++,j++)
        {    
            if(i == j)  // kbhi i > j nhi hoga as hum diagonal mei chl rhe hai.
            {
                dp[i][j] = 1;
                continue;
            }
        
            if(s.charAt(i) == s.charAt(j))
                dp[i][j] = dp[i+1][j-1] + 2;
            else
                dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
        }
    }
    return dp[I][J];  // jaha recursion ans bnaata hai dp bhi vahi hi ans bnaata hai.
}

//Now we are printing the longest palindromic subsequence so keeping the dp of String
public String printLongestPalindromeSubseq_DP(String s)
{
    int n = s.length();
    String[][] dp = new String[n][n];

    for(int gap = 0; gap < n; gap++)
    {
        for(int i = 0, j = gap; j < n; i++, j++)
        {
            if(i == j)
            {
                dp[i][j] = s.charAt(i) + "";
                continue;
            }

            if(s.charAt(i) == s.charAt(j))
            {
                dp[i][j] = s.charAt(i) + dp[i+1][j-1] + s.charAt(j);
                continue;
            }

            else
                dp[i][j] = (dp[i+1][j].length() > dp[i][j-1].length() ? dp[i+1][j] : dp[i][j-1]);
        }
    }
    return dp[0][n-1];
}

//Leetcode function
public int longestPalindromeSubseq(String s) 
{
    int n = s.length();
    int[][] dp = new int[n][n];
    // for(int[] d : dp)  only for memo -> tabulation mei 0 pdha hona chahiye by default
    //     Arrays.fill(d,-1);
        
    // return longestPalind_memo(s,0,n-1,dp);    
    return longestPalindromeSubseq_DP(s,0,n-1,dp);    
}

//Leetcode 115 -> Distinct Subsequences -> return no. of distinct subsequences t found in String s
public int numDistinct_memo(String s,String t,int n, int m,int[][] dp)
{
    if(m == 0) // agr t ki length == 0 means hume ek distinct subsequence mil gya
        return dp[n][m] = 1;

    if(n < m)  // agr s ki length t se chotti hogyi toh no ans possible
        return dp[n][m] = 0;
        
    if(dp[n][m] != -1)
        return dp[n][m];
        
    if(s.charAt(n-1) == t.charAt(m-1)) // n and m are lengths so -1 krke check kr rhe hai
    {
        dp[n][m] = numDistinct_memo(s,t,n-1,m-1,dp) + numDistinct_memo(s,t,n-1,m,dp);  
        // Do calls when char are equal -> ek call present subseq bnaake laati hai and dusri call aage khi same subseq bn rha ho to vo laati hai
    }
    else{
        dp[n][m] = numDistinct_memo(s,t,n-1,m,dp);
    }
    return dp[n][m];    
}

public int numDistinct_DP(String s,String t,int N, int M,int[][] dp)
{
    for(int n = 0; n <= N; n++)
    {
        for(int m = 0; m <= M; m++)
        {
            if(m == 0)
            {
                dp[n][m] = 1;
                continue;
            }
            
            if(n < m) 
            {
                dp[n][m] = 0;
                continue;
            }
            
            if(s.charAt(n-1) == t.charAt(m-1))
            {
                dp[n][m] = dp[n-1][m-1] + dp[n-1][m];  
            }
            else{
                dp[n][m] = dp[n-1][m];
            }
        
        }
    }
    return dp[N][M];        
}

//Leetcode function
public int numDistinct(String s, String t) 
{
    int n = s.length();
    int m = t.length();
        
    int[][] dp = new int[n+1][m+1];
    // for(int[] d : dp) // only for memo
    //     Arrays.fill(d,-1);
        
    // return numDistinct_memo(s,t,n,m,dp); 
    return numDistinct_DP(s,t,n,m,dp); 
}

//Leetcode 1143 -> Longest Common Subsequence(LCS)
public int longestCommonSubseq_memo(String text1,String text2,int n,int m,int[][] dp)
{
    if(m == 0 || n == 0)
        return dp[n][m] = 0;
        
    if(dp[n][m] != -1)
        return dp[n][m];
        
    if(text1.charAt(n-1) == text2.charAt(m-1))
    {
        dp[n][m] = longestCommonSubseq_memo(text1,text2,n-1,m-1,dp) + 1;
    }
    else{
        dp[n][m] = Math.max(longestCommonSubseq_memo(text1,text2,n-1,m,dp),longestCommonSubseq_memo(text1,text2,n,m-1,dp));
    }
    return dp[n][m];
}

public int longestCommonSubseq_DP(String text1,String text2,int N,int M,int[][] dp)
{
    for(int n = 0; n <= N; n++)
    {
        for(int m = 0; m <= M; m++)
        {         
            if(m == 0 || n == 0)
            {
                dp[n][m] = 0;
                continue;
            }
        
            if(text1.charAt(n-1) == text2.charAt(m-1))
            {
                dp[n][m] = dp[n-1][m-1] + 1;
            }
            else{
                dp[n][m] = Math.max(dp[n-1][m],dp[n][m-1]);
            }
        }
    }
    return dp[N][M];
}

//Leetcode function
public int longestCommonSubsequence(String text1, String text2) 
{
    int n = text1.length();
    int m = text2.length();
        
    int[][] dp = new int[n+1][m+1];
    // for(int[] d : dp)
    //     Arrays.fill(d,-1);
        
    // return longestCommonSubseq_memo(text1,text2,n,m,dp);
    return longestCommonSubseq_DP(text1,text2,n,m,dp);

}

//Leetcode 1035 -> Uncrossed Lines (Based on LCS) -> Same lcs vaali tabulation se solve ho jaayega
public int maxUncrossedLines(int[] nums1, int[] nums2) 
{
    int N = nums1.length;
    int M = nums2.length;
        
    int[][] dp = new int[N+1][M+1];
        
    for(int n = 0; n <= N; n++)
    {
        for(int m = 0; m <= M; m++)
        {
            if(n == 0 || m == 0)
            {
                dp[n][m] = 0;
                continue;
            }
                
            if(nums1[n-1] == nums2[m-1])
                dp[n][m] = dp[n-1][m-1] + 1;
            else
                dp[n][m] = Math.max(dp[n-1][m],dp[n][m-1]);
        }
    }
    return dp[N][M];
}

//Leetcode 1458 -> Max Dot Product of Two Subsequences
public int maxDotProduct_memo(int[] nums1,int[] nums2,int n,int m,int[][] dp)
{
    if(n == 0 || m == 0)
        return dp[n][m] = -(int)1e7;
        
    if(dp[n][m] != -(int)1e8)
        return dp[n][m];
        
    int val = nums1[n-1] * nums2[m-1];
        
    int acceptBothNum = maxDotProduct_memo(nums1,nums2,n-1,m-1,dp) + val;
    int ignoreFirstNum = maxDotProduct_memo(nums1,nums2,n-1,m,dp);
    int ignoreSecondNum = maxDotProduct_memo(nums1,nums2,n,m-1,dp);
        
    return dp[n][m] = Math.max(Math.max(acceptBothNum,val),Math.max(ignoreFirstNum,ignoreSecondNum));
}

public int maxDotProduct_DP(int[] nums1,int[] nums2,int N,int M,int[][] dp)
{
    for(int n = 0; n <= N; n++)
    {
        for(int m = 0; m <= M; m++)
        {
                
            if(n == 0 || m == 0)
            {
                dp[n][m] = -(int)1e7;
                continue;
            }
        
            int val = nums1[n-1] * nums2[m-1];
                
            int acceptBothNum = dp[n-1][m-1] + val;
            int ignoreFirstNum = dp[n-1][m];
            int ignoreSecondNum = dp[n][m-1];
                
            dp[n][m] = Math.max(Math.max(acceptBothNum,val),Math.max(ignoreFirstNum,ignoreSecondNum));
        }
    }
    return dp[N][M];
}

//Leetcode function
public int maxDotProduct(int[] nums1, int[] nums2) 
{
    int n = nums1.length;
    int m = nums2.length;
    int[][] dp = new int[n+1][m+1];
    // for(int[] d : dp)
    //     Arrays.fill(d,-(int)1e8);
        
    // return maxDotProduct_memo(nums1,nums2,n,m,dp);
    return maxDotProduct_DP(nums1,nums2,n,m,dp);
}

//Leetcode 72 -> Edit Distance
public int minDistance_memo(String word1,String word2,int n,int m,int[][] dp)
{
    if(n == 0 || m == 0)
        return dp[n][m] = (n == 0) ? m : n;
        
    if(dp[n][m] != -1)
        return dp[n][m];
        
    int insert = minDistance_memo(word1,word2,n,m-1,dp);
    int delete = minDistance_memo(word1,word2,n-1,m,dp);
    int replace = minDistance_memo(word1,word2,n-1,m-1,dp);
        
    if(word1.charAt(n-1) == word2.charAt(m-1))
        return dp[n][m] = replace;  // n-1, m-1 ki call replace vaali hi to hai(idhr koi operation nhi perform krna)
    else
        return dp[n][m] = Math.min(Math.min(insert,delete),replace) + 1;
}

public int minDistance_DP(String word1,String word2,int N,int M,int[][] dp)
{
    for(int n = 0; n <= N; n++)
    {
        for(int m = 0; m <= M; m++)
        {
            if(n == 0 || m == 0)
            {
                dp[n][m] = (n == 0) ? m : n;
                continue;
            }
        
            int insert = dp[n][m-1];
            int delete = dp[n-1][m];
            int replace = dp[n-1][m-1];
        
            if(word1.charAt(n-1) == word2.charAt(m-1))
                dp[n][m] = replace;
            else
                dp[n][m] = Math.min(Math.min(insert,delete),replace) + 1;
                
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
    // for(int[] d : dp)
    //     Arrays.fill(d,-1);

    // return minDistance_memo(word1,word2,n,m,dp);
    return minDistance_DP(word1,word2,n,m,dp);
}

//Follow-up of minDistance -> now cost of each operation is given tell the min cost for transformation
// cost[0] -> cost of insert operation; cost[1] -> delete; cost[2] -> replace
public int minDistance_withCost(String word1,String word2,int[] cost,int n,int m,int[][] dp)
{
    if(n == 0 || m == 0)
        return dp[n][m] = (n == 0) ? m * cost[0] : n * cost[1];
        
    if(dp[n][m] != -1)
        return dp[n][m];
        
    int insert = minDistance_withCost(word1,word2,n,m-1,dp);
    int delete = minDistance_withCost(word1,word2,n-1,m,dp);
    int replace = minDistance_withCost(word1,word2,n-1,m-1,dp);
        
    if(word1.charAt(n-1) == word2.charAt(m-1))
        return dp[n][m] = replace + 0;  // n-1, m-1 ki call replace vaali hi to hai(idhr koi operation nhi perform krna)
    else
        return dp[n][m] = Math.min(Math.min(insert + cost[0],delete + cost[1]),replace + cost[2]);
}

//Leetcode 44 -> WildCard Matching
// 0 -> False ; 1 -> True ; -1 -> Delfault
public int isMatch_memo(String s,String p,int n,int m,int[][] dp)
{
    if(n == 0 || m == 0)
    {
        if(n == 0 && m == 0)
            return dp[n][m] = 1;
        else if(m == 1 && p.charAt(m-1) == '*')  // case when s is empty but p still has one character(ek se zyaada * nhi bach skta as humne continuous *'s hatta diye the string se')
            return dp[n][m] = 1;
        else 
            return dp[n][m] = 0;  
    }
        
    if(dp[n][m] != -1)
        return dp[n][m];
        
    char ch1 = s.charAt(n-1);
    char ch2 = p.charAt(m-1);
        
    if(ch1 == ch2 || ch2 == '?')
        return dp[n][m] = isMatch_memo(s,p,n-1,m-1,dp);
    else if(ch2 == '*')
    {
        boolean res = false;
        res = res || (isMatch_memo(s,p,n-1,m,dp) == 1); // '*' matches the curr char
        res = res || (isMatch_memo(s,p,n,m-1,dp) == 1); // '*' doesnt matches the curr char -> * matches to empty sequence
            
        return dp[n][m] = (res) ? 1 : 0;
    }

    else
        return dp[n][m] = 0;
}

// removes Consecutive Stars in the pattern String -> Ek '*' bhi vohi kaam krega jo consecutive '*'s krenge   
public String removeConsecutiveStars(String str)
{
    if(str.length() == 0)
        return "";
        
    StringBuilder sb = new StringBuilder();
    sb.append(str.charAt(0));
        
    int idx = 1;
    while(idx < str.length())
    {
        while(idx < str.length() && sb.charAt(sb.length() - 1) == '*' && str.charAt(idx) == '*')
            idx++;
        
        if(idx < str.length())
            sb.append(str.charAt(idx));
        idx++;
    }
    return sb.toString();
}

//Leetcode function
public boolean isMatch(String s, String p) 
{
    p = removeConsecutiveStars(p); // sbse pehle stars remove krni hai and string p reduce krni hai.
    int n = s.length();
    int m = p.length();
        
    int[][] dp = new int[n+1][m+1];
    for(int[] d : dp)
        Arrays.fill(d,-1);
        
    return isMatch_memo(s,p,n,m,dp) == 1;
}

//Count Palindromic Subsequences -> GFG
int mod = (int)1e9 + 7;
long countPS_memo(String str,int i,int j,long[][] dp)
{
    if(i >= j)
    {
        return dp[i][j] = (i == j) ? 1 : 0;
    }
        
    if(dp[i][j] != -1)
    return dp[i][j];
        
    long a = countPS_memo(str,i+1,j,dp);
    long b = countPS_memo(str,i,j-1,dp);
    long c = countPS_memo(str,i+1,j-1,dp);
        
    return dp[i][j] = ((str.charAt(i) != str.charAt(j)) ? a + b - c + mod : a + b + 1) % mod;
}

//GFG function
long countPS(String str)
{
    int n = str.length();
    long[][] dp = new long[n][n];
        
    for(long[] d : dp)
    Arrays.fill(d,-1);
        
    return countPS_memo(str,0,n-1,dp);
}
}