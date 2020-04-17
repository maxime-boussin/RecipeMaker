package fr.eni.recipemaker.ui.listing;

import fr.eni.recipemaker.R;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.eni.recipemaker.models.Recipe;

public class RecipeAdapter extends ArrayAdapter<Recipe> {
    private int resId;

    public RecipeAdapter(@NonNull Context context, int resource, @NonNull List<Recipe> objects) {
        super(context, resource, objects);

        resId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder myViewHolder;

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resId, null);

            myViewHolder = new ViewHolder();
            myViewHolder.textViewTitle = convertView.findViewById(R.id.textViewTitle);
            myViewHolder.textViewCalories = convertView.findViewById(R.id.textViewCalories);
            myViewHolder.imageView = convertView.findViewById(R.id.itemImage);


            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (ViewHolder) convertView.getTag();
        }

        Recipe item = getItem(position);

            Picasso.get().load(item.getImage()).into(myViewHolder.imageView);
            myViewHolder.textViewTitle.setText(item.getLabel());
            myViewHolder.textViewCalories.setText(String.valueOf(Math.round(item.getCalories()))+"\ncal");


        return convertView;
    }

    class ViewHolder {
        TextView textViewTitle;
        TextView textViewCalories;
        ImageView imageView;
    }
}
