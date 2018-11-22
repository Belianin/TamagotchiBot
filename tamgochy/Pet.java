package tamgochy;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
//import com.sun.javafx.collections.MappingChange.Map;
public class Pet {
    private Date lastUpdate = new Date();
    public boolean alive = true;
    public int hunger = 100;
    public int fun = 100;
    public int clean = 100;
    public int sleep = 100;
    public int toilet = 100;
    
    public Date getLastUpdate() {
        return lastUpdate;
    }
    
    public void setLastUpdate(Date date) {
        lastUpdate = date;
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
    public void Down(String key, int value) {
        try {
        	Field field = this.getClass().getField(key);
        	int newValue = field.getInt(this) - value;
            if (key.equals("hunger") && newValue < 0) {
                alive = false;
            }
            if (newValue < 0) {
            	newValue = 0;
            }
            if (newValue < 30) {
                System.out.println("!!!Критические показатели: " + key + ": " + newValue);
            }
            field.set(this, newValue);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}