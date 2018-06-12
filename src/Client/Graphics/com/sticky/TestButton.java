package Client.Graphics.com.sticky;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;

public class TestButton extends ButtonSkeleton {
    private Shape shape;
    private Image current, up, down,press;
    private Sound click;

    public TestButton(Shape s, Image up, Image down,Image press, Sound click) {
        shape = s;
        setShape(s);
        this.up = up;
        this.down = down;
        this.press=press;

        current = up;
        this.click = click;
    }


    @Override
    public void render(GameContainer container, Graphics g) {
        current.draw(shape.getX(), shape.getY());
    }

}
