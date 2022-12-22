import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Pencil extends Painter {

  ArrayList<Point> pointsTrace = new ArrayList<Point>();


  public Pencil(Color color, int size, boolean Filled) {
    super(color, size, Filled);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void setFinishPoint(Point finishPoint) {
    pointsTrace.add(finishPoint);
  }

  @Override
  public void draw(Graphics2D g) {
    g.setColor(color);
    g.setStroke(new BasicStroke(size));
    Point firstPoint = startPoint;
    for (Point nextPoint : pointsTrace) {
      // System.out.println(nextPoint);
      g.drawLine(firstPoint.x, firstPoint.y, nextPoint.x, nextPoint.y);
      firstPoint = nextPoint;
    }
  }

}
