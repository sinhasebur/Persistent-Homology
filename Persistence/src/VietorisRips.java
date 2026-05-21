import java.util.*;

public class VietorisRips {

    private double maxSimplices=3;
    private PointUtils pointUtils;

    public VietorisRips(double maxSimplices){
        this.maxSimplices=maxSimplices;
        pointUtils= PointUtils.getInstance();
    }

    public  List<Simplex> filter(List<Point> points) {

        int n = points.size();
        List<Simplex> filtration = new ArrayList<>();

        if(maxSimplices>=0){
            for (int i = 0; i < n; i++) {
                List <Integer> name= new ArrayList<>();
                name.add(i);
                filtration.add(new Simplex(0, 0.0, i, name));
            }
        }

        if(maxSimplices>=1){
            int fakeId=0;
            Set < List <Integer> > names= new HashSet<>();
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if(anyEqual(Arrays.asList(i,j))) continue;

                    List <Integer> name = getSet(2,Arrays.asList(i,j));
                    if(!names.add(name)) {continue; }

                    System.out.println("took: "+i+j);
                    fakeId++;

                    double distanceAsBirthTime = pointUtils.getMaxDistance( Arrays.asList(points.get(i), points.get(j)));

                    filtration.add(new Simplex(1, distanceAsBirthTime, fakeId, name));
                }
            }
        }


        if(maxSimplices>=2){
            int fakeId=0;
            Set < List <Integer> > names = new HashSet<>();

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    for (int k = j + 1; k < n; k++) {

                        //System.out.print("checking: "+i+j+k+ "   ");
                        if(anyEqual(Arrays.asList(i,j,k))) { continue;}

                        List <Integer> name = getSet(3,Arrays.asList(i,j,k));
                        if(names.contains(name)) {continue; }
                        else names.add(name);

                        System.out.println("took: "+i+j+k);
                        fakeId++;

                        List<Point> consideredPoints = new ArrayList<>(); consideredPoints.add(points.get(i)); consideredPoints.add(points.get(j)); consideredPoints.add(points.get(k));
                        double birthtime = pointUtils.getMaxDistance(consideredPoints);

                        filtration.add(new Simplex(2, birthtime, fakeId, name));
                    }
                }
            }
        }
    
        if(maxSimplices>=3){
            int fakeId=0;
            Set <List <Integer> > names = new HashSet<>();

            for (int w = 0; w < n; w++){
                for (int i = 0; i < n; i++) {
                    for (int j = i + 1; j < n; j++) {
                        for (int k = j + 1; k < n; k++) {
                            if(anyEqual(Arrays.asList(i,j,k,w))) continue;

                            List <Integer> name = getSet(4,Arrays.asList(w,i,j,k));
                            if(names.contains(name)) {continue; }
                            else names.add(name);

                            System.out.println("took: "+ w+i+j+k);
                            //System.out.println(" name was "+ name[0]+name[1]+name[2]+name[3]+" .");
                            fakeId++;

                            List<Point> consideredPoints = new ArrayList<>();
                            consideredPoints.add(points.get(i)); consideredPoints.add(points.get(j)); consideredPoints.add(points.get(k)); consideredPoints.add(points.get(w));
                            double birthtime = pointUtils.getMaxDistance(consideredPoints);

                            filtration.add(new Simplex(3, birthtime, fakeId, name));
                        }
                    }
                }

            }
        }

        //sorting first by time, then dimension

        filtration.sort((s1, s2) -> {
            if (Math.abs(s1.getBirthTime() - s2.getBirthTime()) > 1e-9) {
                return Double.compare(s1.getBirthTime(), s2.getBirthTime());
            }
            if (s1.getDimension() != s2.getDimension()) {
                return Integer.compare(s1.getDimension(), s2.getDimension());
            }
            // Tie-breaker: fakeid
            if (s1.getFakeId() != s2.getFakeId()) {
                return Integer.compare(s1.getFakeId(), s2.getFakeId());
            }
            System.out.println("sorting isssues occured btw sadf");
            return 0;
        });


        //printing the filtration list
        System.out.println("Filtration done, took " + filtration.size() +" simplicies");
        for (int i = 0; i < filtration.size(); i++) {
            System.out.println("Index "+ i + " : "+ filtration.get(i));
        }
        System.out.println(" ");
        return filtration;
    }

    Boolean anyEqual(List<Integer> a){
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < a.size(); i++) {
            set.add(a.get(i));
        }
        if (set.size() != a.size()) {
            return true;
        }
        return false;
    }

    List <Integer> getSet(int n, List <Integer> list){
        List <Integer> s= new ArrayList<>();
        for (int i = 0; i < n; i++) {
            s.add(list.get(i));
        }
        Collections.sort(s);
        return s;
    }


}
