#include <iostream>
#include <queue>
#include <vector>

using namespace std;

void test1_maxPQ(vector<int> &arr)
{
    priority_queue<int> pq; // by default -> MaxPQ
    for (int ele : arr)
        pq.push(ele);

    while (pq.size() != 0)
    {
        cout << pq.top();
        pq.pop();
    }
}

void test2_minPQ(vector<int> &arr)
{
    priority_queue<int, vector<int>, greater<int> > pq; // using greater function/comp converts maxPQ into minPQ

    for (int ele : arr)
        pq.push(ele);

    while (pq.size() != 0)
    {
        cout << pq.top();
        pq.pop();
    }
}

class comparator
{
public:
    bool operator()(const vector<int> &a, const vector<int> &b) const
    {
        return a[0] > b[0]; // this > other -> minPQ
        // return b[0] > a[0]; // this < other -> maxPQ
    }
};

void test3(vector<vector<int> > &arr)
{
    priority_queue<vector<int>, vector<vector<int> >, comparator> pq; // minPQ

    for (vector<int> &a : arr)
        pq.push(a);

    while (pq.size() != 0)
    {
        vector<int> a = pq.top();
        int i = a[0];
        int j = a[1];
        cout << "( " << i << ", " << j << " )" << endl;
        pq.pop();
    }
}

int main()
{
    vector<vector<int> > arr{{2, 5}, {1, -1}, {0, -4}, {-6, 3}, {6, 9}, {9, 40}};
    test3(arr);

    return 0;
}
