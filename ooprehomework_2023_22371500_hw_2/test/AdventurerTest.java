import org.junit.Test;

import static org.junit.Assert.*;

public class AdventurerTest {
    
    @Test
    public void add_bottle() {
    }
    
    @Test
    public void add_equipment() {
    }
    
    @Test
    public void delete_bottle() {
    }
    
    @Test
    public void delete_equipment() {
    }
    
    @Test
    public void upstar_equipment() {
        
        Adventurer adventurer = new Adventurer(923,"July");
        adventurer.add_equipment(100,"infinity",5);
        adventurer.upstar_equipment(100);
        assert(adventurer.get_star(100)==6);
    }
}