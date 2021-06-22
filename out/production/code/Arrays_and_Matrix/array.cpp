#include<iostream>//rev
#include<vector>
using namespace std;

void input (vector<int> &arr)
{
    for (int i = 0; i < arr.size(); i++)
    {
        cin>>arr[i];
    }
}
void display(vector<int> &arr)
{
    for (int ele : arr)
    {
        cout<<ele<<" ";
    }
    cout<<endl;
}
bool find (vector<int>& arr,int data)
{
    for(int i = 0; i <= arr.size(); i++)
    {
        if (data == arr[i])
        return true;
    }
    return false;
}
int maximum (vector<int> &arr)
{
    int max_ = arr[0];
    for (int i = 1; i < arr.size(); i++)
    {
        max_ = max(max_,arr[i]);
    }
    return max_;
}
int minimum (vector<int> &arr)
{
    int min_ = arr[0];
    for (int i = 1; i < arr.size(); i++)
    {
        min_ = min(min_,arr[i]);
    }
    return min_;
}
void swap_(vector<int> &arr, int i, int j)
{
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
void reverse (vector<int> &arr ,int li, int ui)
{
    while(li < ui)
    {
    swap_(arr,li,ui);
    li++;
    ui--;
    }
}
void rotate (vector<int> &arr,int r) //using reverse function.
{
    r %= arr.size();
    if (r < 0)
    r += arr.size();

    reverse(arr,0,r-1);
    reverse(arr,r,arr.size()-1);
    reverse(arr,0,arr.size()-1);
}
vector<int> inverse(vector<int> &arr)
{
    vector<int> inv(arr.size(),0);
    for(int i = 0; i < arr.size();i++)
    {
     int val = a[i];
     inv[val] = i;
    }
    return inv;
}
int kadanes(vector<int> &arr)//longest sum in sub-array.
{
    int csum = arr[0];
    int osum = arr[0];
    for (int i = 0; i < arr.size(); i++)
    {
        if (csum < 0)
        {
            csum = arr[i];
        }
        else{
            csum += arr[i];
        }
        if (csum > osum)
        {
            osum = csum;
        }
    }
    return osum;
}

int main(int args, char **argv)
{
    int size;
    cin>>size;
    vector<int> arr (size,0);
    input(arr);
    display(arr);

    //cout<<"is element found:"<<(boolalpha)<<find(arr,6)<<endl;
    //cout<<"Maximum ele:"<<maximum(arr)<<endl;

    //cout<<"Rotated Array:"<<endl;
    //rotate(arr,3);
    //display(arr);
    cout<<kadanes(arr);
    return 0;
}

