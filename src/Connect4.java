public class Connect4 {
    
    private Checker[][] grid = new Checker[6][7];
    private int count;

    public Connect4() {
        this.count = 1;
        for (int r=0; r<grid.length; r++) {
            for (int c=0; c<grid[r].length; c++) {
                grid[r][c] = Checker.EMPTY;
            }
        }
    }

    public boolean tie() {
        if (count >= 43 && winR() == false && winY() == false) {
            return true;
        }
        return false;
    }

    public void clear() {
        for (int r=0; r<grid.length; r++) {
            for (int c=0; c<grid[r].length; c++) {
                grid[r][c] = Checker.EMPTY;
            }
        }
        count = 1;
    }

    public Checker get(int r, int c) {
        if (r > 5 || r < 0 || c > 6 || c < 0) {
            return Checker.EMPTY;
        }
        return grid[r][c];
    }

    public void set(int c) {
        if (c > 6 || c < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (grid[5][c] != Checker.EMPTY) {
           throw new IndexOutOfBoundsException();
        }
        int i = 0;
        while (grid[i][c] != Checker.EMPTY) {
            i++;
        }
        if (count % 2 == 1) {
            grid[i][c] = Checker.RED;
        }
        else {
            grid[i][c] = Checker.YELLOW;
        }
        count++;
    }

    public boolean winR() {
        //check for horizontal win
        for (int r=0; r<grid.length; r++) {
            for (int c=0; c<4; c++) {
                if (grid[r][c] == Checker.RED && 
                grid[r][c+1] == Checker.RED && 
                grid[r][c+2] == Checker.RED && 
                grid[r][c+3] == Checker.RED) {
                    return true;
                }
            }
        }

        //check for vertical win
        for (int r=0; r<grid.length - 3; r++) {
            for (int c=0; c<grid[r].length; c++) {
                if (grid[r][c] == Checker.RED && 
                grid[r+1][c] == Checker.RED && 
                grid[r+2][c] == Checker.RED && 
                grid[r+3][c] == Checker.RED) {
                    return true;
                }
            }
        }

        //check for diagonal start top left running downward and right
        for (int r=3; r<grid.length; r++) {
            for (int c=0; c<grid[0].length-3; c++) {
                if (grid[r][c] == Checker.RED &&
                grid[r-1][c+1] == Checker.RED &&
                grid[r-2][c+2] == Checker.RED &&
                grid[r-3][c+3] == Checker.RED) {
                    return true;
                }
            }
        }

        //check for diagonal top right running downward and left
        for (int r=grid.length - 1; r<grid.length - 4; r--) {
            for (int c=0; c<grid[r].length - 3; c++) {
                if (grid[r][c] == Checker.RED && 
                grid[r-1][c+1] == Checker.RED && 
                grid[r-2][c+2] == Checker.RED && 
                grid[r-3][c+3] == Checker.RED) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean winY() {
        //check for horizontal win
        for (int r=0; r<grid.length; r++) {
            for (int c=0; c<4; c++) {
                if (grid[r][c] == Checker.YELLOW && 
                grid[r][c+1] == Checker.YELLOW && 
                grid[r][c+2] == Checker.YELLOW && 
                grid[r][c+3] == Checker.YELLOW) {
                    return true;
                }
            }
        }

        //check for vertical win
        for (int r=0; r<grid.length - 3; r++) {
            for (int c=0; c<grid[r].length; c++) {
                if (grid[r][c] == Checker.YELLOW && 
                grid[r+1][c] == Checker.YELLOW && 
                grid[r+2][c] == Checker.YELLOW && 
                grid[r+3][c] == Checker.YELLOW) {
                    return true;
                }
            }
        }

        //check for diagonal start top left running downward and right
        for (int r = 3; r<grid.length; r++) {
            for (int c=0; c<grid[0].length-3; c++) {
                if (grid[r][c] == Checker.YELLOW &&
                grid[r-1][c+1] == Checker.YELLOW &&
                grid[r-2][c+2] == Checker.YELLOW &&
                grid[r-3][c+3] == Checker.YELLOW) {
                    return true;
                }
            }
        }

        //check for diagonal top right running downward and left
        for (int r=grid.length - 1; r<grid.length - 4; r--) {
            for (int c=0; c<grid[r].length - 3; c++) {
                if (grid[r][c] == Checker.YELLOW && 
                grid[r-1][c+1] == Checker.YELLOW && 
                grid[r-2][c+2] == Checker.YELLOW && 
                grid[r-3][c+3] == Checker.YELLOW) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        String result = "";
        for (int r=5; r>=0; r--) {
            for (int c=0; c<grid[r].length; c++) {
                Checker check = get(r, c);
                if (check == Checker.EMPTY) {
                    result += "_ ";
                }
                if (check == Checker.RED) {
                    result += "R ";
                }
                if (check == Checker.YELLOW) {
                    result += "Y ";
                }
            }
            result += "\n";
        }
        return result;
    }
}
