import java.util.List;

public final class PointUtils {
    static double maxDistance;
    private static PointUtils instance;

    private PointUtils(){
        System.out.println(".");
    }

    public static PointUtils getInstance(){
        if(instance == null){
            instance = new PointUtils();
        }
        return instance;
    }

    private double distance(Point p, Point q) {
        double distance=0;
        double dx= p.getX()-q.getX();
        double dy= p.getY()-q.getY();
        if(p.getPointDimension()==2){
             distance= Math.sqrt(dx*dx+dy*dy);
        }
        else if(p.getPointDimension()==3){
            double dz= p.getZ()-q.getZ();
            distance = Math.sqrt(dx*dx+dy*dy + dz*dz);
        }
        rememberMax(distance);
        return distance;
    }

    public double getMaxDistance(List<Point> points){
        double maxDistance=0;
        for( Point q:points) {
            for (Point p : points) {
                if (maxDistance < distance(p, q))
                    maxDistance = distance(p, q);
            }
        }
        return maxDistance;
    }

    private static void rememberMax(double distance){
        if(distance>maxDistance){
            maxDistance=distance;
        }
    }

    private static void setMaxDistance(double x) {
        maxDistance=x ;
    }
    public static double getMaxDistance(){
        return maxDistance;
    }

}
