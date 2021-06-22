#include <iostream>
using namespace std;

struct ListNode
{
    int val;
    ListNode *next;
    ListNode(int x) : val(x),next(NULL){}
};
ListNode* middleNodeByIndex(ListNode* head)
{//first middle node is our mid in even size.
    ListNode *slow = head;
    ListNode *fast = head;
        
        while (fast != nullptr && fast->next!=nullptr && fast->next->next != nullptr)
        {
            slow = slow->next;
            fast = fast->next->next;
        }
        return slow;
        
}

//leetcode 876//======================================================================
ListNode* middleNode(ListNode* head)
{
    ListNode *slow = head;
    ListNode *fast = head;
        
        while (fast != nullptr && fast->next!=nullptr && fast->next->next != nullptr)
        {
            slow = slow->next;
            fast = fast->next->next;
        }
        return fast->next!=nullptr?slow->next:slow;
        
}

//leetcode 206//======================================================================
ListNode* reverseList(ListNode *head)
{
    ListNode *prev = nullptr;
    ListNode *curr = head;
    ListNode *forw = nullptr;

    while (curr!=nullptr)
    {
        forw = curr->next;//backup

        curr->next = prev;//linkjoin

        prev = curr;//move forward
        curr = forw;
    }
    return prev;//new head
}
//leetcode 141//===================================================================
bool hasCycle(ListNode *head) 
{
    if (head == nullptr || head->next == nullptr)
        return false;
    ListNode *slow = head;
    ListNode *fast = head;
    while (fast!=nullptr && fast->next != nullptr)
    {
        slow = slow->next;
        fast = fast->next->next;
        if (slow==fast) break;
    }
        return slow == fast;
        
}
//leetcode 142//================================================================================
ListNode *detectCycle(ListNode *head) 
{
    if (head == nullptr || head->next == nullptr)
            return nullptr;
        ListNode *slow = head;
        ListNode *fast = head;
        while (fast != nullptr && fast->next != nullptr)
        {
            slow = slow->next;
            fast = fast->next->next;
          if (slow==fast)
              break;
        }
        if (slow == fast)
        {
        slow = head;
        while (slow!=fast)
        {
            slow = slow->next;
            fast=fast->next;
        }
        return slow;
        }
        return nullptr;
        
    }
}