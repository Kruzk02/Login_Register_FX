package openjFX.Login_Register_FX;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import openjFX.Login_Register_FX.DAO.LoginDAO;

public class LoginController implements Initializable{

	@FXML
	Button registerButton,loginButton;
	@FXML
	TextField usernameField;
	@FXML
	PasswordField passwordField;

	LoginDAO loginDAO = new LoginDAO();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	public boolean isValidated() {
		if(usernameField.equals("") || usernameField.getText().length() <5) {
			
			showAlert(AlertType.ERROR, null, "ERROR", "Username or Email cannot be less than 5 ");
			usernameField.setStyle("-fx-border-color:#FF4C4C;");
			return false;
		}else {
			usernameField.setStyle("-fx-border-color:#aaa;");
		}
		if(passwordField.equals("") || passwordField.getText().length() <5) {
			showAlert(AlertType.ERROR, null,"ERROR", "Password cannot be less than 5");	
			passwordField.setStyle("-fx-border-color:#FF4C4C;");
			return false;
		}else {
			passwordField.setStyle("-fx-border-color:#aaa;");
		}

		return false;
	}
	public void Login(ActionEvent e) throws IOException {
		//this will check username and password field less than 5 char or username don't exist in MySQL
		if(!isValidated() && loginDAO.Login(usernameField.getText(),passwordField.getText())) {

			Parent root = FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));
			Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		}else {
			showAlert(AlertType.ERROR, null, "ERROR", "Invalid Username or Password");
		}
	}
	public void showAlert(Alert.AlertType alertType, Window owner , String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}
	public void SwitchScene(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
		Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

}
