#include <iostream>
#include <vector>
#include <unordered_set> 
#include <algorithm>     //for using sort function.

using namespace std;

//Leetcode 17 -> Letter Combinations of a Phone Number
vector<string> codes{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
vector<string> nokiaKeypad(string digits, int idx)
{
    if (idx == digits.length())
        return {""};

    char dig = digits[idx];
    vector<string> myAns;

    vector<string> recAns = nokiaKeypad(digits, idx + 1);
    for (string rstr : recAns)
    {
        string word = codes[dig - '0'];
        for (char ch : word)
            myAns.push_back(ch + rstr);
    }
    return myAns;
}

//Leetcode function
vector<string> letterCombinations(string digits)
{
    if (digits.length() == 0)
        return {};

    return nokiaKeypad(digits, 0);
}

//Leetcode 46 -> permute in an array with duplicate ans allowed.
void permute_(vector<int> &arr, int idx, vector<int> &smallAns, vector<vector<int> > &res, vector<bool> &vis)
{
    if (idx == arr.size())
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
            permute_(arr, idx + 1, smallAns, res, vis);
            smallAns.pop_back();
            vis[i] = false;
        }
    }
}

//Leetcode function
vector<vector<int> > permute(vector<int> &nums)
{
    vector<bool> vis(nums.size(), false);
    vector<int> smallAns;
    vector<vector<int> > res;
    permute_(nums, 0, smallAns, res, vis);

    return res;
}

//Leetcode 47 -> permute_Unique
void permuteUnique_(vector<int> &arr, int idx, vector<bool> &vis, vector<int> &smallAns, vector<vector<int> > &res)
{
    if (idx == arr.size())
    {
        res.push_back(smallAns);
        return;
    }

    unordered_set<int> set;

    for (int i = 0; i < arr.size(); i++)
    {
        if (!vis[i] && set.find(arr[i]) == set.end())
        {
            vis[i] = true;
            smallAns.push_back(arr[i]);
            set.insert(arr[i]);
            permuteUnique_(arr, idx + 1, vis, smallAns, res);
            vis[i] = false;
            smallAns.pop_back();
        }
    }
}

//Leetcode function
vector<vector<int> > permuteUnique(vector<int> &nums)
{
    vector<int> smallAns;
    vector<bool> vis(nums.size(), false);
    vector<vector<int> > res;

    permuteUnique_(nums, 0, vis, smallAns, res);
    return res;
}

//Leetcode 47 better optimized approach
void permuteUnique_(vector<int> &arr, int idx, vector<bool> &vis, vector<int> &smallAns, vector<vector<int> > &res)
{
    if (idx == arr.size())
    {
        res.push_back(smallAns);
        return;
    }

    int prev = -11; // -10 to +10 range of data is given
    for (int i = 0; i < arr.size(); i++)
    {
        if (!vis[i] && prev != arr[i])
        {
            vis[i] = true;
            smallAns.push_back(arr[i]);
            permuteUnique_(arr, idx + 1, vis, smallAns, res);
            smallAns.pop_back();
            vis[i] = false;
            prev = arr[i];
        }
    }
}

//Leetcode function
vector<vector<int> > permuteUnique(vector<int> &nums)
{
    vector<bool> vis(nums.size(), false);
    vector<int> smallAns;
    vector<vector<int> > res;

    sort(nums.begin(), nums.end());
    permuteUnique_(nums, 0, vis, smallAns, res);
    return res;
}

// Leetcode 77 -> Combinations -> Same faith as permutations just i = idx + 1 in recursion to get unique combinations
void combine_(int n, int k, int idx, vector<int>& smallAns, vector<vector<int>>& ans) {
    if(smallAns.size() == k) {
        ans.push_back(smallAns);
        return;
    }

    for(int i = idx; i <= n; i++) {
        smallAns.push_back(i);
        combine_(n, k, i + 1, smallAns, ans);
        smallAns.pop_back();
    }
} 

// Leetcode function
vector<vector<int>> combine(int n, int k) {
    vector<int> smallAns;
    vector<vector<int>> ans;

    combine_(n, k, 1, smallAns, ans);
    return ans;     
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

//Leetcode 78 -> Subsets -> CoinChangeCombinationSingle for loop method
void subsets_01(vector<int> &arr, int idx, vector<int> &smallAns, vector<vector<int> > &res)
{
    if (idx >= arr.size())
        return;

    for (int i = idx; i < arr.size(); i++)
    {
        smallAns.push_back(arr[i]);
        res.push_back(smallAns);
        subsets_01(arr, i + 1, smallAns, res);
        smallAns.pop_back();
    }
}

//using subsequence method -> nci method is better as height of tree is lesser in that case
void subsets_02(vector<int> &arr, int idx, vector<int> &smallAns, vector<vector<int> > &res)
{
    if (idx >= arr.size())
    {
        res.push_back(smallAns);
        return;
    }

    smallAns.push_back(arr[idx]);
    subsets_01(arr, idx + 1, smallAns, res);
    smallAns.pop_back();

    subsets_01(arr, idx + 1, smallAns, res);
}

//Leetcode function
vector<vector<int> > subsets(vector<int> &nums)
{
    vector<int> smallAns;
    vector<vector<int> > res;
    res.push_back({});
    subsets_01(nums, 0, smallAns, res);

    return res;
}

//Leetcode 90 -> Subsets II -> Duplicate sets not allowed
void subsetsDupli(vector<int> &arr, int idx, vector<int> &smallAns, vector<vector<int> > &res)
{
    // if(idx >= arr.size())
    //     return;

    int prev = -11;
    for (int i = idx; i < arr.size(); i++)
    {
        if (prev == arr[i])
            continue;

        smallAns.push_back(arr[i]);
        res.push_back(smallAns);
        subsetsDupli(arr, i + 1, smallAns, res);
        smallAns.pop_back();
        prev = arr[i];
    }
}

//Leetcode function
vector<vector<int> > subsetsWithDup(vector<int> &nums)
{
    vector<int> smallAns;
    vector<vector<int> > res;
    res.push_back({});
    sort(nums.begin(), nums.end());  // sort krlia and if prev == curr to call nhi maarenge
    subsetsDupli(nums, 0, smallAns, res);
    return res;
}
