package pandroidsoft.com.criminalintent;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by pumba30 on 14.11.2014.
 */
public class CrimePagerActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private ArrayList<Crime> mCrimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mCrimes = CrimeLab.get(this).getCrimes();

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int i) {
                Crime crime = mCrimes.get(i);
                return CrimeFragment.newInstance(crime.getUUID());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
        //В ActionBar  вместо заголовка приложения выводим краткое описание преступления(действия)
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                        Crime crime = mCrimes.get(i);
                if(crime.getTitle() != null){
                    setTitle(crime.getTitle());
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        for (int i = 0; i < mCrimes.size(); i++) {
            if(mCrimes.get(i).getUUID().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
