package swing_example.buttonsample;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class JDialogSample extends JFrame {

	public static void main(String[] args) {
		JDialogSample frame = new JDialogSample();
		frame.setVisible(true);
	}
	
	public JDialogSample() {
		
		setTitle("JDialogのサンプル");
		setSize(400, 200);
		
		// 画面の中央表示 (コンポーネントを基準にした表示位置を設定)
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		JButton button1 = new JButton("ボタン1");
		JButton button2 = new JButton("ボタン2");
		
		button1.addActionListener(e -> {
			JDialog dialog = new JDialog(this, "カスタムダイアログ", false); //第三引数 -> モーダル表示かどうか
			dialog.setLocationRelativeTo(button1);
			dialog.setSize(200, 100);
			dialog.add(new JLabel("これはJDialogです"));
			dialog.setVisible(true);
		});
		
		button2.addActionListener(e -> {
			String name = JOptionPane.showInputDialog("あなたの名前は？");
			System.out.println(name);
		});
		
		add(button1);
		add(button2);
		
	}
}
