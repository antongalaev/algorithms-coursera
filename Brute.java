
/**
 * Created with IntelliJ IDEA.
 * User: anton
 * Date: 04/03/2014
 * Time: 13:06
 */
public class Brute {
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
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                if (i == j) {
                    continue;
                }
                for (int k = 0; k < length; ++k) {
                    double slopeJ = points[i].slopeTo(points[j]);
                    double slopeK = points[i].slopeTo(points[k]);
                    if (k == j || k == i || slopeJ != slopeK) {
                        continue;
                    }
                    for (int l = 0; l < length; ++l) {
                        if (l == k || l == i || l == j) {
                            continue;
                        }
                        double slopeL = points[i].slopeTo(points[l]);
                        if (slopeJ == slopeL
                                && points[i].compareTo(points[j]) < 0
                                && points[j].compareTo(points[k]) < 0
                                && points[k].compareTo(points[l]) < 0) {
                            StdOut.printf("%s -> %s -> %s -> %s\n", points[i], points[j], points[k], points[l]);
                            points[i].drawTo(points[l]);
                        }
                    }
                }
            }
        }
    }
}
