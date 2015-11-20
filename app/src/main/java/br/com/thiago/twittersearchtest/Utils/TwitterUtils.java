package br.com.thiago.twittersearchtest.Utils;

import android.content.Context;
import android.util.Log;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterSession;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Thiago on 17/11/2015.
 */
public class TwitterUtils {

    private static final String TWITTER_KEY = "Ygo6sCcb3SJmLWpWzuEHMCFzQ";
    private static final String TWITTER_SECRET = "2zTz7zhArCvqyI5sA6QqvCLknRDThQc8QoYTSkIigryrRnR6Dm";

    public static void autentication(Context ctx) {
        Log.i("LOG", "Entrou no autentication : " + ctx.getPackageName());

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TwitterUtils.TWITTER_KEY, TwitterUtils.TWITTER_SECRET);

        Log.i("LOG","Inicializando o Fabricn ... authConfig.getConsumerKey()" + authConfig.getConsumerKey());
        Log.i("LOG", "Inicializando o Fabricn ... authConfig.getConsumerSecret()" + authConfig.getConsumerSecret());
        Log.i("LOG", "Inicializando o Fabricn ... authConfig.getRequestCode()" + authConfig.getRequestCode());

        Fabric.with(ctx, new Twitter(authConfig));

    }

    public static TwitterSession getSession(){
        return Twitter.getSessionManager().getActiveSession();
    }


}
