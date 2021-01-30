package com.ntsoftware.vspc.myvspc.ui;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;

import com.google.android.material.appbar.MaterialToolbar;
import com.ntsoftware.vspc.myvspc.R;

public class CenteredTitleToolbar extends MaterialToolbar {

    private TextView _titleTextView;
    private int _screenWidth;
    private boolean _centerTitle = true;

    public CenteredTitleToolbar(Context context) {
        super(context);
        init();
    }

    public CenteredTitleToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CenteredTitleToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        _screenWidth = getScreenSize().x;
        _titleTextView = new TextView(getContext());
        _titleTextView.setTextAppearance(getContext(), R.style.Theme_MyVSPC_ToolBar_Title);
        _titleTextView.setText("Title");
        addView(_titleTextView);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (_centerTitle) {
            int[] location = new int[2];
            _titleTextView.getLocationOnScreen(location);
            _titleTextView.setTranslationX(_titleTextView.getTranslationX() + (-location[0] + _screenWidth / 2 - _titleTextView.getWidth() / 2));
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        _titleTextView.setText(title);
        requestLayout();
    }

    @Override
    public void setTitle(int titleRes) {
        _titleTextView.setText(titleRes);
        requestLayout();
    }

    public void setTitleCentered(boolean centered) {
        _centerTitle = centered;
        requestLayout();
    }

    private Point getScreenSize() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point screenSize = new Point();
        display.getSize(screenSize);

        return screenSize;
    }
}
