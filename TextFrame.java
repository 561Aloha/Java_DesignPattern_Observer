import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

/**
 A class for displaying the model as a column of textfields in a frame.
 */
public class TextFrame extends JPanel implements ChangeListener {
    public TextFrame(DataModel d, JFrame mainFrame)
    {
        dataModel = d;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        ArrayList<Double> a = dataModel.getData();
        fieldList = new JTextField[a.size()];

        // A listener for action events in the text fields
        ActionListener l = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // Figure out which field generated the event
                JTextField c = (JTextField) e.getSource();
                int i = 0;
                int count = fieldList.length;
                while (i < count && fieldList[i] != c)
                    i++;

                String text = c.getText().trim();

                try
                {
                    double value = Double.parseDouble(text);
                    dataModel.update(i, value);
                }
                catch (Exception exc)
                {
                    c.setText("Error.  No update");
                }
            }
        };

        final int FIELD_WIDTH = 11;
        for (int i = 0; i < a.size(); i++)
        {
            JTextField textField = new JTextField(a.get(i).toString(),FIELD_WIDTH);
            textField.addActionListener(l);
            add(textField);
            fieldList[i] = textField;
        }

        mainFrame.add(this);
    }

    public void stateChanged (ChangeEvent e)
    {
        ArrayList a = dataModel.getData();
        int i = 0;
        for (JTextField t : fieldList)
        {
            Double d = (Double) a.get(i);
            i++;
            t.setText(d.toString());
        }
    }

    DataModel dataModel;
    JTextField[] fieldList;
}