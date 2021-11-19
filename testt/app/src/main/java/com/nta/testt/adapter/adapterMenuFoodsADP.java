package com.nta.testt.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nta.testt.R;
import com.nta.testt.common.Common;
import com.nta.testt.common.Foods;
import com.nta.testt.ui.FoodsFragment;

public class adapterMenuFoodsADP extends FirebaseRecyclerAdapter<Foods,adapterMenuFoodsADP.viewMolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public adapterMenuFoodsADP(@NonNull FirebaseRecyclerOptions<Foods> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewMolder holder, int position, @NonNull Foods model) {
        holder.txtName.setText(model.getName());
        Glide.with(holder.imgFoods.getContext()).load(model.getImage()).into(holder.imgFoods);
        holder.imgFoods.setOnClickListener(view -> {
            Common.details = FoodsFragment.arrayList.get(position);
            Navigation.findNavController(view).navigate(R.id.action_foodsFragment_to_detailsFoods);
        });
    }

    @NonNull
    @Override
    public viewMolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_foods,parent,false);
        return new viewMolder(view);
    }

    public static class viewMolder extends RecyclerView.ViewHolder{
        TextView txtName;
        ImageView imgFoods;
        public viewMolder(@NonNull View itemView) {
            super(itemView);
            txtName     = (TextView) itemView.findViewById(R.id.idTVadp_Foods);
            imgFoods    = (ImageView) itemView.findViewById(R.id.idIMGadp_Foods);
        }
    }
}
