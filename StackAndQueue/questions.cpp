#include <iostream>
#include <stack>
#include <vector>

using namespace std;

//N : next , G : greater , S : smaller , R : right , L : left
void NGOR(vector<int> &arr, vector<int> &ans)
{
    int n = arr.size();
    ans.resize(n, n);

    stack<int> st;
    for (int i = 0; i < n; i++)
    {
        while (st.size() != 0 && arr[st.top()] < arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }
        st.push(i);
    }
}

//Next Greater on Left -> so bs for loop ki direction opposite krni hai
void NGOL(vector<int> &arr, vector<int> &ans)
{
    int n = arr.size();
    ans.resize(n, n);

    stack<int> st;
    for (int i = n - 1; i >= 0; i--)
    {
        while (st.size() != 0 && arr[st.top()] < arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }
        st.push(i);
    }
}

//Next smaller on right -> bs comparison ka sign change krna hai in NGOR mei
void NSOR(vector<int> &arr, vector<int> &ans)
{
    int n = arr.size();
    ans.resize(n, n);

    stack<int> st;
    for (int i = 0; i < n; i++)
    {
        while (st.size() != 0 && arr[st.top()] > arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }
        st.push(i);
    }
}

void NSOL(vector<int> &arr, vector<int> &ans)
{
    int n = arr.size();
    ans.resize(n, n);

    stack<int> st;
    for (int i = n - 1; i >= 0; i--)
    {
        while (st.size() != 0 && arr[st.top()] > arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }
        st.push(i);
    }
}

//Leetcode 503 -> Next Greater Element II
vector<int> nextGreaterElements(vector<int> &arr)
{
    int n = arr.size();
    vector<int> ans(n, -1);
    stack<int> st;

    for (int i = 0; i < 2 * n; i++) // do baar arr pe iteration(as arr is circular)
    {
        while (st.size() != 0 && arr[st.top()] < arr[i % n])
        {
            ans[st.top()] = arr[i % n];
            st.pop();
        }
        if (i < n) // sirf pehli baar mei st mei push krne ki need hai elements ko
            st.push(i);
    }
    return ans;
}

//Leetcode 739 -> Daily Temperatures -> uses NGOR
vector<int> dailyTemperatures(vector<int> &arr)
{
    int n = arr.size();
    vector<int> ans = NGOR(arr);

    for (int i = 0; i < n; i++)
    {
        if (ans[i] != 0)
            ans[i] = ans[i] - i;
    }
    return ans;
}

//Leetcode 20 -> Valid Parenthesis
bool isValid(string s)
{
    stack<char> st;
    for (int i = 0; i < s.length(); i++)
    {
        char ch = s[i];
        if (ch == '(' || ch == '[' || ch == '{') //opening brakcets st mei push
            st.push(ch);
        else
        {
            if (st.size() == 0) // means no. of closing brackets > no. of opening brackets
                return false;
            else if (ch == ')' && st.top() != '(')
                return false;
            else if (ch == ']' && st.top() != '[')
                return false;
            else if (ch == '}' && st.top() != '{')
                return false;
            else
                st.pop();
        }
    }
    return st.size() == 0; // when st.size() != 0 even after iterating string means no. of opening brackets > no. of closing brackets.
}

//Leetcode 946 -> Validate Stack Sequences
bool validateStackSequences(vector<int> &pushed, vector<int> &popped)
{
    int i = 0;
    stack<int> st;
    for (int ele : pushed)
    {
        st.push(ele);
        while (st.size() != 0 && st.top() == popped[i])
        {
            st.pop();
            i++;
        }
    }
    return st.size() == 0;
}