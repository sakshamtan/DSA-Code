import java.util.ArrayList;
import java.util.Arrays;

public class LIS{

//Leetcode 300 -> Longest Increasing Subsequence
// LIS left to right (memo approach)
public int LIS_memo(int[] arr,int i,int[] dp)
{
    if(dp[i] != 0)
        return dp[i];
        
    int longestLen = 1;
    for(int j = i - 1; j >= 0; j--)    
    {
        if(arr[j] < arr[i])
        {
            int len = LIS_memo(arr,j,dp) + 1;   // j-th index tk LIS
            longestLen = Math.max(longestLen,len);
        }
    }
    return dp[i] = longestLen;
}

//Leetcode function for memo
public int lengthOfLIS(int[] nums) 
{
    int n = nums.length;
    int[] dp = new int[n];
        
    int len = 0;
    for(int i = 0; i < n; i++)
    {
        len = Math.max(len,LIS_memo(nums,i,dp)); // poore arr ka LIS
    }
    return len;
}

//LIS -> Left To Right Tabulation approach
public int LIS_DP(int[] arr,int[] dp)
{
    int len = 0;
    for(int i = 0; i < arr.length; i++)
    {
        dp[i] = 1; // initially dp mei har jagah 1 hoga as ek ele bhi to LIS hai
        for(int j = i - 1; j >= 0; j--)
        {
            if(arr[j] < arr[i])
            {
                dp[i] = Math.max(dp[i],dp[j] + 1); // dp[j] mei j tk ki LIS ki length pdhi hai
            }
        }
        len = Math.max(len,dp[i]); // saath saath update krlia maxLen
    }
    return len; // final Ans
}

// Minimum Deletions to be perfomed to sort an array = arr.length - LIS
public int minDeletions(int[] arr)
{
    int n = arr.length;
    int[] dp = new int[n];

    int len = 0;
    for(int i = 0; i < arr.length; i++)  // finding LIS
    {
        dp[i] = 1;
        for(int j = i - 1; j >= 0; j--)
        {
            if(arr[j] <= arr[i]) // bas isme equal hone pr bhi no.s bhi LIS bnaa skte hai
            {
                dp[i] = Math.max(dp[i],dp[j] + 1);
            }
        }
        len = Math.max(len,dp[i]);
    }
    return n - len; // minDeletions = arr.length - LIS in that array
}

//Maximum Sum Increasing Subsequence -> GFG (max sum of increasing subsequnce return krna hai hai ab)
public int maxSumIS(int arr[], int n)  
{
    int[] dp = new int[n];
    int maxSum = 0;
    for(int i = 0; i < n; i++)
    {
        dp[i] = arr[i]; // by defualt arr[i] (base case of recursion)
        for(int j = i - 1; j >= 0; j--)
        {
            if(arr[j] < arr[i])
            {
                dp[i] = Math.max(dp[i],dp[j] + arr[i]);
            }
        }
        maxSum = Math.max(maxSum,dp[i]);
    }
    return maxSum;
}

//Longest Bitonic Subsequence -> Uses LIS_LR + LIS_RL(LDS)
public static int[] LIS_LR(int[] arr)  // LIS_LR se dp return krwa rhe hai
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

//LIS_RL is equivalent to LDS(longest decreasing subsequence)
public static int[] LIS_RL(int[] arr)
{
    int n = arr.length;
    int[] dp = new int[n];

    for(int i = n - 1; i >= 0; i--) // for loops ke direction opposite krdi faith same rkhi
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

//GFG function
public static int LBS_uphill(int[] arr) // uphill means first increasing then decreasing subsequence
{
    int[] LIS = LIS_LR(arr);
    int[] LDS = LIS_RL(arr);

    int len = 0;
    for(int i = 0; i < arr.length; i++)
    {
        len = Math.max(len,LIS[i] + LDS[i] - 1);
    }
    return len;
}

//Follow up to LBS -> Now tell LBS_downhill -> First decreasing then increasing
public static int[] LDS_LR(int[] arr)  
{
    int n = arr.length;
    int[] dp = new int[n];

    for(int i = 0; i < n; i++)
    {
        dp[i] = 1;
        for(int j = i - 1; j >= 0; j--)
        {
            if(arr[j] > arr[i]) // is check ka difference hai bs LIS_LR mei and LDS_LR mei
            dp[i] = Math.max(dp[i],dp[j] + 1);
        }
    }
    return dp;
}

public static int[] LDS_RL(int[] arr)
{
    int n = arr.length;
    int[] dp = new int[n];

    for(int i = n - 1; i >= 0; i--) 
    {
        dp[i] = 1;
        for(int j = i + 1; j < n; j++)
        {
            if(arr[j] > arr[i]) // is check ka difference hai LIS_RL mei and LDS_RL mei
            dp[i] = Math.max(dp[i],dp[j] + 1);
        }
    }
    return dp;
}

//LBS_downhill uses -> LDS_LR + LDS_RL -> Due to faith hume LIS end point pr khtm hone vaala milta hai to us hisaab se graph ki convention set kri
public static int LBS_downhill(int[] arr)
{
    int[] LDS = LDS_LR(arr);
    int[] LIS = LDS_RL(arr);

    int len = 0;
    for(int i = 0; i < arr.length; i++)
    {
        len = Math.max(len,LDS[i] + LIS[i] - 1);
    }
    return len;
}

//Leetcode 673 -> Number of Longest Increasing Subsequence -> Count no of LIS
public int findNumberOfLIS(int[] arr) 
{
    int n = arr.length;
    int[] dp = new int[n];
    int[] count = new int[n];
        
    int maxLen = 0;
    int maxCount = 0;
        
    for(int i = 0; i < n; i++)
    {
        dp[i] = 1;
        count[i] = 1;
        for(int j = i - 1; j >= 0; j--)
        {
            if(arr[j] < arr[i])
            {
                if(dp[j] + 1 > dp[i]) // greater length ki lis mil gyi to dono dp, count update
                {
                    dp[i] = dp[j] + 1;
                    count[i] = count[j]; // ab badi len ki lis hi matter kregi to uske count se update
                }
                else if(dp[j] + 1 == dp[i]) // equal length ki lis mil gyi to sirf count add
                {
                    count[i] += count[j];
                }
            }
        }
        if(dp[i] > maxLen) // maxLen , maxCount saath saath update according to situations
        {
            maxLen = dp[i];
            maxCount = count[i];
        }
        else if(dp[i] == maxLen)
        {
            maxCount += count[i];
        }
    }
    return maxCount;
}

//Follow up -> print all the LIS found in the above question
//DFS call on len and idx (on mapping)
public void allLIS(ArrayList<ArrayList<Integer>> mapping,int[] arr,int idx,int len,String ans)
{
    if(len == 1)
    {
        System.out.println(ans + arr[idx]);
        return;
    }

    for(Integer i : mapping.get(len-1))
    {
        if(i < idx && arr[i] < arr[idx]) // i -> n-1 len ke liye ; idx -> n len of LIS ke liye hai
        {
            allLIS(mapping,arr,i,len-1,ans + arr[idx] + " , ");
        }
    }
}

//LIS vaali dp pr back-Engineering lagaaenge
public void findAllLIS(int[] arr)
{
    int n = arr.length;
    int[] dp = new int[n]; // LIS vaali dp maintain krenge.
    int len = 0; // len of longest Increasing Subseq. in arr

    for(int i = 0; i < n; i++)
    {
        dp[i] = 1;
        for(int j = i - 1; j >= 0; j--)
        {
            if(arr[j] < arr[i])
            {
                dp[i] = Math.max(dp[i],dp[j] + 1);
            }
        }
        len = Math.max(len,dp[i]);
    }

    ArrayList<ArrayList<Integer>> mapping = new ArrayList<>(); // len vs indexes of that length (graph type structure).
    for(int i = 0; i <= len; i++) // len ko as index in AL use krna hai
        mapping.add(new ArrayList<>());

    for(int i = 0; i < n; i++)
        mapping.get(dp[i]).add(i);  // dp[i] pr len pdhi hai uske against i push krdia.
    
    for(Integer i : mapping.get(len))
        allLIS(mapping,arr,i,len,"");
}

//Building Bridges -> GFG
// input arr -> {{sp1,ep1},{sp2,ep2}.......}
public static int buildingBridges(int[][] arr)
{
    Arrays.sort(arr,(a,b)->{
        return a[0] - b[0];  // sp ke basis pr arr sort kra
    });

    int n = arr.length;
    int[] dp = new int[n];
    int len = 0;

    for(int i = 0; i < n; i++)
    {
        dp[i] = 1;
        for(int j = i - 1; j >= 0; j--)
        {
            if(arr[j][0] < arr[i][0] && arr[j][1] < arr[i][1])
            dp[i] = Math.max(dp[i],dp[j] + 1);
        }
        len = Math.max(dp[i],len);
    }
    return len; // max parallel bridges ->  LIS on ep after sorting on sp
}

//Leetcode 354 -> Russian Doll Envelopes -> same as building briges (chotte height and width ke env. bade height and width vaale env. ke andar daalne hai)
public int maxEnvelopes(int[][] envelopes) 
{
    Arrays.sort(envelopes,(a,b)->{ 
        return a[0] - b[0]; // height ke basis pr sort then width pr LIS to find sorted order w.r.t both height and width
    });
        
    int n = envelopes.length;
    int[] dp = new int[n];
    int maxLen = 0;
        
    for(int i = 0; i < n; i++)
    {
        dp[i] = 1;
        for(int j = i - 1; j >= 0; j--)
        {
            if(envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1])
            {
                dp[i] = Math.max(dp[i],dp[j] + 1);
            }
        }
        maxLen = Math.max(dp[i],maxLen);
    }
    return maxLen;
}
}