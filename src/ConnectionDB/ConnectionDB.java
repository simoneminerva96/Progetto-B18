package ConnectionDB;

import GameClasses.Answer;
import GameClasses.Categories;
import GameClasses.Question;

import javax.naming.CommunicationException;
import javax.xml.transform.Result;
import java.sql.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

        cn = DriverManager.getConnection("jdbc:mysql://10.87.144.91:3306/trivial?user=root&password=root");
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
                    questions.add(new Question(rs.getString("DESCRIZIONE"),conversionCode(cod),answers));//LA CATEGORIA VA SETTATA QUANDO CHIAMO IL METODO NEL COSTRUTTORE DEL TABELLONE
                    answers.clear();
                }


        } catch (SQLException e) {
            System.out.println("errore:" + e.getMessage());
        } // fine try-catch
        cn.close(); // chiusura connessione
        return questions;
    }
    //metodo che converte il codice corrispondente nella categoria della domanda
    private Categories conversionCode(String cod){
        Categories category=null;
        switch (cod){
            case "GEO":
            case "FGEO":
                category= Categories.Geografia;
                break;
            case "STO":
            case "FSTO":
                category= Categories.Storia;
                break;
            case "SPO":
            case "FSPO":
                category= Categories.Sport;
                break;
            case "SPE":
            case "FSPE":
                category= Categories.Spettacolo;
                break;
            case "ART":
            case "FART":
                category= Categories.ArteLetteratura;
                break;
            case "SCI":
            case "FSCI":
                category= Categories.Scienze;
                break;
        }
        return category;
    }
    // metodo che converte y/n in true e false
    private static Boolean conversionDB(String input){
        if(input.equalsIgnoreCase("y")){
            return true;
        }
        else return false;
    }


   /* public static void main(String[] args) throws SQLException  {
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

//-------------------------------------------------------------------------------


        ChooseCOD("MAT"); //A seconda del codice passato, il metodo chiamato fa tornare una domanda casuale con le relative risposte


    }*/

    //Procedura per l'inserimento delle credenziali utente nel DB(registrazione)
    public String getPlayer(String IDNAME, String PW) {
        //
        String query = "{ call ADD_PLAYER(?,?) }";
        ResultSet rs;
        String returnMessage="";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://10.87.144.91:3306/trivial?user=root&password=root");
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setString(1, IDNAME);
            stmt.setString(2, PW);

            rs = stmt.executeQuery();
            while (rs.next()) {
                 returnMessage = rs.getString("msg"); //Il risultato viene inserito in una stringa
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnMessage;
    }

    public static void ExistsPlayer(String IDNAME, String PW) { //Funzione per fare ritornare il messaggio di avvenuto login o errore
        //
        String query = "{ ?=call PLAYER_EXIST(?,?) }";
        ResultSet rs;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://10.87.144.91:3306/trivial?user=root&password=root");
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
/*
    public static String ChooseCOD(String COD) { //Funzione per fare ritornare il messaggio di avvenuto login o errore
        //
        String query = "{ ?=call CHOOSE_IDQUEST(?) }";
        ResultSet rs;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trivial?user=root&password=root");
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setString(2,COD);

            rs = stmt.executeQuery();

            while (rs.next()) {
                String r =( rs.getString(1));
                System.out.println( rs.getString(1));

                ChooseQUEST(r);  //Passa il codice generato al metodo per la visaulizzazione della domanda


                //Con questi quattro metodi, passa il codice generato ai metodi i quali, generano le quattro risposte corrispondenti
                ChooseANSWER1(r);
                ChooseANSWER2(r);
                ChooseANSWER3(r);
                ChooseANSWER4(r);

                return r;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return  query;

    }

    public static void ChooseQUEST(String QUEST) { //Funzione per fare ritornare il messaggio di avvenuto login o errore
        //
        String query = "{ ?=call CHOOSE_QUEST(?) }";
        ResultSet rs;
        String re;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trivial?user=root&password=root");
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setString(2,QUEST);

            rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println( rs.getString(1));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }

    public static void ChooseANSWER1(String QUEST) { //Funzione per fare ritornare il messaggio di avvenuto login o errore
        //
        String query = "{ ?=call CHOOSE_ANSWER1(?) }";
        ResultSet rs;
        String re;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trivial?user=root&password=root");
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setString(2,QUEST);

            rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println( rs.getString(1));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }


    public static void ChooseANSWER2(String QUEST) { //Funzione per fare ritornare il messaggio di avvenuto login o errore
        //
        String query = "{ ?=call CHOOSE_ANSWER2(?) }";
        ResultSet rs;
        String re;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trivial?user=root&password=root");
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setString(2,QUEST);

            rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println( rs.getString(1));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }

    public static void ChooseANSWER3 (String QUEST) { //Funzione per fare ritornare il messaggio di avvenuto login o errore
        //
        String query = "{ ?=call CHOOSE_ANSWER3(?) }";
        ResultSet rs;
        String re;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trivial?user=root&password=root");
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setString(2,QUEST);

            rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println( rs.getString(1));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }

    public static void ChooseANSWER4(String QUEST) { //Funzione per fare ritornare il messaggio di avvenuto login o errore
        //
        String query = "{ ?=call CHOOSE_ANSWER4(?) }";
        ResultSet rs;
        String re;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trivial?user=root&password=root");
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setString(2,QUEST);

            rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println( rs.getString(1));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }*/


}
