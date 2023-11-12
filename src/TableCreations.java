import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreations {

    public static void createDB() {

        String creationTableTeachers =
                "CREATE TABLE Teachers ( " +
                        "id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
                        "first_name VARCHAR(20) NOT NULL," +
                        "last_name VARCHAR(20) NOT NULL" +
                        ")" +
                        "WITH (  OIDS=FALSE)";

        String creationTableSubjects =
                "CREATE TABLE Subjects ( " +
                        "id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
                        "name VARCHAR(20) NOT NULL" +
                        ")" +
                        "WITH (  OIDS=FALSE)";

        String creationTableDays =
                "CREATE TABLE Days ( " +
                        "name VARCHAR PRIMARY KEY" +
                        ")" +
                        "WITH (  OIDS=FALSE)";

        String creationTableClassrooms =
                "CREATE TABLE Classrooms ( " +
                        "id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
                        "classroom_number INTEGER NOT NULL" +
                        ")" +
                        "WITH (  OIDS=FALSE)";

        String creationTableGroups =
                "CREATE TABLE Groups ( " +
                        "id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY," +
                        "name VARCHAR(20) NOT NULL," +
                        "number INTEGER NOT NULL" +
                        ")" +
                        "WITH (  OIDS=FALSE)";

        String creationTableLessons =
                "CREATE TABLE Lessons ( " +
                        "id_teacher BIGINT REFERENCES Teachers," +
                        "id_classroom BIGINT NOT NULL REFERENCES Classrooms," +
                        "id_subject BIGINT NOT NULL REFERENCES Subjects," +
                        "id_group BIGINT NOT NULL REFERENCES Groups," +
                        "day VARCHAR NOT NULL REFERENCES Days" +
                        ")" +
                        "WITH (  OIDS=FALSE)";

        updateTable( creationTableTeachers );
        updateTable( creationTableSubjects );
        updateTable( creationTableDays );
        updateTable( creationTableClassrooms );
        updateTable( creationTableGroups );
        updateTable( creationTableLessons );

    }

    public static void updateTable(String s) {
        
        try(Connection conn = MyConnection.connectionToBD()){
            Statement statement = conn.createStatement();
            statement.executeUpdate( s );
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println( "Error SQL ");
        }
    }
}
