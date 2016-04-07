package app.robotcontroller.nirsoffer.com.robotcontroller;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nir on 3/27/2016.
 */
public class ParticleIORequestor {


    protected static final String TAG = "TAG";
    private static RequestQueue rq;
    private static final String urlbase = "https://api.spark.io/v1/devices/";
    private static String _url;
    private static String _token;


    private static Response.Listener resplisten;
    private static Response.ErrorListener resperrlisten;

    public ParticleIORequestor(Context ctx, Response.Listener rl, Response.ErrorListener rel, String device, String token, String functionname) {
        SingletonRequestQueue srq;
        srq=SingletonRequestQueue.getInstance(ctx);
        resplisten = rl;
        resperrlisten = rel;
        rq=srq.getRequestQueue();
        _url = urlbase+device+"/"+functionname;
        _token = token;

    }

    public void turn(final int num) {
        // this is where we start adding requests to the queue

        // cancel all requests pending - as we are only interested in the last event

        rq.cancelAll(TAG);
        StringRequest sr = new StringRequest(Request.Method.POST, _url, resplisten, resperrlisten) {

            // I tried playing with JsonRequest-  which is a problem since I _think_ (unconfirmed since I'm lazy) that it puts the parameters of the request as a json doc and not as post parameters.
            // I might be wrong.
        //JsonObjectRequest or = new JsonObjectRequest(Request.Method.POST, url, null, resplisten, resperrlisten) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("arg", ""+num);
                System.out.println("in turn arg is "+params.get("arg"));
                return params;

            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
               params.put("Authorization:", "Bearer "+_token);


                return params;
            }
        };
        sr.setTag(TAG);
        rq.add(sr);



    }


}
