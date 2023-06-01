package openjFX.Login_Register_FX.DAO;

import openjFX.Login_Register_FX.MySQL.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDAO {
    private static final Logger logger = Logger.getLogger(LoginDAO.class.getName());

    //this Method for Login only
    public boolean Login (String username, String password){
        String loginQuery = "Select * from user where username =? and password =?";
        try(Connection conn = database.DbConnected();
            PreparedStatement statement = conn.prepareStatement(loginQuery)){
            statement.setString(1,username);
            statement.setString(2,password);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    logger.info("WELL");
                    return true;}
            }
        }catch (SQLException e){
            logger.log(Level.SEVERE,e.getMessage());
        }
    return false;
    }
}
