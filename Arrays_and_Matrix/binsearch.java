import java.util.Arrays;//rev
public class binsearch{

public static int BS(int[] arr,int data)
{
    int li=0;
    int ri=arr.length - 1;
    while(li<=ri)
    {
        int mid=(li+ri)/2;

        if (arr[mid]==data)
        return mid;
        else if(arr[mid]<data)
        li=mid+1;

        else ri=mid-1;
    }
    
    return -1;
}

public static int BSLB(int[] arr,int data)// return lowest index of the multi-instant data present.
{
    int li=0;
    int ri=arr.length - 1;
    while(li<=ri)
    {
        int mid = (li + ri) / 2;
        if (arr[mid] == data)
        {
            if (mid - 1 > 0 && arr[mid] == arr[mid-1])
            {
                ri = mid - 1;   
            }
            else
            return mid;
        
        }
            else if(arr[mid]<data)
            li=mid+1;
            else ri=mid-1;
    }
    
    return -1;
}

public static int BSUB(int[] arr,int data)
{
    int li=0;
    int ri=arr.length - 1;
    while(li<=ri)
    {
        int mid=(li+ri)/2;
        if (arr[mid]==data)
        {
            if (mid + 1 < arr.length && arr[mid+1] == arr[mid])
            {
                li = mid + 1;
            }
        else
            return mid;
            
        }
        else if(arr[mid]<data)
        li=mid+1;
        else
        ri=mid-1;
    }
    
    return -1;
}

public static int BSCE (int [] arr,int data)//if data not found return closest element.
{
    int  li = 0;
    int ri = arr.length - 1;
    if (data > arr[ri]) return ri;
    if (data < arr[li]) return li;
        
        
    while (li <= ri)
    {
        int mid = (li + ri) / 2;
        if (arr[mid] > data)
        {
            ri = mid - 1;
        }

        else if (arr[mid] < data)
        {
            li = mid + 1;
        }

        else 
            return mid;
            

    }
    return (data - arr[ri] <= arr[li] - data?ri:li);

}

public static void targetSum(int [] arr , int target, int li, int ri)
{
    while (li < ri)
    {
        int sum = arr[li] + arr[ri];
        if (sum == target)
        {
            System.out.println("(" + arr[li] + "," + arr[ri]  + ")");
            li++;
            ri--;
        }

        else if (sum < target)
        {
            li++;
        }

        else
            ri--;
    

    }
        
}

public static void main(String[] args)
{
    // int[] arr={-2,-1,-1,0,0,0,2,2,2,2,2,2,2,3,4,5,6,7,12,18,19,20,22,28,30,48,48};
    // System.out.println(BS(arr,2));
    // System.out.println(BSLB(arr,2));
    // System.out.println(BSUB(arr,2));
    int [] arr = {10,15,20,40,50,55,60};
    //System.out.println(BSCE(arr,25));
    targetSum(arr,70,0,arr.length-1);
    
}
}