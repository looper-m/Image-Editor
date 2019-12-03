package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GeneratorViewGUI extends JFrame implements GeneratorView {
  private JPanel mainPanel;
  private JScrollPane mainScrollPane;

  private JLabel imageDisplayLabel;
  private JLabel saveImageLabel;

  private JButton fileOpenButton;
  private JButton fileSaveButton;
  private JButton blurButton;
  private JButton sharpenButton;
  private JButton sepiaButton;
  private JButton greyscaleButton;
  private JButton mosaicButton;
  private JButton ditherButton;

  private JButton franceButton;
  private JButton greeceButton;
  private JButton swissButton;

  private JButton rainbowButton;
  private JButton checkerboardButton;

  private JButton undoButton;

  public GeneratorViewGUI() throws IOException, ClassNotFoundException,
          UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
    super();
    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
      if ("Nimbus".equals(info.getName())) {
        UIManager.setLookAndFeel(info.getClassName());
        break;
      }
    }
//    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");  // todo
//     check if these two lines can be pushed out
    setDefaultLookAndFeelDecorated(false);
    setTitle("Image Processor");
    setSize(1000, 1000);
    setLocation(400, 20);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    setVisible(true);

    mainPanel = new JPanel();
//    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainPanel.setLayout(new BorderLayout());
//    mainScrollPane = new JScrollPane(mainPanel);
//    add(mainScrollPane);
    add(mainPanel);

    // load and save panel
    JPanel loadSavePanel = new JPanel();
    loadSavePanel.setBorder(BorderFactory.createTitledBorder("load/save image"));
    loadSavePanel.setLayout(new BoxLayout(loadSavePanel, BoxLayout.LINE_AXIS));
    loadSavePanel.setPreferredSize(new Dimension(10, 70));
    mainPanel.add(loadSavePanel, BorderLayout.PAGE_START);

    // load image
    fileOpenButton = new JButton("Load");
    fileOpenButton.setFont(new Font("Helvetica", Font.BOLD, 14));
    fileOpenButton.setActionCommand("load");
    loadSavePanel.add(Box.createRigidArea(new Dimension(8, 0)));
    loadSavePanel.add(fileOpenButton);
    loadSavePanel.add(Box.createRigidArea(new Dimension(8, 0)));

    // Save image
    fileSaveButton = new JButton("Save");
    fileSaveButton.setFont(new Font("Helvetica", Font.BOLD, 14));
    fileSaveButton.setActionCommand("save");
//    fileSaveButton.addActionListener(new FileActionListener(this));
    loadSavePanel.add(fileSaveButton);
    loadSavePanel.add(Box.createRigidArea(new Dimension(8, 0)));

    //image panel
    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder(" "));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
//    imagePanel.setMaximumSize();
    mainPanel.add(imagePanel, BorderLayout.CENTER);

    BufferedImage image = ImageIO.read(new File("res/backdrop.jpg"));
    imageDisplayLabel = new JLabel();
    imageDisplayLabel.setHorizontalAlignment(JLabel.CENTER);
    imageDisplayLabel.setVerticalAlignment(JLabel.CENTER);
    imageDisplayLabel.setIcon(new ImageIcon(image));
    JScrollPane imageScrollPane = new JScrollPane(imageDisplayLabel);
//    imageScrollPane.setPreferredSize(new Dimension(200, 900));
    imagePanel.add(imageScrollPane);

    // Generator panel
    JPanel generatorPanel = new JPanel();
//    generatorPanel.setBorder(BorderFactory.createTitledBorder(""));
    generatorPanel.setLayout(new BoxLayout(generatorPanel, BoxLayout.Y_AXIS));
//    operationsPanel.setPreferredSize(new Dimension(10, 70));
    mainPanel.add(generatorPanel, BorderLayout.WEST);

    // Operations panel
    JPanel operationsPanel = new JPanel();
    operationsPanel.setBorder(BorderFactory.createTitledBorder("operations"));
    operationsPanel.setLayout(new BoxLayout(operationsPanel, BoxLayout.PAGE_AXIS));
//    operationsPanel.setPreferredSize(new Dimension(10, 70));
    generatorPanel.add(operationsPanel);

    // Blur image
    blurButton = new JButton("Blur");
    blurButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
    blurButton.setActionCommand("blur");
    blurButton.setMaximumSize(new Dimension(100, 30));
    operationsPanel.add(Box.createRigidArea(new Dimension(4, 4)));
    operationsPanel.add(blurButton);

    // Sharpen image
    sharpenButton = new JButton("Sharpen");
    sharpenButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
    sharpenButton.setActionCommand("sharpen");
    sharpenButton.setMaximumSize(new Dimension(100, 30));
    operationsPanel.add(Box.createRigidArea(new Dimension(4, 0)));
    operationsPanel.add(sharpenButton);

    // Dither image
    ditherButton = new JButton("Dither");
    ditherButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
    ditherButton.setActionCommand("dither");
    ditherButton.setMaximumSize(new Dimension(100, 30));
    operationsPanel.add(Box.createRigidArea(new Dimension(4, 0)));
    operationsPanel.add(ditherButton);

    // Mosaic
    mosaicButton = new JButton("Mosaic");
    mosaicButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
    mosaicButton.setActionCommand("mosaic");
    mosaicButton.setMaximumSize(new Dimension(100, 30));
    operationsPanel.add(Box.createRigidArea(new Dimension(4, 0)));
    operationsPanel.add(mosaicButton);

    // Sepia
    sepiaButton = new JButton("Sepia");
    sepiaButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
    sepiaButton.setActionCommand("sepia");
    sepiaButton.setMaximumSize(new Dimension(100, 30));
    operationsPanel.add(Box.createRigidArea(new Dimension(4, 10)));
    operationsPanel.add(sepiaButton);

    // Grayscale
    greyscaleButton = new JButton("Greyscale");
    greyscaleButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
    greyscaleButton.setActionCommand("greyscale");
    greyscaleButton.setMaximumSize(new Dimension(100, 30));
    operationsPanel.add(Box.createRigidArea(new Dimension(4, 0)));
    operationsPanel.add(greyscaleButton);
    operationsPanel.add(Box.createRigidArea(new Dimension(4, 12)));

    // Patterns panel
    JPanel patternsPanel = new JPanel();
    patternsPanel.setBorder(BorderFactory.createTitledBorder("patterns"));
    patternsPanel.setLayout(new BoxLayout(patternsPanel, BoxLayout.PAGE_AXIS));
//    patternsPanel.setPreferredSize(new Dimension(10, 70));
    generatorPanel.add(patternsPanel);

    // France
    franceButton = new JButton("France");
    franceButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
    franceButton.setActionCommand("france");
    franceButton.setMaximumSize(new Dimension(100, 30));
    patternsPanel.add(Box.createRigidArea(new Dimension(4, 4)));
    patternsPanel.add(franceButton);

    // Greece
    greeceButton = new JButton("Greece");
    greeceButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
    greeceButton.setActionCommand("greece");
    greeceButton.setMaximumSize(new Dimension(100, 30));
    patternsPanel.add(Box.createRigidArea(new Dimension(4, 0)));
    patternsPanel.add(greeceButton);

    // Swiss
    swissButton = new JButton("Swiss");
    swissButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
    swissButton.setActionCommand("switzerland");
    swissButton.setMaximumSize(new Dimension(100, 30));
    patternsPanel.add(Box.createRigidArea(new Dimension(4, 0)));
    patternsPanel.add(swissButton);

    // Rainbow
    rainbowButton = new JButton("Rainbow");
    rainbowButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
    rainbowButton.setActionCommand("rainbow");
    rainbowButton.setMaximumSize(new Dimension(100, 30));
    patternsPanel.add(Box.createRigidArea(new Dimension(4, 10)));
    patternsPanel.add(rainbowButton);

    // Checkerboard
    checkerboardButton = new JButton("Checkers");
    checkerboardButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
    checkerboardButton.setActionCommand("checkerboard");
    checkerboardButton.setMaximumSize(new Dimension(100, 30));
    patternsPanel.add(Box.createRigidArea(new Dimension(4, 0)));
    patternsPanel.add(checkerboardButton);

    // Undo
    undoButton = new JButton("Undo");
    undoButton.setFont(new Font("Helvetica", Font.ITALIC, 14));
    undoButton.setActionCommand("undo");
    undoButton.setMaximumSize(new Dimension(100, 30));
    patternsPanel.add(Box.createRigidArea(new Dimension(4, 20)));
    patternsPanel.add(undoButton);

    setVisible(true);
//    pack();
  }

  @Override
  public void setImageOnCanvas(BufferedImage image) {
    imageDisplayLabel.setIcon(new ImageIcon(image));
  }

  @Override
  public void addActionListenerForFile(ActionListener listener) {
    fileOpenButton.addActionListener(listener);
    fileSaveButton.addActionListener(listener);
  }

  @Override
  public void addActionListenerForOperations(ActionListener listener) {
    blurButton.addActionListener(listener);
    sharpenButton.addActionListener(listener);
    sepiaButton.addActionListener(listener);
    greyscaleButton.addActionListener(listener);
    ditherButton.addActionListener(listener);
    mosaicButton.addActionListener(listener);
    franceButton.addActionListener(listener);
    greeceButton.addActionListener(listener);
    swissButton.addActionListener(listener);
    rainbowButton.addActionListener(listener);
    checkerboardButton.addActionListener(listener);
    undoButton.addActionListener(listener);
  }

  @Override
  public BufferedImage getImageOnCanvas() {
    Icon icon = imageDisplayLabel.getIcon();
    BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
            BufferedImage.TYPE_INT_RGB);
    Graphics graphics = image.createGraphics();
    icon.paintIcon(null, graphics, 0, 0);
    graphics.dispose();
    return image;
  }
}
