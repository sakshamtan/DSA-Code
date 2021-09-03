#include <iostream>
#include <vector>
#include <unordered_set> //hashset.
#include <algorithm>     //for using sort function.

using namespace std;

//Leetcode 17 -> Letter Combinations of a Phone Number
vector<string> codes = {" ", " ", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
vector<string> letterCombinations(string digits)
{
    vector<string> ans;
    if (digits.length() == 0)
    {
        return ans;
    }
    ans = helper(digits);
    return ans;
}

vector<string> helper(string digits)
{
    if (digits.length() == 0)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    char ch = digits[0];
    vector<string> recAns = helper(digits.substr(1));
    vector<string> myAns;
    int chi = ch - '0';
    if (chi >= 2 && chi <= 9)
    {
        string code = codes[chi];
        for (int i = 0; i < code.length(); i++)
        {
            for (string rstr : recAns)
            {
                myAns.push_back(code[i] + rstr);
            }
        }
    }
    return myAns;
}

//Leetcode 46 -> permute in an array with duplicate ans allowed.
vector<vector<int> > res;
vector<int> smallAns;
void permute_(vector<int> &arr, int count, vector<bool> &vis)
{
    if (count == arr.size())
    {
        res.push_back(smallAns);
        return;
    }
    for (int i = 0; i < arr.size(); i++)
    {
        if (!vis[i])
        {
            vis[i] = true;
            smallAns.push_back(arr[i]);
            permute_(arr, count + 1, vis);
            smallAns.pop_back();
            vis[i] = false;
        }
    }
}

vector<vector<int> > permute(vector<int> &nums)
{
    vector<bool> vis(nums.size(), false);
    permute_(nums, 0, vis);
    return res;
}

//Leetcode 47 -> permute_Unique
vector<vector<int> > res;
vector<int> smallAns;
void permute_(vector<int> &arr, int count, vector<bool> &vis)
{
    if (count == arr.size())
    {
        res.push_back(smallAns);
        return;
    }
    for (int i = 0; i < arr.size(); i++)
    {
        if (!vis[i])
        {
            vis[i] = true;
            smallAns.push_back(arr[i]);
            permute_(arr, count + 1, vis);
            smallAns.pop_back();
            vis[i] = false;
        }
    }
}
vector<vector<int>> permute(vector<int> &nums)
{
    vector<bool> vis(nums.size(), false);
    permute_(nums, 0, vis);
    return res;
}

//Leetcode 47 better optimized approach
vector<vector<int>> res;
vector<int> smallAns;
void permute(vector<int> &arr, int count, vector<bool> &vis)
{
    if (count == arr.size())
    {
        res.push_back(smallAns);
        return;
    }
    int prev = -(int)1e8;
    for (int i = 0; i < arr.size(); i++)
    {
        if (!vis[i] && prev != arr[i])
        {
            vis[i] = true;
            smallAns.push_back(arr[i]);
            permute(arr, count + 1, vis);
            smallAns.pop_back();
            vis[i] = false;
            prev = arr[i];
        }
    }
}

//Leetcode function
vector<vector<int>> permuteUnique(vector<int> &nums)
{
    sort(nums.begin(), nums.end());
    vector<bool> vis(nums.size(), false);
    permute(nums, 0, vis);
    return res;
}

//Leetcode 39 -> Combination Sum
void coinChange_Combi_Infi(vector<int> &arr, int tar, int idx, vector<int> &ans, vector<vector<int> > &res)
{
    if (tar == 0)
    {
        res.push_back(ans);
        return;
    }
    for (int i = idx; i < arr.size(); i++)
    {
        if (tar - arr[i] >= 0)
        {
            ans.push_back(arr[i]);
            coinChange_Combi_Infi(arr, tar - arr[i], i, ans, res);
            ans.pop_back();
        }
    }
}

//Leetcode function
vector<vector<int> > combinationSum(vector<int> &candidates, int target)
{
    vector<int> ans;
    vector<vector<int> > res;
    coinChange_Combi_Infi(candidates, target, 0, ans, res);
    return res;
}
