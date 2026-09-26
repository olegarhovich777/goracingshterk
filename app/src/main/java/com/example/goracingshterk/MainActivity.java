package com.example.goracingshterk;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

public class MainActivity extends AppCompatActivity {

    // Флажки состояния игры (Рисунок 5)
    public boolean Started = false;
    public boolean Finished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 1. Splash Screen должен быть первой строкой
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);

        // 2. Скрываем ActionBar (Рисунок 4)
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
            // ActionBar отсутствует — ничего не делаем
        }

        // 3. Подключаем разметку
        setContentView(R.layout.activity_game);

        // 4. Полноэкранный режим — убираем статус-бар
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * Кнопка "СТАРТ" / "Пауза" / "Заново"
     */
    public void Start(View view) {
        Button button = findViewById(R.id.btnStart);

        if (!Finished) {
            if (!Started) {
                // Игра на паузе → запускаем
                button.setBackgroundColor(Color.RED);
                button.setText("Пауза");
                Started = true;
            } else if (Started) {
                // Игра идёт → ставим на паузу
                button.setBackgroundColor(Color.GREEN);
                button.setText("Старт");
                Started = false;
            }
        } else {
            // Игра окончена → перезапускаем активность
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Движение первой машины
     */
    public void Drive1(View view) {
        Button button = findViewById(R.id.btnStart);
        View Car = findViewById(R.id.Car1);
        TextView result = findViewById(R.id.tvResult);

        if (Started && !Finished) {
            ViewGroup.MarginLayoutParams margin =
                    (ViewGroup.MarginLayoutParams) Car.getLayoutParams();
            margin.leftMargin += 40;
            margin.rightMargin -= 40;
            Car.requestLayout();

            if (margin.rightMargin <= -100) {
                result.setText("Победа 1 игрока");
                button.setText("Заново");
                result.setTextColor(0xFFE91E63);
                Finished = true;
            }
        }
    }

    /**
     * Движение второй машины
     */
    public void Drive2(View view) {
        Button button = findViewById(R.id.btnStart);
        View Car = findViewById(R.id.Car2);
        TextView result = findViewById(R.id.tvResult);

        if (Started && !Finished) {
            ViewGroup.MarginLayoutParams margin =
                    (ViewGroup.MarginLayoutParams) Car.getLayoutParams();
            margin.leftMargin += 40;
            margin.rightMargin -= 40;
            Car.requestLayout();

            if (margin.rightMargin <= -100) {
                result.setText("Победа 2 игрока");
                button.setText("Заново");
                result.setTextColor(0xFF000000);
                Finished = true;
            }
        }
    }
}