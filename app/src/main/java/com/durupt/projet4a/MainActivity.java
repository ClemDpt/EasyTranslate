package com.durupt.projet4a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner langue = findViewById(R.id.inLangue);

        // Affichage langues

        AndroidNetworking.post("https://api-free.deepl.com/v2/languages")
                .addHeaders("Authorization","2419403")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener(){

                    @Override
                    public void onResponse(JSONObject response) {
                        switch(response.hashCode()){
                            case 200:
                                // TODO afficher langues -> "name" + "language"
                                break;
                            default :
                                Toast toastDft = Toast.makeText(getApplicationContext(),"Erreur", Toast.LENGTH_SHORT);
                                toastDft.show();
                                break;
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast toast = Toast.makeText(getApplicationContext(),"Erreur lors de la connexion", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
    }

    // TODO historique des traductions

    public void onClickTraduction(View v) {

        EditText aTraduire = findViewById(R.id.inText);
        Spinner langue = findViewById(R.id.inLangue);
        TextView Traduit = findViewById(R.id.outText);

        // Récupération langue
            // TODO

        // Traduction
        AndroidNetworking.post("https://api-free.deepl.com/v2/translate")
                .addHeaders("Authorization","2419403")
                .addBodyParameter("text", aTraduire.getText().toString())
                .addBodyParameter("target_lang", langue.toString()) // ex: "EN"
                .build()
                .getAsJSONObject(new JSONObjectRequestListener(){

                    @Override
                    public void onResponse(JSONObject response) {
                        switch(response.hashCode()){
                            case 200:
                                // TODO Récupérer traduction
                                //response.getText();
                                Traduit.setText("oui");
                                break;
                            default :
                                Toast toastDft = Toast.makeText(getApplicationContext(),"Erreur", Toast.LENGTH_SHORT);
                                toastDft.show();
                                break;
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast toast = Toast.makeText(getApplicationContext(),"Erreur lors de la connexion", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

    }
    public void onClickHistory(View v) {
        // TODO créer activité et transmettre historique des tradcutions

        //Intent onHistory = new Intent(
        //        getApplicationContext(),
        //        HisoryActivity.class );
        ////onConnect.putExtra("token", token);
        //startActivity(onHistory);
    }

    public void onClickParameters(View v) {
        // TODO créer activité

        //Intent onParameters = new Intent(
        //        getApplicationContext(),
        //        ParametersActivity.class );
        //startActivity(onParameters);


    }
}