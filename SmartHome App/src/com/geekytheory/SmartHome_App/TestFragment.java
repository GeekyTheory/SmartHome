package com.geekytheory.SmartHome_App;

import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

/**
 * Author: Mario Pérez Esteso
 * Website: http://geekytheory.com
 * Mail: mario@geekytheory.com
 */

@SuppressLint("ValidFragment")
public class TestFragment extends SherlockFragment {
    private String mContent = "???";
    private static final String KEY_TAB_NUM = "key.tab.num";
    ListView listView;
    List<ListView> listViews;
    MyActivity mainActivity;
    @SuppressLint("ValidFragment")
	public static TestFragment newInstance(String text, MyActivity activity) {
        TestFragment fragment = new TestFragment(activity);

        Bundle args = new Bundle();
        args.putString(KEY_TAB_NUM, text);
        fragment.setArguments(args);
        return fragment;
    }

    public TestFragment(MyActivity activity) {
        this.mainActivity = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, null);

        //ListViews
        ((TextView)view.findViewById(R.id.text)).setText("");
        listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter(this.mainActivity.adapterList.get(Integer.parseInt(mContent)));
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                DeviceItem deviceItem = (DeviceItem) parent
                        .getItemAtPosition(position);
            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContent = getArguments() != null ? getArguments().getString(KEY_TAB_NUM) : "???";
    }

}