package com.example.memorygame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setSupportActionBar(toolBar);
        List<Integer> images = Arrays.asList(R.drawable.camel, R.drawable.fox, R.drawable.lion, R.drawable.coala,R.drawable.camel, R.drawable.fox, R.drawable.lion, R.drawable.coala,R.drawable.lion);
        List<Button> buttons = Arrays.asList(
                findViewById(R.id.button1),
                findViewById(R.id.button2),
                findViewById(R.id.button3),
                findViewById(R.id.button4),
                findViewById(R.id.button5),
                findViewById(R.id.button6),
                findViewById(R.id.button7),
                findViewById(R.id.button8),
                findViewById(R.id.button9));

        AtomicReference<Integer> clicked = new AtomicReference<>(0);
        AtomicBoolean turnOver = new AtomicBoolean(false);
        Integer cardBack = R.drawable.code;
        Integer lastClicked = -1;
        Map<Button,Integer> buttonIntegerMap = zipToMap(buttons,images);

        buttonIntegerMap.entrySet().forEach(Element -> {
            Button buttonElement = Element.getKey();
            Integer pairdImage = Element.getValue();
            buttonElement.setBackgroundResource(R.drawable.code);
            buttonElement.setText("cardBacK");
            buttonElement.setTextSize(0.0F);
            buttonElement.setOnClickListener(v -> {
                if (buttonElement.getText().equals("cardBack") && !turnOver.get()) {
                    buttonElement.setBackgroundResource(pairdImage);
                    buttonElement.setText(pairdImage);
                }
                else  {
                    buttonElement.setBackgroundResource(cardBack);
                    buttonElement.setText("cardBack");
                    clicked.getAndSet(clicked.get() - 1);
                }
                if (clicked.get() == 2) {
                    turnOver.set(true);
                    if (buttonElement.getText().equals(buttons.get(lastClicked))) {
                        buttonElement.setClickable(false);
                        buttons.get(lastClicked).setClickable(false);
                    }
                }
                else if (clicked.get()==0){
                    turnOver.set(false);

                }
            });
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static <K, V> Map<K, V> zipToMap(List<K> keys, List<V> values) {
        return IntStream.range(0, keys.size()).boxed()
                .collect(Collectors.toMap(keys::get, values::get));
    }
}