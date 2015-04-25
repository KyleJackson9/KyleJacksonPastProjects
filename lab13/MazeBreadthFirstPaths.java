import java.util.Observable;
/** 
 *  @author Kyle Jackson
 */

public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields: 
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze; 

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;        
    }

    /** Conducts a breadth first search of the maze starting at vertex x. */
    private void bfs(int s) {
    //     marked[s] = true;
    //     announce();

    //     if (s == t) {
    //         targetFound = true;
    //     }

    //     if (targetFound) {
    //         return;
    //     }

    //     for (int w : maze.adj(s)) {
    //         if (!marked[w]) {
    //             edgeTo[w] = s;
    //             announce();
    //             distTo[w] = distTo[s] + 1;
    //             bfs(w);
    //             if (targetFound) {
    //                 return;
    //             }                
    //         }
    //     }
    // }
        Queue<Integer> q = new Queue<Integer>();
        //for (int v = 0; v < maze.adj(s); v++) distTo[v] = INFINITY;
        distTo[s] = 0;
        marked[s] = true;
        announce();
        q.enqueue(s);
        if (s == t) {
            targetFound = true;
        }

        if (targetFound) {
            return;
        }

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    announce();
                    if (w == t) {
                        targetFound = true;
                    }

                    if (targetFound) {
                        return;
                    }
                    q.enqueue(w);
                    //bfs(w);
                }
            }
        }
    }
        // marked[s] = true;
        // announce();
        // for (int w : maze.adj(s)) {
        //     if (!marked[w]) {
        //         edgeTo[w] = s;
        //         announce();
        //         bfs(w);
        //     }
        // }
    


    @Override
    public void solve() {
        bfs(s);
    }
} 

