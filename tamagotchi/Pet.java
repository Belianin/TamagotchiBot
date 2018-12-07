package tamagotchi;
import java.util.Date;

public class Pet {
    public int trainLvl = 0;
    public boolean alive = true;
    public boolean isSleep = false;

    //мб уже и не надо
    private Date lastUpdate = new Date();
    
    private int hunger = 100;
    private int health = 100;
    private int clean = 100;
    private int sleep = 100;
    private int toilet = 100;

    //Пока вечно истинно
    public boolean canToilet = true;
    public boolean canClean = true;
    public boolean canFun = true;
    public boolean canEat = true;
    public boolean canSleep = true;
    
    public boolean InDungeon = false;
    
    public String name;
    public UserData master;
    
    public Pet(String name, UserData master) {
    	this.name = name;
    	this.master = master;
    }


    public int getToilet() {
        return toilet;
    }

    public void addToilet(int toilet) {
        this.toilet = correctValue(this.toilet + toilet);
    }

    public int getSleep() {
        return sleep;
    }

    public void addSleep(int sleep) {
        this.sleep = correctValue(this.sleep + sleep);
    }

    public int getClean() {
        return clean;
    }

    public void addClean(int clean) {
        this.clean = correctValue(this.clean + clean);
    }

    public int getHealth() {
        return health;
    }

    public void addHealth(int fun) {
        this.health = correctValue(this.health + fun);
    }

    public int getHunger() {
        return hunger;
    }

    public void addHunger(int hunger) {
        this.hunger = correctValue(this.hunger + hunger);
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date date) {
        lastUpdate = date;
    }

    private int correctValue(int value) {
        if (value > 100)
            value = 100;
        else if (value < 0)
            value = 0;

        return value;
    }

    public String getStates() {
        return new StringBuilder()
                .append("Сытость: " + hunger + " || ")
                .append("Здоровье: " + health + " || ")
                .append("Гигиена: " + clean + " || ")
                .append("Сон: " + sleep + " || ")
                .append("Туалет: " + toilet)
                .toString();
    }

}