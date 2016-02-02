package it.sandona.avis.avis;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import it.sandona.avis.avis.Parcelable.WebViewParcelable;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private WebViewParcelable model;
    private Bundle b;
    private Fragment mainFragment = new MainFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Let's display the progress in the activity title bar, like the
        // browser app does.
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getFragmentManager();
        model = new WebViewParcelable("", 0x00000000, "Home", "");
        b = new Bundle();
        b.putParcelable("WebView", model);
        mainFragment.setArguments(b);
        fm.beginTransaction().replace(R.id.content_frame, mainFragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Ciao, hai scaricato l'app gratuita dell'AVIS di San Donà di Piave? Eccola: https://play.google.com/store/apps/details?id=it.sandona.avis");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
            return true;
        } else if (id == R.id.action_info) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Informazioni App")
                    .setMessage("Applicazione sviluppata da Alberto Schiabel - ITTS V. Volterra - 2016")
                    .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fm = getFragmentManager();

        int id = item.getItemId();
        if ((id != R.id.nav_telefono) && (id != R.id.nav_prenota)) {

            if (id == R.id.nav_home) {
                model = new WebViewParcelable("", 0x00000000, "Home", "");
                b = new Bundle();
                b.putParcelable("WebView", model);
            } else if (id == R.id.nav_chi_siamo) {
                model = new WebViewParcelable("/chi-siamo", 0x00000000, "Chi Siamo", "file:///android_asset/chi-siamo.html");
                b = new Bundle();
                b.putParcelable("WebView", model);
            } else if (id == R.id.nav_sedi) {
                model = new WebViewParcelable("/sedi", 0x00000000, "Sedi Distrettuali", "");
                b = new Bundle();
                b.putParcelable("WebView", model);
            } else if (id == R.id.nav_news) {
                model = new WebViewParcelable("/argomenti/news-ed-eventi", 0x00000000, "News ed Eventi", "");
                b = new Bundle();
                b.putParcelable("WebView", model);
            } else if (id == R.id.nav_sangue) {
                model = new WebViewParcelable("/prenotazioni", 0x00000000, "Donazioni di Sangue", "");
                b = new Bundle();
                b.putParcelable("WebView", model);
            } else if (id == R.id.nav_plasma) {
                model = new WebViewParcelable("/prenotazioni", 0x00000000, "Donazioni di Plasma", "");
                b = new Bundle();
                b.putParcelable("WebView", model);
            } else if (id == R.id.nav_aspiranti_donatori) {
                model = new WebViewParcelable("/prenotazioni", 0x00000000, "Aspiranti Donatori", "");
                b = new Bundle();
                b.putParcelable("WebView", model);
            } else if (id == R.id.nav_volontari) {
                model = new WebViewParcelable("/forum/punto-di-incontro", 0x00000000, "Volontari in APPrendimento", "");
                b = new Bundle();
                b.putParcelable("WebView", model);
            } else if (id == R.id.nav_sconti_convenzioni) {
                model = new WebViewParcelable("/sconti-e-convenzioni-avis", 0x00000000, "Sconti e Convenzioni", "");
                b = new Bundle();
                b.putParcelable("WebView", model);
            } else if (id == R.id.nav_mail) {
                Intent send = new Intent(Intent.ACTION_SENDTO);
                String uriText = "mailto:" + Uri.encode("info@avissandona.it") +
                        "?subject=" + Uri.encode("AVIS San Donà di Piave") +
                        "&body=" + Uri.encode("Salve, ho un domanda da fare: ");
                Uri uri = Uri.parse(uriText);

                send.setData(uri);

                startActivity(Intent.createChooser(send, "Invia Email..."));
                return true;
            }

            mainFragment = new MainFragment();
            mainFragment.setArguments(b);
            fm.beginTransaction().addToBackStack(null).replace(R.id.content_frame, mainFragment).commit();
        } else if (id == R.id.nav_prenota) {
            Intent i = new Intent(this, PrenotaActivity.class);
            startActivity(i);
        } else {
            fm.beginTransaction().replace(R.id.content_frame, new TelefonoFragment()).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}