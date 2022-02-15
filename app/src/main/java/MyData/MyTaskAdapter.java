package MyData;

import android.content.Context;
import android.view.LayoutInflater;
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
        //View v=View.inflate( getContext(), R.layout.task_item_layout,parent );
        //بناء واجهة لمعطى واحد
        View v= LayoutInflater.from(getContext()).inflate(R.layout.task_item_layout,parent,false);

        return v;
        MyTask item= getItem( position );

    }
}
