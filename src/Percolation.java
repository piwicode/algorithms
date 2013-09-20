/*
 * Solution proposal to coursea Algorithms Part 1
 * Programming Assignment 1: Percolation
 */


/**
 * model a percolation system. By convention, the indices i and j are integers
 * between 1 and N, where (1, 1) is the upper-left site: Throw a
 * java.lang.IndexOutOfBoundsException if either i or j is outside this range.
 * The constructor should take time proportional to N^2; all methods should take
 * constant time plus a constant number of calls to the union-find methods
 * union(), find(), connected(), and count().
 *
 * @author plabatut
 */
public class Percolation {
    private final int n;
    private final WeightedQuickUnionUF uf;
    private final boolean cells[][];
    /**
     * create N-by-N grid, with all sites blocked
     */
    public Percolation(int N) {
        this.n=N;
        this.cells=new boolean[n+2][n+2];
        this.uf=new WeightedQuickUnionUF(n*n+2);
        for(int i = 1 ; i <= N ; i++){
            uf.union(0,cid(1,i));
            uf.union(1,cid(N,i));
        }
    }

    private void checkBounds(int i , int j ){
        if(i<=0||i>n||j<=0||j>n) throw new IllegalArgumentException("("+i+","+j+") not within "+n+" square");
    }
    private int cid(int i , int j ){
        checkBounds(i,j);
        return (i-1)*n+(j-1)+2;
    }
    /**
     * open site (row i, column j) if it is not already
     */
    public void open(int i, int j) {
        checkBounds(i,j);
        cells[i][j]=true;
        if(cells[i-1][j]) uf.union(cid(i,j),cid(i-1,j));
        if(cells[i+1][j]) uf.union(cid(i,j),cid(i+1,j));
        if(cells[i][j-1]) uf.union(cid(i,j),cid(i,j-1));
        if(cells[i][j+1]) uf.union(cid(i,j),cid(i,j+1));
    }

    /**
     * is site (row i, column j) open?
     */
    public boolean isOpen(int i, int j) {
        checkBounds(i,j);        
        return cells[i][j];
    }

    /**
     * is site (row i, column j) full?
     */
    public boolean isFull(int i, int j) {
        checkBounds(i,j);
        return uf.connected(0, cid(i,j));
    }

    /**
     * does the system percolate?
     */
    public boolean percolates() {
        return uf.connected(0,1);
    }
    
    void show(){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){                
                if(cells[i][j]){
                    System.out.print(isFull(i,j) ?'\u25a4': '\u25a2');   
                }else{
                    System.out.print('\u25a0');   
                }
            }            
            System.out.println();
        }
    }

}
