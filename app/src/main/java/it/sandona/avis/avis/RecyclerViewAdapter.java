package it.sandona.avis.avis;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import it.sandona.avis.avis.Parcelable.WebViewParcelable;

/**
 * Created by Root on 30/01/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.PrenotazioniViewHolder> {

    private Context context;
    private PrenotaActivity pA;

    private List<Prenotazioni> prenotazioniList;

    public RecyclerViewAdapter(Context context, PrenotaActivity pA) {
        this.context = context;
        this.pA = pA;
        prenotazioniList = new ArrayList<>();
    }

    public void setPrenotazioniList(List<Prenotazioni> prenotazioniList) {
        this.prenotazioniList = prenotazioniList;
        notifyDataSetChanged();
    }

    @Override
    public PrenotazioniViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card, parent, false);
        PrenotazioniViewHolder viewHolder = new PrenotazioniViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PrenotazioniViewHolder holder, final int position) {

        Prenotazioni prenotazione = prenotazioniList.get(position);

        holder.cardTitle.setText(prenotazione.getTitle());
        Picasso.with(context).load(prenotazione.getPrenotazioniArt()).into(holder.prenotazioniArt);
        holder.description.setText(prenotazione.getDescription());
    }

    @Override
    public int getItemCount() {
        return prenotazioniList.size();
    }

    public class PrenotazioniViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView cardTitle;
        TextView description;
        ImageView prenotazioniArt;

        RelativeLayout container;

        WebViewParcelable model;
        Bundle b;

        public PrenotazioniViewHolder(View itemView) {
            super(itemView);
            cardTitle = (TextView) itemView.findViewById(R.id.card_title);
            description = (TextView) itemView.findViewById(R.id.description);
            prenotazioniArt = (ImageView) itemView.findViewById(R.id.prenotazioni_art);
            container = (RelativeLayout) itemView.findViewById(R.id.container);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            View layout = (View) pA.findViewById(R.id.rootLayout);

            Snackbar snackbar = Snackbar.make(layout, "Funzione momentaneamente non disponibile", Snackbar.LENGTH_LONG);
            snackbar.show();

/*            Fragment mainFragment = new MainFragment();

            int itemPosition = getAdapterPosition();
            if (itemPosition == 0){
                model = new WebViewParcelable("/prenotazioni/register/", 0x00000000, "Sconti e Convenzioni", "");
                b = new Bundle();
                b.putParcelable("WebView", model);
            } else if (itemPosition == 1) {
                model = new WebViewParcelable("/prenotazioni/login/", 0x00000000, "Sconti e Convenzioni", "");
                b = new Bundle();
                b.putParcelable("WebView", model);
            } else {
                model = new WebViewParcelable("/prenotazioni/password-reset/", 0x00000000, "Sconti e Convenzioni", "");
                b = new Bundle();
                b.putParcelable("WebView", model);
            }
            mainFragment.setArguments(b);

            pA.getFragmentManager().beginTransaction().replace(R.id.rootLayout, mainFragment).commit();*/
        }
    }
}