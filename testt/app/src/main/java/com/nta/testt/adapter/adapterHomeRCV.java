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
import com.nta.testt.common.Category;
import com.nta.testt.R;
import com.nta.testt.common.Common;
import com.nta.testt.ui.HomeFragment;
import com.squareup.picasso.Picasso;

public class adapterHomeRCV extends FirebaseRecyclerAdapter<Category,adapterHomeRCV.viewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public adapterHomeRCV(@NonNull FirebaseRecyclerOptions<Category> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull Category model) {
        holder.txtNameFoods.setText(model.getName());
        Picasso.get().load(model.getImage()).placeholder(R.drawable.wait).error(R.drawable.error).fit().into(holder.imgFoods);
        //Glide.with(holder.imgFoods.getContext()).load(model.getImage()).into(holder.imgFoods);
        holder.imgFoods.setOnClickListener(view -> {
            Common.Key = HomeFragment.listKey.get((position));
            Navigation.findNavController(view).navigate(R.id.action_nav_menu_to_foodsFragment);
        });
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter_home,parent,false);
        return new viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imgFoods;
        TextView txtNameFoods;
        DatabaseReference reference;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoods        = (ImageView) itemView.findViewById(R.id.idIMGadp_Home);
            txtNameFoods    = (TextView) itemView.findViewById(R.id.idTVadp_Home);
            reference = FirebaseDatabase.getInstance().getReference("Category");
        }
    }
}


