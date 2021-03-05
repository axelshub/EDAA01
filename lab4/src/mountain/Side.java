package mountain;

public class Side {
	Point p1;
	Point p2;
	
	public Side(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	 @Override
	 public boolean equals(Object x) {
		 if(x instanceof Side) {
			 Side side = (Side) x;
			 return (side.p1 == p1 && side.p2 == p2 || side.p1 == p2 && side.p2 == p1); //Är equals eller == bäst?
		 }
		 return false;	
	 }
	
	
	 @Override
     public int hashCode() {
         return p1.hashCode() + p2.hashCode();
     }
	
}
