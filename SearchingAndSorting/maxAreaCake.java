public class maxAreaCake{

public static boolean isPossibleToServeCake(int[] radiusArray,double cakeArea,int guests)
{
    int g = 0;
    for(int i = radiusArray.length-1; i >= 0; i--)
    {
        double area = Math.PI * radiusArray[i] * radiusArray[i];
        g += Math.floor(area / cakeArea);
        
        if(g >= guests)
        return true;
    }
    return false;
}

public static double maximumAreaCake(int[] radius,int guests)
{
    int n = radius.length;
    double si = 0.0, ei = 1e7;

    while((ei - si) > 1e-5)
    {
        double cakeArea = (si + ei) / 2.0;

        if(!isPossibleToServeCake(radius,cakeArea,guests))
            ei = cakeArea - 1e-5;
        else
            si = cakeArea;
    }
    return si;
}

public static void main(String[] args)
{
    int[] radius = new int[]{1,1,1,2,2,3};
    System.out.println(maximumAreaCake(radius,6));
}
}