public class Sorting {

public static void Bubble_Sort(int [] arr)
{
    int n = arr.length;
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < n-i-1; j++)
        {
            if(arr[j+1] < arr[j])
            {
                swap(arr,j+1,j);
            }
        }
    }
    display(arr);
}

public static void Selection_Sort(int [] arr)
{
    int n = arr.length;
    for(int i = 0; i < n-1; i++)
    {
        int min = i;
        for(int j = i+1; j < n; j++)
        {
            if(arr[j] < arr[min])
            {
                min = j;
            }
        }
        swap(arr,i,min);
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
    Selection_Sort(arr);
    // insertion_Sort(arr);
}
}
