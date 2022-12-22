import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Lines extends Painter {

	public Lines(Color color, int size, boolean Filled) {
		super(color, size, Filled);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(color);
		float f[]= {50.0f,50.0f};
		if(is_Filled==true)
			g.setStroke(new BasicStroke(size));
		else {
			g.setStroke(new BasicStroke(size,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_MITER,10.0f,f,0.0f));
		}
			g.drawLine(startPoint.x, startPoint.y,finishPoint.x , finishPoint.y);
		
	}

}
