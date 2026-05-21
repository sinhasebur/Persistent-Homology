public class Point {

    private final double x;
    private final double y;
    private double z;
    private final int pointDimension;
    private static int numberOfPoints;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z=z;
        pointDimension=3;
    }
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        pointDimension=2;
    }

    public double getX(){ return x;}
    public double getY(){ return y;}
    public double getZ(){ if (pointDimension==3) return z; else { System.out.println("Point not 3d"); return 0;  } }
    public int getPointDimension(){ return pointDimension; }

    public static int getNumberOfPoints(){ return numberOfPoints; }
    public static void setNumberOfPoints(int numberOfPoints) { Point.numberOfPoints = numberOfPoints; }
}
