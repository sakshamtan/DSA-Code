import java.util.ArrayList;
public  class heap{
    private ArrayList <Integer> arr;
    private boolean isMax = false;
    public heap()
    {
        this.arr = new ArrayList<>();
    }
    public void createHeap (int [] ar)
    {
        this.arr = new ArrayList<>();
        for (int ele : ar)
        this.arr.add(ele);

        for (int i = arr.size() - 1; i >= 0 ; i--)
        {
            downHeapify(i);
        }
    }
    public heap(int [] ar)
    {
        createHeap(ar);
    }
    public heap (int [] ar , boolean isMax)
    {
        this.isMax = isMax;
        createHeap(ar);
    }
    public void downHeapify (int idx)
    {
        int maxidx = idx;
        int lci = 2 * idx + 1;
        int rci = 2 * idx + 2;
        if (lci < arr.size() && compareTo(lci, maxidx) > 0)
        maxidx = lci;
        if (rci < arr.size() && compareTo(rci, maxidx) > 0)
        maxidx = rci;
        if (maxidx != idx)
        {
            swap(maxidx,idx);
            downHeapify(maxidx);
        }
    }
    public void swap (int i,int j)
    {
        int val1 = arr.get(i);
        int val2 = arr.get(j);

        arr.set(i,val2);
        arr.set(j,val1);
    }
    public void upHeapify (int cidx)
    {
        int pidx = (cidx-1)/2;
        if (arr.get(pidx) < arr.get(cidx))
        swap (pidx,cidx);
        upHeapify(pidx);
    }
    public void push (int val)
    {
        arr.add(val);
        upHeapify(arr.size() - 1);
    }
    public void pop ()
    {
        swap(0,arr.size()-1);
        arr.remove(arr.size() - 1);
        downHeapify(0);
    }
    public void update (int idx , int val)
    {
        arr.set(idx,val);
        upHeapify(idx);
        downHeapify(idx);
    }
    public int top()
    {
        return arr.get(0);
    }
    public int compareTo (int child , int par)
    {
        if (isMax)
        {
            return arr.get(child)-arr.get(par);
        }
        else{
            return arr.get(par)-arr.get(child);
        }
    }

}