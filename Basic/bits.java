public class bits{

//OFF -> ON and ON -> ON the kTh bit. (Similar to arr[i] = true;)
public static void OFF_ON_Kth_bit(int n, int k)
{
    int mask = 1 << k;
    int SetBit = (n | mask);
    System.out.println(Integer.toBinaryString(SetBit));
}

//ON -> OFF and OFF -> OFF the kTh bit. (Similar to arr[i] = false;)
public static void ON_OFF_Kth_bit(int n, int k)
{
    int rev_mask = ~(1<<k); // 1's complement 
    int SetBit = (n & rev_mask);
    System.out.println(Integer.toBinaryString(SetBit));
}

//ON -> OFF and OFF -> ON the kTh bit.(Similar to arr[i] = !arr[i]).
public static void Toggle_Kth_bit(int n, int k)
{
    int mask = (1<<k);
    int SetBit = (n ^ mask);
    System.out.println(Integer.toBinaryString(SetBit));
}

//Leetcode 191 -> Number of 1 bits. O(n) as loop 32 baar chlega hi har case mei.
public static void Count_Of_ON_bits(int n)
{
    int count = 0;
    for (int i = 0; i < 32 ; i++)
    {
        int mask = (1 << i);
        if ((mask & n) != 0) count++;
    }
    System.out.println(count);
}

//avg case O(logn) as no. is divided by 2 in each iteration by right shifting.
public static void Count_of_ON_bits_02(int n)
{
    int count = 0;
    while (n != 0)
    {
        if ((n & 1) != 0) count++;
        n >>>= 1;
    }
    System.out.print(count);
}

//most optimized as loop no. of set bits ke equivalent hi chlega. O(No. of set bits times).
public static void Count_of_ON_bits_03(int n)
{
    int count = 0;
    while (n != 0)
    {
        n &= (n-1);// n mei ye last set bit ko unset ('0') kr aata hai.
        count++;
    }
    System.out.print(count);
}

//Leetcode 231 -> Power of Two
public static boolean PowerofTwo(int n)
{
    if(n > 0)
    {
        if((n & (n-1)) == 0) return true; 
        // for power of two numbers -> n & (n-1) always = 0. (except when n = 0.)
    }
    return false;
}

//Leetcode 342 -> power of four no.s mei even no. of zeroes hote.(even no. of zeroes hote hai 1 ke right side mei)
public static boolean PowerofFour(int n)
{
    if(n > 0 && (n & (n-1))== 0)// Power of 4 numbers saare power of 2 to honge hi.
    {
        int count = 0;
        while(n != 1)
        {
            n >>= 1;
            //OR even no. of times 2 se divide krne pr no. 1 bna to power of four hota hai vrna power of 2.
            count++;
        }
        if((count & 1) == 0) return true;// even no.s always end with '0' in their bit representation.
        //if count = even then no. is a power of four.
    }
    return false;
}

// Leetcode 136 -> to print unique element in array where rest values are repeated twice.
public static int single_Number(int []arr)
{
    int ans = 0;
    for (int i = 0; i < arr.length; i++)
    {
        ans ^= arr[i];
        //same values 0 ho jaayengi ek dusre ke saath XOR krne se and unique value bach jaayegi last mei.
    }
    return ans;
}

// Leetcode 137 -> here all numbers are repeated k times (here 3). and 1 no. is present exactly once return that single no.
public static int single_Number_02(int [] arr)
{
    int ans = 0;
    int k = 3;
    for(int i = 0;i < 32;i++)//Saare 32 bit places par no. of '1' bits count krne hai.
    {
        int count = 0;
        int mask = (1<<i);
        for(int ele : arr)
        {
            if((ele & mask) != 0)//To count no. of 1's in the total array.
            count++; 
        }
        if((count % k) != 0) //Agr us place pr k ke multiple pr 1 nhi hai to us place pr ans ke 1 set kr dena hai.
        ans |= mask;
    }
    return ans;
}

//Leetcode 260 -> Single Number III
public int[] singleNumber_03(int[] nums) 
{
    int[] ans = new int[2];
        
    int val = 0;
    for(int ele : nums)
        val ^= ele;   // val mei ab dono unique no.s ka xor hoga.
        
    int d = (val & (-val));  // d will contain the last set bit of val
        
    int x = 0, y = 0;
    for(int ele : nums)   // dividing all no.s into two sets such that har unique ele diff set mei aaye and single Number I vaala concept use ho jaaye
    {
        if((d & ele) != 0)
            x ^= ele;  
        else
            y ^= ele;
    }
        
    ans[0] = x;
    ans[1] = y;
    return ans;
}

//Leetcode 1009 -> Complement of Base 10 Integer same as Leetcode 476 -> Number Complement
public int bitwiseComplement(int n) 
{
    int mask = 1;
    while(n > mask)
    {
        mask = (mask << 1) | 1;
    }
    return n ^ mask;
}

// Leetcode 2997 -> Minimum Operations to make Array XOR Equal to K
public int minOperations(int[] nums, int k) 
{
    int xorNums = 0;
    for(int ele : nums) 
    {
        xorNums ^= ele;
    }
    int ans = 0;
    for (int i = 31; i >= 0; i--)
    {
        int mask = (1 << i);
        if ((k & mask) != (xorNums & mask)) 
            ans++;
    }
    return ans;
}

public static void Print_bits(int n)
{
    for (int i = 31; i >= 0; i--)
    {
        int mask = (1 << i);
        if ((n & mask) == 0) 
        System.out.print(0);
        else
        {
            System.out.print(1);
        }
    }
}   

public static void main(String[] args)
{
    // System.out.println(Integer.toBinaryString(57));
    //OFF_ON_Kth_bit(57,1);
    // ON_OFF_Kth_bit(57,0);
    //Toggle_Kth_bit(57,1);
    // Count_of_ON_bits_03(57);
   // Print_bits(57);
    int [] arr = {1,1,2,2,3,3,4,4,5};
    single_Number(arr);

}
}