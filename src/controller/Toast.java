package controller;

import java.awt.*;

import javax.swing.*;

class Toast extends JFrame {
  JWindow toast;

  Toast(String toastMessage, int x, int y) {
    toast = new JWindow();

    // make the background transparent
    toast.setBackground(new Color(0, 0, 0, 0));

    // create a panel
    JPanel p = new JPanel() {
      public void paintComponent(Graphics g) {
        int toastWidth = g.getFontMetrics().stringWidth(toastMessage);
        int toastHeight = g.getFontMetrics().getHeight();

        // draw the boundary of the toast and fill it
        g.setColor(Color.darkGray);
        g.fillRect(10, 10, toastWidth + 50, toastHeight + 20);
        g.setColor(Color.darkGray);
        g.drawRect(10, 10, toastWidth + 50, toastHeight + 20);

        // set the color of text
        g.setColor(new Color(214, 214, 214, 240));
        g.setFont(new Font("default", Font.BOLD, 13));
        g.drawString(toastMessage, 25, 32);
        int t = 250;

        // draw the shadow of the toast
        for (int i = 0; i < 4; i++) {
          t -= 60;
          g.setColor(new Color(0, 0, 0, t));
          g.drawRect(10 - i, 10 - i, toastWidth + 50 + i * 2,
                  toastHeight + 20 + i * 2);
        }
      }
    };

    toast.add(p);
    toast.setLocation(x, y);
    toast.setSize(300, 100);
  }

  void showToast() {
    try {
      toast.setOpacity(1);
      toast.setVisible(true);

      // wait for some time
      Thread.sleep(2000);

      // make the message disappear  slowly
      for (double d = 1.0; d > 0.2; d -= 0.1) {
        Thread.sleep(100);
        toast.setOpacity((float) d);
      }

      // set the visibility to false
      toast.setVisible(false);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}