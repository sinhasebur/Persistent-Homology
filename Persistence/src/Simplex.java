import java.util.Collections;
import java.util.List;

public class Simplex {
    private int dimension;
    private double birthTime;
    //private List <Integer> vertices;
    private final int fakeId;
    private final List <Integer> name;
    private int ID;

    Simplex(int dimension, double birthTime, int fakeId, List <Integer> name) {
        this.dimension = dimension;
        this.birthTime = birthTime;
        this.fakeId = fakeId;
        this.name = name;
    }

    public int getFakeId() { return fakeId; }
    public int getDimension() { return dimension; }
    public double getBirthTime() { return birthTime; }
    public void setID(int ID) { this.ID = ID; }
    public int getID() { return ID; }
    public List <Integer> getName() { return name; }

    @Override
    public String toString() {
        return "Simplex{" + "dimension=" + dimension + ", birthTime=" + birthTime + '}';
    }
}
