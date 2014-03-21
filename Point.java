import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: anton
 * Date: 04/03/2014
 * Time: 12:34
 */
public class Point implements Comparable<Point> {

    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            double slope1 = slopeTo(o1);
            double slope2 = slopeTo(o2);
            if (slope1 < slope2) {
                return -1;
            } else if (slope1 > slope2) {
                return 1;
            }
            return 0;
        }
    };        // compare points by slope to this point

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }                         // construct the point (x, y)

    public void draw() {
        StdDraw.point(x, y);
    }                               // draw this point

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }                  // draw the line segment from this point to that point

    public String toString() {
        return "(" + x + ", " + y + ")";
    }                           // string representation

    public int compareTo(Point that) {
        int cmp = y - that.y;
        if (cmp == 0) {
            cmp = x - that.x;
        }
        return cmp;
    }               // is this point lexicographically smaller than that point?

    public double slopeTo(Point that) {
        if (x == that.x) {
            if (y == that.y) {
                return Double.NEGATIVE_INFINITY;
            } else {
                return Double.POSITIVE_INFINITY;
            }
        } else if (y == that.y) {
            return 0;
        }
        return (double) (y - that.y) /  (x - that.x);
    }                 // the slope between this point and that point
}
