package swing_example.buttonsample;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JButtonSample extends JFrame {

	public JButtonSample() {
		// タイトルを設定
		setTitle("サンプルアプリ");
		
		// 画面の表示位置、サイズを設定
		// (画面の左上からの位置(横),画面の左上からの位置(縦),画面の横幅,画面の縦幅)
		setBounds(100, 100, 400, 200);
		
		// 画面のサイズ
		//setSize(400, 200);
		
		// 画面の中央表示 (コンポーネントを基準にした表示位置を設定)
		//setLocationRelativeTo(null);
		
		// 閉じるボタンを押したときに終了する
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// コンテントペインを取得
		Container contentPane = getContentPane();
		
		// レイアウトをフローレイアウトに変更
		contentPane.setLayout(new FlowLayout());
		
		// ボタンを作成
		JButton button1 = new JButton("ボタン1");
		JButton button2 = new JButton("ボタン2");
		
		// 匿名クラスでアクションリスナーを追加
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("【匿名クラス版】ボタン1が押されました。");
			}
		});
		
		// ラムダ式でアクションリスナーを追加
		button2.addActionListener(e -> {
			System.out.println("【ラムダ式版】ボタン2が押されました。");
		});
		
		// ボタンにアクションコマンドを設定
		button1.setActionCommand("btn1");
		button2.setActionCommand("btn2");
		
		// ボタンが押された時の処理を追加
//		button1.addActionListener(new ButtonListener());
//		button2.addActionListener(new ButtonListener());
		
		// コンテントペインにボタンを追加
		contentPane.add(button1);
		contentPane.add(button2);
		
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("btn1")) {
				System.out.println("ボタン1が押されました");
			} else if ("btn2".equals(cmd)) {
				System.out.println("ボタン2が押されました");
			}
		}
		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			// 設定したアクションコマンドを取得
//			String cmd = e.getActionCommand();
//			if (cmd.equals("btn1")) {
//				JOptionPane.showMessageDialog(JButtonSample.this, "ボタン1が押されました");
//			} else if ("btn2".equals(cmd)) {
//				JOptionPane.showMessageDialog(JButtonSample.this, "ボタン2が押されました");
//			}
//		}
		
	}
}
