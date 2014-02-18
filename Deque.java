import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: anton
 * Date: 16/02/2014
 * Time: 19:58
 */
public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int size;

    public Deque() { // construct an empty deque
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() { // is the deque empty?
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node<Item> oldFirst = first;
        first = new Node<Item>(item);
        if (isEmpty()) {
            last = first;
        }  else {
            first.setNext(oldFirst);
            oldFirst.setPrev(first);
        }
        ++size;
    }

    // insert the item at the end
    public void addLast(Item item)          {
        if (item == null) {
            throw new NullPointerException();
        }
        Node<Item> oldLast = last;
        last = new Node<Item>(item);
        if (isEmpty()) {
            first = last;
        } else {
            last.setPrev(oldLast);
            oldLast.setNext(last);
        }
        ++size;
    }

    // delete and return the item at the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item value = first.getValue();
        first = first.getNext();
        --size;
        return value;
    }

    // delete and return the item at the end
    public Item removeLast()               {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item value = last.getValue();
        last = last.getPrev();
        --size;
        return value;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator()       {
           return new Iterator<Item>() {
               Node<Item> current = first;

               @Override
               public boolean hasNext() {
                   return current != null;
               }

               @Override
               public Item next() {
                   if (hasNext()) {
                       Item value = current.getValue();
                       current = current.getNext();
                       return value;
                   } else {
                       throw new NoSuchElementException();
                   }
               }

               @Override
               public void remove() {
                   throw new UnsupportedOperationException();

               }
           };
    }

    private class Node<Item> {
        private Item value;
        private Node<Item> next;
        private Node<Item> prev;

        Node(Item value) {
            this.value = value;
        }

        public void setPrev(Node<Item> prev) {
            this.prev = prev;
        }

        public Node<Item> getPrev() {
            return prev;
        }


        public Item getValue() {
            return value;
        }

        public void setValue(Item value) {
            this.value = value;
        }

        public Node<Item> getNext() {
            return next;
        }

        public void setNext(Node<Item> next) {
            this.next = next;
        }

    }

    // unit testing
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        for (int i = 0; i < 10; ++i) {
            deque.addLast(i);
        }
        for (int i = 0; i < 10; ++i) {
            System.out.println(deque.removeLast());
        }
        for (int i = 0; i < 10; ++i) {
            deque.addFirst(i);
        }
        for (Integer i : deque) {
            System.out.println(i);
        }
    }
}

