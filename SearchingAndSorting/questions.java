import java.util.Arrays;
import java.util.ArrayList;

public class questions{

public static int binarySearch(int[] arr,int data)
{
    int n = arr.length, si = 0, ei = n - 1;

    while(si <= ei)
    {
        int mid = (si + ei) / 2;
        if(arr[mid] == data)
            return data;

        else if(arr[mid] < data)
            si = mid+1;
        else
            ei = mid-1;
    }

    return -1;
}

public int firstIndex(int[] arr,int data)
{
    int n = arr.length, si = 0, ei = n-1;
    while(si <= ei)
    {
        int mid = (si + ei) / 2;
        if(arr[mid] == data)
        {
            if(mid - 1 >= 0 && arr[mid-1] == data)
                ei = mid-1;
            else
                return mid;
        }
        else if(arr[mid] < data)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    return -1;
}

public int lastIndex(int[] arr,int data)
{
    int n = arr.length, si = 0, ei = n - 1;
    while(si <= ei)
    {
        int mid = (si + ei) / 2;
        if(arr[mid] == data)
        {
            if(mid + 1 < n && arr[mid+1] == data)
                si = mid+1;
            else
                return mid;
        }
        else if(arr[mid] < data)
            si = mid+1;
        else
            ei = mid-1;
    }
    return -1;
}

//Leetcode 34 -> Find First and Last Position of Element in Sorted Array
public int[] searchRange(int[] arr, int data) 
{
    return new int[]{firstIndex(arr,data),lastIndex(arr,data)};
}

//Leetcode 35 -> Search Insert Position
public int searchInsert(int[] arr, int data) 
{
    int n = arr.length, si = 0, ei = n-1;
    while(si <= ei)
    {
        int mid = (si + ei) / 2;
            
        if(arr[mid] <= data)
            si = mid + 1;
        else
            ei = mid-1;
    }
        
    int lastIndex = si - 1;
    int insertPos = si;
    return (lastIndex >= 0 && arr[lastIndex] == data) ? lastIndex : insertPos;
}

//Inversion Count -> (GFG)
// Returns inversion count across two Arrays and sort the array at that range(merge sort)
public static long inversionAcrossArray(long[] arr,int l,int r,int mid,long[] sortedArray)
{
    int lsi = l, lei = mid;
    int rsi = mid+1, rei = r;

    int k = 0;
    long count = 0;
    while(lsi <= lei && rsi <= rei)
    {
        if(arr[lsi] > arr[rsi])
        {
            count += (lei - lsi + 1);
            sortedArray[k++] = arr[rsi++];
        }
        else
            sortedArray[k++] = arr[lsi++];
    }

    while(lsi <= lei) // agr dono mei se koi arr bach gya ho end mei to as it is copy
        sortedArray[k++] = arr[lsi++];
    while(rsi <= rei)
        sortedArray[k++] = arr[rsi++];   

    k = 0;
    for(int i = l; i <= r; i++)
        arr[i] = sortedArray[k++];  // sortedArr copied into original arr to make changes in the real arr.
    
    return count;
}

// Main recursive function
public static long inversionCount(long[] arr,int si,int ei,long[] sortedArray)
{
    if(si >= ei)
    return 0;

    int mid = (si + ei) / 2;

    long leftInversionCount = inversionCount(arr,si,mid,sortedArray);  // Inversion count within left subArray
    long rightInversionCount = inversionCount(arr,mid+1,ei,sortedArray); 

    return (leftInversionCount + rightInversionCount + inversionAcrossArray(arr,si,ei,mid,sortedArray));
}

//GFG function
public static long inversionCount(long arr[], long N)
{
    if(N <= 1)
    return 0;

    long[] sortedArray = new long[(int)N];
    return inversionCount(arr,0,(int)N-1,sortedArray);
}

//Leetcode 33 -> Search in Rotated Sorted Array
public int search(int[] arr, int target) 
{
    int si = 0, ei = arr.length-1;
        
    while(si <= ei)
    {
        int mid = (si + ei) / 2;

        if(arr[mid] == target)
            return mid;
        else if(arr[si] <= arr[mid])
        {
            if(arr[si] <= target && target < arr[mid])
                ei = mid-1;
            else
                si = mid+1;
        }
        else
        {
            if(arr[mid] < target && target <= arr[ei])
                si = mid+1;
            else
                ei = mid-1;
        }
    }
    return -1;
}

//Leetcode 81 -> Search in Rotated Sorted Array -> Now duplicate values are also present in the arr
public boolean search(int[] arr, int target) 
{
    int si = 0, ei = arr.length-1;
        
    while(si <= ei)
    {
        int mid = (si + ei) / 2;
        
        if(arr[mid] == target || arr[si] == target)
            return true;
        else if(arr[si] < arr[mid])
        {
            if(arr[si] <= target && target < arr[mid])
                ei = mid-1;
            else
                si = mid+1;
        }
        else if(arr[mid] < arr[ei])
        {
            if(arr[mid] < target && target <= arr[ei])
                si = mid+1;
            else
                ei = mid-1;
        }
        else
            si++;  // worst case -> O(N) when all ele are duplicate
    }
    return false;
}

//Leetcode 153 -> Find Minimum in Rotated Sorted Array -> Find the pivot ele
public int findMin(int[] arr) 
{
    int n = arr.length, si = 0, ei = n - 1;
    if(arr[si] <= arr[ei])
        return arr[si];
        
    while(si < ei)
    {
        int mid = (si + ei) / 2;
            
        if(arr[mid] < arr[ei])
            ei = mid;
        else if(arr[si] <= arr[mid])
            si = mid + 1;
    }
    return arr[si];
}

//Leetcode 154 -> Find Minimum in Sorted Array II -> Now duplicate values are allowed
public int findMin(int[] arr) 
{
    int si = 0, ei = arr.length-1;
        
    if(arr[si] < arr[ei])
        return arr[si];
        
    while(si < ei)
    {
        int mid = (si + ei) / 2;
            
        if(arr[mid] < arr[ei])
            ei = mid;
        else if(arr[mid] > arr[ei])
            si = mid+1;
        else
            ei--;
    }
    return arr[si];
}
}