public class basic {//rev
    public static void isPrime(int n)
    {
        int div = 2;
        while(div * div <= n)
        {
            if (n % div == 0)
            {
                break;
            }
            div++;
        }
        if (div * div > n)
        {
            System.out.print("Prime");
        }
        else
        System.out.print("Not Prime");

    }
    public static void Nth_fibo_series(int n)
    {
    int a = 0;
    int b = 1;
    for(int i = 0; i < n; i++)
    {
        System.out.println(a);
        int c = a + b;
        a = b;
        b = c;
      }

    }

public static int digits(int n)
{
    int len = 0;
    while(n != 0)
    {
        len++;
        n /= 10;
    }
    return len;
}

public static void print_digits(int n)
{
    int nod = digits(n);
    int div = Math.pow(10,nod - 1);
    while (div != 0) // (n!=0) mei 0 se end hone vaale no. faste hai.
    {
        int q = n / div;
        System.out.println(q);
        n %= div;
        div /= 10;
    }
}

public static int rotate (int n , int r)
{
    int digit = digits(n);
    r %= digit;
    r = r < 0 ? r+digit : r;

    int mul = 1;
    int divd = 1;
    for(int i = 1; i <= digit; i++)
    {
        if (i <=  r)
        mul*=10;
        else
        div*=10;
    }
    int fdigits = n % div;
    int sdigits = n / div;

    return fdigits * mul + sdigits;

}

public static void inverseOfNo(int n) //pep-foundation-online.
{
    int op = 1;
    int inv = 0;
    while (n != 0)
    {
      int od = n % 10;
      int id = op;
      int ip = od;
      inv += id * (int)Math.pow(10,ip-1);
      
      n /= 10;
      op++;
      
    }
  System.out.println(inv);
 }


public static void main(String[]args)
    {
        // isPrime(5);
        Nth_fibo_series(5);

    }
}
