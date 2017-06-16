package tw.edu.fcu.postoffice.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import tw.edu.fcu.postoffice.Server.ConnectServer;

public class MyService extends Service {
    public final static String KEY_MSG_TO_SERVICE = "KEY_MSG_TO_SERVICE";
    private final static String IP_ADRESS = "192.168.56.1";
    private final static int PORT = 9998;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initConnectServer();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_STICKY;
    }

    public void initConnectServer(){
        ConnectServer client = new ConnectServer(IP_ADRESS,PORT);
        client.start();
    }

    public void sendNotification(){
        Intent intent = new Intent();
        intent.setAction(KEY_MSG_TO_SERVICE);
        sendBroadcast(intent);
    }
}
