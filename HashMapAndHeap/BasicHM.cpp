#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

void test_01()
{
    unordered_map<string,int> map;
    map["USA"] = 1000;
    map["IND"] = 10000;   // similar to using vector
    map["NEP"] = 90;
    map["usa"] = 9000;

    cout << map["USA"] << endl;
    // cout << map["BAN"] << endl; //by default if key not present in UM then cpp adds it into UM with value 0 so 0 print ho jaayega and BAN UM mei add ho jaayega
    
    //to prevent this->
    if(map.find("BAN") != map.end())
        cout << map["BAN"] << endl; // jab map mei mile tbhi get krna 

    // Direct map print nhi kr skte cpp mei -> we use pair of key vs value to iterate over it
    for(pair<string,int> p : map)
        cout << p.first << " -> " << p.second << endl;
}

//Frequency map of a given string
void test_02(string str)
{
    unordered_map<char,int> map;
    for(char ch : str)
    {
        map[ch]++;  // map[ch] krne se khud hi map mei add ho jaata hai ch with 0 value
    }

    for(pair<char,int> p : map)
    {
        cout << p.first << " -> " << p.second << endl;
    }
}

//Indexes of all characters occuring in a string
void test_03(string str)
{
    unordered_map<char,vector<int>> map;
    for(int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        map[ch].push_back(i); // map[ch] krte hi already empty vector add hogya ch ke against
    }

    //printing the map
    for(auto &key : map)  // using auto for better syntax -> auto automatically req pair le aayega key mei
    {
        char k = key.first;
        vector<int> val = key.second;

        cout << k << " -> " ;
        for(int ele : val)
            cout << ele << ", ";
        cout << endl;

    }
}

int main()
{
    // test_01();
    // test_02("ababnabnajijdidd");
    test_03("ababnabnajijdidd");
}