#include <iostream>//rev
using namespace std;
void mirrorTriangle_01(int rows)
{
    int nst=1;
    int nsp=rows-1;
    for(int r=1;r<=rows;r++)
    {
        for(int csp=1;csp<=nsp;csp++)
        {
            cout<<" ";
        
        }
        for(int cst=1;cst<=nst;cst++)
        {
            cout<<"*";
        }
        nst++;
        nsp--;
        cout<<endl;
    }
}
void mirrorTriangle_02(int rows)
{
    int nst=1;
    int nsp=0;
    for(int r=1;r<=rows;r++){
        for(int csp=1;csp<=nsp;csp++){
            cout<<" ";
        
        }
        for(int cst=1;cst<=nst;cst++){
            cout<<"*";
        }
        nst++;
        nsp=0;
        cout<<endl;
    }
}
void halfDiamond(int rows)
{
    int nst=1;
    int nsp=rows-1;
    for(int r=1;r<=rows;r++)
    {
        for(int csp=1;csp<=nsp;csp++)
        {
            cout<<" ";
        
        }
        for(int cst=1;cst<=nst;cst++)
        {
            cout<<"*";
        }
        nst+=2;
        nsp--;
        cout<<endl;
    }
}
void Full_Diamond(int rows)
{
    int nst=1;
    int nsp=rows/2;
    for(int r = 1;r <= rows; r++)
    {
        for(int csp=1;csp<=nsp;csp++)
        {
            cout<<" ";
        
        }
        for(int cst=1;cst<=nst;cst++)
        {
            cout<<"*";

        }
        if (r<=rows/2)
        {
        nst+=2;
        nsp--;
        }
        else
        {
            nst-=2;
            nsp++;   
        }
    
    cout<<endl;
    }
}
void hollow_Diamond(int rows)
{
 int nst = (rows/2) + 1;
 int nsp = 1;
 for(int r = 1; r <= rows; r++)
    {
     for(int cst = 1; cst <= nst; cst++)
         {
            cout<<"*"<<" ";
         }
     for(int csp = 1; csp <= nsp; csp++)
         {
            cout<<" ";
         }
     for(int cst = 1; cst <= nst; cst++)
         {
            cout<<"*"<<" ";
         }
        if(r <= rows/2)
         {
            nst--;
            nsp += 2;
         }
        else{
            nst++;
            nsp -= 2;
        }
        cout<<endl;;
    }          
    
}
void Pattern_X(int rows)
{
 for(int i = 1; i <= rows; i++)
 {
     for(int j = 1; j <= rows; j++)
    {
        if(i == j || i + j == rows + 1)
        cout<<"*"<<" ";
        else 
        cout<<" ";
    }
    cout<<endl;             
 }
}
void special_Diamond(int rows)//Diamond with inner and outer spaces.
{
 int os = rows/2;
 int is = -1;
 for(int i = 1; i <= rows; i++)
    {
     for(int j = 1; j <= os; j++)
     {
        cout<<" ";
     }
    cout<<"*"<<" ";
     for(int j = 1; j <= is; j++)
     {
      cout<<" ";
     }
     if (i > 1 && i < rows)
    cout<<"*"<<" ";
     if(i <= rows/2)
     {
        os--;
        is += 2;
     }
      else{
         os++;
         is -= 2;
     }
    cout<<endl;
    }
}

int main(int args,char**argv)
{
hollow_Diamond(5);
Pattern_X(5);
}