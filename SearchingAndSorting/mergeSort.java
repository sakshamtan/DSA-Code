public class mergeSort {

// Merge two sorted arrays + recursion -> Merge Sort
public static int[] mergeTwoSortedArrays(int[] a, int[] b)
{
    int[] ans = new int[a.length + b.length];
    int i = 0, j = 0, k = 0;

    while(i < a.length && j < b.length)
    {
        if(a[i] <= b[j])
        {
            ans[k] = a[i];
            i++;
        }
        else{
            ans[k] = b[j];
            j++;
        }
        k++;
    }

    while(i < a.length)
    {
        ans[k] = a[i];
        i++;
        k++;
    }

    while(j < b.length)
    {
        ans[k] = b[j];
        k++;
        j++;
    }

    return ans;
}

public static int[] mergeSort(int[] arr, int lo, int hi) 
{
    if(lo == hi)
    {
        int[] base = new int[1];
        base[0] = arr[lo];
        return base;
    }
  
    int mid = (lo + hi) / 2;
    int[] left = mergeSort(arr,lo,mid);
    int[] right = mergeSort(arr,mid+1,hi);
  
    return mergeTwoSortedArrays(left,right);
} 

public static void main(String[] args)
{
    int[] arr = new int[]{1,3,4,-1,0,200,10};
    int[] sortedArr = mergeSort(arr,0,arr.length-1);
    for(int ele : sortedArr)
    {
        System.out.print(ele + " ");
    }
}
}
