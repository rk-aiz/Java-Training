package swing_example.buttonsample;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JRadioButtonSample extends JFrame {

	private JLabel label;
	
	public static void main(String[] args) {
		JRadioButtonSample frame = new JRadioButtonSample();
		frame.setVisible(true);
	}
	
	public JRadioButtonSample() {
		
		setTitle("ラジオボタンサンプル");
		
		// 画面の表示位置、サイズを設定
		// (画面の左上からの位置(横),画面の左上からの位置(縦),画面の横幅,画面の縦幅)
		setBounds(100, 100, 400, 200);
		
		// 画面のサイズ
		//setSize(400, 200);
		
		// 画面の中央表示 (コンポーネントを基準にした表示位置を設定)
		//setLacationRelativeTo(null);
		
		// 閉じるボタンを押したときに終了する
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// コンテントペインを取得
		Container contentPane = getContentPane();
		
		// レイアウトをフローレイアウトに変更
		contentPane.setLayout(new FlowLayout());
		
		// ラジオボタンを作成
		JRadioButton radio1 = new JRadioButton("On", true); //第二引数で初期状態isSelectedを設定
		JRadioButton radio2 = new JRadioButton("Off");
		JRadioButton radio3 = new JRadioButton("Off");
		
		radio1.addChangeListener(new ButtonListener());
		radio2.addChangeListener(new ButtonListener());
		radio3.addChangeListener(new ButtonListener());
		
//		radio1.addChangeListener(e -> {
//			System.out.println(radio1.getText() + radio1.isSelected());
//		});
		
		ButtonGroup group = new ButtonGroup();
		group.add(radio1);
		group.add(radio2);
		group.add(radio3);
		
		this.label = new JLabel("ラジオボタン");
		
		// コンテントペインに追加
//		contentPane.add(label);
//		contentPane.add(radio1);
//		contentPane.add(radio2);
//		contentPane.add(radio3);
		
		setLayout(new FlowLayout());
		add(label);
		add(radio1);
		add(radio2);
		add(radio3);
		
		
	}
	
	class ButtonListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			if (e.getSource() instanceof JRadioButton radio) {
				boolean checked = radio.isSelected();
				if (checked) {
					radio.setText("On");
				} else {
					radio.setText("Off");
				}
			}
		}
		
		
	}
	
}
