public class client{

//Basics of Exception Handling
public static int divideSolve(int a,int b) throws Exception
{
    if(b == 0)
    {
        throw new Exception("Divisor cant be 0");
    }
    return a / b;
}

//Exception Handling by Try Catch block
public static int divide(int a,int b) throws Exception
{
    int ans = -1;
    try{
        ans = divideSolve(a,b);
    }
    catch(Exception e){
        System.out.println("ans is INFI");
    }
    return ans;
}

public static void main(String[] args) throws Exception
{
    dynamciStack st = new dynamicStack();
    for(int i = 0; i < 12; i++)
    {
        st.push(i+10);
    }

    while(st.size() != 0)
    {
        System.out.print(st.pop() + " ");
    }

}
}