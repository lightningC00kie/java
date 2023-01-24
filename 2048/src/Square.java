import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;

public class Square extends PApplet {
	int val;
	int[] pos = new int[2];
	int x = (int)this.getCoords()[0];
	int y = (int)this.getCoords()[1];
	private static Map<Integer, Integer[]> colors = new HashMap<Integer, Integer[]>();
	public float width = 120;
	public float height = 120;
	Square left;
	Square right;
	Square up;
	Square down;
	
	public Square(int val, int[] pos) {
		this.val = val; this.pos = pos;
	}
	
	public void move(Direction d) {
		if (d == Direction.up) {
			while (this.up == null && this.pos[1] > 0) {
				this.pos[1] -= 1;
			}
		}
		else if (d == Direction.down) {
			while (this.down == null && this.pos[1] < 3) {
				this.pos[1] += 1;
			}
		}
		else if (d == Direction.left) {
			while (this.left == null && this.pos[0] > 0) {
				this.pos[0] -= 1;
			}
		}
		else if (d == Direction.right) {
			while (this.right == null && this.pos[0] < 3) {
				this.pos[0] += 1;
			}
		}
	}
	
	public float[] getCoords() {
		return new float[] {(this.pos[0] * 120) + ((this.pos[0] + 1) * 5), (this.pos[1] * 120) + ((this.pos[1] + 1) * 5)};
	}
	
	public Integer[] getColor() {
		return colors.get(this.val);
	}
	
	public static void setColor(Integer val, Integer[] color) {
		colors.put(val, color);
	}
	
	public int[] getTextColor() {
		if (this.val == 2 || this.val == 4) {
			return new int[] {119, 110, 101};
		}
		else {
			return new int[] {249, 246, 242};
		}
	}
	
//	void display() {
//    	Integer[] color = this.getColor();
//    	fill(color[0], color[1], color[2]);
//    	rect(x, y, this.width, this.height);
//    	int[] textColor = this.getTextColor();
//    	fill(textColor[0], textColor[1], textColor[2]);
//    	textSize(80);
//    	text(String.valueOf(this.val), (float)(x + 40), (float)(y + 80));
//    	this.slide();
//	}
	
	void slide() {
		if (this.x < this.getCoords()[0]) {
			this.x += 2;
		}
		else if (this.x > this.getCoords()[0]) {
			this.x -= 2;
		}
		else if (this.y < this.getCoords()[1]) {
			this.y += 2;
		}
		else if (this.y > this.getCoords()[1]) {
			this.y -= 2;
		}
	}
}
