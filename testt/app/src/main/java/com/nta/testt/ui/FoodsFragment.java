package com.nta.testt.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nta.testt.R;
import com.nta.testt.adapter.adapterMenuFoodsADP;
import com.nta.testt.common.Common;
import com.nta.testt.common.Foods;
import com.nta.testt.databinding.FragmentFoodsBinding;

import java.util.ArrayList;

public class FoodsFragment extends Fragment {
    private FragmentFoodsBinding binding;
    DatabaseReference reference;
    adapterMenuFoodsADP adapter;
    public static ArrayList<Foods> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFoodsBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        //Init firebase
        reference = FirebaseDatabase.getInstance().getReference("Foods");
        arrayList = new ArrayList<>();
        reference.orderByChild("menuId").equalTo(Common.Key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Foods foods = dataSnapshot.getValue(Foods.class);
                    arrayList.add(foods);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        
        //options cho firebase adapter recycler
        FirebaseRecyclerOptions<Foods> options = new FirebaseRecyclerOptions.Builder<Foods>()
                .setQuery(reference.orderByChild("menuId").equalTo(Common.Key),Foods.class).build();
        //Init adapter
        adapter = new adapterMenuFoodsADP(options);
        binding.idRCVFoods.setHasFixedSize(true);
        binding.idRCVFoods.setLayoutManager(new LinearLayoutManager(root.getContext()));
        binding.idRCVFoods.setAdapter(adapter);
        //
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}