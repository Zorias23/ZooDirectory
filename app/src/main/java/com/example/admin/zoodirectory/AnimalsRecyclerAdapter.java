package com.example.admin.zoodirectory;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/14/2017.
 */

public class AnimalsRecyclerAdapter extends RecyclerView.Adapter<AnimalsRecyclerAdapter.ViewHolder>{

    public static ArrayList<Animal> animalsList = new ArrayList<>();

    public List<Animal> getAnimalsList() {
        return animalsList;
    }



    public static final String TAG = "AnimalRecycler";

    @Override
    public AnimalsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animals_recycler_item, null);
        return new ViewHolder(view);
    }
    public AnimalsRecyclerAdapter(ArrayList<Animal> animalsList) {
        this.animalsList = animalsList;
}
    public  AnimalsRecyclerAdapter outer()
    {
        return AnimalsRecyclerAdapter.this;
    }
    public AnimalsRecyclerAdapter()
    {

    }

    @Override
    public void onBindViewHolder(AnimalsRecyclerAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        Animal a  = animalsList.get(position);
        if (a != null)
        {
            holder.tvZooName.setText(a.getName());
        }

    }

    @Override
    public int getItemCount() {
        return animalsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView tvZooName;
        AnimalsRecyclerAdapter a = new AnimalsRecyclerAdapter();


        public ViewHolder(View itemView) {
            super(itemView);
            tvZooName = itemView.findViewById(R.id.tvZooName);
            tvZooName.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ArrayList<Animal> animals = AnimalsRecyclerAdapter.animalsList;
            Log.d("AnimalsRecyclerAdapter", "onClick: you clicked position " + getPosition());
            Log.d(TAG, "onClick: this results in value from list: " + animals.get(getPosition()).getName());
            Intent intent = new Intent(view.getContext(), ViewAnimalActivity.class);
            intent.putExtra("animal", animals.get(getPosition()).getName());
            view.getContext().startActivity(intent);
        }
    }
}
