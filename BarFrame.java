import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 A class that implements an Observer object that displays a barchart view of
 a data model.
 */
public class BarFrame extends JPanel implements ChangeListener
{
    public BarFrame(final DataModel dataModel,JFrame mainFrame)
    {
        this.dataModel = dataModel;
        a = dataModel.getData();
        this.setLayout(new FlowLayout());
        this.setSize(100,400);
        setLocation(0,200);

        Icon barIcon = new Icon() {
            public int getIconWidth() { return ICON_WIDTH; }
            public int getIconHeight() { return ICON_HEIGHT; }
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g;
                max = a.get(0);
        
                for (Double value : a) {
                    double val = value;
                    if (val > max)
                        max = val;
                }
        
                double barHeight = getIconHeight() / a.size();
        
                int i = 0;
                for (Double value : a) {
                    double val = value;
                    double barLength = getIconWidth() * val / max;
                    
                    if (i==0) { 
                        g2.setColor(Color.RED);
                    } else if (i == 1) {
                        g2.setColor(Color.YELLOW);
                    } else {
                        g2.setColor(Color.BLUE);
                    }
        
                    Rectangle2D.Double rectangle = new Rectangle2D.Double(0, barHeight * i, barLength, barHeight);
                    i++;
                    g2.fill(rectangle);
                }
            }
        };
        
        MouseListener l = new MouseListener()
        {

            @Override
            public void mouseClicked(MouseEvent e)
            {
                int whichBar = a.size() * e.getY() / getHeight();
                dataModel.update(whichBar, max * e.getX() / getWidth());
            }
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e){}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
        JLabel label = new JLabel(barIcon);
        add(label);
        label.addMouseListener(l);
        mainFrame.add(this);
        mainFrame.setSize(300,300);
        setVisible(true);
    }
    public void stateChanged(ChangeEvent e)
    {
        a = dataModel.getData();
        repaint();
    }

    private ArrayList<Double> a;
    private DataModel dataModel;
    private double max;

    private static final int ICON_WIDTH = 200;
    private static final int ICON_HEIGHT = 200;
}

