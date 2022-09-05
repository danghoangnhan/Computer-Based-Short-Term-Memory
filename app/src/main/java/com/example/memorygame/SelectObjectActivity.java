package com.example.memorygame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.memorygame.Adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SelectObjectActivity extends AppCompatActivity {

    List<Integer> selectedImage;
    RecyclerView recyclerView;
    List<String> dataSource;
    LinearLayoutManager  linearLayoutManager;
    RecyclerViewAdapter recyclerViewAdapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_object);
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
        this.selectedImage = new ArrayList<>();
        Map <Integer,Button> buttonIntegerMap = zipToMap(images,buttons);
        buttonIntegerMap.entrySet().forEach(buttonElement->{
                Integer Image = buttonElement.getKey();
                Button button = buttonElement.getValue();
                button.setBackgroundResource(Image);
                button.setOnClickListener(view -> selectedClick(view,button));
        });
        recyclerView = findViewById(R.id.recycleview);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static <K, V> Map<K, V> zipToMap(List<K> keys, List<V> values) {
        return IntStream.range(0, keys.size()).boxed()
                .collect(Collectors.toMap(keys::get, values::get));
    }
    public void selectedClick(View view,Button button){
        this.selectedImage.add(button.getId());
    }
}