package groupo.travellight.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Sets up for searching yelp
 * @author Brant Unger
 * @version 0.3
 */
public class YelpSearch extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yelp_search);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the options menu from yelp_search.xml
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.yelp_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_yelpsearch:
                onSearchRequested(); //call search dialog
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
