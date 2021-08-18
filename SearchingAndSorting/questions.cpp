#include <iostream>
#include <vector>

using namespace std;

int binarySearch(vector<int> arr,int data)
{
    int n = arr.size(), si = 0, ei = n - 1;

    while(si <= ei)  // equal hone tk chlaate hai as tb mid check nhi hua hota
    {
        int mid = (si + ei) / 2;  // use si + (ei - si)/2 when chances of overflowing
        if(arr[mid] == data)
            return mid;
        else if(arr[mid] < data)
            si = mid+1;
        else
            ei = mid-1;
    }

    return -1;
}