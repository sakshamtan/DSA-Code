import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class recursionREV{

//F -> "abc" mei "bc" ke subseq function le aayega humko ek baar a add krna hai aur ek baar nhi krna.
public static ArrayList<String> subseq_withASCII (String str)
{
    if (str.length() == 0)
    {
        ArrayList<String> base = new ArrayList<>();
        base.add("");
        return base;
    }
    char ch = str.charAt(0);
    ArrayList<String> recAns = subseq_withASCII(str.substring(1));
    ArrayList<String> myAns = new ArrayList<>();

    for (String rstr : recAns)
    {
        myAns.add(rstr);
        myAns.add(ch + rstr);
        myAns.add((int)ch + rstr); // ASCII value of that char.
    }
    return myAns;
}

//F -> "179" mei se "79" ke combinations functions le aayega aur un combinations ke saath "1" ke combinations add kr dene hai.
static String[] codes = {".","abc","def","ghi","jkl","mno","pqr","st","uvwx","yz"};
public static ArrayList<String> nokiaKeypad_01(String str)
{
    if (str.length() == 0)
    {
        ArrayList<String> base = new ArrayList<>();
        base.add("");
        return base;
    }

    char ch = str.charAt(0);
    ArrayList<String> recAns = nokiaKeypad_01(str.substring(1));
    ArrayList<String> myAns = new ArrayList<>();

    for (String rstr : recAns)
    {
        String code = codes[ch - '0'];//to convert char '1' into int 1.
        for (int i = 0; i < code.length(); i++)
        {
            char chcode = code.charAt(i);
            myAns.add(chcode + rstr);
        }
    }
    return myAns;
}

static String[] words = {".","abc","def","ghi","jkl","mno","pqr","st","uvwx","yz","<>","@&","*%"};
public static ArrayList<String> nokiaKeypad_02(String str)//advanced version with 12 keys.
{
    if (str.length() == 0)
    {
        ArrayList<String> base = new ArrayList<>();
        base.add("");
        return base;
    }

    char ch = str.charAt(0);
    ArrayList<String> recAns = nokiaKeypad_02(str.substring(1));
    ArrayList<String> myAns = new ArrayList<>();

    for (String rstr : recAns)
    {
        String word1 = words[ch - '0'];
        for (int i = 0; i < word1.length(); i++)
        {
            myAns.add(word1.charAt(i) + rstr);
        }

    }
    if (str.charAt(0) != 0 && str.length() > 1)
    {
        int num = (str.charAt(0)-'0') * 10 + str.charAt(1)-'0';
        if (num < 12)
        {
        recAns = nokiaKeypad_02(str.substring(2));
            for (String rstr : recAns)
            {
                String word2 = words[num];
                for (int i = 0; i < word2.length(); i++)
                {
                    myAns.add(word2.charAt(i) + rstr);
                }
            }
        }
    }
    return myAns;
}

//F -> Present level pr saare options exhaust krdo(for eg. ek baar a aayega ek baar nhi aayega next level pr same b ke liye and so on).
//Recursion on the way up -> ans builds up when we are travelling upwards in our tree and is finally printed in the base case.
public static int print_Subseq(String str, String asf)//voidtype version
{
    if (str.length() == 0)
    {
        System.out.println(asf);
        return 1;
    }

    int count = 0;
    char ch = str.charAt(0);
    String rstr = str.substring(1);
    count += print_Subseq(rstr,asf);
    count += print_Subseq(rstr,asf + ch);
    // count += print_Subseq(rstr,asf + (int)ch);   With ASCII
    return count;
}

//F -> Ek level mei saare options exhaust krke saare combinations explore krne hai. for eg - "179" -> "1" ke saare options i.e. -> "a","b","c" exhaust krne hai pehle level mei and so on.
// static String[] codes = {".","abc","def","ghi","jkl","mno","pqr","st","uvwx","yz","<>","@&","*%"};//already defined above.
public static int print_nokiaKeyPad(String ques, String asf)//voidtype version
{
    if (ques.length() == 0)
    {
        System.out.println(asf);
        return 1;
    }
    int count = 0;
    char ch = ques.charAt(0);
    String roq = ques.substring(1);
    String chcode = codes[ch - '0'];
    for (int i = 0; i < chcode.length(); i++)
    {
        char chchoice = chcode.charAt(i);
        count += print_nokiaKeyPad(roq,asf + chchoice);
    }
    return count;
}

// static String[] words = {".","abc","def","ghi","jkl","mno","pqr","st","uvwx","yz","<>","@&","*%"};
public static int print_nokiaKeyPad_02 (String ques, String asf)//10 means 1-0 and '10'
{
    if (ques.length() == 0)
    {
        System.out.println(asf);
        return 1;
    }

    int count = 0;
    char ch = ques.charAt(0);
    String word1 = words[ch-'0'];
    for (int i = 0; i < word1.length(); i++)
    {
        char chchoice1 = word1.charAt(i);
        count += print_nokiaKeyPad_02(ques.substring(1),asf + chchoice1);
    }
    if (ques.charAt(0) != 0 && ques.length() > 1)
    {
        int idx = (ch-'0') * 10 + (ques.charAt(1)-'0');
        if (idx  >= 10 && idx <= 12)
        {
        String word2 = words[idx];
            for (int i = 0; i < word2.length(); i++)
            {
                char chchoice2 = word2.charAt(i);
                count += print_nokiaKeyPad_02(ques.substring(2),asf + chchoice2);
            }

        }
    }
    return count;
}

//F->Ek baar single char ki call lagani hai aur ek baar check lga kr pair ki call lagani aur bachi huyi string encode function kr aayega.
public static int Encodings(String str,int idx,String ans)
{
    if(idx == str.length())
    {
        System.out.println(ans);
        return 1;
    }
    char ch = str.charAt(idx);
    int count = 0;
    if(ch == '0')
    return 0;
    count += Encodings(str,idx+1,ans + (char)((ch-'0')+('a' - 1)));
    if(idx < str.length()-1)
    {
        int ch1 = (ch-'0') * 10 + str.charAt(idx+1) - '0';
        if(ch1 >= 10 && ch1 <= 26)
        {
            count += Encodings(str,idx+2,ans + (char)(ch1 +'a' - 1));
        }
    }
    return count;   
}

public static void basic()
{
    // System.out.println(subseq_withASCII("abc"));
    // System.out.print(nokiaKeypad_02("111"));
    // System.out.println(print_Subseq("abc",""));
    // System.out.println(print_nokiaKeyPad("179",""));
    System.out.println(print_nokiaKeyPad_02("111",""));
}

//PathSet

//F-> Humaara function second step se raste bnana jaanta hai bs pehla step chalne ka dhyaan dena h.
public static int printBoardPaths(int src,int dest, String psf)
{
    if (src == dest)
    {
        System.out.println(psf);
        return 1;
    }

    int count = 0;
    for (int dice = 1; dice <= 6; dice++)
    {
        if (src + dice <= dest)
            count += printBoardPaths(src + dice,dest, psf + dice);
    }
    return count;
}
    
public static int printBoardPathsOpeningwith1and6(int src,int dest, String psf)
{
    if (src == dest)
    {
        System.out.println(psf);
        return 1;
    }

    int count = 0;
    if (src == 0) //khulli nhi abhi gitti
    {
        count += printBoardPathsOpeningwith1and6(1,dest,psf + 1);
        count += printBoardPathsOpeningwith1and6(1,dest,psf + 6);
    }
    else
    {
        for (int dice = 1; dice <= 6; dice++)
        {
            if (src + dice <= dest)
                count += printBoardPathsOpeningwith1and6(src + dice,dest, psf + dice);
        }
    }
    return count;
}

public static int printBoardPathsWithLadders(int src, int dest, int [] ladders, String psf)
{
    if (src == dest)
    {
        System.out.println(psf);
        return 1;
    }

    int count = 0;
    if (src == 0)
    {
        count += printBoardPathsWithLadders(1,dest,ladders,psf + 1);
        count += printBoardPathsWithLadders(1,dest,ladders,psf + 6);
    }

    else if (ladders[src] != 0)//ladders se chadhkr dice nhi chalte isliye dice vaale loop se pehle.
    {
        count += printBoardPathsWithLadders(ladders[src],dest,ladders,psf + "[" + src + "->" + ladders[src] + "]");
    }

    else 
    {
        for (int dice = 1; dice <= 6; dice++)
        {
            if (src + dice <= dest)
                count += printBoardPathsWithLadders(src + dice,dest,ladders,psf + dice);
        }
    }
    return count; 
}
    
//It can stuck in an infinite loop and still give a valid path so hence we are given moves array.
public static void BoardwithSNL(int src, int dest, int [] snl, int [] moves, int mvidx)//with snakes and ladders
{
    if (src == dest)
    {
        System.out.println("WIN");
        return;
    }

    if (mvidx == moves.length)
    {
        System.out.println(src);
        return;
    }

    if (src == 0)
    {
        if (moves[mvidx] == 1 || moves[mvidx] == 6)
        BoardwithSNL(1,dest,snl,moves,mvidx+1);
        else 
        BoardwithSNL(src,dest,snl,moves,mvidx+1);//nhi khulli gitti to moves chlte rhenge 
    }

    else 
    {
        if (snl[src] != 0)
        BoardwithSNL(snl[src],dest,snl,moves,mvidx);//snake ya ladder pr jaakr dice nhi chlte.
        else{
            if (src + moves[mvidx] <= dest)
            BoardwithSNL(src + moves[mvidx],dest,snl,moves,mvidx+1);
            else
            BoardwithSNL(src,dest,snl,moves,mvidx+1);//dest pr pohochne se zyaada no. aayega to vahi khade rhenge.
        }
    }
}

//Faith-> (1,0),(0,1) and (1,1) se saare raste function jaanta hai humko bs vaha tk jaane ka kaam krna hai(returntype)
//OR -> Har cell pr saare possible movements exhaust krdo jb base case hit krenge to ans mil jaayega(voidtype)
public static int MazePath_01(int sr, int sc, int er, int ec, String psf)//horizontal,vertical,Diagonal moves allowed.
{
    if (sr == er && sc == ec)
    {
        System.out.println(psf);
        return 1;
    }

    int count = 0;
    if (sr + 1 <= er)
        count += MazePath_01(sr+1,sc,er,ec,psf + "V");
    
    if (sc + 1 <= ec)
        count += MazePath_01(sr,sc+1,er,ec,psf + "H");
    
    if (sr + 1 <= er && sc + 1 <= ec)
        count += MazePath_01(sr+1,sc+1,er,ec,psf +"D");

    return count;
}

//F->Har cell destination tk ki journey jaanta hai hume bs h,v,d ki jumps lagani hai.
public static int MazePath_MM(int sr,int sc,int er,int ec,String psf)
{
    if (sr == er && sc == ec)
    {
        System.out.println(psf);
        return 1;
    }

    int count = 0;
    for (int jump = 1; jump <= er-sr; jump++)
    {
        count += MazePath_MM(sr+jump,sc,er,ec,psf + "V" + jump);
    }

    for (int jump = 1; jump <= ec-sc; jump++)
    {
        count += MazePath_MM(sr,sc+jump,er,ec,psf + "H" + jump);
    }

    for (int jump = 1; jump <= er-sr && jump <= ec-sc; jump++)
    {
        count += MazePath_MM(sr+jump,sc+jump,er,ec,psf + "D" + jump);
    }
    return count;
}

public static void floodFill(int sr,int sc,int [][] maze,boolean [][] visited,String psf)
{
    if (sr == maze.length-1  && sc == maze[0].length-1)
    {
        System.out.println(psf);
        return;
    }
        
    if (isSafe(sr,sc,maze) == true || visited[sr][sc] == true)
    return;

    visited[sr][sc] = true;
    floodFill(sr+1,sc,maze,visited,psf + "D");
    floodFill(sr,sc+1,maze,visited,psf + "R");
    floodFill(sr-1,sc,maze,visited,psf + "U");
    floodFill(sr,sc-1,maze,visited,psf + "L");
    visited[sr][sc] = false; //taaki vo path ke cells dusre paths mei bhi aa paaye.

}

public static boolean isSafe(int sr,int sc,int [][] maze)
{
    if (sr > maze.length-1 || sc > maze[0].length-1)
    return true;
    else if (sc < 0 || sr < 0)
    return true;
    else if (maze[sr][sc] == 0)
    return true;
    else 
    return false;
}

static int [][] dir = {{-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}};
static String[] dirS = {"U", "E", "R", "S", "D", "W", "L", "N"};
public static int floodFill_Jumps(int sr,int sc,int[][] vis,String psf)
{
    if(sr == vis.length-1 && sc == vis[0].length-1)
    {
        System.out.println(psf);
        return 1;
    }
    int count = 0;
    vis[sr][sc] = 1;
    for(int jump = 1; jump <= Math.max(vis.length-1,vis[0].length-1);jump++)
    {
        for(int d = 0; d < dir.length;d++)
        {
            int r = sr + jump * dir[d][0];//(0,4) means 4*(0,1)
            int c = sc + jump * dir[d][1];
            if(r >= 0 && c >= 0 && r < vis.length && c < vis.length && vis[r][c] == 0)//jaha mei jaa rha hu (r,c) vaha ke check lagakr call lagani hai(only valid calls will be allowed.)
            count += floodFill_Jumps(r,c,vis,psf + jump + dirS[d]);
        }
    }
    
    vis[sr][sc] = 0;
    return count;
}

static int[] xMove = {2,1,-1,-2,-2,-1,1,2};
static int[] yMove = {1,2,2,1,-1,-2,-2,-1};
public static boolean KnightsTour(int sr,int sc,int[][] board,int steps)
{
    board[sr][sc] = steps;
        
    if(steps == 63)//63 is the last step of knight in 8*8 board.
        return true;
    

    for(int d = 0; d < xMove.length;d++)
    {
        int r = sr + xMove[d];
        int c = sc + yMove[d];

        if(r >= 0 && c >= 0 && r < 8 && c < 8 && board[r][c] == -1)
        {
            boolean res = KnightsTour(r,c,board,steps+1);
            if(res) return true;
        }
    }
    board[sr][sc] = -1;
    return false;

}

public static void pathSet()
{
    // System.out.print(printBoardPaths(0,10,""));
    // System.out.print(printBoardPathsOpeningwith1and6(0,10,""));

    // int [] ladders = new int[16];
    // ladders[2] = 13;
    // ladders[3] = 11;
    // ladders[5] = 7;
    // System.out.println(printBoardPathsWithLadders(0,15,ladders,""));
    // int [] snl = new int[20];
    // snl[19] = 2;
    // snl[3] = 17;
    // snl[13] = 5;
    // snl[7] = 11;
    // int [] moves1 = {2,5,3,4,6,3,4,3,5,1,2,3};
    // int [] moves2 = {2,5,3,4,6,3,4,3,5,1,1,6,5,2,3,5};
    // BoardwithSNL(0,20,snl,moves2,0);
    // System.out.println(MazePath_01(0,0,2,2,""));
    // System.out.println(MazePath_MM(0,0,3,3,""));
    // int [][] maze ={{1,1,1,1},
    //                 {1,1,1,1},
    //                 {1,1,1,1},
    //                 {1,1,1,1}
    //                };
    // boolean [][] visited = new boolean[maze.length][maze[0].length];
    // floodFill(0,0,maze,visited,"");
    // System.out.print(Encodings("123",0,""));
    int n = 3;
    int m = 3;
    int[][] vis = new int[n][m];
    System.out.println(floodFill_Jumps(0,0,vis,""));
}

//Coin Change

//Har level pr saare options dene hai taaki saare arrangements aa jaaye using any no. of coins any times.
//F-> tar 8,7,5,3 se function ko permu bnane aate hai hume bs 2,3,5,7 se unke respective targets tk pohochana hai.
public static int coinChange_Permutation_01(int [] coins,int tar,String ans)//using multiple coins.
{
    if(tar == 0)
    {
        System.out.println(ans);
        return 1;
    }
    int count = 0;
    for(int i = 0; i < coins.length;i++)
    {
        if(tar - coins[i] >= 0)
        {
            count += coinChange_Permutation_01(coins,tar-coins[i],ans+coins[i]);
        }
    }
    return count;
}

//F-> Bs apne present idx se aage vaale indexes pr call lagani hai har level pr yaani idx se lekr end tk taaki sirf combinations aaye.
//for eg 2 can call/use 3,5,7 and 3 can call/use 5,7 and 5 can only call/use 7 on that certain level.
public static int coinChange_Combination_01(int [] coins,int idx,int tar,String ans)//using multiple coins.
{
    if(tar == 0)
    {
        System.out.println(ans);
        return 1;
    }
    int count = 0;
    for(int i = idx;i < coins.length;i++)//idx se lekr arr ke end tk ki call lagani hai
    {
        if(tar - coins[i] >= 0)
        {
            count += coinChange_Combination_01(coins,i,tar - coins[i],ans + coins[i]);
        }
    }
    return count;
}

//One coin can only be used once so that's why in the call idx = i + 1 taaki ek coin ek hi baar use ho.
public static int coinChange_Combination_Single_01(int [] coins,int idx,int tar,String ans)
{
    if(tar == 0)
    {
        System.out.println(ans);
        return 1;
    }
    int count = 0;
    for(int i = idx; i < coins.length;i++)
    {
        if(tar - coins[i] >= 0)
        {
            count += coinChange_Combination_Single_01(coins,i+1,tar-coins[i],ans + coins[i]);
        }
    }
    return count;
}

// using vis arr in normal voidtype recursion so that one coin is used only once in each call.
public static int coinChange_Permutation_Single_01(int [] coins,int tar,boolean [] vis,String ans)
{
    if(tar == 0)
    {
        System.out.println(ans);
        return 1;
    }
    int count = 0;
    for(int i = 0; i < coins.length;i++)
    {
        if(!vis[i] && tar - coins[i] >= 0)  
        {
            vis[i] = true;
            count += coinChange_Permutation_Single_01(coins,tar-coins[i],vis,ans+coins[i]);  
            vis[i] = false;//because that coin can be used in some other call. 
        }
    }
    return count;
}

//Subseq method -> ek baar coin use krne ki call lagana and ek baar coin nhi use krne ki call lagana.
//Combination using single coin only.
public static int coinChange_Combination_Single_Subseq_01(int [] coins,int idx,int tar,String ans)
{
    if(tar == 0 || idx >= coins.length)
    {
        if(tar == 0)
        {
            System.out.println(ans);
            return 1;
        }
        return 0;
    }
    int count = 0;
    if(tar - coins[idx] >= 0)
    count += coinChange_Combination_Single_Subseq_01(coins,idx+1,tar-coins[idx],ans + coins[idx]);
    //present idx pr coin use krne ki call usme idx+1 taaki same coin do baar use na ho jaaye.

    count += coinChange_Combination_Single_Subseq_01(coins,idx+1,tar,ans);
    //coin na use krne ki call usme bhi idx+1 for iteration.

    return count;
}

//Combination using multiple coins.
public static int coinChange_Combination_Subseq_01(int [] coins,int idx,int tar,String ans)
{
    if(tar == 0 || idx >= coins.length)
    {
        if(tar == 0)
        {
            System.out.println(ans);
            return 1;
        }
        return 0;
    }
    int count = 0;
    if(tar - coins[idx] >= 0)
    count += coinChange_Combination_Subseq_01(coins,idx,tar-coins[idx],ans + coins[idx]);
    //coin use krne pr idx hi taaki vo coin multiple baar use ho paaye.

    count += coinChange_Combination_Single_Subseq_01(coins,idx+1,tar,ans);
    //coin na use krne pr sirf idx+1 for iteration.

    return count;
}

//Permutation using multiple coins.
public static int coinChange_Permutation_Subseq_01(int [] coins,int idx,int tar,String ans)
{
    if(tar == 0 || idx >= coins.length)
    {
        if(tar == 0)
        {
            System.out.println(ans);
            return 1;
        }
        return 0;
    }
    int count = 0;
    if(tar - coins[idx] >= 0)
    count += coinChange_Permutation_Subseq_01(coins,0,tar-coins[idx],ans + coins[idx]);
    //idx = 0 so that agr koi coin use kiya to hai starting se dobaara saare options khul jaaye uske paas.

    count += coinChange_Permutation_Subseq_01(coins,idx+1,tar,ans);
    
    return count;
}

//permu using sinle coin so just using vis arr in the above code.
public static int coinChange_Permutation_Single_Subseq_01(int [] coins,int idx,int tar,boolean [] vis,String ans)
{
    if(tar == 0 || idx >= coins.length)
    {
        if(tar == 0)
        {
            System.out.println(ans);
            return 1;
        }
        return 0;
    }
    int count = 0;
    if(!vis[idx] && tar - coins[idx] >= 0)
    {
        vis[idx] = true;
        count += coinChange_Permutation_Single_Subseq_01(coins,0,tar - coins[idx],vis,ans + coins[idx]);
        vis[idx] = false;
    }
    count += coinChange_Permutation_Single_Subseq_01(coins,idx+1,tar,vis,ans);
    
    return count;
}

public static void CoinChange()
{
    int[] coins = {2,3,5,7};
    System.out.println(coinChange_Permutation_01(coins,10,""));
    // System.out.println(coinChange_Combination_01(coins,0,10,""));
    // System.out.println(coinChange_Combination_Single_01(coins,0,10,""));
    boolean [] vis = new boolean[coins.length];
    // System.out.println(coinChange_Permutation_Subseq_01(coins,0,10,vis,""));
    // System.out.println(coinChange_Combination_Single_Subseq_01(coins,0,10,""));
    // System.out.println(coinChange_Combination_Subseq_01(coins,0,10,""));
    // System.out.println(coinChange_Permutation_Single_Subseq_01(coins,0,10,vis,""));
    System.out.println(coinChange_Permutation_Subseq_01(coins,0,10,""));

}


//QueenCombinationAndPermutation

//q1->q2->q3 proper order of queens.Consider it as a case of coinChange with 1 on every cell of the box and tar = tnq.
//It is same as coinChangeCombination-single coin.
public static int queenCombination(boolean [] box,int idx,int qpsf,int tnq,String ans)
{
    if(qpsf == tnq)
    {
        System.out.println(ans);
        return 1;
    }
    int count = 0;
    for(int i = idx; i < box.length;i++)
    {
        count += queenCombination(box,i+1,qpsf+1,tnq,ans + "B" + i + "Q" + qpsf +" ");//to make ans of the form b0q0 b1q1 and so on.
    }
    return count;
}

//It is same as coinChange-permutation-Single(so using vis/box boolean arr).
public static int queenPermutation(boolean [] box,int qpsf,int tnq,String ans)
{
    if(qpsf == tnq)
    {
        System.out.println(ans);
        return 1;
    }
    int count = 0;
    for(int i = 0; i < box.length;i++)
    {
        if(!box[i])
        {
        box[i] = true;
        count += queenPermutation(box,qpsf+1,tnq,ans + "B" + i + "Q" + qpsf + " ");
        box[i] = false;
        }

    }
    return count;
}

//Same as 1D queenCombination just converting 2D arr of (4,4) to 1D arr of 16 length and changing the format of ans accordingly.
public static int queenCombination2D(boolean [][] box,int idx,int qpsf,int tnq,String ans)
{
    if(qpsf == tnq)
    {
        System.out.println(ans);
        return 1;
    }
    int count = 0;
    int n = box.length;
    for(int i = idx; i < n*n;i++)//considering (4,4) matrix as 1D arr of 16 length.
    {
        int r = i / n; //calculating row coordinate from 1D array of 16 length.
        int c = i % n; //column coordinate based on the current "i".
        count += queenCombination2D(box,i + 1,qpsf+1,tnq,ans + "(" + r + "," + c + ")" + " ");
    }
    return count;

}

//same as permutation-1D just converting 2D array into 1D and changing the format of ans.
public static int queenPermutation2D(boolean [][] box,int qpsf,int tnq,String ans)
{
    if(qpsf == tnq)
    {
        System.out.println(ans);
        return 1;
    }
    int count = 0;
    int n = box.length;
    for(int i = 0; i < n*n; i++)
    {
        int r = i / n;
        int c = i % n;
        if(!box[r][c])
        {
            box[r][c] = true;
            count += queenPermutation2D(box,qpsf+1,tnq,ans + "(" + r + "," + c + ")");
            box[r][c] = false;
        }
    }
    return count;
}

public static void QueenPnC()
{
    // boolean [] box = new boolean[6];
    boolean [][] box = new boolean[4][4];
    int tnq = 4;
    // System.out.println(queenCombination(box,0,0,tnq,""));
    // System.out.println(queenPermutation(box,0,tnq,""));
    System.out.println(queenCombination2D(box,0,0,tnq,""));
    // System.out.println(queenPermutation2D(box,0,tnq,""));
}

//Nqueen

public static boolean isSafeToPlaceQueen(boolean [][] box,int r,int c)
{
    int [][] dirA = {{0,-1},{-1,-1},{-1,0},{-1,1}};//for Combinations.
    // int [][] dirA = {{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};//for permutations.

    for(int d = 0; d < dirA.length;d++)
    {
        for(int rad = 1;rad <= box.length;rad++)
        {
            int x = r + rad * dirA[d][0];
            int y = c + rad * dirA[d][1];

            if(x >= 0 && y >= 0 && x < box.length && y < box[0].length)
            {
                if(box[x][y]) return false;
            }
            else break;//yaha pr aane ka mtlb loop out of index jaa chukka hai us radius/jump ke liye.
        }
    }
    return true;

}

//QueenCombination2D + isSafeToPlaceQueen = Nqueen_01.
public static int Nqueen_Combination_01(boolean [][] box,int idx,int qpsf,int tnq,String ans)
{
    if(qpsf == tnq)
    {
        System.out.println(ans);
        return 1;
    }
    int count = 0;
    int n = box.length;
    for(int i = idx;i < n*n;i++) //using queenCombination2D code base.
    {
        int r = i / n;
        int c = i % n;
        if(isSafeToPlaceQueen(box,r,c))
        {
            box[r][c] = true;//placing queen so next queen can check if any previous queen kills her.
            count += Nqueen_Combination_01(box,i+1,qpsf+1,tnq,ans + "(" + r + "," + c + ")" + " ");
            box[r][c] = false;//unplacing to make way for other combinations.
        }

    }
    return count;
}

//QueenPermutation + isSafeToPlaceQueen.
public static int Nqueen_Permutation_01(boolean[][] box,int qpsf,int tnq,String ans)
{
    if(qpsf == tnq)
    {
        System.out.println(ans);
        return 1;
    }
    int count = 0;
    int n = box.length;
    for(int i = 0; i < n*n;i++)
    {
        int r = i / n;
        int c = i % n;

        if(!box[r][c] && isSafeToPlaceQueen(box,r,c))
        {
            box[r][c] = true;
            count += Nqueen_Permutation_01(box,qpsf+1,tnq,ans + "(" + r + "," + c + ")" + " ");
            box[r][c] = false;
        }
    }
    return count;
}

//Shadow method uses four arrays to check for previous queens. searching in arrays is of O(1) hence it reduces the complexity as we dont use isSafe function.
static boolean [] rowA,colA,diagA,adiagA; //4 arrays of shadow method.
public static int Nqueen_Combination_02(int n,int idx,int qpsf,int tnq,String ans)//by Shadow method.
{
    if(qpsf == tnq)
    {
        System.out.println(ans);
        return 1;
    }
    int count = 0;
    for(int i = idx;i < n*n;i++)
    {
        int r = i / n;
        int c = i % n;
        if(!rowA[r] && !colA[c] && !diagA[r+c] && !adiagA[r-c+n-1])
        {
            rowA[r] = true;
            colA[c] = true;
            diagA[r+c] = true;
            adiagA[r-c+n-1] = true;

            count += Nqueen_Combination_02(n,i+1,qpsf+1,tnq,ans + "(" + r + "," + c + ")" + " ");

            rowA[r] = false;
            colA[c] = false;
            diagA[r+c] = false;
            adiagA[r-c+n-1] = false;
        }
    }
    return count;
}

//Permutation using shadow method.
public static int Nqueen_Permutation_02(int n,int qpsf,int tnq,String ans)
{
    if(qpsf == tnq)
    {
        System.out.println(ans);
        return 1;
    }
    int count = 0;
    for(int i = 0; i < n * n;i++)
    {
        int r = i / n;
        int c = i % n;
        if(!rowA[r] && !colA[c] && !diagA[r+c] && !adiagA[r-c+n-1])
        //present location pr queen placed ko chaaro arrays check kr rhe hai isliye alag se !box[r][c] ka check nhi lgana pdh rha (unlike when we did using isSafe funct.)
        {
            rowA[r] = true;
            colA[c] = true;
            diagA[r+c] = true;
            adiagA[r-c+n-1] = true;

            count += Nqueen_Permutation_02(n,qpsf+1,tnq,ans + "(" + r + "," + c + ")" + " ");

            rowA[r] = false;
            colA[c] = false;
            diagA[r+c] = false;
            adiagA[r-c+n-1] = false;
        }
    }
    return count;
}

//Nqueen-combination more optmized version.(now recursion is optimized).
//Har row mei ek hi queen baith skti hai to recursion mei hi row pr iterate kr rhe hai idx ki jagah.
public static int Nqueen_03(int n,int r,int tnq,String ans)
{
    if(tnq == 0)
    {
        System.out.println(ans);
        return 1;
    }

    int count = 0;
    for(int c = 0; c < n; c++)
    {
        if(!colA[c] && !diagA[r+c] && !adiagA[r-c+n-1])
        {
            //rowA ka alag se check lagane ki need nhi hai kyuki recursion hi har row mei ek hi queen place kr rhi hai.
            colA[c] = true;
            diagA[r+c] = true;
            adiagA[r-c+n-1] = true;

            count += Nqueen_03(n,r+1,tnq-1,ans + "(" + r + "," + c + ")" + " ");
            //on each row row only one queen is to be placed so in call -> r+1.

            colA[c] = false;
            diagA[r+c] = false;
            adiagA[r-c+n-1] = false;
        }
    }
    return count;
}

//Most Optimized solution as bits operations are perfectly O(1).
static int colN = 0, diagN = 0, adiagN = 0;
public static int Nqueen_Bits(int n,int r,int tnq,String ans)
{
    if(tnq == 0)
    {
        System.out.println(ans);
        return 1;
    }
    int count = 0;
    for(int c = 0;c < n;c++)
    {
        if((colN & (1 << c)) == 0 && (diagN & (1 << (r+c)))== 0 && (adiagN & (1 << (r-c+n-1))) == 0)
        {
            colN ^= (1 << c); // equivalent to col[c] = !col[c] (toggle) or col[c] = true;
            diagN ^= (1 << (r + c));
            adiagN ^= (1 << (r-c+n-1));

            count += Nqueen_Bits(n,r+1,tnq-1,ans + "(" + r + "," + c + ")" + " ");

            colN ^= (1 << c); //toggle here equivalent to col[c] = false;
            diagN ^= (1 << (r + c));
            adiagN ^= (1 << (r-c+n-1));
        }
    }
    return count;
}

//Useful when n > tnq but is generally slower than previous approach as the heigth of its tree is more.
public static int Nqueen_03_Subseq(int n,int r,int tnq,String ans)
{
    if(r >= n || tnq == 0)
    {
        if(tnq == 0)
        {
        System.out.println(ans);
        return 1;
        }
    return 0;
    }
    int count = 0;
    for(int c = 0; c < n; c++)
    {
        if(!colA[c] && !diagA[r+c] && !adiagA[r-c+n-1])
        {
            colA[c] = true;
            diagA[r+c] = true;
            adiagA[r-c+n-1] = true;

            count += Nqueen_03_Subseq(n,r+1,tnq-1,ans + "(" + r + "," + c + ")" + " ");

            colA[c] = false;
            diagA[r+c] = false;
            adiagA[r-c+n-1] = false;
        } 
    }
    count += Nqueen_03_Subseq(n,r+1,tnq,ans);
    //Nhi aane ki call mei bhi r+1 taaki next row pr aa jaaye.(similar to coinChangeCombi-single coin with r instead of idx)
    return count;
}

public static void Nqueens()
{
    boolean [][] box = new boolean[4][4];
    int tnq = 4;
    // System.out.println(Nqueen_Combination_01(box,0,0,tnq,""));
    // System.out.println(Nqueen_Permutataion_01(box,0,tnq,""));
    int n = box.length;
    int m = box[0].length;

    rowA = new boolean[n];
    colA = new boolean[m];
    diagA = new boolean[n+m-1];
    adiagA = new boolean[n+m-1];
    // System.out.println(Nqueen_Combination_02(n,0,0,tnq,""));
    // System.out.println(Nqueen_Permutation_02(n,0,tnq,""));
    // System.out.println(Nqueen_03(n,0,tnq,""));
    // System.out.println(Nqueen_03_Subseq(n,0,tnq,""));
    System.out.println(Nqueen_Bits(n,0,tnq,""));

}

//WordBreak

//F-> Agr kaata hua word dictionary(HashSet words) mei hai to usko ans mei add krenge and remaining string ko recursion mei bhej denge ki vo apne sub-sentences bna aaye.
public static int WordBreak_01(String ques,int len,HashSet<String> words,String ans)
{
    if(ques.length() == 0)
    {
        System.out.println(ans);
        return 1;
    }
    int count = 0;
    for(int i = 0; i <= len && i <= ques.length();i++)
    {
        String s = ques.substring(0,i);
        if(words.contains(s))
        {
            count += WordBreak_01(ques.substring(i),len,words,ans + s + " ");
        }
    }
    return count;
}

//substring O(N) ka operation hota hai isme call mei idx ke through virtually kaat rhe hai substring hence better complexity than previous.
public static int WordBreak_02(String ques,int len,int idx,HashSet<String> words,String ans)
{
    if(idx == ques.length())
    {
        System.out.println(ans);
        return 1;
    }
    int count = 0;
    for(int i = idx;i - idx <= len && i <= ques.length();i++)//idx se aage iterate krna hai.
    {
        String s = ques.substring(idx,i);
        if(words.contains(s))
        {
            count += WordBreak_02(ques,len,i,words,ans + s + " ");
        }
    }
    return count;
}

public static void knightTour()
{
    int [][] board = new int[8][8];
    for(int [] a : board) 
    Arrays.fill(a,-1);//function similar to single for loop to fill array(import arrays for this function.)

    KnightsTour(0,0,board,0);

    for(int[] a : board)
    {
        for(int ele : a)
        {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

}

public static void wordbreak()
{
    String [] dictionary = {"mobile","samsung","sam","sung", 
                            "man","mango","icecream","and", 
                            "go","i","like","ice","cream"};

    HashSet<String> words = new HashSet<>();

    int len = 0;
    for(String str : dictionary)
    {
        words.add(str);
        len = Math.max(len,str.length());
    }   

    String ques = "ilikesamsungandmango";
    // System.out.println(WordBreak_01(ques,len,words,""));                     
    System.out.println(WordBreak_02(ques,len,0,words,""));                     

}

public static void solve()
{
    // basic();
    // pathSet();
    // knightTour();
    // CoinChange();
    // QueenPnC();
    Nqueens();
    // wordbreak();
}

public static void main(String[] args)
{
    solve();
}
}