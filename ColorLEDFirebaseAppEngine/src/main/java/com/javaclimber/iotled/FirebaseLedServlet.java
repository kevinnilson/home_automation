package com.javaclimber.iotled;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("serial")
public class FirebaseLedServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        String color = req.getParameter("color");
        String valueString = req.getParameter("value");

        if (color == null) {
            out.print("missing color");
            return;
        }

        if (!("red".equals(color) || "green".equals(color) || "blue".equals(color))) {
            out.print("invalid color: " + color);
            return;
        }

        if (valueString == null) {
            out.print("missing value");
            return;
        }


        if (!("on".equals(valueString) || "off".equals(valueString))) {
            out.print("invalid value: " + valueString);
            return;
        }

        boolean value = false;
        if ("on".equals(valueString))
            value = true;


        HttpURLConnection conn = (HttpURLConnection) new URL("https://ledcolor-c7c49.firebaseio.com/.json").openConnection();
        conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        String body = "{\"" + color + "\":" + value + "}";

        os.write(body.getBytes("UTF-8"));
        os.close();

        InputStream in = conn.getInputStream();
        int ch;
        StringBuilder sb = new StringBuilder();
        while ((ch = in.read()) != -1)
            sb.append((char) ch);

        out.print(sb.toString());

        in.close();
        conn.disconnect();

    }
}
