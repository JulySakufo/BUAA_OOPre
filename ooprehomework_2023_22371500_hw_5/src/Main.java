import java.util.Scanner;

public class Main {
    public static void main(String[] args) { //程序的入口，引入scanner，在demon对scanner进行输入流操作;
        Scanner scanner = new Scanner(System.in);
        Demon demon = new Demon(scanner);//传参建立demon类，供demon进行操作;
        demon.solve();
    }
}
