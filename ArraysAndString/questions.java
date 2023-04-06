import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class questions{

public static void swap(int[] arr,int i,int j)
{
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}

public static void reverse(int[] arr,int i,int j)
{
    while(i < j)
    swap(arr,i++,j--);
}

public static void rotateByK(int[] arr,int r)
{
    int n = arr.length;
    
    // r %= n;
    // if(r < n)
    // r += n;

    r = (r % n + n) % n;

    reverse(arr,0,n-1);
    reverse(arr,n-r,n-1);
    reverse(arr,0,n-r-1);

} 

//+ve and -ve regions maintain krte huye chlenge array mei
public static void segregatePositiveAndNegative(int[] arr)
{
    int ptr = -1;
    int itr = 0;
    int n = arr.length;
    while(itr < n)
    {
        if(arr[itr] < 0)
            swap(arr,++ptr,itr);
        
        itr++;
    }
}

public static void segregateZeroAndOne(int[] arr)
{
    int ptr = -1;
    int n = arr.length;
    int itr = 0;
    while(itr < n)
    {
        if(arr[itr] == 0)
            swap(arr,++ptr,itr);
        
        itr++;
    }
}

// Leetcode 66 -> Plus One 
public int[] plusOne(int[] digits)
{
    for(int i = digits.length-1; i >= 0; i--) 
    {
      if(digits[i] < 9) 
      {
        digits[i]++;
        return digits;
      }
      digits[i] = 0;
    }

    // Case of [9,9,9]
    digits = new int[digits.length + 1];
    digits[0] = 1;
    return digits;
}

//Leetcode 283 -> Move Zeroes
public void moveZeroes(int[] nums) 
{
    int ptr = -1;
    int itr = 0;
    while(itr < nums.length)
    {
        if(nums[itr] != 0)
            swap(nums,++ptr,itr);
        
        itr++;
    }
}

//Leetcode 27 -> Remove Element -> InPlace optimized solution
public int removeElement(int[] nums, int val) 
{
    int ptr = -1, itr = 0;
    int count = 0;
    while(itr < nums.length)
    {
        if(nums[itr] != val)
        {
            swap(nums,++ptr,itr);
            count++;
        }  
        itr++;
    }
    return count;
}

//One pass O(N) Inplace solution (same as Leetcode 75 -> Sort Colours)
public static void segregateZeroOneAndTwo(int[] arr)
{
    int n = arr.length, pt1 = -1, pt2 = n-1, itr = 0;
    while(itr <= pt2)
    {
        if(arr[itr] == 0)
            swap(arr,++pt1,itr++);
        else if(arr[itr] == 2)
            swap(arr,pt2--,itr);
        else
            itr++;
    }
}

//Leetcode 462 -> Minimum Moves to Equal Array Elements II
public int findMedian(int[] arr)
{
    Arrays.sort(arr);
    int n = arr.length;

    if(n % 2 == 0)
        return (arr[n/2] + arr[(n/2)-1]) / 2;
    else
        return arr[n/2];        
}

//Leetcode function
public int minMoves2(int[] nums) 
{
    int med = findMedian(nums);
        
    int moves = 0;
    for(int i = 0; i < nums.length; i++)
    {
        moves += Math.abs(med - nums[i]);
    }
    return moves;
}

//Leetcode 11 -> Container with Most Water
public int maxArea(int[] height) 
{
    int n = height.length, i = 0, j = n-1;
    int maxArea = 0;
        
    while(i < j)
    {
        int w = j - i;
        maxArea = height[i] < height[j] ?  Math.max(maxArea,w * height[i++]) : Math.max(maxArea,w * height[j--]);
    }
    return maxArea;
}

//Leetcode 3 -> Longest Substring Without Repeating Characters
public int lengthOfLongestSubstring(String s) 
{
    int n = s.length(), si = 0, ei = 0, count = 0, len = 0;
    if(n <= 1)
        return n;
    int[] freq = new int[128];
        
    while(ei < n)
    {
        if(freq[s.charAt(ei++)]++ > 0)
            count++;
            
        while(count != 0)
        {
            if(freq[s.charAt(si++)]-- > 1)
                count--;
        }
            
        len = Math.max(len,ei - si);
    }
    return len;
}

//Follow Up Now return the longest Substring without repeating characters
public String longestSubstring(String s) 
{
    int n = s.length(), si = 0, ei = 0, count = 0, len = 0;
    if(n <= 1)
        return n;
    int[] freq = new int[128];

    int ssi = 0, sei = 0; // substring starting index and ending index  
    while(ei < n)
    {
        if(freq[s.charAt(ei)] == 1)
            count++;
        freq[s.charAt(ei)]++;
        ei++;
            
        while(count != 0)
        {
            if(freq[s.charAt(si)] == 2)
                count--;
            freq[s.cahrAt(si)]--;
            si++;
        }
        if(ei - si > len)
        {
            len = ei - si;
            ssi = si;
            sei = ei;
        }  
    }
    return s.substring(ssi,sei);  // returning the longest such substring
}

//Leetcode 159 -> Longest Substring with At Most Distinct Characters(Locked)
public int lengthOfLongestSubstringTwoDistinct(String s) 
{
    int n = s.length(), si = 0, ei = 0, count = 0, len = 0;
    if(n <= 2)
        return n;

    int[] freq = new int[128];

    while(ei < n)
    {
        if(freq[s.charAt(ei++)]++ == 0)
        count++;

        while(count > 2)
        {
            if(freq[s.charAt(si++)]-- == 1)
            count--;
        }
        len = Math.max(len,ei - si);
    }
    return len;
}

//Leetcode 76 -> Minimum Window Substring
public String minWindow(String s, String t) 
{
    int ns = s.length(), nt = t.length();
    if(ns < nt)
        return "";
        
    int si = 0, ei = 0, count = nt, len = (int)1e9, gsi = 0;
    int[] freq = new int[128];
    for(int i = 0; i < t.length(); i++)
        freq[t.charAt(i)]++;
        
    while(ei < ns)
    {
        if(freq[s.charAt(ei++)]-- > 0)
            count--;
            
        while(count == 0)
        {
            if(ei - si < len)
            {
                len = ei - si;
                gsi = si;
            }
            
            if(freq[s.charAt(si++)]++ == 0)
            count++;
        }
    }
    return len == (int)1e9 ? "" : s.substring(gsi,gsi + len);
}

//Smallest Distict Window -> GFG
public static String smallestWindowOfItself(String str)
{
    int n = str.length(), si = 0, ei = 0, count = 0, len = n;

    if(n <= 1)
        return str;

    int[] freq = new int[128];
    for(int i = 0; i < str.length(); i++)
    {
        if(freq[str.charAt(i)] == 0)
        {
            count++;
            freq[str.charAt(i)] = 1;
        }
    }
    
    int ssi = 0;
    while(ei < n)
    {
        if(freq[str.charAt(ei++)]-- > 0)
            count--;

        while(count == 0)
        {
            if(ei - si < len)
            {
                len = ei - si;
                ssi = si;
            }

            if(freq[str.charAt(si++)]++ == 0)
                count++;
        }
    }
    return str.substring(ssi,ssi + len);
}

//Leetcode 340 -> Longest Substring with at most K distinct Characters
public int lengthOfLongestSubstringKDistinct(String s,int k)
{
    int n = s.length(), si = 0, ei = 0, count = 0, len = 0;
    if(n <= k)
        return n;

    int[] freq = new int[128];

    while(ei < n)
    {
        if(freq[s.charAt(ei++)]++ == 0)
        count++;

        while(count > k)  // k instead of 2 everywhere
        {
            if(freq[s.charAt(si++)]-- == 1)
            count--;
        }
        len = Math.max(len,ei - si);
    }
    return len;
}

//Leetcode 1456 -> Maximum Number of Vowels in a Substring of Given Length
public boolean isVowel(Character ch)
{
    return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
}

//Leetcode function    
public int maxVowels(String s, int k) 
{
    int n = s.length(), si = 0, ei = 0, vowelCount = 0, maxVowelCount = 0;
    while(ei < n)
    {
        if(isVowel(s.charAt(ei++)))
            vowelCount++;
            
        if(ei - si == k)
        {
            maxVowelCount = Math.max(maxVowelCount,vowelCount);
            if(isVowel(s.charAt(si++)))
                vowelCount--;
        }
    }
    return maxVowelCount;
}

//Leetcode 992 -> Subarrays with K Different Integers
//returns no of subarrays with at most k distinct integers
public int subarrayWithatMostKDistinct(int[] arr,int k)
{
    HashMap<Integer,Integer> freq = new HashMap<>();
    int n = arr.length, si = 0, ei = 0, ans = 0;
    while(ei < n)
    {
        freq.put(arr[ei],freq.getOrDefault(arr[ei],0) + 1);
        ei++;
            
        while(freq.size() > k)
        {
            freq.put(arr[si],freq.get(arr[si]) - 1);
            
            if(freq.get(arr[si]) == 0)
                freq.remove(arr[si]);
                
            si++;
        }
        ans += ei - si;
    }
    return ans;
}

//Using Array of 20001 size instead of HashMap -> (Better chlta hai ye vaala)
public int subarrayWithatMostKDistinct_02(int[] arr,int k)
{
    int[] freq = new int[20000 + 1];
    int n = arr.length, si = 0, ei = 0,count = 0, ans = 0;
    while(ei < n)
    {
        if(freq[arr[ei++]]++ == 0)
            count++;
            
        while(count > k)
        {
            if(freq[arr[si++]]-- == 1)
                count--;
        }
        ans += ei - si;
    }
    return ans;
}

//Leetcode function
public int subarraysWithKDistinct(int[] nums, int k) 
{
    return subarrayWithatMostKDistinct(nums,k) - subarrayWithatMostKDistinct(nums,k-1);
}

//Leetcode 1248 -> Number of Nice Subarrays (No of subarrays containing exactly k odd no.s)
// Returns no of subarrays containing at most k odd no.s
public int atMostKodd(int[] arr,int k)
{
    int n = arr.length, si = 0, ei = 0, count = 0, ans = 0;
        
    while(ei < n)
    {
        if((arr[ei++] & 1) != 0)
            count++;
            
        while(count > k)
        {
            if((arr[si++] & 1) != 0)
                count--;
        }
        ans += ei - si;
    }
    return ans;
}

//Leetcode function
public int numberOfSubarrays(int[] nums, int k) 
{
    return atMostKodd(nums,k) - atMostKodd(nums,k-1);
}

//Leetcode 904 -> Fruit into Basket -> (Length of Longest subarray with at most 2 unique integers)
public int totalFruit(int[] fruits) 
{
    int n = fruits.length, si = 0, ei = 0, count = 0, len = 0;
    int[] freq = new int[40000 + 1];
        
    while(ei < n)
    {
        if(freq[fruits[ei++]]++ == 0)
            count++;
            
        while(count > 2)
            if(freq[fruits[si++]]-- == 1)
                count--;
            
        len = Math.max(len,ei - si);
    }
    return len;
}

//Leetcode 930 -> Binary SubArrays with Sum
// Same approach -> AtMost tar sum vaale subarrays ka count nikaal lia
public int atMostSum(int[] arr,int tar)
{
    int n = arr.length, si = 0, ei = 0, sum = 0, count = 0;
        
    while(ei < n)
    {
        sum += arr[ei++];
            
        while(sum > tar)
            sum -= arr[si++];
            
        count += ei - si;
    }
    return count;
}

//Leetcode function
public int numSubarraysWithSum(int[] nums, int goal) 
{
    return atMostSum(nums,goal) - (goal != 0 ? atMostSum(nums,goal-1) : 0);
}

//Leetcode 485 -> Max Consecutive Ones
public int findMaxConsecutiveOnes(int[] arr) 
{
    int n = arr.length, si = 0, ei = 0, count = 0,len = 0;
        
    while(ei < n)
    {
        if(arr[ei++] == 0)
            count++;
            
        while(count == 1)
            if(arr[si++] == 0)
                count--;
            
        len = Math.max(len,ei - si);
    }
    return len;
}

//si ko direct ei pe bhi jump krwa skte hai as soon as ei encounters a '0'
public int findMaxConsecutiveOnes_02(int[] arr) 
{
    int n = arr.length, si = 0, ei = 0, len = 0;
        
    while(ei < n)
    {
        if(arr[ei++] == 0)
            si = ei;

        len = Math.max(len,ei - si);
    }
    return len;
}

//Leetcode 487 -> Max Consecutive Ones II (one 0 is allowed to be flipped so one 0 is allowed in the window)
public int findMaxConsecutiveOnes(int[] arr) 
{
    int n = arr.length, si = 0, ei = 0, len = 0, count = 0;
    
    while(ei < n)
    {
        if(arr[ei++] == 0)
            count++;

        while(count == 2)
        {
            if(arr[si++] == 0)
                count--;
        }
        len = Math.max(len,ei - si);
    }
    return len;
}

//Leetcode 1004 -> Max Consecutive Ones III(At most K 0's are allowed to be flipped)
public int longestOnes(int[] arr, int k) 
{
    int n = arr.length, si = 0, ei = 0, len = 0, count = 0;

    while(ei < n)
    {
        if(arr[ei++] == 0)
            count++;
            
        while(count > k)
        {
            if(arr[si++] == 0)
                count--;
        }
        len = Math.max(len,ei - si);
    }
    return len;
}

//Leetcode 974 -> Subarray Sums Divisible By K
public int subarraysDivByK(int[] arr, int k) 
{
    int[] rem = new int[k];
    int n = arr.length, ei = 0, sum = 0, ans = 0;
        
    rem[0] = 1; // jiska rem 0 hai vo already divisible hai
    while(ei < n)
    {
        sum += arr[ei++];
            
        int r = (sum % k + k) % k;
            
        ans += rem[r];  // pehle rem ki pichli freq add and then rem ko update
        rem[r]++;
        
    }
    return ans;    
}

//Length of longest subarray having sum divisible by k using Array
public int subarraysDivByK_length(int[] arr, int k) 
{
    int[] rem = new int[k];
    Arrays.fill(rem,-2);
    int n = arr.length, ei = 0, sum = 0, len = 0;
        
    rem[0] = -1; // jiska rem 0 hai vo already divisible hai
    while(ei < n)
    {
        sum += arr[ei];
        
        int r = (sum % k + k) % k;
        
        if(rem[r] == -2) // sirf pehli baar update krna hai ei as tabhi longest length mil skti hai
            rem[r] = ei;
        else
            len = Math.max(len,ei - rem[r]);

        ei++;
    }
    return len;
}

//Now using HashMap instead of freq array
public int subarraysDivByK_map(int[] arr, int k) 
{
    HashMap<Integer,Integer> rem = new HashMap<>();
    int n = arr.length, ei = 0, sum = 0, ans = 0;
        
    rem.put(0,1); // jiska rem 0 hai vo already divisible hai
    while(ei < n)
    {
        sum += arr[ei++];
            
        int r = (sum % k + k) % k;
            
        ans += rem.getOrDefault(r,0);  // pehle rem ki pichli freq add and then rem ko update
        rem.put(r,rem.getOrDefault(r,0)+1);
        
    }
    return ans;    
}

//Length of longest subarray having sum divisible by k using HashMap
public int subarraysDivByK_length_map(int[] arr, int k) 
{
    HashMap<Integer,Integer> rem = new HashMap<>();
    int n = arr.length, ei = 0, sum = 0, len = 0;
        
    rem.put(0,-1); // jiska rem 0 hai vo already divisible hai
    while(ei < n)
    {
        sum += arr[ei];
        
        int r = (sum % k + k) % k;
        
        rem.putIfAbsent(r,ei); // first time update krega bs

        len = Math.max(len,ei - rem[r]);
        ei++;
    }
    return len;
}

//No of SubArrays with equal 1's and 0's (GFG) -> 0 ko -1 se replace krdia and jb sum 0 aayega to equal no of 0's and 1's honge
public int countSubarrWithEqualZeroAndOne(int[] arr, int n)
{
    HashMap<Integer,Integer> map = new HashMap<>();
    map.put(0,1);
        
    int ei = 0,sum = 0, ans = 0;
    while(ei < n)
    {
        int val = arr[ei++];
        sum += val;
            
        if(val == 0)
        sum += -1;
            
        ans += map.getOrDefault(sum,0);
        map.put(sum,map.getOrDefault(sum,0)+1);
    
    }
    return ans;
}

//Leetcode 525 -> Contiguos Array -> Now return the length of longest subarray having equal no of 0's and 1's
public int findMaxLength(int[] arr) 
{
    HashMap<Integer,Integer> map = new HashMap<>();
    map.put(0,-1);
        
    int n = arr.length, ei = 0, len = 0,sum = 0;
    while(ei < n)
    {
        int val = arr[ei];
        sum += val;
            
        if(val == 0)
            sum += -1;
            
        map.putIfAbsent(sum,ei);
        len = Math.max(len,ei - map.get(sum));
        ei++;
    }
    return len;
}

//Leetcode 239 -> Maximum Sliding Window
// Using PQ -> NlogN time and N space
public int[] maxSlidingWindow_01(int[] arr, int k) 
{
    PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
        return arr[b] - arr[a];
    });
        
    int n = arr.length,idx = 0;
    int[] ans = new int[n - k + 1];

    for(int i = 0; i < n; i++)
    {
        while(pq.size() != 0 && pq.peek() <= i - k)
            pq.remove();
            
        pq.add(i);
        if(i >= k - 1)
            ans[idx++] = arr[pq.peek()];
    }
    return ans;
}

//Using Deque -> O(N) time and O(N) space
public int[] maxSlidingWindow(int[] arr, int k) 
{
    LinkedList<Integer> deque = new LinkedList<>();
    int n = arr.length,idx = 0;
    int[] ans = new int[n - k + 1];

    for(int i = 0; i < n; i++)
    {
        while(deque.size() != 0 && deque.getFirst() <= i - k)
            deque.removeFirst();
            
        while(deque.size() != 0 && arr[deque.getLast()] <= arr[i])
            deque.removeLast();
            
        deque.addLast(i);
        
        if(i >= k - 1)
            ans[idx++] = arr[deque.getFirst()];
    }
    return ans;
}

//Kadanes Algorithm
// -ve arr ke liye 0 ans dega [-2,-4,-5,-9] gSum = 0
public int KadanesAlgo_01(int[] arr)
{
    int gSum = 0, cSum = 0;
    for(int ele : arr)
    {
        cSum += ele;
        if(cSum > gSum)
            gSum = cSum;

        if(cSum <= 0)
            cSum = 0;
    }
    return gSum;
}

//-ve arr ke liye sbse chotta -ve ans dega [-2,-4,-5,-9] gSum = -2
public int KadanesAlgo_02(int[] arr)
{
    int gSum = -(int)1e9, cSum = 0;
    for(int ele : arr)
    {
        cSum = Math.max(ele, cSum + ele);
        gSum = Math.max(cSum,gSum);
    }
    return gSum;
}

//Now to return the subArray with largest sum -> store starting and ending indexes
public int KadanesAlgo_01_SubArray(int[] arr)
{
    int gSum = 0, cSum = 0, gsi = 0, gei = 0, csi = 0;  // csi -> current starting index
    for(int i = 0; i < arr.length; i++)
    {
        cSum += arr[i];
        if(cSum > gSum)
        {
            gSum = cSum;

            gsi = csi;
            gei = i;
        }

        if(cSum <= 0)
        {
            cSum = 0;
            csi = i + 1;
        }
    }
    return gSum;
}

//Leetcode 1191 -> K - Concatenation Maximum Sum
int mod = (int)1e9 + 7;
public int KadanesAlgo(int[] arr,int k)
{
    long gsum = 0, csum = 0;
    int n = arr.length;
        
    for(int i = 0; i < k * n; i++)
    {
        int ele = arr[i % n];
        csum += ele;
            
        if(csum > gsum)
            gsum = csum;
        if(csum <= 0)
            csum = 0;
    }
    return (int)(gsum % mod);
}

//Leetcode function   
public int kConcatenationMaxSum(int[] arr, int k) 
{
    long prevSum = 0, sum = 0;
    for(int i = 1; i <= 3; i++)
    {
        prevSum = sum;
        sum = KadanesAlgo(arr,i);
            
        if(i == k)
            return (int)sum;
    }
    return (int)((prevSum + (k-2) * (sum - prevSum)) % mod);
}

//x , s2, s3 alag se nikaal lia and direct formula apply
public int kConcatenationMaxSum_02(int[] arr, int k) 
{
    int kadanesSum = KadanesAlgo(arr,1);
    if(k == 1)
        return kadanesSum;
        
    long prefixSum = 0, suffixSum = 0, maxPrefixSum = 0, maxSuffixSum = 0, arraySum = 0;
    int n = arr.length;
    for(int i = 0, j = n - 1; i < n; i++,j--)
    {
        prefixSum += arr[j];
        suffixSum += arr[i];
        arraySum += arr[i];
            
        maxPrefixSum = Math.max(maxPrefixSum,prefixSum);
        maxSuffixSum = Math.max(maxSuffixSum,suffixSum);
    }

    arraySum = arraySum < 0 ? 0 : arraySum % mod;
    return (int) Math.max(kadanesSum,maxPrefixSum + maxSuffixSum + ((k-2) * arraySum) % mod) % mod;        
}

//Maximum Sum Rectangle -> GFG
public int maximumSumRectangle(int R, int C, int arr[][]) 
{
    int n = R, m = C, maxSum = -(int)1e9;
        
    for(int fixRow = 0; fixRow < n; fixRow++)
    {
        int[] colPrefixSum = new int[m];
        for(int row = fixRow; row < n; row++)
        {
            for(int col = 0; col < m; col++)
                colPrefixSum[col] += arr[row][col];
                    
            int sum = KadanesAlgo_02(colPrefixSum);
            maxSum = Math.max(maxSum,sum);
        }
    }
    return maxSum;
}

//Now to print the subMatrix / rectangle that has maximum sum
public int[] KadanesAlgo_Generic(int[] arr)
{
    int cSum = 0, gSum = -(int)1e9, gsi = 0, csi = 0, gei = 0;
    for(int i = 0; i < arr.length; i++)
    {
        int ele = arr[i];
        cSum += ele;

        if(ele >= cSum)
        {
            cSum = ele;
            csi = i;
        }

        if(cSum > gSum)
        {
            gSum = cSum;
            gsi = csi;
            gei = i;
        }
    }
    return new int[]{gSum,gsi,gei};
}

public int maximumSumRectangle_02(int R, int C, int arr[][]) 
{
    int n = R, m = C, maxSum = -(int)1e9;
    int r1 = 0, c1 = 0, r2 = 0, c2 = 0;
    
    for(int fixRow = 0; fixRow < n; fixRow++)
    {
        int[] colPrefixSum = new int[m];
        for(int row = fixRow; row < n; row++)
        {
            for(int col = 0; col < m; col++)
                colPrefixSum[col] += arr[row][col];
                    
            int[] res = KadanesAlgo_Generic(colPrefixSum);
            if(res[0] >= maxSum)
            {
                maxSum = res[0];
                r1 = fixRow;
                c1 = res[1]; // si of kadanes
                r2 = row;
                c2 = res[2]; // ei of kadanes
            }
        }
    }

    for(int i = r1; i <= r2; i++)  // printing matrix from coordinates (r1,c1) to (r2,c2)
    {
        for(int j = c1; j <= c2; j++)
        {
            System.out.print(arr[i][j] + " ");
        }
        System.out.println();
    }

    return maxSum;
}

//Leetcode 781 -> Rabbits in Forrest
public int numRabbits(int[] arr) 
{
    int[] map = new int[999 - 0 + 1];
    int ans = 0;
    for(int ele : arr)
    {
        if(map[ele] == 0)
            ans += ele + 1;
            
        map[ele]++;
        if(map[ele] == ele + 1)
            map[ele] = 0;
    }
    return ans;
}

//Leetcode 560 -> Subarray Sum Equals k -> Return no of subarrays that sum to target
public int subarraySum(int[] arr, int tar) 
{
    HashMap<Integer,Integer> map = new HashMap<>();
    map.put(0,1);
        
    int count = 0, sum = 0;
        
    for(int ele : arr)
    {
        sum += ele;
        count += map.getOrDefault(sum - tar,0);
        
        map.put(sum,map.getOrDefault(sum,0) + 1);
    }
    return count;
}

//Leetcode 1074 -> Number of SubMatrices That Sum to K -> Uses Leetcode 560 (Leetcode 560 ka bs 2D version hai)
public int numSubmatrixSumTarget(int[][] matrix, int target) 
{
    int n = matrix.length, m = matrix[0].length, count = 0;
        
    for(int fixedRow = 0; fixedRow < n; fixedRow++)
    {
        int[] prefixColArray = new int[m];
        for(int row = fixedRow; row < n; row++)
        {
            for(int col = 0; col < m; col++)
                prefixColArray[col] += matrix[row][col];
            
            count += subarraySum(prefixColArray,target);
        }
    }
    return count;
}

//Leetcode 363 -> Max Sum No Larger than K
public int kadanesAlgoWithSumUnderK(int[] arr,int k)
{
    int gsum = -(int)1e9, csum = 0;
    for(int ele : arr)
    {
        csum += ele;
        csum = Math.max(csum,ele);
        gsum = Math.max(gsum,csum);
            
        if(gsum >= k)
            return gsum;
    }
    return gsum;
}

public int maxSumSubmatrix(int[][] arr, int k) 
{
    int n = arr.length, m = arr[0].length;
    int maxRes = 0;
        
    for(int fixedRow = 0; fixedRow < n; fixedRow++)
    {
        int[] prefixColArray = new int[m];
        for(int row = fixedRow; row < n; row++)
        {
            for(int col = 0; col < m; col++)
                prefixColArray[col] += arr[row][col];
                
            int sum = kadanesAlgoWithSumUnderK(prefixColArray,k);
            
            if(sum == k)
                return sum;
            else if(sum < k)
                maxRes = Math.max(maxRes,sum);
                
            // ?????
        }
    }
    return maxRes;
}

//Leetcode 605 -> Can Place Flowers -> Greedy
public boolean canPlaceFlowers(int[] flowerbed, int n) 
{
    int i = 0, count = 0;
    while(i < flowerbed.length)
    {
        if(flowerbed[i] == 0 && (i == 0 || flowerbed[i-1] == 0) && (i == flowerbed.length-1 || flowerbed[i+1] == 0))
        {
            flowerbed[i] = 1;
            count++;
        }
        i++;
    }
    return count >= n;
}

// Leetcode 28 -> Find the index of first occurence of a string
// Two pointer method
public int strStr(String haystack, String needle) {
    int i = 0;
    int j = 0;
    int possibleAns = -1;
    if(needle.length() > haystack.length())
    return -1;

    while(i < haystack.length())
    {
        if(haystack.charAt(i) == needle.charAt(j))
        {
            if(j == 0)
            {
                possibleAns = i;
            }
            if(j == needle.length() - 1)
            {
                return possibleAns;
            }
            i++;
            j++;
        }
        else {
            if(j == 0)
            {
                i++;
                possibleAns = -1;
            }
            else {
                i = possibleAns + 1;
                j = 0;
            }
        }
    }
    return -1; 
}

// Substring method
public int strStr_02(String haystack, String needle) {
    int end = needle.length();

    for(int i = 0; i < haystack.length() - end + 1; i++)
    {
        if(haystack.substring(i, i + end).equals(needle))
        return i;
    }
    return -1;
}
}