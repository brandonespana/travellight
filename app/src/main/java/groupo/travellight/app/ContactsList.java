package groupo.travellight.app;



//package exp.brandone.experiments.app;

        import android.app.Activity;
        import android.app.Fragment;
        import android.content.ContentProvider;
        import android.content.ContentResolver;
        import android.net.Uri;
        import android.provider.Contacts;
        import android.provider.ContactsContract;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.database.Cursor;
        import android.net.Uri;
        import android.provider.ContactsContract;
        import android.provider.ContactsContract.CommonDataKinds.Email;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ListView;
        import android.widget.SimpleCursorAdapter;
        import android.widget.TextView;


public class ContactsList extends Fragment {

    private ContentResolver contentResolver;
    private Uri contactsUri;
    private Cursor cursor;
    private ListView contactsList;
    //    private String [] projection;
//    private String [] selectionClause;
//    private String [] selectionArgs;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        contactsUri= ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        contentResolver = getActivity().getContentResolver();
        //setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle instancedState){

        View rootView = inflater.inflate(R.layout.activity_my_own_contacts,container,false);
        contactsList = (ListView) rootView.findViewById(R.id.contacts_list);
        contactsList.setAdapter(displayContacts());

        return rootView;
    }

    private SimpleCursorAdapter displayContacts(){
        //selecting the columns in contacts table
        String [] projection = new String[]{

                ContactsContract.RawContacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Email.DATA

        };
        //selecint the rows, null=all of them:
        String selectionClause = null;
        //for choosing rows that a chooser chose, their search input:
        String[] selectionArgs= new String[]{""};
        cursor = contentResolver.query(contactsUri, projection, null,null,null);

        //Cursor theCursor = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, projection, null,null,null);
        String [] columnsToAdapt=new String[] {
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Email.DATA
        };

        int[] listItemViews= new int[]{
                R.id.contact_display_name,
                R.id.contact_email_address
        };
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.list_item_contacts,
                cursor,
                columnsToAdapt,
                listItemViews,
                0
        );
        return cursorAdapter;
//        contactsList.setAdapter(cursorAdapter);

    }

}

