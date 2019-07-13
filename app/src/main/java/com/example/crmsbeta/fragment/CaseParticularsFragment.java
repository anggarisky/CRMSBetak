package com.example.crmsbeta.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import com.example.crmsbeta.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CaseParticularsFragment extends Fragment {

    private Calendar calendar;

    private TextView sourceTitleSection;
    private TextView priorityTitleSection;
    private TextView caseTypeTitleSection;
    private TextView caseNatureTitleSection;
    private TextView progressTitleSection;

    private TextView requiredCaseStatus;
    private TextView requiredCaseHandling;
    private TextView requiredSummary;

    private TextView titleComplaintDate;
    private ImageView btnComplaintDate;

    private TextView titleReceiptDate;
    private ImageView btnReceiptDate;

    private ImageView btnCaseNature;
    private TextView titleCaseNature;
    private View caseNatureTrigger;

    private ImageView btnOfficer;
    private TextView titleOfficer;
    private View officerTrigger;

    private ImageView btnValidity;
    private TextView titleValidity;
    private View validityTrigger;

    private ImageView btnPrivacyStatus;
    private TextView titlePrivacyStatus;
    private View privacyStatusTrigger;

    private ImageView btnCaseType;
    private TextView titleCaseType;
    private View caseTypeTrigger;

    private ImageView btnSource;
    private TextView titleSource;
    private View sourceTrigger;

    private ImageView btnCouncil;
    private TextView titleCouncil;
    private View councilTrigger;

    public static CaseParticularsFragment newInstance() {

        Bundle args = new Bundle();

        CaseParticularsFragment fragment = new CaseParticularsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CaseParticularsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_caseparticulars, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sourceTitleSection = view.findViewById(R.id.sourceTitleSection);
        setSpanBintang(sourceTitleSection);

        requiredCaseStatus = view.findViewById(R.id.requiredCaseStatus);
        setSpanBintangPertama(requiredCaseStatus);

        requiredCaseHandling = view.findViewById(R.id.requiredCaseHandling);
        setSpanBintangPertama(requiredCaseHandling);

        requiredSummary = view.findViewById(R.id.requiredSummary);
        setSpanBintangPertama(requiredSummary);

        progressTitleSection = view.findViewById(R.id.progressTitleSection);
        setSpanBintang(progressTitleSection);

        caseNatureTitleSection = view.findViewById(R.id.caseNatureTitleSection);
        setSpanBintang(caseNatureTitleSection);

        priorityTitleSection = view.findViewById(R.id.priorityTitleSection);
        setSpanBintang(priorityTitleSection);

        caseTypeTitleSection = view.findViewById(R.id.caseTypeTitleSection);
        setSpanBintang(caseTypeTitleSection);

        titleComplaintDate = view.findViewById(R.id.titleComplaintDate);
        btnComplaintDate = view.findViewById(R.id.btnComplaintDate);

        titleReceiptDate = view.findViewById(R.id.titleReceiptDate);
        btnReceiptDate = view.findViewById(R.id.btnReceiptDate);

        btnCaseNature = view.findViewById(R.id.btnCaseNature);
        titleCaseNature = view.findViewById(R.id.titleCaseNature);
        caseNatureTrigger = view.findViewById(R.id.caseNatureTrigger);

        btnValidity = view.findViewById(R.id.btnValidity);
        titleValidity = view.findViewById(R.id.titleValidity);
        validityTrigger = view.findViewById(R.id.validityTrigger);

        btnOfficer = view.findViewById(R.id.btnOfficer);
        titleOfficer = view.findViewById(R.id.titleOfficer);
        officerTrigger = view.findViewById(R.id.officerTrigger);

        btnPrivacyStatus = view.findViewById(R.id.btnPrivacyStatus);
        titlePrivacyStatus = view.findViewById(R.id.titlePrivacyStatus);
        privacyStatusTrigger = view.findViewById(R.id.privacyStatusTrigger);

        btnCaseType = view.findViewById(R.id.btnCaseType);
        titleCaseType = view.findViewById(R.id.titleCaseType);
        caseTypeTrigger = view.findViewById(R.id.caseTypeTrigger);

        btnSource = view.findViewById(R.id.btnSource);
        titleSource = view.findViewById(R.id.titleSource);
        sourceTrigger = view.findViewById(R.id.sourceTrigger);

        btnCouncil = view.findViewById(R.id.btnCouncil);
        titleCouncil = view.findViewById(R.id.titleCouncil);
        councilTrigger = view.findViewById(R.id.councilTrigger);

        btnComplaintDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openCalendarComplaintDate();

            }
        });

        btnReceiptDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openCalendarReceiptDate();

            }
        });

        btnValidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), validityTrigger);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        titleValidity.setText(menuItem.getTitle());
                        return false;
                    }
                });
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.validity_casestatus_options, popupMenu.getMenu());
                popupMenu.show();
            }
        });


        btnOfficer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), officerTrigger);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        titleOfficer.setText(menuItem.getTitle());
                        return false;
                    }
                });
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.officer_casestatus_options, popupMenu.getMenu());
                popupMenu.show();
            }
        });


        btnCouncil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), councilTrigger);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        titleCouncil.setText(menuItem.getTitle());
                        return false;
                    }
                });
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.council_summary_options, popupMenu.getMenu());
                popupMenu.show();
            }
        });

        btnSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), sourceTrigger);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        titleSource.setText(menuItem.getTitle());
                        return false;
                    }
                });
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.source_summary_options, popupMenu.getMenu());
                popupMenu.show();
            }
        });

        btnCaseNature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), caseNatureTrigger);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        titleCaseNature.setText(menuItem.getTitle());
                        return false;
                    }
                });
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.case_nature_options, popupMenu.getMenu());
                popupMenu.show();
            }
        });

        btnPrivacyStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), privacyStatusTrigger);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        titlePrivacyStatus.setText(menuItem.getTitle());
                        return false;
                    }
                });
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.privacy_status_options, popupMenu.getMenu());
                popupMenu.show();
            }
        });

        btnCaseType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), caseTypeTrigger);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        titleCaseType.setText(menuItem.getTitle());
                        return false;
                    }
                });
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.case_type_options, popupMenu.getMenu());
                popupMenu.show();
            }
        });

    }

//    calendar receipt date start here

    private void openCalendarReceiptDate(){
        calendar = Calendar.getInstance();

        DatePickerDialog newDp = new DatePickerDialog(getActivity(),
                openReceiptDate(), calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        newDp.show();
    }

    private DatePickerDialog.OnDateSetListener openReceiptDate() {
        return new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);

                updateReceiptDate(calendar);
            }
        };
    }

    private void updateReceiptDate(Calendar calendar) {
        String formatDate = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
        titleReceiptDate.setText(sdf.format(calendar.getTime()));
    }

//    calendar complaint date start here

    private void openCalendarComplaintDate(){
        calendar = Calendar.getInstance();

        DatePickerDialog newDp = new DatePickerDialog(getActivity(),
                openComplaintDate(), calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        newDp.show();
    }

    private DatePickerDialog.OnDateSetListener openComplaintDate() {
        return new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);

                updateComplaintDate(calendar);
            }
        };
    }

    private void updateComplaintDate(Calendar calendar) {
        String formatDate = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
        titleComplaintDate.setText(sdf.format(calendar.getTime()));
    }

    private void setSpanBintang(TextView tvSpan) {
        SpannableString spannableString = new SpannableString(tvSpan.getText());
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
        int textLength = tvSpan.getText().length();
        spannableString.setSpan(colorSpan, textLength - 1, textLength, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvSpan.setText(spannableString);
    }

    private void setSpanBintangPertama(TextView tvSpan) {
        SpannableString spannableString = new SpannableString(tvSpan.getText());
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(colorSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvSpan.setText(spannableString);
    }
}
