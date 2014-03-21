import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: anton
 * Date: 04/03/2014
 * Time: 19:13
 */
public class Fast {
    public static void main(String[] args) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        Point[] points;
        In inf = new In(args[0]);
        int length = inf.readInt();
        points = new Point[length];
        for (int i = 0; i < length; ++i) {
            int x = inf.readInt();
            int y = inf.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }
        Point[] pointsCopy = Arrays.copyOf(points, length);
        for (int i = 0; i < length; ++i) {
            Arrays.sort(pointsCopy, points[i].SLOPE_ORDER);
            for (int j = 0; j < length - 3; ++j) {
                double slope = points[i].slopeTo(pointsCopy[j]);
                int k = j + 1;
                while (points[i].slopeTo(pointsCopy[k]) == slope) {
                    ++k;
                }
                if (k - j >= 3) {
                    StringBuilder str = new StringBuilder(points[i].toString());
                    for (int l = j; l < k; ++l) {
                        str.append(" -> ").append(pointsCopy[l]);
                    }
                    StdOut.println(str);
                    points[i].drawTo(pointsCopy[k]);
                }
            }
        }
    }
}
