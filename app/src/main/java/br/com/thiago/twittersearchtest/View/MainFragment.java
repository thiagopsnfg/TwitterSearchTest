package br.com.thiago.twittersearchtest.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.SearchService;
import com.twitter.sdk.android.tweetui.TweetViewAdapter;

import java.util.List;

import br.com.thiago.twittersearchtest.Persistence.LastSearchDao;
import br.com.thiago.twittersearchtest.R;
import br.com.thiago.twittersearchtest.Utils.TextUtils;

public class MainFragment extends Fragment {

    private EditText editTextSearch;
    private static List<Tweet> tweets;
    private TweetViewAdapter adapter;
    private static final String SEARCH_RESULT_TYPE = "recent";
    private static final int SEARCH_COUNT = 20;
    private SearchService service;
    ListView SearchList;
    private Context context;
    private String search;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //Set Text for search
        editTextSearch = (EditText) view.findViewById(R.id.et_Search);
        editTextSearch.setTypeface(TextUtils.getTypeface(context, TextUtils.FONT_CUTE_CARTOON));

        adapter = new TweetViewAdapter(context);
        SearchList = (ListView) view.findViewById(R.id.searchMainList);
        SearchList.setAdapter(adapter);

        //Set Button for search
        Button btnSearch = (Button) view.findViewById(R.id.btnSearch);
        btnSearch.setTypeface(TextUtils.getTypeface(context, TextUtils.FONT_CUTE_CARTOON));
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search = editTextSearch.getText().toString();
                searchTweets(search);
                TextUtils.hideKeyboard(context, editTextSearch);
                LastSearchDao.insert(search);
            }
        });

        return view;
    }

    public void searchTweets(String search) {
        service = Twitter.getApiClient().getSearchService();
        service.tweets(search, null, null, null, SEARCH_RESULT_TYPE, SEARCH_COUNT, null, null, null, true, new Callback<Search>() {

                    @Override
                    public void success(Result<Search> searchResult) {
                        List<Tweet> tweets = searchResult.data.tweets;
                        adapter.getTweets().clear();
                        adapter.getTweets().addAll(tweets);
                        adapter.notifyDataSetChanged();
                        System.out.println("adapter.notifyDataSetChanged() ");
                        Log.i("LOG","adapter.notifyDataSetChanged() ");
                    }

                    @Override
                    public void failure(TwitterException error) {
                        Toast.makeText(context, getString(R.string.fail), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
