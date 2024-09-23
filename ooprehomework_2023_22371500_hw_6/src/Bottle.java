public class Bottle implements Commodity {
    private int id;
    private String name;
    private int capacity;
    private long price;
    
    public Bottle(int id, String name, int capacity, long price) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.price = price;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public long getPrice() {
        return price;
    }
    
    public int subCapacity() {
        capacity = 0;
        return capacity;
    }
    
}
