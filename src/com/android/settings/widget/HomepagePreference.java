/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.preference.Preference;

import com.android.settings.R;

/** A customized layout for homepage preference. */
public class HomepagePreference extends Preference {
    public HomepagePreference(Context context, AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setLayoutResource(R.layout.homepage_preference);
    }

    public HomepagePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutResource(R.layout.homepage_preference);
    }

    public HomepagePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutResource(R.layout.homepage_preference);
    }

    public HomepagePreference(Context context) {
        super(context);
        setLayoutResource(R.layout.homepage_preference);
    }

    @Override
    public void onBindViewHolder(androidx.preference.PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        styleTile(holder);
    }

    /** Styles a homepage preference tile as a uniform centered B&W 3x3 grid box. */
    public static void styleTile(androidx.preference.PreferenceViewHolder holder) {
        android.view.View titleView = holder.findViewById(android.R.id.title);
        android.view.View iconFrame = holder.findViewById(android.R.id.icon_frame);
        android.view.View iconView = holder.findViewById(android.R.id.icon);
        android.graphics.drawable.Drawable iconDrawable = (iconView instanceof android.widget.ImageView)
                ? ((android.widget.ImageView) iconView).getDrawable() : null;
        if (iconFrame != null) iconFrame.setVisibility(android.view.View.GONE);
        if (iconView != null) {
            iconView.setVisibility(android.view.View.GONE);
            if (iconView.getParent() instanceof android.view.View && iconView.getParent() != holder.itemView) {
                ((android.view.View) iconView.getParent()).setVisibility(android.view.View.GONE);
            }
        }
        if (titleView instanceof android.widget.TextView) {
            android.widget.TextView tv = (android.widget.TextView) titleView;
            tv.setVisibility(android.view.View.VISIBLE);
            tv.setTextSize(9f);
            tv.setMaxLines(1);
            tv.setEllipsize(android.text.TextUtils.TruncateAt.END);
            tv.setGravity(android.view.Gravity.CENTER);
            tv.setTextColor(android.graphics.Color.WHITE);
            tv.setCompoundDrawablePadding(6);
            if (tv.getLayoutParams() != null) {
                tv.getLayoutParams().width = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
            }
            if (tv.getParent() instanceof android.view.View && tv.getParent() != holder.itemView) {
                android.view.View parent = (android.view.View) tv.getParent();
                if (parent.getLayoutParams() != null) {
                    parent.getLayoutParams().width = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
                }
                parent.setPadding(0, 0, 0, 0);
            }
            if (iconDrawable != null) {
                iconDrawable.setTint(android.graphics.Color.WHITE);
                tv.setCompoundDrawablesWithIntrinsicBounds(null, iconDrawable, null, null);
            }
        }
        android.view.View summary = holder.findViewById(android.R.id.summary);
        if (summary != null) summary.setVisibility(android.view.View.GONE);
        holder.itemView.setPadding(4, 4, 4, 4);
        if (holder.itemView.getLayoutParams() != null) {
            holder.itemView.getLayoutParams().height = 120;
        }
        android.graphics.drawable.GradientDrawable border = new android.graphics.drawable.GradientDrawable();
        border.setColor(android.graphics.Color.BLACK);
        border.setStroke(2, 0xFF333333);
        holder.itemView.setBackground(border);
        holder.itemView.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                v.requestRectangleOnScreen(new android.graphics.Rect(0, 0, v.getWidth(), v.getHeight()), false);
            }
            border.setColor(hasFocus ? android.graphics.Color.WHITE : android.graphics.Color.BLACK);
            border.setStroke(2, hasFocus ? android.graphics.Color.WHITE : 0xFF333333);
            if (titleView instanceof android.widget.TextView) {
                ((android.widget.TextView) titleView).setTextColor(
                        hasFocus ? android.graphics.Color.BLACK : android.graphics.Color.WHITE);
            }
            if (iconDrawable != null) {
                iconDrawable.setTint(hasFocus ? android.graphics.Color.BLACK : android.graphics.Color.WHITE);
            }
        });
    }
}
