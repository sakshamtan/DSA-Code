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

public static void main(String[] args)
{
    MCM();
}
}