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


/**
 * The main class of the NHPlus application. <br>
 * <br>
 * It launches the JavaFX application and manages the startup, login, and main window scenes.
 */
public class Main extends Application {

    /**
     * The primary stage (main window) of the application.
     */
    private Stage primaryStage;

    /**
    * The application's entry point. Launches the JavaFX framework.
    */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Called by the JavaFX framework when the application is ready to start.
     * Initializes the primary stage and shows the login window.
     *
     * @param primaryStage the primary window (stage) of the JavaFX application
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        startUpMessage();
        loginWindow();
    }

    /**
     * Displays the login window using the <code>UserLoginView.fxml</code> layout.
     * Initializes the corresponding presenter and configures the window.
     */
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

    /**
     * Displays the main application window using the <code>MainWindowView.fxml</code> layout.
     */
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
                closeMessage();
                ConnectionBuilder.closeConnection();
                Platform.exit();
                System.exit(0);
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Prints a startup ASCII message to the console when the application starts.
     */
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

    /**
     * Prints a goodbye ASCII message to the console when the application closes.
     */
    private void closeMessage(){
        System.out.println("\n" +
                "\n" +
                "  ____               ____             \n" +
                " |  _ \\             |  _ \\            \n" +
                " | |_) |_   _  ___  | |_) |_   _  ___ \n" +
                " |  _ <| | | |/ _ \\ |  _ <| | | |/ _ \\\n" +
                " | |_) | |_| |  __/ | |_) | |_| |  __/\n" +
                " |____/ \\__, |\\___| |____/ \\__, |\\___|\n" +
                "         __/ |              __/ |     \n" +
                "        |___/              |___/      \n" +
                "\n");
    }
}