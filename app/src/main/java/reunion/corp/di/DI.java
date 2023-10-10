package reunion.corp.di;

import reunion.corp.repository.MeetingRepository;
import reunion.corp.service.DummyMeetingApiService;

/**
 * Dependency injector to get instance of services
 */
public class DI {

    public static MeetingRepository createMeetingRepository() {
        return new MeetingRepository(new DummyMeetingApiService());
    }
}

