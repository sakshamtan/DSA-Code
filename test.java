public class test{

// n = no. of Prisoners ; m = no. of Treats ; s = starting chair Number
public static int saveThePrisoner(int n,int m,int s)
{
    int r = m % n;
    
    if((r + (s - 1)) % n == 0)
        return n;
    else
        return (r + s - 1) % n; 
}

public static int magicSquare(int[][] matrix)
{
    int[][] squares = new int[][]{
        {8, 3, 4, 1, 5, 9, 6, 7, 2},
        {4, 3, 8, 9, 5, 1, 2, 7, 6},
        {8, 1, 6, 3, 5, 7, 4, 9, 2},
        {4, 9, 2, 3, 5, 7, 8, 1, 6},
        {2, 7, 6, 9, 5, 1, 4, 3, 8},
        {6, 7, 2, 1, 5, 9, 8, 3, 4},
        {6, 1, 8, 7, 5, 3, 2, 9, 4},
        {2, 9, 4, 7, 5, 3, 6, 1, 8}
    };

    int minCost = (int)1e9;
    for(int i = 0; i < 8; i++)
    {
        int presentCost = 0;
        for(int j = 0; j < 9; j++)
        {
            presentCost += Math.abs(matrix[j/3][j%3] - squares[i][j]);
        }
        minCost = Math.min(minCost,presentCost);
    }
    return minCost;
}

public static void main(String[] args)
{
    // System.out.println(saveThePrisoner(5,2,2));

    // int[][] arr = new int[][]{{5,3,4},{1,5,8},{6,4,2}};
    int[][] arr = new int[][]{{4,9,2},{3,5,7},{8,1,5}};

    System.out.println(magicSquare(arr));

}
}