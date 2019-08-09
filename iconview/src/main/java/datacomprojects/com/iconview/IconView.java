package datacomprojects.com.iconview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;


/**
 * Класс который рисует иконку с нужным цветом.
 *
 * Если IconView.isSelected() , тогда цвет иконки будет selectedColor, в другом случае , цвет будет defaultColor.
 * Значения цветов можно указать в XML (атрибуты default_color" и "selected_color").
 * По дефолту , defaultColor = BLACK , а selectedColor = R.color.colorPrimaryDark.
 * Также цвета можно указать через методы setSelectedColor(int selectedColor) и setDefaultColor(int defaultColor).
 *
 * Важно!
 * Класс меняет все цвета входного изображения на один из этих двух цветов. Соотвественно, если у иконки все пиксели непрозрачны,
 * выходное изображение будет одноцветным прямоугольником размером с входным изображением.
 *
 * @author Adrian Vlad
 */

public class IconView extends androidx.appcompat.widget.AppCompatImageView {

    private final int DEFAULT_VALUE_OF_DEFAULT_COLOR = Color.BLACK;
    private final int DEFAULT_VALUE_OF_SELECTED_COLOR = Color.WHITE;

    private int defaultColor = DEFAULT_VALUE_OF_DEFAULT_COLOR;
    private int selectedColor = DEFAULT_VALUE_OF_SELECTED_COLOR;


    public IconView(Context context) {
        super(context);
        init(null);
    }

    public IconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public IconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs){

        if(attrs != null) {
            TypedArray arr = getContext().obtainStyledAttributes(attrs, R.styleable.IconView);
            defaultColor = arr.getColor(R.styleable.IconView_default_color, DEFAULT_VALUE_OF_DEFAULT_COLOR);
            selectedColor = arr.getColor(R.styleable.IconView_selected_color, DEFAULT_VALUE_OF_SELECTED_COLOR);
            arr.recycle();
        }

        updateColorFilter();
    }

    private void updateColorFilter(){
        setColorFilter(isSelected() ? selectedColor : defaultColor);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        updateColorFilter();
    }
}
