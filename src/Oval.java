import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Oval extends Painter {

	public Oval(Color color, int size, boolean Filled) {
		super(color, size, Filled);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(color);
		g.setStroke(new BasicStroke(size));
		int x=Math.min(startPoint.x, finishPoint.x);
		int y=Math.min(startPoint.y, finishPoint.y);
		int width=Math.abs(startPoint.x-finishPoint.x);
		int height=Math.abs(startPoint.y-finishPoint.y);
		if(is_Filled==true) {
			g.fillOval(x, y, width, height);
		}else {
			g.drawOval(x, y, width, height);
		}
	}

}
