package fr.eni.recipemaker.ui.listing;

import fr.eni.recipemaker.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (ViewHolder) convertView.getTag();
        }

        Recipe item = getItem(position);

        myViewHolder.textViewTitle.setText(item.getLabel());

        return convertView;
    }

    class ViewHolder {
        TextView textViewTitle;
    }
}
