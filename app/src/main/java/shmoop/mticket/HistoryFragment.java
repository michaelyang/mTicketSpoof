package shmoop.mticket;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class HistoryFragment extends Fragment implements View.OnClickListener {
    private SharedPreference sharedPreference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        sharedPreference = new SharedPreference();
        Button addTicket1Button = (Button) view.findViewById(R.id.addTicket1);
        addTicket1Button.setOnClickListener(this);
        Button addTicket2Button = (Button) view.findViewById(R.id.addTicket2);
        addTicket2Button.setOnClickListener(this);
        Button clearTickets = (Button) view.findViewById(R.id.clearTickets);
        clearTickets.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        //do what you want to do when button is clicked
        switch (v.getId()) {
            case R.id.addTicket1:
                Ticket ticket1 = new Ticket("MB22K3OGYXZ", "One Way", 11.50, "Back Bay", "Providence", "****4431", "8");
                sharedPreference.addTicket(getContext(), ticket1);
                Toast.makeText(getActivity(), "Ticket 1 Added", Toast.LENGTH_LONG).show();
                break;
            case R.id.addTicket2:
                Ticket ticket2 = new Ticket("MB22K3OGYXZ", "One Way", 11.50, "Providence", "Back Bay", "****4431", "8");
                sharedPreference.addTicket(getContext(), ticket2);
                Toast.makeText(getActivity(), "Ticket 2 Added", Toast.LENGTH_LONG).show();
                break;
            case R.id.clearTickets:
                sharedPreference.clearTickets(getContext());
                break;
        }
    }
}
