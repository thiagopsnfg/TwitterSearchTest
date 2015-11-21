package br.com.thiago.twittersearchtest.View;

import android.content.Context;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import br.com.thiago.twittersearchtest.R;
import br.com.thiago.twittersearchtest.Utils.TextUtils;
import br.com.thiago.twittersearchtest.Utils.TwitterUtils;

public class MainFragment extends Fragment {

    private EditText editTextSearch;
    private static List<Tweet> tweets;
    private RecyclerView recyclerView;
    private ListTweetsAdapter listTweetsAdapter;
    private Context context;
    private String search;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
        Fresco.initialize(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_root_body, container, false);

        //Set RecycleView
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        listTweetsAdapter = new ListTweetsAdapter();
        recyclerView.setAdapter(listTweetsAdapter);

        //Set Text for search
        editTextSearch = (EditText) view.findViewById(R.id.et_Search);
        editTextSearch.setTypeface(TextUtils.getTypeface(context, TextUtils.FONT_CUTE_CARTOON));

        TextView txtSearchMsg = (TextView) view.findViewById(R.id.txtSearchMsg);
        txtSearchMsg.setTypeface( TextUtils.getTypeface(context,TextUtils.FONT_CUTE_CARTOON ));

        //Set Button for search
        Button btnSearch = (Button) view.findViewById(R.id.btnSearch);
        btnSearch.setTypeface( TextUtils.getTypeface(context, TextUtils.FONT_CUTE_CARTOON ));
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search = editTextSearch.getText().toString();
                searchTweets(search);
                TextUtils.hideKeyboard(context, editTextSearch);
            }
        });

        return view;
    }

    public void searchTweets(String search) {
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient(TwitterUtils.getSession());
        twitterApiClient.getSearchService().tweets(search, null, null, null, null, 25, null, null, null, true, new Callback<Search>() {

            @Override
            public void success(Result<Search> result) {
                tweets = result.data.tweets;
                listTweetsAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(TwitterException exception) {}
        });
    }

//----------------------------------------------------------------------------------------------------------------

    public class ListTweetsAdapter extends RecyclerView.Adapter<ListTweetsAdapter.InnerViewHolder> {


        public ListTweetsAdapter() {
            searchTweets(search);
        }

        @Override
        public InnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.i("LOG", "onCreateViewHolder do ListTweetsAdapter.");

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.activity_main_card, parent, false);
            InnerViewHolder holder = new InnerViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(InnerViewHolder holder, int position) {
            Log.i("LOG", "onBindViewHolder do ListTweetsAdapter.");

            Tweet tweet = tweets.get(position);
            holder.nameOfUser.setText(tweet.user.name);
            holder.txtOfTweet.setText(tweet.text);

            Uri uri = Uri.parse(tweet.user.profileImageUrl);
            holder.imgUser.setImageURI(uri);
        }

        @Override
        public int getItemCount() {
            return tweets != null ? tweets.size() : 0;
        }

        public class InnerViewHolder extends RecyclerView.ViewHolder {

            public SimpleDraweeView imgUser;
            public TextView txtOfTweet;
            public TextView nameOfUser;

            public InnerViewHolder(View itemView) {
                super(itemView);
                Log.i("LOG", "construtor do InnerViewHolder.");

                imgUser = (SimpleDraweeView) itemView.findViewById(R.id.imgUser);
                nameOfUser = (TextView) itemView.findViewById(R.id.txtName);
                txtOfTweet = (TextView) itemView.findViewById(R.id.txtTweet);
            }
        }
    }

}
