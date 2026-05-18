import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //load points
        List<Point> points = loadPointsFromFile("/home/adils/IdeaProjects/Persistence/src/in.txt");
        double maxRadius=0; // will be changed in vietoris rips
        //filter
        List<Simplex> filtration = VietorisRips.filter(points, maxRadius);

        //build Matrix
        int [][] boundaryMatrix = BoundaryMatrix.buildMatrix(filtration);

        //compute persistence
        Persistence.reduce(boundaryMatrix,filtration);

    }

    public static List<Point> loadPointsFromFile(String fileName) {
        List<Point> points = new ArrayList<Point>();

        try {
            Scanner sc = new Scanner(new File(fileName));

            int n = sc.nextInt();

            for (int i = 0; i < n; i++) {

                double x,y,z;
                try {
                    x = sc.nextDouble();
                    y = sc.nextDouble();
                    z = sc.nextDouble();
                }
                catch (Exception e) {
                    System.out.println("In number "+ (i+1)+"th point");
                    throw new Exception("xyz not given properly");
                }


                points.add( new Point( x,y,z ) );
            }
            return points;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return points;
    }
}
