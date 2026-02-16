package swing_example.layoutsample;


import java.awt.Container;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GroupLayoutSample extends JFrame {

	public GroupLayoutSample() {
		// タイトル設定
		setTitle("テーブルサンプル");
		// 画面サイズを設定
		setBounds(100, 100, 600, 400);
		// 閉じるボタンがクリックされた時、終了するように設定（デフォルトは非表示）
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// コンテントペインを取得
		Container cont = getContentPane();
		// レイアウトにグループレイアウトを設定
		GroupLayout layout = new GroupLayout(cont);
		cont.setLayout(layout);

		// コンポーネント間の隙間の設定
		layout.setAutoCreateGaps(true);
		// コンテナ間の隙間の設定
		layout.setAutoCreateContainerGaps(true);

		// 表示するコンポーネント
		JLabel lbl = new JLabel("名前");
		JTextField tf = new JTextField();
		tf.setPreferredSize(new Dimension(100, 10));
		JButton btn = new JButton("追加");

		// 水平グループの定義
		//   列の方向に縦に平行に並んでいる要素を定義していく。
		//+- SequentialGroup ---------------------------------+
		//|+---------------+ +--------------+ +--------------+|
		//||ParallelGroup  | |ParallelGroup | |ParallelGroup ||
		//||+-------------+| |+------------+| |+------------+||
		//|||component    || ||component   || ||component   |||
		//|||component    || ||component   || ||component   |||
		//||+-------------+| |+------------+| |+------------+||
		//|+---------------+ +--------------+ +--------------+|
		//+---------------------------------------------------+
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(lbl))
				.addGroup(layout.createParallelGroup()
						.addComponent(tf))
				.addGroup(layout.createParallelGroup()
						.addComponent(btn)));

		// 垂直グループの定義
		//   行の方向に、横に平行に並んでいる要素を定義していく。
		//+-SequentialGroup ------------------------------+
		//|+---------------------------------------------+|
		//||              +-----------------------------+||
		//||ParallelGroup |component component component|||
		//||              +-----------------------------+||
		//|+---------------------------------------------+|
		//|+---------------------------------------------+|
		//||              +-----------------------------+||
		//||ParallelGroup |component component component|||
		//||              +-----------------------------+||
		//|+---------------------------------------------+|
		//+-----------------------------------------------+
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl)
						.addComponent(tf)
						.addComponent(btn)));


	}

}
