package practicumopdracht.comparator;

import practicumopdracht.model.Player;

import java.util.Comparator;

public class naamComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        return o1.getNaam().compareTo(o2.getNaam());
    }

}
