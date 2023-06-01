package openjFX.Login_Register_FX;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        Scene scene = new Scene(root,450,400);
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
           
        stage.setScene(scene);
        stage.show();
        
    }
    public static void main(String[] args) {
        launch();
    }

}