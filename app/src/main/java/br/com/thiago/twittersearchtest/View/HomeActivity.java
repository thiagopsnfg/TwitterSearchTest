package br.com.thiago.twittersearchtest.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import br.com.thiago.twittersearchtest.Utils.HTTPUtils;
import br.com.thiago.twittersearchtest.R;
import br.com.thiago.twittersearchtest.Utils.TextUtils;
import br.com.thiago.twittersearchtest.Utils.TwitterUtils;
import io.fabric.sdk.android.Fabric;

public class HomeActivity extends AppCompatActivity {

    private TwitterLoginButton btnLoginTwitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterUtils.autentication(this);
        setContentView(R.layout.activity_home);
        setUpViews();
    }

    private void setUpViews() {
        setUpTwitterButton();
        setUpTxtFonts();
    }

    private void setUpTxtFonts() {
        TextView txtHomeApp = (TextView) findViewById(R.id.txtHomeApp);
        TextUtils.setFont(this, txtHomeApp, TextUtils.CUTE_CARTOON);
    }
    private void setUpTwitterButton() {
        btnLoginTwitter = (TwitterLoginButton) findViewById(R.id.btn_Login_Twiter);
        btnLoginTwitter.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Intent it = new Intent(getApplication().getBaseContext(), MainActivity.class);
                startActivity(it);
            }

            @Override
            public void failure(TwitterException e) {
                Log.i("LOG", "Falha ao autenticar usuário no Twitter.");
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if( !HTTPUtils.isNetworkAvailable(this)){
            btnLoginTwitter.setEnabled(false);
            Toast.makeText(this, R.string.without_Internet, Toast.LENGTH_SHORT).show();
            return;
        }
        btnLoginTwitter.setEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        btnLoginTwitter.onActivityResult(requestCode, resultCode, data);
    }
}
