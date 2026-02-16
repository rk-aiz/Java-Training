package swing_example.windowbuildersample;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Sample01 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private LocalDateTime start = LocalDateTime.now();
	private boolean reset = false;
	private boolean stop = false;
	private long timerSeconds = 0;
	private boolean timerReset = false;
	
	JCheckBox isStopWatchCheckBox = new JCheckBox("ストップウォッチ");
	JLabel timeDisplay;
	JSpinner minuteSpinner = new JSpinner();
    Timer timer = new Timer(100, e -> {
    	if (stop) return;
    	Duration duration = Duration.between(start, LocalDateTime.now());
        
        if (e.getSource() instanceof Timer t && t.getActionCommand().equals("StopWatch")) {
        	
	        timeDisplay.setText(
	    		String.format(
	        		"%02d:%02d:%02d.%02d", 
	        		duration.toHoursPart(),
	        		duration.toMinutesPart(),
	        		duration.toSecondsPart(),
	        		duration.toMillisPart()));
        } else {
        	long dispSeconds = this.timerSeconds - duration.toSeconds();
        	
	        timeDisplay.setText(toTimeString(dispSeconds));
        }
    });
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sample01 frame = new Sample01();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Sample01() {
		setTitle("タイマー");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		minuteSpinner.setModel(new SpinnerNumberModel(3, 0, 120, 1));
		minuteSpinner.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		minuteSpinner.setBounds(40, 17, 80, 31);
		contentPane.add(minuteSpinner);
		
		JLabel minuteLabel = new JLabel("分");
		minuteLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		minuteLabel.setBounds(144, 20, 50, 25);
		contentPane.add(minuteLabel);
		
		JButton startButton = new JButton("Start");
		startButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		startButton.setBounds(12, 153, 120, 25);
		contentPane.add(startButton);
		
		timeDisplay = new JLabel(toTimeString(60 * toInt(minuteSpinner.getValue())));
		timeDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		timeDisplay.setFont(new Font("Consolas", Font.PLAIN, 50));
		timeDisplay.setBounds(12, 70, 411, 73);
		contentPane.add(timeDisplay);
				

		
		JButton pauseButton = new JButton("Pause");
		pauseButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		pauseButton.setBounds(157, 153, 120, 25);
		contentPane.add(pauseButton);
		
		JButton resetButton = new JButton("Stop");
		resetButton.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		resetButton.setBounds(303, 153, 120, 25);
		contentPane.add(resetButton);
		
		
		isStopWatchCheckBox.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		isStopWatchCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		isStopWatchCheckBox.setBounds(303, 17, 114, 25);
		contentPane.add(isStopWatchCheckBox);
        
		startButton.addActionListener(e -> { 
        	if (this.reset) {
        		this.start = LocalDateTime.now();
        		this.reset = false;            	
    		}
        	
        	if (this.timerReset) {
        		
        		//int sec = this.minuteSpinner.getValue();
        		
        		//this.start = this.timerSeconds - Duration.between(start, LocalDateTime.now()).toSeconds();
        	}
        	
        	stop = false;
        	
			timer.start();
		});
		
		pauseButton.addActionListener(e -> { 
			stop = true;
			timer.stop();
			
		});
		
		resetButton.addActionListener(e -> {
			timer.stop();
			reset = true;
		});
		
		isStopWatchCheckBox.addMouseListener(new TimerMouseListener());
		minuteSpinner.addChangeListener(e -> resetTimeDisplay());
		
		updateTimerActionCommand();
		this.reset = true;
	}
	
	private int toInt(Object obj) {
		if (obj instanceof Integer num) {
			return num;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	private String toTimeString(long dispSeconds) {
		return String.format(
				"%02d:%02d",
				dispSeconds / 60,
				dispSeconds % 60);
	}
	
	private void resetTimeDisplay() {
		timer.stop();
		if (isStopWatchCheckBox.isSelected()) {
			timeDisplay.setText("00:00:00.000");
		} else {
			timeDisplay.setText(toTimeString(60 * toInt(minuteSpinner.getValue())));
		}
	}
	
	private void updateTimerActionCommand() {
		if (this.timer != null) {
			if (isStopWatchCheckBox.isSelected()) {
				this.timer.setActionCommand("StopWatch");
			} else {
				this.timer.setActionCommand("Timer");
			}
		}
	}
	
	class TimerMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			resetTimeDisplay();
			
			if (isStopWatchCheckBox.isSelected()) {
				timer.setActionCommand("StopWatch");
			} else {
				timer.setActionCommand("Timer");
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}
		
	}
}
