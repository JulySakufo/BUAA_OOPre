public class Bottle {
    private int id;
    private String name;
    private int capacity;
    
    public Bottle(int id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
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
    
    public int subCapacity() {
        capacity = 0;
        return capacity;
    }
    
}
