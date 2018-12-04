package dev.rohanb.movie_mania;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private Context mContext;
    private ArrayList<ExampleItem> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public ExampleAdapter(Context context, ArrayList<ExampleItem> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item,parent,false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position){
        ExampleItem currentItem= mExampleList.get(position);

        String posterUrl = currentItem.getmPosterUrl();
        String title = currentItem.getmTitle();
        String releaseDate = currentItem.getmReleaseDate();
        Boolean adult = currentItem.getmAdult();

        // Text Views
        holder.txtTitle.setText(title);
        holder.txtReleaseDate.setText(releaseDate);
        //holder.txtAdult.setText(adult.toString());
        holder.txtAdult.setAllCaps(adult);  // boolean Adult
        //Image View
        Picasso.with(mContext).load("https://image.tmdb.org/t/p/w500/"+posterUrl).fit().centerInside().into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imgPoster;
        public TextView txtTitle;
        public TextView txtReleaseDate;
        public TextView txtAdult;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            imgPoster=itemView.findViewById(R.id.imgPoster);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtReleaseDate=itemView.findViewById(R.id.txtReleaseDate);
            txtAdult=itemView.findViewById(R.id.txtAdult);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION);
                        {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
