package application;

public class Point{

	private int x, y;

	public Point(int intialX, int intialY) {
		this.x = intialX;
		this.y = intialY;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object object) {
		Point other = (Point) object;
		if (this == object) {
			return true;
		}
		// If they truly are identical
		else if (this.getClass() != object.getClass()) {
			return false;
		}
		// If the type of class are not the same, return false
		else if (this.x != other.x || this.y != other.y) {
			return false;
		}
		// If the x or y-coordinates are not equal
		else {
			return true;
		}
	}
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}