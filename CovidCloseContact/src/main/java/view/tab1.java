package view;

import Popup.ExitPopUp;
import controller.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Person;

public class tab1 extends Tab {
    private Label contacts, fName, mName, lName, id, phone, email;
    private TextField fNameTxt, lNameTxt, idTxt, phoneTxt, emailTxt, mNameTxt;
    private Button add, remove, list, save, exit, load, loop;
    private TableView table;
    private TableColumn firstNameCol, lastNameCol, middleNameCol, phoneCol, emailCol, idCol;
    private GridPane gridPane;
    private VBox root;
    private HBox title;
    private FlowPane flowPane1, flowPane2;

    public tab1(Stage window) {
        //Initial Setup
        ObservableList<Person> data = FXCollections.observableArrayList(Manager.getAllPersons());
        ExitPopUp testing = new ExitPopUp();
        setText("Tab1");
        //Labels Setup
        contacts = new Label("Contacts");
        fName = new Label("Enter First Name");
        lName = new Label("Enter Last Name");
        mName = new Label("Enter Middle Name");
        id = new Label("Enter Unique ID");
        phone = new Label("Enter Phone Number ");
        email = new Label("Enter Email ");

        //Text Fields Setup
        fNameTxt = new TextField();
        lNameTxt = new TextField();
        mNameTxt = new TextField();
        idTxt = new TextField();
        phoneTxt = new TextField();
        emailTxt = new TextField();
        //Buttons Setup
        add = new Button("Add");
        remove = new Button("Remove");
        list = new Button("List");
        save = new Button("Save");
        exit = new Button("Exit");
        load = new Button("Load");
        loop = new Button("Loop");

        //Table Area Setup
        table = new TableView(data);
        table.setEditable(false);
        //Table Column Setup
        idCol = new TableColumn("Person ID");
        firstNameCol = new TableColumn("First Name");
        middleNameCol = new TableColumn("Middle Name");
        lastNameCol = new TableColumn("Last Name");
        phoneCol = new TableColumn("Phone");
        emailCol = new TableColumn("Email");

        idCol.setCellValueFactory(new PropertyValueFactory<Person, String>("personID"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        middleNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("middleName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));


        table.getColumns().addAll(idCol, firstNameCol, middleNameCol, lastNameCol, phoneCol, emailCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Add Button Action //Contact Object Creation Action
        add.setOnAction(actionEvent ->
        {
            if (fNameTxt.getText().isEmpty() || lNameTxt.getText().isEmpty() || idTxt.getText().isEmpty() || phoneTxt.getText().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("All fields must be entered");
                a.show();
            } else {
                Manager.addPerson(Integer.parseInt(idTxt.getText()), fNameTxt.getText(), mNameTxt.getText(), lNameTxt.getText(), phoneTxt.getText(), emailTxt.getText());
                idTxt.clear();
                fNameTxt.clear();
                mNameTxt.clear();
                lNameTxt.clear();
                phoneTxt.clear();
                emailTxt.clear();
                tab2.tab2Refresh();
            }
        });

        //List Button Action // Show Existing Contacts
        list.setOnAction(actionEvent -> {
            ObservableList<Person> refresh = FXCollections.observableArrayList(Manager.getAllPersons());
            table.setItems(refresh);
            table.refresh();
            tab2.tab2Refresh();


        });
        //Remove Button Action // Remove Contact
        remove.setOnAction(actionEvent -> {
            Label removeIdLabel = new Label("Enter ID");
            TextField removeTextField = new TextField();
            Button removeRemoveButton = new Button("REMOVE");
            Button removeBackButton = new Button("BACK");

            //Scene Setup
            Stage removePopUp = new Stage();
            removePopUp.initModality(Modality.APPLICATION_MODAL);
            removePopUp.initOwner(window);

            HBox removeBox = new HBox(10);
            removeBox.getChildren().addAll(removeIdLabel, removeTextField, removeRemoveButton, removeBackButton);
            removeBox.setPadding(new Insets(10));

            Scene removeScene = new Scene(removeBox, 350, 50);
            removeScene.getRoot().setStyle("-fx-base:black");
            removePopUp.setScene(removeScene);
            removePopUp.show();

            //Back Action // Close Window And Return To Main Application
            removeBackButton.setOnAction(actionEvent1 -> {
                removePopUp.close();
            });

            //Remove Action // Delete Contact And Return To Main Application
            removeRemoveButton.setOnAction(actionEvent2 -> {
                tab2.tab2Refresh();
                Manager.deletePerson(Integer.parseInt(removeTextField.getText()));
                removePopUp.close();
            });
        });
        save.setOnAction(actionEvent -> Manager.save());
        load.setOnAction(actionEvent -> Manager.load());
        loop.setOnAction(actionEvent -> Manager.loop());

        //Exit Button Action // Exit Application
        exit.setOnAction(actionEvent -> {
            if (testing.ExitPopUp(window)){Manager.save();}
        });

        //Contents Setup
        root = new VBox(10);
        title = new HBox(10);
        title.getChildren().addAll(contacts);

        flowPane1 = new FlowPane();
        flowPane1.setHgap(19);
        flowPane1.getChildren().addAll(add, remove, list);

        flowPane2 = new FlowPane();
        flowPane2.setHgap(19);
        flowPane2.getChildren().addAll(save, load,loop);

        gridPane = new GridPane();
        gridPane.setVgap(15);
        gridPane.setHgap(20);
        gridPane.add(fName, 0, 0);
        gridPane.add(fNameTxt, 1, 0);
        gridPane.add(mName, 0, 1);
        gridPane.add(mNameTxt, 1, 1);
        gridPane.add(lName, 0, 2);
        gridPane.add(lNameTxt, 1, 2);
        gridPane.add(id, 0, 3);
        gridPane.add(idTxt, 1, 3);
        gridPane.add(phone, 0, 4);
        gridPane.add(phoneTxt, 1, 4);
        gridPane.add(email, 0, 5);
        gridPane.add(emailTxt, 1, 5);


        gridPane.add(flowPane1, 0, 6, 2, 1);
        gridPane.add(table, 0, 7, 2, 1);
        gridPane.add(flowPane2, 0, 8, 2, 1);
        gridPane.add(exit, 1, 9);

        //Alignment Setup
        GridPane.setHalignment(exit, HPos.RIGHT);
        root.setPadding(new Insets(20));
        title.setAlignment(Pos.CENTER);

        root.getChildren().addAll(title, gridPane);
        setContent(root);
    }

}
