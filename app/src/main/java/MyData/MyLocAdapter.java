package MyData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.osamafinalproject.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



public class MyLocAdapter extends ArrayAdapter<MyLoc>
{

    public MyLocAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    /**
     * ملائمة المعطى طريقة عرضه
     * تقوم باخذ المعطى من قاعدة البيانات وبناءؤ واجهة وعرض هذه البيانات على الواجهة
     * وتثوم بارجاع الواجهة لكل معطى
     * @param position  رقم المعطى
     * @param convertView
     * @param parent
     * @return  تعيد واجهة عرض لمطى واحد حسب رقمه
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //View v=View.inflate(getContext(), R.layout.task_item_layout,parent);
        //بناء واجهة لمعطى واحد
        View v= LayoutInflater.from(getContext()).inflate( R.layout.loc_item_layout,parent,false);
        // استخراج المعطر حسب رقمه
        MyLoc item = getItem(position);

        //تجهيز مؤشر لكل كائن على الواجهة
        TextView title=v.findViewById(R.id.itmLocTitle );
        TextView subj=v.findViewById(R.id.itmLocSubject );
        ImageButton btnDel=v.findViewById(R.id.itmBtnDel);
        ImageButton btngo=v.findViewById(R.id.itmBtnGo );
        ImageButton btnEdit=v.findViewById(R.id.itmBtnEdit);
        ImageView img=v.findViewById(R.id.itmImg);

        //وضع قيم المعطى المستخرج على كائنات الواجهة
        title.setText(item.getTitle());
        subj.setText(item.getSubject());

        if(item.getImage()==null || item.getImage().length()==0)
        {
            img.setImageResource(R.drawable.ic_launcher_foreground);
        }
        else
        {
            //todo deal with image
        }
        //


        return v;
    }
}
