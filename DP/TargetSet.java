import java.util.Arrays;

public class TargetSet{


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

//A single coin can be picked infinite no. of times
public static int coinChangePermutation_memo(int[] arr,int tar,int[] dp)
{
    if(tar == 0)
    return dp[tar] = 1;

    if(dp[tar] != -1) // 0 ans ka part bn raha hai
    return dp[tar];

    int count  = 0;
    for(int ele : arr)
    {
        if(tar - ele >= 0)
        count += coinChangePermutation_memo(arr,tar-ele,dp);
    }
    return dp[tar] = count;
}

public static int coinChangePermutation_DP(int[] arr,int Tar,int[] dp)
{
    dp[0] = 1;  // base case hamesha dp[0] mei 1 daalna hi hai
    for(int tar = 1; tar <= Tar; tar++)
    {
        for(int ele : arr)
        {
            if(tar - ele >= 0)
            dp[tar] += dp[tar-ele];
        }
    }
    return dp[Tar];
}

//A single coin can be picked infinite no. of times
public static int coinChangeCombination_DP(int[] arr,int Tar,int[] dp)
{
    dp[0] = 1;
    for(int ele : arr) // ek coin ke saare tar bnane ke options exhaust then agle coin pe move kia
    {
        for(int tar = ele; tar <= Tar; tar++) // present coin apne se bade tar hi bna paayega so loop tar = ele se start kia
        {
            if(tar - ele >= 0)
            dp[tar] += dp[tar-ele];
        }
    }
    return dp[Tar];
}

public static void coinChange()
{
    int[] arr = {2,3,5,7}; // coins
    int tar = 10;

    int[] dp = new int[tar+1];
    // Arrays.fill(dp,-1); only for memo

    // System.out.println(coinChangePermutation_memo(arr,tar,dp));
    // System.out.println(coinChangePermutation_DP(arr,tar,dp));
    System.out.println(coinChangeCombination_DP(arr,tar,dp));


    print1D(dp);
}

public static void main(String[] args)
{
    coinChange();
}
}