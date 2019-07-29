package com.azazellj.recyclerview.adapter.html.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity {

    private HtmlAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new HtmlAdapter();
        RecyclerView recyclerView = findViewById(R.id.root);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load();
            }
        });
    }

    private void load() {
        String data = "";

        Document document = Jsoup.parse(data);
        Element element = document.body();
        Elements elements = element.children();

        Elements els = parseElements(elements);
        adapter.setData(els);
    }

    private Elements parseElements(Elements document) {
        Elements elements = new Elements();

        for (Element element : document) {
            Elements children = element.children();

            if (children.isEmpty()) {
                elements.add(element);
            } else {
                elements.addAll(parseElements(children));
            }
        }

        return elements;
    }
}
