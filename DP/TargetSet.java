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

public static void coinChangePermutation()
{
    int[] arr = {2,3,5,7}; // coins
    int tar = 10;

    int[] dp = new int[tar+1];
    // Arrays.fill(dp,-1); only for memo

    // System.out.println(coinChangePermutation_memo(arr,tar,dp));
    System.out.println(coinChangePermutation_DP(arr,tar,dp));

    print1D(dp);
}

//A single coin can be picked infinite no. of times
public static int coinChangeCombination_memo(int[] arr,int li,int tar,int[][] dp)
{
    if(tar == 0)
    return dp[li][tar] = 1;

    if(dp[li][tar] != -1)
    return dp[li][tar];

    int count = 0;
    for(int i = li; i >= 0; i--)
    {
        if(tar - arr[i] >= 0)
        count += coinChangeCombination_memo(arr,i,tar-arr[i],dp);
    }
    return dp[li][tar] = count;
}

//Tabulation is better in this question than memo as not much repetition of calls is taking place
public static int coinChangeCombination_DP(int[] arr,int Li,int Tar,int[][] dp)
{
    for(int li = 0; li <= Li; li++)
    {
        for(int tar = 0; tar <= Tar; tar++)
        {
            if(tar == 0)
            {
                dp[li][tar] = 1;
                continue;
            }

            for(int i = li; i >= 0; i--)  // coins pr peeche se chla rhe hai loop taaki 0,0 pr base case bne
            {
                if(tar - arr[i] >= 0)
                dp[li][tar] += dp[i][tar-arr[i]];
            }
        }
    }
    return dp[Li][Tar];
}

//optimized version of coinChangeCombination_DP as here we are just using 1D dp
public static int coinChangeCombination_1DP(int[] arr,int Tar,int[] dp)
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

public static void coinChangeCombination()
{
    int[] arr = {2,3,5,7};
    int tar = 10;
    // int[] dp = new int[tar+1];
    int[][] dp = new int[arr.length][tar+1];
    // for(int[] d : dp)
    // Arrays.fill(d,-1); only for memo

    // System.out.println(coinChangeCombination_memo(arr,arr.length-1,tar,dp));
    System.out.println(coinChangeCombination_DP(arr,arr.length-1,tar,dp));
    // System.out.println(coinChangeCombination_1DP(arr,tar,dp));

    // print1D(dp);
    print2D(dp);
}

//Leetcode 377 -> Combination Sum IV -> CoinChangePermutation_Infi
public int coinChangePermu(int[] arr,int Tar,int[] dp)
{
    dp[0] = 1;
    for(int tar = 0; tar <= Tar; tar++)
    {
        for(int ele : arr)
        {
            if(tar - ele >= 0)
                dp[tar] += dp[tar-ele];
        }
    }
    return dp[Tar];
}

//Leetcode function
public int combinationSum4(int[] nums, int target) 
{
    int[] dp = new int[target+1];
    return coinChangePermu(nums,target,dp);
}

//Leetcode 518 -> Coin Change 2 -> CoinChangeCombinations_Infi
// Solved using 1D dp -> most optimized
public int coinChange(int Tar,int[] coins,int[] dp)
{
    dp[0] = 1;
    for(int ele : coins)
    {
        for(int tar = 0; tar <= Tar; tar++)
        {
            if(tar - ele >= 0)
                dp[tar] += dp[tar-ele];
        }
    }
    return dp[Tar];
}

//Leetcode function
public int change(int amount, int[] coins) 
{
    int[] dp = new int[amount+1];
    return coinChange(amount,coins,dp);
}

public static void main(String[] args)
{
    // coinChangePermutation();
    coinChangeCombination();
}
}