package swing_example.comboboxsample;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class ComboBoxSample extends JFrame {

	public static void main(String[] args) {
		ComboBoxSample frame = new ComboBoxSample();
		frame.setVisible(true);
	}

	ComboBoxSample() {
		
		setTitle("ComboBoxサンプル");
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		JComboBox<String> comboBox = new JComboBox<String>();
		
		comboBox.addItem("寿司");
		comboBox.addItem("豚キムチ");
		comboBox.addItem("豚骨ラーメン");
		comboBox.addItem("カレー");
		comboBox.addItem("焼きそば");
		
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					System.out.println(e.getItemSelectable()); 
				}
			}
		});
		
		contentPane.add(comboBox);
	}
}
