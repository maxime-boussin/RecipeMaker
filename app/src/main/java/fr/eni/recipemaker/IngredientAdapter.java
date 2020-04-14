package fr.eni.recipemaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fr.eni.recipemaker.models.Ingredient;

public class IngredientAdapter extends ArrayAdapter<Ingredient> {

    private int resId;

    public IngredientAdapter(@NonNull Context context, int resource, @NonNull List<Ingredient> objects) {
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
            myViewHolder.textView = convertView.findViewById(R.id.textView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (ViewHolder) convertView.getTag();
        }

        Ingredient item = getItem(position);

        myViewHolder.textView.setText(item.getText());

        return convertView;
    }

    private class ViewHolder {
        TextView textView;
    }
}
