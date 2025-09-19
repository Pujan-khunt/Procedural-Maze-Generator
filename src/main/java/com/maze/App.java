package com.maze;

import com.maze.maze_generator.DFSMazeGenerator;
import com.maze.maze_generator.MazeGenerator;
import com.maze.models.Maze;

public class App {
  public static void main(String[] args) {
    int width = 3, height = 3;
    Maze maze = new Maze(width, height);
    MazeGenerator mazeGenerator = new DFSMazeGenerator();
    mazeGenerator.generate(maze, maze.getMazeCell(0, 0));

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        System.out.println(maze.getMazeCell(i, j).toString());
      }
    }
  }
}
