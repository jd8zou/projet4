package reunion.corp;

import android.app.Application;
import android.content.res.Configuration;

import reunion.corp.di.DI;
import reunion.corp.repository.MeetingRepository;

public class ReuApplication extends Application {

    private MeetingRepository mMeetingRepository;

    public MeetingRepository getMeetingRepository() {
        if (mMeetingRepository == null) {
            mMeetingRepository = DI.createMeetingRepository();
        }
        return mMeetingRepository;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.mMeetingRepository = null;
    }
}

