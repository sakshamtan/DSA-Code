public class dynamicStack extends stack{

    public dynamicStack()
    {
        super();  // constructor chaining -> stack ka constructor chlega yaha
    }

    public dynamicStack(int size)
    {
        super(size);
    }

    //Constructor when arr is given and all the ele's of arr is to be pushed in the stack
    public dynamicStack(int[] arr)
    {
        int n = arr.length;
        super.initialize(n * 2);

        for(int ele : arr)
        {
            super.push_(ele);
        }
    }

//Sirf push ko change krne ki need hai as stackOverflow hone pe stack grow krna chahiye so sirf push
//Ko override krenge and baaki ke functions / features same hi chl jaayenge
//arr and maxCapacity ko access nhi kr skte st ke as private members But size and capacity functions(getter functions) ke through kr skte hai
@Override
public void push(int data) throws Exception
{
    if(super.size() == super.capacity())     
    {
        int n = super.size();
        int[] temp = new int[n]; // ek temp arr mei data laayenge saare stack ka

        int i = n-1;
        while(super.size() != 0)
        {
            temp[i--] = super.pop_(); // seedhe order ke liye peeche se arr fill kr rhe hai
        }

        super.initialize(2 * n); // now st ke arr ka size twice kr dia and saara data dobaara copy krdia
        for(int ele : temp)
        {
            super.push_(ele);
        }                                  
    }
    super.push(data); // pichla data copy krne ke baad ab naya data bhi push krna hai
}
}