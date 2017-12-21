package shmoop.mticket;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Handler;

public class TicketActivity extends AppCompatActivity {
    private SharedPreference sharedPreference;
    TextView datetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        Intent i = getIntent();
        Ticket ticket = (Ticket) i.getSerializableExtra("Ticket");
        final int position = (int) i.getSerializableExtra("position");

        sharedPreference = new SharedPreference();

        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        ImageView logoImage = (ImageView) toolbar.findViewById(R.id.logo_image);
        logoImage.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarTitle.setText(ticket.getFareType());


        ColorBarView colorBarView = (ColorBarView) findViewById(R.id.colorBar);
        Animation animBlink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        colorBarView.startAnimation(animBlink);

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
        Animation animSidetoSide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sidetoside);
        datetime.startAnimation(animSidetoSide);


        TextView activated = (TextView) findViewById(R.id.activated);
        if (ticket.getActive()) {
            SimpleDateFormat df = new SimpleDateFormat("h:mm a");
            activated.setText("Ticket activated at " + df.format(ticket.getActivatedAt()));
        } else {
            activated.setVisibility(View.INVISIBLE);
        }

        TextView fareType = (TextView) findViewById(R.id.fareType);
        fareType.setText(ticket.getFareType());

        Button actionsButton = (Button) findViewById(R.id.actionsButton);
        actionsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sharedPreference.activateTicket(TicketActivity.this, position);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", "1");
                setResult(RESULT_OK,returnIntent);
            }
        });

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
}
