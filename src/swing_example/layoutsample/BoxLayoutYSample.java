package swing_example.layoutsample;


import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

//JFrameを継承して作成
public class BoxLayoutYSample extends JFrame {

	// コンストラクタで作成する画面を作る
	BoxLayoutYSample() {
		// タイトル設定
		setTitle("テーブルサンプル");
		// 画面サイズを設定
		setBounds(100, 100, 600, 400);
		// 閉じるボタンがクリックされた時、終了するように設定（デフォルトは非表示）
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// コンテントペインを取得
		Container cont = getContentPane();
		// レイアウトにボックスレイアウトを設定（縦方向）
		cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
		// ボタンを追加
		cont.add(new JButton("Button1"));
		cont.add(new JButton("Button2"));
		cont.add(new JButton("Button3"));
		cont.add(new JButton("Button4"));
		cont.add(new JButton("Button5"));
	}
}
