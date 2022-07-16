import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Solver {
    private static final int[][] DIRECTIONS= {{0,1}, {1,0}, {0,-1}, {-1,0}};

    public List<Coordinator> solve(SlidingPuzzles slidingPuzzles){
        LinkedList<Coordinator> nextToVisit= new LinkedList<>();
        Coordinator start= slidingPuzzles.getEntry();
        nextToVisit.add(start);

        while (!nextToVisit.isEmpty()){
            Coordinator current= nextToVisit.remove();

            if (! slidingPuzzles.isValidLocation(current.getX(), current.getY()) || slidingPuzzles.isVisited(current.getX(), current.getY())){
                continue;
            }

            if (slidingPuzzles.isRock(current.getX(), current.getY())){
                slidingPuzzles.setVisited(current.getX(), current.getY(), true);
                continue;
            }

            if (slidingPuzzles.isFinish(current.getX(), current.getY())){
                return backtrackPath(current);
            }

            for (int[] direction : DIRECTIONS){
                Coordinator coordinator= new Coordinator(current.getX() + direction[0], current.getY() + direction[1], current);
                nextToVisit.add(coordinator);
                slidingPuzzles.setVisited(current.getX(), current.getY(), true);
            }
        }
        return Collections.emptyList();
    }

    private List<Coordinator> backtrackPath(Coordinator current){
        List<Coordinator> path= new ArrayList<>();
        Coordinator iteration= current;

        while (iteration != null){
            path.add(iteration);
            iteration= iteration.parent;
        }
        return  path;
    }
}
