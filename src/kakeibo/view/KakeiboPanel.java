package kakeibo.view;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import kakeibo.entity.Entity;
import kakeibo.entity.Himoku;
import kakeibo.service.HimokuService;
import kakeibo.service.KakeiboService;

public class KakeiboPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	private final KakeiboService kakeiboService;
	private final HimokuService himokuService;

	/**
	 * Create the panel.
	 */
	public KakeiboPanel(
			KakeiboService kakeiboService,
			HimokuService himokuService) {

		this.kakeiboService = kakeiboService;
		this.himokuService = himokuService;
		
		setLayout(null);
		setSize(726, 433);
		
		DefaultTableModel model = kakeiboService.createTable();
		
		JTable table = new JTable(model) {
			@Override
			public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
				super.changeSelection(rowIndex, -1, toggle, extend);
			}
		};
		table.setSize(690, 280);
		
		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBounds(12, 10, 690, 280);
		
		add(tableScrollPane);
		
		JComboBox<String> himokuComboBox = new JComboBox<>();
		himokuComboBox.setBounds(75, 320, 134, 20);
		add(himokuComboBox);
		
		for (Entity entity : himokuService.getList()) {
			if (entity instanceof Himoku himoku && !himoku.isDeleteflag()) {
				himokuComboBox.addItem(himoku.getHimoku());
			}
		}
		
		JLabel himokuLabel = new JLabel("費目");
		himokuLabel.setBounds(26, 322, 48, 13);
		add(himokuLabel);
		
		JLabel memoLabel = new JLabel("メモ");
		memoLabel.setBounds(26, 355, 48, 13);
		add(memoLabel);
		
		JTextArea memoTextArea = new JTextArea("メモテキストエリア");
		memoTextArea.setSize(50, 15);
		JScrollPane memoScrollPane = new JScrollPane(memoTextArea);
		memoScrollPane.setBounds(75, 353, 260, 39);
		add(memoScrollPane);
		
		JLabel himokuLabel_1 = new JLabel("入出金");
		himokuLabel_1.setBounds(381, 324, 48, 13);
		add(himokuLabel_1);
		
		JLabel himokuLabel_2 = new JLabel("金額");
		himokuLabel_2.setBounds(381, 355, 48, 13);
		add(himokuLabel_2);
		
		textField = new JTextField();
		textField.setBounds(441, 352, 233, 19);
		add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnNyukin = new JRadioButton("入金");
		rdbtnNyukin.setBounds(437, 320, 62, 21);
		add(rdbtnNyukin);
		
		JRadioButton rdbtnShukkin = new JRadioButton("出金");
		rdbtnShukkin.setBounds(503, 320, 62, 21);
		add(rdbtnShukkin);
		
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(rdbtnNyukin);
		radioGroup.add(rdbtnShukkin);
		
		
		//model.addRow(new Object[] {"ビビ", 18, "アラバスタの女王", "なし"});

	}
}
