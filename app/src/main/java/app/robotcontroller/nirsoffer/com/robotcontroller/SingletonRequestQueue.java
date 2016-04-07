package app.robotcontroller.nirsoffer.com.robotcontroller;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by nir on 3/13/2016.
 */
public class SingletonRequestQueue {
    private static RequestQueue _rq;
    private static SingletonRequestQueue _instance;
    private static Context _ctx;

    private SingletonRequestQueue(Context ctx) {
        _ctx=ctx;
        _rq = getRequestQueue();

    }
    public static synchronized SingletonRequestQueue getInstance(Context ctx) {
        if (_instance == null) _instance = new SingletonRequestQueue(ctx);
        return _instance;
    }

    public RequestQueue getRequestQueue() {
        if (_rq == null) {
            _rq = Volley.newRequestQueue(_ctx.getApplicationContext());
        }
        return _rq;
    }
}
