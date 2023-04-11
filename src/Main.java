import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "postgres";
    static final String QUERY = " select u.login,u.email,ua.account_number,ua.currency from users u\n" +
            "join users_account ua on u.id = ua.user_id";

    public static void main(String[] args) {

        UsersAccount usersAccount = new UsersAccount();
        List<String> list = new ArrayList<>(10);
        Map<String,String>map=new HashMap<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY)) {
            //Extract data from result
            while (rs.next()) {
                usersAccount.setLogin(rs.getString("login"));
                usersAccount.setEmail(rs.getString("email"));
                usersAccount.setAccountNumber(rs.getString("account_number"));
                usersAccount.setCurrency(rs.getString("currency"));
                list.add(usersAccount.getEmail() + " " + usersAccount.getAccountNumber() + " " + usersAccount.getCurrency() + " " + usersAccount.getLogin());
                map.put(usersAccount.getLogin(), usersAccount.getCurrency());
            } System.out.println(list);
            System.out.println(map);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
