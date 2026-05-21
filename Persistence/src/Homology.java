public class Homology {
    private int dimension;
    private double birthTime;
    private double deathtime=-1;

    Homology(int dimension, double birthTime) {
        this.dimension = dimension;
        this.birthTime = birthTime;
    }
    public void setDeathTime(double deathTime) {this.deathtime = deathTime;}

    public String toString(){
        if(deathtime == -1){
            return "Dimension: " + dimension + ", Birth Time: " + birthTime+ "\nDeath Time: inf";
        }
        else{
            return "Dimension: " + dimension + ", Birth Time: " + birthTime+ "\nDeath Time: "+ deathtime;
        }
    }
}
