package br.com.thiago.twittersearchtest.Persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Thiago on 21/11/2015.
 */
public class LastSearchDao {

    private static List<String> lastSearchList = new LinkedList<>();

    private static final String LAST_SEARCH = "lastSeach";

    public static void insert(String search) {
        if (lastSearchList.size() > 50) {
            while (lastSearchList.size() > 40)
                lastSearchList.remove(0);
        }
        lastSearchList.add(search);
    }

    public static void loadPrefs(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(LAST_SEARCH, Context.MODE_PRIVATE);
        Set<String> temp = prefs.getStringSet(LAST_SEARCH, null);

        if (temp != null) {
            lastSearchList.clear();
            lastSearchList.addAll(temp);
        }
    }

    public static void salvePrefs(Context ctx) {
        SharedPreferences.Editor ed = ctx.getSharedPreferences(LAST_SEARCH, Context.MODE_PRIVATE).edit();
        ed.clear();
        Set<String> set = new HashSet<>(lastSearchList);
        ed.putStringSet(LAST_SEARCH, set);
        ed.commit();
    }

    public static List<String> getLastSearchList() {
        return lastSearchList;
    }


}
