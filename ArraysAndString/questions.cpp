#include <iostream>
#include <vector>
#include <list>

using namespace std;

void reverse(vector<int> &arr, int i, int j)
{
    while (i < j)
        swap(arr, i++, j--);
}

void rotateByK(vector<int> &arr, int r)
{
    int n = arr.size();

    // r %= n;
    // if(r < n)
    // r += n;

    r = (r % n + n) % n;

    reverse(arr, 0, n - 1);
    reverse(arr, n - r, n - 1);
    reverse(arr, 0, n - r - 1);
}

//Max Sum in the Configuration -> GFG
int max_sum(vector<int> &arr, int n)
{
    int sum = 0, sumWithIndex = 0;
    for (int i = 0; i < n; i++)
    {
        sum += arr[i];
        sumWithIndex += i * arr[i];
    }

    int maxAns = sumWithIndex;
    for (int i = 1; i < n; i++)
    {
        sumWithIndex = sumWithIndex - sum + arr[i - 1] * n;
        maxAns = max(maxAns, sumWithIndex);
    }
    return maxAns;
}

//Leetcode 239 -> Maximum Sliding Window
vector<int> maxSlidingWindow(vector<int> &arr, int k)
{
    list<int> deque;
    int n = arr.size();
    vector<int> ans;

    for (int i = 0; i < n; i++)
    {
        while (deque.size() != 0 && deque.front() <= i - k)
            deque.pop_front();

        while (deque.size() != 0 && arr[deque.back()] <= arr[i])
            deque.pop_back();

        deque.push_back(i);

        if (i >= k - 1)
            ans.push_back(arr[deque.front()]);
    }
    return ans;
}

//Leetcode 565 -> Array Nesting (Stimulation of DFS cycle detection)
int arrayNesting(vector<int> &arr)
{
    int len = 0, maxLen = 0, curr = 0, prev = 0;

    for (int i = 0; i < arr.size(); i++)
    {
        if (arr[i] == -1)
            continue;

        curr = i, len = 0;
        while (arr[curr] != -1)
        {
            prev = curr;
            curr = arr[curr];
            arr[prev] = -1;
            len++;
        }
        maxLen = max(maxLen, len);
    }
    return maxLen;
}