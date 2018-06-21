import Client.Graphics.*;

public class MainController {

    public static void main(String[] args) throws Exception {
        MainGraphics mainGrapichs=new MainGraphics("trivial", new ClientInterface());
        mainGrapichs.Run();

    }
}
