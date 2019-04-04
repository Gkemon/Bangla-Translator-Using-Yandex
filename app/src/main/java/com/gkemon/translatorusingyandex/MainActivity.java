package com.gkemon.translatorusingyandex;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class MainActivity extends AppCompatActivity {
    APIinterface apiInterface;
    Context context=this;
    String yandexKey = "trnsl.1.1.20190404T064003Z.9abfad32ee340aba.45073f9adfc07e672859d7c30ab3b82d7cd4681c";
    EditText editTextInput;
    Button btnTranslate;
    TextView txtViewresult;

    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = findViewById(R.id.edit_text);
        btnTranslate = findViewById(R.id.btn_translate_bangla);
        txtViewresult = findViewById(R.id.text_view);
        apiInterface = APIclient.getClient().create(APIinterface.class);
        final String languagePair = "en-bn";

        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public
            void onClick(View v) {
                translate(editTextInput.getText().toString(),languagePair);
            }
        });


    }

    void translate(final String textToBeTranslated, String languagePair){
        Call<TranslatedLanguage> call = apiInterface.doTranslate(yandexKey,textToBeTranslated,languagePair);
        call.enqueue(new Callback<TranslatedLanguage>() {
            @Override
            public void onResponse(Call<TranslatedLanguage> call, Response<TranslatedLanguage> response) {
                Log.d("GK",response.body().getText().get(0)+"");
                txtViewresult.setText(response.body().getText().get(0));
            }

            @Override
            public void onFailure(Call<TranslatedLanguage> call, Throwable t) {
                call.cancel();
                Log.d("GK",t.getLocalizedMessage()+"");
            }
        });

    }

}

