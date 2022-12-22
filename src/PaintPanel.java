import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PaintPanel extends JPanel {
  private ArrayList<Painter> painters = new ArrayList<Painter>();
  private Color currentColor = Color.black;
  private int currentSize = Size.SmallSize.getNum();
  private boolean CurrentFilled = false;
  private Painter currentPainter = new Pencil(currentColor, currentSize, CurrentFilled);
  private int currentPainterindex = 0;
  private Point currentPoint;
  private Color previousColor = currentColor;
  BufferedImage myImage;
  Graphics2D g;


  public PaintPanel() {
    setBackground(Color.WHITE);

    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        switch (currentPainterindex) {
          case 0:
            currentPainter = new Pencil(currentColor, currentSize, CurrentFilled);
            break;
          case 1:
            currentPainter = new Lines(currentColor, currentSize, CurrentFilled);
            break;
          case 2:
            currentPainter = new Oval(currentColor, currentSize, CurrentFilled);
            break;
          case 3:
            currentPainter = new Rect(currentColor, currentSize, CurrentFilled);
            break;
          case 4:
            currentPainter = new RoundRect(currentColor, currentSize, CurrentFilled);
            break;
          case 5:
            currentPainter = new Pencil(Color.white, currentSize, CurrentFilled);
            break;
        }
        currentPainter.setStartPoint(e.getPoint());
        currentPainter.setFinishPoint(e.getPoint());
        UpdatePaint();
      }

      public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        painters.add(currentPainter);
        currentPainter = null;
      }
    });
    setLayout(new BorderLayout());
  }


  public void setCurrentPainter(int index) {
    currentPainterindex = index;
  }

  public void setCurrentColor(Color currentColor) {
    this.currentColor = currentColor;
  }

  public void setPreviousColor(Color previousColor) {
    this.previousColor = previousColor;
  }


  public void setCurrentSize(int size) {
    this.currentSize = size;
  }


  public void setCurrentFilled(boolean currentFilled) {
    CurrentFilled = currentFilled;
  }

  public Painter getcurrentPainter() {
    return currentPainter;
  }

  public Color getCurrentColor() {
    return currentColor;
  }

  public void clearpicture() {
    // TODO Auto-generated method stub
    painters.clear();
    UpdatePaint();
  }

  public void Erase() {
    previousColor = currentColor;
    currentColor = Color.white;
    currentPainterindex = 0;
  }

  public void Undo() {
    painters.remove(painters.size() - 1);
    UpdatePaint();
  }

  public void savePicture() throws AWTException {
    try {
      Path path = getDirectoryPath();
      String string = JOptionPane.showInputDialog(this, "\u8f38\u5165\u6a94\u6848\u540d\u7a31");
      ImageIO.write(myImage, "jpg", new File(path.toString() + "/" + string + ".jpg"));
    } catch (IOException x) {
      x.printStackTrace();
    }
  }

  private Path getDirectoryPath() {
    // configure dialog allowing selection of a file or directory
    JFileChooser fileChooser = new JFileChooser("\u9078\u64c7\u5132\u5b58\u8cc7\u6599\u593e");
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int result = fileChooser.showSaveDialog(this);

    // if user clicked Cancel button on dialog, return
    if (result == JFileChooser.CANCEL_OPTION)
      System.exit(1);

    // return Path representing the selected file
    return fileChooser.getSelectedFile().toPath();
  }

  public void UpdatePaint() {
    g = (Graphics2D) myImage.createGraphics();
    g.fill(new Rectangle(getWidth(), getHeight()));
    if (!painters.isEmpty()) {
      for (Painter paint : painters) {
        paint.draw(g);
      }
    }
    if (currentPainter != null)
      currentPainter.draw(g);
    g.dispose();
    repaint();
  }

  public void setBufferimage(int width, int height) {
    myImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

  }



  @Override
  protected void paintComponent(Graphics g) {
    // TODO Auto-generated method stub
    super.paintComponent(g);
    g.drawImage(myImage, 0, 0, null);
  }


}
