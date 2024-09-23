import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;

public class Adventurer {
    private int id;
    private String name;
    private int hp;
    private int level;
    private HashMap<Integer, Bottle> bottleMap;//创建bot_id与bottle对应的关系；
    private HashMap<Integer, Equipment> equipmentMap;//创建equ_id与equipment对应的关系；
    private HashMap<Integer, Food> foodMap;//创建food_id与food对应的关系;
    private HashMap<String, Equipment> equipmentBag;//背包中的装备;
    private HashMap<String, ArrayList<Integer>> foodBag;//不同id同名食物放在一个arraylist里，arraylist实现id排序;
    private HashMap<String, ArrayList<Integer>> bottleBag;
    
    public Adventurer(int id, String name) {
        this.id = id;
        this.name = name;
        this.hp = 500;
        this.level = 1;
        this.bottleMap = new HashMap<>();//无参数容器的初始化
        this.equipmentMap = new HashMap<>();
        this.foodMap = new HashMap<>();
        this.equipmentBag = new HashMap<>();
        this.foodBag = new HashMap<>();
        this.bottleBag = new HashMap<>();
    } //加入一个冒险者
    
    public void add_bottle(int botId, String name, int capacity) {
        Bottle bottle = new Bottle(botId, name, capacity);
        bottleMap.put(botId, bottle);//实现id和药水瓶的映射关系;
    } //增加一个药水瓶,用bottle_array.size()即可得到该冒险者的药水瓶个数;
    
    public void add_equipment(int equId, String name, int star) {
        Equipment equipment = new Equipment(equId, name, star);
        equipmentMap.put(equId, equipment);
    }
    
    public void add_food(int foodId, String name, int energy) {
        Food food = new Food(foodId, name, energy);
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
        equipmentMap.remove(equId, equipment);
        Equipment equipment1 = equipmentBag.get(equName);
        if (equipment1 != null) { //背包中已有同名装备;
            if (equipment == equipment1) { //背包里的就是仓库里的那一件;
                equipmentBag.remove(equName); //从背包中删去这件装备;
            }
        }
        int equSize = equipmentMap.size();
        System.out.println(equSize + " " + equName);
    }
    
    public void delete_food(int foodId) {
        Food food = get_food(foodId);
        String foodName = food.getName();
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
    
    public void use_bottle(String botName) {
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
                hp = hp + capacity;//体力增加;
                if (capacity != 0) { //先喝，喝完保留空瓶，下次再喝的时候喝了空瓶然后扔掉空瓶;
                    capacity = bottle.subCapacity();
                } else { //capacity为0的扔掉瓶子;
                    arraylist.remove(0);//丢弃用过的id最小的药水瓶;
                    bottleMap.remove(botId);//背包中丢弃，也即他不再拥有这个药水瓶;
                }
                System.out.println(botId + " " + hp);
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
                arraylist.remove(0);//食物不再属于冒险者;
                foodMap.remove(foodId);
                System.out.println(foodId + " " + level);
            }
        }
    }
}
