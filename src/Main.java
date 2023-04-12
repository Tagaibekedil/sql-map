import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "postgres";
    static final String QUERY2 = "insert into user_account (id,age,login,fulname,email,gender,) values(1,27,'Edil','tagaibek','edk@','m')";
    static final String QUERY1 = "delete from users where id=1";
    static final String QUERY = " select u.login,u.email,ua.account_number,ua.currency from users u\n" +
            "join users_account ua on u.id = ua.user_id";

    public static void main(String[] args) {

        UsersAccount usersAccaunt = new UsersAccount();
        List<String> list = new ArrayList<>(10);
        Map<String,String>map=new HashMap<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY)) {
            //Extract data from result
            while (rs.next()) {
                usersAccaunt.setLogin(rs.getString("login"));
                usersAccaunt.setEmail(rs.getString("email"));
                usersAccaunt.setAccountNumber(rs.getString("account_number"));
                usersAccaunt.setCurrency(rs.getString("currency"));
                list.add(usersAccaunt.getEmail() + " " + usersAccaunt.getAccountNumber() + " " + usersAccaunt.getCurrency() + " " + usersAccaunt.getLogin());
                map.put(usersAccaunt.getLogin(), usersAccaunt.getCurrency());
            } System.out.println(list);
            System.out.println(map);

//

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}