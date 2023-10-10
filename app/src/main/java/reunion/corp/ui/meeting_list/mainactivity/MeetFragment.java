package reunion.corp.ui.meeting_list.mainactivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Spinner;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import reunion.corp.R;
import reunion.corp.base.BaseActivity;
import reunion.corp.model.Room;
import reunion.corp.repository.MeetingRepository;
import reunion.corp.utilities.DeleteEvent;
import reunion.corp.utilities.DialogDatePickerFragment;
import reunion.corp.utilities.DialogPlaceSpinner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MeetFragment extends Fragment implements DialogDatePickerFragment.DialogDatePickerListener,
        DialogPlaceSpinner.DialogPlaceSpinnerListener {

    private MeetingRepository mMeetingRepository;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter adapter;
    private FloatingActionButton restoreDataFab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMeetingRepository = ((BaseActivity) getActivity()).getMeetingRepository();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_activity, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.meeting_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        configureFABRestoreData();
        initList();
        return view;
    }

    @SuppressLint("RestrictedApi")
    private void initList() {
        adapter = new RecyclerViewAdapter(mMeetingRepository.getMeetingsList());
        mRecyclerView.setAdapter(adapter);
        restoreDataFab.setVisibility(View.INVISIBLE);
    }


    private void configureFABRestoreData() {
        restoreDataFab = getActivity().findViewById(R.id.back_filter_meeting_fab);
        restoreDataFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initList();
            }
        });
    }



    @SuppressLint("RestrictedApi")
    private void filterListByDate(String dateString) {
        adapter.updateList(mMeetingRepository.filterByDate(dateString));
        mRecyclerView.setAdapter(adapter);
        restoreDataFab.setVisibility(View.VISIBLE);
    }

    @SuppressLint("RestrictedApi")
    private void filterListByPlace(String place) {
        adapter.updateList(mMeetingRepository.filterByPlace(place));
        mRecyclerView.setAdapter(adapter);
        restoreDataFab.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteMeeting(DeleteEvent event) {
        mMeetingRepository.deleteMeeting(event.mMeeting);
        initList();
    }

    private void showDatePikerDialog() {
        DialogDatePickerFragment dialog = new DialogDatePickerFragment();
        dialog.setTargetFragment(MeetFragment.this, 5);
        dialog.show(getFragmentManager(), "DialogDatePickerFragment");
    }

    private void showSpinnerPlaceDialog() {
        DialogPlaceSpinner dialog = new DialogPlaceSpinner();
        dialog.setTargetFragment(MeetFragment.this, 6);
        dialog.show(getFragmentManager(), "DialogSpinnerPlaceFragment");
    }

    @Override
    public void onDialogDatePikerValidateClick(DialogDatePickerFragment dialogDatePickerFragment) {
        DatePicker datePicker = (DatePicker) dialogDatePickerFragment.getDialog().findViewById(R.id.date_dp);
        int mDay = datePicker.getDayOfMonth();
        int mMonth = datePicker.getMonth();
        int mYear = datePicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(mYear, mMonth, mDay);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = sdf.format(date);
        filterListByDate(dateString);
    }

    @Override
    public void onDialogPlaceSpinnerValidateClick(DialogFragment dialog) {
        Spinner spinner = (Spinner) dialog.getDialog().findViewById(R.id.dialog_room_spinner_sp);
        Room room = (Room) spinner.getSelectedItem();
        filterListByPlace(room.toString());
    }
}
