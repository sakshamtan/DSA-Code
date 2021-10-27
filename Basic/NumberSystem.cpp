#include <iostream>

using namespace std;

int dtob(int num)
{
    int res = 0;
    int pow = 1;
    while (num != 0)
    {
        int rem = num % 2;
        num /= 2;
        res += pow * rem;
        pow *= 10;
    }
    return res;
}

int btod(int num)
{
    int res = 0;
    int pow = 1;
    while (num != 0)
    {
        int rem = num % 10;
        num /= 10;
        res += pow * rem;
        pow *= 2;
    }
    return res;
}

int DtoO(int num)
{
    int res = 0;
    int pow = 1;
    while (num != 0)
    {
        int rem = num % 8;
        num /= 8;
        res = res + pow * rem;
        pow *= 10;
    }
    return res;
}

int AnytoD(int num, int base) // for converting AnytoAny first convert Any(base1)toD and then DtoAny(base2)
{
    int res = 0;
    int pow = 1;
    while (num != 0)
    {
        int rem = num % 10;
        num /= 10;
        res += pow * rem;
        pow *= base;
    }
    return res;
}

int DtoAny(int num, int base)
{
    int res = 0;
    int pow = 1;
    while (num != 0)
    {
        int rem = num % base;
        num /= base;
        res += pow * rem;
        pow *= 10;
    }
    return res;
}

int getSum(int b, int n1, int n2) //any base addition of two no.s
{
    int res = 0;
    int carry = 0;
    int pow = 1;
    while (n1 > 0 || n2 > 0 || c > 0)
    {
        int d1 = n1 % 10;
        int d2 = n2 % 10;
        n1 = n1 / 10;
        n2 = n2 / 10;

        int d = d1 + d2 + c;
        c = d / b;
        d = d % b;

        rv += d * p;
        p = p * 10;
    }

    return rv;
}

int main(int args, char **argv)
{
    int num, base1;
    cout << "Enter the number:" << endl;
    cin >> num;
    cout << "Enter base1:" << endl;
    cin >> base1;
    cout << AnytoD(num, base1);
    return 0;
}