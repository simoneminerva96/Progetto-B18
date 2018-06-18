package Client.Graphics;

public class ClientApplication {
    // ClientInterface e passarlo al costruttore della MainGraphics
    public static void main(String[] args) {
        ClientInterface clientInterface = new ClientInterface();
        clientInterface.comunica();
        /*try {
            MainGraphics m = new MainGraphics("Trivial");
            m.Run();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
