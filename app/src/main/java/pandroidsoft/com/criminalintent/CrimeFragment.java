package pandroidsoft.com.criminalintent;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.text.format.DateFormat;
import android.support.v4.app.FragmentManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class CrimeFragment extends android.support.v4.app.Fragment {

    private Crime mCrime;
    private TextView mTitleField;
    private Button mButtonDate;
    private CheckBox mCheckBoxSolved;
    public static final String EXTRA_CRIME_ID =
            "com.pandroidsoft.criminalintent.crime_id";
    public static final String DIALOG_DATE = "date";
    public static final  String DIALOG_TIME = "time";
    public static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;
    private String mFormDate;
    private Button mButtonTime;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        }
        if(requestCode == REQUEST_TIME){
            Date time = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            mCrime.setTime(time);
            updateTime();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID mCrimeId = (UUID) getArguments().getSerializable((EXTRA_CRIME_ID));
        mCrime = CrimeLab.get(getActivity()).getCrime(mCrimeId);
        //mCrime = new Crime();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime, parent, false);

        mTitleField = (TextView) view.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //пока пустое место
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        // определили кнопку, установили текст, текстом вывели дату, заблокировали нажатие
        mButtonDate = (Button) view.findViewById(R.id.crime_date);
        updateDate();

        //mButtonDate.setEnabled(false); //теперь кнопка доступна
        mButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity()
                        .getSupportFragmentManager();
                //DatePickerFragment dialog = new DatePickerFragment();
                DatePickerFragment dialog = new DatePickerFragment().newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(fragmentManager, DIALOG_DATE);
            }
        });

        //назначили слушатель для изменений в CheckBox
        mCheckBoxSolved = (CheckBox) view.findViewById(R.id.crime_solved);
        mCheckBoxSolved.setChecked((mCrime.isSolved()));
        mCheckBoxSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //назначение флага раскрытия преступления
                mCrime.setSolved(isChecked);
            }
        });

        mButtonTime = (Button) view.findViewById(R.id.crime_time);
        updateTime();
        mButtonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity()
                        .getSupportFragmentManager();
                TimePickerFragment dialog = new TimePickerFragment().newInstance(mCrime.getTime());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_TIME);
                dialog.show(fragmentManager, DIALOG_TIME);
            }
        });

        return view;
    }

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID, crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;

    }


    private void updateTime() {
        String mTimeForm = DateFormat.format("hh:mm", mCrime.getTime()).toString();
        mButtonTime.setText(mTimeForm);
    }

    private void updateDate() {
        mFormDate = DateFormat.format("EEEEE,  MMM d, yyyy", mCrime.getDate()).toString();
        mButtonDate.setText(mFormDate);
    }
}
