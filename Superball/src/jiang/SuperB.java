package jiang;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.util.*;
import java.util.*;

class Balls {
	int x;
	int y;
	int size;
	int speedX;
	int speedY;
	Color color;
}

public class SuperB extends JFrame {

	public static ArrayList<Balls> myBalls = new ArrayList<Balls>();// arraylist

	static Scanner console = new Scanner(System.in);
	static Random rand = new Random();
	static int R, G, B;
	static int width = 800;
	static int height = 600;
	static int choiceOfShape;
	static int num;// user-how many

	public SuperB() {

		super("wendy");
		setBounds(100, 100, width, height);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void createBalls() {
		// prompt user for shape and how many
		System.out.println("Please select which objects below");
		System.out.print("type 1 for Ball, 2 for Star or 3 for Both ");
		choiceOfShape = console.nextInt();
		System.out.print("how many? ");
		num = console.nextInt();

		for (int i = 0; i < num; i++) {
			Balls b = new Balls();
			R = rand.nextInt(256);
			G = rand.nextInt(256);
			B = rand.nextInt(256);
			b.color = new Color(R, G, B);

			b.x = rand.nextInt(width - 2 * b.size) + b.size;
			b.y = rand.nextInt(height - 2 * b.size) + b.size;
			b.size = rand.nextInt(50) + 10;
			b.speedX = rand.nextInt(30) + 5;
			b.speedY = rand.nextInt(30) + 5;

			myBalls.add(b);
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		int heightMin = getHeight() - getContentPane().getHeight();// new height

		for (int i = 0; i < myBalls.size(); i++) {
			g.setColor(myBalls.get(i).color);

			myBalls.get(i).x += myBalls.get(i).speedX;
			if (myBalls.get(i).x + myBalls.get(i).size > width) {
				myBalls.get(i).speedX = myBalls.get(i).speedX * (-1);
			}
			if (myBalls.get(i).x - myBalls.get(i).size < 0) {
				myBalls.get(i).speedX = myBalls.get(i).speedX * (-1);
			}

			myBalls.get(i).y += myBalls.get(i).speedY;
			if (myBalls.get(i).y + myBalls.get(i).size > height) {
				myBalls.get(i).speedY = myBalls.get(i).speedY * (-1);
			}
			if (myBalls.get(i).y < heightMin) {
				myBalls.get(i).speedY = myBalls.get(i).speedY * (-1);
			}

				if (choiceOfShape == 1) {
				g.fillOval(myBalls.get(i).x, myBalls.get(i).y, myBalls.get(i).size, myBalls.get(i).size);
				}
				if (choiceOfShape == 2) {
				drawStar(g, myBalls.get(i).x, myBalls.get(i).y, myBalls.get(i).size);
				}
				if (choiceOfShape == 3) {

				if (i < myBalls.size() / 2) {
					g.fillOval(myBalls.get(i).x, myBalls.get(i).y, myBalls.get(i).size, myBalls.get(i).size);
				} else { // star method
					drawStar(g, myBalls.get(i).x, myBalls.get(i).y, myBalls.get(i).size);

				}
			}
		} // end of for loop
		try {
			Thread.sleep(100);
		} catch (Exception exc) {
		}
		repaint();

	}

	public void drawStar(Graphics g, int sx, int sy, int size) {

		int[] xCoords = new int[10];
		int[] yCoords = new int[10];
		int ang = 90 - 36;
		double rad;
		double PI = 3.14159;

		for (int j = 0; j < 10; j++) {
			if (j % 2 == 0)
				rad = size * 0.38;
			else
				rad = size;
			xCoords[j] += (int) (rad * Math.cos(PI * ang / 180.0));
			yCoords[j] -= (int) (rad * Math.sin(PI * ang / 180.0));
			ang += 36;
			xCoords[j] += sx;
			yCoords[j] += sy;
		}
		g.fillPolygon(xCoords, yCoords, 10);

	}

	public static void main(String[] args) {
		createBalls();
		new SuperB();
	}
}
