public class dynamicQueue extends queue{

    public dynamicQueue()
    {
        super();
    }

    public dynamicQueue(int size)
    {
        super(size);
    }

    //Constructor to convert arr into dynamicQueue
    public int dynamicQueue(int[] arr)
    {
        int n = arr.length;
        super.initialize(n * 2);

        for(int ele : arr)
        {
            super.push_(ele);
        }
    }

//Same as dynamicStack idhr bhi sirf push ko override krne ki need hai baaki sb pichle features same use ho jaayenge
@Override
public void push(int data)
{
    if(super.size() == super.capacity)
    {
        int n = super.size();
        int[] temp = new int[n];
        int i = 0;
        while(super.size() != 0)
        {
            temp[i++] = super.pop_();
        }

        super.initialize(n * 2);

        for(int ele : temp)
        {
            super.push_(ele);
        }

    }
    super.push(data);
}
}