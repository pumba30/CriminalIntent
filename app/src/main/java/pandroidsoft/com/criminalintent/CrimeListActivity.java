package pandroidsoft.com.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by pumba30 on 14.11.2014.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
