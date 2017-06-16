package tw.edu.fcu.postoffice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tw.edu.fcu.postoffice.Data.MailBoxData;
import tw.edu.fcu.postoffice.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by kiam on 3/19/2017.
 */

public class MailBoxAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    ArrayList<MailBoxData> mailBoxData = new ArrayList<>();

    public MailBoxAdapter(Context context){
        layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    private class ViewHolder{
        TextView txtTitle;
        TextView txtContent;
    }

    public void addItem(String title, String content){
        this.mailBoxData.add(new MailBoxData(title,content));
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mailBoxData.size();
    }

    @Override
    public Object getItem(int position) {
        return mailBoxData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.adapter_mail_reception, null);
            viewHolder = new ViewHolder();
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.txt_title);
            viewHolder.txtContent = (TextView) convertView.findViewById(R.id.txt_content);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.txtTitle.setText(mailBoxData.get(position).getTitle());
        viewHolder.txtContent.setText(mailBoxData.get(position).getContent());

        return convertView;
    }
}
