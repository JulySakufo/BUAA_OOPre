import org.junit.Test;

import static org.junit.Assert.*;

public class AdventurerTest {
    
    @Test
    public void add_bottle() {
        Adventurer adventurer = new Adventurer(100, "July");
        adventurer.add_bottle(123, "bottle", 100);
        Bottle bottle = adventurer.get_bottle(123);
        assertEquals(bottle.getName(), "bottle");
        assertEquals(bottle.getId(), 123);
        assertEquals(bottle.getCapacity(), 100);
        bottle.subCapacity();
        assertEquals(bottle.getCapacity(), 0);
        String[] strings = new String[10];
        strings[2] = "124";
        strings[3] = "bottle2";
        strings[4] = "50";
        Service.case2(strings, adventurer);
        Bottle bottle2 = adventurer.get_bottle(124);
        assertEquals(bottle2.getName(), "bottle2");
        strings[2] = "125";
        Service.case4(strings, adventurer);
        Equipment equipment2 = adventurer.get_equipment(125);
        assertEquals(equipment2.getName(), "bottle2");
        strings[2] = "126";
        Service.case7(strings, adventurer);
        Food food2 = adventurer.get_food(126);
        assertEquals(food2.getName(), "bottle2");
    }
    
    @Test
    public void add_equipment() {
        Adventurer adventurer = new Adventurer(100, "July");
        adventurer.add_equipment(123, "equipment", 100);
        Equipment equipment = adventurer.get_equipment(123);
        assertEquals(equipment.getName(), "equipment");
        assertEquals(equipment.getId(), 123);
        assertEquals(equipment.getStar(), 100);
        adventurer.upstar_equipment(123);
        assertEquals(equipment.getStar(), 101);
    }
    
    @Test
    public void add_food() {
        Adventurer adventurer = new Adventurer(100, "July");
        adventurer.add_food(123, "food", 100);
        Food food = adventurer.get_food(123);
        assertEquals(food.getName(), "food");
        assertEquals(food.getId(), 123);
        assertEquals(food.getEnergy(), 100);
        food.notUseEnd();
        assertEquals(food.getEnergy(), 0);
    }
    
    @Test
    public void delete_bottle() {
        Adventurer adventurer = new Adventurer(100, "July");
        adventurer.add_bottle(123, "bottle", 100);
        adventurer.carry_bottle(123);
        adventurer.delete_bottle(123);
        Bottle bottle = adventurer.get_bottle(123);
        assertNull(bottle);
    }
    
    @Test
    public void delete_equipment() {
        Adventurer adventurer = new Adventurer(100, "July");
        adventurer.add_equipment(123, "equipment", 100);
        adventurer.carry_equipment(123);
        adventurer.delete_equipment(123);
        Equipment equipment = adventurer.get_equipment(123);
        assertNull(equipment);
    }
    
    @Test
    public void delete_food() {
        Adventurer adventurer = new Adventurer(100, "July");
        adventurer.add_food(123, "food", 100);
        adventurer.carry_food(123);
        adventurer.delete_food(123);
        Food food = adventurer.get_food(123);
        assertNull(food);
    }
    
    @Test
    public void use_bottle() {
        Adventurer adventurer = new Adventurer(100, "July");
        adventurer.add_bottle(123, "bottle", 100);
        adventurer.carry_bottle(123);
        adventurer.use_bottle("bottle");
        adventurer.use_bottle("bottle");
        Bottle bottle = adventurer.get_bottle(123);
        assertNull(bottle);
    }
    
    @Test
    public void use_food() {
        Adventurer adventurer = new Adventurer(100, "July");
        adventurer.add_food(123, "food", 100);
        adventurer.carry_food(123);
        adventurer.use_food("bottle");
        adventurer.use_food("food");
        Food food = adventurer.get_food(123);
        assertNull(food);
    }
    
    @Test
    public void getName() {
        Adventurer adventurer = new Adventurer(123, "July");
        assertEquals(123, adventurer.getId());
        assertEquals("July", adventurer.getName());
        assertEquals(1, adventurer.getLevel());
    }
    
    @Test
    public void testService() {
        String[] strings = new String[10];
        strings[0] = "1";//type
        strings[1] = "123";//id
        strings[2] = "July";//name
        Service.service(strings);//case1
        strings[0] = "2";
        strings[2] = "456";
        strings[3] = "bottle";
        strings[4] = "50";
        Service.service(strings);//case2
        strings[0] = "4";//type
        strings[2] = "456";//id
        strings[3] = "bottle";
        strings[4] = "50";
        Service.service(strings);//case4
        strings[0] = "7";//type
        strings[2] = "456";//id
        strings[3] = "bottle";
        strings[4] = "50";
        Service.service(strings);
        strings[0] = "6";
        Service.service(strings);
        strings[0] = "9";
        Service.service(strings);
        strings[0] = "10";
        Service.service(strings);
        strings[0] = "11";
        Service.service(strings);
        strings[0] = "12";
        Service.service(strings);
        strings[0] = "13";
        Service.service(strings);
        strings[0] = "3";
        strings[2] = "456";
        strings[3] = "bottle";
        strings[4] = "50";
        Service.service(strings);//case4
        strings[0] = "5";
        Service.service(strings);//case5
        strings[0] = "8";
        Service.service(strings);
    }
    
    @Test
    public void testMain() {
        String[] strings = new String[10];
        strings[0]="1";
        strings[1]="123";
        strings[2]="advName1";
        Service.service(strings);
        strings[1]="124";
        strings[2]="advName2";
        Service.service(strings);
        strings[1]="125";
        strings[2]="advName3";
        Service.service(strings);
        strings[0]="4";
        strings[1]="124";
        strings[2]="1";
        strings[3]="equName";
        strings[4]="20";
        Service.service(strings);
        strings[0]="2";
        strings[1]="123";
        strings[2]="20";
        strings[3]="botName";
        strings[4]="10";
        Service.service(strings);
        strings[0]="10";
        strings[1]="123";
        strings[2]="20";
        Service.service(strings);
        strings[0]="10";
        strings[1]="123";
        strings[2]="20";
        Service.service(strings);
        strings[0]="2";
        strings[1]="123";
        strings[2]="2";
        strings[3]="botName";
        strings[4]="10";
        Service.service(strings);
        strings[0]="10";
        strings[1]="123";
        strings[2]="2";
        Service.service(strings);
        strings[0]="9";
        strings[1]="124";
        strings[2]="1";
        Service.service(strings);
        Main.add_fightAdventurerMap(0,"advName1");
        Main.add_fightAdventurerMap(1,"advName2");
        Main.add_fightAdventurerMap(2,"advName3");
        String value = "2022/09-advName1-botName";
        Main.case14(value);
        value = "2022/09-advName2@#-equName";
        Main.case14(value);
        value = "2022/09-advName2@advName3-equName";
        Main.case14(value);
        value = "hh";
        Main.case14(value);
        value = "2022/10-advName4@#-equ";
        Main.case15("2022/09");
        Main.case16("123");
        Main.case17("123");
    }
}