package practicumopdracht.comparator;
import practicumopdracht.model.Player;
import java.util.Comparator;

public class leeftijdComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
     return o1.getGeboorteDatum().compareTo(o2.getGeboorteDatum());
    }

}