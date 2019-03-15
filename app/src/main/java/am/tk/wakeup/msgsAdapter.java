package am.tk.wakeup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class msgsAdapter extends RecyclerView.Adapter<msgsAdapter.msgsViewHolder> {
    ArrayList<msgs> msgsList;

    public msgsAdapter(ArrayList<msgs> msgsList) {
        this.msgsList= msgsList;
    }

    @NonNull
    @Override
    public msgsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.list_item_date, viewGroup, false);

        return new msgsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull msgsViewHolder viewHolder, int i) {
        msgs msgs = msgsList.get(i);
        viewHolder.tvAddress.setText(msgs.getAddress());
        viewHolder.tvMessage.setText(msgs.getBody());
    }

    @Override
    public int getItemCount() {
        return msgsList.size();
    }

    class msgsViewHolder extends RecyclerView.ViewHolder {
        TextView tvAddress,tvMessage;

        public msgsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvMessage = itemView.findViewById(R.id.tvMessage);


        }
    }

}
