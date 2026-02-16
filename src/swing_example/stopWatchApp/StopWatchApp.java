package swing_example.stopWatchApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.Duration;
import java.time.Instant;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class StopWatchApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel timeLabel; // 時間表示用ラベル
	private JButton swStartBtn = new JButton("Start"); //　スタートボタン
	private JButton swStopBtn = new JButton("Stop"); // ストップボタン
	private JButton swResetBtn = new JButton("Reset"); // リセットボタン
	private Timer timer;
	
	
	private boolean running = false;
	private Instant startTime;
	private Duration elapsedTime = Duration.ZERO;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StopWatchApp frame = new StopWatchApp();
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
	public StopWatchApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel stopwatchPanel = new JPanel();
		contentPane.add(stopwatchPanel);
		stopwatchPanel.setLayout(new BorderLayout(0, 0));
		
		this.timeLabel = new JLabel("00:00:00.0");
		timeLabel.setFont(new Font("Arial", Font.BOLD, 30));
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stopwatchPanel.add(timeLabel, BorderLayout.CENTER);
		
		JPanel swControlPanel = new JPanel();
		stopwatchPanel.add(swControlPanel, BorderLayout.SOUTH);
		swControlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.swStartBtn = new JButton("Start");
		this.swStartBtn.addActionListener(e -> start());
		swControlPanel.add(this.swStartBtn);
		
		this.swStopBtn = new JButton("Stop");
		this.swStopBtn.addActionListener(e -> stop());
		swControlPanel.add(this.swStopBtn);
		
		this.swResetBtn = new JButton("Reset");
		this.swResetBtn.addActionListener(e -> reset());
		swControlPanel.add(this.swResetBtn);

		this.timer = new Timer(100, e -> updateTime());
	}
	
	public void start() {
		if (!this.running) {
			this.startTime = Instant.now().minus(this.elapsedTime);
			this.timer.start();
			this.running = true;
		}
	}
	
	public void stop() {
		if (this.running) {
			this.running = false;
			this.elapsedTime = Duration.between(this.startTime, Instant.now());
			this.timer.stop();
		}
	}
	
	public void reset() {
		this.running = false;
		this.elapsedTime = Duration.ZERO;
		this.timeLabel.setText("00:00:00.0");
	}
	
	public void updateTime() {
		if (this.running) {
			this.elapsedTime = Duration.between(this.startTime, Instant.now());
			this.timeLabel.setText(formatDuration(this.elapsedTime));
		}
	}
	
	private String formatDuration(Duration duration) {
		return String.format("%02d:%02d:%02d.%01d", 
				duration.toHours(),
				duration.toMinutesPart(),
				duration.toSecondsPart(),
				duration.toMillisPart() / 100
				);
	}

}
