package swing_example.layoutsample;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

//JFrameを継承して作成
public class GridBagLayoutSample extends JFrame {
	// コンストラクタで作成する画面を作る
	GridBagLayoutSample() {
		// タイトル設定
		setTitle("テーブルサンプル");
		// 画面サイズを設定
		setBounds(100, 100, 600, 400);
		// 閉じるボタンがクリックされた時、終了するように設定（デフォルトは非表示）
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// コンテントペインを取得
		Container cont = getContentPane();
		// レイアウトにボックスレイアウトを設定
		GridBagLayout layout = new GridBagLayout();
		cont.setLayout(layout);

		// コンポーネントを配置するクラス
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH; // 縦横にコンポーネットサイズを満たすように配置
		gbc.insets = new Insets(2, 2, 2, 2); // 隙間の設定

		JLabel lblId = new JLabel("ID：");
		gbc.gridx = 0; //位置x
		gbc.gridy = 0; //位置y
		gbc.gridwidth = 2; //表示領域のセル数 横
		gbc.gridheight = 1; //表示領域のセル数 縦
		layout.setConstraints(lblId, gbc);

		JTextField txtId = new JTextField("ID");
		gbc.gridx = 4; //位置x
		gbc.gridy = 0; //位置y
		gbc.gridwidth = 3; //表示領域のセル数 横
		gbc.gridheight = 1; //表示領域のセル数 縦
		layout.setConstraints(txtId, gbc);

		JLabel lblPwd = new JLabel("パスワード：");
		gbc.gridx = 0; //位置x
		gbc.gridy = 1; //位置y
		gbc.gridwidth = 3; //表示領域のセル数 横
		gbc.gridheight = 1; //表示領域のセル数 縦
		layout.setConstraints(lblPwd, gbc);

		JTextField txtPwd = new JTextField("パスワード");
		gbc.gridx = 4; //位置x
		gbc.gridy = 1; //位置y
		gbc.gridwidth = 3; //表示領域のセル数 横
		gbc.gridheight = 1; //表示領域のセル数 縦
		layout.setConstraints(txtPwd, gbc);

		JButton okBtn = new JButton("OK");
		gbc.gridx = 0; //位置x
		gbc.gridy = 2; //位置y
		gbc.gridwidth = 3; //表示領域のセル数 横
		gbc.gridheight = 1; //表示領域のセル数 縦
		okBtn.setPreferredSize(new Dimension(150, 30));
		layout.setConstraints(okBtn, gbc);

		JButton cancelBtn = new JButton("キャンセル");
		gbc.gridx = 4; //位置x
		gbc.gridy = 2; //位置y
		gbc.gridwidth = 3; //表示領域のセル数 横
		gbc.gridheight = 1; //表示領域のセル数 縦
		cancelBtn.setPreferredSize(new Dimension(100, 50));
		layout.setConstraints(cancelBtn, gbc);

		cont.add(lblId);
		cont.add(txtId);
		cont.add(lblPwd);
		cont.add(txtPwd);
		cont.add(okBtn);
		cont.add(cancelBtn);

	}

}
