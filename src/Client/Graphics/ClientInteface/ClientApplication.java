package Client.Graphics.ClientInteface;

import Client.Graphics.CheckControls;
import Client.Graphics.MainGraphics;

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
