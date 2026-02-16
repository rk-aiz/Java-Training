package swing_example.layoutsample;


import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.OverlayLayout;

public class OverlayLayoutSample extends JFrame {

	public OverlayLayoutSample() {
		// タイトル設定
		setTitle("テーブルサンプル");
		// 画面サイズを設定
		setBounds(100, 100, 600, 400);
		// 閉じるボタンがクリックされた時、終了するように設定（デフォルトは非表示）
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// コンテントペインを取得
		Container cont = getContentPane();
		// レイアウトにグループレイアウトを設定
		OverlayLayout layout = new OverlayLayout(cont);
		cont.setLayout(layout);

		JLabel lbl = new JLabel(new ImageIcon("img/sample.jpg"));
		JButton btn = new JButton("ボタン");
		cont.add(lbl);
		cont.add(btn);

	}

}
