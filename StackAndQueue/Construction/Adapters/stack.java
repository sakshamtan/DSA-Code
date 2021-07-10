public class stack{ // stack implementation using LL (LL bhi khud ki likhi huyi)

    private class ListNode{
        int data = 0;
        ListNode next = null;
    }

    public ListNode(int data)
    {
        this.data = data;
    }

    private ListNode head = null;
    private ListNode tail = null;
    private int NoOfElements = 0;

//addFirst and removeFirst se stack implement ho jaata hai
private void addFirst(ListNode node)
{
    if(this.head == null)
    {
        this.head = node;
        this.tail = node;
    }
    else
    {
        node.next = this.head;
        this.head = node;
    }
}

private ListNode removeFirst()
{
    ListNode rn = this.head;
    if(this.head == this.tail)
    {
        this.head = null;
        this.tail = null;
    }
    else
    {
        this.head = rn.next;
    }
    rn.next = null;
    return rn;
}

public int size()
{
    return this.NoOfElements;
}

public boolean isEmpty()
{
    return this.NoOfElements == 0;
}

//LL is dynamic in size so stack kbhi overflow nhi hoga
public void stackEmptyException() throws Exception
{
    if(this.NoOfElements == 0)
    throw new Exception("StackIsEmpty");
}

public void push(int data)
{
    ListNode node = new ListNode(data);
    addFirst(node);
    this.NoOfElements++;
}

public int top_()
{
    return this.head.data;
}

public int top() throws Exception
{
    stackEmptyException();
    return top_();
}

public int pop_()
{
    ListNode rn = removeFirst();
    this.NoOfElements--;
    return rn.data;
}

public int pop() throws Exception
{
    stackEmptyException();
    return pop_();
}
}