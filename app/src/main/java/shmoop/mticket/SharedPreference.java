package shmoop.mticket;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPreference {
    public static final String PREFS_NAME = "SharedPrefs";
    public static final String TICKETS = "Tickets";
    public static final String COLOR1 = "Color1";
    public static final String COLOR2 = "Color2";
    public static final String COLOR3 = "Color3";

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
        if (ticketsList != null) {
            ((Ticket) ticketsList.get(position)).activate();
            storeTickets(context, ticketsList);
        }
    }

    public Ticket getTicketAt(Context context, int position) {
        ArrayList ticketsList = loadTickets(context);
        if (ticketsList != null) {
            return ((Ticket) ticketsList.get(position));
        } else {
            return null;
        }
    }

    public void clearTickets(Context context) {
        SharedPreferences settings;
        Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.remove(TICKETS).commit();
    }

    public void storeColor1(Context context, int color) {
        SharedPreferences settings;
        Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putInt(COLOR1, color);
        editor.commit();
    }

    public void storeColor2(Context context, int color) {
        SharedPreferences settings;
        Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putInt(COLOR2, color);
        editor.commit();
    }

    public void storeColor3(Context context, int color) {
        SharedPreferences settings;
        Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putInt(COLOR3, color);
        editor.commit();
    }

    public int loadColor1(Context context) {
        SharedPreferences settings;
        int color;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        color =  settings.getInt(COLOR1, -1);
        return color;
    }

    public int loadColor2(Context context) {
        SharedPreferences settings;
        int color;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        color =  settings.getInt(COLOR2, -1);
        return color;
    }

    public int loadColor3(Context context) {
        SharedPreferences settings;
        int color;
        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        color =  settings.getInt(COLOR3, -1);
        return color;
    }
}