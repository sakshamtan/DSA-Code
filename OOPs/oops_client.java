public class oops_client {
     public static void swap1 (person p1,person p2)// swapping doesnt work
    {
        person temp = p1;
        p1 = p2;
        p2 = temp;
    }
    public static void swap2 (person p1,person p2) // swapping takes place
    {
        int tage = p1.age;
        p1.age = p2.age;
        p2.age = tage;

        String tname = p1.name;
        p1.name = p2.name;
        p2.name = tname;
    }
    public static void main (String [] args)
    {
        person p1 = new person();
        person p2 = new person();
        p1.age = 10;
        p1.name = "A";

        p2.age = 20;
        p2.name = "B";
        System.out.println( p1.name +" -> "+ p1.age);
        System.out.println(p2.name +" -> "+ p2.age);
        System.out.println();
        //swap1(p1,p2);
        swap2(p1,p2);
        System.out.println(p1.name +" -> "+ p1.age);
        System.out.println(p2.name +" -> "+ p2.age);

    }
}