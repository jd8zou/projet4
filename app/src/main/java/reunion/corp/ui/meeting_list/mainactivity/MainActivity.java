package reunion.corp.ui.meeting_list.mainactivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import reunion.corp.R;
import reunion.corp.base.BaseActivity;
import reunion.corp.databinding.ActivityMainBinding;
import reunion.corp.databinding.MainActivityBinding;
import reunion.corp.ui.meeting_list.addmeet.AddMeetActivity;


public class MainActivity extends BaseActivity {

    private MainActivityBinding binding;
    FloatingActionButton add_meeting_fab;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        configureFragment();
        configureToolbar();
        configureFABAddMeeting();
    }

    /**
     * Configure the fragment Meeting to show in main activity
     */
    private void configureFragment() {
        MeetFragment fragment = new MeetFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_main, fragment)
                .commit();
    }

    private void configureToolbar() {
        setSupportActionBar(toolbar);
    }

    /**
     * SetUp the FloatingActionButton to start new AddMeetActivity
     */
    private void configureFABAddMeeting() {
        add_meeting_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMeetActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Get ressources menu to show in this activity.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

}
