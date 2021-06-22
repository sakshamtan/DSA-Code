
//Leetcode 51 -> NQueens
class Solution {
    boolean [][] chess;
    boolean [] colA,diagA,adiagA;
    List<List<String>> res = new ArrayList<>();
    
    
    public List<List<String>> solveNQueens(int n) 
    {
        colA = new boolean[n];
        diagA = new boolean[n+n-1];
        adiagA = new boolean[n+n-1];
        chess = new boolean[n][n];
        Nqueen(n,0,n);
        return res;
        
    }

public void Nqueen(int n,int r,int tnq)
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
    return;
}
}