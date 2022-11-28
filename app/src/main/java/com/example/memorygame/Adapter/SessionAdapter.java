package com.example.memorygame.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memorygame.Database.Entity.Session;
import com.example.memorygame.R;
import com.example.memorygame.ViewHolder.SessionViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionViewHolder> implements Filterable {

    private final Context context;
    private final ArrayList<Session> sessionList;
    private ArrayList<Session> filteredSessionList;
    private static final int TYPE_ROW = 0;
    private static final int TYPE_ROW_COLORFUL = 1;

    public SessionAdapter(Context context, ArrayList<Session> sessionList) {
        this.context = context;
        this.sessionList = sessionList;
        this.filteredSessionList = sessionList;
    }
    @Override
    public int getItemViewType(int position)
    {
        if (position % 2 == 0)
        {
            return TYPE_ROW_COLORFUL;
        }

        return TYPE_ROW;
    }


    @NonNull
    @Override
    public SessionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ROW)
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_club, viewGroup, false);
            return new SessionViewHolder(view);
        } else
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_club_colorful, viewGroup, false);
            return new SessionViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull SessionViewHolder holder, int position) {
        final Session item = this.filteredSessionList.get(position);

        holder.txtName.setText(item.name);
        holder.txtLocation.setText(club.location);
        holder.txtStadiumName.setText(club.stadiumName);
        holder.txtLeagueName.setText(club.leagueName);
        holder.txtCoachName.setText(club.coachName);
        holder.txtStarPlayerName.setText(club.starPlayerName);

        Glide.with(context).load(club.logoUrl).into(holder.imgLogo);
    }

    @Override
    public int getItemCount() {
        return this.filteredSessionList.size();
    }
    @Override
    public Filter getFilter() {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence)
            {
                String charString = charSequence.toString();
                if (charString.isEmpty())
                {
                    filteredSessionList = sessionList;
                } else
                {
                    List<Session> filteredList = new ArrayList<>();
                    for (Session session : sessionList)
                    {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name
                        if (session.name.toLowerCase().contains(charString.toLowerCase()) )
                        {
                            filteredList.add(session);
                        }
                    }

                    filteredSessionList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredSessionList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults)
            {
                filteredSessionList = (ArrayList<Session>) filterResults.values;
                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }
}
