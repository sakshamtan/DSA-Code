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
}