package MyData;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.osamafinalproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

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
        ImageView btnDel=v.findViewById(R.id.itmBtnDel);
        ImageView btngo=v.findViewById(R.id.itmBtnGo );
        ImageView btnEdit=v.findViewById(R.id.itmBtnEdit);
        ImageView img=v.findViewById(R.id.itmImg);

        if(item.getImage()!=null && item.getImage().length()>0 )
            downloadImageToLocalFile(item.getImage(),img);   //connect item view to data source

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
    private void downloadImageToLocalFile(String fileURL, final ImageView toView) {
        StorageReference httpsReference = FirebaseStorage.getInstance().getReferenceFromUrl(fileURL);
        final File localFile;
        try {
            localFile = File.createTempFile("images", "jpg");


            httpsReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    // Local temp file has been created
                    Toast.makeText(getContext(), "downloaded Image To Local File", Toast.LENGTH_SHORT).show();
                    toView.setImageURI( Uri.fromFile(localFile));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                    Toast.makeText(getContext(), "onFailure downloaded Image To Local File "+exception.getMessage(), Toast.LENGTH_SHORT).show();
                    exception.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
