package openjFX.Login_Register_FX.DAO;

import openjFX.Login_Register_FX.MySQL.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RegisterDAO {
    List<User> userList = new ArrayList<>();

    private static final Logger logger = Logger.getLogger(RegisterDAO.class.getName());

    public boolean checkUsers(String username, String email) {
        String checkQuery = "SELECT * from user where username =? OR email =?";
        boolean checkuser = false;
        try (Connection conn = database.DbConnected();
             PreparedStatement statement = conn.prepareStatement(checkQuery)) {

            statement.setString(1, username);
            statement.setString(2, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    checkuser = true;

                    User user = new User();
                    user.setId(resultSet.getInt(1));
                    user.setUsername(resultSet.getString(2));
                    user.setEmail(resultSet.getString(3));
                    user.setPassword(resultSet.getString(4));
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Problem at checkUsers in RegisterDAO: " + e.getMessage());
        }
        return checkuser;
    }

    public int saveUser(User user) throws SQLException {
        String query = "INSERT INTO user(username, email, password) VALUES(?, ?, ?)";

        try(Connection conn = database.DbConnected();
            PreparedStatement statement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1,user.getUsername());
            statement.setString(2,user.getEmail());
            statement.setString(3,user.getPassword());
            statement.executeUpdate();

            try(ResultSet resultSet = statement.getGeneratedKeys()){
                if(resultSet.next()) return resultSet.getInt(1);
            }
        }catch (SQLException e){
            logger.log(Level.SEVERE,e.getMessage());
        }
        return 0;
    }

}
