package groupo.travellight.app;

import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import groupo.travellight.yelp.Yelp;

public class YelpSearch extends ActionBarActivity implements SearchView.OnQueryTextListener
{
    private TextView searchReturned;
    private SearchView searchView;
    private MenuItem searchMenu;
    private String responseJSON = "Shit didnt work bitch";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yelp_search);

        searchReturned = (TextView) findViewById(R.id.textView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the options menu from yelp_search.xml
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.yelp_search, menu);

        // Setup private variables
        searchMenu = menu.findItem(R.id.yelp_searchmenu);
        searchView = (SearchView) searchMenu.getActionView();

        // Setup the search view widget
        searchView.setQueryHint("Search Yelp");
        searchView.setOnQueryTextListener(this); //set listener for a yelp search

        return super.onCreateOptionsMenu(menu);
    }

    // Fired when search query is text is changed
    @Override
    public boolean onQueryTextSubmit(String query)
    {
        new SearchYelp().execute(query);
        MenuItemCompat.collapseActionView(searchMenu); //close the search menu
        return false;
    }

    // Fired when search query is submitted with the 'return' key
    @Override
    public boolean onQueryTextChange(String newText)
    {
        //Placeholder for future search suggestions
        return false;
    }


    class SearchYelp extends AsyncTask<String, Void, String>
    {
        String stringJSON;

        protected String doInBackground(String... query) {
            try
            {
                stringJSON = new Yelp().search(query[0], "Mesa, AZ");
            }
            catch(Exception e)
            {
                  System.out.println(e.getMessage());
            }

            return stringJSON;
        }

        protected void onPostExecute(String query) {
            searchReturned.append(query + '\n');
        }
    }

}
