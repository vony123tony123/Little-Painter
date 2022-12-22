import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public abstract class Painter {
  protected Point startPoint;
  protected Point finishPoint;
  protected Color color;
  protected int size;
  protected boolean is_Filled;

  public Painter(Color color, int size, boolean Filled) {
    // TODO Auto-generated constructor stub
    this.color = color;
    this.size = size;
    is_Filled = Filled;
  }

  public abstract void draw(Graphics2D g);

  public Point getStartPoint() {
    return startPoint;
  }

  public void setStartPoint(Point startPoint) {
    this.startPoint = startPoint;
  }

  public Point getFinishPoint() {
    return finishPoint;
  }

  public void setFinishPoint(Point finishPoint) {
    this.finishPoint = finishPoint;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public boolean getIs_Filled() {
    return is_Filled;
  }

  public void setIs_Filled(boolean is_Filled) {
    this.is_Filled = is_Filled;
  }

}
