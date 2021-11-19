package com.nta.testt.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nta.testt.R;
import com.nta.testt.common.Common;
import com.nta.testt.databinding.FragmentDetailsFoodsBinding;
import com.squareup.picasso.Picasso;

public class DetailsFoods extends Fragment {
    private FragmentDetailsFoodsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Init binding
        binding = FragmentDetailsFoodsBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        //default quantity
        binding.idBTNquantityDetailsFoods.setText(1+"");
        //binding data
        Glide.with(root.getContext()).load(Common.details.getImage()).into(binding.idIMGdetailFoods);
        binding.idTVnameDetailsFoods.setText(Common.details.getName());
        binding.idTVpriceDetailsFoods.setText(Common.details.getPrice());
        binding.idComment.setText(Common.details.getDescription());

        //add and sub quantity of foods
        Button btnQuantity = binding.idBTNquantityDetailsFoods;
        binding.idBTNsubDetailsFoods.setOnClickListener(view -> {
            if (btnQuantity.getText().toString().equals("0")){
            }else{
                btnQuantity.setText(Integer.parseInt(btnQuantity.getText().toString())-1+"");
            }
        });
        binding.idBTNaddDetailsFoods.setOnClickListener(view ->
                btnQuantity.setText(Integer.parseInt(btnQuantity.getText().toString())+1+""));

        return root;
    }
}