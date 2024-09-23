public class Bottle implements Commodity {
    private int id;
    private String name;
    private int capacity;
    private long price;//bottle,equipment,food类是没有money这一概念的，它们的price起到adventurer的money;
    private boolean isempty;
    
    public Bottle(int id, String name, int capacity, long price) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.price = price;
        this.isempty = false;
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
    
    public boolean getIsEmpty() {
        return isempty;
    }
    
    public void becomeEmpty() {
        isempty = true;
    }
    
}
