public class basic{

public static int heightOfTree(int[] arr,int idx)
{
    if(idx >= arr.length)
    return -1;

    int lh = heightOfTree(arr,idx * 2 + 1);
    int rh = heightOfTree(arr,idx * 2 + 2);

    return Math.max(lh,rh) + 1;
}

public static void main(String[] args)
{
    int[] arr = {10, 20, 30, -3, -4, 18, 10, 40, 75, 86, 99, 100, -1000};
    
    System.out.println(heightOfTree(arr,0));
}
}