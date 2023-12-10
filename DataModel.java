import java.util.ArrayList;
import javax.swing.event.*;

/**
 A Subject class for the observer pattern.
 */
public class DataModel
{
    /**
     Constructs a DataModel object
     @param d the data to model
     */
    public DataModel(ArrayList<Double> d)
    {
        data = d;
        listeners = new ArrayList<ChangeListener>();
    }

    /**
     Constructs a DataModel object
     @return the data in an ArrayList
     */
    public ArrayList<Double> getData()
    {
        return (ArrayList<Double>) (data.clone());
    }


    public void attach(ChangeListener c)
    {
        listeners.add(c);
    }

    public void update(int location, double value)
    {
        data.set(location, Double.valueOf(value));
        for (ChangeListener l : listeners)
        {
            l.stateChanged(new ChangeEvent(this));
        }
    }

    ArrayList<Double> data;
    ArrayList<ChangeListener> listeners;
}