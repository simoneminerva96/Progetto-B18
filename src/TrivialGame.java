/*
    CLASSE CONTROLLER
    FUNZIONA DA INTERFACCIA TRA LA PARTE DI LOGICA APPLICATIVA DEL GIOCO E LE INTERFACCE GRAFICHE UTENTE
    INOLTRE TRAMITE LA CLASSE CREATOR_PARTITA QUANDO AVVIA IL GIOCO, ISTANZIA GLI OGGETTI DELLE VARIE CLASSI
    */
public class TrivialGame {
    private Login_registrazione login_registrazione;
    public TrivialGame(){
        login_registrazione=new Login_registrazione();
    }

    public static void main(String[] args) {
        TrivialGame partita=new TrivialGame();
    }
}
