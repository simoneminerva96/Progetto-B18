package Client.Graphics;

public class ClientApplication {
    public static void main(String[] args) {
        CheckControls checkControls=new CheckControls();
        ClientInterface clientInterface = new ClientInterface(checkControls);
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
