public class StockBuyAndSell{

//Leetcode 121 -> Best Time to Buy and Sell Stock -> Only 1 transaction is allowed (so sirf sell and buy ki dp bnegi)
public int maxProfit(int[] prices) 
{
    int dpi10 = 0;   // present day mei sell krne se max profit
    int dpi11 = -(int)1e9; // present day mei buy krne se max profit
        
    for(int price : prices)
    {
        dpi10 = Math.max(dpi10,dpi11 + price);
        dpi11 = Math.max(dpi11,0 - price);  // 0 - price because 1 transaction is allowed
    }        
    return dpi10;  // profit to stock bech kr hi generate hota hai
}

//Leetcode 123 -> Best Time to Buy and Sell Stock III -> Now At most 2 transactions are allowed
public int maxProfit(int[] prices) 
{
    int dpi10 = 0, dpi11 = -(int)1e9; // k = 1
    int dpi20 = 0, dpi21 = -(int)1e9; // k = 2
        
    for(int price : prices)
    {
        dpi10 = Math.max(dpi10,dpi11 + price);
        dpi11 = Math.max(dpi11,0 - price);
            
        dpi20 = Math.max(dpi20,dpi21 + price);
        dpi21 = Math.max(dpi21,dpi10 - price);
    }
    return dpi20;
}

//Leetcode 122 -> Best Time to Buy and Sell Stock -> Now infinite transactions are allowed so ignoring k term
public int maxProfit(int[] prices) 
{
    int dpik0 = 0, dpik1 = -(int)1e9;
        
    for(int price : prices)
    {  
        int dpik_10 = dpik0;  
        dpik0 = Math.max(dpik0,dpik1 + price);
        dpik1 = Math.max(dpik1,dpik_10 - price); // 0 - price ki jagah k-1 transactions ke baad bache profit mei se price subtract krenge
    }
    return dpik0;
}

//Leetcode 714 -> Best Time to Buy and Sell Stock with Transaction Fee -> infinte transactions allowed but we will have to pay 'fee' money additional on each transaction
public int maxProfit(int[] prices, int fee) 
{
    int dpik0 = 0, dpik1 = -(int)1e9;
        
    for(int price : prices)
    {
        int dpik_10 = dpik0;
        dpik0 = Math.max(dpik0,dpik1 + price);
        dpik1 = Math.max(dpik1, dpik_10 - price - fee); // bs fee aur subtract krni hai har buy pe(itna hi change hai previous question se)
    }
    return dpik0;
}

//Leetcode 309 -> Best Time to Buy and Sell Stock with cooldown -> cooldown => bech kr agle din khareed nhi skte ek din ka rest lena pdega
public int maxProfit(int[] prices) 
{
    int dpik0 = 0, dpik1 = -(int)1e9;
    int dpi_2k_10 = 0;  // i - 2th day ka profit store krna pdega
        
    for(int price : prices)
    {
        int dpik_10 = dpik0;
            
        dpik0 = Math.max(dpik0,dpik1 + price);
        dpik1 = Math.max(dpik1,dpi_2k_10 - price);
        
        dpi_2k_10 = dpik_10;
    }
    return dpik0;
}
}