package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MUserDao extends UserDao{
    
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        Connection c = DriverManager.getConnection
                ("jdbc:mariadb://localhost:3306/springbook", "root", "root");
        return c;
    }
}
