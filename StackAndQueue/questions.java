import java.util.Stack;
import java.util.ArrayDeque;

public class questions{

//N : next , G : greater , S : smaller , R : right , L : left
public static void NGOR(int[] arr,int[] ans)
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
public static void NGOL(int[] arr,int[] ans)
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
public static void NSOR(int[] arr,int[] ans)
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

public static void NSOL(int[] arr,int[] ans)
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

//Stock Span Problem -> GFG
public static int[] NGOL(int[] arr,int n)
{
    int[] NGOL = new int[n];
    Arrays.fill(NGOL,-1);
    Stack<Integer> st = new Stack<>();
    for(int i = n - 1; i >= 0; i--)
    {
        while(st.size() != 0 && arr[st.peek()] < arr[i])
        {
            NGOL[st.pop()] = i;
        }
        st.push(i);
    }
    return NGOL;
}

//GFG function
public static int[] calculateSpan(int price[], int n)
{
    int[] ans = NGOL(price,n);
    for(int i = 0; i < n; i++)
    {
        ans[i] = i - ans[i];
    }
    return ans;
}

//Leetcode 901 -> Online Stock Span
class StockSpanner {
Stack<int[]> st;
int day;
    
    public StockSpanner() 
    {
        this.st = new Stack<>();
        this.day = 0;
        
        st.push(new int[]{-1,-1}); // day vs data -> -1,-1 default value for easier calculations
    }
    
    public int next(int price) 
    {
        while(st.size() != 1 && st.peek()[1] <= price) // agr top pe pdhe ele se bda ya equal price aayega to pop krte rahenge
            st.pop();
        
        int span = day - st.peek()[0];
        st.push(new int[]{day++,price});
        return span;
    }
}

//Leetcode 1249 -> Minimum Remove to make Valid Parenthesis
public String minRemoveToMakeValid(String s) 
{
    ArrayDeque<Integer> st = new ArrayDeque<>(); // same as LL bs ye arr pe implemented hota hai
    int n = s.length();
        
    for(int i = 0; i < n; i++)
    {
        char ch = s.charAt(i);
        if(ch == '(')
            st.addFirst(i);
        else if(ch == ')')
        {
            if(st.size() != 0 && s.charAt(st.getFirst()) == '(')
                st.removeFirst();
            else
                st.addFirst(i);
        }
    }
        
    StringBuilder ans = new StringBuilder();
    for(int i = 0; i < n; i++)
    {
        if(st.size() != 0 && st.getLast() == i)
        {
            st.removeLast();
            continue;
        }
        ans.append(s.charAt(i));
    }
    return ans.toString();
}

//Leetcode 32 -> Longest Valid Parenthesis
public int longestValidParentheses(String s) 
{
    int n = s.length();
    Stack<Integer> st = new Stack<>();
    st.push(-1);
        
    int maxLen = 0;
    for(int i = 0; i < n; i++)
    {
        if(st.peek() != -1 && s.charAt(st.peek()) == '(' && s.charAt(i) == ')')
        {
            st.pop();
            maxLen = Math.max(maxLen,i - st.peek());
        }
        else
            st.push(i);
    }
    return maxLen;
}

//Leetcode 316 -> Remove Duplicate Letters (Leetcode 1081 is also same) 
public String removeDuplicateLetters(String s) 
{
    int n = s.length();
    StringBuilder st = new StringBuilder(); // using StringBuilder as stack
    int[] freq = new int[26];
    boolean[] vis = new boolean[26];
        
    for(int i = 0; i < n; i++)
    {
        char ch = s.charAt(i);
        freq[ch-'a']++;
    }
        
    for(int i = 0; i < n; i++)
    {
        char ch = s.charAt(i);
        
        freq[ch-'a']--;
            
        if(vis[ch-'a'])
            continue;
            
        while(st.length() != 0 && st.charAt(st.length()-1) > ch && freq[st.charAt(st.length()-1)-'a'] > 0)
        {
            char rch = st.charAt(st.length()-1);
            st.deleteCharAt(st.length()-1);
            vis[rch-'a'] = false;
        }
            
        vis[ch-'a'] = true;
        st.append(ch);
    }
    return st.toString();
}

//Leetcode 225 -> Implement Stack Using Queues -> Push Efficent -> push = O(1) ; pop = O(N)
class MyStack {
    private Queue<Integer> q1;
    private Queue<Integer> q2;
    private int tos;
    private int noOfEle;
    /** Initialize your data structure here. */
    public MyStack() 
    {
        this.q1 = new LinkedList<>();
        this.q2 = new LinkedList<>();
        this.tos = -1;
        this.noOfEle = 0;
    }
    
    /** Push element x onto stack. */
    public void push(int x) 
    {
        q1.add(x);
        tos = x;
        noOfEle++;
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() 
    {
        while(q1.size() > 1)
        {
            tos = q1.remove();
            q2.add(tos);
        }
        int rv = q1.remove();
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
        
        noOfEle--;
        return rv;
    }
    
    /** Get the top element. */
    public int top() 
    {
        return this.tos;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() 
    {
        return this.noOfEle == 0;
    }
}
}