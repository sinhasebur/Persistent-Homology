import java.util.*;

import static java.lang.Math.max;

class Persistence {
    private Map <Integer,Integer> idToH = new HashMap<>();
    private Map <Integer,Integer> lowToLowCreator = new HashMap<>();

    public List <Homology> reduce(int [][] boundaryMatrix){

        int n= BoundaryMatrixUtils.getSize();
        List <Homology> persistence = new ArrayList <>();

        int [] low = new int [n];

        for(int i=0;i<n;i++){
            low[i]=setLow(boundaryMatrix, i);
        }
        Set<Integer> set = new HashSet<Integer>();

        for(int i=0;i<n;i++){
            System.out.println("For "+ BoundaryMatrixUtils.getNameFromID(i) );

            while(true){
                System.out.print("         ");
                if(low[i]==-1){
                    Homology h = new Homology( BoundaryMatrixUtils.getDimensionFromID(i), BoundaryMatrixUtils.getBirthTimeFromID(i) ) ;
                    persistence.add(h);
                    idToH.put(i, persistence.size()-1 );
                    System.out.println("Line started for H"+  BoundaryMatrixUtils.getDimensionFromID(i));
                    break;
                }
                else if( set.contains(low[i]) ){
//                    System.out.println("here");
//                    xorColumns( boundaryMatrix,i,lowToLowCreator.get(low[i]) );
//                    low[i]=setLow(boundaryMatrix, i);
//                    System.out.println("low of" + i + "is not unique "+low[i]+", xoring with "+ BoundaryMatrixUtils.getNameFromID(lowToLowCreator.get(low[i]) ));
//                    System.out.println("Got new low"+ low[i]);
                    int sameLow = low[i];
                    int previousholder = lowToLowCreator.get(sameLow);

                    xorColumns(boundaryMatrix, i, previousholder);
                    low[i] = setLow(boundaryMatrix, i);

                    System.out.println("low of " + i + " was duplicate at " + sameLow + ", xoring with " + BoundaryMatrixUtils.getNameFromID(previousholder));

                    System.out.println("Got new low " + low[i]);
                }
                else{
                    set.add(low[i]);
                    lowToLowCreator.put(low[i],i);
                    //System.out.println("wanna kill "+ )
                    persistence.get( idToH.get( low[i] ) ).setDeathTime( BoundaryMatrixUtils.getBirthTimeFromID(i) ) ;
                    System.out.println("Got new low"+ low[i]);
                    System.out.println("Line killed for "+ BoundaryMatrixUtils.getDimensionFromID(low [i]));
                    break;
                }
            }
            System.out.println("\n");
            BoundaryMatrixUtils.printMatrix(boundaryMatrix);
        }

        return persistence;
    }

    private int setLow(int [][] boundaryMatrix, int index){
        int n= BoundaryMatrixUtils.getSize();
        int low=-1;
        for (int j=0;j<n;j++){
            if(boundaryMatrix[index][j]==1){
                low=max(low, j);
            }
        }
        return low;
    }

    private void xorColumns(int [][] boundaryMatrix,int index,Integer previousLowHolder){
        int n= BoundaryMatrixUtils.getSize();

        for (int j=0;j<n;j++){
                boundaryMatrix[index][j]= boundaryMatrix[index][j]^ boundaryMatrix[previousLowHolder][j];
        }
    }

    public void getInfo(List <Homology> h){

        for (Homology h1 : h){
            System.out.println(h1.toString());
        }
    }

}
