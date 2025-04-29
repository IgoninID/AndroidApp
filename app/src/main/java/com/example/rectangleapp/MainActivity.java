package com.example.rectangleapp;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

/**
 * Главный класс активности приложения для вычисления площади и периметра прямоугольника.
 * Наследуется от AppCompatActivity, который предоставляет совместимость с современными функциями Android и упрощает управление активностью на разных версиях ОС.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Метод жизненного цикла активности, вызываемый при её создании.
     * @param savedInstanceState Содержит данные, сохранённые при предыдущем закрытии активности,
     *                           или null, если активность создаётся впервые.
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Вызывает реализацию метода onCreate в родительском классе AppCompatActivity для инициализации базовых компонентов активности.
        EdgeToEdge.enable(this); // Включает режим отображения приложения в полноэкранном режиме, позволяя контенту занимать область системных панелей (например, статус-бара).
        setContentView(R.layout.activity_main); // Устанавливает XML-лейаут activity_main.xml в качестве пользовательского интерфейса активности.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> { // Устанавливает слушатель для обработки отступов (insets) системных панелей, чтобы контент не перекрывался ими.
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()); // Получает размеры системных панелей (статус-бар, навигационная панель) для корректного размещения контента.
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom); // Устанавливает отступы для корневого View (с id "main") в соответствии с размерами системных панелей.
            return insets; // Возвращает объект insets для дальнейшей обработки системой, подтверждая, что отступы применены.
        });
    }

    /**
     * Поле для хранения объекта прямоугольника, используемого для вычислений.
     */
    public Rect rec = new Rect();

    /**
     * Обработчик нажатия кнопки "Вычислить" для расчёта площади и периметра прямоугольника.
     * @param view View-объект кнопки, вызвавшей событие (в данном случае кнопка с id "Calc").
     */
    public void onClickCalc(View view)
    {
        final int errnonum = 0xe46a63ff; // цвет поля для невозможности преобразования введенного значения (фиолетовый)
        final int errzero = 0xE0362BFF; // цвет поля для отрицательного или нулевого введенного числа (синий)

        TextInputEditText lentx = findViewById(R.id.length); // Находит поле ввода длины (TextInputEditText с id "length") из XML-лейаута для получения введённого пользователем значения.
        TextInputEditText widtx = findViewById(R.id.width); // Находит поле ввода ширины (TextInputEditText с id "width") из XML-лейаута для получения введённого пользователем значения.
        TextInputEditText ar = findViewById(R.id.area); // Находит поле вывода площади (TextInputEditText с id "area") из XML-лейаута для отображения результата вычисления.
        TextInputEditText per = findViewById(R.id.perim); // Находит поле вывода периметра (TextInputEditText с id "perim") из XML-лейаута для отображения результата вычисления.

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
            widtx.setBackgroundColor(errnonum); // меняем цвет полей на фиолетовый
            return;
        }
        try
        {
            rec.setallsides(side1, side2); // пробуем создать прямоугольник с введенными сторонами
        }
        catch (RuntimeException ex) // если пользователь ввел отрицательное число или 0
        {
            lentx.setBackgroundColor(errzero); // меняем цвет полей на синий
            widtx.setBackgroundColor(errzero);
            return;
        }
        per.setText(Double.toString(rec.calcperim())); // вывод периметра
        ar.setText(Double.toString(rec.calcarea())); // вывод площади

    }
}