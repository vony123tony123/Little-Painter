/*
 * HW2 107403549 資管二 涂建名 有做的加分:上一步、存檔
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


public class Main {

  private JFrame frame;
  //  private String[] toollist = {"\u7b46\u5237", "\u76f4\u7dda", "\u6a62\u5713", "\u77e9\u5f62",
  //      "\u5713\u89d2\u77e9\u5f62", "\u6a61\u76ae\u64e6"};
  private String[] toollist = {"\u7b46\u5237", "\u6a62\u5713", "\u6a61\u76ae\u64e6"};
  private JPanel NorthPanel;
  private JRadioButton SmallButton;
  private JRadioButton MiddleButton;
  private JLabel Postion_Label;
  private JComboBox ToolChoose_Box;
  private JPanel ToolChoose_Panel;
  private JCheckBox Fill_CheckBox;
  private JPanel SizeChoose_Panel;
  private JButton ClearButton;
  private static PaintPanel CenterPanel;
  private JPanel SouthPanel;
  private JRadioButton BigButton;
  private JLabel PostionTitleLabel;
  private JButton PaintColorButton;
  private ButtonGroup sizeButteonGroup;
  private ArrayList<JRadioButton> radioArray = new ArrayList<JRadioButton>();
  private JButton Undo_button;
  private JButton Save_button;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Main window = new Main();
          window.frame.setLocationRelativeTo(null);
          window.frame.setVisible(true);
          JOptionPane.showMessageDialog(null, "Wellcome!", "\u6b61\u8fce\u4f7f\u7528",
              JOptionPane.PLAIN_MESSAGE, new ImageIcon(getClass().getResource("\u7d22\u5f15.jpg")));
          CenterPanel.setBufferimage(CenterPanel.getWidth(), CenterPanel.getHeight());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public Main() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame("\u5C0F\u756B\u5BB6");
    frame.setBounds(100, 100, 954, 704);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    sizeButteonGroup = new ButtonGroup();

    NorthPanel = new JPanel();
    frame.getContentPane().add(NorthPanel, BorderLayout.NORTH);

    ToolChoose_Panel = new JPanel();
    ToolChoose_Panel.setBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 0),
        "\u7E6A\u5716\u5DE5\u5177", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
    NorthPanel.add(ToolChoose_Panel);
    ToolChoose_Panel.setLayout(new BoxLayout(ToolChoose_Panel, BoxLayout.Y_AXIS));

    ToolChoose_Box = new JComboBox(toollist);
    ToolChoose_Box.addItemListener(new ItemListener() {

      public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        if (e.getStateChange() == ItemEvent.SELECTED) {
          int index = ToolChoose_Box.getSelectedIndex();
          // System.out.println(index);
          CenterPanel.setCurrentPainter(index);
          if (index == 0 || index == 2) {
            Fill_CheckBox.setSelected(false);
            Fill_CheckBox.setVisible(false);
          } else {
            Fill_CheckBox.setVisible(true);
          }
          if (index == 5) {
            PaintColorButton.setVisible(false);
          } else {
            PaintColorButton.setVisible(true);
          }
        }
      }
    });
    ToolChoose_Panel.add(ToolChoose_Box);

    SizeChoose_Panel = new JPanel();
    SizeChoose_Panel
        .setBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 0), "\u7B46\u5237\u5927\u5C0F",
            TitledBorder.LEFT, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
    NorthPanel.add(SizeChoose_Panel);

    BigButton = new JRadioButton("\u5927");
    BigButton.addItemListener(new itemhandler());
    BigButton.setActionCommand("\u5927");
    radioArray.add(BigButton);
    SizeChoose_Panel.add(BigButton);
    sizeButteonGroup.add(BigButton);

    MiddleButton = new JRadioButton("\u4E2D");
    MiddleButton.addItemListener(new itemhandler());
    MiddleButton.setActionCommand("\u4E2D");
    SizeChoose_Panel.add(MiddleButton);
    sizeButteonGroup.add(MiddleButton);
    radioArray.add(MiddleButton);

    SmallButton = new JRadioButton("\u5C0F");
    SmallButton.setSelected(true);
    SmallButton.addItemListener(new itemhandler());
    SmallButton.setActionCommand("\u5C0F");
    SizeChoose_Panel.add(SmallButton);
    sizeButteonGroup.add(SmallButton);
    radioArray.add(SmallButton);

    Fill_CheckBox = new JCheckBox("\u586B\u6EFF");
    Fill_CheckBox.setHorizontalTextPosition(SwingConstants.CENTER);
    Fill_CheckBox.setVerticalTextPosition(SwingConstants.TOP);
    Fill_CheckBox.setVisible(false);
    Fill_CheckBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        CenterPanel.setCurrentFilled(e.getStateChange() == ItemEvent.SELECTED ? true : false);
        if (e.getStateChange() == ItemEvent.SELECTED) {
          SizeChoose_Panel.setVisible(false);
        } else {
          SizeChoose_Panel.setVisible(true);
        }
      }
    });
    NorthPanel.add(Fill_CheckBox);

    PaintColorButton = new JButton("\u7B46\u5237\u984F\u8272");
    PaintColorButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        CenterPanel.setCurrentColor(
            JColorChooser.showDialog(frame, "\u7B46\u5237\u984F\u8272", Color.black));
      }
    });
    NorthPanel.add(PaintColorButton);

    ClearButton = new JButton("\u6E05\u9664\u756B\u9762");
    ClearButton.addActionListener(new ActionListener() {


      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        CenterPanel.clearpicture();
      }
    });
    NorthPanel.add(ClearButton);

    Undo_button = new JButton("\u4E0A\u4E00\u6B65");
    NorthPanel.add(Undo_button);
    Undo_button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        CenterPanel.Undo();
      }
    });

    //    Save_button = new JButton("\u5B58\u6A94");
    //    NorthPanel.add(Save_button);
    //    Save_button.addActionListener(new ActionListener() {
    //
    //
    //      public void actionPerformed(ActionEvent e) {
    //        // TODO Auto-generated method stub
    //        try {
    //          CenterPanel.savePicture();
    //        } catch (AWTException e1) {
    //          // TODO Auto-generated catch block
    //          e1.printStackTrace();
    //        }
    //      }
    //    });

    CenterPanel = new PaintPanel();
    CenterPanel.addMouseMotionListener(new MouseMotionListener() {


      public void mouseDragged(MouseEvent e) {
        CenterPanel.getcurrentPainter().setFinishPoint(e.getPoint());
        Postion_Label.setText("(" + e.getX() + "," + e.getY() + ")");
        CenterPanel.UpdatePaint();
      }


      public void mouseMoved(MouseEvent e) {
        Postion_Label.setText("(" + e.getX() + "," + e.getY() + ")");
      }
    });
    frame.getContentPane().add(CenterPanel, BorderLayout.CENTER);

    SouthPanel = new JPanel();
    SouthPanel.setBackground(Color.BLACK);
    frame.getContentPane().add(SouthPanel, BorderLayout.SOUTH);
    SouthPanel.setLayout(new BoxLayout(SouthPanel, BoxLayout.X_AXIS));

    PostionTitleLabel = new JLabel("\u6307\u6A19\u4F4D\u7F6E:");
    PostionTitleLabel.setFont(new Font("�s�ө���", Font.PLAIN, 19));
    PostionTitleLabel.setForeground(Color.WHITE);
    SouthPanel.add(PostionTitleLabel);

    Postion_Label = new JLabel("(0,0)");
    Postion_Label.setFont(new Font("", Font.PLAIN, 19));
    Postion_Label.setForeground(Color.WHITE);
    SouthPanel.add(Postion_Label);


  }



  private class itemhandler implements ItemListener {

    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {

        if (sizeButteonGroup.getSelection().getActionCommand() == "\u5927")
          CenterPanel.setCurrentSize(Size.BigSize.getNum());
        else if (sizeButteonGroup.getSelection().getActionCommand() == "\u4E2D") {
          CenterPanel.setCurrentSize(Size.MiddleSize.getNum());
        } else if (sizeButteonGroup.getSelection().getActionCommand() == "\u5C0F") {
          CenterPanel.setCurrentSize(Size.SmallSize.getNum());
        }
      }
    }
  }


}
