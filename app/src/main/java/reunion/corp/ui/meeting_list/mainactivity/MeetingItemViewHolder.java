package reunion.corp.ui.meeting_list.mainactivity;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import reunion.corp.databinding.MeetingItemBinding;
import reunion.corp.model.Meeting;
import reunion.corp.utilities.DeleteEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;



public class MeetingItemViewHolder extends RecyclerView.ViewHolder {

    private MeetingItemBinding binding;

    // @BindView(R.id.item_list_title)
    TextView meet;
    //@BindView(R.id.item_list_pastil)
    ImageView color;
    //@BindView(R.id.delete_item_iv)
    ImageView delete;
    //@BindView(R.id.item_mail_list)
    TextView mails;

    private String mail = "";

    public MeetingItemViewHolder(MeetingItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
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
