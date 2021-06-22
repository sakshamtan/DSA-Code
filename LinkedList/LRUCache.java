import java.util.HashMap;
class LRUCache {
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
    private HashMap<Integer,Node> map = new HashMap<>();

    private Node head = null;
    private Node tail = null;

public LRUCache(int capacity) 
{
    this.capacity = capacity;
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
    if(!map.containsKey(key))
    return -1;

    Node node = map.get(key);
    makeMostRecent(node);
    return node.value;    
}
    
public void put(int key, int value) 
{
    if(map.containsKey(key))
    {
        Node node = map.get(key);
        node.value = value;
        get(key);
    }
    else{
        if(this.size == this.capacity)
        {
            map.remove(this.tail.key);
            removeTailNode();   
        }
        Node node = new Node(key,value);
        addFirstNode(node);
        map.put(key,node);
    }       
}
}