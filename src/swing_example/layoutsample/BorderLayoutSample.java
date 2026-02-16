package swing_example.layoutsample;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

// JFrameを継承して作成
public class BorderLayoutSample extends JFrame {

	// コンストラクタで作成する画面を作る
	BorderLayoutSample() {
		// タイトル設定
		setTitle("テーブルサンプル");
		// 画面サイズを設定
		setBounds(100, 100, 600, 400);
		// 閉じるボタンがクリックされた時、終了するように設定（デフォルトは非表示）
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// コンテントペインを取得
		Container cont = getContentPane();
		// レイアウトはデフォルトのボーダーレイアウト
		// 5つの場所にボタンを追加
		cont.add(new JButton("NORTH"), BorderLayout.NORTH);
		cont.add(new JButton("WEST"), BorderLayout.WEST);
		cont.add(new JButton("CENTER"), BorderLayout.CENTER);
		cont.add(new JButton("EAST"), BorderLayout.EAST);
		cont.add(new JButton("SOUTH"), BorderLayout.SOUTH);
	}

}
