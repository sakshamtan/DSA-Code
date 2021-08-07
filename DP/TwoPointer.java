import java.util.Arrays;
import java.util.LinkedList;

public class TwoPointer{

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
    System.out.println();
}

//Memoization converted from recursive
public static int fib_memo(int n,int[] dp)
{
    if(n <= 1)
    return dp[n] = n;  // return krte hi store 

    if(dp[n] != -1)  // agr us state ka ans pehle se calculate hogya hai to vohi return call maarne se pehle hi
    return dp[n];

    int ans = fib_memo(n-1,dp) + fib_memo(n-2,dp);

    return dp[n] = ans;  // return maarte hi store
}

//Tabulation converted from memoization
public static int fib_DP(int N,int[] dp)
{
    for(int n = 0; n <= N; n++)
    {
        if(n <= 1)
        {
            dp[n] = n;
            continue;  // return ki jagah continue
        }

        int ans = dp[n-1] + dp[n-2];  // change of faith recursive call ki jagah dp array mei store

        dp[n] = ans; // return krne ki jagah store and continue(yaha need nhi hai khud ho jaayega)
    }
    return dp[N];  // Nth index of dp pr humaara ans calculate hokr stored hoga
}

//Tabulation ko aur optimize krlia not using array so space optimization as sirf previous do states ke ans ki need hoti hai.
// Most optimized.
public static int fib_twoPointer(int N)
{
    int a = 0, b = 1;
    for(int i = 0; i < N; i++)
    {
        // System.out.print(a + " ");
        int sum = a + b;
        a = b;  // moving pointers forward.
        b = sum; // a and b contain ans of previous two states 
    }
    return a;
}

public static void fibo()
{
    int n = 8;
    int[]dp = new int[n+1];  // n + 1 size ka dp as n ka ans nth index pr hi milega.
    Arrays.fill(dp,-1);  // -1 khi aayega nhi tree mei.

    System.out.println(fib_memo(n,dp));
    System.out.println(fib_DP(n,dp));
    System.out.println(fib_twoPointer(n));

    print1D(dp);
}

public static int mazePath_memo(int sr,int sc,int er,int ec,int[][] dp)
{
    if(sr == er && sc == ec)
    {
        return dp[sr][sc] = 1;
    }    

    if(dp[sr][sc] != 0)  // 0 khi nhi aata calculation mei so vohi defualt value rkhi.
    return dp[sr][sc];

    int count = 0;
    if(sr + 1 <= er)
    count += mazePath_memo(sr+1,sc,er,ec,dp);

    if(sc + 1 <= ec)
    count += mazePath_memo(sr,sc+1,er,ec,dp);

    if(sr + 1 <= er && sc + 1 <= ec)
    count += mazePath_memo(sr+1,sc+1,er,ec,dp);

    return dp[sr][sc] = count;
}

public static int mazePath_DP(int SR,int SC,int er,int ec,int[][] dp)
{
    for(int sr = er; sr >= 0; sr--)
    {
        for(int sc = ec; sc >= 0; sc--)
        {
            if(sr == er && sc == ec)
            {
                dp[sr][sc] = 1;
                continue;
            }

            int count =  0;
            if(sr + 1 <= er)
            count += dp[sr+1][sc];

            if(sc + 1 <= ec)
            count += dp[sr][sc+1];

            if(sr + 1 <= er && sc + 1 <= ec)
            count += dp[sr+1][sc+1];

            dp[sr][sc] = count;
        }
    }
    return dp[SR][SC];
}

//Now multi-moves are allowed
public static int mazePathInfi_memo(int sr,int sc,int er,int ec,int[][] dp)
{
    if(sr == er && sc == ec)
    return dp[sr][sc] = 1;

    if(dp[sr][sc] != 0)
    return dp[sr][sc];

    int count = 0;
    for(int jump = 1; sr + jump <= er; sr++)
    count += mazePathInfi_memo(sr + jump,sc,er,ec,dp);

    for(int jump = 1; sc + jump <= ec; jump++)
    count += mazePathInfi_memo(sr,sc + jump,er,ec,dp);

    for(int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
    count += mazePathInfi_memo(sr + jump, sc + jump,er,ec,dp);

    return dp[sr][sc] = count;
}

public static int mazePathInfi_DP(int SR,int SC,int er,int ec,int[][] dp)
{
    for(int sr = er; sr >= 0; sr--)
    {
        for(int sc = ec; sc >= 0; sc--)
        {
            if(sr == er && sc == ec)
            {
                dp[sr][sc] = 1;
                continue;
            }

            int count = 0;
            for(int jump = 1; sr + jump <= er; jump++)
            count += dp[sr + jump][sc];

            for(int jump = 1; sc + jump <= ec; jump++)
            count += dp[sr][sc + jump];

            for(int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
            count += dp[sr + jump][sc + jump];

            dp[sr][sc] = count;
        }
    }
    return dp[SR][SC];
}

public static void mazePath()
{
    int n = 5;
    int m = 5;
    int[][] dp = new int[n][m];

    // System.out.println(mazePath_memo(0,0,n-1,m-1,dp));
    // System.out.println(mazePath_DP(0,0,n-1,m-1,dp));
    // System.out.println(mazePath_memo(0,0,n-1,m-1,dp));
    System.out.println(mazePathInfi_DP(0,0,n-1,m-1,dp));

    print2D(dp);
}

public static int boardPath_memo(int sp,int ep,int[] dp)
{
    if(sp == ep)
    {
        return dp[sp] = 1;
    }
    if(dp[sp] != 0)
    return dp[sp];

    int count = 0;
    for(int dice = 1;dice <= 6 && sp + dice <= ep; dice++)
    {
        count += boardPath_memo(sp + dice,ep,dp);
    }

    return dp[sp] = count;
}

//Time -> O(6n) Space -> O(n)
public static int boardPath_DP(int SP,int ep,int[] dp)
{
    for(int sp = ep; sp >= 0; sp--)
    {
        if(sp == ep)
        {
            dp[sp] = 1;
            continue;
        }

        int count = 0;
        for(int dice = 1; dice <= 6 && sp + dice <= ep; dice++)
        count += dp[sp + dice];

        dp[sp] = count;
    }
    return dp[SP];
}

// Time -> O(n) Space -> O(6)
public static int boardPath_opti(int sp,int ep)
{
    LinkedList<Integer> list = new LinkedList<>();

    for(sp = ep; sp >= 0; sp--)
    {
        if(sp >= ep - 1)  // initially pehle do '1' add krne hai list mei
        list.addFirst(1);
        else{
            if(list.size() <= 6)
            list.addFirst(list.getFirst() * 2);  // agr list ka size <= 6 then first ele * 2 should be added in the list
            else
            list.addFirst(list.getFirst() * 2 - list.removeLast()); // if size > 6 then ele * 2 - lastNode(removed).
        }
    }
    return list.getFirst();
}

public static void boardPath()
{
    int n = 10;
    int[] dp = new int[n+1];

    // System.out.println(boardPath_memo(0,10,dp));
    System.out.println(boardPath_opti(0,10));

    // print1D(dp);
}

//Leetcode 70 -> Climbing Stairs
public static int climbStairs_memo(int n,int[] dp)
{
    if(n <= 1)
    {
        return dp[n] = 1;
    }

    if(dp[n] != 0)
    return dp[n];

    return dp[n] = climbStairs_memo(n-1,dp) + climbStairs_memo(n-2,dp);
}

public static int climbStairs_DP(int N,int[] dp)
{
    for(int n = 0; n <= N; n++)
    {
        if(n <= 1)
        {
            dp[n] = 1;
            continue;
        }

        dp[n] = dp[n-1] + dp[n-2];

    }
    return dp[N];
}

//Leetcode function
public static int climbStairs(int n)
{
    int[] dp = new int[n+1];
    // int ans = climbStairs_memo(n,dp);
    int ans = climbStairs_DP(n,dp);
    print1D(dp);
    return ans;
}

//Leetcode 746 -> Minimum Cost Climbing Stairs
public static int minCostClimbingStairs_memo(int n,int[] cost,int[] dp)
{
    if(n <= 1)
    return dp[n] = cost[n];

    if(dp[n] != -1)
    return dp[n];

    int minCost = Math.min(minCostClimbingStairs_memo(n-1,cost,dp),minCostClimbingStairs_memo(n-2,cost,dp));

    return dp[n] = minCost + (n == cost.length ? 0 : cost[n]); // agr top pr pohoch gye to cost 0 return krni hai.
}

public static int minCostClimbingStairs_DP(int N,int[] cost,int[] dp)
{
    for(int n = 0; n <= N; n++) // right to left recursive call so ans left to right bnega
    {
        if(n <= 1)
        {
            dp[n] = cost[n];
            continue;
        }

        int minCost = Math.min(dp[n-1],dp[n-2]);

        dp[n] = minCost + (n == cost.length ? 0 : cost[n]);
    }
    return dp[N];
}

public static int minCostClimbingStairs_opti(int[] cost)
{
    int a = cost[0];
    int b = cost[1];

    for(int i = 2; i < cost.length;i++)
    {
        int minCost = Math.min(a,b) + cost[i];

        a = b;
        b = minCost;
    }
    return Math.min(a,b);
}

//leetcode function
public static int minCostClimbingStairs(int[] cost)
{
    int n = cost.length;
    int[] dp = new int[n+1];
    Arrays.fill(dp,-1);  //for memo.

    return minCostClimbingStairs_DP(n,cost,dp);
}

//Friends pairing problem -> GFG
static long mod = (long)1e9 + 7;
public static long countFriendsPairing_memo(int n,long[] dp)
{
    if(n <= 1)
    return dp[n] = 1;

    if(dp[n] != 0)
    return dp[n];

    long single = countFriendsPairing_memo(n-1,dp);
    long pairup = countFriendsPairing_memo(n-2,dp) * (n-1);  // (n-1) pairs bnenge n logo ke and un sbke no. of ways same honge.

    return dp[n] = (single % mod + pairup % mod) % mod;  //(a+b)%c = (a%c + b%c) % c
}

public static long countFriendsPairing_DP(int N,long[] dp)
{
    for(int n = 0; n <= N; n++)
    {
        if(n <= 1)
        {
            dp[n] = 1;
            continue;
        }

        long single = dp[n-1];
        long pairup = dp[n-2] * (n-1);

        dp[n] = (single % mod + pairup % mod) % mod;
    }
    return dp[N];
}

public static int printFriendsPairing(String friends,String ans)
{
    if(friends.length() == 0)
    {
        System.out.println(ans);
        return 1;
    }
    int count = 0;
    char ch = friends.charAt(0);

    count += printFriendsPairing(friends.substring(1),ans + ch);

    for(int i = 1; i < friends.length(); i++)
    {
        String rstr = friends.substring(1,i) + friends.substring(i+1);
        count += printFriendsPairing(rstr,ans + ch + friends.charAt(i) + " ");
    }
    return count;
}

//GFG function
public static long countFriendsPairing(int n)
{
    long[] dp = new long[n+1];
    return countFriendsPairing_DP(n,dp);
    // System.out.println(printFriendsPairing("ABCDEF",""));
    
}

//GoldMine problem -> GFG
public static int goldMine_memo(int r,int c,int[][] mat,int[][] dp,int[][] dir)
{
    if(c == mat[0].length-1)
    return dp[r][c] = mat[r][c];
        
    if(dp[r][c] != -1)
    return dp[r][c];
        
    int maxGold = 0;
    for(int d = 0; d < 3; d++)
    {
        int x = r + dir[d][0];
        int y = c + dir[d][1];
            
        if(x >= 0 && x < mat.length)
        maxGold = Math.max(maxGold,goldMine_memo(x,y,mat,dp,dir));
    }
    return dp[r][c] = maxGold + mat[r][c];
}

public static int goldMine_DP(int[][] mat,int[][] dp,int[][] dir)
{
    int n = mat.length;
    int m = mat[0].length;
    for(int c = m-1; c >= 0; c--)
    {
        for(int r = n-1; r >= 0; r--)
        {
            if(c == m-1)
            {
                dp[r][c] = mat[r][c];
                continue;
            }

            int maxGold = 0;
            for(int d = 0; d < 2; d++)
            {
                int x = r + dir[d][0];
                int y = c + dir[d][1];
            
                if(x >= 0 && x < mat.length)
                maxGold = Math.max(maxGold,dp[x][y]);
            }
            dp[r][c] = maxGold + mat[r][c];
        }
    }
    int ans = 0;
    for(int i = 0; i < n; i++) // first column mei sabke maxGolds aagye honge unme se max humaara ans hai.
    {
        ans = Math.max(ans,dp[i][0]);
    }
    return ans;
}

//GFG function
public static int goldMine(int n, int m, int mat[][])    
{
    int[][] dp = new int[n][m];
    for(int[] d : dp)
    Arrays.fill(d,-1);

    int[][] dir = new int[][]{{-1,1},{0,1},{1,1}};
    int maxGold = 0;
    for(int i = 0; i < n; i++)
    {
        maxGold = Math.max(maxGold,goldMine_memo(i,0,mat,dp,dir));  // for memo call.
    }
    return maxGold;
}

//Leetcode 91 -> Decode Ways
public static int numDecodings_memo(String s,int idx,int[] dp)
{
    if(idx == s.length())
    return dp[idx] = 1;

    if(dp[idx] != -1)
    return dp[idx];

    char ch1 = s.charAt(idx);

    if(ch1 == '0')
    return dp[idx] = 0;
    int count = 0;
    count += numDecodings_memo(s,idx+1,dp); // single char lene ki call

    if(idx < s.length()-1)
    {
        char ch2 = s.charAt(idx+1);
        int num = (ch1-'0') * 10 + (ch2-'0');
        if(num <= 26)
        count += numDecodings_memo(s,idx+2,dp); // double char / 2 digit num lene ki call
    }
    return dp[idx] = count;
}

public static int numDecodings_DP(String s,int IDX,int[] dp)
{
    for(int idx = s.length(); idx >= 0; idx--)
    {
        if(idx == s.length())
        {
            dp[idx] = 1;
            continue;
        }

        char ch1 = s.charAt(idx);

        if(ch1 == '0')
        {
            dp[idx] = 0;
            continue;
        }

        int count = 0;
        count += dp[idx+1];

        if(idx < s.length()-1)
        {
            char ch2 = s.charAt(idx+1);
            int num = (ch1-'0') * 10 + (ch2-'0');
            if(num <= 26)
            count += dp[idx+2];
        }
        dp[idx] = count;
    }
    return dp[IDX];  // return dp[0];
}

//Optimized version of numDecodings using two pointers
public static int numDecodings_Opti(String s)
{
    int a = 1, b = 0;
    for(int idx = s.length() - 1; idx >= 0; idx--)
    {
        int count = 0;
        char ch1 = s.charAt(idx);
        if(ch1 != '0')
        {
            count += a; // agr single digit char '0' naa ho tbh a add krna hai count/sum mei

            if(idx < s.length() - 1)
            {
                char ch2 = s.charAt(idx+1);
                int num = (ch1-'0') * 10 + (ch2-'0');
                if(num <= 26)
                count += b; // agr double digit char 26 se chotta ho tbh hi b add krna hai count mei.
            }
        }
        b = a;
        a = count;
    }
    return a;
}

//Leetcode function
public static int numDecodings(String s)
{
    int[] dp = new int[s.length() + 1]; // base case mei s.length tk chla rhe hai.
    Arrays.fill(dp,-1); // as '0' part bn rha hai ans ka (for memo)

    return numDecodings_memo(s,0,dp);
    // return numDecodings_DP(s,0,dp);
    // return numDecodings_Opti(s);
}

//Leetcode 639 -> Decode Ways II
// long mod = (long)1e9 + 7;  // ans can be large enough so 1e9 + 7 se mod krke return krna hai.
public long numDecodingsII_memo(String s,int idx,long[] dp)
{
    if(idx == s.length())
        return dp[idx] = 1;
        
    if(dp[idx] != -1)
        return dp[idx];
        
    char ch1 = s.charAt(idx);

    if(ch1 == '0')
        return dp[idx] = 0;
        
    long count = 0;
    if(ch1 == '*')   
    {
        count = (count + 9 * numDecodingsII_memo(s,idx+1,dp)) % mod;  // '*' single * vaala case
        if(idx < s.length() - 1)
        {
            char ch2 = s.charAt(idx+1);
            if(ch2 == '*')
                count = (count + 15 * numDecodingsII_memo(s,idx+2,dp)) % mod;  // '**' vaala case
            else{
                if(ch2 >= '0' && ch2 <= '6')
                    count = (count + 2 * numDecodingsII_memo(s,idx+2,dp)) % mod; // '*N' where N belongs to [0,6]
                else if(ch2 > '6')
                    count = (count + numDecodingsII_memo(s,idx+2,dp)) % mod; // '*N' where N belongs to [7,9]
            }
        }  
    }
    else{
        count = (count + numDecodingsII_memo(s,idx+1,dp)) % mod; // Single No waala case -> 'N'
        if(idx < s.length() - 1)
        {
            char ch2 = s.charAt(idx+1);
            if(ch2 == '*')
            {
                if(ch1 == '1')
                    count = (count + 9 * numDecodingsII_memo(s,idx+2,dp)) % mod; // 'N*' where N is 1
                else if(ch1 == '2')
                    count = (count + 6 * numDecodingsII_memo(s,idx+2,dp)) % mod; // 'N*' where N is 2
            }
            else{
                int num = (ch1-'0') * 10 + (ch2-'0');
                if(num <= 26)
                    count = (count + numDecodingsII_memo(s,idx+2,dp)) % mod; // 'NN' case -> dono hi no. aagye.
            }
        } 
    }
    return dp[idx] = count;
}

public long numDecodingsII_DP(String s,int IDX,long[] dp)
{
    for(int idx = s.length(); idx >= 0; idx--)
    {
        if(idx == s.length())
        {
            dp[idx] = 1;
            continue;
        }
        
        char ch1 = s.charAt(idx);
        if(ch1 == '0')
        {
            dp[idx] = 0;
            continue;
        }
        
        long count = 0;
        if(ch1 == '*')
        {
            count = (count + 9 * dp[idx+1]) % mod;
            if(idx < s.length() - 1)
            {
                char ch2 = s.charAt(idx+1);
                if(ch2 == '*')
                    count = (count + 15 * dp[idx+2]) % mod;
                else{
                    if(ch2 >= '0' && ch2 <= '6')
                        count = (count + 2 * dp[idx+2]) % mod;
                    else if(ch2 > '6')
                        count = (count + dp[idx+2]) % mod;
                }
            }  
        }
        else{
            count = (count + dp[idx+1]) % mod;
            if(idx < s.length() - 1)
            {
                char ch2 = s.charAt(idx+1);
                if(ch2 == '*')
                {
                    if(ch1 == '1')
                        count = (count + 9 * dp[idx+2]) % mod;
                    else if(ch1 == '2')
                        count = (count + 6 * dp[idx+2]) % mod;
                }
                else{
                    int num = (ch1-'0') * 10 + (ch2-'0');
                    if(num <= 26)
                        count = (count + dp[idx+2]) % mod;
                }
            } 
        }
        dp[idx] = count;
    }
    return dp[IDX];
}

public long numDecodingsII_Opti(String s)
{
    long a = 1 , b = 0;
    for(int idx = s.length()-1; idx >= 0; idx--)
    {
        long count = 0;
        char ch1 = s.charAt(idx);
        if(ch1 != '0')
        {           
            if(ch1 == '*')
            {
                count = (count + 9 * a) % mod;
                if(idx < s.length() - 1)
                {
                    char ch2 = s.charAt(idx+1);
                    if(ch2 == '*')
                    count = (count + 15 * b) % mod;
                    else{
                    if(ch2 >= '0' && ch2 <= '6')
                        count = (count + 2 * b) % mod;
                    else if(ch2 > '6')
                        count = (count + b) % mod;
                    }
                }  
            }
        else{
            count = (count + a) % mod;
            if(idx < s.length() - 1)
            {
                char ch2 = s.charAt(idx+1);
                if(ch2 == '*')
                {
                    if(ch1 == '1')
                        count = (count + 9 * b) % mod;
                    else if(ch1 == '2')
                        count = (count + 6 * b) % mod;
                }
                else{
                    int num = (ch1-'0') * 10 + (ch2-'0');
                    if(num <= 26)
                        count = (count + b) % mod;
                    }
                } 
            }
        }
        b = a;
        a = count;
    }
    return a;
}

//Leetcode function
public int numDecodingsII(String s) 
{
    long[] dp = new long[s.length() + 1];
    Arrays.fill(dp,-1);
    // return (int)numDecodingsII_memo(s,0,dp);
    // return (int)numDecodingsII_DP(s,0,dp);
    return (int)numDecodingsII_Opti(s);
}

//No of ways to partition n elements into k subsets(GFG)
public static int noOfWays_memo(int n,int k,int[][] dp)
{
    if(k == 1)
    return dp[n][k] = 1;

    if(n == k)
    return dp[n][k] = 1;

    if(dp[n][k] != 0)
    return dp[n][k];

    int uniqueGroup = noOfWays_memo(n-1,k-1,dp); // present ele apna alag group bnaeega.
    int partOfExistingGroup = noOfWays_memo(n-1,k,dp) * k; // present ele dusre ele ke bnne groups mei jaayega

    return dp[n][k] = uniqueGroup + partOfExistingGroup;
}

public static int noOfWays_DP(int N,int K,int[][] dp)
{
    for(int n = 1; n <= N; n++)
    {
        for(int k = 1; k <= K; k++)
        {
            if(k == 1)
            {
                dp[n][k] = 1;
                continue;
            }

            if(n == k)
            {
                dp[n][k] = 1;
                continue;
            }

            int uniqueGroup = dp[n-1][k-1]; // present ele apna alag group bnaeega.
            int partOfExistingGroup = dp[n-1][k] * k; // present ele dusre ele ke bnne groups mei jaayega

            dp[n][k] = uniqueGroup + partOfExistingGroup;
        }
    }
    return dp[N][K];
}

public static int noOfWays(int n,int k)
{
    int[][] dp = new int[n+1][k+1];
    // return noOfWays_memo(n,k,dp);
    
    return noOfWays_DP(n,k,dp);
}

public static void main(String args[])
{
    // fibo();
    // mazePath();
    // boardPath();
    // climbStairs(10);
    // countFriendsPairing(5);
    System.out.println(noOfWays(5,3));
}
}