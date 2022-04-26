import java.awt.Color;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class BubblePanel extends JPanel {
	
	JSlider slider;
	Random rand = new Random();
	ArrayList<Bubble> bubbleList;
	int size = 25;
	Timer timer;
	int delay = 33;
	
	
	public BubblePanel(){
		timer = new Timer(delay, new BubbleListener());
		bubbleList = new ArrayList<Bubble>();
		setBackground(Color.BLACK);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("Animation speed:");
		add(panel);
		
		JButton btnStartPause = new JButton("Pause");
		btnStartPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btnStPs = (JButton)e.getSource();
				if(btnStPs.getText().equals("Pause")){
					timer.stop();
					btnStPs.setText("Start"); 
				}
				else{
					timer.start();
					btnStPs.setText("Pause");
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Animation speed:");
		lblNewLabel.setToolTipText("Animation speed:");
		panel.add(lblNewLabel);
		
	    slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int speed = slider.getValue() + 1;
				int delay = 1000 /speed;
				timer.setDelay(delay);
			}
		});
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMinorTickSpacing(5);
		slider.setMaximum(120);
		panel.add(slider);
		panel.add(btnStartPause);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bubbleList = new ArrayList<Bubble>();
				repaint();
			}
		});
		panel.add(btnClear);
		addMouseListener(new BubbleListener());
		addMouseMotionListener(new BubbleListener());
		addMouseWheelListener(new BubbleListener());
		timer.start();
	}
	
	public void paintComponent(Graphics canvas){
		super.paintComponent(canvas);
		for(Bubble b: bubbleList){
			b.draw(canvas);
		}
	}
	
	public void testBubbles(){
		for(int i = 0; i < 100; i++){
			int x = rand.nextInt(600);
			int y = rand.nextInt(400);
			int size = rand.nextInt(50);
			bubbleList.add(new Bubble(x, y, size));
		}
		repaint();
	}
	
	private class BubbleListener extends MouseAdapter implements ActionListener{
		public void mousePressed(MouseEvent e){
			bubbleList.add(new Bubble(e.getX(),e.getY(), size));
			repaint();
		}
		
		public void mouseDragged(MouseEvent e){
			bubbleList.add(new Bubble(e.getX(),e.getY(), size));
			repaint();
		}
				
		public void mouseWheelMoved(MouseWheelEvent e){
			if(System.getProperty("os.name").startsWith("Mac"))
				size += e.getUnitsToScroll();
			else
				size -= e.getUnitsToScroll();
			
			if(size <= 3){
				size = 3;
			}
		}
		
		public void actionPerformed(ActionEvent e){
			for(Bubble b: bubbleList)
				b.update();
			repaint();
		}		
			
	}
	
	private class Bubble{
		private int x;
		private int y;
		private int size;
		private Color color;
		private int xSpeed, ySpeed;
		private final int MAX_SPEED = 5;
		
		public Bubble(int X, int Y, int Size){
			x = X;
			y = Y;
			size = Size;
			color = new Color(rand.nextInt(256),
							  rand.nextInt(256),
							  rand.nextInt(256),
							  rand.nextInt(256));
			xSpeed = rand.nextInt(MAX_SPEED * 2 + 1) - MAX_SPEED;
			ySpeed = rand.nextInt(MAX_SPEED * 2 + 1) - MAX_SPEED;
			
			if(xSpeed == 0){
				xSpeed = rand.nextInt(MAX_SPEED * 2 + 1) - MAX_SPEED;
			}
			
			if(ySpeed == 0){
				ySpeed = rand.nextInt(MAX_SPEED * 2 + 1) - MAX_SPEED;
			}
		}
		
		public void draw(Graphics canvas){
			canvas.setColor(color);
			canvas.fillOval(x - size/2, y - size/2, size, size);
			
		}
		
		public void update(){
			x += xSpeed;
			y += ySpeed;
			
			if(x - size/2 <= 0 || x + size/2 >= getWidth())
				xSpeed = -xSpeed;
			
			if(y - size/2 <= 0 || y + size/2 >= getHeight())
				ySpeed = -ySpeed;
		}
	}

}
