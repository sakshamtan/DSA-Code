#include <iostream>
#include <vector>

using namespace std;

int binarySearch(vector<int> &arr, int data)
{
    int n = arr.size(), si = 0, ei = n - 1;

    while (si <= ei) // equal hone tk chlaate hai as tb mid check nhi hua hota
    {
        int mid = (si + ei) / 2; // use si + (ei - si)/2 when chances of overflowing
        if (arr[mid] == data)
            return mid;
        else if (arr[mid] < data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return -1;
}

int firstIndex(vector<int> &arr, int data)
{
    int n = arr.size(), si = 0, ei = n - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] == data)
        {
            if (mid - 1 >= 0 && arr[mid - 1] == data)
                ei = mid - 1;
            else
                return mid;
        }
        else if (arr[mid] < data)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    return -1;
}

int lastIndex(vector<int> &arr, int data)
{
    int n = arr.size(), si = 0, ei = n - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] == data)
        {
            if (mid + 1 < n && arr[mid + 1] == data)
                si = mid + 1;
            else
                return mid;
        }
        else if (arr[mid] < data)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    return -1;
}

//Leetcode 34 -> Find First and Last Position of Element in Sorted Array
vector<int> &searchRange(vector<int> &arr, int data)
{
    return new vector<int> &{firstIndex(arr, data), lastIndex(arr, data)};
}

//Important
int insertLocation(vector<int> &arr, int data)
{
    int n = arr.size(), si = 0, ei = n - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;

        if (arr[mid] <= data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return si;
}

int perfectPosOfElement(vector<int> &arr, int data)
{
    int insertPos = insertLocation(arr, data);
    int lastIndex = insertPos - 1;

    return (lastIndex >= 0 && arr[lastIndex] == data) ? lastIndex : insertPos;
}

//firstIndex using the concept used in insertLocation function
int firstIndex_02(vector<int> &arr, int data)
{
    int n = arr.size(), si = 0, ei = n - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[data] < mid) // equal to mei ei ko move krenge
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return si < n && arr[si] == data ? si : -1;
}

//LastIndex using the concept used in insertLocation function
int lastIndex_02(vector<int> &arr, int data)
{
    int n = arr.size(), si = 0, ei = n - 1;
    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] <= data) // equal to pe bhi si ko move krenge
            si = mid + 1;
        else
            ei = mid - 1;
    }

    si--;
    return si >= 0 && arr[si] == data ? si : -1;
}

//Return the nearest element present in arr to data
// Always aftr the while loop -> arr[ei] <= data < arr[si]
int nearestElement(vector<int> &arr, int data)
{
    int n = arr.size(), si = 0, ei = n - 1;

    if (n == 0)
        return 0;

    if (data <= arr[0] || data >= arr[n - 1])
        return data <= arr[0] ? arr[0] : arr[1];

    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] <= data)
            si = mid + 1; // si is at ceil value after while loop
        else
            ei = mid - 1; // ei is at floor value after while loop
    }

    return data - arr[ei] <= arr[si] - data ? arr[ei] : arr[si];
}

//Leetcode 74 -> Search a 2D matrix
bool searchMatrix(vector<vector<int> > &matrix, int target)
{
    int n = matrix.size(), m = matrix[0].size(), si = 0, ei = n * m - 1;

    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        int r = mid / m, c = mid % m;

        if (matrix[r][c] == target)
            return true;
        else if (matrix[r][c] < target)
            si = mid + 1;
        else
            ei = mid - 1;
    }
    return false;
}

//Leetcode 240 -> Search a 2D Matrix II
bool searchMatrix(vector<vector<int>> &matrix, int target)
{
    int n = matrix.size(), m = matrix[0].size(), r = n - 1, c = 0;

    while (r >= 0 && c < m)
    {
        int ele = matrix[r][c];
        if (ele == target)
            return true;

        else if (ele < target)
            c++;
        else
            r--;
    }
    return false;
}