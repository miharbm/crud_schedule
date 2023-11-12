import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TableSelects {

    public static void infoTeachersThatWorksOnGivenDay()  {

        System.out.print("Day: ");
        String day = new Scanner( System.in ).nextLine().strip();

        System.out.print("Class: ");
        int classroom = new Scanner( System.in ).nextInt();

        String selectStmt =
                "SELECT Teachers.first_name, Teachers.last_name " +
                        "FROM Lessons " +
                        "JOIN Teachers " +
                        "ON Lessons.id_teacher = Teachers.id " +
                        "JOIN Classrooms " +
                        "ON Lessons.id_classroom = Classrooms.id " +
                        "WHERE Lessons.day = ? AND Classrooms.classroom_number = ?";


        try(Connection connection = MyConnection.connectionToBD()) {

            PreparedStatement statement = connection.prepareStatement( selectStmt );
            statement.setString( 1, day );
            statement.setInt( 2, classroom );

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                String firstName = resultSet.getString("first_name");
                String lastName  = resultSet.getString("last_name");
                System.out.println(firstName + " " + lastName);
            }
        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
    }

    public static void infoTeachersThatDontWorksOnGivenDay()  {

        System.out.print("Day: ");
        String day = new Scanner( System.in ).nextLine().strip();

        String selectStmt =
                "SELECT DISTINCT Teachers.first_name, Teachers.last_name " +
                        "FROM Teachers " +
                        "LEFT JOIN Lessons " +
                        "ON Lessons.id_teacher = Teachers.id " +
                        "WHERE Teachers.id NOT IN (SELECT Lessons.id_teacher " +
                        "FROM Lessons " +
                        "WHERE Lessons.day = ?)";


        try(Connection connection = MyConnection.connectionToBD()) {

            PreparedStatement statement = connection.prepareStatement( selectStmt );
            statement.setString( 1, day );
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                String firstName = resultSet.getString("first_name");
                String lastName  = resultSet.getString("last_name");
                System.out.println(firstName + " " + lastName);
            }
        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
    }

    public static void dayWithSameLessons()  {

        System.out.print("Number: ");
        int number = new Scanner( System.in ).nextInt();


        String selectStmt =
                "SELECT day " +
                        "FROM Lessons " +
                        "GROUP BY day " +
                        "HAVING COUNT(day) = ?";

        try(Connection connection = MyConnection.connectionToBD()) {

            PreparedStatement statement = connection.prepareStatement( selectStmt );
            statement.setInt( 1, number );
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                String day = resultSet.getString("day");
                System.out.println(day);
            }
        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
    }

}
