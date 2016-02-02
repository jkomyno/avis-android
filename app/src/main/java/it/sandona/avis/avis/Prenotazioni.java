package it.sandona.avis.avis;

import android.app.Fragment;

/**
 * Created by Root on 30/01/2016.
 */
public class Prenotazioni {
    String title;
    int prenotazioniArt;
    Fragment destinationFragment;
    String description;

    public Prenotazioni(Fragment destinationFragment, int prenotazioniArt, String title, String description) {
        this.destinationFragment = destinationFragment;
        this.prenotazioniArt = prenotazioniArt;
        this.title = title;
        this.description = description;
    }

    public Fragment getDestinationFragment() {
        return destinationFragment;
    }

    public int getPrenotazioniArt() {
        return prenotazioniArt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDestinationFragment(Fragment destinationFragment) {
        this.destinationFragment = destinationFragment;
    }

    public void setPrenotazioniArt(int prenotazioniArt) {
        this.prenotazioniArt = prenotazioniArt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
