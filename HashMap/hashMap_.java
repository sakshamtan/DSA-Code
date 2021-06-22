import java.util.LinkedList;
public class hashMap_{
    public class Node {
        Integer key;
        Integer value;

        public Node (Integer key , Integer value)
        {
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString ()
        {
            return this.key + "=" + this.value;
        }
    }
    private int size = 0;
    private LinkedList<Node>[] buckets = new  LinkedList[10];
    public hashMap_()
    {
        reAssign();
    }
    public void reAssign()
    {
        for (int i = 0 ; i < buckets.length ; i++)
        {
            buckets[i] = new LinkedList<>();
        }
        this.size = 0;
    }
    @Override
    public String toString ()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (int i = 0 ; i < buckets.length ; i++)
        {
            if (buckets[i].size()>0)
            {
                LinkedList<Node> group = buckets[i];
                int size = group.size();

                while (size-- > 0)
                {
                    Node tempNode = group.getFirst();
                    sb.append(tempNode + "");
                    group.addlast(group.removeFirst());
                }

            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("}");
        return sb.toString();
    }
    public int size()
    {
        return this.size;
    }
    public int MyGroup (Integer key)
    {
        return MyHashCode(key)%buckets.length;
    }
    public int MyHashCode (Integer key)
    {
        Integer val = key.hashCode();
        return Math.abs(val);
    }
    private Node get_(Integer key)
    {
        int code = MyGroup(key);
        LinkedList<Node> group = buckets[code];
    }
    public Integer get(Integer key)
    {
        return get_(key).value;
    }
    private Node foundInGroup (LinkedList<Node> group , Integer key)
    {
        Node rn = null;
        int size = group.size();
        while (size-- > 0)
        {
            Node tempNode = group.getFirst();
            if (tempNode.key == key)
            rn = tempNode;
            break;  
        }
        group.addLast(group.removeFirst());
        return rn;
    }
    public Node remove (Integer key)
    {
        int code = MyGroup (key);
        LinkedList <Node> group = buckets[code];

        Node rn = foundInGroup(group, key);
        if (rn != null)
        {
            this.size--;
            return group.removeFirst();
        }
        else {
            System.out.print("Not Found In HashMap");
        }
        return null;
    }
    public void put (Integer key , Integer value)
    {
        int code = MyGroup(key);
        LinkedList<Node> group = buckets[code];
        Node rn = foundInGroup(group, key);
        if (rn != null)
        rn.value = value;
        else {
            Node node = new Node (key , value);
            group.addLast(node);
            this.size++;

            double lambda = group.size()*1.0/buckets.length;
            if (lambda >= 0.3)
            rehash();
        }

    }
    public Boolean containsKey (Integer key)
    {
        int code = MyGroup(key);
        LinkedList<Node> group = buckets[code];
        Node rn = foundInGroup(group, key);
        if (rn = null)
        return false;
    }
   
}