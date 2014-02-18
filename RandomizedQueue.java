import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: anton
 * Date: 16/02/2014
 * Time: 21:08
 */
public class RandomizedQueue<Item> implements Iterable<Item> {



    private int size;
    private Item[] data;


    public RandomizedQueue()  {
        data = (Item[]) new Object[2];


    }               // construct an empty randomized queue

    public boolean isEmpty()    {
        return size == 0;
    }             // is the queue empty?

    public int size()    {
         return size;
    }            // return the number of items on the queue


    public void enqueue(Item item){

    }           // add the item

    public Item dequeue()          {
         return null;
    }          // delete and return a random item

    public Item sample()            {
         return null;
    }         // return (but do not delete) a random item

    private void resize(int capacity) {
        assert capacity >= size;
        Item[] t = (Item[]) new Object[capacity];
        for (int i = 0; i < size; ++i) {
            t[i] = data[i];
        }
        data = t;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int current;

            @Override
            public boolean hasNext() {
                return current < size;
            }

            @Override
            public Item next() {
                if (hasNext()) {
                    return data[current++];
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }        // return an independent iterator over items in random order

    public static void main(String[] args)   {

    }// unit testing

}
