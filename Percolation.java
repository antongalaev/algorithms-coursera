/**
 * Created with IntelliJ IDEA.
 * User: anton
 * Date: 02/02/2014
 * Time: 14:50
 */
public class Percolation {

    private int size;
    private WeightedQuickUnionUF uf;
    private boolean[] data;

    public Percolation(int N) {         // create N-by-N grid, with all sites blocked
        size = N;
        // set opens
        data = new boolean[N * N + 2];
        data[0] = true;
        data[data.length - 1] = true;
        // set uqf
        uf = new WeightedQuickUnionUF(N * N + 2);
    }

    public void open(int i, int j) {     // open site (row i, column j) if it is not already
        if (isOpen(i, j)) return;
        int index = xyTo1D(i, j);
        data[index] = true;
        //if not top row
        if (i != 1 && isOpen(i - 1, j)) {
            uf.union(xyTo1D(i - 1, j), index);
        } else if (i == 1) {
            //connect to virtual top index
            uf.union(index, 0);
        }
        //if not bottom row
        if (i != size && isOpen(i + 1, j)) {
            uf.union(xyTo1D(i + 1, j), index);
        } else if (i == size) {
            //connect to virtual bottom index
            uf.union(index, size * size + 1);
        }
        //if not left border
        if (j != 1 && isOpen(i, j - 1)) {
            uf.union(xyTo1D(i, j - 1), index);
        }
        //if not right border
        if (j != size && isOpen(i, j + 1)) {
            uf.union(xyTo1D(i, j + 1), index);
        }
    }

    public boolean isOpen(int i, int j) {   // is site (row i, column j) open?
       return data[xyTo1D(i, j)];
    }

    public boolean isFull(int i, int j) {   // is site (row i, column j) full?
       return uf.connected(xyTo1D(i, j), 0);
    }

    public boolean percolates() {            // does the system percolate?
        return uf.connected(0, size * size + 1);
    }

    private int xyTo1D(int i, int j) {
        checkBounds(i, j);
        return size * (i - 1) + j;
    }

    private void checkBounds(int i, int j) {
        if (i < 1 || j < 1 || i > size || j > size) {
            throw new IndexOutOfBoundsException();
        }
    }
}

