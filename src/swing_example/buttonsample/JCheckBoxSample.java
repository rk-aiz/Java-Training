package swing_example.buttonsample;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class JCheckBoxSample extends JFrame {

	private JLabel label;
	
	public static void main(String[] args) {
		JCheckBoxSample frame = new JCheckBoxSample();
		frame.setVisible(true);
	}
	
	public JCheckBoxSample() {
		
		setTitle("チェックボックスサンプル");
		setBounds(100, 100, 400, 200);
		
		// 閉じるボタンを押したときに終了する
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ラジオボタンを作成
		JCheckBox checkBox1 = new JCheckBox("赤色", true); //第二引数で初期状態isSelectedを設定
		JCheckBox checkBox2 = new JCheckBox("青色");
		JCheckBox checkBox3 = new JCheckBox("黄色");
		
		checkBox1.addMouseListener(new ButtonListener());
		checkBox2.addMouseListener(new ButtonListener());
		checkBox3.addMouseListener(new ButtonListener());
		
		this.label = new JLabel("チェックボックスのサンプルです");

		setLayout(new FlowLayout());
		add(checkBox1);
		add(checkBox2);
		add(checkBox3);
		add(this.label);
	}
	
	class ButtonListener implements MouseListener {

		private void clicked(MouseEvent e) {			
			if (e.getSource() instanceof JCheckBox checkBox) {
				boolean checked = checkBox.isSelected();
				if (checked) {
					label.setText(checkBox.getText() + "のチェックが付きました　　");
				} else {
					label.setText(checkBox.getText() + "のチェックがなくなりました");
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			this.clicked(e);
		}

		@Override
		public void mousePressed(MouseEvent e) { }

		@Override
		public void mouseReleased(MouseEvent e) { }

		@Override
		public void mouseEntered(MouseEvent e) { }

		@Override
		public void mouseExited(MouseEvent e) { }
	}
}
