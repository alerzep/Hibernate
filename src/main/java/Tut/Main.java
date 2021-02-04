package Tut;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            QueryExecutor.executeQuery("alka", 2);
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM gatunki where ID_gatunku = 2");
            while(result.next()) {
                String gatunki = result.getString("nazwa_zwyczajowa");
                System.out.println("Znaleziono: " + gatunki);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Błąd w mainie");
        }
    }
}
