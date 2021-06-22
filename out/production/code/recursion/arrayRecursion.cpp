#include <iostream>
#include <vector>
#include <climits>

using namespace std;

void display (vector<int> &arr,int vidx)
{
    if (vidx == arr.size())
    return;

    cout<<arr[vidx]<<" ";
    display(arr,vidx+1);
}

void displayR (vector<int> &arr,int vidx)
{
    if (vidx == arr.size())
    return;

    displayR(arr,vidx+1);
    cout<<arr[vidx]<<" ";

}

int maximum (vector<int>&arr,int vidx)
{
    if (vidx==arr.size())
    {
    return INT_MIN;
    }

int recAns = maximum(arr,vidx+1);

return max(arr[vidx],recAns);
}

int minimum (vector<int>&arr,int vidx)
{
    if (vidx == arr.size())
    {
    return INT_MAX;
    }

int recAns = minimum(arr,vidx+1);

return min(arr[vidx],recAns);
}

bool find (vector<int> &arr,int data,int vidx)
{
    if (vidx == arr.size())
    return false;

    if (arr[vidx]==data) 
    return true;

    return find(arr,vidx+1,data);
}

int firstIndex (vector<int> &arr, int data,int vidx)
{
    if (vidx == arr.size())
    return -1;
    
    if (arr[vidx] == data)
    return vidx;

    return firstIndex(arr,data,vidx+1);
}

int lastIndex (vector<int> &arr,int data,int vidx)
{
    if (vidx == arr.size())
    return -1;

    int recAns = lastIndex(arr,data,vidx+1);
    if (recAns != -1)
    return recAns;
    else{
        if (arr[vidx] == data)
        return vidx;
    }
    return -1;
}

int main(int args,char**argv)
{
    vector<int> arr = {5,9,3,12,9,1};
    // input(arr);
    // displayR(arr,0);
    // display(arr,0);
    // minimum(arr,0);
    // cout<<maximum(arr,0);
    // cout<<boolalpha<<find(arr,9,0);
    cout<<firstIndex(arr,9,0)<<endl;
    cout<<lastIndex(arr,9,0)<<endl;
}