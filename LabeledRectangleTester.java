import java.awt.*;
import javax.swing.*;
import java.awt.Graphics2D;

/**
   A class for testing the LabeledRectangle class
*/
public class LabeledRectangleTester
{
   /**
      Create and display a LabeledRectangle object
      @param args unused
   */
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();

      frame.setLayout(new BorderLayout());

      JComponent panel = new JComponent()
      {
         public void paintComponent(Graphics g)
         {
            final int REC_LOC = 20;
            final int REC_WIDTH = 100;
            final int REC_HEIGHT = 100;
            LabeledRectangle p =
               new LabeledRectangle(
                  REC_LOC, REC_LOC, REC_WIDTH, REC_HEIGHT, "Impinge");

            p.draw(g);
         }
      };

      final int PANEL_WIDTH = 300;
      final int PANEL_HEIGHT = 300;
      panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

      frame.add(panel, BorderLayout.CENTER);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   }
}