import javax.xml.transform.Result;
import java.sql.*;
import java.io.IOException;
import java.util.Scanner;


public class ConnectionDB {
    /*public static void main(String[] args) throws SQLException {
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
                System.out.println(rs.getString("ID_QUEST") + "\t" + rs.getString("DESCRIZIONE"));
        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        } // fine try-catch


        cn.close(); // chiusura connessione


    }// fine main*/


    public static void main(String[] args) {

        String NAME;
        String PASS;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci il tuo nome: ");
        NAME = scanner.nextLine();
                                                                //questo è l'inserimento di un nuovo utente
        System.out.print("Inserisci la tua password: ");
        PASS = scanner.nextLine();

        getPlayer(NAME, PASS);

//------------------------------------------------------------------------------

        System.out.print("Inserisci il tuo nome: ");
        NAME = scanner.nextLine();
                                                                    //questo è il login
        System.out.print("Inserisci la tua password: ");
        PASS = scanner.nextLine();

        ExistsPlayer(NAME, PASS);
    }

    /**
     * @param IDNAME
     * @param PW
     */
    public static void getPlayer(String IDNAME, String PW) {  //Procedura per l'inserimento delle credenziali utente nel DB
        //
        String query = "{ call ADD_PLAYER(?,?) }";
        ResultSet rs;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trivial?user=root&password=root");
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setString(1, IDNAME);
            stmt.setString(2, PW);

            rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println( rs.getString("msg"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void ExistsPlayer(String IDNAME, String PW) { //Funzione per fare ritornare il messaggio di avvenuto login o errore
        //
        String query = "{ ?=call PLAYER_EXIST(?,?) }";
        ResultSet rs;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trivial?user=root&password=root");
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setString(2,IDNAME);
            stmt.setString(3,PW);

            rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println( rs.getString(1));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

}
