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
        need.Down("Питание", 3 * coef);
        need.Down("Сон", 2 * coef);
        need.Down("Гигиена", 5 * coef);
        need.Down("Счастье", 3 * coef);
        need.Down("Туалет", 6 * coef);
        need.setLastUpdate(new Date());
        bot.setTamagochyMap(Who, need);

    }
}
