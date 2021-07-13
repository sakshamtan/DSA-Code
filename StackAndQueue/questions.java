import java.util.Stack;
import java.util.ArrayDeque;

using namespace std;

//N : next , G : greater , S : smaller , R : right , L : left
void NGOR(int[] arr,int[] ans)
{
    int n = arr.length;
    Arrays.fill(ans,n);

    Stack<Integer> st = new Stack<>();
    for(int i = 0; i < n; i++)
    {
        while(st.size() != 0 && arr[st.peek()] < arr[i])
        {
            ans[st.pop()] = i;
        }
        st.push(i);
    }
}

//Next Greater on Left -> so bs for loop ki direction opposite krni hai
void NGOL(int[] arr,int[] ans)
{
    int n = arr.length;
    Arrays.fill(ans,-1);
    
    Stack<Integer> st = new Stack<>();
    for(int i = n-1; i >= 0; i--)
    {
        while(st.size() != 0 && arr[st.peek()] < arr[i])
        {
            ans[st.pop()] = i;
        }
        st.push(i);
    }
}

//Next smaller on right -> bs comparison ka sign change krna hai in NGOR mei
void NSOR(int[] arr,int[] ans)
{
    int n = arr.length;
    Arrays.fill(ans,n);

    Stack<Integer> st = new Stack<>();
    for(int i = 0; i < n; i++)
    {
        while(st.size() != 0 && arr[st.peek()] > arr[i])
        {
            ans[st.pop()] = i;
        }
        st.push(i);
    }
}

void NSOL(int[] arr,int[] ans)
{
    int n = arr.length;
    Arrays.fill(ans,-1);

    Stack<Integer> st = new Stack<>();
    for(int i = n-1; i >= 0; i--)
    {
        while(st.size() != 0 && arr[st.peek()] > arr[i])
        {
            ans[st.pop()] = i;
        }
        st.push(i);
    }
}