import java.util.Scanner;
public class strings {

public static void printChar(String str)
{
    for (int i = 0; i < str.length(); i++)
    {
        System.out.println(str.charAt(i));
    }
}

public static void printSubstring(String str)    
{
    for (int i = 0; i < str.length(); i++)
    {
        for (int j = i+1; j <= str.length(); j++)
        {
            System.out.println(str.substring(i,j));
        }
    }
}

public static boolean isPalindrome(String str)
{
    int left = 0;
    int right = str.length()-1;
    while (left <= right)
    {
        if (str.charAt(left) != str.charAt(right))
            return false;

        left++;
        right--;
    }
    return true;
}

public static void printAllPalindromicSubstrings(String str)
{
        
    for (int i = 0; i < str.length(); i++)
    {
        for (int j = i + 1; j <= str.length(); j++)
        {
            String substr = str.substring(i,j);
            boolean palindrome = isPalindrome(substr);
            if(palindrome)
                System.out.println(substr);
        }
    }       
}

public static String toggleCharsCase(String str)
{
    StringBuilder sb = new StringBuilder(str);
    for (int i = 0; i < sb.length(); i++)
    {
        char ch = sb.charAt(i);
        if (ch >= 'a' && ch <= 'z') // if case is lower
            ch = (char)(ch - 'a' + 'A'); // convert to upper case. also (ch - 32) would work same.
        else
            ch = (char)(ch - 'A' + 'a'); // to lower case // (ch + 32) would also work same.
    
        sb.setCharAt(i,ch);
    }
    return sb.toString();
}

public static String modifyASCII(String str)
{
    StringBuilder sb = new StringBuilder(str);
    for (int i = 0; i < sb.length(); i++)
    {
        char ch = sb.charAt(i);
        if (i % 2 == 0)
            ch = (char)(ch - 1);  // at even index -1 of ascii value.
        else 
            ch =  (char)(ch + 1);
            
        sb.setCharAt(i,ch);
    }
    return sb.toString();
}

public static String modifyDifferenceASCII(String str)
{
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < str.length()-1; i++)
    {
        sb.append(str.charAt(i));
        int diff = str.charAt(i+1) - str.charAt(i);
        sb.append(diff); 
    }
    sb.append(str.charAt(str.length()-1));
    return sb.toString();
}
  
public static void Compress1(String str)
{
        
    for (int i = 0; i < str.length()-1; i++)
    {
        if (str.charAt(i) != str.charAt(i+1))
            System.out.print(str.charAt(i));
        else
            System.out.print("_");
    }
    System.out.print(str.charAt(str.length()-1));
}

public static void Compress2(String str)
{
    int count = 1;
    for (int i = 0; i < str.length()-1; i++)
    {
        if(str.charAt(i) != str.charAt(i+1))
        {
            System.out.print(str.charAt(i));
        
            if (count > 1)
            {
                System.out.print(count);
            }
            count = 1;
        }
        else           
            count++;
            
    }

    if (count > 1)
    System.out.print(count);
    System.out.print(str.charAt(str.length()-1));
}

public static void printSubseq(String str)//Using bits.
{
    for (int i = 0;  i < (1 << str.length()); i++)
    {
        for (int j = str.length() - 1; j >= 0; j--)
        {
            int mask = 1 << j;
            if ((i & mask) != 0) 
            {
            System.out.print(str.charAt(str.length() - 1 - j));
            }
        }
        System.out.println();
    }
}

public static void main(String[] args)    
{
        
    Scanner scn = new Scanner(System.in);
    System.out.println("Enter a string:");
    String str = scn.nextLine();
    // printChar(str);
    // printSubstring(str);
    // System.out.println(isPalindrome(str));
    // printAllPalindromicSubstrings(str);
    // System.out.println(toggleCharsCase(str));
    System.out.println(modifyASCII(str));
    // System.out.println(modifyDifferenceASCII(str));
    // Compress2(str);
    // printSubseq(str);
}    
}