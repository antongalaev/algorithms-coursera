/**
 * Created with IntelliJ IDEA.
 * User: anton
 * Date: 23/02/2014
 * Time: 18:35
 */
public class Subset {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (StdIn.hasNextLine()) {
            try {
                queue.enqueue(StdIn.readString());
            } catch (Exception e) { }
        }
        for (int i = 0; i < k; ++i) {
            StdOut.println(queue.dequeue());
        }
    }
}
