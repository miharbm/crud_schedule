import java.sql.*;
import java.util.Scanner;

public class Main {
        public static void main(String[] argv) {

        final String[] MENU_MAIN = {"Create Database", "Add new data", "Selects" };

        int menu;
        do {

            menu = getChoose(MENU_MAIN);

            switch(menu) {
                case 0: break;

                case 1: {
                    TableCreations.createDB();
                } break;

                case 2: {
                    addMenu();
                } break;

                case 3: {
                    selectMenu();
                } break;

                default:
                    System.out.println("***Error, please try again***");
                    break;
            }
        } while (menu != 0);
    }


    public static void addMenu()  {

        String[] ADD_MENU= {"Add days", "Add classrooms", "Add subjects", "Add groups",
                "Add teachers", "Add lessons", "Add random lessons"};

        TableInserts insertion = new TableInserts( MyConnection.connectionToBD() );
        Scanner in = new Scanner( System.in );

        int menu;
        do {
            menu = getChoose(ADD_MENU);

            switch(menu) {
                case 0: break;

                case 1: {
                    try {
                        insertion.insertDays( "Monday" );
                        insertion.insertDays( "Tuesday" );
                        insertion.insertDays( "Wednesday" );
                        insertion.insertDays( "Thursday" );
                        insertion.insertDays( "Friday" );
                        insertion.insertDays( "Saturday" );
                        insertion.insertDays( "Sunday" );
                    } catch (SQLException e) {
                        throw new RuntimeException( e );
                    }
                } break;

                case 2: {
                    System.out.print("Number of inputting classrooms = ");
                    int numClass =  new java.util.Scanner( System.in ).nextInt();
                    for(int i = 0; i < numClass; i++){
                        try{
                            insertion.insertClassrooms( new java.util.Scanner( System.in ).nextInt() );
                        } catch (SQLException  e){
                            e.printStackTrace();
                        }
                    }
                } break;

                case 3: {
                    System.out.print("Number of inputting Subjects = ");
                    int numSubj =  new java.util.Scanner( System.in ).nextInt();
                    for(int i = 0; i < numSubj; i++){

                        try{
                            insertion.insertSubjects( new java.util.Scanner( System.in ).next() );
                        } catch (SQLException  e){
                            e.printStackTrace();
                        }
                    }
                } break;

                case 4: {
                    System.out.print("Number of inputting Groups = ");
                    int numGroups =  in.nextInt();
                    for(int i = 0; i < numGroups; i++){
                        try{
                            insertion.insertGroups(in.next(), in.nextInt() );
                        } catch (SQLException  e){
                            e.printStackTrace();
                        }
                    }
                } break;

                case 5: {
                    System.out.print("Number of inputting Teachers = ");
                    int numTeachers =  in.nextInt();
                    for(int i = 0; i < numTeachers; i++){

                        try{
                            insertion.insertTeachers(in.next(), in.next() );
                        } catch (SQLException  e){
                            e.printStackTrace();
                        }
                    }
                } break;

                case 6: {
                    System.out.print("Number of inputting lessons = ");
                    int numLessons =  in.nextInt();
                    for(int i = 0; i < numLessons; i++){
                        try{
                            insertLessons();
                        } catch (SQLException  e){
                            e.printStackTrace();
                        }
                    }
                } break;

                case 7: {
                    System.out.print("Number of inputting lessons = ");
                    int numLessons =  in.nextInt();
                    for(int i = 0; i < numLessons; i++) {
                        try {
                            new TableInserts( MyConnection.connectionToBD() ).insertLessonsRandom();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }break;

                default:
                    System.out.println("***Error, please try again***");
                    break;
            }
        } while (menu != 0);
    }

    public static void selectMenu(){
        final String[] SELECT_MENU = {"information about teachers working on a given day of the week in a given audience",
                "information about teachers who do not teach classes on a given day of the week",
                "days of the week on which a given number of classes are held" };

        int menu;
        do {
            menu = getChoose(SELECT_MENU);
            switch(menu) {
                case 0: break;

                case 1: {
                    TableSelects.infoTeachersThatWorksOnGivenDay();
                } break;

                case 2: {
                    TableSelects.infoTeachersThatDontWorksOnGivenDay();
                } break;

                case 3: {
                    TableSelects.dayWithSameLessons();
                } break;

                default:
                    System.out.println("***Error, please try again***");
                    break;
            }
        } while (menu != 0);
    }



    public static void insertLessons() throws SQLException {
        Scanner in = new Scanner( System.in );

        String teacherFirstName, /*String*/ teacherSecondName;
        int classroom;
        String subject;
        String groupFirstPart; int groupSecondPart;
        String day;

        System.out.print( "First name of Teacher: " );
        teacherFirstName =  in.next();

        System.out.print( "Second name of Teacher: " );
        teacherSecondName =  in.next();

        System.out.print( "Classroom: " );
        classroom =  in.nextInt();

        System.out.print( "Subject: " );
        subject =  in.next();

        System.out.print( "Group first part: " );
        groupFirstPart =  in.next();

        System.out.print( "Group second part: " );
        groupSecondPart =  in.nextInt();

        System.out.print( "Day of week: " );
        day =  in.next();

        new TableInserts( MyConnection.connectionToBD() ).insertLessons(
                teacherFirstName, teacherSecondName,
                classroom,
                subject,
                groupFirstPart, groupSecondPart,
                day );
    }


    public static int getChoose(String[] s){
        System.out.println("0. Back");

        for(int i = 0; i < s.length; i++){
            System.out.println(i + 1 + ". " + s[i]);
        }
        return new java.util.Scanner(System.in).nextInt();
    }
}


