package in.co.akgroups.apnaClinic.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import in.co.akgroups.apnaClinic.R;

public class CustomTextView extends AppCompatTextView {

    public final String PATH_FONT_BLACK = "Avenir-Black.ttf";
    public final String PATH_FONT_HEAVY = "Avenir-Heavy.ttf";
    public final String PATH_FONT_ROMAN = "Avenir-Roman.ttf";


    public CustomTextView(Context context) {
        super(context);
        init(context, null);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attributeSet) {

        if (attributeSet != null) {
            TypedArray arr = context.obtainStyledAttributes(attributeSet, R.styleable.CustomTextView);
            String value = arr.getString(R.styleable.CustomTextView_type_face);

            String typeface = PATH_FONT_ROMAN;
            if (!TextUtils.isEmpty(value)) {
                if (context.getString(R.string.font_roman).equals(value)) {
                    typeface = PATH_FONT_ROMAN;
                } else if (context.getString(R.string.font_heavy).equals(value)) {
                    typeface = PATH_FONT_HEAVY;
                } else if (context.getString(R.string.font_black).equals(value)) {
                    typeface = PATH_FONT_BLACK;
                }
            }

            Typeface tf = null;
            try {
                tf = Typeface.createFromAsset(context.getAssets(), typeface);
                setTypeface(tf);

            } catch (Exception e) {
                return;
            }

            arr.recycle();
        }
    }

}
