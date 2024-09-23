import java.util.Scanner;

public class Main {
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
                Fightlog.case15(strings[1]);
            } else if (type == 16) {
                Fightlog.case16(strings[1]);
            } else if (type == 17) {
                Fightlog.case17(strings[1]);
            } else { //进行无关日志的基本操作;
                Service.service(strings);
            }
        }
        scanner.close();//关闭scanner;
    }
    
    public static void input(String[] strings, Scanner scanner) {
        int fightPeople = Integer.parseInt(strings[1]); //战斗的人数;
        for (int j = 0; j < fightPeople; j++) {
            Fightlog.put_into_fightAdventurerMap(j, strings[3 + j]);
        }
        int number = Integer.parseInt(strings[2]);
        for (int j = 0; j < number; j++) {
            String value = scanner.nextLine(); //读取一行日志;
            Fightlog.case14(value);//进行今日日志操作;
        }
        Fightlog.clear_fightAdventurerMap(); //战斗模式结束，清空这次进入战斗的冒险者，为下一次做准备;
    }
}

