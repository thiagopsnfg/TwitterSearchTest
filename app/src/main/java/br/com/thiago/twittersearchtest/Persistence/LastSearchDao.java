package br.com.thiago.twittersearchtest.Persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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

    public static void insert(String search){
        if( lastSearchList.size() > 5) {
            lastSearchList.remove(0);
            Log.i("LOG", "Item removido, tamanho atual: " + lastSearchList.size());
        }

        lastSearchList.add( search );
    }

    public static void loadPrefs( Context ctx){
        SharedPreferences prefs = ctx.getSharedPreferences(LAST_SEARCH, Context.MODE_PRIVATE);
        Set<String> temp = prefs.getStringSet(LAST_SEARCH, new HashSet<String>());
        lastSearchList.addAll(temp);
        Log.i("LOG", "Retortando com " + lastSearchList.size());
    }

    public static void salvePrefs( Context ctx) {
        Log.i("LOG", "Salvando com " + lastSearchList.size());
        SharedPreferences.Editor ed = ctx.getSharedPreferences(LAST_SEARCH, Context.MODE_PRIVATE).edit();
        ed.putStringSet(LAST_SEARCH, (Set<String>) lastSearchList);
        ed.commit();
    }

    public static List<String> getLastSearchList(){
        return lastSearchList;
    }


}
