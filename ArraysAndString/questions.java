import java.util.Arrays;

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

//One pass O(N) Inplace solution
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
}