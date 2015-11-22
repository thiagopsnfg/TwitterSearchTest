package br.com.thiago.twittersearchtest.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.SearchService;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TweetViewAdapter;

import java.util.List;

import br.com.thiago.twittersearchtest.R;
import br.com.thiago.twittersearchtest.Utils.TextUtils;

/**
 * Created by Thiago on 21/11/2015.
 */
public class SearchTTFragment extends Fragment {

    private Context context;
    private static String SEARCH_QUERY = "#Trends";
    private TweetViewAdapter adapter;
    private static final String SEARCH_RESULT_TYPE = "recent";
    private static final int SEARCH_COUNT = 20;
    private ProgressBar progressBar;
    private SearchService service;
    ListView SearchList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ttopics, container, false);
        context = view.getContext();

        adapter = new TweetViewAdapter(view.getContext());
        SearchList = (ListView) view.findViewById(R.id.search_list);
        SearchList.setAdapter(adapter);

        //Set Button to search TTrends.
        Button btnSearchTT = (Button) view.findViewById(R.id.btnSearchTT);
        btnSearchTT.setTypeface(TextUtils.getTypeface(context, TextUtils.FONT_CUTE_CARTOON));
        btnSearchTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchTTopics();
            }
        });

        return view;
    }

    private void searchTTopics() {
        service = Twitter.getApiClient().getSearchService();
        service.tweets(SEARCH_QUERY, null, null, null, SEARCH_RESULT_TYPE, SEARCH_COUNT, null, null, null, true, new Callback<Search>() {

                    @Override
                    public void success(Result<Search> searchResult) {
                        final List<Tweet> tweets = searchResult.data.tweets;
                        adapter.getTweets().addAll(tweets);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void failure(TwitterException error) {
                        Toast.makeText(context, getString(R.string.fail), Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }


}

