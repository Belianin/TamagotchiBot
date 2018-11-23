package tamgochy;
import java.util.Date;

public class Death extends Event {
    private Bot bot;
    private int coef;


    public Death(Bot bot, String id, int coef) {
        super();
        this.bot = bot;
        this.Who = id;
        this.coef = coef;
        this.When = new Date();
        this.What = "Death";
    }

    void apply() {
        //System.out.println(Who + " || " + "Смерть");
        Pet need = bot.getTamagochyMap().get(Who);
        //System.out.println(need.getStates());
        need.addHunger(-3 * coef);
        need.addFun(-3 * coef);
        need.addClean(-5 * coef);
        need.addSleep(-2 * coef);
        need.addToilet(-6 * coef);
        need.setLastUpdate(new Date());
        // зачем? это же сслычоный тип bot.putTamagochi(Who, need);

    }
}
