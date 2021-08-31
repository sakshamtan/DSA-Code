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

//Leetcode 167 -> Two Sum II - Input Array is Sorted
public int[] twoSum(int[] arr, int target) 
{
    int si = 0, ei = arr.length-1;
    
    while(si < ei)
    {
        int sum = arr[si] + arr[ei];
        if(sum == target)
            return new int[]{si+1,ei+1};
        if(sum < target)
            si++;
        else
            ei--;
    }
    return new int[0];
}

//Leetcode 658 -> Find K Closest Elements
public int insertPosition(int[] arr,int data)
{
    int n = arr.length, si = 0, ei = n-1;
    while(si <= ei)
    {
        int mid = (si + ei) / 2;
        if(arr[mid] <= data)
            si = mid+1;
        else
            ei = mid-1;
    }
    return si;  // last mei si insertPosition mei pohoch jaata hai
}

//Leetcode function
public List<Integer> findClosestElements(int[] arr, int k, int x) 
{
    ArrayList<Integer> ans = new ArrayList<>();
    for(int ele : arr)
        ans.add(ele);
        
    int n = arr.length;
    int idx = insertPosition(arr,x);
        
    int lr = Math.max(0,idx - k);
    int rr = Math.min(n-1,idx + k);
    
    if(x <= arr[0])
        return ans.subList(0,k);
    else if(x >= arr[n-1])
        return ans.subList(n-k,n);
    else
    {
        while((rr - lr) + 1 > k)
        {
            if(x - arr[lr] > arr[rr] - x)
                lr++;
            else 
                rr--;
        }
        return ans.subList(lr,rr+1);
    }
}

//Leetcode 300 -> Longest Increasing Subsequence
public int insertPosition(ArrayList<Integer> arr,int data)
{
    int si = 0, ei = arr.size()-1;
    while(si <= ei)
    {
        int mid = (si + ei) / 2;
        if(arr.get(mid) <= data)
            si = mid+1;
        else
            ei = mid-1;
    }

    int insertPos = si;
    int lastIndex = si-1;
        
    return lastIndex >= 0 && arr.get(lastIndex) == data ? lastIndex : insertPos; // if Present then its lastIndex if not then insertPosition
}

//Leetcode function
public int lengthOfLIS(int[] nums)
{
    int n = nums.length;
    if(n <= 1)
        return n;
        
    ArrayList<Integer> list = new ArrayList<>();
    for(int ele : nums)
    {
        int loc = insertPosition(list,ele);
        if(loc == list.size())
            list.add(ele);
        else
            list.set(loc,ele);
    }
    return list.size();
}

//Leetcode 875 -> Koko Eating Bananas
public boolean isPossibleToEat(int[] arr,int hour,int eatingSpeed)
{
    int hr = 0;
    for(int i = arr.length-1; i >= 0; i--)
    {
        hr += Math.ceil(arr[i] / (eatingSpeed * 1.0));
        if(hr > hour)
            return false;
    }
    return true;
}

//Leetcode function
public int minEatingSpeed(int[] piles, int h) 
{
    int si = 1, ei = (int)1e9;
    
    while(si < ei)
    {
        int eatingSpeed = (si + ei) / 2;
        if(!isPossibleToEat(piles,h,eatingSpeed))
            si = eatingSpeed + 1;   // speed kam hai to speed badaani hai
        else
            ei = eatingSpeed;  // nhi to speed kam krke dekhna hai aur chotta ans mil jaaye to
    }
    return si;
}

//Leetcode 1011 -> Capacity to Ship Packages Within D days
public boolean isPossibleToShip(int[] weights,int capacity,int days)
{
    int d = 1, totalWeightPerDay = 0;
    for(int w : weights)
    {
        totalWeightPerDay += w;
        if(totalWeightPerDay > capacity)
        {
            d++;
            totalWeightPerDay = w;   
        }
            
        if(d > days)
            return false;
    }
        
    return true;        
}

//Leetcode function
public int shipWithinDays(int[] weights, int days) 
{
    int maxEle = 0, sum = 0;
    for(int w : weights)
    {
        maxEle = Math.max(maxEle,w);
        sum += w;
    }
            
    int si = maxEle, ei = sum;
        
    while(si < ei)
    {
        int capacity = (si + ei) / 2;
        if(!isPossibleToShip(weights,capacity,days))
            si = capacity + 1;
        else
            ei = capacity;
    }
    return si;
}

//Method 2 -> Now range 1 se 1e7 tk rakhenge 
public boolean isPossibleToShip_02(int[] weights,int capacity,int days)
{
    int d = 1, totalWeightPerDay = 0;
    for(int w : weights)
    {
        if(w > capacity)   // agr present weight capacity se bada hogya to hum usko carry nhi kr skte
            return false;
        totalWeightPerDay += w;
        if(totalWeightPerDay > capacity)
        {
            d++;
            totalWeightPerDay = w;   
        }
            
        if(d > days)
            return false;
    }
        
    return true;        
}

//Leetcode function
public int shipWithinDays_02(int[] weights, int days) 
{
    int si = 1, ei = (int)1e7;  
        
    while(si < ei)
    {
        int capacity = (si + ei) / 2;
        if(!isPossibleToShip_02(weights,capacity,days))
            si = capacity + 1;
        else
            ei = capacity;
    }
    return si;
}

//Max Cake Area Question
public boolean isPossibleToServeCake(int[] radiusArray,int cakeArea,int guests)
{
    int g = 0;
    for(int i = radiusArray.length-1; i >= 0; i--)
    {
        double area = Math.PI * radiusArray[i] * radiusArray[i];
        g += Math.floor(area / cakeArea);
        
        if(g >= guests)
        return true;
    }
    return false;
}

public static double maximumAreaCake(int[] radius,int guests)
{
    int n = radius.length;
    double si = 0.0, ei = 1e7;

    while((ei - si) > 1e-5)
    {
        double cakeArea = (si + ei) / 2.0;

        if(!isPossibleToServeCake(radius,cakeArea,guests))
            ei = cakeArea - 1e-5;
        else
            si = cakeArea;
    }
    return si;
}

//Leetcode 774 -> Minimize Max Distance to Gas Distance(Lintcode 848)
public boolean isItCorrectPenalty(int[] arr,double distance,int k)
{
    int noOfGasStation = 0, n = arr.length;
    for(int i = 1; i < n; i++)
    {
        noOfGasStation += (arr[i] - arr[i-1]) / distance;
        if(noOfGasStation > k)
            return false;
    }
    return true;
}

//Leetcode function
public double minmaxGasDist(int[] stations, int k) 
{
    double si = 0.0, ei = 1e9;
    while((ei - si) > 1e-6)
    {
        double distance = (si + ei) / 2;
        if(!isItCorrectPenalty(stations,distance,k))
            si = distance + 1e-6;
        else
            ei = distance;
    }
    return si;
}