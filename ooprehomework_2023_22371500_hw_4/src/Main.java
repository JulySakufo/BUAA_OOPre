import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;
import java.util.ArrayList;

public class Main {
    private static HashMap<String, ArrayList<String>> dateLog = new HashMap<>();//创建map
    private static HashMap<Integer, ArrayList<String>> attackLog = new HashMap<>();//攻击者日志
    private static HashMap<Integer, ArrayList<String>> attackedLog = new HashMap<>();//被攻击者日志；
    private static HashMap<Integer, String> fightAdventurerMap = new HashMap<>();//开启战斗模式的冒险者;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim()); // 读取行数
        for (int i = 0; i < n; ++i) {
            String nextLine = scanner.nextLine(); // 读取本行指令
            String[] strings = nextLine.trim().split(" +"); // 按空格对行进行分割
            int type = Integer.parseInt(strings[0]);
            if (type == 14) {
                System.out.println("Enter Fight Mode");
                input(strings, scanner);
            } else if (type == 15) {
                case15(strings[1]);
            } else if (type == 16) {
                case16(strings[1]);
            } else if (type == 17) {
                case17(strings[1]);
            } else { //进行无关日志的基本操作;
                Service.service(strings);
            }
        }
        scanner.close();//关闭scanner;
    }
    
    public static void input(String[] strings, Scanner scanner) {
        int fightPeople = Integer.parseInt(strings[1]); //战斗的人数;
        for (int j = 0; j < fightPeople; j++) {
            fightAdventurerMap.put(j, strings[3 + j]);//将开启战斗模式的冒险者加入战斗名单中;
        }
        int number = Integer.parseInt(strings[2]);
        for (int j = 0; j < number; j++) {
            String value = scanner.nextLine(); //读取一行日志;
            case14(value);//进行今日日志操作;
        }
        fightAdventurerMap.clear(); //战斗模式结束，清空这次进入战斗的冒险者，为下一次做准备;
    }
    
    public static void add_fightAdventurerMap(int id, String name) { //仅用于junit,方便对map的增加;
        fightAdventurerMap.put(id, name);
    }
    
    public static void case14(String value) {
        Pattern pattern1 = Pattern.compile("(\\d{4})/((1[0-2])|(0[1-9]))-(.+)-(.+)"); //第一种战斗日志;
        Pattern pattern2 = Pattern.compile("(\\d{4})/((1[0-2])|(0[1-9]))-(.+)@(.+)-(.+)");//第二种战斗日志
        Pattern pattern3 = Pattern.compile("(\\d{4})/((1[0-2])|(0[1-9]))-(.+)@#-(.+)");//第三种战斗日志;
        Matcher matcher1 = pattern1.matcher(value);
        Matcher matcher2 = pattern2.matcher(value);
        Matcher matcher3 = pattern3.matcher(value);
        if (matcher3.find()) { //冒险者用名字为name的武器发起了所有进入战斗模式的aoe攻击
            String advName = matcher3.group(5);
            String equName = matcher3.group(6);
            Adventurer adventurer = Service.get_adventurer(advName);
            if (is_legal_3(advName, equName, adventurer)) {
                save_into_dateLog_3(matcher3);
                save_into_attackLog_3(matcher3);
                save_into_attackedLog_3(matcher3);
            }
        } else if (matcher2.find()) { //冒险者1对冒险者2发起了攻击;
            String advName1 = matcher2.group(5);
            String advName2 = matcher2.group(6);
            String equName = matcher2.group(7);
            Adventurer adventurer1 = Service.get_adventurer(advName1);
            Adventurer adventurer2 = Service.get_adventurer(advName2);
            if (is_legal_2(adventurer1, adventurer2, equName)) {
                save_into_dateLog_2(matcher2);
                save_into_attackLog_2(matcher2);
                save_into_attackedLog_2(matcher2);
            }
        } else if (matcher1.find()) { //满足第一种战斗日志格式，判断合法性;
            String advName = matcher1.group(5);//查询冒险者是否进入了战斗模式;
            String bottleName = matcher1.group(6);//查询该冒险者是否携带了这个药水瓶;
            Adventurer adventurer = Service.get_adventurer(advName);
            if (is_legal_1(advName, bottleName, adventurer)) { //合法存储进日志；
                save_into_dateLog_1(matcher1);
            }
        } else { //战斗日志不满足以上三种格式，视为战斗日志无效，不产生任何作用，也不会存储进战斗日志;
            System.out.println("Fight log error");
        }
    }
    
    public static void case15(String strings) {
        ArrayList<String> arraylist = dateLog.get(strings);//得到日期对应的战斗日志;
        if (arraylist == null) { //不存在符合条件的日志;
            System.out.println("No Matched Log");
        } else {
            for (String s : arraylist) { //按输入顺序打表;
                System.out.println(s);
            }
        }
    }
    
    public static void case16(String strings) {
        int advId = Integer.parseInt(strings);
        ArrayList<String> arraylist = attackLog.get(advId);
        if (arraylist == null) {
            System.out.println("No Matched Log");
        } else {
            for (String s : arraylist) { //按输入顺序打表;
                System.out.println(s);
            }
        }
    }
    
    public static void case17(String strings) {
        int advId = Integer.parseInt(strings);
        ArrayList<String> arraylist = attackedLog.get(advId);
        if (arraylist == null) {
            System.out.println("No Matched Log");
        } else {
            for (String s : arraylist) { //按输入顺序打表;
                System.out.println(s);
            }
        }
    }
    
    public static boolean is_legal_1(String advName, String bottleName, Adventurer adventurer) {
        if (!fightAdventurerMap.containsValue(advName)) { //冒险者未进入战斗模式;
            System.out.println("Fight log error");
        } else {
            if (!adventurer.search_bottlebag(bottleName)) { //该冒险者未将该名字的药水瓶放入背包中;
                System.out.println("Fight log error");
            } else { //合法,进行成功的输出，并保存在datelog中;
                adventurer.use_bottle(bottleName);//用封装好的函数直接操作；进行成功的输出;
                return true;
            }
        }
        return false;
    }
    
    public static void save_into_dateLog_1(Matcher matcher) {
        String date = matcher.group(1) + "/" + matcher.group(2);
        String log = date + " " + matcher.group(5) + " used " + matcher.group(6);
        ArrayList<String> arraylist = dateLog.get(date);
        if (arraylist == null) { //还未出现过该日期;
            arraylist = new ArrayList<>(); //创建一个对应日期的日志表;
            arraylist.add(log);
            dateLog.put(date, arraylist);
        } else { //已经出现过;
            arraylist.add(log); //增加日志数即可;
        }
    }
    
    public static boolean is_legal_2(Adventurer adv1, Adventurer adv2, String equName) {
        String n1 = adv1.getName();
        String n2 = adv2.getName();
        if (!(fightAdventurerMap.containsValue(n1)) || !(fightAdventurerMap.containsValue(n2))) {
            System.out.println("Fight log error");
        } else {
            if (!adv1.search_equipmentbag(equName)) {
                System.out.println("Fight log error");
            } else { //合法;
                int level = adv1.getLevel();
                int star = adv1.get_equipment_bag(equName).getStar();
                adv2.attacked(star, level);
                return true;
            }
        }
        return false;
    }
    
    public static void save_into_dateLog_2(Matcher matcher) {
        String date = matcher.group(1) + "/" + matcher.group(2);
        String log = date + " " + matcher.group(5) + " attacked " + matcher.group(6);
        log = log + " with " + matcher.group(7);
        ArrayList<String> arraylist = dateLog.get(date);
        if (arraylist == null) { //还未出现过该日期;
            arraylist = new ArrayList<>(); //创建一个对应日期的日志表;
            arraylist.add(log);
            dateLog.put(date, arraylist);
        } else { //已经出现过;
            arraylist.add(log); //增加日志数即可;
        }
    }
    
    public static void save_into_attackLog_2(Matcher matcher) {
        String date = matcher.group(1) + "/" + matcher.group(2);
        String log = date + " " + matcher.group(5) + " attacked ";
        log = log + matcher.group(6) + " with " + matcher.group(7);
        Adventurer adventurer = Service.get_adventurer(matcher.group(5));
        int advId = adventurer.getId();
        ArrayList<String> arraylist = attackLog.get(advId);
        if (arraylist == null) {
            arraylist = new ArrayList<>();
            arraylist.add(log);
            attackLog.put(advId, arraylist);
        } else {
            arraylist.add(log);
        }
    }
    
    public static void save_into_attackedLog_2(Matcher matcher) {
        String date = matcher.group(1) + "/" + matcher.group(2);
        String log = date + " " + matcher.group(5) + " attacked ";
        log = log + matcher.group(6) + " with " + matcher.group(7);
        Adventurer adventurer = Service.get_adventurer(matcher.group(6));
        int advId = adventurer.getId();
        ArrayList<String> arraylist = attackedLog.get(advId);
        if (arraylist == null) {
            arraylist = new ArrayList<>();
            arraylist.add(log);
            attackedLog.put(advId, arraylist);
        } else {
            arraylist.add(log);
        }
    }
    
    public static boolean is_legal_3(String advName, String equName, Adventurer adventurer) {
        if (!fightAdventurerMap.containsValue(advName)) {
            System.out.println("Fight log error");
        } else {
            if (!adventurer.search_equipmentbag(equName)) {
                System.out.println("Fight log error");
            } else { //合法;
                int level = adventurer.getLevel();
                int star = adventurer.get_equipment_bag(equName).getStar();
                for (int i = 0; i < fightAdventurerMap.size(); i++) {
                    String name = fightAdventurerMap.get(i);//按进入战斗顺序得到冒险者的名字;
                    if (!advName.equals(name)) { //不是该发起攻击者，成为被攻击者;
                        Adventurer adv = Service.get_adventurer(name);//找到被攻击的冒险者;
                        adv.aoe_attacked(star, level);
                    }
                }
                System.out.println();//结束了aoe攻击，输出换行，等待下一次的整齐格式输出;
                return true;
            }
        }
        return false;
    }
    
    public static void save_into_dateLog_3(Matcher matcher) {
        String date = matcher.group(1) + "/" + matcher.group(2);
        String log = date + " " + matcher.group(5) + " AOE-attacked with " + matcher.group(6);
        ArrayList<String> arraylist = dateLog.get(date);
        if (arraylist == null) { //还未出现过该日期;
            arraylist = new ArrayList<>(); //创建一个对应日期的日志表;
            arraylist.add(log);
            dateLog.put(date, arraylist);
        } else { //已经出现过;
            arraylist.add(log); //增加日志数即可;
        }
    }
    
    public static void save_into_attackLog_3(Matcher matcher) {
        String date = matcher.group(1) + "/" + matcher.group(2);
        String log = date + " " + matcher.group(5) + " AOE-attacked with " + matcher.group(6);
        Adventurer adventurer = Service.get_adventurer(matcher.group(5));
        int advId = adventurer.getId();
        ArrayList<String> arraylist = attackLog.get(advId);
        if (arraylist == null) {
            arraylist = new ArrayList<>();
            arraylist.add(log);
            attackLog.put(advId, arraylist);
        } else {
            arraylist.add(log);
        }
    }
    
    public static void save_into_attackedLog_3(Matcher matcher) {
        String date = matcher.group(1) + "/" + matcher.group(2);
        String advName = matcher.group(5);
        String log = date + " " + matcher.group(5) + " AOE-attacked with " + matcher.group(6);
        for (int i = 0; i < fightAdventurerMap.size(); i++) {
            String name = fightAdventurerMap.get(i);//按进入战斗顺序得到冒险者的名字;
            if (!advName.equals(name)) { //不是该发起攻击者，成为被攻击者;
                Adventurer adv = Service.get_adventurer(name);//找到被攻击的冒险者;
                int advId = adv.getId();
                ArrayList<String> arraylist = attackedLog.get(advId);
                if (arraylist == null) {
                    arraylist = new ArrayList<>();
                    arraylist.add(log);
                    attackedLog.put(advId, arraylist);
                } else {
                    arraylist.add(log);
                }
            }
        }
    }
}

