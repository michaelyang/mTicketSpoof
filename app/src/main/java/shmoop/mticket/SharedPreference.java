package shmoop.mticket;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPreference {
    public static final String PREFS_NAME = "SharedPrefs";
    public static final String TICKETS = "Tickets";

    public SharedPreference() {
        super();
    }

    public void storeTickets(Context context, List tickets) {
        SharedPreferences settings;
        Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor = settings.edit();
        Gson gson = new Gson();
        String jsonTickets = gson.toJson(tickets);
        editor.putString(TICKETS, jsonTickets);
        editor.commit();
    }

    public ArrayList loadTickets(Context context) {
        SharedPreferences settings;
        List<Ticket> ticketsList;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        if (settings.contains(TICKETS)) {
            String jsonTickets = settings.getString(TICKETS, null);
            Gson gson = new Gson();
            Type type = new TypeToken<List<Ticket>>() {}.getType();
            ticketsList = gson.fromJson(jsonTickets, type);
        } else
            return null;
        return (ArrayList) ticketsList;
    }

    public void addTicket(Context context, Ticket ticket) {
        ArrayList ticketsList = loadTickets(context);
        if (ticketsList == null)
            ticketsList = new ArrayList<>();
        ticketsList.add(ticket);
        storeTickets(context, ticketsList);
    }

    public void removeTicket(Context context, Ticket ticket) {
        ArrayList ticketsList = loadTickets(context);
        if (ticketsList != null) {
            ticketsList.remove(ticket);
            storeTickets(context, ticketsList);
        }
    }

    public void activateTicket(Context context, int position) {
        ArrayList ticketsList = loadTickets(context);
        if (ticketsList == null)
            return;
        ((Ticket) ticketsList.get(position)).activate();
        storeTickets(context, ticketsList);
    }

    public void clearTickets(Context context) {
        SharedPreferences settings;
        Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.remove(TICKETS).commit();
    }
}