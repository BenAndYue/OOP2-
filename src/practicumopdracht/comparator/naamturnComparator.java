package practicumopdracht.comparator;

import practicumopdracht.model.Player;

import java.util.Comparator;

public class naamturnComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        return o2.getNaam().compareTo(o1.getNaam());
    }

}
