package swing_example.panelsample;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JPanelGroupingExample extends JFrame {

	JPanelGroupingExample() {
		
		setTitle("JPanel Grouping Example");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,300);
		setLayout(new BorderLayout()); //フレームのレイアウトを設定
		
		// 上部のパネル(赤背景、ボタン2つ)
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.RED);
		topPanel.add(new JButton("ボタン A"));
		topPanel.add(new JButton("ボタン B"));
		
		// 下部のパネル（青背景、ボタン2つ）
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.BLUE);
		bottomPanel.add(new JButton("ボタン X"));
		bottomPanel.add(new JButton("ボタン Y"));
		
		// フレームにパネルを追加
		add(topPanel, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		
		JPanelGroupingExample frame = new JPanelGroupingExample();
		frame.setVisible(true);
	}
}
