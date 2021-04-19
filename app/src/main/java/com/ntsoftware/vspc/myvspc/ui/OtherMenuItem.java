package com.ntsoftware.vspc.myvspc.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.card.MaterialCardView;
import com.ntsoftware.vspc.myvspc.R;

public class OtherMenuItem extends MaterialCardView {

    protected ConstraintLayout baseLayout;

    protected ImageView itemIcon;

    protected TextView itemTitle;

    public OtherMenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.other_item,this);

        baseLayout = findViewById(R.id.other_item_root);
        itemIcon = findViewById(R.id.other_item_icon);
        itemTitle = findViewById(R.id.other_item_title);

        this.setClickable(true);
        this.setFocusable(true);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.OtherMenuItem);

        Drawable drawable = typedArray.getDrawable(R.styleable.OtherMenuItem_piv_icon);
        if(drawable != null) itemIcon.setImageDrawable(drawable);

        String string = typedArray.getString(R.styleable.OtherMenuItem_piv_title);
        if (string != null) itemTitle.setText(string);

        typedArray.recycle();
    }

    public void setTitle(CharSequence sequence) {
        if (itemTitle != null) {
            itemTitle.setText(sequence);
        }
    }
}
