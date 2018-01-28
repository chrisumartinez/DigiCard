package xyz.digicard.app.android;

import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import xyz.digicard.app.android.models.User;

/**
 * Created by Chris on 1/27/2018.
 */

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {

    private List<User> data;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView firstName, lastName, email;
        private ImageView imgView;
        public ViewHolder(View v){
            super(v);
            this.firstName = (TextView)v.findViewById(R.id.contact_first_name);
            this.lastName = (TextView)v.findViewById(R.id.contact_last_name);
            this.imgView = (ImageView)v.findViewById(R.id.profile_image);
            this.email = (TextView)v.findViewById(R.id.email);
        }
        public void bind(User u){
            firstName.setText(u.getFirstName());
            lastName.setText(u.getLastName());
            imgView.setImageDrawable(ResourcesCompat.getDrawable(imgView.getContext().getResources(), R.drawable.user_photo_fallback, imgView.getContext().getTheme()));
            email.setText(u.getEmail());
        }
    }
    public NewAdapter(List<User> dataset){
        data = dataset;
    }
    @Override
    public NewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_profile_contact_container, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.bind(data.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.size();
    }
}


