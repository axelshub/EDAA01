package mountain;

import java.util.HashMap;

import fractal.*;

public class Mountain extends Fractal {
	private Point a;
	private Point b;
	private Point c;
	private static double dev = 20;
	
	//Map with key = Side and value = Point (midpoint)
	HashMap<Side, Point> map = new HashMap<>();
	
	/** Creates an object that handles Mountain's fractal. 
	 * @param length the length of the triangle side
	 */
	public Mountain(Point a, Point b, Point c) {
		//super();
		this.a = a;
		this.b = b;
		this.c = c;

	}

	/**
	 * Returns the title.
	 * @return the title
	 */
    @Override
	public String getTitle() {
		return "Mountains";
	}

	/** Draws the fractal.  
	 * @param turtle the turtle graphic object
	 */
    @Override
	public void draw(TurtleGraphics turtle) {
		fractalLine(turtle, order, a, b, c, dev);
	}

	/* 
	 * Reursive method: Draws a recursive line of the triangle. 
	 */
	private void fractalLine(TurtleGraphics turtle, int order, Point a, Point b, Point c, double offSet) {
		//TODO
    	
		if (order == 0) {
			
			turtle.moveTo(a.getX(), a.getY());
			turtle.penDown();
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
			
        } else {
        	
        	Point ab = midPoint(a, b, offSet);
        	Point ac = midPoint(a, c, offSet);
        	Point bc = midPoint(b, c, offSet);
        	
        	fractalLine(turtle, order - 1, a, ab, ac, offSet/2);
        	fractalLine(turtle, order - 1, ab, b, bc, offSet/2);
        	fractalLine(turtle, order - 1, ac, bc, c, offSet/2);
        	fractalLine(turtle, order - 1, ab, ac, bc, offSet/2);

        }	
	}
	
	private Point midPoint(Point x, Point y, double offSet) {
		Side side = new Side(x, y);
		if(map.containsKey(side)) { //Searches the map for a Side with endpoints a and b
			//Gets the side found in the map, puts it in a placeholder Point, removes it from the map, returns the placeholder Point
			Point midPoint = map.get(side);
			map.remove(side);
			return midPoint;
		} else {
			//Creates a new midpoint (with offset), puts it in the map, then returns it
			Point midPoint = new Point((x.getX()+ y.getX())/2, (x.getY() + y.getY())/2 + (int)RandomUtilities.randFunc(offSet));
			map.put(side, midPoint);
			return midPoint;
		}
	}
}

