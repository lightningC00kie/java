public class Cell {
    char val;
    int row;
    int col;
    int liveNeighbors;
    public Cell(char val, int row, int col) {
        this.val = val; this.row = row; this.col = col; this.liveNeighbors = 0;
    }

    public char computeVal() {
        if (this.val == 'X') {
            if (liveNeighbors < 2) {
                return '_';
            }
            else if (liveNeighbors == 2 || liveNeighbors == 3) {
                return 'X';
            }
            else {
                return '_';
            }
        }
        else if(this.val == '_' && liveNeighbors == 3) {
            return 'X';
        }
        else {
            return '_';
        }
    }

    @Override
    public String toString() {
        return Character.toString(this.val);
    }
}
