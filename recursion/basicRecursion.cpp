#include <iostream> //rev
using namespace std;

//F-> function (n-1) ke saare dec. no.s print krdega hume bs nth no. print krna hai.
void printDecreasing(int n)
{
    if (n == 0)
        return;

    cout << n << endl;
    printDecreasing(n - 1);
}

//F-> function (n-1) ke liye incr. no.s print krdega hume bs nth no. print krna hai.
void printIncreasing(int n)
{
    if (n == 0)
        return;

    printIncreasing(n - 1);
    cout << n << endl;
}

//F-> function (n-1) ke liye dec.-incr. sequence print krdega hum nth no. seq ke pehle and last mei print krna hai.
void printDecreasingIncreasing(int n)
{
    if (n == 0)
    {
        cout << "BASE-> 0" << endl;
        return;
    }

    cout << "HI-> " << n << endl;
    printDecreasingIncreasing(n - 1);
    cout << "BYE-> " << n << endl;
}

//same as above just even and odd check.
void EvenOdd(int n)
{
    if (n == 0)
    {
        cout << "BASE-> 0" << endl;
        return;
    }
    if (n % 2 == 0)
    {
        cout << "HI-> " << n << endl;
    }
    EvenOdd(n - 1);
    if (n % 2 != 0)
    {
        cout << "BYE-> " << n << endl;
    }
}

//F-> function (n-1) ka factorial de dega.
int factorial(int n)
{
    if (n == 0)
    {
        return 1;
    }
    int fnm1 = factorial(n - 1);
    int facto = n * fnm1;
    return facto;
}

//F-> function x^(n-1) ki power de dega.
int power(int x, int n) // x^n
{
    if (n == 0)
        return 1;

    int pnm1 = power(x, n - 1); // pow of x^(n-1)
    int pow = x * pnm1;
    return pow;
}

//F-> function x^(n/2) ki power de dega.
int powerBetter(int x, int n) // logn
{
    if (n == 0)
        return 1;

    int smallAns = powerBetter(x, n / 2); //aadi calls bachayi to time and space bacha lia.
    smallAns *= smallAns;
    return (n % 2 != 0 ? smallAns * x : smallAns);
}

int fibo(int n)
{
    if (n <= 1)
        return n;

    return fibo(n - 1) + fibo(n - 2);
}

//E-> Move n discs from "A" to "B" using "C".
//F1-> Move (n-1) discs from "A" to "C" using "B".
//F2-> Move (n-1) discs from "C" to "B" using "A".
void tower_hanoi(string src, string dest, string help, int n)
{
    if (n == 0)
        return;

    tower_hanoi(src, help, dest, n - 1);
    cout << "Move " << n << "th disc from " << src << " to " << dest << endl;
    tower_hanoi(help, dest, src, n - 1);
}

int euler_01(int n, int level)
{
    if (n <= 1)
    {
        cout << " Base: " << n << " @ " << level << endl;
        return n;
    }
    cout << " Pre: " << n << " @ " << level << endl;

    int a = euler_01(n - 1, level + 1);
    cout << " In: " << n << " @ " << level << endl;
    int b = euler_01(n - 2, level + 1);

    cout << " Post: " << n << " @ " << level << endl;

    return a + b + 3;
}

int euler_02(int n, int level)
{
    if (n <= 2)
    {
        cout << " Base: " << n * level << endl;
        return n;
    }
    cout << " Pre: " << n * level << endl;

    int a = euler_02(n - 1, level + 1);
    cout << " In1: " << n * level << endl;

    int b = euler_02(n - 2, level + 1);
    cout << " In2: " << n * level << endl;

    int c = euler_02(n - 3, level - 1);
    cout << " Post: " << n * level << endl;

    return a + b + c + 3;
}

int main(int args, char **argv)
{

    // cout<<powerBetter(5,3);
    // EvenOdd(5);
    // cout<<power(5,3);
    // cout<<factorial(5);
    // printDecreasingIncreasing(5);
    // printIncreasing(5);
    // printDecreasing(5);
    // DecreasingO(3);
    // tower_hanoi("A","B","C",3);
    // cout<<fibo(5);
    cout << euler_02(4, 3);
    // cout<<euler_01(3,2);
    return 0;
}