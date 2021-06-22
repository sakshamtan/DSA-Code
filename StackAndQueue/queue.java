public class queue{
    int [] que;
    int size = 0;
    int head = 0;
    int tail = -1;
    queue()
    {
        this.que = new int [10];
    }
    queue(int size)
    {
        this.que = new int[size];
    }
    queue(int [] arr)
    {
        this.que = new int [2*arr.length];
        for (int i = 0; i<arr.length;i++)
        {
            this.que[i] = arr[i];
        }
        this.head = arr.length-1;
    }
    public void push(int data)
    {
        if (this.size==que.length)
        {
            System.out.print("QueueIsFull");
            return;
        }
        this.tail =  (this.tail + 1) % this.que.length;
        que[this.tail] = data;
        this.size++;
    }
    public int size()
    {
        return this.size;
    }
    public boolean isEmpty()
    {
        return this.size == 0;
    }
    public void display()
    {
        for (int i = 0; i < this.size; i++)
        {
            int val = this.que[(this.head + 1) % que.length];
            System.out.print(val + " ");
        }
        System.out.println();
    }
    public int front()
    {
        if (this.size == 0)
        {
            System.out.print("QueueIsEmpty");
            return -1;
        }
        return this.que[this.head];
    }
    public int pop()
    {
        if (this.size == 0)
        {
            System.out.println("QueueIsEmpty");
            return -1;
        }
        int rv = this.que[this.head];
        this.que[this.head] = 0;
        this.size--;
        
        this.head = (this.head + 1) % this.que.length;
        return rv;
    }
}