package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Board;
import model.Board.Tile;
import model.GamePlay;

public class GUI extends JFrame {

	// images for graphic view
	private static BufferedImage GreenTile;
	private static BufferedImage WhiteTile;
	private static BufferedImage RedPiece;
	private static BufferedImage BlackPiece;
	private static BufferedImage RedQueen;
	private static BufferedImage BlackQueen;
	private static BufferedImage Playable;
	private static BufferedImage Clear;
	private static BoardAsImage boardImage;
	private static GamePlay game;
	
	private int oldX = -1;
	private int oldY = -1;
	private int newX = -1;
	private int newY = -1;

	static {

		// try to read in all of our images
		// this block is the blueprint to be used for all images
		try {
			GreenTile = ImageIO.read(new File("img" + File.separator
					+ "GreenTile.png"));
			WhiteTile = ImageIO.read(new File("img" + File.separator
					+ "WhiteTile.png"));
			RedPiece = ImageIO.read(new File("img" + File.separator
					+ "RedPiece.png"));
			BlackPiece = ImageIO.read(new File("img" + File.separator
					+ "BlackPiece.png"));
			RedQueen = ImageIO.read(new File("img" + File.separator
					+ "RedQueen.png"));
			BlackQueen = ImageIO.read(new File("img" + File.separator
					+ "BlackQueen.png"));
			Playable = ImageIO.read(new File("img" + File.separator
					+ "Playable.png"));
			Clear = ImageIO
					.read(new File("img" + File.separator + "Clear.png"));
		} catch (IOException e) {
			System.out.println("Could not find an image.");
		}
	}

	public static void main(String[] args) {
		new GUI().setVisible(true);
	}

	GUI() {
		game = new GamePlay();
		boardImage = new BoardAsImage();

		// lay out GUI
		this.setSize(425, 450); // size may need to change
		// this.setPreferredSize(new Dimension(735, 550)); // this does not work
		// this.setSize(735, 550);

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Checkers");

		initializeGUIComponents();
		addGUIComponents();

	}

	private void addGUIComponents() {
		// TODO Auto-generated method stub

	}

	private void initializeGUIComponents() {
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLUE);
		this.add(mainPanel);
//		mainPanel.setLayout(null);
		mainPanel.add(boardImage);
		mainPanel.addMouseListener(new MouseEventListener());

	}

	private class MouseEventListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int clickedY = e.getX(); // save the x position of our click
			int clickedX = e.getY(); // save the y position of our click
			System.out.println("X: " + clickedX + "\nY: " + clickedY);
			
			int X = (clickedX-7)/50;
			int Y = (clickedY-7)/50;
			game.clickedTile(X,Y);

			boardImage.paintComponent(boardImage.getGraphics());
			boardImage.repaint();
			game.getBoard().toString();
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private class BoardAsImage extends JPanel {

		BoardAsImage() {
			this.setPreferredSize(new Dimension(8 * 50, 8 * 50));
		}

		public void paintComponent(Graphics g) { // here is where we paint it
			super.paintComponent(g); // normal business
			Graphics2D g2d = (Graphics2D) g;

			BufferedImage newImage; // instantiate our image for painting
			for (int r = 0; r < 8; r++) { // for every row
				for (int c = 0; c < 8; c++) { // for every column

					newImage = chooseImageBackGround(r, c);
					g2d.drawImage(newImage, c * 50, r * 50, this);

					newImage = chooseImage(r, c);
					g2d.drawImage(newImage, c * 50, r * 50, this);
				}
			}

		}
	}

	public BufferedImage chooseImage(int r, int c) {
		if (game.getBoard().getTile(r, c).piece) {
			if (game.getBoard().getTile(r, c).black) {
				if (game.getBoard().getTile(r, c).queen) {
					return BlackQueen;
				} else {
					return BlackPiece;
				}
			}
			else {
				if (game.getBoard().getTile(r, c).queen) {
					return RedQueen;
				} else {
					return RedPiece;
				}
			}
		} 
		return Clear;

	}

	public BufferedImage chooseImageBackGround(int r, int c) {
		if(game.getBoard().getTile(r, c).isPlayable){
			System.out.println("It's trying to show playable");
			return Playable;

		}
		if ((r + c) % 2 != 0) {
			return GreenTile;
		} else {
			return WhiteTile;
		}
	}
}
