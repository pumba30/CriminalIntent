package pandroidsoft.com.criminalintent;

import android.text.format.Time;

import java.util.Date;
import java.util.UUID;


/**
 * Created by pumba30 on 12.11.2014.
 */
public class Crime {


    private UUID mUUID;
    private String mTitle;
    private Date mDate; //дата преступления
    private Date mTime;


    private boolean mSolved; //признак того, что преступление раскрыто


    public Crime() {
        //генерирование уникального индетификатора
        mUUID = mUUID.randomUUID();
        mDate = new Date();
        mTime = new Date();

    }


    public UUID getUUID() {
        return mUUID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    public Date getTime() {
        return mTime;
    }

    public void setTime(Date time) {
        mTime = time;
    }
}
