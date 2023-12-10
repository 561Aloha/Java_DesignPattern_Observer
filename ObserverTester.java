import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;

public class ObserverTester {

    public static void main(String[] args) {

        // Create a JFrame
        JFrame frame = new JFrame("Observer Tester");

        ArrayList<Double> data = new ArrayList<Double>();
        data.add(Double.valueOf(10.0));
        data.add(Double.valueOf(20.0));
        data.add(Double.valueOf(40.0));

        DataModel model = new DataModel(data);
        TextFrame textFrame = new TextFrame(model, frame);
        BarFrame barFrame = new BarFrame(model, frame);

        model.attach(textFrame);
        model.attach(barFrame);

        // Set JFrame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel to hold the TextFrame and BarFrame
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(textFrame, BorderLayout.EAST);
        panel.add(barFrame, BorderLayout.WEST);

        frame.getContentPane().add(panel); // Add the panel to the content pane

        frame.pack(); // Adjust the frame's size based on the added components
        frame.setVisible(true);
    }
}
