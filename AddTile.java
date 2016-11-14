package com.game.ngo;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AddTile extends JPanel{

	Random r = new Random();
	//Tile t = new Tile();
	int x, y;
	JButton[][] btn = new JButton[4][4];
	
	public AddTile (JButton[][] btn) {
		/*
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				this.btn[i][j] = btn[i][j];
			}
		}
		*/
	}
	
	public void newTile (int limit) {
		int addedTile = 0;
		
		while (addedTile < limit) {
			x = r.nextInt(4);
			y = r.nextInt(4);
			
			if (btn[x][y].getText().isEmpty()) {
				btn[x][y].setText("2");
				btn[x][y].setContentAreaFilled(true);
				addedTile++;
			}
			
		};
	}
	
}
