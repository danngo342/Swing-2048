package com.game.ngo;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Window implements KeyListener{ //implements KeyListener
	
	
	//might remove private modifier
	private GridLayout grid = new GridLayout(4, 4);
	private JButton[][] btn = new JButton[4][4];
	private JPanel panel = new JPanel();
	private JFrame frame = new JFrame();
	//private AddTile addTile = new AddTile();
	//Tile t = new Tile();
	
	/*
	public JButton[][] getBtn () {
		return this.btn;
	}
	*/
	
	public void makeWindow () {
		makePanel();
		makeFrame();
		makeBoard();
		
		frame.setVisible(true);
	}

	private void makePanel () {
		grid.setHgap(15);
		grid.setVgap(15);
		panel.setLayout(grid);
		panel.setBackground(new Color(206, 189, 174));	//(186, 167, 150) for borders (206, 189, 174) for background of button
		Border border = BorderFactory.createLineBorder(new Color(206, 189, 174), 15, true);
		panel.setBorder(border);
	}
	
	private void makeFrame () {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setResizable(false);
		frame.add(panel);			//fix
	}
	
	private void makeBoard () {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				btn[x][y] = new JButton("");
				btn[x][y].addKeyListener(this);
				formatBgBtn(x, y);
				panel.add(btn[x][y]);
			}
		}
		int initialTiles = 2;
		//formatTile();
		newTile(initialTiles);
	}
	
	private void formatBgBtn (int x, int y) {
		btn[x][y].setContentAreaFilled(false);
		btn[x][y].setFont(new Font("sans-serif", Font.BOLD, 52));
		btn[x][y].setBackground(new Color(245, 237, 231));
	}
	
	/*
	private void formatTile () {
		t.setDimension(panel.getWidth(), panel.getHeight());
		//set Text
	}
	*/
	
	private void displayTile () {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (btn[x][y].getText().isEmpty()) {
					btn[x][y].setContentAreaFilled(false);
				} else {
					btn[x][y].setContentAreaFilled(true);
				}
			}
		}
	}
	
	public void newTile (int limit) {
		Random r = new Random();
		int addedTile = 0;
		while (addedTile < limit) {
			int x = r.nextInt(4);
			int y = r.nextInt(4);
			if (btn[x][y].getText().isEmpty()) {
				btn[x][y].setText("2");
				btn[x][y].setContentAreaFilled(true);
				addedTile++;
			}
		};
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Movement move = new Movement(btn);
		if (e.getKeyCode() == e.VK_UP) {
			move.direction("up");
    	} else if (e.getKeyCode() == e.VK_DOWN) {
			move.direction("down");
    	} else if (e.getKeyCode() == e.VK_RIGHT) {
			move.direction("right");
    	} else if (e.getKeyCode() == e.VK_LEFT) {
			move.direction("left");
    	}
		
		int addedTile = 1;
		if (move.hasReplaced()) {
			newTile(addedTile);	
		}
		displayTile();
		frame.revalidate();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
