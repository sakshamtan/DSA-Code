//package StackAndQueue;

import java.util.Stack;
public class stackques{
    public static void nextGreaterOnRight(int [] arr)
    {
        Stack<Integer> st = new Stack<>();
        for (int i = 0;i < arr.length; i++)
        {
            if (st.size()==0)
            {
                st.push(arr[i]);
                continue;
            }
            while (st.size()!=0 && st.peek() < arr[i])
            {
                System.out.println(st.peek() + "->" + arr[i]);
            }
            st.push(arr[i]);
        }
        while (st.size() != 0)
        {
            System.out.println(st.pop() +"->" + -1);
        }
       
    }
    public static void main(String [] args)
    {
        int [] arr = {5,6,2,8,9,21,7};
        nextGreaterOnRight(arr);
    }
}