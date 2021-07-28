#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool compareTo(vector<int> &arr, bool isIncreasing, int i, int j)
{
    if (isIncreasing)
        return arr[i] > arr[j];
    else
        return arr[i] < arr[j];
}

void downHeapify(vector<int> &arr, bool isIncreasing, int pi, int li)
{
    int maxIdx = pi;
    int lci = 2 * pi + 1;
    int rci = 2 * pi + 2;

    if (lci <= li && compareTo(arr, isIncreasing, lci, maxIdx))
        maxIdx = lci;
    if (rci <= li && compareTo(arr, isIncreasing, rci, maxIdx))
        maxIdx = rci;

    if (maxIdx != pi)
    {
        swap(arr[maxIdx], arr[pi]);
        downHeapify(arr, isIncreasing, maxIdx, li);
    }
}

void heapSort(vector<int> &arr, bool isIncreasing)
{
    int li = arr.size() - 1;
    for (int i = li; i >= 0; i--) // constructing heap
        downHeapify(arr, isIncreasing, i, li);

    while (li >= 0)
    {
        swap(arr[0], arr[li--]);
        downHeapify(arr, isIncreasing, 0, li);
    }
}

int main()
{
    vector<int> arr = {10, 20, 30, -2, -3, -4, 5, 6, 7, 9, 22, 11, 13};
    heapSort(arr, true);

    for (int ele : arr)
        cout << ele << " ";

    return 0;
}