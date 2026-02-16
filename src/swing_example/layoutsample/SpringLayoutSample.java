package swing_example.layoutsample;


import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SpringLayout;

public class SpringLayoutSample extends JFrame {

	SpringLayoutSample() {
		// タイトル設定
		setTitle("テーブルサンプル");
		// 画面サイズを設定
		setBounds(100, 100, 600, 400);
		// 閉じるボタンがクリックされた時、終了するように設定（デフォルトは非表示）
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// コンテントペインを取得
		Container cont = getContentPane();
		// レイアウトにスプリングレイアウトを設定
		SpringLayout layout = new SpringLayout();
		cont.setLayout(layout);

		// コンポーネント
		JButton btn1 = new JButton("ボタン1");
		JButton btn2 = new JButton("ボタン2");
		JButton btn3 = new JButton("ボタン3");
		JButton btn4 = new JButton("ボタン4");

		// btn1
		// btn1とコンテントペインのNORTHの幅を指定
		layout.putConstraint(SpringLayout.NORTH, btn1, 10, SpringLayout.NORTH, cont);
		// btn1とコンテントペインのWESTの幅を指定
		layout.putConstraint(SpringLayout.WEST, btn1, 10, SpringLayout.WEST, cont);

		// btn2
		// btn2とコンテントペインのNORTHの幅を指定
		layout.putConstraint(SpringLayout.NORTH, btn2, 10, SpringLayout.NORTH, cont);
		// btn2とコンテントペインのEASTの幅を指定
		layout.putConstraint(SpringLayout.EAST, btn2, -10, SpringLayout.EAST, cont);
		// btn2のWESTとbtn1のEASTの幅を指定
		layout.putConstraint(SpringLayout.WEST, btn2, 10, SpringLayout.EAST, btn1);

		// btn3
		// btn3のNORTHとbtn2のSOUTHの幅を指定
		layout.putConstraint(SpringLayout.NORTH, btn3, 10, SpringLayout.SOUTH, btn2);
		// btn3のEASTとbtn2のEASTの幅を指定
		layout.putConstraint(SpringLayout.EAST, btn3, 0, SpringLayout.EAST, btn2);

		// btn4
		// btn4のNORTHとbtn3のSOUTHの幅を指定
		layout.putConstraint(SpringLayout.NORTH, btn4, 10, SpringLayout.SOUTH, btn3);
		// btn4とコンテントペインのSOUTHの幅を指定
		layout.putConstraint(SpringLayout.SOUTH, btn4, -10, SpringLayout.SOUTH, cont);
		// btn4とコンテントペインのWESTの幅を指定
		layout.putConstraint(SpringLayout.WEST, btn4, 10, SpringLayout.WEST, cont);
		// btn4とコンテントペインのEASTの幅を指定
		layout.putConstraint(SpringLayout.EAST, btn4, -10, SpringLayout.EAST, cont);

		cont.add(btn1);
		cont.add(btn2);
		cont.add(btn3);
		cont.add(btn4);

	}

}
