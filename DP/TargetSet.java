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

//Leetcode 322 -> Coin Change -> Tell minimum no of coins to make up amount
//As no of coins permu and combi mei same rehte hai so dono mei se koi bhi use kr skte hai(permu ki call simpler hoti hai to using that)
public int coinChange_memo(int[] coins,int amount,int[] dp)
{
    if(amount == 0)
        return dp[amount] = 0;  // 0 coins are used to make 0 target
        
    if(dp[amount] != (int)1e9)  // 1e8 ans ka part bn skta hai so 1e9 rkhni hai default value
        return dp[amount];
        
    int minCoins = (int)1e8;
    for(int ele : coins)
    {
        if(amount - ele >= 0)
            minCoins = Math.min(minCoins,coinChange_memo(coins,amount-ele,dp)+1);
    }
    return dp[amount] = minCoins;
}

//Tabulation
public int coinChange_DP(int[] coins,int Tar,int[] dp)
{
    dp[0] = 0;
    for(int amount = 1; amount <= Tar; amount++)
    {
        int minCoins = (int)1e8;
        for(int ele : coins)
        {
            if(amount - ele >= 0)
                minCoins = Math.min(minCoins,dp[amount-ele]+1);
        }
        dp[amount] = minCoins;      
    }
    return dp[Tar];        
}

//Leetcode function
public int coinChange(int[] coins, int amount) 
{
    int[] dp = new int[amount+1];
    // Arrays.fill(dp,(int)1e9); only for memo
        
    // int ans = coinChange_memo(coins,amount,dp);
    int ans = coinChange_DP(coins,amount,dp);
        
    return ans == (int)1e8 ? -1 : ans;        
}

// Number of Solutions of a linear equation of n variables
// 2p + 3q + 5r + 7s = 10 => coins(2,3,5,7) with target = 10 so coinChangeCombination
public static int noOfSolutions_DP(int[] coeff,int rhs)
{
    int[] dp = new int[rhs+1];
    dp[0] = 1;
    for(int ele : coeff)
    {
        for(int tar = 0; tar <= rhs; tar++)
        {
            if(tar - ele >= 0)
            dp[tar] += dp[tar-ele];
        }
    } 
    return dp[rhs];
}

//Now print the solutions of the given equation
public static int printSolutions(int[] coeff,int rhs,int aRHS,int idx,int[] freq)
{
    if(rhs == 0)
    {
        for(int i = 0; i < coeff.length; i++)
        {
            System.out.print(coeff[i] + "(" + freq[i] + ")" );
            if(i != coeff.length - 1)
            System.out.print(" + ");
        }
        System.out.println(" = " + aRHS);
        return 1;
    }

    int count = 0;
    for(int i = idx; i < coeff.length; i++)
    {
        if(rhs - coeff[i] >= 0)
        {
            freq[i]++; // konsa coin kitni baar use kr rhe hai ye store kr rhe hai
            count += printSolutions(coeff,rhs - coeff[i],aRHS,i,freq);
            freq[i]--;
        }
    }
    return count;
}

public static void numberOfSoltions()
{
    int[] coeff = {2,3,5,7};
    int rhs = 10;
    int[] freq = new int[coeff.length];

    // System.out.println(noOfSolutions_DP(coeff,rhs));
    System.out.println(printSolutions(coeff,rhs,rhs,0,freq));

}

//Subset Sum -> GFG
public static int subsetSum_memo(int[] arr,int n,int tar,int[][] dp)
{
    if(n == 0 || tar == 0)
    return dp[n][tar] = (tar == 0) ? 1 : 0;

    if(dp[n][tar] != -1)
    return dp[n][tar];

    boolean res = false;
    if(tar - arr[n-1] >= 0)
        res = res || (subsetSum_memo(arr,n-1,tar-arr[n-1],dp) == 1);
    res = res || (subsetSum_memo(arr,n-1,tar,dp) == 1);

    return dp[n][tar] = (res) ? 1 : 0;
}

//Tabulation -> defualt value ki need nhi to boolean ki hi dp bnaa skte hai
public static boolean subsetSum_DP(int[] arr,int Tar)
{
    int N = arr.length;
    boolean[][] dp = new boolean[N+1][Tar+1];

    for(int n = 0; n <= N; n++)
    {
        for(int tar = 0; tar <= Tar; tar++)
        {
            if(n == 0 || tar == 0)
            {
                dp[n][tar] = (tar == 0);
                continue;
            }

            if(tar - arr[n-1] >= 0)
                dp[n][tar] = dp[n][tar] || dp[n-1][tar-arr[n-1]];
            dp[n][tar] = dp[n][tar] || dp[n-1][tar];
        }
    }
    return dp[N][Tar];
}

//Now tell total no of ways to subset the arr that sums upto target(so idhr saari ways explore krni hai and count+= vaala concenpt use krna hai)
public static int subsetSumTotalWays_DP(int[] arr,int Tar)
{
    int N = arr.length;
    int[][] dp = new int[N+1][Tar+1];

    for(int n = 0; n <= N; n++)
    {
        for(int tar = 0; tar <= Tar; tar++)
        {
            if(n == 0 || tar == 0)
            {
                dp[n][tar] = (tar == 0) ? 1 : 0;
                continue;
            }

            if(tar - arr[n-1] >= 0)
                dp[n][tar] += dp[n-1][tar-arr[n-1]];
            dp[n][tar] +=  dp[n-1][tar];
        }
    }
    return dp[N][Tar];
}

public static void subsetSum(int[] arr,int tar)
{
    int n = arr.length;
    int[][] dp = new int[n+1][tar+1];
    for(int[] d : dp)
    Arrays.fill(d,-1);

    boolean res = subsetSum_memo(arr,n,tar,dp) == 1;
    System.out.println(res);
    print2D(dp);
}

//Leetcode 416 -> partition Equal Subset Sum -> uses subsetSum
public boolean canPartition(int[] nums) 
{
    int n = nums.length;
    int sum = 0;
    for(int ele : nums)
        sum += ele;
        
    if(sum % 2 != 0)  // agr arr ka sum odd aaya to usko do equal no.s mei partition nhi kr skte
        return false;
        
    int tar = sum/2;  // even aaya to sum/2 tar agr subsetSum bnaa paa rha hai means we can divide arr into two subsets with equal sums
    int[][] dp = new int[n+1][tar+1];
    for(int[] d : dp)
        Arrays.fill(d,-1);
    return subsetSum_memo(nums,n,tar,dp) == 1;
} 

//Leetcode 494 -> Target Sum
public int findTargetSumWays_recur(int[] arr,int n,int sum,int tar)
{
    if(n == 0)
        return (sum == tar) ? 1 : 0;
        
    int count = 0;
    count += findTargetSumWays_recur(arr,n-1,sum + arr[n-1],tar);
    count += findTargetSumWays_recur(arr,n-1,sum - arr[n-1],tar);
        
    return count;
}

//Leetcode function -> Through Recursion
public int findTargetSumWays_01(int[] nums, int target) 
{
    int n = nums.length;
    int arrSum = 0;
    for(int ele : nums)
        arrSum += ele;
    if(target > arrSum || target < -arrSum)
        return 0;
    return findTargetSumWays_recur(nums,n,0,target);
}

//Using 2D dp with origin shifting
public int findTargetSumWays_memo(int[] arr,int n,int sum,int tar,int[][] dp)
{
    if(n == 0)
        return dp[n][sum] = (sum == tar) ? 1 : 0;
        
    if(dp[n][sum] != -1)
        return dp[n][sum];
        
    int count = 0;
    count += findTargetSumWays_memo(arr,n-1,sum + arr[n-1],tar,dp);
    count += findTargetSumWays_memo(arr,n-1,sum - arr[n-1],tar,dp);
        
    return dp[n][sum] = count;
}

//Leetcode function -> Through memoization
public int findTargetSumWays_02(int[] nums, int target) 
{
    int n = nums.length;
    int arrSum = 0;
    for(int ele : nums)
        arrSum += ele;
    if(target > arrSum || target < -arrSum)
        return 0;
        
    int[][] dp = new int[n+1][2 * arrSum + 1];
    for(int[] d : dp)
        Arrays.fill(d,-1);
    return findTargetSumWays_memo(nums,n,0 + arrSum,target + arrSum,dp);
    //Shifting so sum so far gets shifted 0 + arrSum and target gets shifted to target + arrSum
}

public static void main(String[] args)
{
    // coinChangePermutation();
    // coinChangeCombination();
    // numberOfSoltions();
    subsetSum(new int[]{2,3,5,7},10);

}
}