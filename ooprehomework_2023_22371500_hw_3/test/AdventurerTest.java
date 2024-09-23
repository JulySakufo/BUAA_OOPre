import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class AdventurerTest {
    
    @Test
    public void add_bottle() {
        Adventurer adventurer = new Adventurer(100,"July");
        adventurer.add_bottle(123,"bottle",100);
        Bottle bottle = adventurer.get_bottle(123);
        assertEquals(bottle.getName(),"bottle");
        assertEquals(bottle.getId(),123);
        assertEquals(bottle.getCapacity(),100);
        bottle.subCapacity();
        assertEquals(bottle.getCapacity(),0);
        ArrayList<String> value = new ArrayList<>();
        value.add("2");
        value.add("100");
        value.add("124");
        value.add("bottle2");
        value.add("100");
        Main.case2(124,value,adventurer);
        Bottle bottle2 = adventurer.get_bottle(124);
        assertEquals(bottle2.getName(),"bottle2");
        Main.case4(125,value,adventurer);
        Equipment equipment2 = adventurer.get_equipment(125);
        assertEquals(equipment2.getName(),"bottle2");
        Main.case7(126,value,adventurer);
        Food food2 = adventurer.get_food(126);
        assertEquals(food2.getName(),"bottle2");
    }
    
    @Test
    public void add_equipment() {
        Adventurer adventurer = new Adventurer(100,"July");
        adventurer.add_equipment(123,"equipment",100);
        Equipment equipment = adventurer.get_equipment(123);
        assertEquals(equipment.getName(),"equipment");
        assertEquals(equipment.getId(),123);
        assertEquals(equipment.getStar(),100);
        adventurer.upstar_equipment(123);
        assertEquals(equipment.getStar(),101);
    }
    
    @Test
    public void add_food() {
        Adventurer adventurer = new Adventurer(100,"July");
        adventurer.add_food(123,"food",100);
        Food food = adventurer.get_food(123);
        assertEquals(food.getName(),"food");
        assertEquals(food.getId(),123);
        assertEquals(food.getEnergy(),100);
        food.notUseEnd();
        assertEquals(food.getEnergy(),0);
    }
    
    @Test
    public void delete_bottle() {
        Adventurer adventurer = new Adventurer(100,"July");
        adventurer.add_bottle(123,"bottle",100);
        adventurer.carry_bottle(123);
        adventurer.delete_bottle(123);
        Bottle bottle = adventurer.get_bottle(123);
        assertNull(bottle);
    }
    
    @Test
    public void delete_equipment() {
        Adventurer adventurer = new Adventurer(100,"July");
        adventurer.add_equipment(123,"equipment",100);
        adventurer.carry_equipment(123);
        adventurer.delete_equipment(123);
        Equipment equipment = adventurer.get_equipment(123);
        assertNull(equipment);
    }
    
    @Test
    public void delete_food() {
        Adventurer adventurer = new Adventurer(100,"July");
        adventurer.add_food(123,"food",100);
        adventurer.carry_food(123);
        adventurer.delete_food(123);
        Food food = adventurer.get_food(123);
        assertNull(food);
    }
    
    @Test
    public void use_bottle() {
        Adventurer adventurer = new Adventurer(100,"July");
        adventurer.add_bottle(123,"bottle",100);
        adventurer.carry_bottle(123);
        adventurer.use_bottle("bottle");
        adventurer.use_bottle("bottle");
        Bottle bottle = adventurer.get_bottle(123);
        assertNull(bottle);
    }
    
    @Test
    public void use_food() {
        Adventurer adventurer = new Adventurer(100,"July");
        adventurer.add_food(123,"food",100);
        adventurer.carry_food(123);
        adventurer.use_food("bottle");
        adventurer.use_food("food");
        Food food = adventurer.get_food(123);
        assertNull(food);
    }
}