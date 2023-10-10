package reunion.corp.ui.meeting_list.mainactivity;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import reunion.corp.R;
import reunion.corp.databinding.MainActivityBinding;
import reunion.corp.databinding.MeetingItemBinding;
import reunion.corp.model.Meeting;
import reunion.corp.utilities.DeleteEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;



public class MeetingItemViewHolder extends RecyclerView.ViewHolder {
    private MeetingItemBinding binding;

    private String mail = "";

    public MeetingItemViewHolder(@NonNull View itemView) {

        super(itemView);

        MeetingItemBinding.bind(this, itemView);
    }

    public void update(Meeting meeting) {
        this.binding.itemListPastil.getDrawable().setTint(Color.parseColor(meeting.getRoom().getColor()));
        this.binding.itemListTitle.setText(meeting.getName() + " - " + meeting.getRoom() + " - " +
                meeting.getTimeFormated() + "\n" + meeting.getDateFormated());

        List<String> mails = meeting.getMail();
        for (int i = 0; i < mails.size(); i++) {
            mail += mails.get(i);
            if (i == mails.size() - 1) {
                mail += ".";
            } else {
                mail += ",";
            }
        }
        this.binding.itemMailList.setText(mail);

        this.binding.deleteItemIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteEvent(meeting));
            }
        });
    }
}
