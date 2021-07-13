#include <iostream>
#include <stack>
#include<vector>

using namespace std;

//N : next , G : greater , S : smaller , R : right , L : left
void NGOR(vector<int>& arr,vector<int>& ans)
{
    int n = arr.size();
    ans.resize(n,n);

    stack<int> st;
    for(int i = 0; i < n; i++)
    {
        while(st.size() != 0 && arr[st.top()] < arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }
        st.push(i);
    }
}

//Next Greater on Left -> so bs for loop ki direction opposite krni hai
void NGOL(vector<int>& arr,vector<int>& ans)
{
    int n = arr.size();
    ans.resize(n,n);
    
    stack<int> st;
    for(int i = n-1; i >= 0; i--)
    {
        while(st.size() != 0 && arr[st.top()] < arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }
        st.push(i);
    }
}

//Next smaller on right -> bs comparison ka sign change krna hai in NGOR mei
void NSOR(vector<int>& arr,vector<int>& ans)
{
    int n = arr.size();
    ans.resize(n,n);

    stack<int> st;
    for(int i = 0; i < n; i++)
    {
        while(st.size() != 0 && arr[st.top()] > arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }
        st.push(i);
    }
}

void NSOL(vector<int>& arr,vector<int>& ans)
{
    int n = arr.size();
    ans.resize(n,n);

    stack<int> st;
    for(int i = n-1; i >= 0; i--)
    {
        while(st.size() != 0 && arr[st.top()] > arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }
        st.push(i);
    }
}