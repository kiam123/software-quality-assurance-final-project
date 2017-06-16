package tw.edu.fcu.postoffice.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import tw.edu.fcu.postoffice.Adapter.MailBoxAdapter;
import tw.edu.fcu.postoffice.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformedFragment extends Fragment {
    MailBoxAdapter mailBoxAdapter;
    ListView listView;
    String title, content;

    public InformedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_informed, container, false);
        return viewGroup;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initAdapter();
    }

    public void initView(){
        listView = (ListView) getView().findViewById(R.id.listview);
    }

    public void initAdapter(){
        mailBoxAdapter = new MailBoxAdapter(getActivity());
        listView.setAdapter(mailBoxAdapter);
        listView.setOnItemClickListener(mailBoxAdapterListenr);
        title = "品名: 電爐";
        content = "您好，您的貨物已經到達我們的地方";
        mailBoxAdapter.addItem(title, content);
        mailBoxAdapter.addItem(title, content);
    }

    private AdapterView.OnItemClickListener mailBoxAdapterListenr = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent();
            intent.setClass(getActivity(),InformedMailBoxActivity.class);
            startActivity(intent);
        }
    };
}
