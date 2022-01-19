class LRUCache2 { // using arr of Node instead of HashMap
    private class Node{
        int key = 0; // app name
        int value = 0; // app state

        Node next = null;
        Node prev = null;

        Node(int key,int value)
        {
            this.key = key;
            this.value = value;
        }

    }

    private int capacity = 0;
    private int size = 0;

            // key vs Node
    private Node[] map; // LookUp -> precise O(1).

    private Node head = null;
    private Node tail = null;

public LRUCache2(int capacity) 
{
    this.capacity = capacity;
    this.map = new Node[3001]; // Extra space is used as 3001 ka arr bnana hi hai but time is better.
}

public void addFirstNode(Node node)
{
    if(this.head == null)
    {
        this.head = node;
        this.tail = node;
    }
    else{
        this.head.next = node;
        node.prev = this.head;
        this.head = node;
    }
    this.size++;
}

public void removeTailNode()
{
    if(this.size == 1)
    {
        this.head = null;
        this.tail = null;
    }
    else{
        Node next = this.tail.next;
        next.prev = null;
        
        this.tail = next;
    }
    this.size--;
}

public void removeNode(Node node)
{
    if(this.size == 1)
    {
        this.head = null;
        this.tail = null;
    }
    else if(node == this.tail)
    {
        removeTailNode();
        return;
    }
    else{
        Node next = node.next;
        Node prev = node.prev;

        node.next = null;
        node.prev = null;

        prev.next = next;
        next.prev = prev;
    }
    this.size--;
}

public void makeMostRecent(Node node)
{
    if(node == this.head)
    return;

    removeNode(node);
    addFirstNode(node);
} 
public int get(int key) 
{
    if(map[key] == null)
    return -1;

    Node node = map[key];
    makeMostRecent(node);
    return node.value;    
}
    
public void put(int key, int value) 
{
    if(map[key] != null)
    {
        Node node = map[key];
        node.value = value;
        makeMostRecent(node);
    }
    else{
        if(this.size == this.capacity)
        {
            map[this.tail.key] = null;
            removeTailNode();   
        }
        Node node = new Node(key,value);
        addFirstNode(node);
        map[key]= node;
    }       
}
}