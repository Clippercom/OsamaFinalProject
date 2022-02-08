package MyData;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.osamafinalproject.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyTaskAdapter extends ArrayAdapter
{

    public MyTaskAdapter(@NonNull Context context, int resource) {
        super( context, resource );
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=View.inflate( getContext(), R.layout.task_item_layout,parent );
        return v;
    }
}
