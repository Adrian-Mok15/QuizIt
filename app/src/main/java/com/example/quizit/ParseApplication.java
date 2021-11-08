package com.example.quizit;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("X3LhHvoicAnCegpK6CSOV2GmKmmkzSLfZK05sZyA")
                .clientKey("0KgSbf42lG1h1wJFkcoQ96uj5t42Jkmg8n24UaLT")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
