package com.example.socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et1,et2,et3;
    private Button btnEnviar;
    private String mensaje;
    private int port=0;
    private Socket cliente;
    private PrintWriter printWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText) findViewById(R.id.etIp);
        et2=(EditText) findViewById(R.id.etPuerto);
        et3=(EditText)findViewById(R.id.etMensaje);
        btnEnviar=(Button)findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {

        mensaje= et3.getText().toString();
        port=Integer.parseInt(et2.getText().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cliente= new Socket(et1.getText().toString(),port);
                    printWriter= new PrintWriter(cliente.getOutputStream());
                    printWriter.write(mensaje);
                    printWriter.flush();
                    printWriter.close();
                    cliente.close();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}