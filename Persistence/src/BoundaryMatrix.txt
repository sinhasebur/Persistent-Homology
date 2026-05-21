import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoundaryMatrix {
    public static int[][]  buildMatrix(List<Simplex> filtration){
        int n = filtration.size();
        int [][] B= new int[n][n];
        Map<List<Integer>, Integer> lookup= new HashMap<>();
        for(int i=0;i<n;i++){
            lookup.put(filtration.get(i).vertices,i);
        }

        for(int i=0;i<n;i++){
            Simplex s =filtration.get(i);
            if(s.dimension==1){
                B[lookup.get(Arrays.asList(s.vertices.get(0)))][i]=1;
                B[lookup.get(Arrays.asList(s.vertices.get(1)))][i]=1;
            }
            else if(s.dimension==2){
                B[lookup.get(Arrays.asList(s.vertices.get(0), s.vertices.get(1)))][i] = 1;
                B[lookup.get(Arrays.asList(s.vertices.get(1), s.vertices.get(2)))][i] = 1;
                B[lookup.get(Arrays.asList(s.vertices.get(0), s.vertices.get(2)))][i] = 1;
            }
            else{
                //throw new RuntimeException("Invalid dimension");
            }
        }
        return B;
    }
}
