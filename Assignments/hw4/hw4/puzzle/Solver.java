package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author Nguyen Cuong
 */

public class Solver {
    /**
     * @description Constructor which solves the puzzle, computing
     *              everything necessary for moves() and solution() to
     *              not have to solve the problem again.
     *              Solves the puzzle using the A* algorithm. Assumes a solution exists.
     */
    private SearchNode initNode;
    private MinPQ<SearchNode> PQ;
    private int numMovesToGoal;
    private Stack<WorldState> path;
    private HashMap<WorldState, Integer> distanceToGoalCache;
    private HashMap<WorldState, Iterable<WorldState>> neighborsCache;

    private int totalEnqueue;

    public Solver(WorldState worldstate) {
        if (worldstate == null) {
            throw new IllegalArgumentException();
    }
        /* Initialize initNode */
        initNode = new SearchNode(worldstate, 0, null, 0 + worldstate.estimatedDistanceToGoal());
        PQ = new MinPQ<>();
        distanceToGoalCache = new HashMap<>();
        neighborsCache = new HashMap<>();
        numMovesToGoal = 0;
        path = new Stack<>();
        PQ.insert(initNode);
        totalEnqueue = 1;

        while(!PQ.min().worldState.isGoal()) {
            SearchNode currNode = PQ.delMin();
            SearchNode preNode = currNode.getPreviousNode();
            WorldState currWorldState = currNode.getWorldState();
            Iterable<WorldState> neighbors;
            if (neighborsCache.containsKey(currWorldState)) {
                neighbors = neighborsCache.get(currWorldState);
            } else {
                neighbors = currWorldState.neighbors();
                neighborsCache.put(currWorldState, neighbors);
            }

            for (WorldState neighbor : neighbors) {
                if (preNode == null || !neighbor.equals(preNode.getWorldState())) {
                    int numMoves = currNode.getNumMovesToReach() + 1;
                    int priority;
                    if (!distanceToGoalCache.containsKey(neighbor)) {
                        int distanceToGoal = neighbor.estimatedDistanceToGoal();
                        priority = numMoves + distanceToGoal;
                        distanceToGoalCache.put(neighbor, distanceToGoal);
                    } else {
                        priority = numMoves + distanceToGoalCache.get(neighbor);
                    }
                    PQ.insert(new SearchNode(neighbor, numMoves, currNode, priority));
                    ++totalEnqueue;
                }

            }
        }

        SearchNode goalNode = PQ.delMin();
        numMovesToGoal = goalNode.getNumMovesToReach();
        SearchNode tmpNode = goalNode;

        while(tmpNode.getPreviousNode() != null) {
            path.push(tmpNode.getWorldState());
            tmpNode = tmpNode.getPreviousNode();
        }
        path.push(tmpNode.getWorldState());
    }

    private class SearchNode implements Comparable<SearchNode> {
        private WorldState worldState;
        private int numMovesToReach;
        private SearchNode previousNode;
        private int priority;

        public SearchNode(WorldState wS, int numMoves, SearchNode preNode, int prior) {
            worldState = wS;
            numMovesToReach = numMoves;
            previousNode = preNode;
            this.priority = prior;
        }

        public WorldState getWorldState() {
            return worldState;
        }

        public int getNumMovesToReach() {
            return numMovesToReach;
        }

        public SearchNode getPreviousNode() {
            return previousNode;
        }

        public int getPriority() {
            return priority;
        }

        @Override
        public int compareTo(SearchNode that) {
            return this.getPriority() - that.getPriority();
        }
    }

    /**
     * @description Returns the minimum number of moves to solve the puzzle starting at the initial WorldState.
     * @return int
     */
    public int moves() {
        return numMovesToGoal;
    }

    /**
     * @description Returns a sequence of WorldStates from the initial WorldState to the solution.
     * @return
     */
    public Iterable<WorldState> solution() {
        return path;
    }

    public int getTotalEnqueue() {
        return totalEnqueue;
    }
}
