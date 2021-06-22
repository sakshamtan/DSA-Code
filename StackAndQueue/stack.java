public class stack{
   protected int [] st;
   protected int tos = -1;
    stack()
    {
        this.st = new int[10];
    }
    stack(int size)
    {
        this.st = new int[size];
    }
    stack(int [] arr)
    {
        this.st = new int [2*arr.length];
        for (int i = 0; i<arr.length;i++)
        {
            this.st[i] = arr[i];
        }
        this.tos = arr.length-1;
    }
    public void push(int data)
    {
        if (tos == st.length)
        {
            System.out.println("StackOverflow:"+tos);
            return;
        }
        st[++tos] = data;
    }
    public int top()
    {
        if (tos == -1)
        {
            System.out.println("StackIsEmpty");
            return -1;
        }
        return st[tos];
    }
    public int pop()
    {
        if (tos == -1)
        {
            System.out.println("StackIsEmpty");
        }
        int rv = st[tos];
       st[tos] = 0;
       tos--;
        return rv;
    }
    public int size()
    {
        return tos+1;
    }
    boolean isEmpty()
    {
        if (tos == -1)
        {
            return true;
        }
        return false;
    }
    
}
