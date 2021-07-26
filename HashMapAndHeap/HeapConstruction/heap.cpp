#include <iostream>
#include <vector>

using namespace std;

class heap
{
    private:
    vector<int> arr;
    bool isMaxHeap;

    void constructHeap()
    {
        for(int i = this->arr.size()-1; i >= 0; i--)
        {
            downHeapify(i);
        }
    }

    void defaultValue(bool isMaxHeap)
    {
        this->isMaxHeap = isMaxHeap;
    }

    bool compareTo(bool isMaxHeap)
    {

    }

    public:
    heap()
    {
        defaultValue(true);
    }
    
    heap(isMaxHeap)
    {
        defaultValue(isMaxHeap);
    }

    heap(vector<int>& arr,bool isMaxHeap)
    {
        defaultValue(isMaxHeap);

        for(int ele : arr)
        {
            this->arr.push_back(ele);
        }

        constructHeap();
    }

    int size()
    {
        return this->arr.size();
    }

    bool isEmpty()
    {
        return this->arr.size() == 0;
    }

    void add(int data)
    {

    }

    int remove()
    {
        int rv = this.arr[0];

        int n = this.arr.size();
        swap(this->arr[0],this->arr[n-1]);

        this->arr.pop_back();
        downHeapify(0);

        return rv;
    }

    int top()
    {
        return this->arr[0];
    }

    private:
    void downHeapify(int pi)
    {
        int maxIdx = pi;
        int lci = (pi * 2) + 1;
        int rci = (pi * 2) + 2;

        if(lci < this->arr.size() && this.arr[lci] > this.arr[maxIdx])
            maxIdx = lci;
        if(rci < this->arr.size() && this.arr[rci] > this.arr[maxIdx])
            maxIdx = rci;

        if(maxIdx != pi)
        {
            swap(this->arr[pi],this->arr[maxIdx]);
            downHeapify(maxIdx);
        }
    }

};