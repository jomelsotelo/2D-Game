/* This is a way to pass a sprite's key information in one entity. (x, y, tag) */

package Data;

public class spriteInfo {
	// Fields
		// TODO: Add private class fields to store x, y (use Vector2D for this) and tag (String) values given in class constructor
		private Vector2D v2d;
		private String tag;
		public int width;
		public int height;
	// Constructor
	public spriteInfo(Vector2D v2d, int width, int height, String tag){
		// TODO: Save the constructor parameters into class fields
		this.v2d = v2d;
		this.tag = tag;
		this.width = width;
		this.height = height;
	}
	
	// Methods
	public String getTag(){
		// TODO: Remove my placeholder code below (which is there to prevent an error) and replace it with returning the value of your private field tag
		return tag;
	}
	
	public Vector2D getCoords(){
		// TODO: Remove my placeholder code below (which is there to prevent an error) and replace it with returning the value of your private field v2d
		return v2d;
	}
	
	public void setTag(String newTag){
		// TODO: Update the value of tag to be the value in newTag (Absolute assignment)
		this.tag = newTag;
	}
	
	public void setCoords(Vector2D newV2D){
		// TODO: Update the value of v2d to be the value in newV2D (Absolute assignment)
		this.v2d = newV2D;
	}
	
	public void setCoords(int x, int y){
		// TODO: Overload the setCoords method to allow another way to set the coordinates. Place the x, y integers into v2d by changing the values of v2d to hold x and y (Absolute assignment)
		this.v2d.setX(x);
		this.v2d.setX(y);
	}
	
	public String toString(){
		// TODO: Create a "toString" method to test. Assume an x, y of 100, 50 and a tag of "star", this should return: [100, 50, star]
			// Remove my placeholder code below (which is there to prevent an error) and replace it with your proper toString method with specifications above
		return "["+ v2d.getX() + ", " + v2d.getY() + ", " + tag + "]";
	}

	public boolean willCollide(spriteInfo x) {
		//TODO
	        int Left1 = getCoords().getX();
	        int Right1 = getCoords().getX() + width;
	        int Top1 = getCoords().getY();
	        int Bottom1 = getCoords().getY() + height;

	        int Left2 = x.getCoords().getX();
	        int Right2 = x.getCoords().getX() + x.width;
	        int Top2 = x.getCoords().getY();
	        int Bottom2 = x.getCoords().getY() + x.height;
	        
	        if (Left1 < Right2 
	        && Right1 > Left2
	        && Top1 < Bottom2
	        && Bottom1 > Top2) {
	            return true;
	        }
	        return false;
	}
}
