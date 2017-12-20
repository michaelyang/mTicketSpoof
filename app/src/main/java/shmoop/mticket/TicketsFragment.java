package shmoop.mticket;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class TicketsFragment extends Fragment {
        private SharedPreference sharedPreference;
        private ListView listView;
        private TicketAdapter mAdapter;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_tickets, container, false);
            sharedPreference = new SharedPreference();

            listView = (ListView) view.findViewById(R.id.tickets_list);
            final ArrayList ticketsList = sharedPreference.loadTickets(getContext());

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    //Toast.makeText(getActivity(), "position is: " + position, Toast.LENGTH_SHORT).show();
                    //sharedPreference.activateTicket(getContext(), position);
                    Intent startIntent = new Intent(getActivity(), TicketActivity.class);
                    startIntent.putExtra("Ticket", (Ticket) ticketsList.get(position));
                    startIntent.putExtra("position", position);
                    startActivityForResult(startIntent, 1);
                }
            });

            if (ticketsList != null) {
                mAdapter = new TicketAdapter(getContext(), ticketsList);
                listView.setAdapter(mAdapter);
            }
            return view;
        }

        @Override
        public void setUserVisibleHint(boolean isVisibleToUser) {
            super.setUserVisibleHint(isVisibleToUser);
            if (isVisibleToUser) {
                getFragmentManager().beginTransaction().detach(this).attach(this).commit();
            }
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getActivity();
        if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
}
