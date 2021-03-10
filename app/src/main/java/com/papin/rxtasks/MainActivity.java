package com.papin.rxtasks;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter();


//        presenter.loadSmth();
        presenter.task1();
//        presenter.task2();
//        presenter.task3();
//        presenter.task4();
//        presenter.task5();
//        presenter.task7();
//        presenter.task8();
//        presenter.task9();
//        presenter.task10();
//        presenter.task11();


    }
}