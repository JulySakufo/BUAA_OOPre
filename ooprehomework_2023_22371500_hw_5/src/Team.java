import java.util.ArrayList;
import java.util.Iterator;

public class Team {
    private final ArrayList<Soldier> soldiers;
    
    public Team() {
        this.soldiers = new ArrayList<>();
    }
    
    public void addSoldier(Soldier soldier) {
        soldiers.add(soldier);
    }
    
    public void clear_soldiers() {
        soldiers.clear();
    }
    
    public void screen(int standard) {
        Iterator<Soldier> iterator = soldiers.iterator();
        while (iterator.hasNext()) {
            Soldier soldier = iterator.next();
            if (!soldier.notQualifiedByStandard(standard)) { //not-qualified为false执行删除;
                iterator.remove();//用迭代器来删除;
            }
        }
    }
    
    public void allAddStr(String str) {
        for (Soldier soldier : soldiers) {
            soldier.appendStr2Incantation(str);
        }
    }
    
    public void allIntercept(int a, int b) {
        for (Soldier soldier : soldiers) {
            int length = soldier.get_incantation_length() - 1;//length是下标范围，长度为6，下标是5;
            if (a > b || a > length) { //a超出下标范围或者a>b得到空串;
                soldier.set_incantation_empty();
            } else if (b > length) { //b>s,截取到末尾即可;
                soldier.cutIncantation(a, length);
            } else { //否则进行符合规则的截取；
                soldier.cutIncantation(a, b);
            }
        }
    }
    
    public Team cloneSelf() {
        Team team = new Team();
        for (Soldier soldier : soldiers) {
            team.addSoldier(soldier.cloneSoldier());
        }
        return team;
    }
    
    public void mergeTeam(Team team) {
        for (Soldier soldier : team.soldiers) { //team2
            boolean repeat = false;
            for (Soldier oldSoldier : this.soldiers) { //this-> team1
                if (soldier.equal(oldSoldier)) { //如果两小队中存在一模一样的两个士兵，则并入后的士兵仅保留其中一个。
                    repeat = true;
                    break;
                }
            }
            if (!repeat) { //不重复则加入team1;
                this.soldiers.add(soldier);
            }
        }
    }
    
    public int getSize() {
        return this.soldiers.size();
    }
    
    public int getSizeOfHasStr(String str) {
        int count = 0;
        for (Soldier soldier : soldiers) {
            if (soldier.hasString(str)) {
                count++;
            }
        }
        return count;
    }
}

