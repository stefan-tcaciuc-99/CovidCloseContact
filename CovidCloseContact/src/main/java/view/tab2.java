package view;

import controller.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import model.Person;

public class tab2 extends Tab {
    private BorderPane root;
    private static ComboBox<Person> comboBox1, comboBox2;
    private Label contact1, contact2, date;
    private GridPane gridPane;
    private DatePicker datePicker;
    private FlowPane flowPane1, flowPane2;
    private TextField timeTxt;
    private Button recordButton;
    private ObservableList<Person> comboBoxList;

    public tab2() {
        //Initial Setup
        setText("Tab2");
        root = new BorderPane();


        //Label Setup
        contact1 = new Label("Contact1");
        contact2 = new Label("Contact2");
        date = new Label("Pick Date");

        //DatePicker Setup
        datePicker = new DatePicker();
        datePicker.setEditable(false);

        //Time Setup
        timeTxt = new TextField();
        timeTxt.setPromptText("eg.16:16");

        //Button Setup
        recordButton = new Button("Record");

        //ComboBox Setup
        comboBoxList = FXCollections.observableList(Manager.getAllPersons());
        comboBox1 = new ComboBox<>(comboBoxList);
        comboBox1.setPromptText("Select Contact");
        comboBox2 = new ComboBox<>(comboBoxList);
        comboBox2.setPromptText("Select Contact");

        //Flow Pane Setup
        flowPane1 = new FlowPane();
        flowPane2 = new FlowPane();
        flowPane1.setHgap(25);
        flowPane2.setHgap(19);
        flowPane1.getChildren().addAll(date, datePicker);

        //GridPane Setup
        gridPane = new GridPane();
        gridPane.setVgap(15);
        gridPane.setHgap(20);
        gridPane.add(contact1, 0, 1);
        gridPane.add(contact2, 0, 4);
        gridPane.add(comboBox1, 0, 2);
        gridPane.add(comboBox2, 0, 5);
        gridPane.add(flowPane1, 0, 8);
        gridPane.add(flowPane2, 0, 10);
        gridPane.add(recordButton, 0, 12);

        //RecordButton Action Event
        recordButton.setOnAction(actionEvent -> {
            if (datePicker.getValue() == null || comboBox1.getValue() == null || comboBox2.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("All fields must be entered");
                alert.show();
            } else {
                Manager.addCloseContact(comboBox1.getValue(), comboBox2.getValue(),datePicker.getValue());
                datePicker.getEditor().clear();
                datePicker.setValue(null);
                timeTxt.clear();
                comboBox1.setValue(null);
                comboBox2.setValue(null);
            }
        });
        //RefreshContactList Action Event

        //Alignment Setup
        root.setCenter(gridPane);
        root.setPadding(new Insets(20));

        setContent(root);
    }

    public static void tab2Refresh() {
        comboBox1.setItems(FXCollections.observableList(Manager.getAllPersons()));
        comboBox2.setItems(FXCollections.observableList(Manager.getAllPersons()));
    }
}
