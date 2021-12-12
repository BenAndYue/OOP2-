package practicumopdracht.data;

import practicumopdracht.controller.PlayerController;
import practicumopdracht.model.Earnings;
import practicumopdracht.model.Player;

import java.io.*;
import java.util.ArrayList;

public class EarningData {


    private ArrayList<Earnings> earningList;
    private PlayerController playerController;

    public EarningData(ArrayList<Earnings> earningList) {
        this.earningList = earningList;
        earningList = new ArrayList<>();
        load();
    }

    public ArrayList<Earnings> getAll(Player player) {
        ArrayList<Earnings> list = new ArrayList<>();
        for (Earnings earnings : earningList) if (earnings.getPlayer() == player) list.add(earnings);
        return list;
    }

    public void add(Earnings earnings) {
        earningList.add(earnings);
    }


    public void remove(Earnings earnings) {
        earningList.remove(earnings);

    }


    public boolean load() {
        earningList = new ArrayList<Earnings>();
        return false;
    }

    //save
    public void writePlayerObject() {

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Earning.Object"));
           for (Earnings earnings : earningList) objectOutputStream.writeObject(earnings);
        } catch (Exception ex) {
            System.out.println("Oeps iets ging niet goed." + ex);
        }
    }

    //load
    public void leesPlayerObject() {
        try {

            FileInputStream fi = new FileInputStream(new File("Earning.Object"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            earningList = (ArrayList<Earnings>) oi.readObject();

            Earnings earnings = (Earnings) oi.readObject();
            earnings.setPlayer(playerController.lookup(earnings.getPlayer().getNaam()));
            earningList.add(earnings);

            oi.close();
            fi.close();

        } catch (EOFException ingnore) {
        } catch (Exception ex) {
            System.out.println("Oeps iets ging niet goed. \n" + ex);
        }
    }
}

