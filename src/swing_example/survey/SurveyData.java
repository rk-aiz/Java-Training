package swing_example.survey;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class SurveyData {
	    private String name;
	    private String decade;
	    
	    private List<String> hobbies = new ArrayList();
	    private String otherHobby;
	    private String device;
	    
	    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);


	    public void addPropertyChangeListener(PropertyChangeListener listener) {
	        pcs.addPropertyChangeListener(listener);
	    }
	    
	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        String old = this.name;
	        this.name = name;
	        pcs.firePropertyChange("name", old, name);
	    }
	    
}