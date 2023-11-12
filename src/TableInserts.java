import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableInserts {

    private final Connection connection;

    TableInserts(Connection _connection){
        this.connection = _connection;
    }

    public void insertDays(String input) throws SQLException {

        String insertDays =
                "INSERT INTO Days VALUES (?)";

        PreparedStatement statement = connection.prepareStatement(insertDays);
        statement.setString( 1, input );
        statement.executeUpdate(  );
    }

    public void insertClassrooms(int input) throws SQLException {
        String insertClassrooms =
                "INSERT INTO Classrooms (classroom_number) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement( insertClassrooms );
        statement.setInt( 1, input );
        statement.executeUpdate();
    }

    public void insertSubjects(String input) throws SQLException {
        String insertSubjects =
                "INSERT INTO Subjects ( name ) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement( insertSubjects );
        statement.setString( 1, input );
        statement.executeUpdate();
    }

    public void insertGroups(String firstPart, int secondPart) throws SQLException {
        String insertSubjects =
                "INSERT INTO Groups (name, number) VALUES (?,?)";
        PreparedStatement statement = connection.prepareStatement( insertSubjects );
        statement.setString( 1, firstPart );
        statement.setInt( 2, secondPart );
        statement.executeUpdate();
    }

    public void insertTeachers(String firstName, String secondName) throws SQLException {
            String insertSubjects =
                    "INSERT INTO Teachers (first_name, last_name) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement( insertSubjects );
            statement.setString( 1, firstName );
            statement.setString( 2, secondName );
            statement.executeUpdate();
    }

    public void insertLessons(
            String teacherFirstName, String teacherSecondName,
            int classroom,
            String subject,
            String groupFirstPart, int groupSecondPart,
            String day
                                ) throws SQLException {

        String insertLessons =
                "INSERT INTO Lessons " +
                        "(id_teacher, id_classroom, id_subject, id_group, day) " +
                        "VALUES (" + "?,?,?,?,?" +
//                            "SELECT id FROM Teachers WHERE first_name = (?) AND last_name = (?)," +
//                            "SELECT id FROM Classrooms WHERE classroom_number = (?)," +
//                            "SELECT id FROM Subjects WHERE name = (?)," +
//                            "SELECT id FROM Groups WHERE name = (?) AND number = (?)," +
//                            "(?)" +
                        ")";
        PreparedStatement statement = connection.prepareStatement( insertLessons );
        statement.setLong( 1, getId( "Teachers", "first_name",teacherFirstName,"last_name", teacherSecondName) );
        statement.setLong( 2, getId( "Classrooms", "classroom_number", classroom));
        statement.setLong( 3, getId( "Subjects", "name", subject));
        statement.setLong( 4, getId( "Groups", "name",groupFirstPart,"number", groupSecondPart) );
        statement.setString( 5, day );

        System.out.println( " execute update = " + statement.executeUpdate());

    }

    public void insertLessonsRandom() throws SQLException {

        String insertLessons =
                "INSERT INTO Lessons " +
                        "(id_teacher, id_classroom, id_subject, id_group, day) " +
                        "VALUES (" + "?,?,?,?,?" +
                        ")";
        PreparedStatement statement = connection.prepareStatement( insertLessons );
        statement.setLong( 1, (int) ( Math.random() * 9 + 1 ) );
        statement.setLong( 2, (int) ( Math.random() * 5 + 1 ));
        statement.setLong( 3, (int) ( Math.random() * (19 - 13) + 13 ));
        statement.setLong( 4, (int) ( Math.random() * 6 + 1 ));
        statement.setString( 5, randomDay() );

        System.out.println( " execute update = " + statement.executeUpdate());

    }

    public static String randomDay(){

        int a = (int) ( Math.random() * 7 + 1 );

        switch (a) {
            case 1 -> {
                return "Monday";
            }
            case 2 -> {
                return "Tuesday";
            }
            case 3 -> {
                return "Wednesday";
            }
            case 4 -> {
                return "Thursday";
            }
            case 5 -> {
                return "Friday";
            }
            case 6 -> {
                return "Saturday";
            }
            case 7 -> {
                return "Sunday";
            }
        }
        return null;
    }

    public long getId(String table, String column1, String par1, String column2, String par2) throws SQLException{

        long id = 0;
        try(Connection connection = MyConnection.connectionToBD()) {

            String insert =
                    "SELECT id FROM " + table + " WHERE ( " + column1 + " = ? ) AND ( " + column2 + " = ?)";
            PreparedStatement statement = connection.prepareStatement( insert );
            statement.setString( 1, par1 );
            statement.setString( 2, par2 );

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                id = resultSet.getLong("id");
            }
        }
        return id;
    }

    public long getId(String table, String column1, String par1, String column2, Integer par2) throws SQLException{

        long id = 0;
        try(Connection connection = MyConnection.connectionToBD()) {

            String insert =
                    "SELECT id FROM " + table + " WHERE ( " + column1 + " = ? ) AND ( " + column2 + " = ?)";
            PreparedStatement statement = connection.prepareStatement( insert );
            statement.setString( 1, par1 );
            statement.setInt( 2, par2 );

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                id = resultSet.getLong("id");
            }
        }
        return id;
    }
    public long getId(String table, String column1, String par1) throws SQLException{

        long id = 0;
        try(Connection connection = MyConnection.connectionToBD()) {

            String insert =
                    "SELECT id FROM " + table + " WHERE ( " + column1 + " = ? )";
            PreparedStatement statement = connection.prepareStatement( insert );
            statement.setString( 1, par1 );

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                id = resultSet.getLong("id");
            }
        }
        return id;
    }

    public long getId(String table, String column1, Integer par1) throws SQLException{

        long id = 0;
        try(Connection connection = MyConnection.connectionToBD()) {

            String insert =
                    "SELECT id FROM " + table + " WHERE ( " + column1 + " = ? )";
            PreparedStatement statement = connection.prepareStatement( insert );
            statement.setInt( 1, par1 );

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                id = resultSet.getLong("id");
            }
        }
        return id;
    }

}
