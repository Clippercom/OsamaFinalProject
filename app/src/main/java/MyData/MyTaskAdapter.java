package MyData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.osamafinalproject.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyTaskAdapter extends ArrayAdapter<MyTask>
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

        MyTask item= getItem( position );

        ImageButton itmTaskDelete=v.findViewById( R.id.itmTaskDelete );
        ImageButton  itmTaskEdit=v.findViewById( R.id.itmTaskEdit );
        RatingBar rbNecessity =v.findViewById( R.id.itmRtNecessity );
        ImageView img =v.findViewById( R.id.img );
        TextView title =v.findViewById( R.id.itmTaskTitle );
        TextView subj=v.findViewById( R.id.itmTaskSubject );



        title.setText(item.getTitle());
        subj.setText(item.getSubject());
        rbNecessity.setRating(5*(item.getNecessity()/(float)10));

        if(item.getImage()==null || item.getImage().length()==0)
        {
            img.setImageResource(R.mipmap.ic_launcher);
        }
        else
        {
            //todo deal with image
        }
        //


        return v;

    }
}
