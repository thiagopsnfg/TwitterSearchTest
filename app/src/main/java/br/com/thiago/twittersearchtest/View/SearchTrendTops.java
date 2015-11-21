package br.com.thiago.twittersearchtest.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.thiago.twittersearchtest.R;

/**
 * Created by Thiago on 21/11/2015.
 */
public class SearchTrendTops extends Fragment {

    public SearchTrendTops() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_trendtops, container, false);
    }
}

