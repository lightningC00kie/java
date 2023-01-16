import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Square {
	int val;
	int[] pos = new int[2];
	private static Map<Integer, Integer[]> colors = new HashMap<Integer, Integer[]>();
	public float width = 120;
	public float height = 120;
	
	public Square(int val, int[] pos) {
		this.val = val; this.pos = pos;
	}
	
	public void move(Direction d) {
		if (d == Direction.up) {
			this.pos[1] -= 1;
		}
		else if (d == Direction.down) {
			this.pos[1] += 1;
		}
		else if (d == Direction.left) {
			this.pos[0] -= 1;
		}
		else if (d == Direction.right) {
			this.pos[0] += 1;
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
	
	public static Square generateSquare() {
		Random rand = new Random();
		int randInt = rand.nextInt(2);
		if (randInt == 0) {
			return new Square(2, new int[] {1, 1});
		}
		return new Square(4, new int[] {1, 1});
	}
}
