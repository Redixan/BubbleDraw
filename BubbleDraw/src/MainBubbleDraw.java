import javax.swing.JFrame;

public class MainBubbleDraw extends JFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("BubbleMubbleDRAW");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new BubblePanel());
		frame.setSize(new java.awt.Dimension(600,400));
		frame.setVisible(true);
		frame.setResizable(false);
	}

}
