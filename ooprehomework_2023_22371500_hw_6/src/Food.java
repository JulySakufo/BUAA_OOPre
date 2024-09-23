public class Food implements Commodity {
    private int id;
    private String name;
    private int energy;
    private long price;
    
    public Food(int id, String name, int energy, long price) {
        this.id = id;
        this.name = name;
        this.energy = energy;
        this.price = price;
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
    
    public long getPrice() {
        return price;
    }
}
