import javax.swing.*;
import java.util.Arrays;
import java.util.List;

class Persistence {

    static int [] holes=new int [10];
    static double [] holesMaxPersistence=new double [10];

    public static void reduce(int[][] B, List<Simplex> filtration) {
        int n = B.length;
        int[] pivotToColumn=new int[n];
        Arrays.fill(pivotToColumn,-1);
        Arrays.fill(holes,0);
        Arrays.fill(holesMaxPersistence,0);

        for (int i = 0; i < n; i++) {
            while (true) {
                int low = getLow(B, i);
                if (low == -1)
                    break; //this is a birth, recorded by -1
                int columnWithSameLow = pivotToColumn[low];
                if (columnWithSameLow != -1) {
                    xorColumn(B, columnWithSameLow, i);
                } else {
                    // index 'low' is born and index j is kills
                    pivotToColumn[low] = i;
                    printPair(filtration, low, i);
                    break;
                }
            }
        }

        //looping through to find infinite persistence

        //recording deaths
        int [] killed = new int [n];
        Arrays.fill(killed,0);

        for (int i = 0; i < n; i++){
            int low = getLow(B, i);
            if(low != -1){
                killed[low]=1;
                //System.out.println(killed[i]+" was killed lmao");
            }
        }

        for (int i = 0; i < n; i++){
            int low = getLow(B, i);
            int died= killed[i];

            if( (low == -1) && died==0){
                printInfinite(filtration,i);
            }
        }
        for(int i=0; i<10; i++){
            if(holesMaxPersistence[i] != 0){
                //System.out.println(holesMaxPersistence[i] + " vs " + Point.getMaxDistance());
                if(holesMaxPersistence[i] - Point.getMaxDistance() < 1e-8){
                    //System.out.println("H" + i + " hole present");
                    holes[i]++;
                }
                else
                    System.out.println("Hole of " + i + " existed for " + holesMaxPersistence[i]);
            }
        }
        //print betti numbers
        for(int i=0;i<10;i++){
            System.out.println("H"+i+" "+holes[i]);
        }

    }

    private static int getLow(int[][] B, int col) {
        for(int row=B.length-1;row>=0;row--) {
            if(B[row][col] == 1)
                return row;
        }
        return -1;
    }

    private static void xorColumn(int[][] B, int source, int destination) {
        for(int row=B.length-1;row>=0;row--) {
            B[row][destination] ^= B[row][source];
        }
    }


    private static void printPair(List<Simplex> filtration, int birthIndex, int deathIndex) {
        Simplex birth = filtration.get(birthIndex);
        Simplex death = filtration.get(deathIndex);

        // The dimension of the feature is always the dimension of the simplex that CREATED it.
        int dimension = birth.dimension;

        double birthTime = birth.birthTime;
        double deathTime = death.birthTime;
        double persistence = deathTime - birthTime;
        if(deathTime - Point.getMaxDistance() > 1e-6){
             holesMaxPersistence[dimension] = Point.getMaxDistance();
        }
//        if (persistence > 1e-9) {
//            System.out.printf("Dimension  is %d, Age is [%.4f, %.4f), Persistence: %.4f\n",
//                    dimension, birthTime, deathTime, persistence);
//
        if(persistence> holesMaxPersistence[dimension]) {
            holesMaxPersistence[dimension] = persistence;
        }
    }
    private static void printInfinite(List<Simplex> filtration, int birthIndex) {
        Simplex birth = filtration.get(birthIndex);

        // The dimension of the feature is always the dimension of the simplex that CREATED it.
       int dimension = birth.dimension;

        if( dimension != 0) {
            //System.out.println(dimension +"-D simplex persists");
        }
//       if(  dimension != 0){
//           //System.out.println("H"+ (dimension) +" hole present");
//           holes[dimension]++;
//       }
      holes[dimension]++;
//        double birthTime = birth.birthTime;
        //double persistence = doubleMax;

        //if (persistence > 1e-9) {
//        System.out.printf("Dimension  is %d, Age is [%.4f, inf), Persistence: inf\n",
//                dimension, birthTime );
//        //}

    }
}
