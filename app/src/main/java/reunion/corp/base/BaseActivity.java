package reunion.corp.base;

import androidx.appcompat.app.AppCompatActivity;

import reunion.corp.ReuApplication;
import reunion.corp.repository.MeetingRepository;

public abstract class BaseActivity extends AppCompatActivity {
    public MeetingRepository getMeetingRepository() {
        return ((ReuApplication) getApplication()).getMeetingRepository();
    }
}
