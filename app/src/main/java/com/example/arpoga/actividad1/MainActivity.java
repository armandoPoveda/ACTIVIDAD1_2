package com.example.arpoga.actividad1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
//importaciones que necesitamos para metodos o cualquier cosa que necesitemos

public class MainActivity extends AppCompatActivity {

    // creamos las variables de lo que vayamos a utilizar
    private EditText editText_Nombre;
    private Button button_Siguiente;
    private RadioButton radioButon_Mujer;
    private RadioButton radioButon_Hombre;
    private TextView mensaje_Activity2;

    // etiqueta para ir viendo los errores en el catlog
    private static final String TAG = "prueba activity1";

    //una variable para el código de resquestCode
    private final  int RESQUEST_EDAD = 0;

    //el metodo onCreate se crea por defecto
    //cuando una actividad se crea, se puede querer crear con información que tenía previamente.
    //Esta información se recibe en el objeto savedInstanceState
    // el setCOntentView solo se crea una vez y es para ver la vista del activity (una unica vista por actividad) y es inmutable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instanciamos los objetos de las variables que vamos a utilizar
        // con el id del xml
        editText_Nombre = (EditText) findViewById(R.id.editText_Nombre);
        button_Siguiente = (Button) findViewById(R.id.button_Siguiente);
        radioButon_Hombre = (RadioButton) findViewById(R.id.radioButton_Hombre);
        radioButon_Mujer = (RadioButton) findViewById(R.id.radioButton_Mujer);
        mensaje_Activity2 = (TextView) findViewById(R.id.textView_Activity2);

        // creamos el setonclicklistener para el boton haga una accion al darle
        button_Siguiente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //etiqueta para que salga en el logcat cuando clicamos en el boton
                Log.d(TAG, "Nombre" + editText_Nombre.getText());

                //creamos un objeto intent que moverá información entre Activitys
                Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                //putextra le pasamos dos parametros, primero un código y luego el view que se guardará en el intent
                i.putExtra("Nombre", editText_Nombre.getText());
                //lanzamos el activity2 con startActivityForResutl, con los datos del intent y el RequestCode
                startActivityForResult(i, RESQUEST_EDAD);
            }
        });

    }

    //Con el método onActivityResutl que pasa 3 parametros, el resquestCode (codigo), el resultCode del Activity2 y el intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // son setenabled ponemos todos los View en false para que no pueda ejecutarlos al volver del Activity2
        radioButon_Hombre.setEnabled(false);
        radioButon_Mujer.setEnabled(false);
        button_Siguiente.setEnabled(false);
        editText_Nombre.setEnabled(false);

        // con if comprobamos
       if (requestCode == RESQUEST_EDAD) // si el requestCode es igual al que hemos puesto haz...
            if (resultCode == RESULT_OK) { // si el resultCode es igual a OK, haz lo siguiente...
                String e = data.getStringExtra("EdadActivity"); //guardamos el String del segundo Activity2 con su codigo en una variable
                int numero = Integer.parseInt(e);// pasamos la variable String (e) a int para hacer las verificaciones
                // dependiendo de la edad que tenga, muestra un mensaje u otro en el TextView
                if (numero<=17){
                    mensaje_Activity2.setText(data.getCharSequenceExtra("EdadActivity") + " años eres menor de edad");
                }
                if (numero>=18 && numero<25) {
                    mensaje_Activity2.setText(data.getCharSequenceExtra("EdadActivity") + " años eres mayor de edad");
                }
                if (numero>25 && numero<40) {
                    mensaje_Activity2.setText(data.getCharSequenceExtra("EdadActivity") + " años estás en la flor de la vida");
                }
                if (numero>=40 && numero<=70) {
                    mensaje_Activity2.setText(data.getCharSequenceExtra("EdadActivity") + " años deberías pensar en jubilarte jajajaja");
                }
            }
            // si el setResult del Activity2 es result_Cancel, que nos muestre un TOAST con el mensaje que queramos
            else
                Toast.makeText(this, "EL subactivity se ha cancelado", Toast.LENGTH_LONG).show();
                          }
    }

