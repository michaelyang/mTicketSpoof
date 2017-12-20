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

public class TicketActivity extends AppCompatActivity {
    private SharedPreference sharedPreference;

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
}
