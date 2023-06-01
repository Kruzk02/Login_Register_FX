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
import openjFX.Login_Register_FX.DAO.RegisterDAO;
import openjFX.Login_Register_FX.DAO.User;
import openjFX.Login_Register_FX.MySQL.database;

public class RegisterController implements Initializable{

	@FXML
	TextField usernameField,emailField;
	@FXML
	PasswordField passwordField;
	@FXML
	Button signupButton,loginButton;

	RegisterDAO registerDAO = new RegisterDAO();
	static Logger logger = Logger.getLogger(RegisterController.class.getName());
	@Override
	public void initialize(URL location, ResourceBundle resources) {}
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
		if(registerDAO.checkUsers(usernameField.getText(),emailField.getText())){
			showAlert(AlertType.ERROR,null,"ERROR","Username or email already take");}
		return false;
		
	}

	public void SignUp() throws SQLException {
		// this will check all field have more than 5 char and Email
		if(!isvalidate() && Email(emailField.getText())) {
			User user = this.userObject(usernameField.getText(), emailField.getText(), passwordField.getText());
			if (registerDAO.saveUser(user)>0)
				showAlert(AlertType.INFORMATION, null, "INFORMATION", "Success");
		}
	}
	public User userObject(String username,String email,String password){
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);

		return user;
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
