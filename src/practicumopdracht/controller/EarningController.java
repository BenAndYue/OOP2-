package practicumopdracht.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import practicumopdracht.Main;
import practicumopdracht.data.EarningData;
import practicumopdracht.MainApllication;
import practicumopdracht.model.Earnings;
import practicumopdracht.model.Player;
import practicumopdracht.views.EarningView;

import java.time.LocalDate;


public class EarningController {

    private EarningView earningView;
    private EarningData earningData;
    private Scene scene;
    private PlayerController playerController;
    private Player player;


    public EarningController(PlayerController playerController) {
        this.playerController = playerController;
        earningView = new EarningView();
        earningData = new EarningData(null);
        earningView.getEditKnop().setOnAction(this::handleEdit);
        earningView.getVerwijderKnop().setOnAction(this::handleRemove);
        earningView.getGoback().setOnAction(this::handleGoBack);
        earningView.getLoadButton().setOnAction(e -> loadHandle());

        scene = new Scene(earningView.getGridPane());
        loadHandle();
        refreshData();

    }

//alleen hier om te gaan testen
    private void loadHandle() {
        earningData.leesPlayerObject();
        refreshData();
    }



    private void handleRemove(ActionEvent actionEvent) {
        Earnings selectearnings = earningView.getEarningsListView().getSelectionModel().getSelectedItem();
        earningData.remove(selectearnings);
        refreshData();
    }

    public void displayView(Player player) {
        this.player = player;
        MainApllication.setStage(scene);
    }

    private void handleEdit(ActionEvent actionEvent) {

        String waarschuwingBerichtDefault = "Er is een fout in het formulier: ";
        String waarschuwingBericht = waarschuwingBerichtDefault;


        if (earningView.getInvoerlaatstgespeeld().getValue() == null) {
            waarschuwingBericht += "\n\t-Geen datum ingevuld.";
        } else {
            LocalDate tijdNu = LocalDate.now();
            if (earningView.getInvoerlaatstgespeeld().getValue().compareTo(tijdNu) > 0) {
                waarschuwingBericht += "\n\t- Evenement kan niet in de toekomst worden gespeeld.";
            }
        }
        //geen illegale tekens
        if (earningView.getInvoervolledigenaam().getText().matches("\\d{0,7}([\\.]\\d{0,4})?")) {
            waarschuwingBericht += "\n\t-U heeft illegale tekens bij de naam staan.";
        }

        //displayed de error.
        if (!waarschuwingBericht.equals(waarschuwingBerichtDefault)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Zorg ervoor dat u alle data wel goed inzet." +"\n"
                    +waarschuwingBericht);
            alert.show();

            return;
        }

        String vollgedigeNaam = earningView.getInvoervolledigenaam().getText();
        try {

            int aantalevenementen = Integer.parseInt(earningView.getInvoerAantalevenementen().getText());
            LocalDate laatsGespeeld = earningView.getInvoerlaatstgespeeld().getValue();
            //maakt nieuwe velden aan en voegd dit vervolgens toe
            Earnings earnings = new Earnings(aantalevenementen, vollgedigeNaam, laatsGespeeld);
            earningData.add(new Earnings( aantalevenementen, vollgedigeNaam, laatsGespeeld));

            //leegt alle velden
            earningView.getInvoervolledigenaam().clear();
            earningView.getInvoerAantalevenementen().clear();
            earningView.getInvoerlaatstgespeeld().setValue(null);

        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Uw Moet de juiste gegevens gaan invullen" + ex);
            alert.show();
        }
        earningData.writePlayerObject();
        refreshData();
    }


    private void refreshData() {

        ObservableList<Earnings> EarningObservableList = FXCollections.observableList(earningData.getAll(player));
        earningView.getEarningsListView().setItems(EarningObservableList);

    }

    private void handleGoBack(ActionEvent actionEvent) {
        this.player = player;
        earningView.getEarningsListView().setItems(FXCollections.observableList(earningData.getAll(player)));
        playerController.displayView();
    }


}

