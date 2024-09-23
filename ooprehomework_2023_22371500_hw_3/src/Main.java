import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, Adventurer> adventurerMap = new HashMap<>();//创建一个冒险者名单
        ArrayList<ArrayList<String>> inputInfo = new ArrayList<>(); // 解析后的输入将会存进该容器中, 类似于c语言的二维数组
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim()); // 读取行数
        for (int i = 0; i < n; ++i) {
            String nextLine = scanner.nextLine(); // 读取本行指令
            String[] strings = nextLine.trim().split(" +"); // 按空格对行进行分割
            inputInfo.add(new ArrayList<>(Arrays.asList(strings))); // 将指令分割后的各个部分存进容器中
        }
        
        for (int i = 0; i < inputInfo.size(); i++) {
            ArrayList<String> value = inputInfo.get(i);//从inputInfo容器中取出一行指令；准备看指令类型做出不同的反应；
            String type = value.get(0);//看看指令的type是什么，从而决定进行什么操作；
            int advId = Integer.parseInt(value.get(1));//指令为新加入一个冒险者，value只有两个值，依次取出来即可；
            if (type.equals("1")) {
                String name = value.get(2);
                Adventurer adventurer = new Adventurer(advId, name);//创建了一个新的冒险者账号，生成了id和name；
                adventurerMap.put(advId, adventurer);//adventurer_map生成冒险者名单，每一个id对应一个adventurer对象；
            } else {
                Adventurer adventurer = adventurerMap.get(advId);//adventurer保存了冒险者的一切数据；下面我们对其进行操作；
                if (type.equals("12") || type.equals("13")) {
                    String name = value.get(2);
                    if (type.equals("12")) {
                        adventurer.use_bottle(name);//尝试使用名字为bottleName的药水瓶;
                    } else {
                        adventurer.use_food(name);
                    }
                } else {
                    int id = Integer.parseInt(value.get(2));
                    if (type.equals("2")) {
                        case2(id, value, adventurer);
                    } else if (type.equals("3") || type.equals("5") || type.equals("8")) {
                        if (type.equals("3")) {
                            adventurer.delete_bottle(id);//充分利用id的唯一性进行查询操作；
                        } else if (type.equals("5")) {
                            adventurer.delete_equipment(id);
                        } else {
                            adventurer.delete_food(id);
                        }
                    } else if (type.equals("4") || type.equals("7")) {
                        if (type.equals("4")) {
                            case4(id, value, adventurer);
                        } else {
                            case7(id, value, adventurer);
                        }
                    } else if (type.equals("6")) {
                        adventurer.upstar_equipment(id);
                    } else if (type.equals("9")) {
                        adventurer.carry_equipment(id);//尝试携带id为equId的装备;
                    } else if (type.equals("10")) {
                        adventurer.carry_bottle(id);
                    } else if (type.equals("11")) {
                        adventurer.carry_food(id);
                    }
                }
            }
        }
        scanner.close();//关闭scanner;
    }
    
    public static void case2(int id, ArrayList<String> value, Adventurer adventurer) {
        String name = value.get(3);
        int capacity = Integer.parseInt(value.get(4));
        adventurer.add_bottle(id, name, capacity);//对该adventurer对应目录下新增了一个药水瓶；
    }
    
    public static void case4(int id, ArrayList<String> value, Adventurer adventurer) {
        String name = value.get(3);
        int star = Integer.parseInt(value.get(4));
        adventurer.add_equipment(id, name, star);//对该adventurer目录下新增了一件装备；
    }
    
    public static void case7(int id, ArrayList<String> value, Adventurer adventurer) {
        String name = value.get(3);
        int star = Integer.parseInt(value.get(4));
        adventurer.add_food(id, name, star);
    }
}
