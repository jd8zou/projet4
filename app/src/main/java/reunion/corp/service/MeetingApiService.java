package reunion.corp.service;

import reunion.corp.model.Meeting;
import reunion.corp.model.Room;

import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeetingList();

    void addMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

    List<Room> getRooms();

    List<Meeting> filterByDate(String date);

    List<Meeting> filterByPlace(String place);
}
