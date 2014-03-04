package groupo.travellight.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Joseph Bandola on 2/25/14.
 */
public class PackingListActivity extends ActionBarActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packing_list);

        listView = (ListView) findViewById(R.id.pack_list);

        //Default List Items for the Packing List
        String[] values = new String[] {
                "Passport and Identification",
                "Money",
                "Necessary Medications",
                "Clothing",
                "International Cell Phone + Charger",
                "Dental and Bodily Hygine Products",
                "Destination Travel Guide",
                "Reading Books",
                "Music Player and/or Personal Electronics",
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                values);

        listView.setAdapter(adapter);
    }

}

