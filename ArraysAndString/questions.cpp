#include <iostream>
#include <vector>


using namespace std;

void reverse(vector<int>& arr,int i,int j)
{
    while(i < j)
    swap(arr,i++,j--);
}

void rotateByK(vector<int>& arr,int r)
{
    int n = arr.size();
    
    // r %= n;
    // if(r < n)
    // r += n;

    r = (r % n + n) % n;

    reverse(arr,0,n-1);
    reverse(arr,n-r,n-1);
    reverse(arr,0,n-r-1);

}