package com.example.crmsbeta.fragment;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.crmsbeta.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class CaseFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private ImageView btnDate;

    private ImageView btnAttachmentCommentType;

    private View containerAttachmentCommentType;
    private ImageView btnExpand;
    private ImageView btnExpand2;
    private ExpandableLayout expandableLayout1;
    private ExpandableLayout expandableLayout2;
    private TextView tvDate;
    private TextView lblCommentIssueDate;
    private TextView lblAttachmentCommentType;
    private TextView tvTitlePopupMenu;
    private Calendar calendar;

    public static CaseFragment newInstance() {

        Bundle args = new Bundle();

        CaseFragment fragment = new CaseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_case, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnDate = view.findViewById(R.id.btnDate);
        tvDate = view.findViewById(R.id.tvDate);
        tvTitlePopupMenu = view.findViewById(R.id.tvTitlePopupMenu);
        btnAttachmentCommentType = view.findViewById(R.id.btnAttachmentCommentType);
        lblAttachmentCommentType = view.findViewById(R.id.lblAttachmentCommentType);
        lblCommentIssueDate = view.findViewById(R.id.lblCommentIssueDate);
        containerAttachmentCommentType = view.findViewById(R.id.containerAttachmentCommentType);
        btnExpand = view.findViewById(R.id.btnExpand);
        btnExpand2 = view.findViewById(R.id.btnExpand2);
        expandableLayout1 = view.findViewById(R.id.expandable_layout_1);
        expandableLayout2 = view.findViewById(R.id.expandable_layout_2);

        setSpanBintang(lblAttachmentCommentType);
        setSpanBintang(lblCommentIssueDate);

        final Drawable imgUp = ContextCompat.getDrawable(getActivity(), R.drawable.arrow_up_expandable);
        final Drawable imgDown = ContextCompat.getDrawable(getActivity(), R.drawable.arrow_down_expandable);

        btnExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandableLayout1.isExpanded()) {
                    expandableLayout1.collapse();
                    btnExpand.setImageDrawable(imgDown);
                } else {
                    expandableLayout1.expand();
                    btnExpand.setImageDrawable(imgUp);
                }
            }
        });

        btnExpand2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandableLayout2.isExpanded()) {
                    expandableLayout2.collapse();
                    btnExpand2.setImageDrawable(imgDown);
                } else {
                    expandableLayout2.expand();
                    btnExpand2.setImageDrawable(imgUp);
                }
            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalendar();
            }
        });

        btnAttachmentCommentType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), containerAttachmentCommentType);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        tvTitlePopupMenu.setText(menuItem.getTitle());
                        return false;
                    }
                });
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.popup_action, popupMenu.getMenu());
                popupMenu.show();
            }
        });
    }

    private void setSpanBintang(TextView tvSpan) {
        SpannableString spannableString = new SpannableString(tvSpan.getText());
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
        int textLength = tvSpan.getText().length();
        spannableString.setSpan(colorSpan, textLength - 1, textLength, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvSpan.setText(spannableString);
    }

    private void initiatePopup() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.layout_row_popup, null);
        TextView title = layout.findViewById(R.id.tvTitlePopup);


    }

    private void openCalendar() {
        calendar = Calendar.getInstance();
        DatePickerDialog dp = new DatePickerDialog(getActivity(), this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dp.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        updatelabel(calendar);
    }

    private void updatelabel(Calendar calendar) {
        String formatDate = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
        tvDate.setText(sdf.format(calendar.getTime()));
    }
}
