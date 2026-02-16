package swing_example.survey;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SurveyForm {
    private String name;
    private String decade;
    
    private List<String> hobbies = new ArrayList<>();
    private String otherHobby;
    private String device;
    private LocalDateTime createdAt;
    
    public static SurveyForm fromJComponents(List<JComponent> components) {
    	SurveyForm surveyForm = new SurveyForm();
    	String otherHobby = "";
    	
    	for (JComponent c : components) {
    		if (c instanceof JTextField tf) {
    			switch(tf.getName()) {
	    			case "名前" -> { surveyForm.setName(tf.getText()); }
	    			case "その他趣味" -> { otherHobby = tf.getText(); }
    			}
    		}
    		
    		if (c instanceof JComboBox cb) {
    			surveyForm.setDecade(cb.getSelectedItem().toString());
    		}
    		
    		if (c instanceof JCheckBox cb && cb.isSelected()) {
    			surveyForm.hobbies.add(cb.getText());
    		}
    		
			if (c instanceof JRadioButton rb && rb.isSelected()) {
				surveyForm.setDevice(rb.getText());
    		}
    	}
    	
    	for (int i = 0; i < surveyForm.hobbies.size(); i++) {
    		if ("その他".equals(surveyForm.hobbies.get(i))) {
    			surveyForm.hobbies.set(i, ("その他(" + otherHobby + ")"));
    		}
    	}
    	
    	surveyForm.setCreatedAt(LocalDateTime.now());
    	
    	return surveyForm;
    	
    }

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDecade() {
		return decade;
	}

	public void setDecade(String decade) {
		this.decade = decade;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	public String getOtherHobby() {
		return otherHobby;
	}

	public void setOtherHobby(String otherHobby) {
		this.otherHobby = otherHobby;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}
    
    
}
