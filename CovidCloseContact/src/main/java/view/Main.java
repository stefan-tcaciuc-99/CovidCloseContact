//--module-path C:\Users\stefa\Downloads\javafx-sdk-11.0.2\lib --add-modules javafx.controls,javafx.fxml
package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage window;
    private TabPane tabPane;
    private BorderPane mainPane;
    private BorderPane root;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Variables Setup

        root = new BorderPane();
        tabPane = new TabPane();
        mainPane = new BorderPane();
        scene = new Scene(root, 500, 640);

        //Window Setup
        window = primaryStage;
        window.setTitle("COVID Contacts");
        scene.getRoot().setStyle("-fx-base:black");

        //Tabs Setup
        tabPane.getTabs().addAll(new tab1(window),new tab2(),new tab3());

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        //Alignment Set up
        mainPane.setCenter(tabPane);
        root.setCenter(mainPane);
        mainPane.prefHeightProperty().bind(scene.heightProperty());
        mainPane.prefWidthProperty().bind(scene.widthProperty());

        window.setScene(scene);
        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
