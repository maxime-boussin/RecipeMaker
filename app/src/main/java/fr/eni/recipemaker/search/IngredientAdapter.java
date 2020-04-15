package fr.eni.recipemaker.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fr.eni.recipemaker.R;
import fr.eni.recipemaker.models.Ingredient;


public class IngredientAdapter extends ArrayAdapter<Ingredient> {

    private int ingredientId;
    private ListView listIngredients;
    private List<Ingredient> ingredients;

    public IngredientAdapter(@NonNull Context context, int resource, @NonNull List<Ingredient> objects) {
        super(context, resource, objects);
        ingredientId = resource;
        ingredients = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(ingredientId, null);
            viewHolder = new ViewHolder();
            viewHolder.ingredientName = convertView.findViewById(R.id.ingredientName);
            viewHolder.buttonSuppr = convertView.findViewById(R.id.buttonSuppr);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Ingredient ingredient = getItem(position);
        viewHolder.ingredientName.setText(ingredient.getText());

        viewHolder.buttonSuppr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredients.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;


    }


    class ViewHolder {
        /**
         * indispensable ??
         */
        TextView ingredientName;
        ImageButton buttonSuppr;
    }
}
