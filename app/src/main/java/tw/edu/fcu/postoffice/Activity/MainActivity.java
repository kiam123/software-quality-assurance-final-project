package tw.edu.fcu.postoffice.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import tw.edu.fcu.postoffice.R;
import tw.edu.fcu.postoffice.Service.MyService;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout mTabLayout;
    ArrayList<Fragment> fragmentList = new ArrayList<>();
    private final String[] mTitles=new String[]{"郵件","已通知"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
        initView();
        startService();
    }

    public void initView(){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout)findViewById(R.id.tabs);
        viewPager.setAdapter(new SimpleFragmentPagerAdapter(getSupportFragmentManager(),fragmentList,mTitles));
        mTabLayout.setupWithViewPager(viewPager);
    }

    public  void initFragment(){
        fragmentList.add(new MailFragment());
        fragmentList.add(new InformedFragment());
    }

    public void startService(){
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
        String[] mtitles;
        ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();
        public SimpleFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentArrayList,String[] mtitles) {
            super(fm);
            this.fragmentArrayList = fragmentArrayList;
            this.mtitles = mtitles;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mtitles[position];
        }
    }
}
