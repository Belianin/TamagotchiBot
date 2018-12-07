package tamagotchi;

import java.util.Date;

public class DeathEvent extends Event {
    private Pet pet;
    public static int minDeathTime = 15000;

    public DeathEvent(Pet pet) {
        this.pet = pet;
        Date currentDate = new Date();
        this.when = new Date(currentDate.getTime() + minDeathTime);
    }

    @Override
    public boolean tryApply() {
    	int coef = 1;//getDeathCount();

    	pet.addHunger(-3 * coef);
    	pet.addClean(-5 * coef);
    	pet.addSleep(-2 * coef);
    	pet.addToilet(-6 * coef);
    	pet.setLastUpdate(new Date());
    	
    	when = new Date(when.getTime() + minDeathTime);
    	
    	//и тут же проверку на смерть
    	return false;
    }
    
	private int getDeathCount() {
		Date currentDate = new Date();
		long difference = currentDate.getTime() - when.getTime();
		int sec = (int) (difference / 1000);
		return sec / 10;
	}
}
