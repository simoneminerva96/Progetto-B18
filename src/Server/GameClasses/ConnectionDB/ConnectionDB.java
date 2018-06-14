package Server.GameClasses.ConnectionDB;

import Server.GameClasses.Answer;
import Server.GameClasses.Categories;
import Server.GameClasses.Question;
import java.sql.*;
import java.util.ArrayList;

/**
 * classe che effettua la connesione al database delle domande e dei giocatori
 * @author Mattia Nichetti <mattia.nichetti01@universitadipavia.it>
 */

public class ConnectionDB {
    private Connection cn; //connessionea al db
    private Statement st; //permette di costruire le query
    private ResultSet rs; //permette di ottenere il risultato della query
    private String sql; //stringa contenete la query

    public ConnectionDB()  { }

    /*
        metodo che ritorna l'arraylist delle domande corrispondenti al codice passato
     */
    public ArrayList<Question> getQuestion (String cod) throws SQLException{

        cn = DriverManager.getConnection("jdbc:mysql://10.87.144.91:3306/trivial?useSSL=false", "root", "root");
        sql = "select ID_QUEST, DESCRIZIONE, RISPOSTA, VALUE from domande join risposte on ID_QUEST = ID_DOMANDA where ID_QUEST LIKE \"" + cod + "%\"";
        // ________________________________query
        ArrayList<Question> questions=new ArrayList<Question>();
        try {
            st = cn.createStatement(); //creo un statement sulla connessione
            rs = st.executeQuery(sql); //faccio la query sullo statement
            Integer i=1;
            ArrayList<Answer> answers=new ArrayList<Answer>();
            while (rs.next() == true)
                if(i%4!=0){
                    //System.out.println(rs.getString("ID_QUEST") + "\t" + rs.getString("DESCRIZIONE"));
                    Boolean correct=conversionDB(rs.getString("VALUE"));
                    answers.add(new Answer(rs.getString("RISPOSTA"),correct));
                    i++;
                }
                else {
                    boolean correct=conversionDB(rs.getString("VALUE"));
                    answers.add(new Answer(rs.getString("RISPOSTA"),correct));
                    i++;
                    questions.add(new Question(rs.getString("DESCRIZIONE"),answers));//LA CATEGORIA VA SETTATA QUANDO CHIAMO IL METODO NEL COSTRUTTORE DEL TABELLONE
                    answers.clear();
                }


        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        } // fine try-catch
        cn.close(); // chiusura connessione
        return questions;
    }
    // metodo che converte y/n in true e false
    private static Boolean conversionDB(String input){
        if(input.equalsIgnoreCase("y")){
            return true;
        }
        else return false;
    }

    //Procedura per l'inserimento delle credenziali utente nel DB(registrazione)
    public Boolean getPlayer(String IDNAME, String PW) {
        //
        String query = "{ ?=call ADD_PLAYER1(?,?) }";
        ResultSet rs;
        Boolean returnMessage = null;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://10.87.144.91:3306/trivial?useSSL=false", "root", "root");
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setString(2, IDNAME);
            stmt.setString(3, PW);

            rs = stmt.executeQuery();
            while (rs.next()) {
                 returnMessage = rs.getBoolean(1); //Il risultato viene inserito in una stringa
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnMessage;
    }
    

    //Funzione per fare ritornare il messaggio di avvenuto login o errore
    public Boolean ExistsPlayer(String IDNAME, String PW) {
        //
        String query = "{ ?=call PLAYER_EXIST(?,?) }";
        ResultSet rs;
        Boolean returnMess = null;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://10.87.144.91:3306/trivial?useSSL=false", "root", "root");
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setString(2,IDNAME);
            stmt.setString(3,PW);

            rs = stmt.executeQuery();

            while (rs.next()) {
                returnMess = rs.getBoolean(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
       return returnMess;
    }
}
