import java.util.HashMap;
import java.util.ArrayList;

public class BasicHM{

public static void test_01()
{
    HashMap<String, Integer> map = new HashMap<>();
    map.put("USA",1000);
    map.put("IND",10000);
    map.put("NEP",90);
    map.put("usa",9000);

    System.out.println(map); // direct map print kr skte hai

    map.put("USA",10022);  // USA is already present so uski value update ho jaayegi
    System.out.println(map);

    System.out.println(map.get("Usa")); // Usa not present so null will be printed

    for(String s : map.keySet()) // .keySet returns an array of keys  
        System.out.println(s + " -> " + map.get(s));

}

//Frequency map of a given string
public static void test_02(String str)
{
    HashMap<Character,Integer> map = new HashMap<>();
    for(int i = 0; i < str.length();i++)
    {
        char ch = str.charAt(i);
        if(!map.containsKey(ch))
            map.put(ch,1);
        else
            map.put(ch,map.get(ch) + 1);
    }

    //OR BETTER SYNTAX ->
    // for(int i = 0; i < str.length(); i++)
    // {
    //     char ch = str.charAt(i);
    //     map.put(ch,map.getOrDefault(ch,0) + 1);
    // }

    System.out.println(map);
}

//Storing indexes of all characters occuring in the string
public static void test_03(String str)
{
    HashMap<Character,ArrayList<Integer>> map = new HashMap<>();
    for(int i = 0; i < str.length(); i++)
    {
        char ch = str.charAt(i);
        map.putIfAbsent(ch,new ArrayList<>()); // pehli baar koi char aaya to uske corresponding new AL add
        map.get(ch).add(i); 
    }

    System.out.println(map);
}

public static void main(String[] args)
{
    // test_01();
    // test_02("abcbaadbdisnduhdej");
    test_03("abcbaadbdisnduhdej");
}
}