public class Homology {
    private int dimension;
    private double birthTime;
    private double deathtime=-1;
    private double persistence;

    static int considerUpto;

    Homology(int dimension, double birthTime) {
        this.dimension = dimension;
        this.birthTime = birthTime;
    }
    public void setDeathTime(double deathTime) {this.deathtime = deathTime; }

    public String toString(){
        if(deathtime == -1){
            return "Dimension: " + dimension + ", Birth Time: " + birthTime+ "\nDeath Time: inf";
        }
        else{
            return "Dimension: " + dimension + ", Birth Time: " + birthTime+ "\nDeath Time: "+ deathtime;
        }
    }
    public double getPersistence() {
        if(deathtime == -1){
            persistence=PointUtils.getMaxDistance()-birthTime;
        }
        else{
            persistence=getDeathTime()-birthTime;
        }

        return persistence;
    }
    static int getConsiderUpto(){return Homology.considerUpto;}
    static void setConsiderUpto(int consideredUpto){ considerUpto= consideredUpto;}
    public double getDeathTime() {return deathtime;}
    public int getDimension() {return dimension;}
}
