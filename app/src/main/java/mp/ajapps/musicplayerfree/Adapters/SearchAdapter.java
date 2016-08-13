package mp.ajapps.musicplayerfree.Adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mp.ajapps.musicplayerfree.POJOS.SearchPojo;
import mp.ajapps.musicplayerfree.R;

/**
 * Created by Sharing Happiness on 12/2/2015.
 */
public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<SearchPojo> mList = new ArrayList<SearchPojo>();
    int rowId;
    private myOnCLickInterface mMyOnClick;

    public SearchAdapter(int id, myOnCLickInterface m) {
        this.rowId = id;
        this.mMyOnClick = m;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 3 || viewType == 4) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(rowId, parent, false);
            return new TrackHolder1(v);
        } else if (viewType == 1) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.header_row, parent, false);
            return new TrackHolder2(v);
        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.header_row, parent, false);
            return new TrackHolder2(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        SearchPojo temp = mList.get(position);
        final int type = temp.getmType();
        final String id = temp.getmId();

        if (holder instanceof TrackHolder1) {
            ((TrackHolder1) holder).bindData(temp);
        } else if (holder instanceof TrackHolder2) {
            ((TrackHolder2) holder).bindData(temp);
        }
       /* holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mMyOnClick) {
                    mMyOnClick.myOnClick(type, id);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return (mList == null || mList.size() == 0) ? 0 : mList.size();
    }

    public void changeCursor(ArrayList<SearchPojo> cursor) {
        if (mList != null)
            this.mList.clear();
        this.mList = cursor;
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getmType();
    }

    public interface myOnCLickInterface {
        public void myOnClick(int type, String id);
    }

    protected class TrackHolder1 extends RecyclerView.ViewHolder {
        public final View mView;
        TextView mTrackName, mTrackDetail;

        public TrackHolder1(View itemView) {
            super(itemView);
            mView = itemView;
            mTrackName = (TextView) itemView.findViewById(R.id.textView);
            mTrackDetail = (TextView) itemView.findViewById(R.id.textView2);
        }

        void bindData(SearchPojo sj) {
            String s = sj.getmType() == 1 ? "Tracks" : "Album";
            mTrackName.setText(sj.getTitle());
            mTrackDetail.setText(sj.getArtist());
        }
    }

    protected class TrackHolder2 extends RecyclerView.ViewHolder {
        public final View mView;
        TextView mHeader, mBaser;
        ImageView mImageView;

        public TrackHolder2(View itemView) {
            super(itemView);
            mView = itemView;
            mHeader = (TextView) itemView.findViewById(R.id.textView9);
            mImageView = (ImageView) itemView.findViewById(R.id.album_list_img);
        }

        void bindData(SearchPojo sj) {
            mView.setBackgroundColor(Color.parseColor("#1e232e"));
            String s = sj.getmType() == 1 ? "Tracks" : "Album";
            mHeader.setText(s);
        }
    }
}


