package br.com.thiago.twittersearchtest.Adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import br.com.thiago.twittersearchtest.R;

/**
 * Created by Thiago on 20/11/2015.
 */
public class ListTweetsAdapter extends RecyclerView.Adapter<ListTweetsAdapter.InnerViewHolder>{

    private List<Tweet> tweets;

    public ListTweetsAdapter(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    @Override
    public InnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tweets, parent, false);

        InnerViewHolder myViewHolder = new InnerViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(InnerViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.nameOfUser.setText(tweet.user.name);
        holder.txtOfTweet.setText(tweet.text);

        Uri uri = Uri.parse(tweet.user.profileImageUrl);
        holder.imgUser.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {

        public SimpleDraweeView imgUser;
        public TextView txtOfTweet;
        public TextView nameOfUser;

        public InnerViewHolder(View itemView) {
            super(itemView);

            imgUser = (SimpleDraweeView) itemView.findViewById(R.id.imgUser);
            nameOfUser = (TextView) itemView.findViewById(R.id.txtName);
            txtOfTweet = (TextView) itemView.findViewById(R.id.txtTweet);


        }
    }
}
