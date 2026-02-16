package swing_example.buttonsample;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GetSourceSample extends JFrame {

	public static void main(String[] args) {
		GetSourceSample frame = new GetSourceSample();
		frame.setVisible(true);
	}
	
	public GetSourceSample() {
		
		setTitle("GetSource()のサンプル");
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		JButton button1 = new JButton("ボタン1");
		JButton button2 = new JButton("ボタン2");
		
		JTextField textField = new JTextField(15);
		JCheckBox checkBox = new JCheckBox("チェックボックス");
		
		button1.addActionListener(new ButtonListener());
		button2.addActionListener(new ButtonListener());
		textField.addActionListener(new ButtonListener());
		checkBox.addActionListener(new ButtonListener());
		
		add(button1);
		add(button2);
		add(textField);
		add(checkBox);
		
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			
			Consumer<String> showDialog = msg -> JOptionPane.showMessageDialog(GetSourceSample.this, msg);
			
			switch(source) {
				case JButton b -> {
					showDialog.accept("クリックされたのは" + b.getText());
				}
				case JTextField tf -> {
					JOptionPane.showMessageDialog(null, "入力されたテキスト:" + tf.getText());
				}
				case JCheckBox cb -> {
					JOptionPane.showMessageDialog(null, "チェックボックスの状態:" + cb.isSelected());
				}
				default -> throw new IllegalArgumentException("Unexpected value: " + source);
			}
		}
		
	}
}
