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

