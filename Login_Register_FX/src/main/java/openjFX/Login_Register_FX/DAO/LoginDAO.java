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

    public boolean Login (String username, String password){
        ResultSet resultSet;
        String loginQuery = "Select * from user where username =? and password =?";
        try(Connection conn = database.DbConnected();
            PreparedStatement statement = conn.prepareStatement(loginQuery)){
            statement.setString(1,username);
            statement.setString(2,password);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                logger.info("WELL");
                return true;
            }
        }catch (SQLException e){
            logger.log(Level.SEVERE,e.getMessage());
        }
    return false;
    }
}
