import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws Exception{
        //getting the start time.
        Instant start= Instant.now();

        //text file inputs which represent the puzzles.

        File maze1= new File("D:\\w1839508_20201293_Dewni_Subasinghe\\src\\maze10_3.txt");
        File maze2= new File("D:\\w1839508_20201293_Dewni_Subasinghe\\src\\puzzle_20.txt");
        File maze3= new File("D:\\w1839508_20201293_Dewni_Subasinghe\\src\\puzzle_40.txt");
        File maze4= new File("D:\\w1839508_20201293_Dewni_Subasinghe\\src\\puzzle_80.txt");
        File maze5= new File("D:\\w1839508_20201293_Dewni_Subasinghe\\src\\puzzle_160.txt");
        File maze6= new File("D:\\w1839508_20201293_Dewni_Subasinghe\\src\\puzzle_320.txt");
        File maze7= new File("D:\\w1839508_20201293_Dewni_Subasinghe\\src\\puzzle_640.txt");
        File maze8= new File("D:\\w1839508_20201293_Dewni_Subasinghe\\src\\puzzle_1280.txt");
        File maze9= new File("D:\\w1839508_20201293_Dewni_Subasinghe\\src\\puzzle_2560.txt");

        //calling execution method one by one here. To track the runtime one by one, all the other execution calls are commented except for the one that want to be analysed.

//        execute(maze1);
//        execute(maze2);
//        execute(maze3);
        execute(maze4);
//        execute(maze5);
//        execute(maze6);
//        execute(maze7);
//        execute(maze8);
//        execute(maze9);

        //getting the finished time of the programme.
        Instant end= Instant.now();
        Duration timeElapsed= Duration.between(start, end);
        System.out.println("Time taken: " + timeElapsed.toMillis() + " milliseconds");

    }
    // execution method
    private static void execute (File file) throws Exception{
        SlidingPuzzles slidingPuzzles= new SlidingPuzzles(file);
        solver(slidingPuzzles);
    }
    //calling Breadth-first approach
    private static void solver(SlidingPuzzles slidingPuzzles){
        Solver solver=new Solver();
        List<Coordinator> path= solver.solve(slidingPuzzles);
        slidingPuzzles.printPath(path);
        slidingPuzzles.reset();
    }
}
