import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

public class Adventurer implements Commodity {
    private int id;
    private String name;
    private int hp;
    private int level;
    private long price; //保证该price只是药水瓶装备食物的价值，仆人的价值没算在里面;
    private HashMap<Integer, Bottle> bottleMap;//创建bot_id与bottle对应的关系；
    private HashMap<Integer, Equipment> equipmentMap;//创建equ_id与equipment对应的关系；
    private HashMap<Integer, Food> foodMap;//创建food_id与food对应的关系;
    private HashMap<String, Equipment> equipmentBag;//背包中的装备;
    private HashMap<String, ArrayList<Integer>> foodBag;//不同id同名食物放在一个arraylist里，arraylist实现id排序;
    private HashMap<String, ArrayList<Integer>> bottleBag;
    private HashSet<Adventurer> employAdventurerSet;//表示该冒险者所拥有的从属，不能重复雇佣;
    
    public Adventurer(int id, String name) {
        this.id = id;
        this.name = name;
        this.hp = 500;
        this.level = 1;
        this.price = 0;
        this.bottleMap = new HashMap<>();//无参数容器的初始化
        this.equipmentMap = new HashMap<>();
        this.foodMap = new HashMap<>();
        this.equipmentBag = new HashMap<>();
        this.foodBag = new HashMap<>();
        this.bottleBag = new HashMap<>();
        this.employAdventurerSet = new HashSet<>();
    } //加入一个冒险者
    
    public void add_bottle(int botId, String name, int capacity, long value, String[] s) {
        add_price(value);//新增一个bottle,更新adventurer的price;
        if (s[6].equals("RegularBottle")) { //常规瓶子;
            RegularBottle bottle = new RegularBottle(botId, name, capacity, value);
            bottleMap.put(botId, bottle);//实现id和药水瓶的映射关系;
        } else if (s[6].equals("ReinforcedBottle")) { //百分比强化药水
            double ratio = Double.parseDouble(s[7]);
            ReinforcedBottle bottle = new ReinforcedBottle(botId, name, capacity, value, ratio);
            bottleMap.put(botId, bottle);//实现id和药水瓶的映射关系;
        } else { //百分比恢复药水;
            double ratio = Double.parseDouble(s[7]);
            RecoverBottle bottle = new RecoverBottle(botId, name, capacity, value, ratio);
            bottleMap.put(botId, bottle);//实现id和药水瓶的映射关系;
        }
    } //增加一个药水瓶,用bottle_array.size()即可得到该冒险者的药水瓶个数;
    
    public void add_equipment(int equId, String name, int star, long value, String[] s) {
        add_price(value);//新增一个equipment，更新price;
        if (s[6].equals("RegularEquipment")) { //常规装备;
            RegularEquipment equipment = new RegularEquipment(equId, name, star, value);
            equipmentMap.put(equId, equipment);
        } else if (s[6].equals("CritEquipment")) { //暴击装备;
            int c = Integer.parseInt(s[7]);
            CritEquipment equipment = new CritEquipment(equId, name, star, value, c);
            equipmentMap.put(equId, equipment);
        } else { //史诗装备;
            double ratio = Double.parseDouble(s[7]);
            EpicEquipment equipment = new EpicEquipment(equId, name, star, value, ratio);
            equipmentMap.put(equId, equipment);
        }
    }
    
    public void add_food(int foodId, String name, int energy, long value) {
        Food food = new Food(foodId, name, energy, value);
        add_price(value);  //新增一个食物，更新price;
        foodMap.put(foodId, food);
    }
    
    public Bottle get_bottle(int botId) {
        return bottleMap.get(botId);
    } //根据bottle的id找到这个药水瓶具体是哪个，便于操作;为了优雅，本次作业修改了map中的参数;
    
    public Equipment get_equipment(int equId) {
        return equipmentMap.get(equId);
    } //根据equipment的id找到这件装备具体是哪个;
    
    public Food get_food(int foodId) {
        return foodMap.get(foodId);
    } //根据id找到对应的food；
    
    public void delete_bottle(int botId) {
        Bottle bottle = get_bottle(botId);//根据id的唯一性得到这个具体的药水瓶，然后进行后面的操作；
        String botName = bottle.getName();
        long value = bottle.getPrice();
        sub_price(value);//price更新;
        bottleMap.remove(botId, bottle);//断绝拥有关系，背包中可能之前有携带，也要从背包中删除该物品；
        ArrayList<Integer> arraylist = bottleBag.get(botName);//找到药水瓶对应的背包数组;
        if (arraylist != null) { //同名药水瓶已被装入背包,检查该id的药水瓶是否放入背包,若没装入背包，则无需进行任何操作;
            for (int i = 0; i < arraylist.size(); i++) { //从背包中执行删除操作;
                if (arraylist.get(i) == botId) {
                    arraylist.remove(i);
                    break;
                }
            }
        }
        int botSize = bottleMap.size();//得到现存药水数；
        System.out.println(botSize + " " + botName);//打印删除后药水瓶的总数和删除的药水瓶的名字；
    }
    
    public void delete_equipment(int equId) {
        Equipment equipment = get_equipment(equId);
        String equName = equipment.getName();
        long value = equipment.getPrice();
        sub_price(value);
        equipmentMap.remove(equId, equipment);
        Equipment equipment1 = equipmentBag.get(equName);
        if (equipment1 != null) { //背包中已有同名装备;
            if (equipment.getId() == equipment1.getId()) { //背包里的就是仓库里的那一件;
                equipmentBag.remove(equName); //从背包中删去这件装备;
            }
        }
        int equSize = equipmentMap.size();
        System.out.println(equSize + " " + equName);
    }
    
    public void delete_food(int foodId) {
        Food food = get_food(foodId);
        String foodName = food.getName();
        long value = food.getPrice();
        sub_price(value);
        foodMap.remove(foodId, food);
        ArrayList<Integer> arraylist = foodBag.get(foodName);
        if (arraylist != null) {
            for (int i = 0; i < arraylist.size(); i++) {
                if (arraylist.get(i) == foodId) {
                    arraylist.remove(i);
                    break;
                }
            }
        }
        int foodSize = foodMap.size();
        System.out.println(foodSize + " " + foodName);
    }
    
    public void upstar_equipment(int equId) {
        Equipment equipment = get_equipment(equId);
        String equName = equipment.getName();
        int star = equipment.upstar();
        System.out.println(equName + " " + star);
    }
    
    public void carry_equipment(int equId) {
        Equipment equipment = get_equipment(equId);
        String equName = equipment.getName();
        if (equipmentBag.containsKey(equName)) { //假如背包中已有一个名字为equName的装备，替换id
            equipmentBag.replace(equName, equipment);//实现装备的替换，从而完成装备id的替换;冒险者只能携带一件同名装备;
        } else {
            equipmentBag.put(equName, equipment);//没有equName的装备直接放进背包即可;
        }
    }
    
    public void carry_food(int foodId) {
        Food food = get_food(foodId);
        String foodName = food.getName();
        if (foodBag.containsKey(foodName)) {
            ArrayList<Integer> arraylist = foodBag.get(foodName);//得到装有同名食物的数组;
            if (!arraylist.contains(foodId)) { //同样的道理，如果数组中有了相同id的就不做任何操作;
                arraylist.add(foodId);//将这个食物装入该同名食物的数组中;
                Collections.sort(arraylist);//实现id的升序排序，保证id最小的在最前面;
            }
        } else {
            ArrayList<Integer> arraylist = new ArrayList<>();//分配空间给这个名字的食物;
            arraylist.add(foodId);//将这个食物装入数组中，等待下一个同名食物的加入;
            foodBag.put(foodName, arraylist);
        }
    }
    
    public void carry_bottle(int botId) {
        Bottle bottle = get_bottle(botId);
        String botName = bottle.getName();
        int bottleMax = level / 5 + 1;//找到bottlemax，以便随时对背包中同名的药水瓶个数进行修改;
        if (bottleBag.containsKey(botName)) {
            ArrayList<Integer> arraylist = bottleBag.get(botName);
            if (!arraylist.contains(botId)) { //如果该同名药水瓶数组中没有这个id的bottle才放进去，有了同一个id还放就会出错；
                int size = arraylist.size();
                if (size != bottleMax) {
                    arraylist.add(botId);
                    Collections.sort(arraylist);
                } //等级只会增加不会减少，因此bottlemax也只会增加而不会减少;当size<bottleMax增加药水瓶，否则不做任何变化;
            }
        } else {
            ArrayList<Integer> arraylist = new ArrayList<>();
            arraylist.add(botId);//size会自增，不需要人为操作;
            bottleBag.put(botName, arraylist);//构建新数组;
        }
    }
    
    public void use_bottle(String botName) { //使用一个药水瓶；
        ArrayList<Integer> arraylist = bottleBag.get(botName);//找到这个名字对应的药水瓶组;
        if (arraylist == null) {
            System.out.println("fail to use " + botName);//从未将其放入背包中;
        } else { //有过该名字的药水瓶;
            int size = arraylist.size();
            if (size == 0) {  //假如背包里面未携带该名字的药水瓶;
                System.out.println("fail to use " + botName);//使用失败;
            } else {
                int botId = arraylist.get(0);//id最小的一直被放在第一个，因此我们每次只需要使用第一个然后删除它即可;
                Bottle bottle = get_bottle(botId);//根据id在仓库中找到对应的药水瓶，因为背包中没有这对映射关系;
                int capacity = bottle.getCapacity();//得到药水瓶的容积;
                if (bottle instanceof RegularBottle) { //常规瓶子，得到的结果与以前一样;
                    RegularBottle regularbottle = (RegularBottle) bottle;
                    hp = hp + capacity;//体力增加;
                    if (capacity != 0) { //先喝，喝完保留空瓶，下次再喝的时候喝了空瓶然后扔掉空瓶;
                        capacity = regularbottle.subCapacity();
                    } else { //capacity为0的扔掉瓶子;
                        long value = regularbottle.getPrice();
                        sub_price(value);//不再拥有，更新price;
                        arraylist.remove(0);//丢弃用过的id最小的药水瓶;
                        bottleMap.remove(botId);//背包中丢弃，也即他不再拥有这个药水瓶;
                    }
                    System.out.println(botId + " " + hp);
                } else if (bottle instanceof ReinforcedBottle) { //是强化的药水瓶;
                    ReinforcedBottle reinforcedbottle = (ReinforcedBottle) bottle;
                    double ratio = reinforcedbottle.getRatio();
                    hp = hp + (int) ((1 + ratio) * capacity); //按照百分比强化药水的计算公式计算;
                    if (capacity != 0) { //先喝，喝完保留空瓶，下次再喝的时候喝了空瓶然后扔掉空瓶;
                        capacity = reinforcedbottle.subCapacity();
                    } else { //capacity为0的扔掉瓶子;
                        long value = reinforcedbottle.getPrice();
                        sub_price(value);
                        arraylist.remove(0);//丢弃用过的id最小的药水瓶;
                        bottleMap.remove(botId);//背包中丢弃，也即他不再拥有这个药水瓶;
                    }
                    System.out.println(botId + " " + hp);
                } else {
                    RecoverBottle recoverbottle = (RecoverBottle) bottle;
                    double ratio = recoverbottle.getRatio();
                    if (capacity != 0) { //先喝，喝完保留空瓶，下次再喝的时候喝了空瓶然后扔掉空瓶;
                        capacity = recoverbottle.subCapacity();
                        hp = hp + (int) (ratio * hp); //按照百分比恢复药水的计算公式计算;
                    } else { //capacity为0的扔掉瓶子;
                        long value = recoverbottle.getPrice();
                        sub_price(value);
                        arraylist.remove(0);//丢弃用过的id最小的药水瓶;
                        bottleMap.remove(botId);//背包中丢弃，也即他不再拥有这个药水瓶;
                    }
                    System.out.println(botId + " " + hp);
                }
            }
        }
    }
    
    public void use_food(String foodName) {
        ArrayList<Integer> arraylist = foodBag.get(foodName);
        if (arraylist == null) {
            System.out.println("fail to eat " + foodName);
        } else {
            int size = arraylist.size();
            if (size == 0) { //使用失败;
                System.out.println("fail to eat " + foodName);
            } else {
                int foodId = arraylist.get(0);
                Food food = get_food(foodId);
                int energy = food.getEnergy();
                level = level + energy;
                long value = food.getPrice();
                sub_price(value);
                arraylist.remove(0);//食物不再属于冒险者;
                foodMap.remove(foodId);
                System.out.println(foodId + " " + level);
            }
        }
    }
    
    public boolean search_bottlebag(String bottleName) {
        ArrayList<Integer> arraylist = bottleBag.get(bottleName);
        if (arraylist == null || arraylist.size() == 0) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean search_equipmentbag(String equName) {
        return equipmentBag.containsKey(equName);
    }
    
    public void attacked(int stars, int levels, Equipment equipment) { //受到攻击，体力值减少；
        if (equipment instanceof RegularEquipment) {
            hp = hp - stars * levels;
            int num = getId();
            System.out.println(num + " " + hp);
        } else if (equipment instanceof CritEquipment) {
            CritEquipment critequipmemt = (CritEquipment) equipment;
            int critical = critequipmemt.getCritical();
            hp = hp - stars * levels - critical;
            int num = getId();
            System.out.println(num + " " + hp);
        } else {
            EpicEquipment epicequipment = (EpicEquipment) equipment;
            double ratio = epicequipment.getRatio();
            hp = hp - (int) (hp * ratio);
            int num = getId();
            System.out.println(num + " " + hp);
        }
        
    }
    
    public void aoe_attacked(int stars, int levels, Equipment equipment) { //aoe攻击，输出之间互带空格;
        if (equipment instanceof RegularEquipment) { //普通装备，同原来一样;
            hp = hp - stars * levels;
            System.out.print(hp + " ");
        } else if (equipment instanceof CritEquipment) { //暴击装备;
            CritEquipment critequipment = (CritEquipment) equipment;
            int critical = critequipment.getCritical();
            hp = hp - stars * levels - critical;
            System.out.print(hp + " ");
        } else { //史诗装备;
            EpicEquipment epicequipment = (EpicEquipment) equipment;
            double ratio = epicequipment.getRatio();
            hp = hp - (int) (hp * ratio);
            System.out.print(hp + " ");
        }
    }
    
    public Equipment get_equipment_bag(String equName) {
        return equipmentBag.get(equName);
    }
    
    public String getName() {
        return name;
    }
    
    public int getLevel() {
        return level;
    }
    
    public int getId() {
        return id;
    }
    
    public long getPrice() {
        return price;
    }
    
    public void add_price(long value) { //每次加入一个(bot,equip,adventurer)就得更新下一个冒险者的自身价值；
        price = price + value;
    }
    
    public void sub_price(long value) {
        price = price - value;
    }
    
    public void add_employAdventurerSet(Adventurer adv) {
        employAdventurerSet.add(adv);
    } //新增一个仆人,且不能重复出现
    
    public HashSet<Adventurer> getEmployAdventurerSet() { //找到该冒险者的雇佣者名单;
        return employAdventurerSet;
    }
    
    public long calculate_employer_price(Adventurer adv) { //计算该冒险者拥有的冒险者价值体的总价值;
        HashSet<Adventurer> map = adv.getEmployAdventurerSet();
        if (map.size() == 0) {
            return adv.getPrice();//这个冒险者只有bot,equip,food带来的价值;
        } else {
            long value = adv.getPrice();//自身非雇佣的价值;
            for (Adventurer employ : map) {
                value = value + calculate_employer_price(employ);//递归计算;
            }
            return value;
        }
    }
    
    public int search_amount_of_commodity() { //该冒险者拥有的价值体数量;
        return bottleMap.size() + equipmentMap.size() + foodMap.size() + employAdventurerSet.size();
    }
    
    public long owner_max_price() { //计算该冒险者持有价值体的最大值;
        if (search_amount_of_commodity() == 0) { //不具有价值体;
            return 0;
        } else {
            long max = 0;
            for (Bottle bottle : bottleMap.values()) {
                long value = bottle.getPrice();
                if (value > max) {
                    max = value;
                }
            }
            for (Equipment equipment : equipmentMap.values()) {
                long value = equipment.getPrice();
                if (value > max) {
                    max = value;
                }
            }
            for (Food food : foodMap.values()) {
                long value = food.getPrice();
                if (value > max) {
                    max = value;
                }
            }
            for (Adventurer adv : employAdventurerSet) {
                long value = calculate_employer_price(adv);
                if (value > max) {
                    max = value;
                }
            }
            return max;
        }
    }
    
    public void search_obj_instanceof(int comId) {
        if (bottleMap.containsKey(comId)) {
            Bottle bottle = bottleMap.get(comId);
            if (bottle instanceof RegularBottle) {
                System.out.println("Commodity whose id is " + comId + " belongs to RegularBottle");
            } else if (bottle instanceof RecoverBottle) {
                System.out.println("Commodity whose id is " + comId + " belongs to RecoverBottle");
            } else {
                System.out.print("Commodity whose id is ");
                System.out.println(comId + " belongs to ReinforcedBottle");
            }
        } else if (equipmentMap.containsKey(comId)) {
            Equipment equipment = equipmentMap.get(comId);
            if (equipment instanceof RegularEquipment) {
                System.out.print("Commodity whose id is ");
                System.out.println(comId + " belongs to RegularEquipment");
            } else if (equipment instanceof CritEquipment) {
                System.out.println("Commodity whose id is " + comId + " belongs to CritEquipment");
            } else {
                System.out.println("Commodity whose id is " + comId + " belongs to EpicEquipment");
            }
        } else if (foodMap.containsKey(comId)) {
            System.out.println("Commodity whose id is " + comId + " belongs to Food");
        } else {
            System.out.println("Commodity whose id is " + comId + " belongs to Adventurer");
        }
    }
}
