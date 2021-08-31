import java.util.Arrays;
public class Sorting {

public static void Bubble_Sort(int [] arr)
{
    int jc = 1;
    while (jc < arr.length)
    {
        for (int i = 0; i < arr.length-jc;i++)
        {
            if(arr[i] > arr[i+1])
                    swap(arr,i,i+1);
        }
        jc++;
    }
    display(arr);   
}

public static void Selection_Sort(int [] arr)
{
    int jc = 1;
    while (jc < arr.length)
    {
        for (int i = jc; i < arr.length; i++)
        {
            if (arr[jc-1] > arr[i])
            {
                swap(arr,i,jc-1);
            }
        }
        jc++;
    }
    display(arr);
}

public static void insertion_Sort(int[] arr)
{
    int jc = 1;
    while (jc < arr.length)
    {
        for (int i = jc; i > 0; i--)
        {
            if(arr[i] < arr[i-1])
                    swap(arr,i,i-1);
            else
                break;
        }
        jc++;
    }

    display(arr);
}

public static void swap(int[] arr,int i,int j)
{
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}

public static void display(int [] arr)
{
    for (int ele : arr)
    {
        System.out.print(ele + " ");
    }
}
    
public static void main(String[] args)
{
    int [] arr = {10,2,3,-1,5};
    // Bubble_Sort(arr);
    // Selection_Sort(arr);
    insertion_Sort(arr);
}
}
