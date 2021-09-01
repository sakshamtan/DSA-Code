#include <iostream>
#include <vector>
#include <climits>

using namespace std;

//F -> vidx + 1 se end tk display krdega function.
void display(vector<int> &arr, int vidx)
{
    if (vidx == arr.size())
        return;

    cout << arr[vidx] << " ";
    display(arr, vidx + 1);
}

//F -> arr.length se vidx + 1 tk display krdega function.
void displayR(vector<int> &arr, int vidx)
{
    if (vidx == arr.size())
        return;

    displayR(arr, vidx + 1);
    cout << arr[vidx] << " ";
}

//F -> vidx + 1 se end tk maximum le aayega function.
int maximum(vector<int> &arr, int vidx)
{
    if (vidx == arr.size())
        return INT_MIN;

    int recAns = maximum(arr, vidx + 1);

    return max(arr[vidx], recAns);
}

int minimum(vector<int> &arr, int vidx)
{
    if (vidx == arr.size())
        return INT_MAX;

    int recAns = minimum(arr, vidx + 1);

    return min(arr[vidx], recAns);
}

//F -> vidx + 1 se end tk mei dhoondh aayega function.
bool find(vector<int> &arr, int data, int vidx)
{
    if (vidx == arr.size())
        return false;

    if (arr[vidx] == data)
        return true;

    return find(arr, vidx + 1, data);
}

//F -> vidx + 1 se end tk mei first index return krdega function.
//optimization -> call se pehle hi check krlena hai if first idx == data.
int firstIndex(vector<int> &arr, int data, int vidx)
{
    if (vidx == arr.size())
        return -1;

    if (arr[vidx] == data)
        return vidx;

    return firstIndex(arr, data, vidx + 1);
}

//F -> pehle call marke stack build krna hai and backtracking ke time check
// krna hai and first index return krna hai.
int lastIndex(vector<int> &arr, int data, int vidx)
{
    if (vidx == arr.size())
        return -1;

    int recAns = lastIndex(arr, data, vidx + 1);
    if (recAns != -1)
        return recAns;
    else
    {
        if (arr[vidx] == data)
            return vidx;
    }
    return -1;
}

//jaate huye count krne hai elements. Base case mei new arr -> arr[counts so far]
//Vaapis aate huye new arr mei check krke arr[csf] pr vidx daalna jb data ke equal ho.
Vector<int> allIndex(vector<int> &arr, int data, int vidx, int csf)
{
    if (vidx == arr.size())
    {
        vector<int> res(csf, 0);
        return res;
    }
    res = null;
    if (arr[vidx] == data)
    {
        res = allIndex(arr, data, vidx + 1, csf + 1);
        res[csf] = vidx;
    }
    else
    {
        res = allIndex(arr, data, vidx + 1, csf);
    }
    return res;
}

int main(int args, char **argv)
{
    vector<int> arr = {5, 9, 3, 12, 9, 1};
    // input(arr);
    // displayR(arr,0);
    // display(arr,0);
    // minimum(arr,0);
    // cout<<maximum(arr,0);
    // cout<<boolalpha<<find(arr,9,0);
    // cout<<firstIndex(arr,9,0)<<endl;
    // cout<<lastIndex(arr,9,0)<<endl;
    vector<int> ans = allIndex(arr, 9, 0, 0);
    for (int i = 0; i < ans.size(); i++)
        cout << ans[i] << " ";
}