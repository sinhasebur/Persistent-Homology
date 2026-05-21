import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        System.out.println("Number of points to take? ");
//        int pNum = sc.nextInt();
//        System.out.println("points is "+ pNum);

        System.out.println("h to consider upto? ");
        int simpD = sc.nextInt();
        System.out.println("considered upto "+ simpD);
        Homology.setConsiderUpto(simpD);

        System.out.println("Dimension to consider ? ");
        int dim = sc.nextInt();
        System.out.println("dimesion is "+ dim);


        VietorisRips filtrationComplex = new VietorisRips(simpD);

        //load points
        List<Point> points = loadPointsFromFile("/home/adils/IdeaProjects/Persistence/src/in.txt", dim);

        //filter
        List<Simplex> filtration = filtrationComplex.filter(points);

//        //build Matrix
        BoundaryMatrixUtils bm = BoundaryMatrixUtils.getInstance(filtration);
        int [][] boundaryMatrix = bm.buildMatrix();
         bm.printMatrix(boundaryMatrix);
//
//
        Persistence p = new Persistence();
        List <Homology> h = p.reduce(boundaryMatrix);
        p.getInfo(h);

    }

    public static List<Point> loadPointsFromFile(String fileName, int dimension) {
        List<Point> points = new ArrayList<Point>();

        try {
            Scanner sc = new Scanner(new File(fileName));

            int n = sc.nextInt();
            Point.setNumberOfPoints(n);

            System.out.println(n + " points loaded");
            for (int i = 0; i < n; i++) {

                double x,y,z;
                if(dimension == 2){
                    x = sc.nextDouble();
                    y = sc.nextDouble();
                    points.add(new Point(x, y));
                }
                else if(dimension == 3){
                    x = sc.nextDouble();
                    y = sc.nextDouble();
                    z = sc.nextDouble();
                    points.add(new Point(x, y,z));
                }
            }
            return points;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return points;
    }
}
