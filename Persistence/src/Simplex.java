import java.util.Collections;
import java.util.List;

public class Simplex {
    int dimension;
    double birthTime;
    List <Integer> vertices;

    Simplex(int dimension, double birthTime, List <Integer> vertices) {
        this.dimension = dimension;
        this.birthTime = birthTime;
        Collections.sort(vertices);
        this.vertices = vertices;
    }

    @Override
    public String toString() {
        return "Simplex{" + "dimension=" + dimension + ", birthTime=" + birthTime + '}';
    }
}
