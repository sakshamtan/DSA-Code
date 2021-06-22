#include <iostream>

using namespace std;

class person{
    public:
     int age;
     string name;
};

void swap1 (person one,person two) // does not swap.
{
    person temp = one;
    one = two;
    two = temp;
}
void swap2 (person &one,person &two) // does swapping through ref.
{
    person temp = one;
    one = two;
    two = temp;
} 
void swap3 (person *one , person *two) // does swapping through pointers.
{
    person temp = *one;
    *one = *two;
    *two = temp;
}
void swap4 (person one,person two)// does not swap unless '&' is used.
{
    string tname = one.name;
    one.name = two.name;
    two.name = tname;

    int tage = one.age;
    one.age = two.age;
    two.age = tage;
}
    
int main(int argc, char** argv)
{
    person p1;
    person p2;

    p1.age = 10;
    p1.name = "A";

    p2.age = 20;
    p2.name = "B";

    cout<<p1.name << " -> " << p1.age<<endl;
    cout<<p2.name << " -> " << p2.age<<endl;
    cout<<endl;
    //swap1(p1,p2);
   // swap2(p1,p2);
    //swap3(p1,p2);
    swap4(p1,p2);

    cout<<p1.name << " -> " << p1.age<<endl;
    cout<<p2.name << " -> " << p2.age<<endl;

    

}