public class LinkedList<E> {
    Node<E> head;
    Node<E> tail;
    
    public LinkedList()
    {
        head = null;
        tail = null;
    }

    public LinkedList(Node<E> given)
    {
        head = given;
    }
    
    public void add(E obj)
    {
        Node<E> tempNode = new Node<>(obj);
        if(tail == null)
            head = tail = tempNode;
        else{
            tail.next = new Node<E>(obj);
            tail = tail.next;
        }
    }
    
    public Node<E> getTop()
    {
        return head;
    }
    
    public Node<E> getBottom() {
        return tail;
    }
    
    public void push(E obj){
        Node<E> tempNode = new Node<>(obj);
        if(head == null)
            head = tempNode;
        else {
            tempNode.next = head;
            head = tempNode;
        }
    }

    public E pop()
    {
        E value = head.getValue();
        head = head.next;
        return value;
    }

    public boolean contains(E obj) {
        Node<E> current = head;
        while (current != null) {
            if (current.value.equals(obj))
                return true;
            current = current.next;
        }
        return false;
    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }
}