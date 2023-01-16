
import processing.core.*;

public class Main extends PApplet{
    Board b = new Board();

	public static void main(String[] args) {
		PApplet.main("Main");
	}
	
	public void settings() {
        size(505,505);
	}

    public void setup() {
        background(187,173,160);
        Integer[][] colors = new Integer[11][3];
        colors[0] = new Integer[] {238, 228, 218};
        colors[1] = new Integer[] {237, 224, 200};
        colors[2] = new Integer[] {242, 177, 121};
        colors[3] = new Integer[] {245, 149, 99};
        colors[4] = new Integer[] {246, 124, 96};
        colors[5] = new Integer[] {246, 94, 59};
        colors[6] = new Integer[] {237, 207, 115};
        colors[7] = new Integer[] {237, 204, 98};
        colors[8] = new Integer[] {237, 200, 80};
        colors[9] = new Integer[] {237, 197, 63};
        colors[10] = new Integer[] {237, 194, 45};
        for (int i = 0; i < 11; i++) {
        	Square.setColor((int)Math.pow(2, i+1), colors[i]);
        }
        
        b.addSquare(Square.generateSquare());
        
    }

    public void draw() {
        drawGrid();

        for (Square s : b.squares) {
            drawSquare(s);
        }

    }
    
    public void drawGrid() {
    	noStroke();
    	fill(204, 192, 179, 23);
    	float startY = 5;
    	float startX = 5;
    	float width = 120;
    	float height = 120;
    	float padding = 5;
    	for (int i = 0; i < 4; i++) {
    		for (int j = 0; j < 4; j++) {
    			rect(startX, startY, width, height);
    			startX += width + padding;
    		}
			startY += (height + padding);
			startX = padding;
    	}
    }
    
    public void drawSquare(Square s) {
    	float[] coords = s.getCoords();
    	Integer[] color = s.getColor();
    	fill(color[0], color[1], color[2]);
    	rect(coords[0], coords[1], s.width, s.height);
    	int[] textColor = s.getTextColor();
    	fill(textColor[0], textColor[1], textColor[2]);
    	textSize(80);
    	text(String.valueOf(s.val), (float)(coords[0] + 40), (float)(coords[1] + 80));
    }
    
    public void keyPressed() {
    	  if (key == CODED) {
    	    if (keyCode == UP) {
    	      b.moveSquares(Direction.up);
    	    } 
    	}
    }
}
