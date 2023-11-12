import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/test_jdbc";
    static final String DB_Driver = "org.postgresql.Driver";


    public static java.sql.Connection connectionToBD()  {
        try {
            Class.forName( DB_Driver ); //Проверяем наличие JDBC драйвера для работы с БД

        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection( DB_URL, "postgres", "root" );
        } catch ( SQLException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
        }

        return  connection;
    }
}
