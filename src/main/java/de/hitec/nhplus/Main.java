package de.hitec.nhplus;

import de.hitec.nhplus.datastorage.ConnectionBuilder;

import de.hitec.nhplus.presenter.UserLoginPresenter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        startUpMessage();
        loginWindow();
    }

    public void loginWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/UserLoginView.fxml"));
            AnchorPane pane = loader.load();

            UserLoginPresenter presenter = loader.getController();
            presenter.setMainApp(this);

            Scene scene = new Scene(pane);
            this.primaryStage.setTitle("NHPlus");
            this.primaryStage.setScene(scene);
            this.primaryStage.setResizable(false);
            this.primaryStage.show();

            this.primaryStage.setOnCloseRequest(event -> {
                ConnectionBuilder.closeConnection();
                Platform.exit();
                System.exit(0);
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void mainWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/MainWindowView.fxml"));
            BorderPane pane = loader.load();

            Scene scene = new Scene(pane);
            this.primaryStage.setTitle("NHPlus");
            this.primaryStage.setScene(scene);
            this.primaryStage.setResizable(false);
            this.primaryStage.show();

            this.primaryStage.setOnCloseRequest(event -> {
                ConnectionBuilder.closeConnection();
                Platform.exit();
                System.exit(0);
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void startUpMessage(){
        System.out.println("  _   _ _     _____  _              _____ _             _           _ \n" +
                " | \\ | | |   |  __ \\| |            / ____| |           | |         | |\n" +
                " |  \\| | |__ | |__) | |_   _ ___  | (___ | |_ __ _ _ __| |_ ___  __| |\n" +
                " | . ` | '_ \\|  ___/| | | | / __|  \\___ \\| __/ _` | '__| __/ _ \\/ _` |\n" +
                " | |\\  | | | | |    | | |_| \\__ \\  ____) | || (_| | |  | ||  __/ (_| |\n" +
                " |_| \\_|_| |_|_|    |_|\\__,_|___/ |_____/ \\__\\__,_|_|   \\__\\___|\\__,_|\n" +
                "                                                                      \n" +
                "                                                                      ");
    }
}