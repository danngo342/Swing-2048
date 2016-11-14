package com.game.ngo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

public class Movement { //implements KeyListener

	//might remove private modifier
	private JButton[][] btn = new JButton[4][4];		
	//private JButton[][] oldBtnTxt = new JButton[4][4];			//remove because oldBtnTxt wasn't added onto panel
	private String[][] oldBtnTxt = new String[4][4];
	
	public Movement (JButton[][] btn) {
		this.btn = btn;
		//this.oldBtnTxt = btn;
		this.setOldBtnTxt();
	}
	
	private void setOldBtnTxt () {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				oldBtnTxt[x][y] = btn[x][y].getText();
			}
		}
	}
	
	public void direction (String dir) {
		switch (dir) {
		case "up":
			vertical(dir);
			break;
		case "down":
			vertical(dir);
			break;
		case "right":
			horizontal(dir);
			break;
		case "left":
			horizontal(dir);
			break;
		}
	}
	
	private void vertical (String dir) {
		for (int y = 0; y < 4; y++) {
			if (dir.equals("up")) {
				this.replaceUp(y);
			} else {
				this.replaceDown(y);
			}	
		}
	}

	private void horizontal (String dir) {
		for (int x = 0; x < 4; x++) {
			if (dir.equals("right")) {
				this.replaceRight(x);
			} else {
				this.replaceLeft(x);
			}
		}
	}
	
	private void replaceUp (int y) {
		String current;
		String bottom;
		boolean same;
		boolean bottomPresent;
		
		for (int x = 0; x < 4; x++) {
			//the below for loop replaces one tile at a time
			boolean isBlocked = false;
			for (int xx = x + 1; xx < 4; xx++) {
				current = btn[x][y].getText();
				bottom = btn[xx][y].getText();
				bottomPresent = (!bottom.isEmpty()) ? true : false;
				same = (bottom.equals(current)) ? true : false;
				
				if (bottomPresent && !isBlocked) {
					if (current.isEmpty()) {
						btn[x][y].setText(bottom);
						btn[xx][y].setText("");
					} else if (same) {
						String sum = getSum(current, bottom);
						btn[x][y].setText(sum);
						btn[xx][y].setText("");
					} else {
						isBlocked = true;
					}
				}
			}
		}
	}
	
	private void replaceDown (int y) {
		String current;
		String top;
		boolean same;
		boolean topPresent;
		
		for (int x = 3; x >= 0; x--) {
			//the below for loop replaces one tile at a time
			boolean isBlocked = false;
			for (int xx = x - 1; xx >= 0; xx--) {
				current = btn[x][y].getText();
				top = btn[xx][y].getText();
				topPresent = (!top.isEmpty()) ? true : false;
				same = (top.equals(current)) ? true : false;
				
				if (topPresent && !isBlocked) {
					if (current.isEmpty()) {
						btn[x][y].setText(top);
						btn[xx][y].setText("");
					} else if (same) {
						String sum = getSum(current, top);
						btn[x][y].setText(sum);
						btn[xx][y].setText("");
					} else {
						isBlocked = true;
					}
				}
			}
		}
	}
	
	private void replaceRight (int x) {
		String current;
		String left;
		boolean same;
		boolean leftPresent;
		
		for (int y = 3; y >= 0; y--) {
			//the below for loop replaces one tile at a time
			boolean isBlocked = false;
			for (int yy = y - 1; yy >= 0; yy--) {
				current = btn[x][y].getText();
				left = btn[x][yy].getText();
				leftPresent = ( !left.isEmpty() ) ? true : false;
				same = (left.equals(current)) ? true : false;
				
				if (leftPresent && !isBlocked) {
					if (current.isEmpty()) {
						btn[x][y].setText(left);
						btn[x][yy].setText("");
					} else if (same) {
						String sum = getSum(current, left);
						btn[x][y].setText(sum);
						btn[x][yy].setText("");
					} else {
						isBlocked = true;
					}
				}
			}
		}
	}
	
	private void replaceLeft (int x) {
		String current;
		String right;
		boolean same;
		boolean rightPresent;
		
		for (int y = 0; y < 4; y++) {
			//the below for loop replaces one tile at a time
			boolean isBlocked = false;
			for (int yy = y + 1; yy < 4; yy++) {
				current = btn[x][y].getText();
				right = btn[x][yy].getText();
				rightPresent = ( !right.isEmpty() ) ? true : false;
				same = (right.equals(current)) ? true : false;
				
				if (rightPresent && !isBlocked) {
					if (current.isEmpty()) {
						btn[x][y].setText(right);
						btn[x][yy].setText("");
					} else if (same) {
						String sum = getSum(current, right);
						btn[x][y].setText(sum);
						btn[x][yy].setText("");
					} else {
						isBlocked = true;
					}
				}
			}
		}
	}
	
	private String getSum (String current, String bottom) {
		int sum = Integer.parseInt(current) + Integer.parseInt(bottom);
		String sumStr = String.valueOf(sum);
		return sumStr;
	}
	
	public boolean hasReplaced () {
		String updated = "";
		boolean replaced = false;
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				updated = btn[x][y].getText();
				if ( !oldBtnTxt[x][y].equals(updated) ) {
					replaced = true;
				}
			}
		}
		
		return replaced;
	}

	/*
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_UP) {
			this.direction("up");
    	} else if (e.getKeyCode() == e.VK_DOWN) {
			this.direction("down");
    	} else if (e.getKeyCode() == e.VK_RIGHT) {
			this.direction("right");
    	} else if (e.getKeyCode() == e.VK_LEFT) {
			this.direction("left");
    	}
		
		int addedTile = 1;
		if (this.hasReplaced()) {
			AddTile add = new AddTile();
			add.newTile(addedTile);	
		}
		//this.displayTile();
		//frame.revalidate(); use repaint instead, revalidate the scores
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	*/
}