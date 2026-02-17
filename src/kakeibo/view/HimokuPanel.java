package kakeibo.view;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import kakeibo.service.HimokuService;

public class HimokuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HimokuService himokuService;
	private JTextField himokuTextField;
	private JTable himokuTable;
	private JRadioButton rdbtnDeleteTrue;
	private JRadioButton rdbtnDeleteFalse;
	
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public HimokuPanel(HimokuService himokuService) {
		this.himokuService = himokuService;
	
		setLayout(null);
		setSize(726, 433);
		
		model = himokuService.createTable();
		
		himokuTextField = new JTextField();
		himokuTextField.setBounds(84, 321, 258, 19);
		add(himokuTextField);
		himokuTextField.setColumns(10);
		
		himokuTable = new JTable(model) {
			@Override
			public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
				if (himokuTable.getSelectedRow() == rowIndex) {
					himokuTable.clearSelection();
					clearValues();
				} else {
					super.changeSelection(rowIndex, -1, false, false);
				}
			}
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // 全てのセルを編集不可にする
			}
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				super.valueChanged(e);
				
				int index = e.getFirstIndex();
				if (himokuTable.getSelectedRow() == index) {
				setValues(
						model.getValueAt(index, 1).toString(), 
						(boolean)model.getValueAt(index, 2));
				}
			}
		};
		
		himokuTable.setSize(690, 280);
		JScrollPane tableScrollPane = new JScrollPane(himokuTable);
		tableScrollPane.setBounds(12, 10, 690, 280);
		
		add(tableScrollPane);
		
		rdbtnDeleteTrue = new JRadioButton("True");
		rdbtnDeleteTrue.setBounds(520, 320, 59, 21);
		add(rdbtnDeleteTrue);
		
		rdbtnDeleteFalse = new JRadioButton("False");
		rdbtnDeleteFalse.setBounds(589, 320, 72, 21);
		add(rdbtnDeleteFalse);
		
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(rdbtnDeleteTrue);
		radioGroup.add(rdbtnDeleteFalse);
		
		JLabel lblInsertUpdate = new JLabel("費目の登録/変更");
		lblInsertUpdate.setBounds(22, 300, 109, 13);
		add(lblInsertUpdate);
		
		JLabel himokuLabel = new JLabel("費目名");
		himokuLabel.setBounds(22, 324, 50, 13);
		add(himokuLabel);
		
		JButton insertButton = new JButton("登録");
		insertButton.setBounds(251, 350, 91, 21);
		insertButton.addActionListener(e -> {
			String himokuName = himokuTextField.getText();
			if (himokuService.getHimokuId(himokuName) == -1) {
				if (himokuService.insertHimoku(himokuName)) {
					himokuTextField.setText("");
					model.addColumn(himokuService.getData());
				}
			}
		});
		add(insertButton);
		
		JLabel lblDelete = new JLabel("費目の削除");
		lblDelete.setBounds(426, 300, 72, 13);
		add(lblDelete);
		
		JLabel deleteflagLabel = new JLabel("削除フラグ");
		deleteflagLabel.setBounds(426, 324, 72, 13);
		add(deleteflagLabel);
		
		JButton updateButton = new JButton("変更");
		updateButton.setBounds(570, 350, 91, 21);
		updateButton.addActionListener(e -> {
			int id = (int)model.getValueAt(himokuTable.getSelectedRow(), 0);
			String himokuName = (String)model.getValueAt(himokuTable.getSelectedRow(), 1);
			boolean deleteflag = (boolean)model.getValueAt(himokuTable.getSelectedRow(), 2);
			himokuService.updateHimoku(id, himokuName, deleteflag);
		});
		add(updateButton);
	}
	
	private void setValues(String himokuName, boolean deleteflag) {
		himokuTextField.setText(himokuName);
		
		if (deleteflag) {
			rdbtnDeleteTrue.setSelected(true);
		} else {
			rdbtnDeleteFalse.setSelected(true);
		}
	}
	
	private void clearValues() {
		himokuTextField.setText("");
		rdbtnDeleteTrue.setSelected(false);
		rdbtnDeleteFalse.setSelected(false);
	}
}
