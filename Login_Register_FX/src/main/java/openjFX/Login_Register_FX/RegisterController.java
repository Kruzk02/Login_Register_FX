package openjFX.Login_Register_FX;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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

public class RegisterController implements Initializable{

	@FXML
	TextField usernameField,emailField;
	@FXML
	PasswordField passwordField;
	@FXML
	Button signupButton,loginButton;
	
	static Logger logger = Logger.getLogger(RegisterController.class.getName());
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

	}
	
	public boolean checkUsers(String username,String email) throws SQLException {
		boolean checkUsers = false;
		
		PreparedStatement pState = null;
		ResultSet rSet = null;
		String query = "SELECT email, username FROM users WHERE username = ? OR email = ?";
		try {
			pState = database.DbConnected().prepareStatement(query);
			pState.setString(1, username);
			pState.setString(2, email);
			
			rSet = pState.executeQuery();
			if(rSet.next()) {
				checkUsers = true;
			}
		}catch (SQLException e) {
			logger.log(Level.SEVERE,e.getMessage());
		}finally {
			if(pState != null) {
				pState.close();
			}
			if(rSet != null) {
				rSet.close();
			}
		}
		
		 return checkUsers;
	}
	public boolean Email(String email) {
		 String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
	     return email.matches(emailPattern);
	}

	public boolean isvalidate() throws SQLException {

		
		if(usernameField.equals("") || usernameField.getText().length() <5) {
			
			showAlert(AlertType.ERROR, null, "ERROR", "Username cannot be less than 5 ");
			usernameField.setStyle("-fx-border-color:#FF4C4C;");
			return false;
		}else {
			usernameField.setStyle("-fx-border-color:#aaa;");
		}
		
		if(!Email(emailField.getText())) {
			showAlert(AlertType.ERROR, null,"ERROR", "Invalid Email");	
			emailField.setStyle("-fx-border-color:#FF4C4C;");
			return false;
		}else {
			emailField.setStyle("-fx-border-color:#aaa;");
		}

		if(passwordField.equals("") || passwordField.getText().length() <5) {
			showAlert(AlertType.ERROR, null,"ERROR", "Password cannot be less than 5");	
			passwordField.setStyle("-fx-border-color:#FF4C4C;");
			return false;
		}else {
			passwordField.setStyle("-fx-border-color:#aaa;");
		}
		if(checkUsers(usernameField.getText(), emailField.getText())) {
			showAlert(AlertType.ERROR, null, "ERROR", "Username or Email already exists");
		}else {
			showAlert(AlertType.INFORMATION, null, "Information", "You success create an account");
		}
		return false;
		
	}
	
	public void SignUp() throws SQLException {
		PreparedStatement pState = null;
		String query = "Insert into users (username,email,password) Values(?,?,?)";
		
		if(!isvalidate() && Email(emailField.getText()) ) {
			try {
				pState = database.DbConnected().prepareStatement(query);
			
				pState.setString(1, usernameField.getText());
				pState.setString(2, emailField.getText());
				pState.setString(3, passwordField.getText());
			
				pState.executeUpdate();
			}catch (SQLException e) {
				logger.log(Level.SEVERE,e.getMessage());
			}finally {
				if(pState != null) {
					pState.close();
			}
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
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

}
