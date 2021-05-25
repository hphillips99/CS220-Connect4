import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class Connect4Board extends JFrame {
    
    private Connect4 connect4;

    protected final int MARGIN_SIZE = 35;
    protected final int DOUBLE_MARGIN_SIZE = MARGIN_SIZE*2;
    protected int squareSize = 100;
    private int numRows = 6;
    private int numCols = 7;
    
    private int width = DOUBLE_MARGIN_SIZE + squareSize * numCols;    		
    private int height = DOUBLE_MARGIN_SIZE + squareSize * numRows;

    private void drawGrid(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawRect(MARGIN_SIZE, MARGIN_SIZE, numCols*squareSize, numRows*squareSize);
        for (int c=1; c<numCols; c++) {
            g.drawLine(c*squareSize+MARGIN_SIZE, MARGIN_SIZE, c*squareSize+MARGIN_SIZE, height-MARGIN_SIZE);
        }
        for (int r=1; r<numRows; r++) {
            g.drawLine(MARGIN_SIZE, r*squareSize+MARGIN_SIZE, width-MARGIN_SIZE, r*squareSize+MARGIN_SIZE);
        }
        for (int r=0; r<6; r++) {
            for (int c=0; c<7; c++) {
                if (connect4.get(r, c) == Checker.RED) {
                    g.setColor(Color.RED);
                    g.fillOval(c*squareSize + MARGIN_SIZE, (5-r)*squareSize + MARGIN_SIZE, squareSize, squareSize);
                }
                else if (connect4.get(r, c) == Checker.YELLOW) {
                    g.setColor(Color.YELLOW);
                    g.fillOval(c*squareSize + MARGIN_SIZE, (5-r)*squareSize + MARGIN_SIZE, squareSize, squareSize);
                }
            }
        }
        Font font = new Font("Verdana", Font.BOLD, 40);
		g.setFont(font);
        g.setColor(Color.BLACK);
        if (connect4.winR() == true) {
            g.drawString("RED WINS!", width/3, MARGIN_SIZE);
            connect4.clear();
        }
        if (connect4.winY() == true) {
            g.drawString("YELLOW WINS!", (width/4)+20, MARGIN_SIZE);
            connect4.clear();
        }
        if (connect4.tie() == true) {
            g.drawString("TIE!", width/2-50, MARGIN_SIZE);
            connect4.clear();
        }
    }

    public Connect4Board() {
        connect4 = new Connect4();

        JFrame frame = this;
        
        Component canvas = new JPanel() {
            public Dimension getMinimumSize() {
                return new Dimension(width, height);
            }
            
            /* (non-Javadoc)
             * @see javax.swing.JComponent#getMaximumSize()
             */
            public Dimension getMaximumSize() {
                return new Dimension(width, height);
            }
            
            /* (non-Javadoc)
             * @see javax.swing.JComponent#getPreferredSize()
             */
            public Dimension getPreferredSize() {
                return new Dimension(width, height);
            }
            
            /* (non-Javadoc)
             * @see java.awt.Component#isFocusable()
             */
            public boolean isFocusable() {
                return true;
            }

			@Override
        	public void paint(Graphics graphics) {
        		Graphics2D g = (Graphics2D)graphics;

        		drawGrid(g);

        		//frame.setPreferredSize(new Dimension(numRows*squareSize + MARGIN_SIZE, numCols*squareSize + MARGIN_SIZE));
        		setPreferredSize(new Dimension((numCols+2)*squareSize + 2*MARGIN_SIZE, (numRows+2)*squareSize + 2*MARGIN_SIZE));
        		frame.pack();
        	}
        };
        this.getContentPane().add(canvas, BorderLayout.CENTER);
        this.setResizable(false);
        this.pack();
        this.setLocation(100,100);
        this.setFocusable(true);

        initializeMouseListener();
        
        };

    private void initializeMouseListener() {
         MouseAdapter a = new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {

                System.out.printf("Mouse cliked at (%d, %d)\n", e.getX(), e.getY());

                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (e.getX() >= 5 && e.getX() <105) {
                        System.out.printf("Column 1\n");
                        connect4.set(0);
                    }
                    else if (e.getX() >= 105 && e.getX() <205) {
                        connect4.set(1);
                    }
                    else if (e.getX() >= 205 && e.getX() <305) {
                        connect4.set(2);
                    }
                    else if (e.getX() >= 305 && e.getX() <405) {
                        connect4.set(3);
                    }
                    else if (e.getX() >= 405 && e.getX() <505) {
                        connect4.set(4);
                    }
                    else if (e.getX() >= 505 && e.getX() <605) {
                        connect4.set(5);
                    }
                    else if (e.getX() >= 605 && e.getX() <705) {
                        connect4.set(6);
                    }
                }
                repaint();
            }
         };
         this.addMouseListener(a);
    }    

    public static void main(String[] args) {
        Connect4Board b = new Connect4Board();
        b.setVisible(true);
    }
}
