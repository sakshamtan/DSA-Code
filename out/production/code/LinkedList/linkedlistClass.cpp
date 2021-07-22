#include <iostream>
using namespace std;

class linkedlist{

    class Node{
        public:         
        int data = 0;
        Node *next = nullptr;

        Node(int data)
        {
            this->data = data;
        }
    };
    Node *head = nullptr;//as by default class in cpp is private
    Node *tail = nullptr;
    int elementCount = 0;


// Basic Functions =============================================================================

public:
int size()
{
    return this->elementCount;
}

public:
bool isEmpty()
{
    return this->elementCount == 0;
}

public:
string toString() //Override nhi hota cpp mei as poori library file likhni pdh jaati hai so making tostring as a function
{
    Node *curr = this->head;
    string sb = "";
    sb += " [ ";
    while(curr != nullptr)
    {
        sb += to_string(curr->data);
       
        if(curr->next != nullptr) 
        sb += ", ";

        curr = curr->next;
    }
    sb += " ] ";
    return sb;
}

// Add(setters) Functions ===============================================

private:
void addFirstNode(Node *node) 
{
    if(this->elementCount == 0)
    {
        this->head = node;
        this->tail = node;
    }
    else{
        node->next = this->head;
        this->head = node;
    }

    this->elementCount++;
}

public:
void push_front(int val) //cpp mei addFirst ko push_front bolte hai.
{
    Node *node = new Node(val);
    addFirstNode(node);
}

private:
void addLastNode(Node *node)
{
    if(this->elementCount == 0)
    {
        this->head = node;
        this->tail = node;
    }
    else{
        this->tail->next = node;
        this->tail = node;
    }

    this->elementCount++;
}

public:
void push_back(int val)
{
    Node *node = new Node(val);
    addLastNode(node);
}

private:
void addNodeAt(Node *node,int idx)
{
    if(idx == 0) addFirstNode(node);
    if(idx == this->elementCount) addLastNode(node);

    else{
        Node *prev = getNodeAt(idx-1);
        Node *curr = prev->next;

        prev->next = node;
        node->next = curr;
    }

    this->elementCount++;

}

public:
void push_at(int val,int idx)
{
    if(idx < 0 || idx > this->elementCount)
    {
        throw("InvalidLocationException" + to_string(idx));
    }

    Node *node = new Node(val);
    addNodeAt(node,idx);
}

// Remove Functions ==============================================================

private:
Node *removeFirstNode()
{
    Node *node = this->head;

    if(this->elementCount == 1)
    {
        this->head = nullptr;
        this->tail = nullptr;
    }
    else{

    this->head = this->head->next;
    node->next = nullptr;
    }

    this->elementCount--;
    return node;  
}

public:
int pop_front()
{
    if(this->elementCount == 0)
    {
        throw("NullPointerException : - 1");
    }
    
    Node *node = removeFirstNode();
    int rv = node->data;
    delete node; // as garbage collector nhi krta ye cpp mei.
    return rv;
}

private:
Node *removeLastNode()
{
    Node *node = this->tail;

    if(this->elementCount == 1)
    {
        this->head = nullptr;//or "NULL" can also be used.
        this->tail = nullptr;
    }

    Node *prev = getNodeAt(this->elementCount - 2);
    this->tail = prev;
    prev->next = nullptr;

    this->elementCount--;
    return node;

}

public:
int pop_back()
{
    if(this->elementCount == 0)
    {
        throw("NullPointerException : - 1");
    }

    Node *node = removeLastNode();
    return node->data;
    delete node;
}

private:
Node *removeNodeAt(int idx)
{
    if(idx == 0) return removeFirstNode();
    if(idx == this->elementCount-1) return removeLastNode();

    Node *prev = getNodeAt(idx-1);
    Node *curr = prev->next;

    prev->next = curr->next;
    curr->next = nullptr;

    this->elementCount--;
    return curr;
 
}

public:
int pop_at(int idx)
{
    if(idx < 0 || idx >= this->elementCount)
    {
        throw("InvalidLocationException" + to_string(idx));
    }

    Node *node = removeNodeAt(idx);
    return node->data;
    delete node;
}

// Get Functions==============================================================================

private:
Node *getFirstNode()
{
    return this->head;
}

public:
int get_front()
{
    if(this->elementCount == 0)
    {
        throw("NullPointerException : - 1");
    }

    Node *node = getFirstNode();
    return node->data;
}

private:
Node *getLastNode()
{
    return this->tail;
}

public:
int get_back()
{
    if(this->elementCount == 0)
    {
        throw("NullPointerException : - 1");
    }

    Node *node = getLastNode();
    return node->data;
}

private:
Node *getNodeAt(int idx)
{
    if(idx == 0) return getFirstNode();
    if(idx == this->elementCount-1) return getLastNode();

    Node *curr = this->head;
    while(idx > 0)
    {
        curr = curr->next;
        idx--;
    }
    return curr;
}

public:
int get_at(int idx)
{
    if(idx < 0 || idx >= this->elementCount)
    {
        throw("InvalidLocationException" + to_string(idx));
    }

    Node *node = getNodeAt(idx);
    return node->data;
}
};

int main()
{
    linkedlist ll;
    for(int i = 1; i <= 10; i++)
    {
        ll.push_back(i*10);
    }

    for(int i = 10;i >= 1;i--)
    {
        ll.push_front(i*10);
    }
    // cout<<ll.get_at(3);

    
    

    cout<< ll.toString()<<endl;

    return 0;
}
