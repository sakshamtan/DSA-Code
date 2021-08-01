import java.util.PriorityQueue;

public class BasicPQ{

public static void test1_minPQ(int[] arr)
{
    PriorityQueue<Integer> pq = new PriorityQueue<>(); // by default -> MinPQ
    for(int ele : arr)
        pq.add(ele);

    while(pq.size() != 0)
        System.out.println(pq.remove());
}

public static void test2_maxPQ(int[] arr)
{
    PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) ->{
        return b - a;  // other - this -> reverse of default behaviour of DS means maxPQ
    });

    for(int ele : arr)
        pq.add(ele);

    while(pq.size() != 0)
        System.out.println(pq.remove());
}

public static void test3(int[][] arr)
{ 
    PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) ->{  // pq of arr bnaayi to compare krna sikhana pdega
        return b[0] - a[0]; // other - this -> maxPQ on the basis of 0th index of arr
    });

    for(int[] a : arr)
        pq.add(a);

    while(pq.size() != 0)
    {
        int[] a = pq.remove();
        int i = a[0];
        int j = a[1];
        System.out.println("( " + i + ", " + j + " )");
    }
}

public static class pair implements Comparable<pair>{
    int i = 0, j = 0; // ye this hai

    pair(int i, int j)
    {
        this.i = i;
        this.j = j;
    }

    public int compareTo(pair o) // o is other
    {
        return o.i - this.i; // other - this so maxPQ
    }
}

public static void test4(int[][] arr)
{
    // PriorityQueue<pair> pq = new PriorityQueue<>((a,b) ->{    // better syntax
    //     return b.i - a.i;
    // });

    // Collections.sort(arr,(a,b)->{
        
    // });

    PriorityQueue<pair> pq = new PriorityQueue<>(); // pair ko compare krna sikha rha hai already so no need of lambda function

    for(int[] a : arr)
        pq.add(new pair(a[0],a[1]));

    while(pq.size() != 0)
    {
        pair p = pq.remove();
        int i = p.i;
        int j = p.j;
        System.out.println("( " + i + ", " + j + " )");
    }
}

public static void main(String[] args)
{
    int[] arr = {2,5,1,-1,0,-4,-6,3,6,9,9,40};
    test1_minPQ(arr);

    // int[][] arr = {{2,5},{1,-1},{0,-4},{-6,3},{6,9},{9,40}};
    // test4(arr);
}
}