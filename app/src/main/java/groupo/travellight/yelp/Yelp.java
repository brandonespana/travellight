import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class Yelp 
{

  private OAuthService service;
  private Token accessToken;
  
  /**
   * Setup the Yelp API OAuth credentials.
   *
   * @param consumerKey Consumer key
   * @param consumerSecret Consumer secret
   * @param token Token
   * @param tokenSecret Token secret
   */
  public Yelp(String consumerKey, String consumerSecret, String token, String tokenSecret) 
  {
    this.service = new ServiceBuilder().provider(YelpApi2.class).apiKey(consumerKey).apiSecret(consumerSecret).build();
    this.accessToken = new Token(token, tokenSecret);
  }

  /**
   * Search with term and location
   * 
   * @param term The search term to use
   * @param location 
   * @return JSON string response
   */
  public String search(String term, String location)
  {
	  OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
	  request.addQuerystringParameter("term", term);
	  request.addQuerystringParameter("location", location);
	  this.service.signRequest(this.accessToken, request);
	  Response response = request.send();
	  return response.getBody();
  }
  
}
