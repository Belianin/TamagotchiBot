package tamagotchi;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
//import com.sun.javafx.collections.MappingChange.Map;
public class Pet {
    public StringBuilder messages = new StringBuilder();
    public int trainLvl = 0;
    public boolean alive = true;
    public boolean train = true;
    private Date lastUpdate = new Date();
    private int hunger = 100;
    private int fun = 100;
    private int clean = 100;
    private int sleep = 100;
    private int toilet = 100;

    public boolean canToilet = false;
    public boolean canClean = false;
    public boolean canFun = false;
    public boolean canEat = false;
    public boolean canSleep = false;

    
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
    
    public int getFun() {
    	return fun;
    }
    
    public void addFun(int fun) {
    	this.fun = correctValue(this.fun + fun);
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
    		.append("Счастье: " + fun + " || ")
    		.append("Гигиена: " + clean + " || ")
    		.append("Сон: " + sleep + " || ")
    		.append("Туалет: " + toilet)
    		.toString();
    }
    public void Up(String key, int value) {
        try {
        	Field field = this.getClass().getField(key);
        	int newValue = field.getInt(this) + value;
            if (newValue > 100) {
            	newValue = 100;
            }
            field.set(this, newValue);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
//    public void Down(String key, int value) {
//        try {
//        	Field field = this.getClass().getField(key);
//        	int newValue = field.getInt(this) - value;
//            if (key.equals("hunger") && newValue < 0) {
//                alive = false;
//            }
//            if (newValue < 0) {
//            	newValue = 0;
//            }
//            if (newValue < 30) {
//                System.out.println("!!!Критические показатели: " + key + ": " + newValue);
//            }
//            field.set(this, newValue);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
}