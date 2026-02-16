package kakeibo;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import kakeibo.view.HimokuPanel;
import kakeibo.view.KakeiboPanel;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	CardLayout cardLayout;
	private JPanel displayPanel;
	
	public MainPanel() {
		setLayout(null);
		
		// 上部パネル
		displayPanel = new JPanel();
		cardLayout = new CardLayout();
		displayPanel.setLayout(cardLayout);
		
		displayPanel.add("パネル1", new KakeiboPanel());
		displayPanel.add("パネル2", new HimokuPanel());
		displayPanel.setBounds(12, 10, 628, 274);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(12, 304, 628, 98);
		
		JButton button1 = new JButton("ボタン1");
		button1.setActionCommand("ボタン1");
		buttonsPanel.add(button1);
		
		JButton button2 = new JButton("ボタン2");
		button2.setActionCommand("ボタン2");
		buttonsPanel.add(button2);
		
		JButton button3 = new JButton("ボタン3");
		button3.setActionCommand("ボタン3");
		buttonsPanel.add(button3);s
	}

}
