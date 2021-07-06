public class CatalanNumber{
// CatalanNumber -> Applied on special questions of cutType -> where only structure / no of ways are required
// Catalan series = 1,1,2,5,14,42....
// Ye pattern observe krna hai Catalan Series vaala if observed then the question can be easily done with less complexity

//Leetcode 96 -> Unique Binary Search Trees
public int numTrees(int n) 
{
    int[] dp = new int[n+1];
    dp[0] = 1;
    for(int i = 1; i <= n; i++)
    {
        for(int j = i - 1, k = 0; j >= 0; j--,k++)
        {
            dp[i] += dp[j] * dp[k];
        }
    }
    return dp[n];
}
}