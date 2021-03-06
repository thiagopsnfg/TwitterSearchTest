package br.com.thiago.twittersearchtest.View;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import br.com.thiago.twittersearchtest.Adapter.TabsAdapter;
import br.com.thiago.twittersearchtest.Persistence.LastSearchDao;
import br.com.thiago.twittersearchtest.R;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public static String POSITION = "POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViewPager();
        setUpTabLayout();

        //Set the last searches
        LastSearchDao.loadPrefs(this);
    }

    private void setUpViewPager() {
        Log.i("LOG", "setUpViewPager");
        viewPager = (ViewPager) findViewById(R.id.viewPagerRoot);
        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager(), this);

        adapter.addFragment(new MainFragment(), getString(R.string.tab_search));
        adapter.addFragment(new LastTweetsFragments(), getString(R.string.tab_last_search_msg));
        adapter.addFragment(new SearchTTFragment(), getString(R.string.tab_top_trends));

        viewPager.setAdapter(adapter);
    }

    private void setUpTabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        if(tabLayout.getTabCount() > 3) {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, tabLayout.getSelectedTabPosition());
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        viewPager.setCurrentItem(savedInstanceState.getInt(POSITION));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LastSearchDao.salvePrefs(this);


    }
}
