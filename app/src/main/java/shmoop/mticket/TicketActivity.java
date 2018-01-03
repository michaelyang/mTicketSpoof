package shmoop.mticket;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import android.os.Handler;

public class TicketActivity extends AppCompatActivity {
    private SharedPreference sharedPreference;
    TextView datetime;
    ColorBarView colorBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        sharedPreference = new SharedPreference();

        Intent i = getIntent();
        final int position = (int) i.getSerializableExtra("position");
        Ticket ticket = sharedPreference.getTicketAt(this, position);

        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        ImageView logoImage = (ImageView) toolbar.findViewById(R.id.logo_image);
        logoImage.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarTitle.setText(ticket.getFareType());


        colorBarView = (ColorBarView) findViewById(R.id.colorBar);
        datetime = (TextView) findViewById(R.id.datetime);
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateTextView();
                            }
                        });
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();

        TextView activated = (TextView) findViewById(R.id.activated);
        TextView fareType = (TextView) findViewById(R.id.fareType);
        fareType.setText(ticket.getFareType());
        ImageButton colorBarButton = (ImageButton) findViewById(R.id.colorBarButton);
        colorBarButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                throw new RuntimeException("mTicket has stopped working.");
            }
        });
        Button activateButton = (Button) findViewById(R.id.activate);
        activateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final BottomSheetDialog dialog = new BottomSheetDialog(TicketActivity.this);
                dialog.setContentView(R.layout.ticket_confirmation);
                ImageView cancel = (ImageView) dialog.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                Button activate = (Button) dialog.findViewById(R.id.activate);
                activate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sharedPreference.activateTicket(TicketActivity.this, position);
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result", "1");
                        setResult(RESULT_OK,returnIntent);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                });
                dialog.show();
            }
        });

        Button actionsButton = (Button) findViewById(R.id.actionsButton);
        actionsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final BottomSheetDialog dialog = new BottomSheetDialog(TicketActivity.this);
                dialog.setContentView(R.layout.ticket_confirmation);
                ImageView cancel = (ImageView) dialog.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                Button activate = (Button) dialog.findViewById(R.id.activate);
                activate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sharedPreference.activateTicket(TicketActivity.this, position);
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result", "1");
                        setResult(RESULT_OK,returnIntent);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                });
                dialog.show();
            }
        });

        FrameLayout activeColorBar = (FrameLayout) findViewById(R.id.colorBarFrameActive);
        LinearLayout inactiveColorBar = (LinearLayout) findViewById(R.id.colorBarFrameInactive);

        if (ticket.getActive()) {
            SimpleDateFormat df = new SimpleDateFormat("h:mm a");
            activated.setText("Ticket activated at " + df.format(ticket.getActivatedAt()));
            inactiveColorBar.setVisibility(View.GONE);
            activateButton.setVisibility(View.GONE);
        } else {
            activated.setVisibility(View.GONE);
            activeColorBar.setVisibility(View.GONE);
            colorBarButton.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }

    private void updateTextView() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("h:mm:ss a\nMM/dd/yy");
        String formattedDate = df.format(c.getTime());
        datetime.setText(formattedDate);
    }

    @Override
    public void onResume() {
        super.onResume();
        final Animation animBlink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        final Animation animSidetoSide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sidetoside);
        colorBarView.startAnimation(animBlink);
        datetime.startAnimation(animSidetoSide);
    }
}
