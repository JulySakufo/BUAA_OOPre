import java.util.ArrayList;
import java.util.HashMap;

public class Adventurer {
    private int id;
    private String name;
    private ArrayList<Bottle> bottleArray;
    private HashMap<Integer, String> bottleMap;//创建bot_id与name对应的关系；
    private ArrayList<Equipment> equipmentArray;
    private HashMap<Integer, String> equipmentMap;//创建equ_id与name对应的关系；
    private HashMap<Integer, Integer> equipmentMapStar;//创建equ_id与star的关系；
    
    public Adventurer(int id, String name) {
        this.id = id;
        this.name = name;
        this.bottleMap = new HashMap<>();//无参数容器的初始化
        this.equipmentMap = new HashMap<>();
        this.equipmentMapStar = new HashMap<>();
        this.bottleArray = new ArrayList<>();
        this.equipmentArray = new ArrayList<>();
    } //加入一个冒险者
    
    public void add_bottle(int botId, String name, int capacity) {
        bottleMap.put(botId, name);//homework_2暂时忽略capacity；那么我们只需要记录药水瓶的名字和id对应，删除时在hashmap中找到id即可；
        Bottle bottle = new Bottle(botId, name, capacity);
        bottleArray.add(bottle);
    } //增加一个药水瓶,用bottle_array.size()即可得到该冒险者的药水瓶个数；
    
    public void add_equipment(int equId, String name, int star) {
        equipmentMap.put(equId, name);
        equipmentMapStar.put(equId, star);
        Equipment equipment = new Equipment(equId, name, star);
        equipmentArray.add(equipment);
    }
    
    public void delete_bottle(int botId) {
        String botName = bottleMap.get(botId);//访问bottle_map的对应表从而得到id与name的对应关系；
        bottleMap.remove(botId, botName);//删除这个药水瓶；
        int botSize = bottleMap.size();//得到现存药水数；
        System.out.println(botSize + " " + botName);//打印删除后药水瓶的总数和删除的药水瓶的名字；
    }
    
    public void delete_equipment(int equId) {
        String equName = equipmentMap.get(equId);
        int star = equipmentMapStar.get(equId);
        equipmentMap.remove(equId, equName);
        equipmentMapStar.remove(equId, star);
        int equSize = equipmentMap.size();
        System.out.println(equSize + " " + equName);
    }
    
    public void upstar_equipment(int equId) {
        String equName = equipmentMap.get(equId);
        int oldStar = equipmentMapStar.get(equId);
        equipmentMapStar.replace(equId, oldStar, oldStar + 1);
        int newStar = oldStar + 1;
        System.out.println(equName + " " + newStar);
    }
    
    public int get_star(int equId) {
        return equipmentMapStar.get(equId);
    }
}
