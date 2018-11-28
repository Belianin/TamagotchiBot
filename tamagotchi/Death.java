package tamagotchi;
import java.util.Date;

public class Death extends Event {
    private Pet pet;
    private int deathCoef = 10; 
    public static int minDeathTime = 100000;

    public Death(Pet pet) {
        this.pet = pet;
        Date currentDate = new Date();
        this.when = new Date(currentDate.getTime() + minDeathTime);
    }

    @Override
    public boolean tryApply() {
    	int coef = getDeathCount(when);
    	//coef = 1;
    	
    	pet.addHunger(-3 * coef);
    	pet.addFun(-3 * coef);
    	pet.addClean(-5 * coef);
    	pet.addSleep(-2 * coef);
    	pet.addToilet(-6 * coef);
    	pet.setLastUpdate(new Date());
    	
    	when = new Date(when.getTime() + minDeathTime);
    	
    	//и тут же проверку на смерть
    	return false;
    }
	private int getDeathCount(Date date) {
		Date currentDate = new Date();
		long difference = currentDate.getTime() - date.getTime();
		int sec = (int) (difference / 1000);
		return Math.abs(sec / deathCoef);
	}
}
