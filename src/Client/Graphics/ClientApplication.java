package Client.Graphics;

public class ClientApplication {
    public static void main(String[] args) {
        ClientInterface clientInterface = new ClientInterface();
        if (clientInterface.isConnected()) {
            try {
                MainGraphics m = new MainGraphics("Trivial", clientInterface);
                m.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
