#include <iostream>
using namespace std;

struct ListNode
{
    int val;
    ListNode *next;

    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

//Leetcode 876
ListNode *middleNode(ListNode *head) // hamesha ceil value deta hai middle ki jab even length ho linked list ki.
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *slow = head;
    ListNode *fast = head;

    while (fast != nullptr && fast->next != nullptr)
    {
        slow = slow->next;
        fast = fast->next->next;
    }

    return slow;
}

//Even length ll mei do middle hote hai pehle vaala floor and dusre vaala ceil. -> middleNode vaala question ho to ceil middle dena hota hai aur middleNode as a function use krna hota hai to floor middle dena hota hai even ll ke case mei
// middle of linked list -> when to be used as a funtion -> it returns floor middle in the case of even length ll.
ListNode *middleNode_02(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *slow = head;
    ListNode *fast = head;

    while (fast->next != nullptr && fast->next->next != nullptr)
    {
        slow = slow->next;
        fast = fast->next->next;
    }
    return slow;
}

//Leetcode 206
ListNode *reverseList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *prev = nullptr;
    ListNode *curr = head;
    ListNode *forw = nullptr;

    while (curr != nullptr)
    {
        forw = curr->next; //backup

        curr->next = prev; //linkjoin

        prev = curr; //move forward
        curr = forw;
    }
    return prev; //new head
}

//reverseData -> pointers and data addresses nodes ki change kiye bina data reverse krna hai.
void reverseData(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *mid = middleNode_02(head); // as a function use krna ho to middleNode_02 use krna hai hmesha.
    ListNode *tHead = mid->next;
    mid->next = nullptr; // breaking list into two lists.

    tHead = reverseList(tHead); //reversing second half of linked list.
    ListNode *c1 = head;
    ListNode *c2 = tHead;

    while (c1 != nullptr || c2 != nullptr) //swapping values of both halves of list.
    {
        int temp = c1.val;
        c1.val = c2.val;
        c2.val = temp;

        c1 = c1->next;
        c2 = c2->next;
    }

    tHead = reverseList(tHead); //reversing the second half of the list again.
    mid->next = tHead;          // connecting both halves of list.
}

//Leetcode 234 -> requires middleNode_02 and reverseList functions
bool isPalindrome(ListNode *head)
{
    if (head == nullptr || head->next == nullptr) //base case of leetcode.
        return true;

    ListNode *mid = middleNode_02(head); //breaking list from middle.
    ListNode *tHead = mid->next;
    mid->next = nullptr;

    tHead = reverseList(tHead); //reversing second half of list
    ListNode *c1 = head;
    ListNode *c2 = tHead;
    bool res = true;
    while (c1 != nullptr && c2 != nullptr)
    {
        if (c1->val != c2->val) //condition to check palindrome.
        {
            res = false;
            break;
        }
        c1 = c1->next;
        c2 = c2->next;
    }

    tHead = reverseList(tHead); //list ko dobaara shi bhi krna hota hai as structure mei change nhi kr skte ll ke.
    mid->next = tHead;

    return res;
}

//Leetcode 143
void reorderList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return;

    ListNode *mid = middleNode_02(head);
    ListNode *tHead = mid->next;
    mid->next = nullptr;

    tHead = reverseList(tHead);

    ListNode *c1 = head;
    ListNode *c2 = tHead;
    ListNode *f1 = nullptr;
    ListNode *f2 = nullptr;

    while (c1 != nullptr && c2 != nullptr)
    {
        f1 = c1->next; //backup
        f2 = c2->next;

        c1->next = c2; //link
        c2->next = f1;

        c1 = f1; //move forward
        c2 = f2;
    }
}

// now Order the list i.e. 1->5->2->4 se 1->2->3->4 laana hai original list vaapis laani hai

ListNode *th1 = nullptr; //temporary head and tails of both linked lists.
ListNode *tt1 = nullptr; //declared as global.taaki upar ke functions ke change neeche
ListNode *th2 = nullptr; //vaale function mei bhi reflect ho th1 th2 tt1 tt2 mei.
ListNode *tt2 = nullptr;
void addFirst(ListNode *node) //addFirst and addLast required for orderList function.
{
    if (th1 == nullptr)
    {
        th1 = node;
        tt1 = node;
    }
    else
    {
        node->next = th1;
        th1 = node;
    }
}

void addLast(ListNode *node)
{
    if (th2 == nullptr)
    {
        th2 = node;
        tt2 = node;
    }
    else
    {
        tt2->next = node;
        tt2 = node;
    }
}

ListNode *orderList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    int count = 0;
    ListNode *curr = head;
    while (curr != nullptr)
    {
        ListNode *forw = curr->next;
        curr->next = nullptr; //breaking link in the original unordered list.

        if (count == 0)
            addLast(curr);
        else
            addFirst(curr);

        curr = forw;
        count = (count + 1) % 2;
    }
    tt2->next = th1; //connecting both lists.
    return th2;      // new head of ordered list
}

//Leetcode 21 -> Merge two sorted Lists
ListNode *mergeTwoLists(ListNode *l1, ListNode *l2)
{
    if (l1 == nullptr || l2 == nullptr)
        return l1 == nullptr ? l2 : l1;

    ListNode *dummy = new ListNode(-1); //using dummy node method(easier syntax).
    ListNode *prev = dummy;
    ListNode *c1 = l1;
    ListNode *c2 = l2;

    while (c1 != nullptr && c2 != nullptr)
    {
        if (c1->val > c2->val)
        {
            prev->next = c2;
            c2 = c2->next;
        }
        else
        {
            prev->next = c1;
            c1 = c1->next;
        }
        prev = prev->next;
    }

    if (c1 == nullptr) // jab ek list pehle khtm ho jaaye and dusri bachi ho.
        prev->next = c2;
    else
        prev->next = c1;

    return dummy->next;
}

//Leetcode 148 -> SortList -> uses mergeTwoLists + middleNode
//SortList divides LL into two sorted Lists and mergeTwoLists merges these two sorted lists.
ListNode *sortList(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *mid = middleNode_02(head);
    ListNode *nHead = mid->next;
    mid->next = nullptr;

    return mergeTwoLists(sortList(head), sortList(nHead));
}

//Leetcode 23 -> merge K Sorted Lists
// mergeKLists first half array mei se ek sorted list le aayega and second half se dusri sorted list le aayega hume bs ye dono lists merge krni hai.
//Complexity -> n*log(k) where k -> size of array and n -> avg size of linkedlist
ListNode *mergeKLists_(vector<ListNode *> &lists, int si, int ei)
{
    if (si == ei)
        return lists[si];
    int mid = (si + ei) / 2;

    ListNode *list1 = mergeKLists_(lists, si, mid);
    ListNode *list2 = mergeKLists_(lists, mid + 1, ei);

    return mergeTwoLists(list1, list2);
}

//Leetcode function
ListNode *mergeKLists(vector<ListNode *> &lists)
{
    if (lists.size() == 0)
        return nullptr;
    return mergeKLists_(lists, 0, lists.size() - 1);
}

//O(n*k) solution
ListNode *mergeKLists_02(vector<ListNode *> &lists)
{
    if (lists.size() == 0)
        return nullptr;
    ListNode *refList = nullptr;
    for (int i = 0; i < lists.size(); i++)
    {
        refList = mergeTwoLists(refList, lists[i]);
    }
    return refList;
}

//leetcode 141 -> using Floyd's cycle algorithm
bool hasCycle(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return false;

    ListNode *slow = head;
    ListNode *fast = head;
    while (fast != nullptr && fast->next != nullptr)
    {
        slow = slow->next;
        fast = fast->next->next;
        if (slow == fast)
            break;
    }
    return slow == fast;
}

//leetcode 142 -> return node where cycle begins.
ListNode *detectCycle(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return nullptr;

    ListNode *slow = head;
    ListNode *fast = head;

    while (fast != nullptr && fast->next != nullptr) //hasCycle while loop.
    {
        slow = slow->next;
        fast = fast->next->next;
        if (slow == fast)
            break;
    }

    if (slow == fast)
    {
        slow = head;         //slow ko starting mei le aaye and fast to intersecting point pe hai hi fir same
        while (slow != fast) //speed se chlaakr jaha meet krenge vo humaara required node hoga.As by equation (x == z) for r = 2.
        {
            slow = slow->next;
            fast = fast->next;
        }
        return slow;
    }

    return nullptr;
}

// Insight of detect cycle ->
// m = no. of rotations of cycle by fast pointer.
// x = length of tail.
// y + z = circumference of cycle.
// distofslow generally = x+y
ListNode *detectCycle(ListNode *head)
{

    if (head == nullptr || head->next == nullptr)
        return nullptr;
    ListNode *slow = head;
    ListNode *fast = head;

    int m = 0, x = 0, y = 0, z = 0;
    int disOfSlow = 0;
    while (fast != nullptr && fast->next != nullptr)
    {
        slow = slow->next;
        fast = fast->next->next;
        disOfSlow++;
        if (slow == fast)
            break;
    }

    if (slow != fast)
        return nullptr;

    slow = head;
    ListNode *node = fast;
    while (slow != fast)
    {
        if (fast == node)
            m++;
        x++;
        slow = slow->next;
        fast = fast->next;
    }

    y = disOfSlow - x;

    return slow;
}

//Leetcode 160 -> requires detectCycle
ListNode *getIntersectionNode(ListNode *headA, ListNode *headB)
{
    if (headA == nullptr || headB == nullptr)
        return nullptr;

    ListNode *curr = headA;
    ListNode *prev = nullptr;

    while (curr != nullptr) // loop to find tail
    {
        prev = curr;
        curr = curr->next;
    }

    prev->next = headB;                 //linking common tail and head of another ll to form a cycle.
    ListNode *ans = detectCycle(headA); //detectCycle required node de dega.
    prev->next = nullptr;               //dobaara structure shi krna hai ll ka so unlinking.

    return ans;
}

//Leetcode 19 -> remove Nth Node from end of the List
ListNode *removeNthfromEnd(ListNode *head, int n)
{
    if (n == 0 || head == nullptr)
        return head;

    ListNode *c1 = head;
    ListNode *c2 = head;

    while (n-- > 0) // n times c2 ko aage move krwa dia.
        c2 = c2->next;

    if (c2 == nullptr) //first node ko hataane ka case seedha head ke next ko return krdo.
        return head->next;

    while (c2->next != nullptr)
    {
        c1 = c1->next; //same speed se dono pointers ko move krwana hai.
        c2 = c2->next; // initial diff distance mei same rahega baad mei bhi.
    }

    c1->next = c1->next->next; //removing required node.
    return head;
}

//Leetcode 61 -> rotate list form right.
ListNode *rotateRight(ListNode *head, int k)
{
    if (head == nullptr || head->next == nullptr || k == 0)
        return head;

    int len = 0;
    ListNode *curr = head;
    while (curr != nullptr) //linked list ki length nikaalne ke liye loop
    {
        curr = curr->next;
        len++;
    }

    k %= len; //if k > length to k ko range mei laane ke liye.
    if (k == 0)
        return head;

    ListNode *c1 = head;
    ListNode *c2 = head;

    while (k-- > 0) //same as above removeNthfromEnd code.
        c2 = c2->next;

    while (c2->next != nullptr)
    {
        c1 = c1->next;
        c2 = c2->next;
    }

    c2->next = head;
    head = c1->next;
    c1->next = nullptr;

    return head;
}

//Leetcode 25 -> Reverse Nodes in K group
ListNode *th = nullptr; // temporary Head and Tail
ListNode *tt = nullptr;
void addFirstNode(ListNode *node)
{
    if (th == nullptr)
    {
        th = node;
        tt = node;
    }
    else
    {
        node->next = th;
        th = node;
    }
}

int lenOfLL(ListNode *head)
{
    int len = 0;
    ListNode *curr = head;
    while (curr != nullptr)
    {
        len++;
        curr = curr->next;
    }
    return len;
}

//Aise tough questions ko addFirst + removeFirst + addLast mei break krne se question easy + readable ho jaate hai
// And nullptr exception bach jaate hai kaafi.
//Using addFirst and lenOfLL funciton -> O(1) space and O(n) time.
ListNode *reverseKGroup(ListNode *head, int k)
{
    if (head == nullptr || head->next == nullptr || k == 1)
        return head;
    ListNode *oh = nullptr; // Original Head and Tail
    ListNode *ot = nullptr;

    ListNode *curr = head;
    int len = lenOfLL(head);
    while (len >= k) // Last mei jb Len k se kam ho jaaye list ki to reverse nhi krna
    {
        int tempK = k;
        while (tempK-- > 0) // K times th and tl vaali list mei addFirst kr rhe hai node ko aage se remove krke list mei se.
        {
            ListNode *forw = curr->next;
            curr->next = nullptr;
            addFirstNode(curr);
            curr = forw;
        }
        if (oh == nullptr) //th and tl vaali bni huyi list ko oh and ot mei add kr rhe hai har baar and pointers update kr rhe hai.
        {
            oh = th;
            ot = tt;
        }
        else
        {
            ot->next = th;
            ot = tt;
        }
        th = nullptr; // Taaki ab nayi K length ki list bnaakr laaye th and tl next iteration mei
        tt = nullptr;

        len -= k;
    }
    ot->next = curr; // last mei K ke multiple mei list nhi bache to bina reverse kre as it is add krdo.
    return oh;
}

//Leetcode 92 -> Reverse LinkedList II -> m se lekr n tk reverse krni hai list and return original head.
//Done similar to above question bs idhr m se n tk addFirst vaala concept lga dia to reverse the list.
ListNode *reverseBetween(ListNode *head, int m, int n)
{
    if (head == nullptr || head->next == nullptr || m == n)
        return head;

    ListNode *curr = head;
    ListNode *prev = nullptr;

    int idx = 1; // idx to check for m and n in the list
    while (curr != nullptr)
    {
        while (idx >= m && idx <= n)
        {
            ListNode *forw = curr->next;
            curr->next = nullptr;
            addFirstNode(curr);
            curr = forw;
            idx++;
        }

        if (idx > n) // confirmed ki list reverse ho gyi hai is instance pr m se n tk.
        {
            if (prev != nullptr) // m to n group middle mei tha list ke.
            {
                prev->next = th;
                tt->next = curr;
                return head;
            }
            else
            { // m to n group starting mei tha list ke.
                tt->next = curr;
                return th;
            }
        }
        idx++;
        prev = curr;
        curr = curr->next;
    }
    return head;
}

//Geeks for Geeks -> segregate odd even nodes in a linked list
ListNode *SegregateOddEven(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *even = new ListNode(-1); // dummy node for even ll.
    ListNode *ep = even;

    ListNode *odd = new ListNode(-1); // dummy node for odd ll.
    ListNode *op = odd;

    ListNode *curr = head;
    while (curr != nullptr)
    {
        if (curr->val % 2 == 0)
        {
            ep->next = curr;
            ep = ep->next;
        }
        else
        {
            op->next = curr;
            op = op->next;
        }
        curr = curr->next;
    }
    ep->next = odd->next; //even nodes pehle chahiye then odd nodes chahiye.
    op->next = nullptr;
    return even->next;
}

//Leetcode 708 -> (premium question) -> insert node of insertVal into ll such that it remains sorted circular ll.
ListNode *insert(ListNode *head, int insertVal)
{
    ListNode *node = new ListNode(insertVal);

    if (head == nullptr)
    {
        node->next = node; // ek node ki hi cycle.
        return node;
    }

    ListNode *prev = head;
    ListNode *curr = head->next;

    bool connect = false;
    while (curr != head) // ek complete circle ka ll ke loop.
    {
        if (insertVal >= prev->val && insertVal <= curr->val)
            connect = true;

        else if (prev->val > curr->val)
        {
            if (insertVal > prev->val || insertVal < curr->val)
                connect = true;
        }

        if (connect)
            break; //taaki prev and curr shi location pr rhe loop band hone ke baad.

        prev = curr;
        curr = curr->next;
    }
    prev->next = node; //inserting node between prev and curr pointers.
    node->next = curr;

    return head;
}

//Leetcode 138 -> deep copy linkedList -> copy data as well pointers(random pointers also)
void copyNode(Node *head) //sirf new copied nodes add krdega same linkedlist mei.
{
    Node *curr = head;
    while (curr != nullptr)
    {
        Node *next = curr->next;
        Node *node = new Node(curr->val);

        curr->next = node;
        node->next = next;
        curr = next;
    }
}

void copyRandomPointers(Node *head) //random pointers set krke aayega newly(copied) created nodes mei.
{
    Node *curr = head;
    while (curr != nullptr)
    {
        if (curr->random != nullptr)                 //nhi to nullpointer exception aa jaayega.
            curr->next->random = curr->random->next; //newly created nodes ke random pointers ko set krdegi ye statement.

        curr = curr->next->next;
    }
}

Node *ExtractMyLL(Node *head) //copied ll extract krdega and puraani ll shi krdega.
{
    Node *dummy = new Node(-1);
    Node *prev = dummy;
    Node *curr = head;

    while (curr != nullptr)
    {
        prev->next = curr->next; //links
        curr->next = curr->next->next;

        prev = prev->next; //move
        curr = curr->next;
    }
    return dummy->next;
}

//Leetcode function
Node *copyRandomList(Node *head)
{
    if (head == nullptr)
        return head;

    copyNode(head);
    copyRandomPointers(head);

    return ExtractMyLL(head);
}

//Leetcode 237 -> delete node when directly node is given and head is not given.
void deleteNode(ListNode *node)
{
    if (node == nullptr)
        return;

    ListNode *prev = nullptr;
    while (node->next != nullptr)
    {
        node->val = node->next->val; //aage ki values pichle nodes mei daalte gye and last ka node free kr dia.
        prev = node;
        node = node->next;
    }
    prev->next = nullptr; //last node ko free krdia.
}

//Leetcode 206 -> Remove LinkedList elements equal to 'val'.
//Done using extra space -> we created a new list and added all the elements whose val was not equal to 'val'.
ListNode *removeElements(ListNode *head, int val)
{
    if (head == nullptr)
        return head;
    ListNode *dummy = new ListNode(-1);
    ListNode *prev = dummy;
    ListNode *curr = head;
    while (curr != nullptr)
    {
        if (curr->val != val)
        {
            prev->next = new ListNode(curr->val);
            prev = prev->next;
        }
        curr = curr->next;
    }
    return dummy->next;
}

//Leetcode 83 -> Remove Duplicates from Sorted List
ListNode *deleteDuplicates(ListNode *head)
{
    if (head == nullptr || head->next == nullptr)
        return head;
    ListNode *curr = head;
    while (curr != nullptr)
    {
        while (curr->next != nullptr && curr->val == curr->next->val)
        {
            curr->next = curr->next->next;
        }
        curr = curr->next;
    }
    return head;
}
