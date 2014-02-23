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


    }

    public boolean isEmpty()    {
        return size == 0;
    }             // is the queue empty?

    public int size()    {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (size == data.length) {
            resize(2 * size);
        }
        data[size++] = item;
    }

    public Item dequeue()  {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int i = StdRandom.uniform(size);
        Item res = data[i];
        data[i] = data[--size];
        if (size > 0 && size == data.length / 4) {
            resize(data.length / 4);
        }
        return res;
    }

    public Item sample()            {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data[StdRandom.uniform(size)];
    }

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
            private int current;
            private Item[] dataCopy;
            {
                dataCopy = (Item[]) new Object[size];
                for (int i = 0; i < size; ++i) {
                    dataCopy[i] = data[i];
                }
                StdRandom.shuffle(dataCopy);
            }

            @Override
            public boolean hasNext() {
                return current < size;
            }

            @Override
            public Item next() {
                if (hasNext()) {
                    return dataCopy[current++];
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

    public static void main(String[] args)   {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < 10; ++i) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 10; ++i) {
            StdOut.print(queue.dequeue());

        }
        System.out.println();
        for (int i = 0; i < 10; ++i) {
            queue.enqueue(i);
        }
        for (int i : queue) {
            StdOut.print(i);
        }
    }

}
