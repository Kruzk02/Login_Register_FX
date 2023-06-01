module openjFX.Login_Register_FX {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;

    opens openjFX.Login_Register_FX to javafx.fxml;
    exports openjFX.Login_Register_FX;
    exports openjFX.Login_Register_FX.MySQL;
    opens openjFX.Login_Register_FX.MySQL to javafx.fxml;
}
