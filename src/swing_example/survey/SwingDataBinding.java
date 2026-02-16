package swing_example.survey;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SwingDataBinding {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SurveyData SurveyEntity = new SurveyData();
            SurveyEntity.setName("初期値");

            JFrame frame = new JFrame("Swing データバインディング");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());

            JTextField textField = new JTextField(20);
            JLabel label = new JLabel();

            // モデル → ビュー
//            SurveyData.addPropertyChangeListener(evt -> {
//                if ("name".equals(evt.getPropertyName())) {
//                    textField.setText((String) evt.getNewValue());
//                    label.setText("ラベル: " + evt.getNewValue());
//                }
//            });

            // ビュー → モデル
            textField.getDocument().addDocumentListener(new DocumentListener() {
                private void updateModel() {
                    SurveyEntity.setName(textField.getText());
                }
                public void insertUpdate(DocumentEvent e) { updateModel(); }
                public void removeUpdate(DocumentEvent e) { updateModel(); }
                public void changedUpdate(DocumentEvent e) { updateModel(); }
            });

            // 初期表示
            textField.setText(SurveyEntity.getName());
            label.setText("ラベル: " + SurveyEntity.getName());

            frame.add(new JLabel("名前:"));
            frame.add(textField);
            frame.add(label);

            frame.pack();
            frame.setVisible(true);
        });
    }

}
