package shmoop.mticket;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 12/17/2017.
 */

public class TicketAdapter extends ArrayAdapter<Ticket> {
    private Context mContext;
    private List<Ticket> ticketsList = new ArrayList<>();

    public TicketAdapter(Context context, ArrayList<Ticket> list) {
        super(context, 0 , list);
        mContext = context;
        ticketsList = list;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.ticket_item,parent,false);

        Ticket currentTicket = ticketsList.get(position);

        RelativeLayout layout =(RelativeLayout) listItem.findViewById(R.id.background);
        TextView fareType = (TextView) listItem.findViewById(R.id.fareType);
        fareType.setText(currentTicket.fareType);
        TextView active = (TextView) listItem.findViewById(R.id.active);
        View stroke = (View) listItem.findViewById(R.id.stroke);

        if (currentTicket.getActive()) {
            active.setText("ACTIVE");
            active.setTextColor(Color.WHITE);
            fareType.setTextColor(Color.WHITE);
            layout.setBackgroundResource(R.drawable.ticket_item_active);
            stroke.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.active));
        } else {
            active.setText("INACTIVE");
            layout.setBackgroundResource(R.drawable.ticket_item_inactive);
        }

        TextView origin = (TextView) listItem.findViewById(R.id.origin);
        origin.setText(currentTicket.origin);

        TextView destination = (TextView) listItem.findViewById(R.id.destination);
        destination.setText(currentTicket.destination);

        return listItem;
    }
}
