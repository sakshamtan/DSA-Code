public class stack{

    private int[] arr;
    private int tos;
    private int NoOfElements;
    private int maxCapacity;

    protected void initialize(int size) // so that constructor mei baar baar use ho jaaye and dynamicStack mei bhi reuse ho jaaye
    {
        this.arr = new int[size];
        this.tos = -1;
        this.NoOfElements = 0;
        this.maxCapacity = size;
    }

    stack()
    {
       initialize(10);
    }

    stack(int size)
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

protected void stackEmptyException() throws Exception
{
    if(this.NoOfElements == 0)
    throw new Exception("StackIsEmpty");
}

protected void stackOverFlowException() throws Exception
{
    if(this.NoOfElements == this.maxCapacity)
    throw new Exception("StackOverflow");
}

//Protected vaale functions are to make code more reusable and modular
protected void push_(int data)
{
    this.arr[++this.tos] = data;
    this.NoOfElements++;
}

//User public vaale functions use kr skta hai bs
public void push(int data) throws Exception
{
    stackOverFlowException();
    push_(data);
}

protected int top_()
{
    return this.arr[this.tos];
}

public int top() throws Exception
{
    stackEmptyException();
    return top_();
}

protected int pop_()
{
    int rv = this.arr[this.tos];
    this.arr[this.tos] = 0;
    this.tos--;
    this.NoOfElements--;
    return rv;
}

public int pop() throws Exception
{
   stackEmptyException();
   return pop_(); 
}
}