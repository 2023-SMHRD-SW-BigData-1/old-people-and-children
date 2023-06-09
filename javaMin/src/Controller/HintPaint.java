package Controller;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class HintPaint extends JFrame {

   private Image background;

   public HintPaint (String hint) {
      background = new ImageIcon(HintPaint.class.getResource("../img/"+hint)).getImage();
   }
   
   public void frame() {

      setTitle("Hint");
      setSize(900,700);
      setResizable(false);
      setLocationRelativeTo(null);
      setVisible(true);
   }

   public void paint(Graphics g) {
      g.drawImage(background, 50, 50, null);
   }
}