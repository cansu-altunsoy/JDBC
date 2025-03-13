package stepDefinitions;

import io.cucumber.java.en.Given;
import manage.QueryManage;
import utilities.ConfigReader;
import utilities.JDBCReusableMethods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertEqualsNoOrder;

public class stepDefinition {

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    PreparedStatement preparedStatement;

    String QUERY;
    QueryManage queryManage = new QueryManage();

    @Given("Database baglantisi kurulur")
    public void database_baglantisi_kurulur() throws SQLException {

       connection = DriverManager.getConnection(ConfigReader.getProperty("URL"),
                                     ConfigReader.getProperty("USERNAME"),
                                     ConfigReader.getProperty("PASSWORD"));


    }
    @Given("SQL Querysi hazirla ve calistir")
    public void sql_querysi_hazirla_ve_calistir() throws SQLException {

        String QUERY = "SELECT DISTINCT user_id FROM deposits WHERE amount BETWEEN 100 AND 500";

       statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
       resultSet = statement.executeQuery(QUERY); // statement da query calistirilirken girilir

       // resultSet = JDBCReusableMethods.executeQuery(QUERY);

    }
    @Given("Sonuclari dogrula")
    public void sonuclari_dogrula() throws SQLException {

        List<Integer> expectedResualt = new ArrayList<>();

        expectedResualt.add(1);
        expectedResualt.add(9);
        expectedResualt.add(10);
        expectedResualt.add(12);
        expectedResualt.add(64);


        List<Integer> actualResualt = new ArrayList<>();

        while (resultSet.next()){

            actualResualt.add(resultSet.getInt("user_id"));
           // actualResualt.add(resultSet.getInt(1)); // istedigim column index degeri, her iki sekildede calıstirabilirirz

        }

        assertEqualsNoOrder(actualResualt,expectedResualt);

    }

    @Given("Database baglantisini sonlandir")
    public void database_baglantisini_sonlandir() throws SQLException {

       // resultSet.close();
        //statement.close();
        //connection.close();

        JDBCReusableMethods.closeConnection();

    }



    // =======================================================


    @Given("(cron_schedules) SQL Query'si calistirilir")
    public void cron_schedules_sql_query_si_calistirilir() {

        QUERY = queryManage.getCroneSchedulesQuery();
        resultSet = JDBCReusableMethods.executeQuery(QUERY);



    }
    @Given("(cron_schedules) sonuclari dogrulanir")
    public void cron_schedules_sonuclari_dogrulanir() throws SQLException {

        List<String> expectedResualtList = new ArrayList<>();
        expectedResualtList.add("5 Minutes");
        expectedResualtList.add("10 Minutes");

        List<String> actualResualtList = new ArrayList<>();

        while (resultSet.next()){

            actualResualtList.add(resultSet.getString(1));
        }

        assertEqualsNoOrder(actualResualtList,expectedResualtList);

    }




    //=====================UPDATE======================


    @Given("Database ile baglanti kurulur")
    public void database_ile_baglanti_kurulur() {

        JDBCReusableMethods.createConnection();

    }
    @Given("\\(users) Update sorgusu hazirlanir ve calistirilir")
    public void users_update_sorgusu_hazirlanir_ve_calistirilir() throws SQLException {

        QUERY = queryManage.getUserMobileUpdateQuery();
       preparedStatement =  JDBCReusableMethods.getConnection().prepareStatement(QUERY);  //  prepareStatement olustururken query icine girilir

        preparedStatement.setString(1,"55555");
        preparedStatement.setString(2, "%e_");
        int etkilenensatirsayisi = preparedStatement.executeUpdate();


    }
    @Given("\\(users) sorgu sonuclari dogrulanir")
    public void users_sorgu_sonuclari_dogrulanir() throws SQLException {

        int etkilenensatirsayisi = preparedStatement.executeUpdate();
        System.out.println(etkilenensatirsayisi);

        int expectedResualt = 5;
        assertEquals(expectedResualt,etkilenensatirsayisi);


    }
    @Given("database baglantisi sonlandirilir")
    public void database_baglantisi_sonlandirilir() {

        JDBCReusableMethods.closeConnection();

    }


  //==================INSERT===================


    @Given("(device_tokens) Insert sorgusu hazirlanir ve calistirilir.")
    public void device_tokens_ınsert_sorgusu_hazirlanir_ve_calistirilir() throws SQLException {

        QUERY = queryManage.getDeviceTokenInsertQuery();

        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(QUERY);


        // INSERT INTO device_tokens (id, user_id, is_app, token) VALUES (?,?,?, 'NewwToken'

        preparedStatement.setInt(1,34);
        preparedStatement.setInt(2,6);
        preparedStatement.setInt(3, 7);
        preparedStatement.setString(4,"NewwToken");

       int etkilenenSatirSayisi =  preparedStatement.executeUpdate();


    }

    @Given("(device_tokens) sorgu sonuclari dogrulanir.")
    public void device_tokens_sorgu_sonuclari_dogrulanir() throws SQLException {

        int etkilenenSatirSayisi =  preparedStatement.executeUpdate();

        System.out.println(etkilenenSatirSayisi);

        int expectedResualt = 1;


        assertEquals(etkilenenSatirSayisi,expectedResualt);


    }


















































}
