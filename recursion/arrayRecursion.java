public class arrayRecursion{

    //jaate huye count krne hai elements. Base case mei new arr -> arr[counts so far]
    //Vaapis aate huye new arr mei check krke arr[csf] pr vidx daalna jb data ke equal ho.
    public static int [] allIndicies (int [] arr, int data,int vidx,int csf)
    {
        if (vidx == arr.length)
        {
        int [] res = new int[csf];
        return res;
        }

        int [] res = null;
        if (arr[vidx] == data)
        {
            res = allIndicies(arr,data,vidx+1,csf+1);
            res[csf] = vidx;
        }
        else {
            res = allIndicies(arr,data,vidx+1,csf);
        }
        return res;
    }

    public static void BubbleSort_Rec (int [] arr, int si , int li)
    {
        if (li == 0)
        return;
        if (si == li)
        {
            BubbleSort_Rec(arr,0,li-1);
            return;
        }

    if (arr[si+1] < arr[si])
    {
    int temp = arr[si+1];
    arr[si+1] = arr[si];
    arr[si] = temp;
    }
    BubbleSort_Rec(arr,si+1,li);
    
    }


    public static void main (String [] args)
    {
        int [] arr = {5,9,23,9,2,9,-1,9};
        int [] ar = allIndicies(arr,9,0,0);
        for (int ele : ar)
        {
            System.out.print(ele + " ");
        }
        // BubbleSort_Rec(arr,0,arr.length-1);
        // for (int ele : arr)
        // {
        //     System.out.print(ele + " ");
        // }
    }
    
}