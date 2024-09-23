public class Equipment implements Commodity {
    private int id;
    private String name;
    private int star;
    private long price;
    
    public Equipment(int id, String name, int star, long price) {
        this.id = id;
        this.name = name;
        this.star = star;
        this.price = price;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getStar() {
        return star;
    }
    
    public int upstar() {
        star++;
        return star;
    }
    
    public long getPrice() {
        return price;
    }
}
