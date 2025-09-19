package com.maze.models;

public class MazeCell {
  boolean left, right, top, bottom;
  boolean visited;

  public MazeCell(boolean left, boolean right, boolean top, boolean bottom) {
    this.left = left;
    this.right = right;
    this.top = top;
    this.bottom = bottom;
    this.visited = false; // All cells are unvisited when created.
  }
}
