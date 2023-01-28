public class Node<E> {
    public E value;
    public Node<E> next;
    
    public Node(E obj)
    {
        value = obj;
        next = null;
    }
    
    public E getValue()
    {
        return value;
    }
}