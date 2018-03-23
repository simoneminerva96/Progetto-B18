import java.sql.*;
import java.io.IOException;

public class ConnectionDB {
    public static void main(String[] args) throws SQLException {
        Connection cn; //connessionea al db
        Statement st; //permette di costruire le query
        ResultSet rs; //permette di ottenere il risultato della query
        String sql; //stringa contenete la query
        // ________________________________connessione



            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trivial?user=root&password=root");
            //trivial è il nome del database


        sql = "SELECT * FROM domande;";
        // ________________________________query
        try {
            st = cn.createStatement(); //creo un statement sulla connessione
            rs = st.executeQuery(sql); //faccio la query sullo statement
            while (rs.next() == true)
                System.out.println(rs.getString("ID_CAT") + "\t" + rs.getString("ID_QUEST")+ "\t" + rs.getString("DESCRIZIONE"));
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        } // fine try-catch
        cn.close(); // chiusura connessione
    }// fine main
}
