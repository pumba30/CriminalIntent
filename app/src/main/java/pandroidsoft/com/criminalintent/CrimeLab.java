package pandroidsoft.com.criminalintent;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.UUID;


/**
 * Created by pumba30 on 13.11.2014.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private Context mAppContext;
    private ArrayList<Crime> mCrimes;

    private CrimeLab(Context appContext) {
        mAppContext = appContext;
        mCrimes = new ArrayList<Crime>();
        for(int i = 0; i < 100; i++){
            Crime c = new Crime();
            c.setTitle("Crime№ " + i);
            c.setSolved(i % 2 == 0); //для каждого второго объекта
            mCrimes.add(c);
        }
    }

    public static CrimeLab get(Context c) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    //метод возвращает список
    public ArrayList<Crime> getCrimes() {
        return mCrimes;
    } ;

    //метод возвращает объект Crime с заданным индетификатором
    public Crime getCrime(UUID id) {
        for (Crime c : mCrimes) {
            if (c.getUUID().equals(id)) {
                return c;
            }
        }
        return null;
    }

    ;

}
