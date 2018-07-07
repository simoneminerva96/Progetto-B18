package Client.Graphics;

public class ClientApplication {
    public static void main(String[] args) {

        ClientInterface clientInterface = new ClientInterface();
        CheckControls checkControls=new CheckControls(clientInterface);
        if (clientInterface.isConnected()) {
            try {
                MainGraphics m = new MainGraphics("Trivial", clientInterface,checkControls);
                m.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
