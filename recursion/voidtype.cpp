#include <iostream>
#include <vector>
#include <string>

using namespace std;

//F -> Present level pr saare options exhaust krdo(for eg. ek baar a aayega ek baar nhi aayega next level pr same b ke liye and so on).
//Recursion on the way up -> ans builds up when we are travelling upwards in our tree and is finally printed in the base case.
int subseq_01(string str, string asf)
{
    if (str.length() == 0)
    {
        cout << asf << endl;
        return 1;
    }

    int count = 0;
    char ch = str[0];
    string rstr = str.substr(1);
    count += subseq_01(rstr, asf);
    count += subseq_01(rstr, asf + ch);
    return count;
}

//F-> Ek level mei saare options exhaust krke saare combinations explore krne hai.
//for eg - "179" -> "1" ke saare options i.e. -> "a","b","c" exhaust krne hai pehle level mei and so on for next levels.
string words[] = {".", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wx", "yz", "&:", "@$"};
int keyPad(string ques, string ans)
{
    if (ques.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    char ch = ques[0];
    string word = words[ch - '0'];

    for (int i = 0; i < word.length(); i++)
    {
        count += keyPad(ques.substr(1), ans + word[i]);
    }
    if (ques[0] != 0 && ques.length() > 1)
    {
        int idx = (ch - '0') * 10 + (ques[1] - '0');
        if (idx >= 10 && idx <= 11)
        {
            string word2 = words[idx];
            for (int i = 0; i < word2.length(); i++)
            {
                count += keyPad(ques.substr(2), ans + word2[i]);
            }
        }
    }
    return count;
}

//F-> Ek baar ek single char(int) jaayega aur ek baar pair mei jaayega
// aur baaki function baaki ki encoding le aayega.
int Encoding(string str, int idx, string asf)
{
    if (idx == str.length())
    {
        cout << asf << endl;
        return 1;
    }
    char ch = str[idx];
    if (ch == '0')
        return 0;
    int count = 0;
    count += Encoding(str, idx + 1, asf + string(1, (ch - '0') + ('a' - 1))); //for correct mapping as a = 1 and not 0.
    if (idx < str.length() - 1)
    {
        int ch1 = (ch - '0') * 10 + (str[idx + 1] - '0');
        if (ch1 >= 10 && ch1 <= 26)
        {
            count += Encoding(str, idx + 2, asf + string(1, ch1 + 'a' - 1)); //to add 1 char to a string(syntax).
        }
    }
    return count;
}

//F-> Find all permutaions starting for 'a' then all starting for 'b' and then for c on each level(on higher levels bache huye options mei se har kisi ko apne se start hone vaale permu bnane ka chance dena hai.)
int permutations_01(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        string ros = str.substr(0, i) + str.substr(i + 1);
        count += permutations_01(ros, ans + ch);
    }
    return count;
}

//Only Unique permutations.-> vis arr rkhna hai and har level pe sirf distinct chars ki call lagani hai.
int permutations_Unique(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    vector<bool> vis(26, 0); //means 26 size and default false.
    for (int i = 0; i < str.length(); i++)
    {
        char ch = str[i];
        if (!vis[ch - 'a']) //ch-'a' maps index of vis arr to each alphabhet eg-> a->0 , b->1 and so on.
        {
            vis[ch - 'a'] = true;
            string ros = str.substr(0, i) + str.substr(i + 1);
            count += permutations_Unique(ros, ans + ch);
        }
    }
    return count;
}

//MazePath

//Faith-> (1,0),(0,1) and (1,1) se saare raste function jaanta hai humko bs vaha tk jaane ka kaam krna hai(returntype)
//OR -> Har cell pr saare possible movements exhaust krdo jb base case hit krenge to ans mil jaayega(voidtype)
int MazePath_HVD(int sr, int sc, int er, int ec, string psf)
{
    if (sr == er && sc == ec)
    {
        cout << psf << endl;
        return 1;
    }
    int count = 0;
    if (sr < er)
    {
        count += MazePath_HVD(sr + 1, sc, er, ec, psf + "V");
    }
    if (sc < ec)
    {
        count += MazePath_HVD(sr, sc + 1, er, ec, psf + "H");
    }
    if (sc < ec && sr < er)
    {
        count += MazePath_HVD(sr + 1, sc + 1, er, ec, psf + "D");
    }
    return count;
}

//F->Har cell dest tk ki journey jaanta hai hume bs h,v,d ki jumps lagani hai aur un cells tk pohochna hai.
int MazePath_Jump(int sr, int sc, int er, int ec, string psf)
{
    if (sr == er && sc == ec)
    {
        cout << psf << endl;
        return 1;
    }
    int count = 0;
    for (int jump = 1; jump <= er - sr; jump++)
    {
        count += MazePath_Jump(sr + jump, sc, er, ec, psf + "V" + to_string(jump));
    }
    for (int jump = 1; jump <= ec - sc; jump++)
    {
        count += MazePath_Jump(sr, sc + jump, er, ec, psf + "H" + to_string(jump));
    }
    for (int jump = 1; jump <= ec - sc && jump <= er - sr; jump++) //jump <= ec - sc -> ('=') important.
    {
        count += MazePath_Jump(sr + jump, sc + jump, er, ec, psf + "D" + to_string(jump));
    }
    return count;
}

vector<vector<int> > vis;                                                                       //cycle se bachane ke liye.
vector<vector<int> > dir{{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}}; //generic direction array.
vector<string> dirS{"U", "E", "R", "S", "D", "W", "L", "N"};                                    //direction string mapped with dir arr.

int floodFill(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    vis[sr][sc] = 1;
    for (int d = 0; d < dir.size(); d++)
    {
        int r = sr + dir[d][0];
        int c = sc + dir[d][1];

        if (r >= 0 && c >= 0 && r < vis.size() && c < vis[0].size() && vis[r][c] == 0)
            count += floodFill(r, c, er, ec, ans + dirS[d]);
    }
    vis[sr][sc] = 0;
    return count;
}
//vector<vector<int>>vis;
//vector<vector<int>> dir{{-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}};
//vector<string> dirS{"U", "E", "R", "S", "D", "W", "L", "N"};
int floodFill_WithJumps(int sr, int sc, int er, int ec, string psf)
{
    if (sr == er && sc == ec)
    {
        cout << psf << endl;
        return 1;
    }
    int count = 0;
    vis[sr][sc] = 1;
    for (int jump = 1; jump <= max(vis.size(), vis[0].size()); jump++)
    {
        for (int d = 0; d < dir.size(); d++)
        {
            int r = sr + jump * dir[d][0]; //(0,4) means 4 times (0,1)
            int c = sc + jump * dir[d][1];

            if (r >= 0 && c >= 0 && r < vis.size() && c < vis[0].size() && vis[r][c] == 0)
                count += floodFill_WithJumps(r, c, er, ec, psf + dirS[d] + to_string(jump) + " ");
        }
    }
    vis[sr][sc] = 0;
    return count;
}

class pathPair
{
public:
    string path;
    int len;

    pathPair(string path, int len)
    {
        this->path = path;
        this->len = len;
    }
};

//F-> recursion shortest path apni call se lekr dest. tk ka le aayega(recAns mei) hume +1 in len krke compare krna
//hai for each branch to find the minimum path. usme +1 length mei krke and path mei apni call ka step add krke ans return krdena hai.
pathPair floodFillShortestPath_jumps(int sr, int sc, int er, int ec)
{
    if (sr == er && sc == ec)
    {
        pathPair p("", 0);
        return p;
    }
    vis[sr][sc] = 1;
    pathPair ans("", 1e8);
    for (int jump = 1; jump <= max(er, ec); jump++)
    {
        for (int d = 0; d < dir.size(); d++)
        {
            int r = sr + jump * dir[d][0];
            int c = sc + jump * dir[d][1];
            if (r >= 0 && c >= 0 && r <= er && c <= ec && vis[r][c] == 0)
            {
                pathPair recAns = floodFillShortestPath_jumps(r, c, er, ec);
                if (recAns.len + 1 < ans.len)
                {
                    ans.len = recAns.len + 1; //updation of ans pair.
                    ans.path = recAns.path + dirS[d] + to_string(jump);
                }
            }
        }
    }

    vis[sr][sc] = 0;
    return ans;
}

//F-> recursion mujhe longest path de dega apni call se lekr destination tk ki mujhe + 1 in that length krke check krna hai
//agr longest path hoga to usme +1 in length and path mei apna step add krke ans return krwa dena hai.
pathPair floodFillLongestPath_jumps(int sr, int sc, int er, int ec)
{
    if (sr == er && sc == ec)
    {
        pathPair p("", 0);
        return p;
    }
    vis[sr][sc] = 1;
    pathPair ans("", -1e8);
    for (int jump = 1; jump <= max(er, ec); jump++)
    {
        for (int d = 0; d < dir.size(); d++)
        {
            int r = sr + jump * dir[d][0];
            int c = sc + jump * dir[d][1];
            if (r >= 0 && c >= 0 && r <= er && c <= ec && vis[r][c] == 0)
            {
                pathPair recAns = floodFillLongestPath_jumps(r, c, er, ec);
                if (recAns.len + 1 > ans.len)
                {
                    ans.len = recAns.len + 1;
                    ans.path = recAns.path + dirS[d] + to_string(jump);
                }
            }
        }
    }
    vis[sr][sc] = 0;
    return ans;
}

//CoinChange

//Har level pr saare options dene hai taaki saare arrangements aa jaaye using any no. of coins any times.
//F-> tar 8,7,5,3 se function ko permu bnane aate hai hume bs 2,3,5,7 se unke respective targets tk pohochana hai.
int coinChange_Permutation_01(vector<int> &coins, int tar, string ans) //using single coins.
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = 0; i < coins.size(); i++)
    {
        if (tar - coins[i] >= 0)
        {
            count += coinChange_Permutation_01(coins, tar - coins[i], ans + to_string(coins[i]));
        }
    }
    return count;
}

//F-> Bs apne present idx se aage vaale indexes pr call lagani hai har level pr yaani idx se lekr end tk taaki sirf combinations aaye.
//for eg 2 can call/use 3,5,7 and 3 can call/use 5,7 and 5 can only call/use 7 on that certain level.
int coinChange_Combination_01(vector<int> &coins, int idx, int tar, string ans) //using multiple coins.
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < coins.size(); i++) //har level pr present idx aur usse aage ki calls lagani hai.
    {
        if (tar - coins[i] >= 0)
        {
            count += coinChange_Combination_01(coins, i, tar - coins[i], ans + to_string(coins[i]));
        }
    }
    return count;
}

//One coin can only be used once so that's why in the call idx = i + 1 taaki ek coin ek hi baar use ho.
int coinChange_Combination_Single_01(vector<int> &coins, int idx, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = idx; i < coins.size(); i++)
    {
        if (tar - coins[i] >= 0)
        {
            count += coinChange_Combination_Single_01(coins, i + 1, tar - coins[i], ans + to_string(coins[i]));
        }
    }
    return count;
}

// using vis arr in normal voidtype recursion so that one coin is used only once in each call.
int coinChange_Permutation_Single_01(vector<int> &coins, vector<bool> &vis, int tar, string ans)
{
    if (tar == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = 0; i < coins.size(); i++)
    {
        if (!vis[i] && tar - coins[i] >= 0)
        {
            vis[i] = true;
            count += coinChange_Permutation_Single_01(coins, vis, tar - coins[i], ans + to_string(coins[i]));
            vis[i] = false;
        }
    }
    return count;
}

// coinChange using Subsequence method
//Subseq method -> ek baar coin use krne ki call lagana and ek baar call nhi use krne ki call lagana.

//Combination using single coin only.
int coinChange_Combination_Single_Subseq_01(vector<int> &coins, int idx, int tar, string ans)
{
    if (idx >= coins.size() || tar == 0)
    {
        if (tar == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }
    int count = 0;

    if (tar - coins[idx] >= 0)
        count += coinChange_Combination_Single_Subseq_01(coins, idx + 1, tar - coins[idx], ans + to_string(coins[idx]));
    //present idx pr coin use krne ki call usme idx+1 taaki same coin do baar use na ho jaaye.

    count += coinChange_Combination_Single_Subseq_01(coins, idx + 1, tar, ans);
    //coin na use krne ki call usme bhi idx+1 for iteration.

    return count;
}

//using multiple coins.
int coinChange_Combination_Subseq_01(vector<int> &coins, int idx, int tar, string ans)
{
    if (idx >= coins.size() || tar == 0)
    {
        if (tar == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }
    int count = 0;
    if (tar - coins[idx] >= 0)
        count += coinChange_Combination_Subseq_01(coins, idx, tar - coins[idx], ans + to_string(coins[idx]));
    //coin use krne pr idx hi taaki vo coin multiple baar use ho paaye.

    count += coinChange_Combination_Subseq_01(coins, idx + 1, tar, ans);
    //coin na use krne pr sirf idx+1 for iteration.

    return count;
}

//Permutation using multiple coins.
int coinChange_Permutation_Subseq_01(vector<int> &coins, int idx, int tar, string ans)
{
    if (idx >= coins.size() || tar == 0)
    {
        if (tar == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }

    int count = 0;
    if (tar - coins[idx] >= 0)
        count += coinChange_Permutation_Subseq_01(coins, 0, tar - coins[idx], ans + to_string(coins[idx]));
    //idx = 0 so that agr koi coin use kiya to hai starting se dobaara saare options khul jaaye uske paas.

    count += coinChange_Permutation_Subseq_01(coins, idx + 1, tar, ans);

    return count;
}

//Permutation using single coins only. -> Just using vis array in the above code.
int coinChange_Permutation_Single_Subseq_01(vector<int> &coins, vector<bool> &vis, int idx, int tar, string ans)
{
    if (idx >= coins.size() || tar == 0)
    {
        if (tar == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }
    int count = 0;
    if (!vis[idx] && tar - coins[idx] >= 0)
    {
        vis[idx] = true;
        count += coinChange_Permutation_Single_Subseq_01(coins, vis, 0, tar - coins[idx], ans + to_string(coins[idx]));
        vis[idx] = false;
    }

    count += coinChange_Permutation_Single_Subseq_01(coins, vis, idx + 1, tar, ans);
    return count;
}

//QueenCombinationAndPermutation======================================================================

//q1->q2->q3 proper order of queens.Consider it as a case of coinChange with 1 on every cell of the box and tar = tnq.
//It is same as coinChangeCombination-single coin.
int queenCombination_1D(vector<bool> &box, int idx, int qpsf, int tnq, string ans)
{
    if (idx >= box.size() || qpsf == tnq)
    {
        if (qpsf == tnq)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }
    int count = 0;

    count += queenCombination_1D(box, idx + 1, qpsf + 1, tnq, ans + "B" + to_string(idx) + "Q" + to_string(qpsf) + " ");
    //to make ans of the form b0q0 b1q1 and so on.

    count += queenCombination_1D(box, idx + 1, qpsf, tnq, ans);

    return count;
}

//It is same as coinChange-permutation-Single(so using vis/box boolean arr).
int queenPermutation_1D(vector<bool> &box, int idx, int qpsf, int tnq, string ans)
{
    if (idx >= box.size() || qpsf == tnq)
    {
        if (qpsf == tnq)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }
    int count = 0;

    if (!box[idx])
    {
        box[idx] = true;
        count += queenPermutation_1D(box, 0, qpsf + 1, tnq, ans + "B" + to_string(idx) + "Q" + to_string(qpsf) + " ");
        box[idx] = false;
    }
    count += queenPermutation_1D(box, idx + 1, qpsf, tnq, ans);

    return count;
}

//Same as 1D queenCombination just converting 2D arr of (4,4) to 1D arr of 16 length and changing the format of ans accordingly.
//through subseq method.(nci method has been done in java(slightly better)).
int queenCombination_2D(vector<vector<bool> > &box, int idx, int qpsf, int tnq, string ans)
{
    if (idx >= box.size() * box[0].size() || qpsf == tnq) //idx = 16 tk jaane dena hai for 4*4 matrix.
    {
        if (qpsf == tnq)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }
    int count = 0;

    int n = box.size();
    int r = idx / n;
    int c = idx % n;

    count += queenCombination_2D(box, idx + 1, qpsf + 1, tnq, ans + "(" + to_string(r) + "," + to_string(c) + ")" + " ");

    count += queenCombination_2D(box, idx + 1, qpsf, tnq, ans);

    return count;
}

//same as permutation-1D just converting 2D array into 1D and changing the format of ans.
int queenPermutation_2D(vector<vector<bool> > &box, int idx, int qpsf, int tnq, string ans)
{
    if (idx >= box.size() * box[0].size() || qpsf == tnq)
    {
        if (qpsf == tnq)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }
    int count = 0;

    int n = box.size();
    int r = idx / n;
    int c = idx % n;

    if (!box[r][c])
    {
        box[r][c] = true;
        count += queenPermutation_2D(box, 0, qpsf + 1, tnq, ans + "(" + to_string(r) + "," + to_string(c) + ")" + " ");
        box[r][c] = false;
    }

    count += queenPermutation_2D(box, idx + 1, qpsf, tnq, ans);

    return count;
}

//Nqueens===============================================================================================

bool isSafeToPlaceQueen(vector<vector<bool> > &box, int r, int c)
{
    // vector<vector<int>> dirA = {{0,-1},{-1,-1},{-1,0},{-1,1}};//for NqueenCombinations.
    vector<vector<int> > dirA = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}}; //for NqueenPermutations.

    for (int d = 0; d < dirA.size(); d++)
    {
        for (int rad = 1; rad < box.size(); rad++)
        {
            int x = r + rad * dirA[d][0];
            int y = c + rad * dirA[d][1];

            if (x >= 0 && y >= 0 && x < box.size() && y < box.size())
            {
                if (box[x][y])
                    return false;
            }
            else
                break; //yaha pr aane ka mtlb loop out of index jaa chukka hai us radius/jump ke liye.
        }
    }
    return true;
}

//QueenCombination2D + isSafeToPlaceQueen = Nqueen_01.
int Nqueen_Combination_01(vector<vector<bool> > &box, int idx, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    int n = box.size();
    for (int i = idx; i < n * n; i++)
    {
        int r = i / n;
        int c = i % n;
        if (isSafeToPlaceQueen(box, r, c))
        {
            box[r][c] = true; //placing queen so next queen can check if any previous queen kills her.
            count += Nqueen_Combination_01(box, i + 1, qpsf + 1, tnq, ans + "(" + to_string(r) + "," + to_string(c) + ")" + " ");
            box[r][c] = false; //unplacing to make way for other combinations.
        }
    }
    return count;
}

//QueenPermutation + isSafeToPlaceQueen.
int Nqueen_Permutation_01(vector<vector<bool> > &box, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    int n = box.size();

    for (int i = 0; i < n * n; i++)
    {
        int r = i / n;
        int c = i % n;

        if (!box[r][c] && isSafeToPlaceQueen(box, r, c))
        {
            box[r][c] = true;
            count += Nqueen_Permutation_01(box, qpsf + 1, tnq, ans + "(" + to_string(r) + "," + to_string(c) + ")" + " ");
            box[r][c] = false;
        }
    }
    return count;
}

//Shadow method uses four arrays to check for previous queens. searching in arrays is of O(1) hence it reduces the complexity as we dont use isSafe function.
vector<bool> row, col, diag, Adiag;                                      //4 arr of shadow method
int Nqueen_Combination_02(int n, int idx, int qpsf, int tnq, string ans) //by shadow method.
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = idx; i < n * n; i++)
    {
        int r = i / n;
        int c = i % n;

        if (!row[r] && !col[c] && !diag[r + c] && !Adiag[r - c + n - 1])
        {
            row[r] = true;
            col[c] = true;
            diag[r + c] = true;
            Adiag[r - c + n - 1] = true;

            count += Nqueen_Combination_02(n, i + 1, qpsf + 1, tnq, ans + "(" + to_string(r) + "," + to_string(c) + ")" + " ");

            row[r] = false;
            col[c] = false;
            diag[r + c] = false;
            Adiag[r - c + n - 1] = false;
        }
    }
    return count;
}

//permutation using shadow method.
int Nqueen_Permutation_02(int n, int qpsf, int tnq, string ans)
{
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;

    for (int i = 0; i < n * n; i++)
    {
        int r = i / n;
        int c = i % n;

        if (!row[r] && !col[c] && !diag[r + c] && !Adiag[r - c + n - 1])
        //present location pr queen placed ko chaaro arrays check kr rhe hai isliye alag se !box[r][c] ka check nhi lgana pdh rha (unlike when we did using isSafe funct.)
        {
            row[r] = true;
            col[c] = true;
            diag[r + c] = true;
            Adiag[r - c + n - 1] = true;

            count += Nqueen_Permutation_02(n, qpsf + 1, tnq, ans + "(" + to_string(r) + "," + to_string(c) + ")" + " ");

            row[r] = false;
            col[c] = false;
            diag[r + c] = false;
            Adiag[r - c + n - 1] = false;
        }
    }
    return count;
}

//Nqueen-combination more optmized version.(now recursion is optimized).
//Har row mei ek hi queen baith skti hai to recursion mei hi row pr iterate kr rhe hai idx ki jagah.
int Nqueen_03(int n, int r, int tnq, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int c = 0; c < n; c++)
    {
        if (!col[c] && !diag[r + c] && !Adiag[r - c + n - 1])
        {
            col[c] = true;
            diag[r + c] = true;
            Adiag[r - c + n - 1] = true;
            //rowA ka alag se check lagane ki need nhi hai kyuki recursion hi har row mei ek hi queen place kr rhi hai.

            count += Nqueen_03(n, r + 1, tnq - 1, ans + "(" + to_string(r) + "," + to_string(c) + ")" + " ");
            //on each row row only one queen is to be placed so in call -> r+1.

            col[c] = false;
            diag[r + c] = false;
            Adiag[r - c + n - 1] = false;
        }
    }
    return count;
}

//Useful when n > tnq but is generally slower than previous approach as the heigth of its tree is more.
int Nqueen_03_Subseq(int n, int r, int tnq, string ans)
{
    if (r >= n || tnq == 0)
    {
        if (tnq == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }
    int count = 0;
    for (int c = 0; c < n; c++)
    {
        if (!col[c] && !diag[r + c] && !Adiag[r - c + n - 1])
        {
            col[c] = true;
            diag[r + c] = true;
            Adiag[r - c + n - 1] = true;

            count += Nqueen_03_Subseq(n, r + 1, tnq - 1, ans + "(" + to_string(r) + "," + to_string(c) + ")" + " ");

            col[c] = false;
            diag[r + c] = false;
            Adiag[r - c + n - 1] = false;
        }
    }
    count += Nqueen_03_Subseq(n, r + 1, tnq, ans);
    //Nhi aane ki call mei bhi r+1 taaki next row pr aa jaaye.(similar to coinChangeCombi-single coin with r instead of idx)
    return count;
}

int colN = 0, diagN = 0, adiagN = 0;
int Nqueen_Bits(int n, int r, int tnq, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int c = 0; c < n; c++)
    {
        if ((colN & (1 << c)) == 0 && (diagN & (1 << (r + c))) == 0 && (adiagN & (1 << (r - c + n - 1))) == 0)
        {
            colN ^= (1 << c);
            diagN ^= (1 << (r + c));
            adiagN ^= (1 << (r - c + n - 1));

            count += Nqueen_Bits(n, r + 1, tnq - 1, ans + "(" + to_string(r) + "," + to_string(c) + ")" + " ");

            colN ^= (1 << c);
            diagN ^= (1 << (r + c));
            adiagN ^= (1 << (r - c + n - 1));
        }
    }
    return count;
}

//Sudoku=======================================================================
vector<vector<int> > board =
    {{3, 0, 0, 0, 0, 0, 0, 0, 0},
     {5, 2, 0, 0, 0, 0, 0, 0, 0},
     {0, 8, 7, 0, 0, 0, 0, 3, 1},
     {0, 0, 3, 0, 1, 0, 0, 8, 0},
     {9, 0, 0, 8, 6, 3, 0, 0, 5},
     {0, 5, 0, 0, 9, 0, 6, 0, 0},
     {1, 3, 0, 0, 0, 0, 2, 5, 0},
     {0, 0, 0, 0, 0, 0, 0, 7, 4},
     {0, 0, 5, 2, 0, 6, 3, 0, 0}};

void printBoard()
{
    for (vector<int> &a : board)
    {
        for (int ele : a)
        {
            cout << ele << " ";
        }
        cout << endl;
    }
}

//isSafeToPlaceNumber + sudokuSolver(recursion) = sudoku.
bool isSafeToPlaceNumber(int r, int c, int num)
{
    //row -> fixing c and iterating on r.
    for (int i = 0; i < 9; i++)
    {
        if (board[i][c] == num)
            return false;
    }

    //column -> fixing r and iterating on c.
    for (int i = 0; i < 9; i++)
    {
        if (board[r][i] == num)
            return false;
    }

    //sub-matrix of 3*3.
    int sr = (r / 3) * 3;
    int sc = (c / 3) * 3;
    // to reach on the starting coordinates (r,c) of the 3*3 sub-matrix.
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        //iterating on the 3*3 sub-matrix and checking if any cell contains num.
        {
            if (board[sr + i][sc + j] == num)
                return false;
        }
    }
    return true;
}

//vector<int> loc -> location of pre-existing 0's in board in the form of 1D coordinate.
//loc ki vajah se hum bs jaha 0 hai vhi recurison chlaate hai.
int SudokuSolver(int vidx, vector<int> &loc)
{
    if (vidx == loc.size())
    {
        printBoard();
        return 1;
    }

    int idx = loc[vidx]; //idx of 0 in the form of 1D coordinate.(i * 9 + j)
    int r = idx / 9;
    int c = idx % 9;

    int count = 0;
    for (int num = 1; num <= 9; num++)
    {
        if (isSafeToPlaceNumber(r, c, num))
        {
            board[r][c] = num;
            count += SudokuSolver(vidx + 1, loc);
            board[r][c] = 0;
        }
    }
    return count;
}

int rowA[9] = {0};
int colA[9] = {0};
int matA[3][3] = {0};
// Leetcode 36
bool isValidSudoku(vector<vector<char> > &board)
{
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            if (board[i][j] != '.') //Agr us jagah pr digit hai tbhi aage jayenge.
            {
                int mask = 1 << (board[i][j] - '0'); //to convert char digit to int digit.
                if ((rowA[i] & mask) == 0 && (colA[j] & mask) == 0 && (matA[i / 3][j / 3] & mask) == 0)
                {
                    rowA[i] ^= mask; //bit set krna hai xor krna same cheez hai.
                    colA[j] ^= mask;
                    matA[i / 3][j / 3] ^= mask;
                    //matA[i/3][j/3] because loop 9,9 tk chl rhe hai and matA 3X3 ka hai to uski range mei laane ke liye i,j ko.
                }
                else
                    return false;
            }
        }
    }
    return true;
}

int SudokuSolver_Best(int vidx, vector<int> &loc)
{
    if (vidx == loc.size())
    {
        printBoard();
        cout << endl;
        return 1;
    }
    int count = 0;
    int idx = loc[vidx];
    int i = idx / 9;
    int j = idx % 9;
    for (int num = 1; num <= 9; num++)
    {
        int mask = (1 << num);
        if ((rowA[i] & mask) == 0 && (colA[j] & mask) == 0 && (matA[i / 3][j / 3] & mask) == 0)
        {
            rowA[i] ^= mask;
            colA[j] ^= mask;
            matA[i / 3][j / 3] ^= mask;
            board[i][j] = num;

            count += SudokuSolver_Best(vidx + 1, loc);

            rowA[i] ^= mask;
            colA[j] ^= mask;
            matA[i / 3][j / 3] ^= mask;
            board[i][j] = 0;
        }
    }
    return count;
}

//Cryptarithmetic=====================================================================================

string s1 = "send";
string s2 = "more";
string s3 = "money";

int numUsed = 0;             //to mark which no. is used(array can also be used for this purpose).
vector<int> mapping(26, -1); // to tell which char is marked to which number.

//for eg 's' -> 9 ; 'e' -> 5; 'n' -> 6 and 'd' -> 7 to "send" ki jagah ye function 9567 return krdega.
int stringToNumber(string str)
{
    int res = 0;
    for (int i = 0; i < str.length(); i++)
    {
        res = res * 10 + mapping[str[i] - 'a'];
    }
    return res;
}

int cryptoSolver(string str, int idx)
{
    if (idx == str.length())
    {
        int x = stringToNumber(s1);
        int y = stringToNumber(s2);
        int z = stringToNumber(s3);

        if (x + y == z)
        {
            cout << " " << x << "\n+" << y << "\n-------\n"
                 << z << endl;
            cout << endl;
            return 1;
        }
        return 0;
    }
    char ch = str[idx];
    int count = 0;
    for (int num = 0; num <= 9; num++) //str mei saare char ko no. assign krne ke saare options.
    {
        int mask = (1 << num);
        if ((mask & numUsed) == 0)
        {
            numUsed ^= mask;
            mapping[ch - 'a'] = num;

            count += cryptoSolver(str, idx + 1);

            numUsed ^= mask;
            mapping[ch - 'a'] = -1;
        }
    }
    return count;
}

void crypto()
{
    string str = s1 + s2 + s3; //pre-processing of str. here str = "sendmoremoney"
    int freq = 0;
    for (char ch : str)
    {
        int mask = 1 << (ch - 'a');
        freq |= mask; //to mark which char's are present in str by their index(unke index pr bits turned on ho jayengi)
    }

    str = "";
    for (int i = 0; i < 26; i++)
    {
        int mask = (1 << i);
        if ((freq & mask) != 0)                //freq mei jonsa 'i' turned on hoga vo char add krna hai string mei taaki unique char's ki string bn jaaye.
            str += string(1, (char)(i + 'a')); // cpp syntax to add one char to string. ye unique char ki str pass krenge cryptosolver mei.
        //here str = "demnrosy" (saare unique characters bs).
    }
    cout << cryptoSolver(str, 0) << endl;
}

//Crossword==========================================================================================

vector<vector<char> > box = {{'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                             {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                             {'+', '-', '-', '-', '-', '-', '-', '-', '+', '+'},
                             {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                             {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                             {'+', '-', '-', '-', '-', '-', '-', '+', '+', '+'},
                             {'+', '-', '+', '+', '+', '-', '+', '+', '+', '+'},
                             {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
                             {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
                             {'+', '+', '+', '+', '+', '+', '+', '+', '+', '+'}};

bool canplaceWordH(string word, int r, int c)
{
    int l = word.length();
    int m = box[0].size();

    if (c == 0 && l < m) // left corner case.
    {
        if (box[r][c + l] != '+')
            return false;
    }

    else if (c + l == m) // right corner case.
    {
        if (box[r][c - 1] != '+')
            return false;
    }

    else
    {
        if ((c - 1 >= 0 && box[r][c - 1] != '+') || (c + l < m && box[r][c + l] != '+')) // middle case.
            return false;
    }

    for (int i = 0; i < word.length(); i++)
    {
        if (c + i == box[0].size())
            return false;

        if (box[r][c + i] != '-' && box[r][c + i] != word[i])
            return false;
    }

    return true;
}

int placeWordH(string word, int r, int c)
{
    int loc = 0;
    for (int i = 0; i < word.length(); i++)
    {
        box[r][c + i] = word[i];
        loc |= (1 << i); //marking the indexes of letters of the word that we have placed that we will unplace in the unplace function.
    }
    return loc;
}

void unplaceWordH(string word, int r, int c, int loc)
{
    for (int i = 0; i < word.length(); i++)
    {
        if ((loc & (1 << i)) != 0)
            box[r][c + i] = '-';
    }
}

bool canplaceWordV(string word, int r, int c)
{
    int l = word.length();
    int n = box.size();

    if (r == 0 && l < n) // left corner case.
    {
        if (box[r + l][c] != '+')
            return false;
    }

    else if (r + l == n) // right corner case.
    {
        if (box[r - 1][c] != '+')
            return false;
    }

    else
    {
        if ((r - 1 >= 0 && box[r - 1][c] != '+') || (r + l < n && box[r + l][c] != '+')) // middle case.
            return false;
    }

    for (int i = 0; i < word.length(); i++)
    {
        if (r + i == box.size())
            return false;

        if (box[r + i][c] != '-' && box[r + i][c] != word[i])
            return false;
    }

    return true;
}

int placeWordV(string word, int r, int c)
{
    int loc = 0;
    for (int i = 0; i < word.length(); i++)
    {
        box[r + i][c] = word[i];
        loc |= (1 << i); //marking the indexes of letters of the word that we have placed that we will unplace in the unplace function.
    }
    return loc;
}

void unplaceWordV(string word, int r, int c, int loc)
{
    for (int i = 0; i < word.length(); i++)
    {
        if ((loc & (1 << i)) != 0)
            box[r + i][c] = '-';
    }
}

bool crossWord(vector<string> &words, int idx)
{
    if (idx == words.size())
    {
        return true;
    }

    string word = words[idx];
    for (int i = 0; i < box.size(); i++)
    {
        for (int j = 0; j < box[0].size(); j++)
        {
            if (box[i][j] == '-' || box[i][j] == word[0])
            {
                if (canplaceWordH(word, i, j))
                {
                    int loc = placeWordH(word, i, j);
                    if (crossWord(words, idx + 1))
                        return true;               //ek ans milte hi recursion ruk jaaye and ans print ho jaaye.
                    unplaceWordH(word, i, j, loc); //for backtracking to give options to other words.
                }
                if (canplaceWordV(word, i, j))
                {
                    int loc = placeWordV(word, i, j);
                    if (crossWord(words, idx + 1))
                        return true;
                    unplaceWordV(word, i, j, loc);
                }
            }
        }
    }
    return false;
}

void basicQues()
{
    cout << subseq_01("abc", "") << endl;
    // cout<<keyPad("111","")<<endl;
    // cout<<Encoding("123",0,"");
    // cout<<permutations_01(string str,string ans)
    // cout<<permutations_Unique("aba","");
}

void mazePath()
{
    // cout<<MazePath_HVD(0,0,2,2,"");
    // cout<<MazePath_Jump(0,0,3,3,"");
    int n = 3;
    int m = 3;
    vis.resize(n, vector<int>(m, 0)); //vis ko dobaara resize(initialise krne ke liye)
    // cout<<floodFill_WithJumps(0,0,2,2,"")<<endl;
    // pathPair finAns = floodFillShortestPath_jumps(0,0,2,2);
    // cout<<finAns.path<<" -> "<<finAns.len;
    pathPair finAns = floodFillLongestPath_jumps(0, 0, 2, 2);
    cout << finAns.path << " -> " << finAns.len;
}

void CoinChange()
{
    vector<int> coins = {2, 3, 5, 7};
    int tar = 10;
    vector<bool> vis(coins.size(), false);
    // cout<<coinChange_Permutation_01(coins,tar,"")<<endl;
    // cout<<coinChange_Combination_01(coins,0,tar,"")<<endl;
    // cout<<coinChange_Combination_Single_01(coins,0,tar,"")<<endl;
    // cout<<coinChange_Permutation_Single_01(coins,vis,tar,"")<<endl;
    // cout<<coinChange_Combination_Single_Subseq_01(coins,0,tar,"")<<endl;
    // cout<<coinChange_Combination_Subseq_01(coins,0,tar,"")<<endl;
    // cout<<coinChange_Permutation_Subseq_01(coins,0,tar,"")<<endl;
    cout << coinChange_Permutation_Single_Subseq_01(coins, vis, 0, tar, "") << endl;
}

void QueenPnC()
{
    // int n = 6;
    int n = 4;
    int m = 4;
    // int tnq = 3;
    int tnq = 4;
    // vector<bool> box(n,false);
    vector<vector<bool> > box(n, vector<bool>(m, false));

    // cout<<queenCombination_1D(box,0,0,tnq,"")<<endl;
    // cout<<queenPermutation_1D(box,0,0,tnq,"")<<endl;
    // cout<<queenCombination_2D(box,0,0,tnq,"")<<endl;
    // cout<<queenPermutation_2D(box,0,0,tnq,"")<<endl;
}

void Nqueens()
{
    int n = 4;
    int m = 4;
    int tnq = 4;

    vector<vector<bool> > box(n, vector<bool>(m, false));

    // cout<<Nqueen_Combination_01(box,0,0,tnq,"")<<endl;
    // cout<<Nqueen_Permutation_01(box,0,tnq,"")<<endl;

    row.resize(n, false);
    col.resize(m, false);
    diag.resize(n + m - 1, false);
    Adiag.resize(n + m - 1, false);
    //resize() funct to re-initialize(vector ka size dena as it has already been declared above) the arrays.

    // cout<<Nqueen_Combination_02(n,0,0,tnq,"")<<endl;
    // cout<<Nqueen_Permutation_02(n,0,tnq,"")<<endl;
    // cout<<Nqueen_03(n,0,tnq,"")<<endl;
    // cout<<Nqueen_03_Subseq(n,0,tnq,"")<<endl;
    cout << Nqueen_Bits(n, 0, tnq, "") << endl;
}

void Sudoku()
{
    vector<int> loc;
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            if (board[i][j] == 0)
                loc.push_back(i * 9 + j);
            else
            {
                int mask = (1 << board[i][j]);
                rowA[i] ^= mask;
                colA[j] ^= mask;
                matA[i / 3][j / 3] ^= mask;
            }
        }
    }
    // cout<<SudokuSolver(0,loc)<<endl;
    cout << SudokuSolver_Best(0, loc) << endl;
}

void crossWord()
{
    vector<string> words = {"agra", "norway", "england", "gwalior"};
    crossWord(words, 0);
    for (int i = 0; i < box.size(); i++)
    {
        for (int j = 0; j < box[0].size(); j++)
        {
            cout << box[i][j] << " ";
        }
        cout << endl;
    }
}

void solve()
{
    // basicQues();
    // mazePath();
    // CoinChange();
    // QueenPnC();
    // Nqueens();
    // Sudoku();
    // crypto();
    crossWord();
}

int main()
{
    solve();
    return 0;
}
