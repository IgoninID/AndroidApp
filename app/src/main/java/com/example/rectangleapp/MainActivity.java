package com.example.rectangleapp;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public Rect rec = new Rect();

    public void onClickCalc(View view)
    {
        final int errnonum = 0xe46a63ff; // цвет поля для невозможности преобразования введенного значения
        final int errzeroorminus = 0xE0362BFF; // цвет поля для отрицательного или нулевого введенного числа

        TextInputEditText lentx = findViewById(R.id.length);
        TextInputEditText widtx = findViewById(R.id.width);
        TextInputEditText ar = findViewById(R.id.area);
        TextInputEditText per = findViewById(R.id.perim);

        lentx.setBackground(null); //цвет поля по умолчанию без ошибок
        widtx.setBackground(null); //цвет поля по умолчанию без ошибок
        Double side1 = 1.0; // длина
        Double side2 = 1.0; // ширина
        try
        {
            side1 = Double.parseDouble(String.valueOf(lentx.getText())); // пробуем преобразовать введенную пользователем длину
        }
        catch (NumberFormatException ex) // если невозможно преобразовать символ в поле в число double
        {
            lentx.setBackgroundColor(errnonum); // меняем цвет полей на розовый
            return;
        }
        try
        {
            side2 = Double.parseDouble(String.valueOf(widtx.getText())); // пробуем преобразовать введенную пользователем ширину
        }
        catch (NumberFormatException ex)
        {
            widtx.setBackgroundColor(errnonum); // меняем цвет полей на розовый
            return;
        }
        try
        {
            rec.setallsides(side1, side2); // пробуем создать прямоугольник с введенными сторонами
        }
        catch (RuntimeException ex) // если пользователь ввел отрицательное число или 0
        {
            lentx.setBackgroundColor(errzeroorminus); // меняем цвет полей на красный
            widtx.setBackgroundColor(errzeroorminus);
            return;
        }
        per.setText(Double.toString(rec.calcperim())); // вывод периметра
        ar.setText(Double.toString(rec.calcarea())); // вывод площади

    }
}