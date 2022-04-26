	import java.awt.Color;
	import java.util.ArrayList;
	import java.util.Random;
	import javax.swing.JPanel;
	import java.awt.Graphics;
	import java.awt.event.*;


public class PixelPanelDraw extends JPanel {
		
		Random rand = new Random();
		ArrayList<Pixel> pixelList;
		int size = 25;
		
		public PixelPanelDraw(){
			pixelList = new ArrayList<Pixel>();
			setBackground(Color.BLACK);
			addMouseListener(new PixelListener());
			addMouseMotionListener(new PixelListener());
			addMouseWheelListener(new PixelListener());
			
		}
		
		public void paintComponent(Graphics canvas){
			super.paintComponent(canvas);
			for(Pixel p: pixelList){
				p.draw(canvas);
			}
		}
		
		public void testPixels(){
			for(int i = 0; i < 100; i++){
				int x = rand.nextInt(600);
				int y = rand.nextInt(400);
				int size = rand.nextInt(50);
				pixelList.add(new Pixel(x, y, size));
			}
			repaint();
		}
		
		private class PixelListener extends MouseAdapter{
			public void mousePressed(MouseEvent e){
				pixelList.add(new Pixel(e.getX(),e.getY(), size));
				repaint();
			}
			
			public void mouseDragged(MouseEvent e){
				pixelList.add(new Pixel(e.getX(),e.getY(), size));
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
				
		}
		
		private class Pixel{
			private int x;
			private int y;
			private int size;
			private Color color;
			
			public Pixel(int X, int Y, int Size){
				x = (X/Size) * Size + Size/2;
				y = (Y/Size) * Size + Size/2;
				size = Size;
				color = new Color(rand.nextInt(256),
								  rand.nextInt(256),
								  rand.nextInt(256));
			}
			
			public void draw(Graphics canvas){
				canvas.setColor(color);
				canvas.fillRect(x - size/2, y - size/2, size, size);
				
			}
		}
		
	}

