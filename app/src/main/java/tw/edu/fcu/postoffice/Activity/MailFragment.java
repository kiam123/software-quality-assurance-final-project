package tw.edu.fcu.postoffice.Activity;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import tw.edu.fcu.postoffice.Adapter.MailBoxAdapter;
import tw.edu.fcu.postoffice.R;
import tw.edu.fcu.postoffice.Service.MyService;

/**
 * A simple {@link Fragment} subclass.
 */
public class MailFragment extends Fragment {
    String title, content;
    MailBoxAdapter mailBoxAdapter;
    ListView listView;
    final static String KEY_MSG_TO_SERVICE = "KEY_MSG_TO_SERVICE";
    static int id = 70000;

    public MailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initAdapter();
        initNotification();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_mail, container, false);
    }

    public void initView(){
        listView = (ListView)getView().findViewById(R.id.lv_mail);
    }

    public void initAdapter(){
        mailBoxAdapter = new MailBoxAdapter(getActivity());
        listView.setAdapter(mailBoxAdapter);
        listView.setOnItemClickListener(mailBoxAdapterListenr);

        title = "品名: 電爐";
        content = "您好，您的貨物已經到達我們的地方";
        mailBoxAdapter.addItem(title, content);
        mailBoxAdapter.addItem(title, content);
        mailBoxAdapter.addItem(title, content);
    }

    private AdapterView.OnItemClickListener mailBoxAdapterListenr = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), MailBoxActivity.class);
            startActivity(intent);
        }
    };

    public void initNotification(){
        BRNotification brnotification = new BRNotification();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(KEY_MSG_TO_SERVICE);
        getActivity().registerReceiver(brnotification, intentFilter);
    }

    public class BRNotification extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(MyService.KEY_MSG_TO_SERVICE)){
                Intent newintent = new Intent();
                newintent.setClass(context, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, newintent, PendingIntent.FLAG_CANCEL_CURRENT);

                Notification notify = null;
                notify = newNotification(context, pendingIntent,"郵局信息", "你好嗎？");
                NotificationManager notificationManager =(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(id, notify);
//                title = "123";
//                content = "wwwwwwww";
//                mailBoxAdapter.addItem(title,content);
//                mailBoxAdapter.addItem(title,content);
//                mailBoxAdapter.addItem(title,content);
            }
        }

        private Notification newNotification(Context context, PendingIntent pi, String title, String msg) {

            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle(title);
            builder.setContentText(msg);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentIntent(pi);
            builder.setTicker(msg);
            builder.setWhen(System.currentTimeMillis());
            builder.setAutoCancel(true);
//            builder.setDefaults(Notification.DEFAULT_VIBRATE); //使用默認手機震動提示
//            builder.setDefaults(Notification.DEFAULT_SOUND); //使用默認聲音提示
            builder.setLights(0xff00ff00, 300, 1000);
            Notification notify = builder.build();
            return notify;
        }
    }
}
