import java.util.ArrayList;

public class heap{

private ArrayList<Integer> arr; // DS of our Heap
private boolean isMaxHeap = true; // to tell if maxHeap is implemented or minHeap

void constructHeap()
{
    for(int i = arr.size()-1; i >= 0; i--)
    {
        downHeapify(i);
    }
}

void defaultValue(boolean isMaxHeap) // function to initialize heap
{
    this.arr = new ArrayList<>();
    this.isMaxHeap = isMaxHeap;
}

public heap()
{
    defaultValue(true); // by default maxHeap bnaaenge
}

public heap(boolean isMaxHeap)
{
    defaultValue(isMaxHeap);
}

public heap(int[] arr,boolean isMaxHeap) // constructor to convert arr into heap
{
    defaultValue(isMaxHeap);

    for(int ele : arr)
    {
        this.arr.add(ele); // apne DS arrayList mei copy krlia data
    }

    constructHeap();
}

//This function works as a comparator of PQ hum is function se minHeap and maxHeap ek hi code se implement kr rhe hai
private boolean compareTo(int i, int j) // hamesha i ko j se compare krenge 
{
    if(this.isMaxHeap)
        return this.arr.get(i) > this.arr.get(j);
    
    else
        return this.arr.get(i) < this.arr.get(j);
}

public int size()
{
    return this.arr.size();
}

public boolean isEmpty()
{
    return this.arr.size() == 0;
}

public void add(int data)
{
    this.arr.add(data);
    int n = this.arr.size();

    upHeapify(n-1);
}

public int remove() // O(Logn)
{
    int rv = this.arr.get(0);

    int n = this.arr.size();
    swap(0,n-1);

    this.arr.remove(n-1); // 0th ele ko arr ke last mei pohocha dia and last idx ko remove to make O(1) operation.

    downHeapify(0); // 0th pe swap hua to vaha heap ki condition bigad gyi hogyi so downHeapify -> Logn
    return rv;
}

public int top()
{
    return this.arr.get(0);
}

private void swap(int i,int j)
{
    int temp = arr.get(i);
    this.arr.set(i,arr.get(j));
    this.arr.set(j,temp);
}

private void downHeapify(int pi) // pi idx pe heap bna deta hai given pi ke lc and rc already heap ho
{
    int maxIdx = pi;
    int lci = (pi * 2) + 1;
    int rci = (pi * 2) + 2;

    if(lci < this.arr.size() && compareTo(lci,maxIdx))
        maxIdx = lci;
    
    if(rci < this.arr.size() && compareTo(rci,maxIdx))
        maxIdx = rci;

    if(maxIdx != pi) // agr maxIdx update hua means heap ki condition satisfied nhi thi to bade ele se par ko swap
    {
        swap(pi,maxIdx);
        downHeapify(maxIdx); // maxIdx pe swap kra to vaha ki heap ki condition bigad skti hai to yaha bhi downHeapify call
    } 
}

private void upHeapify(int ci)
{
    int pi = (ci - 1) / 2;

    if(ci >= 0 && compareTo(ci,pi))
    {
        swap(pi,ci);
        upHeapify(pi);
    }
}
}