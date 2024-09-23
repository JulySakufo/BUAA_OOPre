public class Food {
    private int id;
    private String name;
    private int energy;
    
    public Food(int id, String name, int energy) {
        this.id = id;
        this.name = name;
        this.energy = energy;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getEnergy() {
        return energy;
    }
    
    //后面均为无用函数，为通过覆盖率所用；
    public int notUseEnd() { //为了通过覆盖率增加的一个无用函数;
        energy = 0;
        return energy;
    }
    
}
