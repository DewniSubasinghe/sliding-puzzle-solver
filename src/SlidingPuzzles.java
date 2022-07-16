import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SlidingPuzzles {

    private static final int road= 0;
    private static final int rock= 1;
    private static final int START= 2;
    private static final int FINISH= 3;
    private static final int PATH= 4;

    private int [][] puzzle;
    private boolean[][] visited;
    private Coordinator start;
    private Coordinator finish;

    public SlidingPuzzles (File maze) throws FileNotFoundException{
        String fileInput= "";
        try(Scanner input= new Scanner(maze)){
            while (input.hasNextLine()){
                fileInput += input.nextLine() + "\n";
            }
        }
        initialisePuzzle(fileInput);
    }

    private void initialisePuzzle(String text){
        if (text== null || (text= text.trim()).length()==0){
            throw new IllegalArgumentException("empty lines data");
        }

        String[] lines= text.split("[\r]?\n");
        puzzle= new int[lines.length][lines[0].length()];
        visited= new boolean[lines.length][lines[0].length()];

        for (int row= 0; row< getHeight(); row++){
            if (lines[row].length() != getWidth()){
                throw new IllegalArgumentException("line " + (row + 1) + "wrong length (was " + lines[row].length() + "but should be " + getWidth() + ")");
            }

            for (int col=0; col< getWidth(); col++) {
                if (lines[row].charAt(col) == '0')
                    puzzle[row][col] = rock;
                else if (lines[row].charAt(col) == 'S') {
                    puzzle[row][col] = START;
                    start = new Coordinator(row, col);
                } else if (lines[row].charAt(col) == 'F') {
                    puzzle[row][col] = FINISH;
                    finish = new Coordinator(row, col);
                } else
                    puzzle[row][col] = road;
            }
        }
    }

    public int getHeight(){
        return puzzle.length;
    }

    public  int getWidth(){
        return puzzle[0].length;
    }

    public Coordinator getEntry(){
        return start;
    }

    public boolean isFinish(int x, int y){
        return x== finish.getX() && y== finish.getY();
    }

    public boolean isStart(int x, int y){
        return x== start.getX() && y== finish.getY();
    }

    public boolean isVisited(int row, int col){
        return visited[row][col];
    }

    public boolean isRock(int row, int col){
        return puzzle[row][col]== rock;
    }

    public void setVisited(int row, int col, boolean value){
        visited[row][col]= value;
    }

    public boolean isValidLocation(int row, int col) {
        if (row< 0 || row>= getHeight() || col< 0 || col>= getWidth()){
            return false;
        }
        return  true;
    }

    public void printPath(List<Coordinator> path){
        int[][] tempPuzzle= Arrays.stream(puzzle)
                .map(int[]::clone)
                .toArray(int[][]::new);
        for (Coordinator coordinator: path){
            if (isStart(coordinator.getX(), coordinator.getY()) || isFinish(coordinator.getX(), coordinator.getY())){
                continue;
            }
            tempPuzzle[coordinator.getX()][coordinator.getY()]= PATH;
        }
        System.out.println(toString(tempPuzzle));
    }

    public String toString(int[][] puzzle){
        StringBuilder result= new StringBuilder(getWidth() * (getHeight() + 1));
        for (int row= 0; row< getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                if (puzzle[row][col] == road) {
                    result.append(' ');
                } else if (puzzle[row][col] == rock) {
                    result.append('0');
                } else if (puzzle[row][col] == START) {
                    result.append('S');
                } else if (puzzle[row][col] == FINISH) {
                    result.append('F');
                } else {
                    result.append('.');
                }
            }
            result.append('\n');
        }
        return result.toString();
    }

    public void reset(){
        for (int i= 0; i< visited.length; i++)
            Arrays.fill(visited[i], false);
    }
}
