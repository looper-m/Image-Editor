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

  public GeneratorViewGUI() throws IOException, ClassNotFoundException,
          UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
    super();

    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  // todo check if these two lines can be pushed out
    setDefaultLookAndFeelDecorated(false);
    setTitle("Image Processor");
    setSize(1000, 1000);
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
    fileOpenButton.setActionCommand("load");
    loadSavePanel.add(Box.createRigidArea(new Dimension(8, 0)));
    loadSavePanel.add(fileOpenButton);
    loadSavePanel.add(Box.createRigidArea(new Dimension(8, 0)));

    // Save image
    fileSaveButton = new JButton("Save");
    fileSaveButton.setActionCommand("save");
//    fileSaveButton.addActionListener(new FileActionListener(this));
    loadSavePanel.add(fileSaveButton);
    loadSavePanel.add(Box.createRigidArea(new Dimension(8, 0)));

    //show an image with a scrollbar
    JPanel imagePanel = new JPanel();
//    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
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

    // Operations panel
    JPanel operationsPanel = new JPanel();
    operationsPanel.setBorder(BorderFactory.createEmptyBorder());
    operationsPanel.setLayout(new BoxLayout(operationsPanel, BoxLayout.PAGE_AXIS));
//    operationsPanel.setPreferredSize(new Dimension(10, 70));
    mainPanel.add(operationsPanel, BorderLayout.WEST);

    // Blur image
    blurButton = new JButton("Blur");
    blurButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
    blurButton.setActionCommand("blur");
    operationsPanel.add(Box.createRigidArea(new Dimension(4, 0)));
    operationsPanel.add(blurButton);

    // Sharpen image
    sharpenButton = new JButton("Sharpen");
    sharpenButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
    sharpenButton.setActionCommand("sharpen");
    operationsPanel.add(Box.createRigidArea(new Dimension(4, 0)));
    operationsPanel.add(sharpenButton);

    // Sepia
    sepiaButton = new JButton("Sepia");
    sepiaButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
    sepiaButton.setActionCommand("sepia");
    operationsPanel.add(Box.createRigidArea(new Dimension(4, 0)));
    operationsPanel.add(sepiaButton);

    // Grayscale
    greyscaleButton = new JButton("Greyscale");
    greyscaleButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
    greyscaleButton.setActionCommand("greyscale");
    operationsPanel.add(Box.createRigidArea(new Dimension(4, 0)));
    operationsPanel.add(greyscaleButton);

    setVisible(true);
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
