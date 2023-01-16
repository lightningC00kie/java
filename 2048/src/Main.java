
import processing.core.*;

public class Main extends PApplet{
	public static void main(String[] args) {
		PApplet.main("Main");
	}
	
	public void settings() {
        size(505,505);
	}

    public void setup() {
        background(187,173,160);
    }

    public void draw() {
        drawGrid();
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
}
