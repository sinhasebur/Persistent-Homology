import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class VietorisRips {

    private double radiusMax;

    public static List<Simplex> filter(List<Point> points, double radiusMax) {

        int n = points.size();

        List<Simplex> filtration = new ArrayList<>();

        //adding 0-simplices (vertices)
        for (int i = 0; i < n; i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(i);
            filtration.add(new Simplex(0, 0.0, temp));
        }

        //adding 1-simplices (edges)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double distanceAsBirthTime = Point.distance(points.get(i), points.get(j));
                //if (distanceAsBirthTime < radiusMax) {
                List<Integer> temp = new ArrayList<>();
                temp.add(i);
                temp.add(j);
                filtration.add(new Simplex(1, distanceAsBirthTime, temp));
                //}
                radiusMax = Math.max(radiusMax, distanceAsBirthTime);
            }
        }

        //radiusMax = Point.getMaxDistance();
        Point.setMaxDistance(radiusMax);
        System.out.println("Max radius: " + radiusMax);


        //adding 2-simplicies (triangles)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    double distance1 = Point.distance(points.get(i), points.get(j));
                    double distance2 = Point.distance(points.get(k), points.get(j));
                    double distance3 = Point.distance(points.get(i), points.get(k));
                    if (distance1 <= radiusMax && distance2 <= radiusMax && distance3 <= radiusMax) {
                        double birthtime = Math.max(distance1, Math.max(distance2, distance3));

                        filtration.add(new Simplex(2, birthtime, Arrays.asList(i, j, k)));
                        ;


                    }
                }
            }
        }
        // 3-simplices added only to kill triangles

        //adding 2-simplicies (triangles)
//        for (int w = 0; w < n; w++){
//            for (int i = 0; i < n; i++) {
//                for (int j = i + 1; j < n; j++) {
//                    for (int k = j + 1; k < n; k++) {
//                        double distance1 = Point.distance(points.get(i), points.get(j));
//                        double distance2 = Point.distance(points.get(k), points.get(j));
//                        double distance3 = Point.distance(points.get(i), points.get(k));
//                        double distance4 = Point.distance(points.get(i), points.get(w));
//                        double distance5 = Point.distance(points.get(j), points.get(w));
//                        double distance6 = Point.distance(points.get(k), points.get(w));
//                        if (distance1 <= radiusMax && distance2 <= radiusMax && distance3 <= radiusMax &&  distance4 <= radiusMax
//                        &&  distance5 <= radiusMax && distance6 <= radiusMax) {
//                            double birthtime = Math.max(Math.max(distance1, Math.max(distance2, distance3)), Math.max(distance4, Math.max(distance5, distance6)));
//
//                            filtration.add(new Simplex(3, birthtime, Arrays.asList(w,i, j, k)));
//
//                        }
//                    }
//                }
//            }

//        }

        //sorting first by time, then dimension
        filtration.sort((s1, s2) -> {
            if (Math.abs(s1.birthTime - s2.birthTime) > 1e-9) {
                return Double.compare(s1.birthTime, s2.birthTime);
            }
            if (s1.dimension != s2.dimension) {
                return Integer.compare(s1.dimension, s2.dimension);
            }
            // Tie-breaker: Lexicographical compare of vertex lists
            for (int i = 0; i < s1.vertices.size(); i++) {
                if (!s1.vertices.get(i).equals(s2.vertices.get(i))) {
                    return Integer.compare(s1.vertices.get(i), s2.vertices.get(i));
                }
            }
            return 0;
        });


        //printing the filtration list
        System.out.println("Filtration done, took " + filtration.size() +" simplicies");
//        for (int i = 0; i < filtration.size(); i++) {
//            System.out.println("Index "+ i + " : "+ filtration.get(i));
//        }
//        System.out.println(" ");
        return filtration;
    }




}
