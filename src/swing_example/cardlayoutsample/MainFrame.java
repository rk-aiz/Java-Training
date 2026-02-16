package swing_example.cardlayoutsample;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame implements ActionListener {
	
	private JPanel contentPane;
	JPanel cardPanel;
	CardLayout cardLayout;
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}
	
	public MainFrame () {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(672, 473);
		
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // 余白の設定
		this.contentPane.setLayout(null); // 絶対レイアウト
		
		// 上部パネル
		this.cardPanel = new JPanel();
		this.cardLayout = new CardLayout();
		this.cardPanel.setLayout(this.cardLayout);
		
		this.cardPanel.add("パネル1", new Panel1());
		this.cardPanel.add("パネル2", new Panel2());
		this.cardPanel.add("パネル3", new Panel3());
		this.cardPanel.setBounds(12, 10, 628, 274);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(12, 304, 628, 98);
		
		JButton button1 = new JButton("ボタン1");
		button1.addActionListener(this);
		button1.setActionCommand("ボタン1");
		buttonsPanel.add(button1);
		
		JButton button2 = new JButton("ボタン2");
		button2.addActionListener(this);
		button2.setActionCommand("ボタン2");
		buttonsPanel.add(button2);
		
		JButton button3 = new JButton("ボタン3");
		button3.addActionListener(this);
		button3.setActionCommand("ボタン3");
		buttonsPanel.add(button3);
		
		this.contentPane.add(cardPanel);
		this.contentPane.add(buttonsPanel);
		setContentPane(this.contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.cardLayout.show(this.cardPanel, switch(e.getActionCommand()) {
				case "ボタン1" -> "パネル1";
				case "ボタン2" -> "パネル2";
				case "ボタン3" -> "パネル3";
				default -> throw new IllegalArgumentException("Unexpected value: " + e.getActionCommand());
			});
		} catch (IllegalArgumentException ex) {
			
		}
	}
}
