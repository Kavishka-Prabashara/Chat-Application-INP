package lk.playtech.chatapplicationinp.model;

import lk.playtech.chatapplicationinp.db.DBConnection;
import lk.playtech.chatapplicationinp.dto.UserDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginModel {
    public static boolean saveUser(UserDTO dto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO user_info VALUES(?)";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, dto.gettxtUserName());
            return pstm.executeUpdate() > 0;
        }

    }
}
