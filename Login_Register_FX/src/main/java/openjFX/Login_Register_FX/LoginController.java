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

public class LoginController implements Initializable{

	@FXML
	Button registerButton,loginButton;
	@FXML
	TextField usernameField;
	@FXML
	PasswordField passwordField;
	
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
	public void Login() {

		PreparedStatement pState = null;
		ResultSet rSet = null;
		String query = "Select * from users where username =? and password =? ";
		if(!isValidated()) {
			try {
				pState = database.DbConnected().prepareStatement(query);
				pState.setString(1, usernameField.getText());
				pState.setString(2, passwordField.getText());
				rSet = pState.executeQuery();
				if(rSet.next()) {
					Stage stage = (Stage) loginButton.getScene().getWindow();
					stage.close();
					
					Parent root = FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));
					Scene scene = new Scene(root);
					
					stage.setScene(scene);
					stage.show();
					
				}else {
					showAlert(AlertType.ERROR, null, "ERROR", "Invalid Username or Password");
				}
			}catch (Exception e) {
				System.err.println(e.getMessage());
			}
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
