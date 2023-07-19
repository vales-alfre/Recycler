package Model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Producto {
  
    private String title;
    private String price;
    private String stock;
    private String category;
    private String urlavatar;
    private List<String> imagen;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrlavatar() {
        return urlavatar;
    }

    public void setUrlavatar(String urlavatar) {
        this.urlavatar = urlavatar;
    }

    public List<String> getImagen() {
        return imagen;
    }

    public void setImagen(List<String> imagen) {
        this.imagen = imagen;
    }

    public Producto(JSONObject a) throws JSONException {

        title = a.getString("title").toString();
        price = a.getString("price").toString() ;
        stock = a.getString("stock").toString() ;
        category = a.getString("category").toString() ;
        urlavatar = a.getString("thumbnail").toString() ;
        JSONArray ImgArray = a.optJSONArray("images");
        imagen = new ArrayList<>();
        if (ImgArray != null) {
            int len = ImgArray.length();
            for (int i = 0; i < len; i++) {
                String imageUrl = ImgArray.optString(i);
                if (!imageUrl.isEmpty()) {
                    imagen.add(imageUrl);

                }
            }
        }

    }
    public static ArrayList<Producto> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Producto> productos = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            productos.add(new Producto(datos.getJSONObject(i)));
        }
        return productos;
    }
}

