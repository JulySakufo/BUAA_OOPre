import org.junit.Test;

import static org.junit.Assert.*;

public class AdventurerTest {
    
    @Test
    public void add_bottle() {
        Adventurer adventurer = new Adventurer(100, "July");
        String[] s = new String[10];
        s[5]="10";
        s[6] = "RegularBottle";
        adventurer.add_bottle(123, "bottle", 100, 10, s);
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
        strings[5]="10";
        strings[6]="RegularBottle";
        Service.case2(strings, adventurer);
        Bottle bottle2 = adventurer.get_bottle(124);
        assertEquals(bottle2.getName(), "bottle2");
        strings[2] = "125";
        strings[6]="RegularEquipment";
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
        String[] s = new String[10];
        s[6] = "RegularEquipment";
        adventurer.add_equipment(123, "equipment", 100, 20, s);
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
        adventurer.add_food(123, "food", 100, 10);
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
        String[] s = new String[10];
        s[6] = "RegularBottle";
        adventurer.add_bottle(123, "bottle", 100, 10, s);
        adventurer.carry_bottle(123);
        adventurer.delete_bottle(123);
        Bottle bottle = adventurer.get_bottle(123);
        assertNull(bottle);
    }
    
    @Test
    public void delete_equipment() {
        Adventurer adventurer = new Adventurer(100, "July");
        String[] s = new String[10];
        s[6] = "RegularEquipment";
        adventurer.add_equipment(123, "equipment", 100, 20, s);
        adventurer.carry_equipment(123);
        adventurer.delete_equipment(123);
        Equipment equipment = adventurer.get_equipment(123);
        assertNull(equipment);
    }
    
    @Test
    public void delete_food() {
        Adventurer adventurer = new Adventurer(100, "July");
        adventurer.add_food(123, "food", 100, 10);
        adventurer.carry_food(123);
        adventurer.delete_food(123);
        Food food = adventurer.get_food(123);
        assertNull(food);
    }
    
    @Test
    public void use_bottle() {
        Adventurer adventurer = new Adventurer(100, "July");
        String[] s = new String[10];
        s[6] = "RegularBottle";
        adventurer.add_bottle(123, "bottle", 100, 10, s);
        adventurer.carry_bottle(123);
        adventurer.use_bottle("bottle");
        adventurer.use_bottle("bottle");
        Bottle bottle = adventurer.get_bottle(123);
        assertNull(bottle);
    }
    
    @Test
    public void use_food() {
        Adventurer adventurer = new Adventurer(100, "July");
        adventurer.add_food(123, "food", 100, 10);
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
        strings[5] = "10";
        strings[6] = "RegularBottle";
        Service.service(strings);//case2
        strings[0] = "4";//type
        strings[2] = "456";//id
        strings[3] = "bottle";
        strings[4] = "50";
        strings[5] = "10";
        strings[6] = "RegularEquipment";
        Service.service(strings);//case4
        strings[0] = "7";//type
        strings[2] = "456";//id
        strings[3] = "bottle";
        strings[4] = "50";
        strings[5] = "10";
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
        strings[0] = "1";
        strings[1] = "123";
        strings[2] = "advName1";
        Service.service(strings);
        strings[1] = "124";
        strings[2] = "advName2";
        Service.service(strings);
        strings[1] = "125";
        strings[2] = "advName3";
        Service.service(strings);
        strings[0] = "4";
        strings[1] = "124";
        strings[2] = "1";
        strings[3] = "equName";
        strings[4] = "20";
        strings[5] = "10";
        strings[6] = "RegularEquipment";
        Service.service(strings);
        strings[0] = "2";
        strings[1] = "123";
        strings[2] = "20";
        strings[3] = "botName";
        strings[4] = "10";
        strings[5] = "10";
        strings[6] = "RegularBottle";
        Service.service(strings);
        strings[0] = "10";
        strings[1] = "123";
        strings[2] = "20";
        Service.service(strings);
        strings[0] = "10";
        strings[1] = "123";
        strings[2] = "20";
        Service.service(strings);
        strings[0] = "2";
        strings[1] = "123";
        strings[2] = "2";
        strings[3] = "botName";
        strings[4] = "10";
        strings[5] = "10";
        strings[6] = "RegularBottle";
        Service.service(strings);
        strings[0] = "10";
        strings[1] = "123";
        strings[2] = "2";
        Service.service(strings);
        strings[0] = "9";
        strings[1] = "124";
        strings[2] = "1";
        Service.service(strings);
        Fightlog.add_fightAdventurerMap(0, "advName1");
        Fightlog.add_fightAdventurerMap(1, "advName2");
        Fightlog.add_fightAdventurerMap(2, "advName3");
        String value = "2022/09-advName1-botName";
        Fightlog.case14(value);
        value = "2022/09-advName2@#-equName";
        Fightlog.case14(value);
        value = "2022/09-advName2@advName3-equName";
        Fightlog.case14(value);
        value = "hh";
        Fightlog.case14(value);
        value = "2022/10-advName4@#-equ";
        Fightlog.case15("2022/09");
        Fightlog.case16("123");
        Fightlog.case17("123");
    }
    @Test
    public void testChild(){
        String[] s = new String[10];
        s[0]="1";
        s[1]="123";
        s[2]="advName1";
        Service.service(s);
        s[1]="124";
        s[2]="advName2";
        Service.service(s);
        s[1]="125";
        s[2]="advName3";
        Service.service(s);
        s[0]="18";
        s[1]="123";
        s[2]="124";
        Service.service(s);
        s[2]="125";
        Service.service(s);
        s[0]="2";
        s[1]="124";
        s[2]="1";
        s[3]="botName";
        s[4]="20";
        s[5]="10";
        s[6]="RegularBottle";
        Service.service(s);
        s[0]="4";
        s[1]="125";
        s[2]="2";
        s[3]="equName";
        s[4]="12";
        s[5]="35";
        s[6]="CritEquipment";
        s[7]="123";
        Service.service(s);
        s[0]="20";
        s[1]="123";
        Service.service(s);
        s[0]="1";
        s[1]="126";
        s[2]="advName4";
        Service.service(s);
        s[0]="18";
        s[1]="124";
        s[2]="126";
        Service.service(s);
        s[0]="21";
        Service.service(s);
        s[1]="125";
        s[2]="2";
        Service.service(s);
        s[0]="19";
        s[1]="123";
        Service.service(s);
        s[0]="2";
        s[1]="124";
        s[2]="2";
        s[3]="botName2";
        s[4]="20";
        s[5]="10";
        s[6]="ReinforcedBottle";
        s[7]="0.1";
        Service.service(s);
        Adventurer adventurer = Service.get_adventurer2(124);
        Bottle bottle = adventurer.get_bottle(2);
        ReinforcedBottle bot = (ReinforcedBottle) bottle;
        double ratio = bot.getRatio();
        assertEquals(0.1,ratio,0.01);
        s[0]="10";
        s[1]="124";
        s[2]="2";
        Service.service(s);
        s[0]="12";
        s[1]="124";
        s[2]="botName2";
        Service.service(s);
        s[0]="12";
        s[1]="124";
        s[2]="botName2";
        Service.service(s);
        s[0]="2";
        s[1]="124";
        s[2]="3";
        s[3]="botName3";
        s[4]="20";
        s[5]="10";
        s[6]="RecoverBottle";
        s[7]="0.1";
        Service.service(s);
        s[0]="10";
        s[1]="124";
        s[2]="3";
        Service.service(s);
        s[0]="12";
        s[1]="124";
        s[2]="botName3";
        Service.service(s);
        bottle = adventurer.get_bottle(3);
        RecoverBottle recoverbottle = (RecoverBottle) bottle;
        ratio = recoverbottle.getRatio();
        assertEquals(0.1,ratio,0.01);
        s[0]="12";
        s[1]="124";
        s[2]="botName3";
        Service.service(s);
    }
}