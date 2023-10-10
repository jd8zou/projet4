package reunion.corp.utilities;

import reunion.corp.model.Meeting;

public class DeleteEvent {
    public Meeting mMeeting;

    public DeleteEvent(Meeting meeting) {
        this.mMeeting = meeting;
    }
}

