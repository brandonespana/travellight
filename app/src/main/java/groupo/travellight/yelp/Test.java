
public class Test 
{

	  // This is used as a debug test to make sure it is working
	  public static void main(String[] args) 
	  {
	    // These tokens are licensed to Travelight
	    String consumerKey = "NRZPI68jDX2-ss3mWLOSfw";
	    String consumerSecret = "Ud3mTCdP3wQw1LSN8AN9ClX5L-c";
	    String token = "9iaQQG7aesjpu7JfWsyxFam2pbzSylut";
	    String tokenSecret = "bixiqIK5iduP6nQhd7owJmkMATE";

	    Yelp yelp = new Yelp(consumerKey, consumerSecret, token, tokenSecret); //create a new yelp object
	    String response = yelp.search("Bikes", "Mesa, AZ"); //search for bikes in Mesa, AZ
	    System.out.println(response); //print the response to the console
	  }

}
