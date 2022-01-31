public class queue{
    private int[] arr;
    private int NoOfElements;
    private int maxCapacity;
    private int front;
    private int back;

    protected void initialize(int size)
    {
        this.arr = new int[size];
        this.NoOfElements = 0;
        this.maxCapacity = size;
        this.front = 0;
        this.back = 0;
    }

    public queue()
    {
        initialize(10);
    }

    public queue(int size)
    {
        initialize(size);
    }

protected int capacity()
{
    return this.maxCapacity;
}

public int size()
{
    return this.NoOfElements;
}

public boolean isEmpty()
{
    return this.NoOfElements == 0;
}

public void display()
{
    for(int i = 0; i < this.NoOfElements; i++)
    {
        int idx = (this.front + i) % this.maxCapacity;
        System.out.print(this.arr[idx] + " ");
    }
}

protected void queueEmptyException() throws Exception
{
    if(this.NoOfElements == 0)
    throw new Exception("QueueIsEmpty");
}

protected void queueOverFlowException() throws Exception
{
    if(this.NoOfElements == this.maxCapacity)
    throw new Exception("QueueOverFlow");
}

protected void push_(int data)
{
    this.arr[this.back] = data;
    this.back = (++this.back % this.maxCapacity); //updation of back pointer
    this.NoOfElements++;
}

public void push(int data) throws Exception
{
    queueOverFlowException();
    push_(data);
}

protected int front_()
{
    return this.arr[this.front];
}

public int front() throws Exception
{
    queueEmptyException();
    return front_();
}

protected int pop_()
{
    int rv = this.arr[this.front];
    this.arr[this.front] = 0;
    this.front = (++this.front % this.maxCapacity); //updation of front pointer
    this.NoOfElements--;
    
    return rv;
}

public int pop() throws Exception
{
    queueEmptyException();
    return pop_();
}
}