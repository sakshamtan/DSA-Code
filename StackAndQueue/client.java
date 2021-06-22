

public class client {
    public static void main (String [] args)
    {
        stack st = new stack(5);
        st.push(10);
        st.push(20);
        st.push(30);
        st.push(40);
        st.push(50);
        while (!st.isEmpty())
        {
            System.out.println(st.pop());
        }
      

        queue que = new queue(5);
        que.push(10);
        que.push(20);
        que.push(130);
        que.push(40);
        que.push(50);
        que.pop();
        que.pop();
        que.display();
        


    }
    
    
}