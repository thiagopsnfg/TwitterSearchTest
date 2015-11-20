package br.com.thiago.twittersearchtest.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import br.com.thiago.twittersearchtest.Adapter.ListTweetsAdapter;
import br.com.thiago.twittersearchtest.R;
import br.com.thiago.twittersearchtest.Utils.TextUtils;
import br.com.thiago.twittersearchtest.Utils.TwitterUtils;

public class MainActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private RecyclerView recyclerViewRoot;
    private ListTweetsAdapter listTweetsAdapter;
    private static List<Tweet> tweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("LOG", "onCreate()");
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        TwitterUtils.autentication(this);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Log.i("LOG", "init()");
        editTextSearch = (EditText) findViewById(R.id.et_Search);

        recyclerViewRoot = (RecyclerView) findViewById(R.id.rv_Root);
        recyclerViewRoot.setLayoutManager(new LinearLayoutManager(this));

        TextView txtSearchMsg = (TextView) findViewById(R.id.txtSearchMsg);
        TextUtils.setFont(this, txtSearchMsg, TextUtils.CUTE_CARTOON);

        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        TextUtils.setButtonFont(this, btnSearch, TextUtils.CUTE_CARTOON);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchTweets(editTextSearch.getText().toString());
            }
        });
    }

    public void searchTweets(String search) {
        Log.i("LOG", "searchTweets: "+ search);

        TwitterApiClient twitterApiClient =  TwitterCore.getInstance().getApiClient(TwitterUtils.getSession());
        twitterApiClient.getSearchService().tweets( search , null, null, null, null, 25, null, null, null, true, new Callback<Search>() {
            @Override
            public void success(Result<Search> result) {
                Log.i("LOG", "success");
                tweets = result.data.tweets;
                listTweetsAdapter = new ListTweetsAdapter(tweets);
                recyclerViewRoot.setAdapter(listTweetsAdapter);
                listTweetsAdapter.notifyDataSetChanged();
                Log.i("LOG", "Resultados da pesquisa: " + result.data.tweets.size());
            }

            @Override
            public void failure(TwitterException exception) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
