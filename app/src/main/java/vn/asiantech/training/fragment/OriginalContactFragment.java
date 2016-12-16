package vn.asiantech.training.fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import vn.asiantech.training.R;

public class OriginalContactFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Cursor> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int CONTACT_ID_INDEX = 0;
    private static final int LOOKUP_KEY_INDEX = 1;
    @SuppressLint("InlinedApi")
    private final static String[] FROM_COLUMNS = {
            Build.VERSION.SDK_INT
                    >= Build.VERSION_CODES.HONEYCOMB ?
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
                    ContactsContract.Contacts.DISPLAY_NAME
    };
    private final static int[] TO_IDS = {
            R.id.tvContactName
    };
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private ListView mListview;
    private SimpleCursorAdapter mCursorAdapter;

    public OriginalContactFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_original_contact, container, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListview = (ListView) getActivity().findViewById(R.id.listView);
        mCursorAdapter = new SimpleCursorAdapter(getContext(), R.layout.row_item, null, FROM_COLUMNS, TO_IDS, 0);
        mListview.setAdapter(mCursorAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Define the columns to retrieve
        String[] projectionFields = new String[]{ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
        };
        // Construct the loader
        CursorLoader cursorLoader = new CursorLoader(getActivity(),
                ContactsContract.Contacts.CONTENT_URI, // URI
                projectionFields, // projection fields
                null, // the selection criteria
                null, // the selection args
                null // the sort order
        );
        // Return the loader for use
        return cursorLoader;
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
