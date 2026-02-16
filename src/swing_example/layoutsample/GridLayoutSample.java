package swing_example.layoutsample;


import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GridLayoutSample extends JFrame {

	GridLayoutSample() {
		// タイトル設定
		setTitle("テーブルサンプル");
		// 画面サイズを設定
		setBounds(100, 100, 600, 400);
		// 閉じるボタンがクリックされた時、終了するように設定（デフォルトは非表示）
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// コンテントペインを取得
		Container cont = getContentPane();
		// レイアウトにボックスレイアウトを設定
		GridLayout layout = new GridLayout(3, 4); // 3行、4列
		cont.setLayout(layout);

		JButton btn1 = new JButton("ボタン1");
		JButton btn2 = new JButton("ボタン2");
		JButton btn3 = new JButton("ボタン3");
		JButton btn4 = new JButton("ボタン4");
		JButton btn5 = new JButton("ボタン5");
		JButton btn6 = new JButton("ボタン6");
		JButton btn7 = new JButton("ボタン7");
		JButton btn8 = new JButton("ボタン8");
		JButton btn9 = new JButton("ボタン9");
		JButton btn10 = new JButton("ボタン10");

		cont.add(btn1);
		cont.add(btn2);
		cont.add(btn3);
		cont.add(btn4);
		cont.add(btn5);
		cont.add(btn6);
		cont.add(btn7);
		cont.add(btn8);
		cont.add(btn9);
		cont.add(btn10);

	}

}
