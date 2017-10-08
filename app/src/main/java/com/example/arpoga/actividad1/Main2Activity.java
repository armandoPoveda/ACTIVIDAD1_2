package com.example.arpoga.actividad1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
//importaciones que necesitamos para metodos o cualquier cosa que necesitemos

public class Main2Activity extends AppCompatActivity {

    // creamos las variables de lo que vayamos a utilizar
    private EditText editText_Edad;
    private Button button_Guardar;
    private TextView mensaje_Activity1;
    private Button button_Cancelar;

    // etiqueta para ir viendo los errores en el catlog
    private static final String TAG = "Etiqueta Activity2";

    //el metodo onCreate se crea por defecto
    //cuando una actividad se crea, se puede querer crear con información que tenía previamente.
    //Esta información se recibe en el objeto savedInstanceState
    // el setCOntentView solo se crea una vez y es para ver la vista del activity (una unica vista por actividad) y es inmutable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // instanciamos los objetos de las variables que vamos a utilizar
        // con el id del xml
        editText_Edad = (EditText) findViewById(R.id.editText_Edad);
        button_Guardar = (Button) findViewById(R.id.button_Atras);
        mensaje_Activity1 = (TextView) findViewById(R.id.textView_Activity1);
        button_Cancelar = (Button) findViewById(R.id.button);

        //La llamada getIntent() captura la intent que inició la actividad
         final Intent i = getIntent();

        // creamos un objeto bundle para recoger los datos del intent con getExtras()
        //con el if hacemos la comprobacion que no sea null
        //creamos un String y con getCharSequence hacemos que los datos sean String
        // y lo mostramos en el TextView
        final Bundle parametrosRecibidos = i.getExtras();
        if (parametrosRecibidos != null) {
            String elNombre = parametrosRecibidos.getCharSequence("Nombre").toString();
            mensaje_Activity1.setText("Hola " + elNombre + " necesito más información");
        }

        // creamos el setonclicklistener para el boton haga una accion al darle
        button_Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //etiqueta para que salga en el logcat cuando cliclamos en el boton
                Log.d(TAG, "Edad" + editText_Edad.getText());

                //putextra le pasamos dos parametros, primero un código y luego el view que se guardrá en el intent
                i.putExtra("EdadActivity", editText_Edad.getText().toString());

                // setresutl() permite devolver el Intent con toda la información que se necesite, si el resultado es OK
                setResult(RESULT_OK,i);

                //finalizamos la aplicacion
                finish();
            }
        });

        // creamos el setonclicklistener para el boton haga una accion al darle
        button_Cancelar.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                //etiqueta para que salga en el logcat cuando cliclamos en el boton
                Log.d(TAG, "Edad" + editText_Edad.getText());

                // setresutl() permite devolver el Intent con toda la información que se necesite, si el resultado es Cancelado
                setResult(RESULT_CANCELED);

                //finalizamos la aplicacion
                finish();
            }
        });
    }
}

