package swing_example.layoutsample;


import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FlowLayoutSample extends JFrame {

	FlowLayoutSample() {
		// タイトル設定
		setTitle("テーブルサンプル");
		// 画面サイズを設定
		setBounds(100, 100, 600, 400);
		// 閉じるボタンがクリックされた時、終了するように設定（デフォルトは非表示）
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// コンテントペインを取得
		Container cont = getContentPane();
		// レイアウトにフローレイアウトを設定
		cont.setLayout(new FlowLayout());
		// ボタンを追加
		cont.add(new JButton("Button1"));
		cont.add(new JButton("Button2"));
		cont.add(new JButton("Button3"));
		cont.add(new JButton("Button4"));
		cont.add(new JButton("Button5"));
	}

}
