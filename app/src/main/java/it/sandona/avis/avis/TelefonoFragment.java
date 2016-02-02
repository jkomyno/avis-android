package it.sandona.avis.avis;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import it.sandona.avis.avis.helper.ExpandableListAdapter;
import it.sandona.avis.avis.helper.Utils;

/**
 * Created by Root on 29/01/2016.
 */
public class TelefonoFragment extends Fragment {

    private RecyclerView recyclerview;

    String numero1 = Utils.telSegreteria1;
    String numero2 = Utils.telSegreteria2;
    String numero3 = Utils.telPresidenza1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
/*        EditText num=(EditText)findViewById(R.id.EditText01);
        String number = "tel:" + num.getText().toString().trim();
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
        startActivity(callIntent);*/
        View view = inflater.inflate(R.layout.telephone_main, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Contatti Telefonici");
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        List<ExpandableListAdapter.Item> data = new ArrayList<>();

        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Segreteria"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, numero1));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, numero2));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Presidenza"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, numero3));

        recyclerview.setAdapter(new ExpandableListAdapter(data));
        return view;
    }
}
