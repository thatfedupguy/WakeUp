package am.tk.wakeup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class datesAdapter extends RecyclerView.Adapter<datesAdapter.datesViewHolder> {

    ArrayList<Date> dateList;

    public datesAdapter(ArrayList<Date> dateList) {
        this.dateList = dateList;
    }

    @NonNull
    @Override
    public datesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.list_item_date, viewGroup, false);

        return new datesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull datesViewHolder viewHolder, int i) {
        Date date = dateList.get(i);
        viewHolder.tvMonth.setText(date.getMonth());
        viewHolder.tvDate.setText(date.getDate());
        viewHolder.tvYear.setText(date.getYear());
        viewHolder.tvDay.setText(date.getDay());
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    class datesViewHolder extends RecyclerView.ViewHolder {
        TextView tvMonth, tvYear, tvDay, tvDate;

        public datesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMonth = itemView.findViewById(R.id.tvMonth);
            tvYear = itemView.findViewById(R.id.tvYear);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDay = itemView.findViewById(R.id.tvDay);

        }
    }
}
