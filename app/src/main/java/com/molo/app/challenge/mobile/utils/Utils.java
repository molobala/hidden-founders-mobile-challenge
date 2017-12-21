package com.molo.app.challenge.mobile.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.InputStream;

public class Utils {
    private static RequestQueue queue;
    public static String formatStarCount(int count){
        return count/1000+"k";
    }

    public static void httpInit(RequestQueue requestQueue) {
        queue=requestQueue;
    }
    public static void callAllRequest(Object tag){
        queue.cancelAll(tag);
    }
    public static  class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                in.close();
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
    public static interface HttpResultListener{
        public void onSuccess(String jsonString);
        public void onFail(String err);
    }
    public static abstract class  HttpResultAdapter implements HttpResultListener{
        @Override
        public void onSuccess(String jsonString) {

        }

        @Override
        public void onFail(String err) {

        }
    }
    public static StringRequest http(Context context,String url, final HttpResultListener listener){
        if(queue==null)
            queue = Volley.newRequestQueue(context);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (listener != null) listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(listener!=null) listener.onFail(error.getMessage());
            }
        });
        queue.add(stringRequest);
        return stringRequest;//for other operation by the caller (like cancel for instance)
    }
}
