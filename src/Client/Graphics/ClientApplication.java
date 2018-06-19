package Client.Graphics;

public class ClientApplication {
    // ClientInterface e passarlo al costruttore della MainGraphics
    public static void main(String[] args) {
        ClientInterface clientInterface = new ClientInterface();
        try {
            MainGraphics m = new MainGraphics("Trivial", clientInterface);
            m.Run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
