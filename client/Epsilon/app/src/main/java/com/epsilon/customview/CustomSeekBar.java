package com.epsilon.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class CustomSeekBar extends SeekBar {

    private Paint textPaint;

    private Rect textBounds = new Rect();

    private String text = "";

    public CustomSeekBar(Context context) {
        super(context);
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);

    }

    public CustomSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        textPaint = new Paint();
        textPaint.setTypeface(Typeface.SANS_SERIF);
        textPaint.setColor(Color.BLACK);

    }

    public CustomSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);


        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        // First draw the regular progress bar, then custom draw our text
        super.onDraw(canvas);


        int progress = getProgress();
        text = progress + "";

        // Now get size of seek bar.
        float width = getWidth();
        float height = getHeight();

        // Set text size.
        textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        textPaint.setTextSize(40);
        // Get size of text.
        textPaint.getTextBounds(text, 0, text.length(), textBounds);

        // Calculate where to start printing text.
        float position = (width / getMax()) * getProgress();

        // Get start and end points of where text will be printed.
        float textXStart = position - textBounds.centerX();
        float textXEnd = position + textBounds.centerX();

        // Check does not start drawing outside seek bar.
        if (textXStart <= 1) textXStart = 20;

        if (textXEnd > width) {
            textXStart -= (textXEnd - width + 30);
        }
        // Calculate y text print position.
        float yPosition = height;

        canvas.drawText(text, textXStart, yPosition, textPaint);
    }

    public synchronized void setTextColor(int color) {
        super.drawableStateChanged();
        textPaint.setColor(color);
        drawableStateChanged();
    }


}