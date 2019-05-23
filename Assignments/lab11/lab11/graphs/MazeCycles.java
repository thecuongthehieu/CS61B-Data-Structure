package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int start;
    private int target;
    private boolean circleFound = false;
    private int endVertexOfCircle = Integer.MAX_VALUE;
    private boolean hasFullCircle = false;
    private Maze maze;
    private int[] parent;


    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        parent = new int[maze.V()];

        for (int i = 0; i < maze.V(); ++i) {
            parent[i] = i;
            distTo[i] = 0;
            edgeTo[i] = i;
        }

        int startX = 1;
        int startY = 1;
        int targetX = maze.N();
        int targetY = maze.N();

        start = maze.xyTo1D(startX, startY);
        target = maze.xyTo1D(targetX, targetY);
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        for (int u = start; u <= target; ++u) {
            if (circleFound) {
                break;
            }
            if (!marked[u]) {
                dfs(u);
            }
        }
    }

    // Helper methods go here
    private void dfs(int u) {
        marked[u] = true;

        for (int v : maze.adj(u)) {
            if (!marked[v]) {
                parent[v] = u;
                distTo[v] = distTo[u] + 1;
                dfs(v);
                if (circleFound) {
                    if (v != endVertexOfCircle && !hasFullCircle) {
                        edgeTo[v] = parent[v];
                    } else {
                        hasFullCircle = true;
                    }
                    break;
                }
            } else if (parent[u] != v) {
                circleFound = true;
                endVertexOfCircle = v;
                parent[v] = u;
                edgeTo[v] = parent[v];
                break;
            }
        }
        announce();
    }
}

