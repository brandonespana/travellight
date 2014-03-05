package groupo.travellight.yelp;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 *This class provides the authentication, communication interface, and parsing logic
 * for Yelp API v2.
 *
 * @author Brant Unger
 * @version 0.2
 */
public class Yelp 
{
    private OAuthService service;
    private Token accessToken;

    // These tokens are licensed to Travelight
    String consumerKey = "NRZPI68jDX2-ss3mWLOSfw";
    String consumerSecret = "Ud3mTCdP3wQw1LSN8AN9ClX5L-c";
    String token = "9iaQQG7aesjpu7JfWsyxFam2pbzSylut";
    String tokenSecret = "bixiqIK5iduP6nQhd7owJmkMATE";

    /**
     * Setup the Yelp API OAuth credentials.
     */
    public Yelp()
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
