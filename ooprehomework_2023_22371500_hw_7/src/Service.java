import java.util.ArrayList;
import java.util.HashMap;

public class Service {
    private static HashMap<Integer, Adventurer> adventurerMap = new HashMap<>();//创建一个冒险者名单
    private static HashMap<String, Adventurer> adventurerNameMap = new HashMap<>(); //根据名字创建名单;
    
    public static void service(String[] strings) {
        String type = strings[0];
        int advId = Integer.parseInt(strings[1]);
        if (type.equals("1")) {
            String name = strings[2];
            Adventurer adventurer = new Adventurer(advId, name);
            adventurerMap.put(advId, adventurer);
            adventurerNameMap.put(name, adventurer);//因为战斗日志只给出了冒险者的名字，为了找到冒险者必须用名字去索引;
        } else {
            Adventurer adventurer = adventurerMap.get(advId);
            if (type.equals("2")) {
                case2(strings, adventurer);
            } else if (type.equals("3")) {
                case3(strings, adventurer);
            } else if (type.equals("4")) {
                case4(strings, adventurer);
            } else if (type.equals("5")) {
                case5(strings, adventurer);
            } else if (type.equals("6")) {
                case6(strings, adventurer);
            } else if (type.equals("7")) {
                case7(strings, adventurer);
            } else if (type.equals("8")) {
                case8(strings, adventurer);
            } else if (type.equals("9")) {
                case9(strings, adventurer);
            } else if (type.equals("10")) {
                case10(strings, adventurer);
            } else if (type.equals("11")) {
                case11(strings, adventurer);
            } else if (type.equals("12")) {
                case12(strings, adventurer);
            } else if (type.equals("13")) {
                case13(strings, adventurer);
            } else if (type.equals("18")) {
                case18(strings, adventurer);
            } else if (type.equals("19")) {
                case19(strings, adventurer);
            } else if (type.equals("20")) {
                case20(strings, adventurer);
            } else if (type.equals("21")) {
                case21(strings, adventurer);
            } else if (type.equals("22")) {
                case22(strings, adventurer);
            } else {
                case23(strings, adventurer);
            }
        }
    }
    
    public static Adventurer get_adventurer(String strings) {
        return adventurerNameMap.get(strings);
    }
    
    public static Adventurer get_adventurer2(int id) {
        return adventurerMap.get(id);
    }
    
    public static void case2(String[] strings, Adventurer adventurer) {
        int id = Integer.parseInt(strings[2]);
        String name = strings[3];
        int capacity = Integer.parseInt(strings[4]);
        long price = Long.parseLong(strings[5]);
        adventurer.add_bottle(id, name, capacity, price, strings);
    }
    
    public static void case3(String[] strings, Adventurer adventurer) {
        int id = Integer.parseInt(strings[2]);
        String botName = adventurer.get_bottle(id).getName();
        adventurer.delete_bottle(id);//删除操作，打印操作分别进行;
        int size = adventurer.getBottleMapSize();//先记住名字再删除，否则找不到它;
        adventurer.delete_print(size, botName);//修改print逻辑，以便delete函数复用;
    }
    
    public static void case4(String[] strings, Adventurer adventurer) {
        int id = Integer.parseInt(strings[2]);
        String name = strings[3];
        int star = Integer.parseInt(strings[4]);
        long price = Long.parseLong(strings[5]);
        adventurer.add_equipment(id, name, star, price, strings);
    }
    
    public static void case5(String[] strings, Adventurer adventurer) {
        int id = Integer.parseInt(strings[2]);
        String equName = adventurer.get_equipment(id).getName();
        adventurer.delete_equipment(id);
        int size = adventurer.getEquipmentMapSize();
        adventurer.delete_print(size, equName);
    }
    
    public static void case6(String[] strings, Adventurer adventurer) {
        int id = Integer.parseInt(strings[2]);
        adventurer.upstar_equipment(id);
    }
    
    public static void case7(String[] strings, Adventurer adventurer) {
        int id = Integer.parseInt(strings[2]);
        String name = strings[3];
        int star = Integer.parseInt(strings[4]);
        long price = Long.parseLong(strings[5]);
        adventurer.add_food(id, name, star, price);
    }
    
    public static void case8(String[] strings, Adventurer adventurer) {
        int id = Integer.parseInt(strings[2]);
        String foodName = adventurer.get_food(id).getName();
        adventurer.delete_food(id);
        int size = adventurer.getFoodMapSize();
        adventurer.delete_print(size, foodName);
    }
    
    public static void case9(String[] strings, Adventurer adventurer) {
        int id = Integer.parseInt(strings[2]);
        adventurer.carry_equipment(id);
    }
    
    public static void case10(String[] strings, Adventurer adventurer) {
        int id = Integer.parseInt(strings[2]);
        adventurer.carry_bottle(id);
    }
    
    public static void case11(String[] strings, Adventurer adventurer) {
        int id = Integer.parseInt(strings[2]);
        adventurer.carry_food(id);
    }
    
    public static void case12(String[] strings, Adventurer adventurer) {
        String name = strings[2];
        adventurer.use_bottle(name);
    }
    
    public static void case13(String[] strings, Adventurer adventurer) {
        String name = strings[2];
        adventurer.use_food(name);
    }
    
    public static void case18(String[] strings, Adventurer adventurer) {
        int id1 = Integer.parseInt(strings[1]);
        int id2 = Integer.parseInt(strings[2]);
        Adventurer adventurer1 = adventurerMap.get(id1);
        Adventurer adventurer2 = adventurerMap.get(id2);
        adventurer1.add_employAdventurerSet(adventurer2);//因为不会重复出现，直接加入hashset即可;
    }
    
    public static void case19(String[] strings, Adventurer adventurer) {
        int amount = adventurer.search_amount_of_commodity();
        long value = adventurer.calculate_employer_price(adventurer);
        value = value - adventurer.getMoney();//计算该冒险者的价值的时候，不用算上自己的money
        System.out.println(amount + " " + value);
    }
    
    public static void case20(String[] strings, Adventurer adventurer) {
        long value = adventurer.owner_max_price();
        System.out.println(value);
    }
    
    public static void case21(String[] strings, Adventurer adventurer) {
        int comId = Integer.parseInt(strings[2]);
        adventurer.search_obj_instanceof(comId);
    }
    
    public static void case22(String[] strings, Adventurer adventurer) {
        HashMap<String, ArrayList<Integer>> bottleBag = adventurer.getBottleBag();
        ArrayList<Integer> temp = new ArrayList<>();
        long getMoney = 0;
        for (ArrayList<Integer> arraylist : bottleBag.values()) {
            temp.addAll(arraylist);
        }
        for (Integer integer : temp) {
            getMoney = getMoney + adventurer.get_bottle(integer).getPrice();
            adventurer.delete_bottle(integer);
        } //进行背包中bottle的删除操作，全部卖给商店，边卖边增加卖出的钱;
        temp.clear();
        HashMap<String, ArrayList<Integer>> foodBag = adventurer.getFoodBag();
        for (ArrayList<Integer> arraylist : foodBag.values()) {
            temp.addAll(arraylist);
        }
        for (Integer integer : temp) {
            getMoney = getMoney + adventurer.get_food(integer).getPrice();
            adventurer.delete_food(integer);
        } //进行背包中food的删除操作，全部卖给商店，边卖边增加卖出的钱;
        ArrayList<Equipment> equtemp = new ArrayList<>();
        HashMap<String, Equipment> equipmentBag = adventurer.getEquipmentBag();
        equtemp.addAll(equipmentBag.values());
        for (Equipment equipment : equtemp) {
            getMoney = getMoney + adventurer.get_equipment(equipment.getId()).getPrice();
            adventurer.delete_equipment(equipment.getId());
        } //背包以及拥有关系已经在adventurer的delete函数自动进行，此处只需要记录卖了多少钱即可;
        String advName = adventurer.getName();
        System.out.println(advName + " emptied the backpack " + getMoney);
    }
    
    public static void case23(String[] strings, Adventurer adventurer) {
        int comId = Integer.parseInt(strings[2]);
        String comName = strings[3];
        String type = strings[4];
        String[] s = new String[10];
        s[6] = strings[4];
        if (strings.length > 5) { //因为一定有type，但不一定有额外的others;
            s[7] = strings[5];//交给add_bottle自行处理添加哪个类型的bottle,在service工厂里面我只需要知道需要生产基本类型即可;
        }
        if (type.equals("RegularBottle")) {
            produceBottle(adventurer, comId, comName, s);
        } else if (type.equals("ReinforcedBottle")) {
            produceBottle(adventurer, comId, comName, s);
        } else if (type.equals("RecoverBottle")) {
            produceBottle(adventurer, comId, comName, s);
        } else if (type.equals("RegularEquipment")) {
            produceEquipment(adventurer, comId, comName, s);
        } else if (type.equals("CritEquipment")) {
            produceEquipment(adventurer, comId, comName, s);
        } else if (type.equals("EpicEquipment")) {
            produceEquipment(adventurer, comId, comName, s);
        } else {
            produceFood(adventurer, comId, comName);
        }
    }
    
    public static void produceBottle(Adventurer adv, int id, String name, String[] s) {
        int capacity = (int) Shop.createBottleCapacity();
        long price = Shop.createBottlePrice();
        if (adv.getMoney() >= price) {
            adv.add_bottle(id, name, capacity, price, s);
            adv.subMoney(price);//花钱买东西了;
            System.out.println("successfully buy " + name + " for " + price);
        } else {
            System.out.println("failed to buy " + name + " for " + price);
        }
    }
    
    public static void produceEquipment(Adventurer adv, int id, String name, String[] s) {
        int star = (int) Shop.createEquipmentStar();
        long price = Shop.createEquipmentPrice();
        if (adv.getMoney() >= price) {
            adv.add_equipment(id, name, star, price, s);
            adv.subMoney(price);//花钱买东西了;
            System.out.println("successfully buy " + name + " for " + price);
        } else {
            System.out.println("failed to buy " + name + " for " + price);
        }
    }
    
    public static void produceFood(Adventurer adv, int id, String name) {
        int energy = (int) Shop.createFoodEnergy();
        long price = Shop.createFoodPrice();
        if (adv.getMoney() >= price) {
            adv.add_food(id, name, energy, price);
            adv.subMoney(price);//花钱买东西了;
            System.out.println("successfully buy " + name + " for " + price);
        } else {
            System.out.println("failed to buy " + name + " for " + price);
        }
    }
}
