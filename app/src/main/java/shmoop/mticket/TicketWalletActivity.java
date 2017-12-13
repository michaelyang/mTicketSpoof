package shmoop.mticket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

public class TicketWalletActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_wallet);

        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        ImageView logoImage = (ImageView) toolbar.findViewById(R.id.logo_image);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        logoImage.setVisibility(View.GONE);
        toolbarTitle.setText("Ticket Wallet");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TabHost host = (TabHost)findViewById(android.R.id.tabhost);
        host.setup();

        TabSpec spec = host.newTabSpec("Tickets");
        spec.setContent(R.id.ticket_tab);
        spec.setIndicator("Tickets");
        host.addTab(spec);

        spec = host.newTabSpec("History");
        spec.setContent(R.id.history_tab);
        spec.setIndicator("History");
        host.addTab(spec);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_refresh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_menu_main) {
            Toast.makeText(TicketWalletActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }

}
