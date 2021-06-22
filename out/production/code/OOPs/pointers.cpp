#include <iostream>

using namespace std;

int main(int argc, char** argv)
{
    int i = 10;
    int j = 20;
    int *k = &i;
    int &l = i;
    cout<< *k<<endl;
    int **m = &k;
    k++;
    //k=&j;
    cout<< *k<<endl;
    cout<<l;

}