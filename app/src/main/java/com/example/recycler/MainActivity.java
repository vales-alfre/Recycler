package com.example.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Adapter.Adaptador;
import Model.Producto;
import WebService.WebService;
import WebService.Asynchtask;
public class MainActivity extends AppCompatActivity implements Asynchtask {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService("https://dummyjson.com/products",
                datos, this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<Producto> lstProductos = new ArrayList<Producto>();
        try {
            JSONObject JSONlista = new JSONObject(result);
            JSONArray JSONlistaProducto = JSONlista.getJSONArray("products");
            lstProductos = Producto.JsonObjectsBuild(JSONlistaProducto);


            int resId = R.anim.uptodown;
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(),
                    resId);
            recyclerView.setLayoutAnimation(animation);
            Adaptador adapatorProdcuto = new Adaptador(this, lstProductos);
            recyclerView.setAdapter(adapatorProdcuto);
        } catch (JSONException e) {
            Toast.makeText(this.getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
        }
    }
}