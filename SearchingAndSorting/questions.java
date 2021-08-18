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
}