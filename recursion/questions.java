public class questions{
//Leetcode 51 -> N - Queens
boolean [][] chess;
boolean [] colA,diagA,adiagA;
List<List<String>> res = new ArrayList<>();

public void Nqueen_01(int n,int r,int tnq)
{
    if(tnq == 0)
    {
      List<String> ans = new ArrayList<>();
        for(int i = 0; i < n; i++)
        {
            StringBuilder sb = new StringBuilder();
        
            for(int j = 0; j < n; j++)
            {
                if(chess[i][j])
                {
                    sb.append("Q");
                }
                else sb.append(".");
            }
            ans.add(sb.toString());
        }
        res.add(ans);
        return;
    }
     
    for(int c = 0; c < n; c++)
    {
        if(!colA[c] && !diagA[r+c] && !adiagA[r-c+n-1])
        {
            colA[c] = true;
            diagA[r+c] = true;
            adiagA[r-c+n-1] = true;
            chess[r][c] = true;
            
            Nqueen(n,r+1,tnq-1);
        
            chess[r][c] = false;
            colA[c] = false;
            diagA[r+c] = false;
            adiagA[r-c+n-1] = false; 
        }
    }
}

//Leetcode function
public List<List<String>> solveNQueens(int n)     
{
    colA = new boolean[n];
    diagA = new boolean[n+n-1];
    adiagA = new boolean[n+n-1];
    chess = new boolean[n][n];
    Nqueen_01(n,0,n);
    return res;        
}

//Leetcode 52 -> N - Queens II
boolean [] colA,diagA,adiagA;

public int Nqueen(int n,int r,int tnq)
{
    if(tnq == 0)
        return 1;

    int count = 0;
    for(int c = 0; c < n; c++)
    {
        if(!colA[c] && !diagA[r+c] && !adiagA[r-c+n-1])
        {
            colA[c] = true;
            diagA[r+c] = true;
            adiagA[r-c+n-1] = true;

            count += Nqueen(n,r+1,tnq-1);
            
            colA[c] = false;
            diagA[r+c] = false;
            adiagA[r-c+n-1] = false;
        }
    }
    return count;
}

//Leetcode function
public int totalNQueens(int n) 
{
    colA = new boolean[n];
    diagA = new boolean[n+n-1];
    adiagA = new boolean[n+n-1];
    return Nqueen(n,0,n); 
}

//Leetcode 140 -> Word Break II -> Recursion se bhi pass ho rha hai but better optimized with dp
public List<String> wordBreak(String s, List<String> wordDict) 
{
    List<String> ans = new ArrayList<>();
    if(wordDict.contains(s))
        ans.add(s);
        
    for(int i = 0; i < s.length(); i++)
    {
        String word = s.substring(0,i);
        if(wordDict.contains(word))
        {
            List<String> recAns = wordBreak(s.substring(i),wordDict);
            for(String sen : recAns)
                ans.add(word + " " + sen);
                      
        }
    }
    return ans;
}
}
