package com.msnit.localcafe.server;

import android.content.Context;

import com.msnit.localcafe.cafe.CafeManagement;
import com.msnit.localcafe.cafe.CafeRequest;
import com.msnit.localcafe.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

public class HostServer extends NanoHTTPD {
    private static HostServer webServer;
    private static Context context;
    InputStream data;

    private HostServer() throws IOException {
        super(8080);
    }

    public static void startServer(Context c) {
        context = c;
        try {
            webServer = new HostServer();
            webServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stopServer() {
        webServer.stop();
    }

    public static boolean isOn() {
        if (webServer != null)
            return webServer.isAlive();
        return false;
    }

    public static String getIPAddress() {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        assert sAddr != null;
                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if (isIPv4)
                            return sAddr;
                    }
                }
            }
        } catch (Exception ignored) {
        } // for now eat exceptions
        return " NONE ";
    }

    public static Map<String, String> splitQuery(String query) {
        Map<String, String> query_pairs = new LinkedHashMap<>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(pair.substring(0, idx), pair.substring(idx + 1));
        }
        return query_pairs;
    }

    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        String filePath;
        if (uri.equals("/")) {
            filePath = "index.html";
        } else {
            filePath = uri.substring(1);
        }

        if (session.getMethod().equals(Method.POST)) {
            Map<String, String> data = new HashMap<>();

            try {
                session.parseBody(data);
            } catch (IOException | ResponseException e) {

                System.out.println("ERorrrrrrrrrrrrrrrrrrrrrr");
                e.printStackTrace();
            }
            Map<String, String> query = splitQuery(session.getQueryParameterString());


            int table_id = Utils.getInt(query.get("table_id"));
            int coffeeNum = Utils.getInt(query.get("coffee"));
            int nescafe = Utils.getInt(query.get("nescafe"));
            int capo = Utils.getInt(query.get("capochino"));
            int choco = Utils.getInt(query.get("choco"));
            String note = query.get("note");
            try {
                note = URLDecoder.decode(note, StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException e) {
                note = "";
            }
            CafeManagement.add(new CafeRequest(table_id, coffeeNum, nescafe, capo, choco, note));
            CafeManagement.updateCafe();

        }
        try {
            data = context.getAssets().open(filePath);

            if (filePath.endsWith(".css"))
                return newChunkedResponse(Response.Status.OK, "text/css", data);
            else if (filePath.endsWith(".js"))
                return newChunkedResponse(Response.Status.OK, "text/javascript", data);
            else if (filePath.endsWith(".jpg"))
                return newChunkedResponse(Response.Status.OK, "image/jpeg", data);
            else if (filePath.endsWith(".png"))
                return newChunkedResponse(Response.Status.OK, "image/png", data);
            else if (filePath.endsWith(".ttf"))
                return newChunkedResponse(Response.Status.OK, "font/ttf", data);
            return newChunkedResponse(Response.Status.OK, "text/html", data);
        } catch (
                IOException e) {
            return newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", "404: File not found");
        }
    }
}
