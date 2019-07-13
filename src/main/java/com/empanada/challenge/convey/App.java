package com.empanada.challenge.convey;

import java.util.Random;
import java.util.Scanner;



/**
 * @author ivan
 * 
 * @desc 
 * 
 * About this idea, I have been thinking on a multiThreading app which divides the matrix by 4 
 * This way, each thread will be calculating its quarter of matrix. Save the boolean (isAlive) in a hashmap
 * or hashtable for concurrency and then refresh the matrix.
 * 
 * Enter n of rows
17
Enter n of columns
60
How many generations
200

big console:
40
80
200
 * */

public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter n of rows");
    	int row = sc.nextInt();
    	System.out.println("Enter n of columns");
		int column = sc.nextInt();
		System.out.println("How many generations");
		int generations = sc.nextInt();
		
		Cell [][] world = populateGridRandomly(row, column);
		System.out.println("State of world");
		drawGrid(world);
		
		for (int i = 1; i <= generations; i++) 
		{
			System.out.println("Generation " + i);
			runGeneration(world);
			refreshGrid(world);
			drawGrid(world);
			
			Thread.sleep(350);
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
		}
    }
    
    private static Cell[][] populateGridRandomly(int row, int column) {
    	Cell[][] world = new Cell[row][column];
    	final Random random = new Random();
    	for (int i = 0; i < row; i++) {
    		for(int j = 0; j < column; j++) {
    			world[i][j] = new Cell();
    			Boolean laposta = random.nextBoolean();
    			world[i][j].setAlive(laposta);
    		}
    	}
    	
    	return world;
	}
    
    // Draw some lines 
    private static void drawGrid(Cell[][] world) {
    	final int row = world.length;
    	final int column = world[0].length;
    	for (int i = 0; i < row; i++) {
    		for(int j = 0; j < column; j++) {
    			if(world[i][j].isAlive()) System.out.print("O|");
    			else System.out.print(" |");
    		}
    		System.out.println();
    	}
    	System.out.println("------------------------------------------------------------------");
	}

	private static void runGeneration(Cell[][] world) {
		final int row = world.length;
    	final int column = world[0].length;
    	for (int x = 0; x < row; x++) {
    		for(int y = 0; y < column; y++) {
    			world[x][y] = godPleaseBeMerciful(x, y, world);
    		}
    	}
	}
    
    private static Cell godPleaseBeMerciful(int x, int y, Cell[][] world) {
    	final int neighborsCounter = countNeighbors(world, x, y);
    	final boolean godFinalDesition = godJudgment(neighborsCounter, world[x][y]);
    	world[x][y].setNextState(godFinalDesition);
		return world[x][y];
	}
    
    /**
	 * Retorna la cantidad de vecinos recorriendo @param x e @param y por 
	 * (-1, 0 y +1) 
	 * */
	private static int countNeighbors(Cell[][] world, int x, int y) {
		int counter = 0;
		for (int j = x-1; j <= x+1; j++) {
			for (int i = y-1; i <= y+1; i++) {				
				if(isInBoundariesOfMatrix(j, i, world) 
						&& !(j == x && i == y))
					if(world[j][i].isAlive()) 
						counter = counter + 1;
			}
		}
		return counter;
	}

	private static Boolean godJudgment(int neighborsCounter, Cell cell) {
		if (!cell.isAlive()) {
			if(neighborsCounter == 3) 
				return Boolean.TRUE;
		}else {
			if(neighborsCounter < 2 || neighborsCounter > 3)
				return Boolean.FALSE;
			else
				return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	
	/**
	 * @return true if you have not exceed the limits of the matrix size*/
	private static boolean isInBoundariesOfMatrix(int x, int y, Cell[][] world) {
		final int rowsLength = world.length;
    	final int columnLength = world[0].length;
    	if (x >= 0 && y >= 0)
    		if(x < rowsLength && y < columnLength)
    			return true;
		return false;
	}

	//It only has to set nextstate into actual state 
	private static void refreshGrid(Cell[][] world) {
		final int row = world.length;
    	final int column = world[0].length;
    	for (int i = 0; i < row; i++) {
    		for(int j = 0; j < column; j++) {
    			world[i][j].setAlive(world[i][j].isNextState());
    			world[i][j].setNextState(Boolean.FALSE);
    		}
    	}
	}
    
    
}
