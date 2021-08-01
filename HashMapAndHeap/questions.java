import java.util.PriorityQueue;
import java.util.Random;
import java.util.HashSet;
import java.util.ArrayList;
import hava.util.HashMap;

public class questions{

//Leetcode 215 -> Kth Largest Element in an Array -> Using PQ
public int findKthLargest(int[] nums, int k) 
{
    PriorityQueue<Integer> pq = new PriorityQueue<>(); // kth largest so minPQ
    for(int ele : nums)
    {
        pq.add(ele);
        if(pq.size() > k) // k ke jitne ele hi rkhenege pq mei
            pq.remove();
    }
    return pq.remove();
}

//Leetcode 215 -> Now creating our own heap so better complexity -> N + Klog(N) => Klog(N)
public void swap(int[] arr,int i,int j)
{
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
    
public void downHeapify(int[] arr,int pi,int li)
{
    int maxIdx = pi;
    int lci = 2 * pi + 1;
    int rci = 2 * pi + 2;
        
    if(lci <= li && arr[lci] > arr[maxIdx])
        maxIdx = lci;
    if(rci <= li && arr[rci] > arr[maxIdx])
        maxIdx = rci;
    if(maxIdx != pi)
    {
        swap(arr,maxIdx,pi);
        downHeapify(arr,maxIdx,li);  
    }
}

//Leetcode function
public int findKthLargest(int[] nums, int k) 
{
    int li = nums.length-1;
    for(int i = li; i >= 0; i--)
        downHeapify(nums,i,li);
        
    while(k-- > 1)
    {
        swap(nums,0,li--);
        downHeapify(nums,0,li);
    }

    return nums[0];
}

//Leetcode 703 -> Kth Largest Element in a Stream (Now live data is given)
class KthLargest {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int k = 0;
    
    public KthLargest(int k, int[] nums) 
    {
        this.k = k;
        for(int ele : nums)
        {
            this.pq.add(ele);
            if(this.pq.size() > this.k)
                this.pq.remove();
        }
    }
    
    public int add(int val) 
    {
        this.pq.add(val);
        if(this.pq.size() > this.k)
            this.pq.remove();
        
        return this.pq.peek();
    }
}
}