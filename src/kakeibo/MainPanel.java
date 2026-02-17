package kakeibo;

import java.awt.CardLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JPanel;

import kakeibo.view.HimokuPanel;
import kakeibo.view.KakeiboPanel;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	CardLayout cardLayout;
	private JPanel displayPanel;
	
	public MainPanel() {
		setBounds(new Rectangle(0, 0, 750, 500));
		setLayout(null);
		
		// 上部パネル
		displayPanel = new JPanel();
		cardLayout = new CardLayout();
		displayPanel.setLayout(cardLayout);
		
		displayPanel.add("家計簿パネル", new KakeiboPanel());
		displayPanel.add("費目パネル", new HimokuPanel());
		displayPanel.setBounds(12, 10, 726, 420);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(null);
		buttonsPanel.setBounds(12, 440, 359, 37);
		
		JButton button1 = new JButton("家計簿");
		button1.setBounds(33, 7, 138, 24);
		buttonsPanel.add(button1);
		
		JButton button2 = new JButton("費目");
		button2.setBounds(183, 7, 138, 24);
		buttonsPanel.add(button2);
		
		add(displayPanel);
		add(buttonsPanel);
	}

}
