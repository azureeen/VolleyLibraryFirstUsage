package pleurtuit.trochon.volleyframework;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements Response.Listener<Bitmap>{

    private ImageView viewer1, viewer2, viewer3;
    private ProgressDialog progress;
    private static Integer counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.viewer1 = findViewById(R.id.imageView);
        this.viewer2 = findViewById(R.id.imageView2);
        this.viewer3 = findViewById(R.id.imageView3);
    }

    public void onStart() {
        super.onStart();

        this.progress = new ProgressDialog(this);
        this.progress.setTitle("Veuillez patientez");
        this.progress.setMessage("Récupération de l'image en cours...");
        this.progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        this.progress.show();

        getImagesFromInternet();
    }

    public void getImagesFromInternet(){
        // Instancie la file de message (cet objet doit être un singleton)
        RequestQueue queue = Volley.newRequestQueue(this);
        // Requête d'une image à l'URL demandée
        ImageRequest picRequest = new ImageRequest("https://images.pexels.com/photos/8474434/pexels-photo-8474434.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", this, 0, 0, null, null);
        ImageRequest picRequest2 = new ImageRequest("https://images.pexels.com/photos/8474983/pexels-photo-8474983.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", this, 0, 0, null, null);
        ImageRequest picRequest3 = new ImageRequest("https://images.pexels.com/photos/8474987/pexels-photo-8474987.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2", this, 0, 0, null, null);

        // Insère la requête dans la file
        queue.add(picRequest);
        queue.add(picRequest2);
        queue.add(picRequest3);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onResponse(Bitmap response) {
        // Fermeture de la boite de dialogue
        if(this.progress.isShowing()) this.progress.dismiss();
        // Affectation de l'image dans l'imageview
        if (counter == 0) this.viewer1.setImageBitmap(response);
        if (counter == 1) this.viewer2.setImageBitmap(response);
        if (counter == 2) this.viewer3.setImageBitmap(response);

        counter++;
    }
}