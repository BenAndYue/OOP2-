package practicumopdracht.views;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import practicumopdracht.model.Earnings;
import practicumopdracht.model.Player;

public class EarningView {
    private TextField invoerAantalevenementen;
    private TextField invoervolledigenaam;
    private DatePicker invoerlaatstgespeeld;
    private Button editKnop;
    private Button verwijderKnop;
    private ListView<Earnings> earningsListView;
    private Button goback;
    private GridPane gridPane;

    private Button loadButton;


    public EarningView() {
        this.invoerAantalevenementen = new TextField("0");
        this.invoervolledigenaam = new TextField("");
        this.invoerlaatstgespeeld = new DatePicker();
        this.editKnop = new Button("Toevoegen");
        this.verwijderKnop = new Button("Verwijder");
        this.earningsListView = new ListView<>();
        this.goback = new Button("Ga terug");
        this.gridPane = new GridPane();
        this.loadButton = new Button("Laad");
        schermlayout();
    }
    private void schermlayout() {
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        Label lastlabel = new Label("Laatste event: ");
        Label playerfullnameLabel = new Label("Hele naam ");
        Label aantalevents = new Label("Aantal evenementen ");

        gridPane.add(goback,0,0);
        gridPane.add(invoerAantalevenementen,3,1);
        gridPane.add(invoervolledigenaam,3,2);
        gridPane.add(invoerlaatstgespeeld,3,3);
        gridPane.add(lastlabel, 2, 3);
        gridPane.add(playerfullnameLabel, 2, 2);
        gridPane.add(aantalevents, 2, 1);
        gridPane.add(loadButton,0,2);
        gridPane.add(earningsListView,1,1,1,6);
        gridPane.add(verwijderKnop,3,4);
        gridPane.add(editKnop,3,5);

    }

    public TextField getInvoerAantalevenementen() {
        return invoerAantalevenementen;
    }

    public TextField getInvoervolledigenaam() {
        return invoervolledigenaam;
    }

    public DatePicker getInvoerlaatstgespeeld() {
        return invoerlaatstgespeeld;
    }

    public Button getEditKnop() {
        return editKnop;
    }

    public Button getVerwijderKnop() {
        return verwijderKnop;
    }

    public ListView<Earnings> getEarningsListView() {
        return earningsListView;
    }

    public Button getGoback() {
        return goback;
    }

    public GridPane getGridPane() {
        return gridPane;

    }

    public Button getLoadButton() {
        return loadButton;
    }


}
