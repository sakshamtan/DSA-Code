public class basicRecursion
{

//F-> function (n-1) ke saare dec. no.s print krdega hume bs nth no. print krna hai.
public static void printDecreasing(int n)
{
    if(n == 0)
    return;

    System.out.println(n);
    printDecreasing(n-1);
}

//F-> function (n-1) ke liye incr. no.s print krdega hume bs nth no. print krna hai.
public static void printIncreasing(int n)
{
    if(n == 0)
    return;

    printIncreasing(n-1);
    System.out.println(n);
}

//F-> function (n-1) ke liye dec.-incr. sequence print krdega hum nth no. seq ke pehle and last mei print krna hai.
public static void printDecreasingIncreasing(int n)
{
    if(n == 0)
    {
        System.out.println("BASE -> 0");
        return;
    }

    System.out.println("HI -> " + n);
    printDecreasingIncreasing(n-1);
    System.out.println("BYE -> " + n);
}

//same as above just even and odd check.
public static void EvenOdd(int n)
{
    if(n == 0)
    {
        System.out.println("BASE -> 0");
        return;
    }

    if(n % 2 == 0)
    {
        System.out.println("HI -> " + n);
    }
    EvenOdd(n-1);
    if(n % 2 != 0)
    {
        System.out.println("BYE -> " + n);
    }
}

//F-> function (n-1) ka factorial de dega.
public static int Factorial(int n)
{
    if(n == 0)
    return 1;

    int fnm1 = Factorial(n-1);
    return n*fnm1;
}

//F-> function x^(n-1) ki power de dega.
public static int Power(int x,int n)//To calculate x^n
{
    if(n == 0)
    return 1;

    int pnm1 = Power(x,n-1);
    return x * pnm1;
}

//F-> function x^(n/2) ki power de dega.
public static int Power_Better(int x,int n) //O(logn)
{
    if(n == 0)
    return 1;

    int smallAns = Power_Better(x,n/2);//aadi calls bachayi to time and space bacha lia.
    int Ans = smallAns * smallAns;

    return (n % 2 != 0 ? Ans * x : Ans);
}

//Leetcode 50 -> Pow(x,n)
public double pow(double x,int n)
{
    if(n == 0)
        return 1.0;
        
    double smallAns = pow(x,n/2);
    smallAns *= smallAns;
    return (n % 2) == 0 ? smallAns : smallAns * x;
}

//Leetcode function
public double myPow(double x, int n) 
{
    if(n < 0)
        return 1 / pow(x,n);
        
    return pow(x,n);
}

public static int fibo(int n)
{
    if(n <= 1)
    return n;

    return fibo(n-1) + fibo(n-2);
}

//E-> Move n discs from "A" to "B" using "C".
//F1-> Move (n-1) discs from "A" to "C" using "B".
//F2-> Move (n-1) discs from "C" to "B" using "A".
public static void tower_Of_Hanoi(String src,String dest,String help,int n)
{
    if(n == 0)
    return;

    tower_Of_Hanoi("A","C","B",n-1);
    System.out.println("Move -> " + n + "th disc from " + src + " to " + dest);
    tower_Of_Hanoi("C","B","A",n-1);
}

public static int euler_01(int n,int level)
{
    if (n <= 1)
    {
        System.out.println(" Base: " + n + " @ " + level);
        return n;
    }
    System.out.println(" Pre: "+ n + " @ " + level);

    int a = euler_01(n-1,level+1);
    System.out.println(" In: " + n + " @ " + level);
    int b = euler_01(n-2,level+1);

    System.out.println(" Post: "+ n + " @ " + level);

    return a + b + 3;
}

public static int euler_02(int n, int level)
{
    if (n <= 2)
    {
        System.out.println(" Base: " + n*level);
        return n;
    }
    System.out.println(" Pre: "+ n*level);

    int a = euler_02(n-1,level+1);
    System.out.println(" In1: " + n*level);

    int b = euler_02(n-2,level+1);
    System.out.println(" In2: " + n*level);

    int c = euler_02(n-3,level-1);
    System.out.println(" Post: "+ n*level);

    return a+b+c+3;

}



public static void main(String [] args)
{
    // printDecreasing(5);
    // printIncreasing(5);
    // printDecreasingIncreasing(5);
    // EvenOdd(5);
    // System.out.println(Factorial(5));
    // System.out.println(Power(5,2));
    // System.out.println(Power_Better(5,2));
    // tower_Of_Hanoi("A","B","C",3);
    System.out.println(euler_01(5,0));



}
}