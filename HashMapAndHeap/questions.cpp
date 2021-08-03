#include <iostream>
#include <queue>
#include <unordered_map>

using namespace std;

//Leetcode 215 -> Kth Largest Element in an Array -> Nlog(K) // as k size ki pq hi maintain kri hai
int findKthLargest(vector<int> &nums, int k)
{
    priority_queue<int, vector<int>, greater<int> > pq; // kth largest so minPQ
    for (int ele : nums)
    {
        pq.push(ele);
        if (pq.size() > k)
            pq.pop();
    }
    return pq.top();
}

int findKthSmallest(vector<int> &nums, int k)
{
    priority_queue<int> pq; // kth smallest so maxPQ
    for (int ele : nums)
    {
        pq.push(ele);
        if (pq.size() > k)
            pq.pop();
    }
    return pq.top();
}

//Leetcode 703 -> Kth Largest Element in a Stream
class KthLargest
{
public:
    priority_queue<int, vector<int>, greater<int> > pq;
    int k = 0;

    KthLargest(int k, vector<int> &nums)
    {
        this->k = k;
        for (int ele : nums)
        {
            this->pq.push(ele);
            if (this->pq.size() > this->k)
                this->pq.pop();
        }
    }

    int add(int val)
    {
        this->pq.push(val);
        if (this->pq.size() > this->k)
            this->pq.pop();

        return this->pq.top();
    }
};

//Leetcode 350 -> Intersection of Two Arrays II (Now duplicate alloweed)
vector<int> intersect(vector<int> &nums1, vector<int> &nums2)
{
    unordered_map<int, int> map;
    vector<int> ans;
    for (int ele : nums1)
        map[ele]++;

    for (int ele : nums2)
    {
        if (map.find(ele) != map.end())
        {
            ans.push_back(ele);
            map[ele]--;
            if (map[ele] == 0)
                map.erase(ele);
        }
    }
    return ans;
}

//Leetcode 347 -> Top K Frequent Elements
vector<int> topKFrequent(vector<int> &nums, int k)
{
    unordered_map<int, int> map;
    for (int ele : nums)
        map[ele]++;

    // (freq,val)
    priority_queue<vector<int>, vector<vector<int> >, greater<vector<int> > > pq;
    //vec ke pehle index pe default comparator likha hua hai so freq ko 0th index mei pass krenge

    for (auto &key : map)
    {
        int val = key.first;
        int freq = key.second;
        pq.push({freq, val});
        if (pq.size() > k)
            pq.pop();
    }

    vector<int> ans;
    while (pq.size() != 0)
    {
        vector<int> p = pq.top();
        pq.pop();

        int freq = p[0];
        int val = p[1];

        ans.push_back(val);
    }
    return ans;
}

//Leetcode 380 -> Insert Delete Get Random O(1)
class RandomizedSet
{
public:
    unordered_map<int, int> map;
    vector<int> list;
    /** Initialize your data structure here. */
    RandomizedSet()
    {
    }

    //Inserts a value to the set. Returns true if the set did not already contain the specified element
    bool insert(int val)
    {
        if (map.find(val) != map.end())
            return false;

        list.push_back(val);
        map[val] = list.size() - 1;

        return true;
    }

    // Removes a value from the set. Returns true if the set contained the specified element
    bool remove(int val)
    {
        if (map.find(val) == map.end())
            return false;

        int idx = map[val];
        int lidx = list.size() - 1;

        list[idx] = list[lidx];
        map[list[lidx]] = idx;

        list.pop_back();
        map.erase(val);

        return true;
    }

    // Get a random element from the set
    int getRandom()
    {
        int idx = rand() % list.size();
        return list[idx];
    }
};

//Leetcode 895 -> Maximum Frequency Stack
class FreqStack
{
public:
    class pair
    {
    public:
        int val = 0;
        int freq = 0;
        int idx = 0;

        pair(int val, int freq, int idx)
        {
            this->val = val;
            this->freq = freq;
            this->idx = idx;
        }
    };
    class comp
    {
    public:
        bool operator()(const pair &a, const pair &b) const
        {
            if (a.freq == b.freq)
                return b.idx > a.idx;
            return b.freq > a.freq;
        }
    };
    unordered_map<int, int> map;
    priority_queue<pair, vector<pair>, comp> pq;
    int idx = 0;

    FreqStack()
    {
    }

    void push(int val)
    {
        map[val]++;
        pq.push(pair(val, map[val], idx++));
    }

    int pop()
    {
        pair p = pq.top();
        pq.pop();
        map[p.val]--;
        if (map[p.val] == 0)
            map.erase(p.val);

        return p.val;
    }
};