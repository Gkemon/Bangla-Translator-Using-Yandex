package com.gkemon.translatorusingyandex;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public
class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
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
        setUpDrawer();

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

    @Override
    public
    void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public
    boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public
    boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public
    boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void setUpDrawer(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public
            void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

