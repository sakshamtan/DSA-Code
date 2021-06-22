public class client {
    public static void main(String [] args) throws Exception
    {
        linkedlist ll = new linkedlist();
        for (int i = 1 ; i < 10 ; i++)
        ll.addLast(i*10);
        ll.addAt(10,3);
        System.out.println(ll.getFirst());
        
    }

}