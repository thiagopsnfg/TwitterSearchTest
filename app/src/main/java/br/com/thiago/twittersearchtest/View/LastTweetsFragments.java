package br.com.thiago.twittersearchtest.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.thiago.twittersearchtest.Persistence.LastSearchDao;
import br.com.thiago.twittersearchtest.R;
import br.com.thiago.twittersearchtest.Utils.TextUtils;

/**
 * Created by Thiago on 21/11/2015.
 */
public class LastTweetsFragments extends Fragment {

    private RecyclerView mRecyclerView;
    private Context mContext;
    private LastTweetsAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity().getApplicationContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_last_search, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvLastSearches);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new LastTweetsAdapter();
        mRecyclerView.setAdapter(adapter);

        return view;
    }


//----------------------------------------------------------------------------------------------------------------

    public class LastTweetsAdapter extends RecyclerView.Adapter<LastTweetsAdapter.InnerViewHolder> {

        List<String> lastSearches;

        public LastTweetsAdapter() {
            this.lastSearches = LastSearchDao.getLastSearchList();
        }

        @Override
        public InnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.fragment_last_search_card, parent, false);

            InnerViewHolder holder = new InnerViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(InnerViewHolder holder, int position) {
            Log.i("LOG", "onBindViewHolder do ListTweetsAdapter.");
            holder.mTextView.setText(lastSearches.get(position));

        }

        @Override
        public int getItemCount() {
            return lastSearches != null ? lastSearches.size() : 0;
        }

        //---------------------------------------------------------------------------------------
        public class InnerViewHolder extends RecyclerView.ViewHolder {
            TextView mTextView;

            public InnerViewHolder(final View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.txtLastSearch);
                mTextView.setTypeface(TextUtils.getTypeface(itemView.getContext(), TextUtils.FONT_CUTE_CARTOON));
            }

        }
    }
}
