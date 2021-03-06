#include <iostream>
#include <vector>

using namespace std;

int segregateData(vector<int>& arr,int si,int ei,int pivot)
{
    swap(arr[pivot],arr[ei]);
    int p = si-1, itr = si;
    while(itr <= ei)
    {
        if(arr[itr] <= arr[ei])
            swap(arr[++p],arr[itr]);
        itr++;
    }
    return p;
}

void quickSort(vector<int>& arr,int si,int ei)
{
    if(si > ei)
    return;

    int pivot = ei;
    int pIdx = segregateData(arr,si,ei,pivot);

    // int len = (ei - si + 1);
    // int pivot = rand() % len + si;   // random idx bhi pass krenge agr as pivot to vo bhi shi chlega

    quickSort(arr,si,pIdx-1);
    quickSort(arr,pIdx+1,ei);
}

int main()
{
    vector<int> arr = {-12, 2, 7, 4, 34, 23, 0, -1, 1, -59, 16, 7, 4, 3, 2};
    quickSort(arr,0,arr.size()-1);

    for(int ele : arr)
        cout << ele << " ";
    cout << endl;

    return 0;
}
