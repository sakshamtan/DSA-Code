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

public static void main(String[] args)
{
    // MCM();
    minMaxEvalution();
}
}