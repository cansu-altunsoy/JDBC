import java.sql.*;

public class JDBC {

        /*
    type				jdbc:mysql
    host/ip				195.35.59.18
    port				3306
    database_name		u201212290_qaloantec


    username			u201212290_qaloanuser
    password			HPo?+7r$

     */


    // Ham haldeki bu bilgiler ile JDBC baglantisi kurmak zordur.
    // O yuzden bu datalari analsilir bir URL haline getirmek zorundayiz.


    /*
	URL: jdbc:mysql://195.35.59.18/u201212290_qaloantec
	USERNAME: u201212290_qaloanuser
	PASSWORD: HPo?+7r$

	 */

    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        // 1. ADIM: JDBC Sürücüsünü Yükleme

        Class.forName("com.mysql.cj.jdbc.Driver");  //Type 4


        // 2. ADIM Veritabanı Bağlantısı Kurma (Connection)

        String URL = "jdbc:mysql://195.35.59.18/u201212290_qaloantec";
        String USERNAME = "u201212290_qaloanuser";
        String PASSWORD = "HPo?+7r$";

        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 3. ADIM SQL Sorguları Oluşturma (Query Hazirlama)

        String QUERY = "SELECT * FROM users";

        // 4. ADIM SQL Sorguları Çalıştırma

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(QUERY);

        // 5. ADIM Sonuçları İşleme

        // Database'den gelen sonuclar resultSet icerisinde
        // ResultSet icerisinde "ITERATOR" ile islem yapabilirim.

        resultSet.next(); // Buradaki resualtset icerisind tum tablo vardır String QUERY = "SELECT * FROM users"
        System.out.println(resultSet.getString("firstname"));// ama istedigimiz degerleri kullaniriz

        resultSet.next();
        System.out.println(resultSet.getString("username"));

        resultSet.next();
        resultSet.next();
        System.out.println(resultSet.getInt(8)); // Elf tester 85462


        resultSet.absolute(15);  // istedigim satira ileri gitmek icinc (sutun olarak ilerleme) absolute kullanirim
       System.out.println(resultSet.getString(6));  // bidasa9700@xcmexico.com


        resultSet.previous(); // bulundugum satir da geriye gitmek icin(sutun olarak geri) gitmek icin kullanilir, geriye gitmek icin
        System.out.println(resultSet.getString(2));

        resultSet.first();
        System.out.println(resultSet.getString(3));
        System.out.println(resultSet.getString(7)); //Aynı satirda iken iterator istedigim kadar sutun alabilirim
        System.out.println(resultSet.getString(9)); // tekrar tekrar resualtset yazmaya gerek olmadan...

        resultSet.beforeFirst(); // curser en basa bos alana doner
        resultSet.isBeforeFirst(); //
        resultSet.afterLast(); // curser en son dataya atar
        System.out.println(resultSet.isAfterLast()); // true


    }


}
