import java.util.*;

public class BoundaryMatrixUtils {

    private static Map <List<Integer>, Integer> nameToID= new HashMap<>();
    private static Map <Integer,List<Integer>> ID_ToName= new HashMap<>();
    private static Map <Integer,Integer> idToDimension= new HashMap<>();
    private static Map <Integer,Double> birthtimeFromID= new HashMap<>();
    private static BoundaryMatrixUtils instance;
    private static int fSize;

    private BoundaryMatrixUtils(List<Simplex> filtration) {
        fSize=filtration.size();;
        for(int i=0;i<fSize;i++){
            Simplex simplex = filtration.get(i);
            nameToID.put(simplex.getName(),i);
            ID_ToName.put(i, simplex.getName());
            idToDimension.put(i,simplex.getDimension());
            birthtimeFromID.put(i, simplex.getBirthTime());
        }
    }

    public static BoundaryMatrixUtils getInstance(List <Simplex> filtration){
        if(instance==null){
            instance=new BoundaryMatrixUtils(filtration);
        }
        return instance;
    }

    public int [][]  buildMatrix(){

        int [][] boundaryMatrix= new int[fSize][fSize];

        for(int i=0;i<fSize;i++){

            int [] boundaryIds = getBoundaryIDs(nameToID, ID_ToName, i);
            if( i< Point.getNumberOfPoints()){ continue;}
            for(int j=0; j<boundaryIds.length;j++){
                boundaryMatrix[ i ][ boundaryIds[j] ] = 1;
            }
        }
        return boundaryMatrix;
    }

     public int[] getBoundaryIDs(Map<List<Integer>, Integer> nameToID, Map<Integer, List <Integer>> idToName , int boundaryOf){
        List <Integer> name=idToName.get(boundaryOf);

        int [] boundaryIDs = new int [name.size()];

        if(name.size()==1){
            return null;
        }

        for(int skip=0;skip<name.size();skip++){
            List <Integer> boundaryName=new ArrayList<>();
            for(int i=0;i<name.size();i++){
                if(i!=skip){
                    boundaryName.add(name.get(i));
                }
            }
            boundaryIDs[skip]=nameToID.get(boundaryName);
        }

        return boundaryIDs;
    }

    public static void printMatrix(int[][] matrix){
        //System.out.println("Transposed sorry ");

        for(int i=0;i<matrix.length;i++){
            List <Integer> name = ID_ToName.get(i);

            for(int j=0;j<name.size();j++){System.out.print(name.get(j));}

            System.out.print("  has : ");
            for(int j=0;j<matrix[0].length;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();

        }

    }

    public static int getSize(){return fSize;}

    public static int getDimensionFromID(int id){
        return idToDimension.get(id);
    }
    public static double getBirthTimeFromID(int id){
        return birthtimeFromID.get(id);
    }

    public static String getNameFromID(int id) {
        List<Integer> name = ID_ToName.get(id);
        if (name == null || name.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int digit : name) {
            sb.append(digit);
        }
        return sb.toString();
    }

}
