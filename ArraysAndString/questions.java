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
}