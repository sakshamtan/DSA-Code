import java.util.Arrays;

public class CutType{

public static void print1D(int[] arr)
{
    for(int ele : arr)
    {
        System.out.print(ele + " ");
    }
    System.out.println();
}

public static void print2D(int[][] arr)
{
    for(int[] ar : arr)
    {
        print1D(ar);
    }
}

public static int MCM_memo(int[] arr,int si,int ei,int[][] dp)
{
    if(si + 1 == ei)     // ek matrix ki multiplication cost = 0
    return dp[si][ei] = 0;

    if(dp[si][ei] != -1)
    return dp[si][ei];

    int minAns = (int)1e9;
    for(int cut = si+1; cut < ei; cut++)
    {
        int lans = MCM_memo(arr,si,cut,dp);
        int rans = MCM_memo(arr,cut,ei,dp);

        minAns = Math.min(minAns,lans + arr[si] * arr[cut] * arr[ei] + rans); // mcm ka formula -> cost of multi = q*p*r
    }
    return dp[si][ei] = minAns;
}

//Tabulation -> gap strategy as diagonally fill hoti hai dp isme 
public static int MCM_DP(int[] arr,int SI,int EI,int[][] dp)
{
    int n = arr.length;
    for(int gap = 1; gap < n; gap++)
    {
        for(int si = 0, ei = gap; ei < n; si++,ei++)
        {
            if(si + 1 == ei)     // ek matrix ki multiplication cost = 0
            {
                dp[si][ei] = 0;
                continue;
            }

            int minAns = (int)1e9;
            for(int cut = si+1; cut < ei; cut++)
            {
                int lans = dp[si][cut];
                int rans = dp[cut][ei];

                minAns = Math.min(minAns,lans + arr[si] * arr[cut] * arr[ei] + rans); // mcm ka formula -> cost of multi = q*p*r
            }
            dp[si][ei] = minAns;
        }
    }
    return dp[SI][EI];
}

//Follow up -> Now cost of each multiplication is 3 units and cost of one addition is 5 units
public static int MCM_DP_02(int[] arr,int SI,int EI,int[][] dp)
{
    int n = arr.length;
    for(int gap = 1; gap < n; gap++)
    {
        for(int si = 0, ei = gap; ei < n; si++,ei++)
        {
            if(si + 1 == ei)     // ek matrix ki multiplication cost = 0
            {
                dp[si][ei] = 0;
                continue;
            }

            int minAns = (int)1e9;
            for(int cut = si+1; cut < ei; cut++)
            {
                int lans = dp[si][cut];
                int rans = dp[cut][ei];

                minAns = Math.min(minAns,lans + arr[si] * (3 * arr[cut] + 5 * (arr[cut] - 1)) * arr[ei] + rans); 
                // now formula becomes -> 3*q (5*(q-1)) * pr
            }
            dp[si][ei] = minAns;
        }
    }
    return dp[SI][EI];
}

public static void MCM()
{
    int[] arr = {40,20,30,10,30};
    int n = arr.length;

    int[][] dp = new int[n][n];
    // for(int[] d : dp)
    // Arrays.fill(d,-1);

    System.out.println(MCM_DP(arr,0,n-1,dp));
    print2D(dp);

}

//Minimum and Maximum Values of an Expression with '+' and '*' (GFG)
public static class pair{
    int minValue = (int)1e9;
    int maxValue = -(int)1e9;
    String minExpression = "";
    String maxExpression = "";

    pair()
    {}

    pair(int minValue,int maxValue)
    {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    pair(int minValue,int maxValue,String minExpression,String maxExpression)
    {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.minExpression = minExpression;
        this.maxExpression = maxExpression;
    }
}

public static int evaluate(int a,int b,char ch)
{
    if(ch == '+')
    return a + b;
    else
    return a * b;
}

public static pair minMaxEvalution_memo(String str,int si,int ei,pair[][] dp)
{
    if(si == ei)
    {
        int val = str.charAt(si) - '0';  // jab single no. bach jaaye last mei to vohi minValue and vohi maxValue
        return dp[si][ei] = new pair(val,val,val + "",val + "");
    }

    if(dp[si][ei] != null)
    return dp[si][ei];
    
    pair res = new pair();
    for(int cut = si+1; cut < ei; cut += 2)
    {
        pair lans = minMaxEvalution_memo(str,si,cut-1,dp);
        pair rans = minMaxEvalution_memo(str,cut+1,ei,dp);

        int minValue = evaluate(lans.minValue,rans.minValue,str.charAt(cut));
        int maxValue = evaluate(lans.maxValue,rans.maxValue,str.charAt(cut));

        // res.minValue = Math.min(res.minValue,minValue);
        // res.maxValue = Math.max(res.maxValue,maxValue);

        if(minValue < res.minValue) // jab res.minValue update to res.minExpr bhi update
        {
            res.minValue = minValue;
            res.minExpression = "(" + lans.minExpression + " " + str.charAt(cut) + " " + rans.minExpression + ")";
        }
        if(maxValue > res.maxValue)
        {
            res.maxValue = maxValue;
            res.maxExpression = "(" + lans.maxExpression + " " + str.charAt(cut) + " " + rans.maxExpression + ")";
        }
    }
    return dp[si][ei] = res;
}

public static void minMaxEvalution()
{
    String str = "1+2*3+4*5";
    int n = str.length();

    pair[][] dp = new pair[n][n];
    pair res = minMaxEvalution_memo(str,0,n-1,dp);

    System.out.println("MinValue : " + res.minValue + "\n MinExpression : " + res.minExpression);
    System.out.println("MaxValue : " + res.maxValue + "\n MaxExpression : " + res.maxExpression);

}

//Leetcode 132 -> Palindromic Partitioning II
public int minCut_memo(String s,int si,boolean[][] isPalindrome,int[] dp)
{
    if(isPalindrome[si][s.length()-1])
        return dp[si] = 0;
    if(dp[si] != -1)
        return dp[si];
    int minCuts = (int)1e9;
    for(int cut = si; cut < s.length(); cut++)
    {
        if(isPalindrome[si][cut])
            minCuts = Math.min(minCuts,minCut_memo(s,cut+1,isPalindrome,dp) + 1);
    }
    return dp[si] = minCuts;
}

public int minCut_DP(String s,int SI,boolean[][] isPalindrome,int[] dp)
{
    for(int si = s.length() -1; si >= 0; si--)
    {
        if(isPalindrome[si][s.length()-1])
        {
            dp[si] = 0;
            continue;
        }
            
        int minCuts = (int)1e9;
        for(int cut = si; cut < s.length(); cut++)
        {
            if(isPalindrome[si][cut])
                minCuts = Math.min(minCuts,dp[cut+1] + 1);
        }
        dp[si] = minCuts;
    }
    return dp[SI];
}

//Leetcode function
public int minCut(String s) 
{
    int n = s.length();
    int[] dp = new int[n];
    Arrays.fill(dp,-1);
        
    boolean[][] isPalindrome = new boolean[n][n]; // LPS vaali boolean ki dp
    for(int gap = 0; gap < n; gap++)
    {
        for(int i = 0, j = gap; j < n; i++,j++)
        {
            if(gap == 0)
                isPalindrome[i][j] = true;
            else if(gap == 1)
                isPalindrome[i][j] = s.charAt(i) == s.charAt(j);
            else
                isPalindrome[i][j] = s.charAt(i) == s.charAt(j) && isPalindrome[i+1][j-1];
        }
    }
        
    return minCut_memo(s,0,isPalindrome,dp);
    // return minCut_DP(s,0,isPalindrome,dp);
}

//Leetcode 312 -> Burst Balloons -> Important -> Post Order mei balloons burst kr rhe hai
public int maxCoins_memo(int[] arr,int si,int ei,int[][] dp)
{
    if(dp[si][ei] != -1)
        return dp[si][ei];
        
    int lval = (si - 1 == -1) ? 1 : arr[si-1];  // ek (si,ei) ki range ke liye same lval aur rval honge
    int rval = (ei + 1 == arr.length) ? 1 : arr[ei+1];
        
    int maxAns = 0;
    for(int cut = si; cut <= ei; cut++)
    {
        int lans = (cut == si) ? 0 : maxCoins_memo(arr,si,cut-1,dp); // if si pe hi cut maardenge to left call mei arr bachega nhi to return 0
        int rans = (cut == ei) ? 0 : maxCoins_memo(arr,cut+1,ei,dp); // same agr ei pe hi cut maardenge to
            
        maxAns = Math.max(maxAns,lans + lval * arr[cut] * rval + rans);
            
    }
    return dp[si][ei] = maxAns;
}

//Tabulation -> Gap Strategy -> Diagonall Filling
public int maxCoins_DP(int[] arr,int SI,int EI,int[][] dp)
{
    int n = arr.length;
    for(int gap = 0; gap < n; gap++)
    {
        for(int si = 0, ei = gap; ei < n; si++,ei++)
        {
            int lval = (si - 1 == -1) ? 1 : arr[si-1];
            int rval = (ei + 1 == arr.length) ? 1 : arr[ei+1];
        
            int maxAns = 0;
            for(int cut = si; cut <= ei; cut++)
            {
                int lans = (cut == si) ? 0 : dp[si][cut-1];
                int rans = (cut == ei) ? 0 : dp[cut+1][ei];
            
                maxAns = Math.max(maxAns,lans + lval * arr[cut] * rval + rans);
        
            }
            dp[si][ei] = maxAns;
        }
    }
    return dp[SI][EI];
}

//Leetcode function
public int maxCoins(int[] nums) 
{
    int n = nums.length;
    int[][] dp = new int[n][n];
    for(int[] d : dp)
        Arrays.fill(d,-1);
    return maxCoins_memo(nums,0,n-1,dp);

}

//Leetcode 1039 -> Minimum Score Triangulation of Polygon
public int minScoreTriangulation_memo(int[] arr,int si,int ei,int[][] dp)
{
    if(ei - si <= 1)
        return dp[si][ei] = 0;

    if(dp[si][ei] != -1)
        return dp[si][ei];

    int minAns = (int)1e9;
    for(int cut = si+1; cut < ei; cut++)
    {
        int lans = minScoreTriangulation_memo(arr,si,cut,dp);
        int rans = minScoreTriangulation_memo(arr,cut,ei,dp);
            
        minAns = Math.min(minAns,lans + arr[si] * arr[cut] * arr[ei] + rans);
    }
    return dp[si][ei] = minAns;
    
}

public int minScoreTriangulation_DP(int[] arr,int SI,int EI,int[][] dp)
{
    int n = arr.length;
    for(int gap = 0; gap < n; gap++)
    {
        for(int si = 0, ei = gap; ei < n; si++,ei++)
        {
            if(ei - si <= 1)
            {
                dp[si][ei] = 0;
                continue;
            }
                
            int minAns = (int)1e9;
            for(int cut = si+1; cut < ei; cut++)
            {
                int lans = dp[si][cut];
                int rans = dp[cut][ei];
            
                minAns = Math.min(minAns,lans + arr[si] * arr[cut] * arr[ei] + rans);
            }

        dp[si][ei] = minAns;
        }
    }
    return dp[SI][EI];
}


//Leetcode function
public int minScoreTriangulation(int[] values) 
{
    int n = values.length;
    int[][] dp = new int[n][n];
    for(int[] d : dp)
        Arrays.fill(d,-1);
        
    return minScoreTriangulation_memo(values,0,n-1,dp);
    // return minScoreTriangulation_DP(values,0,n-1,dp);

}

//Boolean Parenthization -> GFG
public static class pairBoolean{
    int trueWays = 0;   // No. of ways to make expression evaluate true 
    int falseWays = 0;  // No. of ways to make expression evaluate false

    pairBoolean(int trueWays,int falseWays)
    {
        this.trueWays = trueWays;
        this.falseWays = falseWays;
    }
}

//calculates the no of true and false ways based on the operator '&' '|' or '^'
static int mod = 1003;
public static pairBoolean evaluate(pairBoolean left,pairBoolean right,char operator)
{
    
    int totalWays = ((left.falseWays + left.trueWays) % mod * (right.falseWays + right.trueWays) % mod) % mod;

    pairBoolean ans = new pairBoolean(0,0);
    if(operator == '&')
    {
        ans.trueWays = (left.trueWays * right.trueWays) % mod;
        ans.falseWays = (totalWays - ans.trueWays + mod) % mod;
    }
    else if(operator == '|')
    {
        ans.falseWays = (left.falseWays * right. falseWays) % mod;
        ans.trueWays = (totalWays - ans.falseWays + mod) % mod;
    }
    else // for '^' (XOR) operator
    {
        ans.trueWays = ((left.falseWays * right.trueWays) % mod + (left.trueWays * right.falseWays) % mod) % mod;
        ans.falseWays = (totalWays - ans.trueWays + mod) % mod;
    }
    return ans;
}

public static pairBoolean booleanParen(String str,int si,int ei,pairBoolean[][] dp)
{
    if(si == ei)
    {
        char ch = str.charAt(si);
        return new pairBoolean(ch == 'T' ? 1 : 0, ch == 'F' ? 1 : 0);
    }

    if(dp[si][ei] != null)
    return dp[si][ei];

    pairBoolean myAns = new pairBoolean(0,0);
    for(int cut = si+1; cut < ei; cut += 2)
    {
        char operator = str.charAt(cut);
        pairBoolean lans = booleanParen(str,si,cut-1,dp);
        pairBoolean rans = booleanParen(str,cut+1,ei,dp);

        pairBoolean recAns = evaluate(lans,rans,operator);

        myAns.trueWays = (myAns.trueWays % mod + recAns.trueWays % mod) % mod;
        myAns.falseWays = (myAns.falseWays % mod + recAns.falseWays % mod) % mod;
    }

    return dp[si][ei] = myAns;
}

//GFG function
public static int countWays(int N, String S)
{
    pairBoolean[][] dp = new pairBoolean[N][N];
    pairBoolean ans = booleanParen(S,0,N-1,dp);
    
    return ans.trueWays;
}

public static void main(String[] args)
{
    // MCM();
    minMaxEvalution();
}
}