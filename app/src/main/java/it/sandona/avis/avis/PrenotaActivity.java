package it.sandona.avis.avis;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class PrenotaActivity extends AppCompatActivity {

    private static String TAG = "Prenotazioni";
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private RecyclerView recyclerView;
    private Context context;

    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenota_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        context = getApplicationContext();
        initializeViewsAdapter();
        loadData();
    }

    /**
     * Initializes views and adapter
     */
    private void initializeViewsAdapter() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle(TAG);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new RecyclerViewAdapter(context, PrenotaActivity.this);
        recyclerView.setAdapter(adapter);
    }

    /**
     * load mock data to adapter
     */
    private void loadData() {
        List<Prenotazioni> prenotazioniList = new ArrayList<>();

        // Fragment destinationFragment, int prenotazioniArt, String title, String description

        Fragment f1 = new MainFragment();

        Prenotazioni registrazione = new Prenotazioni(f1, R.drawable.ic_registrati, "Registrati", "registrazione");
        Prenotazioni login = new Prenotazioni(f1, R.drawable.ic_accedi, "Accedi", "login");
        Prenotazioni resetPsw = new Prenotazioni(f1, R.drawable.ic_list, "Reset Password", "reset");

        prenotazioniList.add(registrazione);
        prenotazioniList.add(login);
        prenotazioniList.add(resetPsw);

        adapter.setPrenotazioniList(prenotazioniList);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
