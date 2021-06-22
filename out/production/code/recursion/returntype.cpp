#include <iostream>
#include <vector>
#include <string>

using namespace std;

//BASIC==================================================================================

// string removeHi(string str)
//{
//     if (str.length()==0) return "";
// char ch=str[0];
//     if (str.length()>1 && str.substr(0,2)=="hi") return removeHi(str.substr(2));
//     else return ch + removeHi(str.substr(1));
// }
// string keepHit(string str){
//     if (str.length()==0) return "";
// char ch=str[0];
//     if (str.length()>1 && str.substr(0,2)=="hi")
//     if (str.lenght()>2 && str[2]=='t')
//      return "hit" keepHit(str.substr(2));
//     else return ch + keepHit(str.substr(1));
// }

// void removeDupli(string str,string ans)
// {
//     if (str.length()==0){
//         cout<<ans<<endl;
//         return;
//     }
//     if(ans[ans.length()-1]!=string [0]){
//         ans+=str[0];
//         removeDupli(str.substr(1),ans);
//     }
// }
vector<string> subseq (string str)
{
    if (str.length() == 0)
    {
        vector<string> base;
        base.push_back(" ");
        return base;
    }

    char ch = str[0];
    vector<string> recAns = subseq(str.substr(1));
    vector<string> myAns;

    for (string rstr : recAns)
    {
        myAns.push_back(rstr);
        myAns.push_back(ch + rstr);
    }
    return myAns;
}

vector<string> codes= {".","abc","def","ghi","jkl","mno","pqrs","tuv","wx","yz","&:","#@","?!"};//10 can mean 1-0 or 10.
vector<string> keyPad(string str)//some problem with this code.
{
    if (str.length() == 0)
    {
    vector<string> base;
    base.push_back(" ");
    return base;
   }
    vector<string> myAns;
    char ch = str[0];
    string word1 = codes[ch - '0']; //char num to integer num conversion.eg->'1' to 1.
    vector<string> firstRes = keyPad(str.substr(1));
    for (string s : firstRes)
    {
        for (int i = 0; i < word1.length(); i++)
        {
            myAns.push_back(word1[i] + s);
        }
    }
    if (str.length() > 1 && ch!='0')
    {
        int num = (ch -'0') * 10 +(str[1] - '0');
        if (num < 12)
        {
        string word2 = codes[num];
        vector<string> secondRes = keyPad(str.substr(2));
        for (string s : secondRes)
        {
            for(int i = 0; i < word2.length(); i++)
            {
                myAns.push_back(word2[i] + s);
            }
        }
        }
    }
return myAns;
}

void basic()
{
    // string str = "abcd";
    // vector<string> ans = subseq(str);
    vector<string> ans = keyPad("10");
    for (string s : ans)
    {
        cout<<s<<endl;
    }
  // cout<<removeHi("hiiihihihihih");
//cout<<keepHit("hithihihi");
  // cout<<removeDupli("aaabbbcddeffghhii",0)<<endl;
}
//MAZEPATH=============================================================================
// vector<string> mazePathSimple(int sr,int sc,int er,int ec)
// {
//     if (sr==er&& sc==ec)
//     {
// vector<string> base;
//         base.push_back("");
//         return base;
//     }
//     vector<string> myAns;
//     if(sc < ec)
//     {
//         vector<string> horizontal=mazePathSimple(sr,sc+1,er,ec);
//         for (string s: horizontal)
//         myAns.push_back("h"+s);
//     }
//     if(sr < er)
//     {
//         vector<string> vertical=mazePathSimple(sr+1,sc,er,ec);
//         for (string s:vertical)
//         myAns.push_back("v"+s);
//     }
//     if(sr < er && sc<ec)
//     {
//         vector<string> diagonal=mazePathSimple(sr+1,sc+1,er,ec);
//         for(string s:diagonal){
//             myAns.push_back("d"+s);
//         }
//     }
//     return myAns;
// }

// vector<string> mazePathJump(int sr,int sc,int er,int ec)
// {
//     if (sr==er&& sc==ec)
//     {
// vector<string> base;
//         base.push_back(" ");
//         return base;
//     }
//     vector<string> myAns;
//         for(int jump=1;sc+jump<=ec;jump++)
//         {
// vector<string> horizontal=mazePathJump(sr,sc+jump,er,ec);
//         for (string s: horizontal)
//         {
//         myAns.push_back("h"+to_string(jump)+s);
//         }
//         }
        
//         for(int jump=1;sr+jump<=er;jump++)
//         {
//  vector<string> vertical=mazePathJump(sr+jump,sc,er,ec);
//         for (string s:vertical)
//         {
//         myAns.push_back("v"+to_string(jump)+s);
//         }

//         }
       
//         for(int jump=1;sr+jump<=er&& sc+jump<=ec;jump++) 
//         {
//             vector<string> diagonal=mazePathJump(sr+jump,sc+jump,er,ec);
//         for(string s:diagonal)
//         {
//             myAns.push_back("d"+to_string(jump)+s);
//         }
//         }
        
    
//     return myAns;
// }

// void mazePath()
// {
//     vector<string>ans=mazePathJump(0,0,3,3);
//     for(string s:ans)
//     cout<<s<<" ";
// }

//FLOODFILL=========================================================================================

// bool isSafe(int x,int y,int er,int ec,vector<vector<bool>>&board)
// {
//     if(x<0 || y<0 || x>er || y>ec || board[x][y]) return false;
//     return true;
// }
// vector<vector<int>>direction={{0,1},{-1,1,},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1}};
// vector<string>dir={"R","1","U","2","L","3","D","4"};
// int floodFill(int sr,int sc,int er,int ec,vector<vector<bool>> &board,string ans)
// {
//     if(sr==er&& sc==ec)
//     {
//     cout<<ans<<endl;
//     return 1;
//     }
//     int count=0;
//             board[sr][sc]=true;
//     for(int d=0;d<8;d++){
//         int x=sr+direction[d][0];
//         int y=sc+direction[d][1];
    
//     if(isSafe(x,y,er,ec,board))
//     {
//         count+=floodFill(x,y,er,ec,board,ans+dir[d]);

//     }
//     }
//     board[sr][sc]=false;
//     return count;
// }


// void floodFill()
// {
//     vector<vector<bool>> board(3,vector<bool>(3,false));
//     cout<<floodFill(0,0,2,2,board,"");
// }



//=======================================================================================

void solve()
{
    basic();
    // mazePathSimple();
   // mazePath();
//    floodFill();

}
int main()
{
    solve();
    return 0;
}
