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
    ans.resize(n, -1);

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
    ans.resize(n, -1);

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

//Leetcode 1249 -> Minimum Remove to make Valid Parenthesis
string minRemoveToMakeValid(string s)
{
    int n = s.length();
    vector<int> st; // vector se stack ki functionality use krli

    for (int i = 0; i < n; i++)
    {
        char ch = s[i];
        if (ch == '(')
            st.push_back(i);
        else if (ch == ')')
        {
            if (st.size() != 0 && s[st.back()] == '(') // balance hogya closing bracket to opening bracket pop kra denge st se
                st.pop_back();
            else
                st.push_back(i); // closing bracket agr st khaali ho jaaye to push krdenge as ab ye remove to krna hi pdega as balance nhi ho paayega kbhi
        }
    }
    string ans = "";
    int idx = 0;
    for (int i = 0; i < n; i++)
    {
        if (idx < st.size() && st[idx] == i) // idx++ hokr st ke size se bda ho jaaye to error aa jaayega st[idx] se
        {
            idx++;
            continue;
        }

        ans.push_back(s[i]);
    }
    return ans;
}

//Leetcode 735 -> Asteroid Collision
vector<int> asteroidCollision(vector<int> &arr)
{
    stack<int> st;
    for (int ele : arr)
    {
        if (ele > 0)
        {
            st.push(ele);
            continue;
        }

        while (st.size() != 0 && st.top() > 0 && st.top() < -ele)
            st.pop();
        if (st.size() != 0 && st.top() == -ele)
            st.pop();
        else if (st.size() == 0 || st.top() < 0)
            st.push(ele);
    }

    int n = st.size();
    vector<int> ans(n, 0);
    int idx = n - 1;
    while (st.size() != 0)
    {
        ans[idx--] = st.top();
        st.pop();
    }
    return ans;
}

//Leetcode 84 -> Largest Rectangle in Histogram
//Using NSOL and NSOR
int largestRectangleArea(vector<int> &heights)
{
    int n = heights.size();
    vector<int> nsol;
    vector<int> nsor;

    NSOL(heights, nsol);
    NSOR(heights, nsor);

    int maxArea = 0;
    for (int i = 0; i < n; i++)
    {
        int h = heights[i];
        int w = nsor[i] - nsol[i] - 1; // nsor and nsol dono ko include nhi krna for a particular bar.

        maxArea = max(maxArea, h * w);
    }
    return maxArea;
}

//Better approach -> time -> 2n and space -> n
int largestRectangleArea_02(vector<int> &heights)
{
    int n = heights.size();
    stack<int> st;
    st.push(-1);

    int maxArea = 0;
    for (int i = 0; i < n; i++)
    {
        while (st.top() != -1 && heights[st.top()] >= heights[i]) // increasing order ka stack mantain kr rhe hai
        {                                                         // so ek ele se neeche usse just chotta ele hoga means uski left boundary
            int idx = st.top();                                   // and jo usko pop krwa rha hoga vo uski right boundary.
            st.pop();

            int h = heights[idx];
            int w = i - st.top() - 1; // dono boundary ko exclude krna hai so -1

            maxArea = max(maxArea, h * w);
        }
        st.push(i);
    }

    while (st.top() != -1) // agr poora arr iterate krne ke baad bhi bach gya(incase of increasing array)
    {
        int idx = st.top();
        st.pop();

        int h = heights[idx];
        int w = n - st.top() - 1; // un sb ele ke liye right boundary = n and left boundary st mei unse just neeche

        maxArea = max(maxArea, h * w);
    }
    return maxArea;
}

//Leetcode 85 -> Maximal Rectangle -> uses largest Rectangle Area
int maximalRectangle(vector<vector<char> > &matrix)
{
    if (matrix.size() == 0 || matrix[0].size() == 0)
        return 0;
    int n = matrix.size();
    int m = matrix[0].size();
    vector<int> heights(m, 0);

    int maxRec = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            char ch = matrix[i][j];
            heights[j] = (ch == '1') ? heights[j] + 1 : 0;
        }
        maxRec = max(maxRec, largestRectangleArea(heights));
    }
    return maxRec;
}

//Leetcode 221 -> Maximal Square -> similar to maximal Rectangle just one additional condition
int largestSquareArea(vector<int> &heights)
{
    int n = heights.size();
    vector<int> nsol;
    vector<int> nsor;

    NSOL(heights, nsol);
    NSOR(heights, nsor);

    int maxArea = 0;
    for (int i = 0; i < n; i++)
    {
        int h = heights[i];
        int w = nsor[i] - nsol[i] - 1;

        maxArea = max(maxArea, h < w ? h * h : w * w); // h,w mei se minimum ka square hi max possible square bna skta for values of h,w
    }
    return maxArea;
}

//Leetcode function -> uses largestSquareArea -> same as largestRectangleArea
int maximalSquare(vector<vector<char> > &matrix)
{
    if (matrix.size() == 0 || matrix[0].size() == 0)
        return 0;
    int n = matrix.size();
    int m = matrix[0].size();
    vector<int> heights(m, 0);

    int maxSq = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            char ch = matrix[i][j];
            heights[j] = (ch == '1') ? heights[j] + 1 : 0;
        }
        maxSq = max(maxSq, largestSquareArea(heights));
    }
    return maxSq;
}

//Leetcode 402 -> Remove K digits
string removeKdigits(string num, int k)
{
    vector<char> st;

    for (int i = 0; i < num.length(); i++)
    {
        char ch = num[i];
        while (st.size() != 0 && st.back() > ch && k > 0)
        {
            st.pop_back();
            k--;
        }
        st.push_back(ch);
    }

    while (k-- > 0) // agr k = 0 naa ho iterate krne ke baad bhi to alag se baad mei remove krna pdega
        st.pop_back();

    string ans = "";
    bool flag = false;
    for (char ch : st)
    {
        if (ch == '0' && flag == false) // leading zeroes remove krne ke liye
            continue;

        flag = true;
        ans += ch;
    }
    return ans.length() == 0 ? "0" : ans; // agr last mei string bachi hi nhi to "0" return krna hai
}

//Leetcode 316 -> Remove Duplicate Letters (same as Leetcode 1081 -> Smallest Subsequence of Distinct Characters)
string removeDuplicateLetters(string s)
{
    string st = "";                   // equivalent to stack
    vector<bool> presInSt(26, false); // which chars are present in stack to ensure at most once a char comes in the ans
    vector<int> freq(26, 0);

    for (char ch : s)
    {
        freq[ch - 'a']++;
    }

    for (char ch : s)
    {

        freq[ch - 'a']--;

        if (presInSt[ch - 'a']) // agr st mei already present hai ch to usse na push krna hai aur na pop
            continue;
        while (st.length() != 0 && st.back() > ch && freq[st.back() - 'a'] > 0)
        {
            char rch = st.back();
            st.pop_back();
            presInSt[rch - 'a'] = false;
        }

        presInSt[ch - 'a'] = true; // st mei char push krte hi true mark.
        st.push_back(ch);
    }
    return st;
}

//Leetcode 42 -> Trapping Rain Water
//Simplest Solution -> Least Optimized
int trap_01(vector<int> &height)
{
    int n = height.size();
    vector<int> lHeight(n, 0); // Greatest height in left of the bar
    vector<int> rHeight(n, 0); // Greates height in right of the bar

    int left = 0;
    for (int i = 0; i < n; i++)
    {
        lHeight[i] = max(height[i], left);
        left = lHeight[i];
    }

    int right = 0;
    for (int i = n - 1; i >= 0; i--)
    {
        rHeight[i] = max(height[i], right);
        right = rHeight[i];
    }

    int totalWater = 0;
    for (int i = 0; i < n; i++)
    {
        totalWater += min(lHeight[i], rHeight[i]) - height[i];
    }
    return totalWater;
}

//Second method -> using stack similar to largest Rectangle in Histogram
int trap_02(vector<int> &height)
{
    int n = height.size();
    stack<int> st;

    int totalWater = 0;
    for (int i = 0; i < n; i++)
    {
        while (st.size() != 0 && height[st.top()] < height[i])
        {
            int idx = st.top();
            st.pop();

            if (st.size() == 0) // agr size pop krte hi 0 hogya means no leftBoundary exist to totalWater nhi calculate krna
                break;

            int h = height[idx];
            int w = i - st.top() - 1;

            totalWater += (min(height[st.top()], height[i]) - h) * w;
        }
        st.push(i);
    }
    return totalWater;
}
