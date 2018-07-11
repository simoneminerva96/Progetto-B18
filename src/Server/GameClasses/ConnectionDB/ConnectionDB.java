package Server.GameClasses.ConnectionDB;

import Server.GameClasses.GameClasses.Answer;
import Server.GameClasses.GameClasses.Credenziali;
import Server.GameClasses.GameClasses.Question;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe che effettua la connesione al database delle domande e dei giocatori
 * @author Mattia Nichetti <mattia.nichetti01@universitadipavia.it>
 */

public class ConnectionDB {
    private Connection cn; //connessionea al db
    private Statement st; //permette di costruire le query
    private ResultSet rs; //permette di ottenere il risultato della query
    private String sql; //stringa contenete la query

    public ConnectionDB()  { }


    /**metodo che ritorna l'arraylist delle domande corrispondenti al codice passato */
    public ArrayList<Question> getQuestion (String cod) throws SQLException{
        cn = DriverManager.getConnection("jdbc:mysql://10.87.144.91:3306/trivial?useSSL=false", "root", "root");
        sql = "select ID_QUEST, DESCRIZIONE, RISPOSTA, VALUE from domande join risposte on ID_QUEST = ID_DOMANDA where ID_QUEST LIKE \"" + cod + "%\"";

        // ________________________________query
        ArrayList<Question> questions=new ArrayList<>();

        try {
            st = cn.createStatement(); //creo un statement sulla connessione
            rs = st.executeQuery(sql); //faccio la query sullo statement
            Integer i=1;
            ArrayList<Answer> answers=new ArrayList<>();
            while (rs.next())
                if(i%4!=0){
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
        }
        cn.close();
        return questions;
    }

    /** metodo che converte y/n in true e false */
    private static Boolean conversionDB(String input){ return input.equalsIgnoreCase("y"); }

    /** Effettua la registrazione collegandosi al db
     * @param credenziali  credenziali inserite dall'utente */
    public Boolean getPlayer(Credenziali credenziali) {
        String IDNAME = credenziali.getUser();
        String PW = credenziali.getPassword();
        String query = "{ ?=call ADD_PLAYER1(?,?) }";

        return connect(query,credenziali);
    }
    

    /**Funzione che permette di effettuare il login collegandosi al database
     @param credenziali credenziali inserite dall'utente
     */
    public Boolean ExistsPlayer(Credenziali credenziali) {
        String IDNAME = credenziali.getUser();
        String PW = credenziali.getPassword();
        String query = "{ ?=call PLAYER_EXIST(?,?) }";
        return connect(query,credenziali);
    }

    public boolean connect(String query,Credenziali credenziali){
        String IDNAME = credenziali.getUser();
        String PW = credenziali.getPassword();
        Boolean returnMessage = false;
        ResultSet rs;

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
}
