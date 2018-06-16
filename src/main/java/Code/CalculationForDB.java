package Code;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CalculationForDB {
    private static int employee_id;
    private static int course_id;

    private static int pc5Employee;
    private static int pc6Employee;
    private static int pc15Employee;

    private static int pc5StartCourse;
    private static int pc6StartCourse;
    private static int pc15StartCourse;

    private static int pc5EndCourse;
    private static int pc6EndCourse;
    private static int pc15EndCourse;


    private static int price;
    private static double effect;

    private static int budget = 0;
    private static int number = 0;
    private static int numberOnCourse = 0;

    private static int increment = 0;


    public static void clearDynamicTables(DBWorker dbWorker) {
        try {
            Statement statement = dbWorker.getConnection().createStatement();

            String deleteTablesSQL = "DELETE FROM effect;";
            statement.executeUpdate(deleteTablesSQL);

            deleteTablesSQL = "DELETE FROM employee_end;";
            statement.executeUpdate(deleteTablesSQL);

            deleteTablesSQL = "DELETE FROM visitation;";
            statement.executeUpdate(deleteTablesSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void FirstCalculationEffect(DBWorker dbWorker) {
        try {
            Statement statement = dbWorker.getConnection().createStatement();
            ResultSet resultSetEmployee = statement.executeQuery("SELECT * FROM employee_start");

            statement = dbWorker.getConnection().createStatement();
            ResultSet resultSetCourse;

            while (resultSetEmployee.next()) {

                employee_id = resultSetEmployee.getInt("employee_id");

                String employee_name = resultSetEmployee.getString("employee_name");

                pc5Employee = resultSetEmployee.getInt("employee_pc5");
                pc6Employee = resultSetEmployee.getInt("employee_pc6");
                pc15Employee = resultSetEmployee.getInt("employee_pc15");

                resultSetCourse = statement.executeQuery("SELECT * FROM course");

                while (resultSetCourse.next()) {

                    course_id = resultSetCourse.getInt("course_id");

                    String course_name = resultSetCourse.getString("course_name");

                    pc5StartCourse = resultSetCourse.getInt("course_pc5_start");
                    pc6StartCourse = resultSetCourse.getInt("course_pc6_start");
                    pc15StartCourse = resultSetCourse.getInt("course_pc15_start");

                    pc5EndCourse = resultSetCourse.getInt("course_pc5_end");
                    pc6EndCourse = resultSetCourse.getInt("course_pc6_end");
                    pc15EndCourse = resultSetCourse.getInt("course_pc15_end");

                    price = resultSetCourse.getInt("course_price");

                    effect = MathCompetence.getEffect(pc5Employee, pc6Employee, pc15Employee,
                            pc5StartCourse, pc6StartCourse, pc15StartCourse,
                            pc5EndCourse, pc6EndCourse, pc15EndCourse,
                            price);


                    insertEffect(dbWorker);
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                dbWorker.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private static void insertEffect(DBWorker dbWorker) throws SQLException {
        PreparedStatement preparedStatementInsertEffect = dbWorker.getConnection()
                .prepareStatement("INSERT INTO effect(course_id, employee_id, effect) " +
                        "VALUE (?,?,?)");

        preparedStatementInsertEffect.setInt(1, course_id);
        preparedStatementInsertEffect.setInt(2, employee_id);
        preparedStatementInsertEffect.setDouble(3, effect);
        preparedStatementInsertEffect.executeUpdate();
    }

    private static void updateEffect(DBWorker dbWorker) throws SQLException {
        Statement statementFindCourse = dbWorker.getConnection().createStatement();
        ResultSet resultSetFindCourse = statementFindCourse.executeQuery("SELECT COUNT(visitation_id) FROM visitation " +
                "WHERE course_id = " + course_id + " AND employee_id = " + employee_id + ";");
        resultSetFindCourse.next();
        int countFindCourse = resultSetFindCourse.getInt("COUNT(visitation_id)");
        if (countFindCourse == 1) {
            effect = -1;
        }

        PreparedStatement preparedStatement = dbWorker.getConnection()
                .prepareStatement("UPDATE effect SET effect.effect = ? WHERE course_id = ? AND employee_id = ?");
        preparedStatement.setDouble(1, effect);
        preparedStatement.setInt(2, course_id);
        preparedStatement.setInt(3, employee_id);
        preparedStatement.executeUpdate();
    }

    private static void closeEffect(DBWorker dbWorker) throws SQLException {
        effect = -1;
        PreparedStatement preparedStatement = dbWorker.getConnection()
                .prepareStatement("UPDATE effect SET effect.effect = ? WHERE course_id = ? AND employee_id = ?");
        preparedStatement.setDouble(1, effect);
        preparedStatement.setInt(2, course_id);
        preparedStatement.setInt(3, employee_id);
        preparedStatement.executeUpdate();

    }

    private static void checkEffect(DBWorker dbWorker) throws SQLException {
        effect = -1;
        String deleteEffectOfCourse = "UPDATE effect SET effect.effect = ? WHERE effect.course_id = ?;";
        PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(deleteEffectOfCourse);
        preparedStatement.setDouble(1, effect);
        preparedStatement.setInt(2, course_id);
        preparedStatement.executeUpdate();

    }

    private static void insertEmployeeEnd(DBWorker dbWorker) throws SQLException {

        Statement statementCountEmployeeEnd = dbWorker.getConnection().createStatement();
        ResultSet resultSetCountEmployeeEnd = statementCountEmployeeEnd.executeQuery("SELECT COUNT(employee_id) FROM employee_end " +
                "WHERE employee_id = " + employee_id + ";");
        resultSetCountEmployeeEnd.next();
        int countEmployeeEnd = resultSetCountEmployeeEnd.getInt("COUNT(employee_id)");
        PreparedStatement preparedStatementInsertEmployeeEnd;
        if (countEmployeeEnd == 1) {
            preparedStatementInsertEmployeeEnd = dbWorker.getConnection()
                    .prepareStatement("UPDATE employee_end SET employee_pc5 = ?, employee_pc6 = ?, employee_pc15 = ? " +
                            "WHERE employee_id = " + employee_id + ";");
            preparedStatementInsertEmployeeEnd.setInt(1, pc5Employee);
            preparedStatementInsertEmployeeEnd.setInt(2, pc6Employee);
            preparedStatementInsertEmployeeEnd.setInt(3, pc15Employee);
            preparedStatementInsertEmployeeEnd.executeUpdate();

        } else {
            preparedStatementInsertEmployeeEnd = dbWorker.getConnection()
                    .prepareStatement("INSERT INTO employee_end(employee_id, employee_pc5, employee_pc6, employee_pc15) " +
                            "VALUE (?,?,?,?)");
            preparedStatementInsertEmployeeEnd.setInt(1, employee_id);
            preparedStatementInsertEmployeeEnd.setInt(2, pc5Employee);
            preparedStatementInsertEmployeeEnd.setInt(3, pc6Employee);
            preparedStatementInsertEmployeeEnd.setInt(4, pc15Employee);
            preparedStatementInsertEmployeeEnd.executeUpdate();
        }


    }

    private static void insertVisitation(DBWorker dbWorker) throws SQLException {
        PreparedStatement preparedStatementInsertVisitation = dbWorker.getConnection()
                .prepareStatement("INSERT INTO visitation(course_id, employee_id, visitation_order) " +
                        "VALUE (?,?,?)");
        preparedStatementInsertVisitation.setInt(1, course_id);
        preparedStatementInsertVisitation.setInt(2, employee_id);
        preparedStatementInsertVisitation.setInt(3, number);
        preparedStatementInsertVisitation.executeUpdate();
    }

    public static int calculationLimitMaxEffect(DBWorker dbWorker) throws SQLException {
        int id;
        int pc5start;
        int pc6start;
        int pc15start;

        int pc5end;
        int pc6end;
        int pc15end;

        int counter;
        int maxEffect = 0;

        Statement statementStart = dbWorker.getConnection().createStatement();
        Statement statementEnd = dbWorker.getConnection().createStatement();
        ResultSet resultSetEmployeeStart = statementStart.executeQuery("SELECT " +
                "employee_id, employee_pc5, employee_pc6, employee_pc15 FROM employee_start");

        ResultSet resultSetEmployeeEnd;

        while (resultSetEmployeeStart.next()) {
            id = resultSetEmployeeStart.getInt("employee_id");
            pc5start = resultSetEmployeeStart.getInt("employee_pc5");
            pc6start = resultSetEmployeeStart.getInt("employee_pc6");
            pc15start = resultSetEmployeeStart.getInt("employee_pc15");

            String query;
            query = "SELECT employee_id, employee_pc5, employee_pc6, employee_pc15, \n" +
                    "COUNT(employee_id) FROM employee_end WHERE employee_id = " + id + ";";


            resultSetEmployeeEnd = statementEnd.executeQuery(query);

            while (resultSetEmployeeEnd.next()) {
                counter = resultSetEmployeeEnd.getInt("COUNT(employee_id)");
                if (counter != 0) {

                    pc5end = resultSetEmployeeEnd.getInt("employee_pc5");
                    pc6end = resultSetEmployeeEnd.getInt("employee_pc6");
                    pc15end = resultSetEmployeeEnd.getInt("employee_pc15");

                    maxEffect = maxEffect + (pc5end - pc5start) + (pc6end - pc6start) + (pc15end - pc15start);
                }
            }
        }

        return maxEffect;
    }

    public static void planMaker(DBWorker dbWorker, int maxBudget, int maxNumberOnCourse, int maxNumber) {
        budget = 0;
        number = 1;
        try {
            while (budget <= maxBudget) {

                Statement statement = dbWorker.getConnection().createStatement();
                ResultSet resultSetMaxEffect = statement.executeQuery("SELECT effect.course_id, effect.employee_id, MAX(effect) " +
                        "FROM effect WHERE effect = (SELECT  MAX(effect) FROM effect)");

                resultSetMaxEffect.next();
                course_id = resultSetMaxEffect.getInt("course_id");
                employee_id = resultSetMaxEffect.getInt("employee_id");
                effect = resultSetMaxEffect.getDouble("MAX(effect)");

                if (effect == -1 || employee_id == 0 || course_id == 0) {
                    return;
                }

                String queryMaxCourse = "SELECT course_pc5_end, course_pc6_end, course_pc15_end, course_price " +
                        "FROM course WHERE course_id = " + course_id;
                statement = dbWorker.getConnection().createStatement();
                ResultSet resultSetMaxCourse = statement.executeQuery(queryMaxCourse);

                resultSetMaxCourse.next();

                String countCourseSQL = "SELECT COUNT(*) FROM visitation WHERE course_id = " + course_id + ";";
                Statement statementCountCourse = dbWorker.getConnection().createStatement();
                ResultSet resultSetCountCourse = statementCountCourse.executeQuery(countCourseSQL);
                resultSetCountCourse.next();
                numberOnCourse = resultSetCountCourse.getInt("COUNT(*)");
                if (numberOnCourse >= maxNumberOnCourse) {
                    closeEffect(dbWorker);
                    continue;
                }

                pc5EndCourse = resultSetMaxCourse.getInt("course_pc5_end");
                pc6EndCourse = resultSetMaxCourse.getInt("course_pc6_end");
                pc15EndCourse = resultSetMaxCourse.getInt("course_pc15_end");

                price = resultSetMaxCourse.getInt("course_price");
                budget = budget + price;

                if (budget > maxBudget) {
                    budget = budget - price;
                    closeEffect(dbWorker);
                    continue;
                }



                Statement statementNumber = dbWorker.getConnection().createStatement();
                ResultSet resultNumber = statementNumber.executeQuery("SELECT COUNT(DISTINCT employee_id) FROM visitation;");
                resultNumber.next();
                int numberCourse = resultNumber.getInt("COUNT(DISTINCT employee_id)");
                numberCourse++;
                if (numberCourse > maxNumber) {
                    return;
                }


                String queryEmployee = "SELECT employee_id, employee_pc5, employee_pc6, employee_pc15, \n" +
                        "COUNT(employee_id) FROM employee_end WHERE employee_id = " + employee_id + " \n" +
                        "AND employee_end_id = (SELECT MAX(employee_end_id) FROM employee_end WHERE employee_id = " + employee_id + ");";

                Statement statementEmployee = dbWorker.getConnection().createStatement();
                ResultSet resultSetCurrentEmployee = statementEmployee.executeQuery(queryEmployee);

                resultSetCurrentEmployee.next();
                int counterEmployee = resultSetCurrentEmployee.getInt("COUNT(employee_id)");

                if (counterEmployee > 0) {
                    pc5Employee = resultSetCurrentEmployee.getInt("employee_pc5");
                    pc6Employee = resultSetCurrentEmployee.getInt("employee_pc6");
                    pc15Employee = resultSetCurrentEmployee.getInt("employee_pc15");

                } else {
                    statementEmployee = dbWorker.getConnection().createStatement();
                    resultSetCurrentEmployee = statementEmployee.executeQuery("SELECT employee_pc5, employee_pc6, employee_pc15 " +
                            "FROM employee_start WHERE employee_id = " + employee_id);

                    resultSetCurrentEmployee.next();
                    pc5Employee = resultSetCurrentEmployee.getInt("employee_pc5");
                    pc6Employee = resultSetCurrentEmployee.getInt("employee_pc6");
                    pc15Employee = resultSetCurrentEmployee.getInt("employee_pc15");
                }

                if (pc5Employee < pc5EndCourse) {
                    pc5Employee = pc5EndCourse;
                }

                if (pc6Employee < pc6EndCourse) {
                    pc6Employee = pc6EndCourse;
                }

                if (pc15Employee < pc15EndCourse) {
                    pc15Employee = pc15EndCourse;
                }


                insertEmployeeEnd(dbWorker);
                insertVisitation(dbWorker);


                ResultSet resultSetCourse = statement.executeQuery("SELECT * FROM course");
                while (resultSetCourse.next()) {

                    course_id = resultSetCourse.getInt("course_id");


                    pc5StartCourse = resultSetCourse.getInt("course_pc5_start");
                    pc6StartCourse = resultSetCourse.getInt("course_pc6_start");
                    pc15StartCourse = resultSetCourse.getInt("course_pc15_start");

                    pc5EndCourse = resultSetCourse.getInt("course_pc5_end");
                    pc6EndCourse = resultSetCourse.getInt("course_pc6_end");
                    pc15EndCourse = resultSetCourse.getInt("course_pc15_end");

                    price = resultSetCourse.getInt("course_price");

                    effect = MathCompetence.getEffect(pc5Employee, pc6Employee, pc15Employee,
                            pc5StartCourse, pc6StartCourse, pc15StartCourse,
                            pc5EndCourse, pc6EndCourse, pc15EndCourse,
                            price);

                    updateEffect(dbWorker);
                }


                number++;


                increment = calculationLimitMaxEffect(dbWorker);
                System.out.println("Бюджет: " + budget);
                System.out.println("Приращение: " + increment);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                dbWorker.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public static void planMaker(DBWorker dbWorker,
                                 int maxBudget,
                                 int maxNumberOnCourse,
                                 int maxNumber,
                                 String string) {


        budget = 0;
        number = 1;
        try {
            while (budget <= maxBudget) {

                Statement statement = dbWorker.getConnection().createStatement();
                ResultSet resultSetMaxEffect = statement.executeQuery("SELECT effect.course_id, effect.employee_id, MIN(effect) " +
                        "FROM effect WHERE effect = (SELECT  MIN(effect) FROM effect WHERE effect >= 0)");

                resultSetMaxEffect.next();
                course_id = resultSetMaxEffect.getInt("course_id");
                employee_id = resultSetMaxEffect.getInt("employee_id");
                effect = resultSetMaxEffect.getDouble("MIN(effect)");

                if (effect == -1 || employee_id == 0 || course_id == 0) {
                    return;
                }

                String queryMaxCourse = "SELECT course_pc5_end, course_pc6_end, course_pc15_end, course_price " +
                        "FROM course WHERE course_id = " + course_id;
                statement = dbWorker.getConnection().createStatement();
                ResultSet resultSetMaxCourse = statement.executeQuery(queryMaxCourse);

                resultSetMaxCourse.next();

                String countCourseSQL = "SELECT COUNT(*) FROM visitation WHERE course_id = " + course_id + ";";
                Statement statementCountCourse = dbWorker.getConnection().createStatement();
                ResultSet resultSetCountCourse = statementCountCourse.executeQuery(countCourseSQL);
                resultSetCountCourse.next();
                numberOnCourse = resultSetCountCourse.getInt("COUNT(*)");
                if (numberOnCourse >= maxNumberOnCourse) {
                    closeEffect(dbWorker);
                    continue;
                }


                pc5EndCourse = resultSetMaxCourse.getInt("course_pc5_end");
                pc6EndCourse = resultSetMaxCourse.getInt("course_pc6_end");
                pc15EndCourse = resultSetMaxCourse.getInt("course_pc15_end");

                price = resultSetMaxCourse.getInt("course_price");
                budget = budget + price;

                if (budget > maxBudget) {
                    budget = budget - price;
                    closeEffect(dbWorker);
                    continue;
                }



                Statement statementNumber = dbWorker.getConnection().createStatement();
                ResultSet resultNumber = statementNumber.executeQuery("SELECT COUNT(DISTINCT employee_id) FROM visitation;");
                resultNumber.next();
                int numberCourse = resultNumber.getInt("COUNT(DISTINCT employee_id)");
                numberCourse++;
                if (numberCourse > maxNumber) {
                    return;
                }


                String queryEmployee = "SELECT employee_id, employee_pc5, employee_pc6, employee_pc15, \n" +
                        "COUNT(employee_id) FROM employee_end WHERE employee_id = " + employee_id + " \n" +
                        "AND employee_end_id = (SELECT MAX(employee_end_id) FROM employee_end WHERE employee_id = " + employee_id + ");";

                Statement statementEmployee = dbWorker.getConnection().createStatement();
                ResultSet resultSetCurrentEmployee = statementEmployee.executeQuery(queryEmployee);

                resultSetCurrentEmployee.next();
                int counterEmployee = resultSetCurrentEmployee.getInt("COUNT(employee_id)");
                if (counterEmployee > 0) {
                    pc5Employee = resultSetCurrentEmployee.getInt("employee_pc5");
                    pc6Employee = resultSetCurrentEmployee.getInt("employee_pc6");
                    pc15Employee = resultSetCurrentEmployee.getInt("employee_pc15");

                } else {
                    statementEmployee = dbWorker.getConnection().createStatement();
                    resultSetCurrentEmployee = statementEmployee.executeQuery("SELECT employee_pc5, employee_pc6, employee_pc15 " +
                            "FROM employee_start WHERE employee_id = " + employee_id);

                    resultSetCurrentEmployee.next();
                    pc5Employee = resultSetCurrentEmployee.getInt("employee_pc5");
                    pc6Employee = resultSetCurrentEmployee.getInt("employee_pc6");
                    pc15Employee = resultSetCurrentEmployee.getInt("employee_pc15");
                }

                if (pc5Employee < pc5EndCourse) {
                    pc5Employee = pc5EndCourse;
                }

                if (pc6Employee < pc6EndCourse) {
                    pc6Employee = pc6EndCourse;
                }

                if (pc15Employee < pc15EndCourse) {
                    pc15Employee = pc15EndCourse;
                }


                insertEmployeeEnd(dbWorker);
                insertVisitation(dbWorker);


                ResultSet resultSetCourse = statement.executeQuery("SELECT * FROM course");
                while (resultSetCourse.next()) {

                    course_id = resultSetCourse.getInt("course_id");


                    pc5StartCourse = resultSetCourse.getInt("course_pc5_start");
                    pc6StartCourse = resultSetCourse.getInt("course_pc6_start");
                    pc15StartCourse = resultSetCourse.getInt("course_pc15_start");

                    pc5EndCourse = resultSetCourse.getInt("course_pc5_end");
                    pc6EndCourse = resultSetCourse.getInt("course_pc6_end");
                    pc15EndCourse = resultSetCourse.getInt("course_pc15_end");

                    price = resultSetCourse.getInt("course_price");

                    effect = MathCompetence.getEffect(pc5Employee, pc6Employee, pc15Employee,
                            pc5StartCourse, pc6StartCourse, pc15StartCourse,
                            pc5EndCourse, pc6EndCourse, pc15EndCourse,
                            price);

                    updateEffect(dbWorker);
                }


                number++;


                increment = calculationLimitMaxEffect(dbWorker);
                System.out.println("Бюджет: " + budget);
                System.out.println("Приращение: " + increment);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println(string);
            System.out.println("Бюджет: " + budget);
            System.out.println("Приращение: " + increment);

            try {
                dbWorker.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

}
