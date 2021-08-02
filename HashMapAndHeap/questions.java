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

//Leetcode 349 -> Intersection of Two Arrays
public int[] intersection(int[] nums1, int[] nums2) 
{
    HashSet<Integer> set = new HashSet<>();
    ArrayList<Integer> ans = new ArrayList<>();
        
    for(int ele : nums1)
        set.add(ele);
        
    for(int ele : nums2)
    {
        if(set.contains(ele))
        {
            ans.add(ele);
            set.remove(ele);
        }
    }
        
    int[] res = new int[ans.size()];
    for(int i = 0; i < ans.size(); i++)
        res[i] = ans.get(i);
    
    return res;
}

//Leetcode 350 -> Intersection of Two Arrays II 
public int[] intersect(int[] nums1, int[] nums2) 
{
    HashMap<Integer,Integer> map = new HashMap<>();
    ArrayList<Integer> ans = new ArrayList<>();

    for(int ele : nums1)
        map.put(ele,map.getOrDefault(ele,0) + 1);
        
    for(int ele : nums2)
    {
        if(map.containsKey(ele))
        {
            ans.add(ele);
            map.put(ele,map.get(ele) -1);
            if(map.get(ele) == 0)
                map.remove(ele);
        }
    }

    int[] res = new int[ans.size()];
    for(int i = 0; i < ans.size(); i++)
        res[i] = ans.get(i);
        
    return res;
}

//Leetcode 128 -> Longest Consecutive Sequence
public int longestConsecutive(int[] nums) 
{
    HashSet<Integer> set = new HashSet<>();
    for(int ele : nums)
        set.add(ele);
        
    int len = 0;
    for(int ele : nums)
    {
        if(!set.contains(ele))
            continue;
            
        int ple = ele - 1, pre = ele + 1;
        set.remove(ele);
            
        while(set.contains(ple))
            set.remove(ple--);
        while(set.contains(pre))
            set.remove(pre++);
            
        len = Math.max(len,pre - ple - 1);
    }
    return len;
}

//Leetcode 347 -> Top K Frequent Elements (return all k freq ele in an array)
public int[] topKFrequent(int[] nums, int k) 
{
    HashMap<Integer,Integer> map = new HashMap<>();
    for(int ele : nums)
        map.put(ele,map.getOrDefault(ele,0) + 1); // freq map
        
    // (val,freq)
    PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{  // minPQ on the basis of frequency
        return a[1] - b[1]; 
    });
        
    for(Integer key : map.keySet())
    {

        pq.add(new int[]{key,map.get(key)});
        if(pq.size() > k)
            pq.remove();
    }
            
    int[] ans = new int[pq.size()];  // last mei k freq ele bache hai que mei
    int i = 0;
    while(pq.size() != 0)  
    {
        int[] p = pq.remove();
        int val = p[0];
        int freq = p[1];
                
        ans[i++] = val;
    }

    return ans;   
}

//Better approach -> only keeping val in pq and sorting it on basis of freq kept in map
public int[] topKFrequent_02(int[] nums, int k) 
{
    HashMap<Integer,Integer> map = new HashMap<>();
    for(int ele : nums)
        map.put(ele,map.getOrDefault(ele,0) + 1);
        
    // now only val
    PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
        return map.get(a) - map.get(b);  // sorted on the basis of freq kept in map
    });
        
    for(Integer key : map.keySet())
    {
        pq.add(key);
        if(pq.size() > k)
            pq.remove();
    }
            
    int[] ans = new int[pq.size()];            
    int i = 0;
    while(pq.size() != 0)
    {
        ans[i++] = pq.remove();
    }
    return ans;   
}
        


//Leetcode 973 -> K Closest Points to Origin
public int[][] kClosest(int[][] points, int k) 
{
    //(x,y) -> co-ordinartes store kr rhe hai pq mei and distances ke basis pe priority sikha di
    PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
        int d1 = a[0] * a[0] + a[1] * a[1]; // equiv to x1^2 + y1^2
        int d2 = b[0] * b[0] + b[1] * b[1];  // x2^2 + y2^2  
            
        return d2 - d1; // maxPQ so other - this
    });
        
    for(int[] p : points)
    {
        pq.add(p);
        if(pq.size() > k)
            pq.remove();
    }
        
    int i = 0;
    int[][] ans = new int[k][];
    while(pq.size() != 0)
    {
        ans[i++] = pq.remove();
    }
        
    return ans;
}
}