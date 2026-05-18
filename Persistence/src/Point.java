public class Point {

    private double x;
    private double y;
    private double z;
    static double maxDistance;
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z=z;
    }

    static double distance(Point p, Point q) {
        double dx = Math.abs(p.x - q.x);
        double dy = Math.abs(p.y - q.y);
        double dz = Math.abs(p.z - q.z);

        double dist= Math.sqrt(dx*dx + dy*dy + dz*dz);
//        if(dist>maxDistance){
//            maxDistance = dist;
//        }
        return dist;
    }

    static void setMaxDistance(double x) {
        //return 1;
         maxDistance=x ;
    }


    static double getMaxDistance() {
        //return 1;
        return maxDistance;
    }
}
