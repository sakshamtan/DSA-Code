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

//Leetcode 378 -> Kth Smallest Element in a sorted Matrix
public int kthSmallest(int[][] matrix, int k) 
{
    int n = matrix.length;
    int m = matrix[0].length;
        
    PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
        int r1 = a / m, c1 = a % m; // 1D ke coordinates mei daal rhe hai pq mei
        int r2 = b / m, c2 = b % m;
        
        return matrix[r1][c1] - matrix[r2][c2]; // matrix ki values pr minPQ
    });
        
    for(int i = 0; i < n; i++)
        pq.add(i * m + 0);  // adding the complete first col of matrix
        
    int ans = 0;
    while(k-- > 0)
    {
        int idx = pq.remove();
        int r = idx / m;
        int c = idx % m;
            
        ans = matrix[r][c];
        c++;
        if(c < m)
            pq.add(r * m + c);
    }
    return ans;
}

//Leetcode 380 -> Insert Delete Get Random O(1)
class RandomizedSet {
    HashMap<Integer,Integer> map;
    ArrayList<Integer> list;
    
    // Initialize your data structure here
    public RandomizedSet() 
    {
        map = new HashMap<>();
        list = new ArrayList<>();
    }
    
    // Inserts a value to the set. Returns true if the set did not already contain the specified element
    public boolean insert(int val) 
    {
        if(map.containsKey(val))
            return false;
        
        list.add(val);
        map.put(val,list.size()-1);
        
        return true;
    }
    
    // Removes a value from the set. Returns true if the set contained the specified element
    public boolean remove(int val) 
    {
        if(!map.containsKey(val))
            return false;
        
        int idx = map.get(val);
        int lidx = list.size()-1;
        int lval = list.get(lidx);
        
        list.set(idx,lval);
        map.put(lval,idx);
        
        list.remove(lidx);
        map.remove(val);
        
        return true;
    }
    
    // Get a random element from the set.
    public int getRandom() 
    {
        Random rand = new Random();
        int idx = rand.nextInt(list.size());
        
        return list.get(idx);
    }
}

//Leetcode 895 -> Maximum Frequency Stack
class FreqStack {
    
    HashMap<Integer,Integer> map;
    ArrayList<Stack<Integer>> freqMap;
    int maxFreq = 0;

    public FreqStack() 
    {
        map = new HashMap<>();
        freqMap = new ArrayList<>();
        
        freqMap.add(new Stack<>());
    }
    
    public void push(int val)  //O(1)
    {
        map.put(val,map.getOrDefault(val,0) + 1);
        maxFreq = Math.max(maxFreq,map.get(val));
        
        if(maxFreq == freqMap.size())
            freqMap.add(new Stack<>());
        
        freqMap.get(map.get(val)).add(val); // freq vaale index vaale stack mei push
    }
    
    public int pop() //O(1)
    {
        int rv = freqMap.get(maxFreq).pop();
        if(freqMap.get(maxFreq).size() == 0)
        {
            freqMap.remove(maxFreq);
            maxFreq--;
        }
        
        map.put(rv,map.get(rv) -1);
        if(map.get(rv) == 0)
            map.remove(rv);
        
        return rv;
    }
}

//Leetcode 895 -> PriorityQueue Solution
class FreqStack {
    class pair{
        int val = 0;
        int freq = 0;
        int idx = 0;
        
        pair(int val,int freq,int idx)
        {
            this.val = val;
            this.freq = freq;
            this.idx = idx;
        }
    }
    
    HashMap<Integer,Integer> map;
    PriorityQueue<pair> pq;
    int idx = 0;

    public FreqStack() 
    {
        map = new HashMap<>();
        pq = new PriorityQueue<>((a,b)->{
            if(a.freq == b.freq)
                return b.idx - a.idx;
            
            return b.freq - a.freq;
        });
        
        idx = 0;
    }
    
    public void push(int val) // logn
    {
        map.put(val,map.getOrDefault(val,0)+1);
        pq.add(new pair(val,map.get(val),idx++));
    }
    
    public int pop()  //logn
    {
        pair p = pq.remove();
        map.put(p.val,map.get(p.val)-1);
        
        if(map.get(p.val) == 0)
            map.remove(p.val);
        
        return p.val;
    }
}

//Leetcode 407 -> Trapping Rain Water II
public int trapRainWater(int[][] heightMap) 
{
    int n = heightMap.length, m = heightMap[0].length;
        
    PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
        int i1 = a / m, j1 = a % m;
        int i2 = b / m, j2 = b % m;
            
        return heightMap[i1][j1] - heightMap[i2][j2];
    });
        
    boolean[][] vis = new boolean[n][m];
        
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < m; j++)
        {
            if(i == 0 || j == 0 || i == n-1 || j == m-1)
            {
                vis[i][j] = true;
                pq.add(i * m + j);
            }
        }
    }
        
    int[][] dir ={{0,-1},{1,0},{-1,0},{0,1}};
        
    int minBoun = 0, water = 0;
    while(pq.size() != 0)
    {
        int idx = pq.remove();
        int i = idx / m;
        int j = idx % m;
            
        minBoun = Math.max(minBoun,heightMap[i][j]);
        water += minBoun - heightMap[i][j];
            
        for(int d = 0; d < 4; d++)
        {
            int r = i + dir[d][0];
            int c = j + dir[d][1];
                
            if(r >= 0 && c >= 0 && r < n && c < m && !vis[r][c])
            {
                vis[r][c] = true;
                pq.add(r * m + c);
            }
        }            
    }
    return water;
}
}