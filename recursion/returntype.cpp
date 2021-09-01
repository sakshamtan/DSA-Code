#include <iostream>
#include <vector>
#include <string>

using namespace std;

//F -> "abc" mei "bc" ke subseq function le aayega humko ek baar "a" add krna hai aur ek baar nhi krna.
vector<string> subseq(string str)
{
    if (str.length() == 0)
    {
        vector<string> base;
        base.push_back("");
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

//F -> "179" mei se "79" ke combinations functions le aayega aur un combinations ke saath "1" ke combinations add kr dene hai.
vector<string> codes {".","abc","def","ghi","jkl","mno","pqrs","tuv","wx","yz","&:","#@","?!"};//10 can mean 1-0 or 10.
vector<string> keyPad(string str)
{
    if (str.length() == 0)
    {
    vector<string> base;
    base.push_back("");
    return base;
    }

    vector<string> myAns;
    char ch = str[0];
    string word1 = codes[ch - '0']; //char "num" to integer num conversion.eg->'1' to 1.
    vector<string> firstRes = keyPad(str.substr(1));
    for (string s : firstRes)
    {
        for (int i = 0; i < word1.length(); i++)
        {
            myAns.push_back(word1[i] + s);
        }
    }
    if (ch != '0' && str.length() > 1)
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

void Basic()
{
    vector<string> ans = keyPad("111");
    for(string s : ans)
    cout<<s<<endl;
}

//MazePath

//F->Faith-> (1,0),(0,1) and (1,1) se saare raste function jaanta hai humko bs vaha tk jaane ka kaam krna hai(returntype)
vector<string> mazePath_HVD(int sr,int sc,int er,int ec)
{
    if(sr == er && sc == ec)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }
    vector<string>myAns;
    if(sr < sc)
    {
        vector<string> vertical = mazePath_HVD(sr+1,sc,er,ec);
        for(string s : vertical)
        myAns.push_back("V" + s);
    }
    if(sc < ec)
    {
        vector<string> horizontal = mazePath_HVD(sr,sc+1,er,ec);
        for(string s : horizontal)
        myAns.push_back("H" + s);
    }
    if(sr < er && sc < ec)
    {
        vector<string> diagonal = mazePath_HVD(sr+1,sc+1,er,ec);
        for(string s : diagonal)
        {
            myAns.push_back("D" + s);
        }
    }
    return myAns;

}

//F-> jumps ke saath cells ke paas dest tk pohochne ke paths hai hume jumps lagakr un cells ke paas pohochna hai.
vector<string> MazePath_Jump(int sr,int sc,int er,int ec)
{
    if(sr == er && sc == ec)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }
    vector<string>myAns;
    for(int jump = 1; sr + jump <= er; jump++)
    {
        vector<string> vertical = MazePath_Jump(sr+jump,sc,er,ec);
        for(string s : vertical)
        myAns.push_back("V" + to_string(jump) + s);
    }
    for(int jump = 1; sc + jump <= ec; jump++)
    {
        vector<string> horizontal = MazePath_Jump(sr,sc+jump,er,ec);
        for(string s : horizontal)
        myAns.push_back("H" + to_string(jump) + s);
    }
    for(int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
    {
        vector<string> diagonal = MazePath_Jump(sr+jump,sc+jump,er,ec);
        for(string s : diagonal)
        myAns.push_back("D" + to_string(jump) + s);
    }
    return myAns;
}

void mazePath()
{
    vector<string>ans = MazePath_Jump(0,0,2,2);
    // vector<string>ans = mazePath_HVD(0,0,2,2);
    for(string s:ans)
    cout<<s<<" ";
}


//FloodFill
//Using isSafe funct. (voidtype)
bool isSafe(int x,int y,int er,int ec,vector<vector<bool>>&board)
{
    if(x < 0 || y < 0 || x > er || y > ec || board[x][y]) return false;
    return true;
}
vector<vector<int> >direction{{0,1},{-1,1,},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1}};
vector<string>dir{"R","1","U","2","L","3","D","4"};
int floodFill(int sr,int sc,int er,int ec,vector<vector<bool> > &board,string ans)
{
    if(sr == er && sc == ec)
    {
    cout<<ans<<endl;
    return 1;
    }
    int count = 0;
    board[sr][sc] = true;
    for(int d = 0;d < 8;d++)
    {
        int x = sr + direction[d][0];
        int y = sc + direction[d][1];
    
    if(isSafe(x,y,er,ec,board))
    {
        count += floodFill(x,y,er,ec,board,ans+dir[d]);
    }
    }
    board[sr][sc] = false;
    return count;
}

void floodFill()
{
    vector<vector<bool> > board(3,vector<bool>(3,false));
    cout<<floodFill(0,0,2,2,board,"");
}

void solve()
{
    Basic();
    // mazePath();
    // floodFill();
}

int main()
{
    solve();
    return 0;
}
