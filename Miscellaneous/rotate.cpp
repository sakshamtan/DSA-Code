#include <iostream>//rev
using namespace std;
int facto(int n)
{
    int ans = 1;
    for(int i = 1 ; i <= n ; i++)
    {
        ans = ans*i;
    }
    return ans;
}
int ncr(int n , int r)
{
    return facto(n)/(facto(n-r)*facto(r));
}
int digits(int n)
{
    int len=0;
    while(n != 0)
    {
    len++;
    n /= 10;
    }
    return len;
}
int rotate(int n, int r)
{
    int digit = digits(n);
    r %= digit;
    r = r < 0 ? r + digit : r;

  int mul = 1;
  int divd = 1;
  for (int i = 1;i <= digit; i++)
  {
    if (i <= r)
    mul *= 10;
    else
    divd *= 10;
  }
    int fdigits=n%divd;
    int sdigits=n/divd;
    return fdigits * mul  + sdigits;
  }

  int main(int args,char** argv)
  {
    int n,r;
    cout<<"enter n and r:"<<endl;
    cin>>n>>r;
    cout<<rotate(n,r)<<endl; 
    // cout<<facto(5)<<endl;
    
    return 0;
  }