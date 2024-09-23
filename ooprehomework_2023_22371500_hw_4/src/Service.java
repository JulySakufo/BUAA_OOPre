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
            } else {
                case13(strings, adventurer);
            }
        }
    }
    
    public static Adventurer get_adventurer(String strings) {
        return adventurerNameMap.get(strings);
    }
    
    public static void case2(String[] strings, Adventurer adventurer) {
        int id = Integer.parseInt(strings[2]);
        String name = strings[3];
        int capacity = Integer.parseInt(strings[4]);
        adventurer.add_bottle(id, name, capacity);
    }
    
    public static void case3(String[] strings, Adventurer adventurer) {
        int id = Integer.parseInt(strings[2]);
        adventurer.delete_bottle(id);
    }
    
    public static void case4(String[] strings, Adventurer adventurer) {
        int id = Integer.parseInt(strings[2]);
        String name = strings[3];
        int star = Integer.parseInt(strings[4]);
        adventurer.add_equipment(id, name, star);
    }
    
    public static void case5(String[] strings, Adventurer adventurer) {
        int id = Integer.parseInt(strings[2]);
        adventurer.delete_equipment(id);
    }
    
    public static void case6(String[] strings, Adventurer adventurer) {
        int id = Integer.parseInt(strings[2]);
        adventurer.upstar_equipment(id);
    }
    
    public static void case7(String[] strings, Adventurer adventurer) {
        int id = Integer.parseInt(strings[2]);
        String name = strings[3];
        int star = Integer.parseInt(strings[4]);
        adventurer.add_food(id, name, star);
    }
    
    public static void case8(String[] strings, Adventurer adventurer) {
        int id = Integer.parseInt(strings[2]);
        adventurer.delete_food(id);
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
    
}
