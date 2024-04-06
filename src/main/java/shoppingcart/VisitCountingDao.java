package shoppingcart;

public class VisitCountingDao {

	//must be static variable, otherwise times won't increment.
	static int visitCounter = 0;
	
	public int getVisitTime() {
		return ++visitCounter;
	}
	

}
