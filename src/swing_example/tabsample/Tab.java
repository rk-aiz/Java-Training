package swing_example.tabsample;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class Tab extends JFrame {
	
	public static void main(String[] args) {
		Tab tab = new Tab();
		tab.setVisible(true);
	}

	public Tab() {
		
		setTitle("JTabbedPaneのサンプル");
		setSize(250, 160);
		setLocationRelativeTo(rootPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabpane = new JTabbedPane();
		tabpane.addChangeListener(e -> {
			int selectedIndex = tabpane.getSelectedIndex();
			System.out.println("選択中のタブ : " + (selectedIndex + 1) + "ページ目");
			//Toolkit.getDefaultToolkit().beep();
		});
		
		// 1ページ目
		JPanel panel1 = new JPanel();
		JTextArea textArea = new JTextArea(5, 10);
		textArea.setText("タブの1ページ目");
		panel1.add(textArea);
		
		// 2ページ目
		JPanel panel2 = new JPanel();
		JLabel label1 = new JLabel();
		label1.setText("タブの2ページ目");
		panel2.add(label1);
		
		// 3ページ目
		JPanel panel3 = new JPanel();
		String[] foods = {"かつ丼", "うどん", "天丼", "ライス", "カレー"};
		//String fools[] = {"かつ丼", "うどん", "天丼", "ライス", "カレー"};
		
		JList<String> listBox = new JList<>(foods);
		panel3.add(listBox);
		
		tabpane.add("1ページ", panel1);
		tabpane.add("2ページ", panel2);
		tabpane.add("3ページ", panel3);
		
		add(tabpane);
	}
}
