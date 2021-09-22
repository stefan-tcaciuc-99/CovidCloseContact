package view;

import controller.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import model.CloseContact;
import model.Person;

public class tab3 extends Tab {
    private BorderPane root;
    private Label contact,date;
    private FlowPane flowPane;
    private Button listButton,findButton;
    private GridPane gridPane;
    private TableView tableView;
    private TableColumn firstNameCol, lastNameCol,middleNameCol,phoneCol,emailCol,idCol,closeContactId,dateCol;
    private TextField contactTxt;
    private FlowPane flowPane1;
    private DatePicker datePicker;

    public tab3() {
        //TAB3
        setText("Tab3");
        root = new BorderPane();
        //Label Setup
        contact = new Label("Enter Person ID");
        contactTxt = new TextField();
        //FlowPane Setup
        flowPane = new FlowPane();
        flowPane.setHgap(19);
        flowPane.getChildren().addAll(contact);
        //Button Setup
        findButton = new Button("Find Person");
        listButton = new Button("List Close Contact");

        datePicker = new DatePicker();
        datePicker.setEditable(false);
        date = new Label("View Close Contacts Since");

        flowPane1 = new FlowPane();
        flowPane1.setHgap(25);
        flowPane1.getChildren().addAll(date, datePicker);

        //DatePicker Setup

        //GridPane Setup
        gridPane = new GridPane();
        gridPane.setVgap(15);
        gridPane.setHgap(20);
        gridPane.add(contact, 0, 0);
        gridPane.add(contactTxt, 0, 2);
        gridPane.add(findButton, 0, 4);
        gridPane.add(listButton, 0, 8);
        gridPane.add(flowPane1,0,6);

        //List Button Action
        findButton.setOnAction(actionEvent -> {
//                  //Table Area Setup
                    ObservableList<Person> data = FXCollections.observableArrayList(Manager.getPerson(Integer.parseInt(contactTxt.getText())));

                    tableView = new TableView(data);
                    tableView.setEditable(false);
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


                    tableView.getColumns().addAll(idCol, firstNameCol, middleNameCol, lastNameCol, phoneCol, emailCol);
                    tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    tableView.setMaxHeight(50);

                    //Add TableView to GridPane
                    gridPane.add(tableView, 0, 3);

                }

        );
        listButton.setOnAction(actionEvent -> {
//                  //Table Area Setup
                    ObservableList<CloseContact> data = FXCollections.observableArrayList(Manager.getContacts(Integer.parseInt(contactTxt.getText()),datePicker.getValue()));

                    tableView = new TableView(data);
                    tableView.setEditable(false);
                    //Table Column Setup
                    closeContactId = new TableColumn("Close Contact ID");
                    idCol = new TableColumn("Person ID");
                    dateCol = new TableColumn("Date");

                    String string =Manager.selectID(data,Integer.parseInt(contactTxt.getText()));
                    closeContactId.setCellValueFactory(new PropertyValueFactory<CloseContact, String>("closeContactID"));
                    idCol.setCellValueFactory(new PropertyValueFactory<CloseContact, String>(string));
                    dateCol.setCellValueFactory(new PropertyValueFactory<CloseContact, String>("dateContact"));

                    tableView.getColumns().addAll(closeContactId,idCol,dateCol);
                    tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    tableView.setMaxHeight(200);

                    //Add TableView to GridPane
                    gridPane.add(tableView, 0, 9);
                    tableView.refresh();


                }

        );
        //Alignment Setup
        root.setCenter(gridPane);
        root.setPadding(new Insets(20));

        setContent(root);
    }

}
