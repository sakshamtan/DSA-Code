#include <iostream>
#include <vector>
#include<string>
#define vb vector<bool>
#define vvb vector<vb>
#define vi vector<int>
#define vvi vector<vi> 

using namespace std;

int subseq(string str,string asf)
{
    if (str.length() == 0)
    {
        cout<<asf<<endl;
        return 1;
    }

    int count = 0;
    char ch = str[0];
    string rstr = str.substr(1);
    count += subseq(rstr,asf);
    count += subseq(rstr,asf + ch);
    return count;
}

string words[] = {".","abc","def","ghi","jkl","mno","pqrs","tuv","wx","yz","&:","@$"};
int keyPad(string ques,string ans)
{
    if (ques.length() == 0)
    {
        cout<<ans<<endl;
        return 1;
    }

    int count = 0;
    int idx1 = ques[0]-'0';
    string word = words[idx1];

    for (int i = 0; i < word.length(); i++)
    {
        count += keyPad(ques.substr(1),ans+word[i]);
    }
    if (ques.length() > 1)
    {
        int idx = idx1 * 10 + (ques[1] - 0);
        if (idx >= 10 && idx <= 11)
        {
            string word2 = words[idx];
            for (int i = 0; i < word2.length(); i++)
            {
                count += keyPad(ques.substr(2),ans+word2[i]);
            }
        }
    }
    return count;
}

// 
//===============================================================================================
//int permuRepe(vector<int>& arr,int tar,string ans){
//    if (tar==0){
 //       cout<<ans<<endl;
//        return 1;
//    }
//    int count=0;
//    for(int i=0;i<arr.size();i++){
//        if (tar-arr[i]>=0){
//            count+=permuRepe(arr,tar-arr[i],ans+to_string (arr[i]));
//        }
//    }
 //   return count;
//}
//=========================================================================
/*
int queenCombi(vector<int>& box,int idx,int qpsf,int tnq,string ans){
  if (qpsf==tnq){
    cout<<ans<<endl;
  return 1;
   }
 int count=0;
    for (int i=idx;i<box.size();i++){
      count+=queenCombi(box,i+1,qpsf+1,tnq,ans+"b"+to_string(i)+"q"+to_string(qpsf))
  }
   return count;

}
int queen2Dcombi(vector<vector<bool>>&  board,int lpql,int qpsf,int tnq,string ans){
    if(qpsf==tnq){
        cout<<ans<<endl;
        return 1;
    }
    int count=0;
    for(int i=lpql;i<board.size()*board[0].size();i++){
        int x=i/board.size();
        int y=i%board.size();
       count+=queen2Dcombi(board,i+1,qpsf+1,tnq,ans+"(" +to_string(x)+","+to_string(y)+")");
    }
    return count;
}

bool isSafeToPlaceQueen(vector<vector<bool>>& board,int r,int c){
   vector<vector<int>> dir={{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}}; 
    for(int i=0;i<8;i++){
        for(int rad=1;rad<max(board.size(),board[0].size());rad++){
            int x=r+rad*dir[i][0];
            int y=c+rad*dir[i][1];
            if(x<0||y<0||x>=board.size()||y>=board[0].size()){
                break;
            }
            if (board[x][y]==true) return false;
        }
    }
    return true;
}
int nQueenCombi(vector<vector<bool>>& board,int lpql,int qpsf,int tnq,string ans){
   if(qpsf==tnq){
        cout<<ans<<endl;
        return 1;
    }
    int count=0;
    for(int i=lpql;i<board.size()*board[0].size();i++){
        int x=i/board[0].size();
        int y=i%board[0].size();
        if(isSafeToPlaceQueen(board,x,y)){
        board[x][y]=true;
        count+=nQueenCombi(board,i+1,qpsf+1,tnq,ans+"(" +to_string(x)+","+to_string(y)+")");
        board[x][y]=false;
    }
    }

    return count;
}

int nQueenPermu(vector<vector<bool>>& board,int lpql,int qpsf,int tnq,string ans){
    if(qpsf==tnq){
        cout<<ans<<endl;
        return 1;
    }
    int count=0;
    for(int i=0;i<board.size()*board[0].size();i++){
        int x=i/board[0].size();
        int y=i%board[0].size();
        if(isSafeToPlaceQueen(board,x,y)&&(!board[x][y])){
        board[x][y]=true;
        count+=nQueenPermu(board,0,qpsf+1,tnq,ans+"(" +to_string(x)+","+to_string(y)+")");
        board[x][y]=false;
    }
    }

    return count; 
   */ 
//}
// int nQueen2(int n,int r,int tnq,int &col,int &diag,int &adiag,string ans){
//     if  (r==n||tnq==0)
//         {if (tnq==0){
//             cout<<ans<<endl;
//             return 1;
//         }
//         return 0;
//     }
//     int count=0;
//     for(int c=0;c<n;c++)
//    { 
//     int w=1<<c;
//     int x=1<<(r+c);
//     int y=1<<(r-c+n-1);
//     if (!(col&w) && !(diag&x) && !(adiag&y)){
//         col^=w;
//         diag^=x;
//         adiag^=y;
//         count+=nQueen2(n,r+1,tnq-1,col,diag,adiag,ans+"(" +to_string(r)+","+to_string(c)+")");
//         col^=w;
//         diag^=x;
//         adiag^=y;
//     }
//    }
//    return count;
// }


void basicQues()
{
// cout<<subseq("abc","")<<endl;
// cout<<permu("abc","")<<endl;
cout<<keyPad("10110","")<<endl;
// cout<<permu2("aba","");
}

void Queens()
{
  //  vector<vector<bool>>board(4,vector<bool>(4,false));
 // cout<<nQueenPermu(board,0,0,4,"")<<endl;
//  int r=4,c=4;
//  int col=0;
//  int diag=0;
//  int adiag=0;
// //  vb col(4,false);
// //  vb diag(r+c-1,false);
// //  vb adiag(r+c-1,false);
//  cout<<nQueen2(4,0,4,col,diag,adiag,"")<<endl;

}

void solve()
{
   basicQues();
// permu();
// Queens();  
}

int main()
{
    solve();
    return 0;
}

