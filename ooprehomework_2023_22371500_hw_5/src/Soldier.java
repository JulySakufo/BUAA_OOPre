public class Soldier {
    private String name;
    private String incantation;
    
    public Soldier(String name, String incantation) {
        this.name = name;
        this.incantation = incantation;
    }
    
    // this method means "cut" the "incantation"
    public void cutIncantation(int a, int b) {
        incantation = incantation.substring(a, b + 1); //[a,b];
    }
    
    //  2 means "to" , you will see it in the os codes  next semester
    public void appendStr2Incantation(String str) {
        incantation += str;
    }
    
    public Soldier cloneSoldier() {
        return new Soldier(this.name, this.incantation);
    }
    
    // !!!!! Be careful, this method  need to be carefully read and analyzed to identify bugs!
    public boolean notQualifiedByStandard(int standard) {
        int head = 0;
        int tail = 1;
        for (int i = head; i < incantation.length(); i++) {
            if (incantation.charAt(i) == '@') {
                head = i;//找到第一个@;
                tail = head + 1;
                break;
            }
        }
        int totalCount = 0;
        while (tail < incantation.length()) {
            if (incantation.charAt(tail) != '@') { //找到末尾为@的地方，把它当作子串的一部分;
                tail++;
                continue;
            } //已经找到了末尾的@;
            int count = 0; //计算小写字母和大写字母谁多，如果大写字母>=小写字母，为>=0;
            for (int i = head; i <= tail; i++) {
                if (incantation.charAt(i) >= 'A' && incantation.charAt(i) <= 'Z') {
                    count++;
                }
                if (incantation.charAt(i) >= 'a' && incantation.charAt(i) <= 'z') {
                    count--;
                }
            }
            if (count >= 0) { //大写字母的个数不少于小写字母;
                totalCount++; //满足特定条件 C 的不同子串数目++;
            }
            head = tail; //将head挪到上一次末尾的@作为下一个子串的开头;
            tail++;//末尾++;
        }
        return totalCount >= standard;
    }
    
    public int get_incantation_length() {
        return incantation.length();
    }
    
    public void set_incantation_empty() {
        incantation = ""; //取代null的空串,(有地址无内容)才符合要求;
    }
    
    public boolean hasString(String str) {
        return incantation.contains(str);
    }
    
    public boolean equal(Soldier soldier) {
        return this.name.equals(soldier.name) && this.incantation.equals(soldier.incantation);
    }
    
}

