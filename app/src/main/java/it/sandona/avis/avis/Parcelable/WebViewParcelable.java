package it.sandona.avis.avis.Parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import it.sandona.avis.avis.R;

/**
 * Created by Root on 30/01/2016.
 */
public class WebViewParcelable implements Parcelable {
    private String subDomain;
    private int color = R.color.transparent;
    private String title;
    private String localUrl = "";

    public WebViewParcelable(String subDomain, int color, String title, String localUrl) {
        this.subDomain = subDomain;
        this.color = color;
        this.title = title;
        this.localUrl = localUrl;
    }

    public int getColor() {
        return color;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public String getSubDomain() {
        return subDomain;
    }

    public String getTitle() {
        return title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.subDomain);
        dest.writeInt(this.color);
        dest.writeString(this.title);
        dest.writeString(this.localUrl);
    }

    protected WebViewParcelable(Parcel in) {
        this.subDomain = in.readString();
        this.color = in.readInt();
        this.title = in.readString();
        this.localUrl = in.readString();
    }

    public static final Creator<WebViewParcelable> CREATOR = new Creator<WebViewParcelable>() {
        public WebViewParcelable createFromParcel(Parcel source) {
            return new WebViewParcelable(source);
        }

        public WebViewParcelable[] newArray(int size) {
            return new WebViewParcelable[size];
        }
    };
}