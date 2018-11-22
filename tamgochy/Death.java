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
        System.out.println(Who + " || " + "Смерть");
        Pet need = bot.getTamagochyMap().get(Who);
        System.out.println(need.getStates());
        need.Down("hunger", 3 * coef);
        need.Down("sleep", 2 * coef);
        need.Down("clean", 5 * coef);
        need.Down("fun", 3 * coef);
        need.Down("toilet", 6 * coef);
        need.setLastUpdate(new Date());
        bot.putTamagochi(Who, need);

    }
}
