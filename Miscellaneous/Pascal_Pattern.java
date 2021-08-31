import java.util.Scacd nner;
public class Pascal_Pattern {

public static Scanner scn = new Scanner(System.in);

public static void NumberDiamond(final int rows) 
{
    int nst = 1;
    int nsp = rows / 2;
    for (int r = 1; r <= rows; r++) 
    {
        for (int csp = 1; csp <= nsp; csp++) 
            System.out.print(" ");

        int ele = r;
        if (r > (rows + 1) / 2) 
            ele = rows - r + 1;
    
        for(int cst = 1; cst <= nst; cst++)
        {
            System.out.print(ele);
            if (cst <= nst / 2)
                ele++;
            else
                ele--;
        }
        if (r <= rows / 2) 
        {
            nst += 2;
            nsp--;
        }
        else
        {
            nst -= 2;
            nsp++;
        }
        System.out.println();
    }
}

public static void pascalTriangle(final int rows)
{
    for (int n = 0; n < rows; n++) {
        int val = 1;
        for (int r = 0; r <= n; r++) {
            System.out.print(val + " ");
            val = (val * (n - r)) / (r + 1);

        }
        System.out.println();
    }

}

public static void special_Triangle(int rows)//online foundation Q17.
{
 int nsp = (rows * 2) - 3;
 int nst = 1;
    for(int r = 1; r <= rows; r++)
    {
        int val = 1;
        for(int cst = 1; cst <= nst; cst++)
        {
           System.out.print(val +"\t");
           val++;
        }
               
        for(int csp = 1; csp <= nsp; csp++)
        {
           System.out.print("\t");
        }
    if(r == rows)
    {
       nst--;
       val--;
    }
               
    for(int cst = 1; cst <= nst; cst++)
    {
       val--;
       System.out.print(val +"\t");
    }
   nst++;
   nsp -= 2;
   System.out.println();
   }         
}

public static void special_Hourglass(int rows)//online foundation Q18.
{
    int nsp = 0;
    int nst = rows;
    for(int r = 1; r <= rows; r++)
    {
        for(int csp = 1; csp <= nsp; csp++)
        {
            System.out.print("\t");
        }
        for(int cst = 1; cst <= nst; cst++)
        {
            if(r > 1 && r <= rows/2 && cst > 1 && cst < nst)
            System.out.print("\t");
            else
            System.out.print("*\t");
        }
        if(r <= rows/2)
        {
            nsp++;
            nst -= 2;
        }
        else
        {
            nst += 2;
            nsp--;
        }
        System.out.println();
    }
    

}

public static void main(final String[] args)
{
    System.out.println("enter rows");
    final int rows = scn.nextInt();
    NumberDiamond(rows);
}
}