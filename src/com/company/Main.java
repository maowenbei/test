package com.company;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        String resources = ";";
        String[] resourceItems = resources.split(";");
        System.out.println(resourceItems.length);
        for (String item : resourceItems) {
            String[] split = item.split(":");
            if (split.length != 2) {
                System.out.println("Resources string format error");
            }
            System.out.println(split[0] + Integer.parseInt(split[1]));
        }
    }

    public void testPhoto() throws Exception {
        String photo = "http://cloud013x.corp.youdao.com:9989/ygs/api/image/normal/1457495927051?userId=group16449215";
        System.out.println(photo);
        URL url = new URL(photo);
        URLConnection conn = (HttpURLConnection) url.openConnection();
        BufferedInputStream in = new BufferedInputStream(
                conn.getInputStream());
        StringBuffer sb = new StringBuffer();
        try {
            String inputLine;
            byte[] buf = new byte[4096];
            int bytesRead = 0;
            while (bytesRead >= 0) {
                inputLine = new String(buf, 0, bytesRead, "ISO-8859-1");
                sb.append(inputLine);
                bytesRead = in.read(buf);
            }
            buf = null;
        } finally {
            in.close();
            conn = null;
            url = null;
        }
        byte[] result = computeMD5Hash(sb.toString().getBytes());

        System.out.println(Arrays.toString(result));
    }

    public static byte[] computeMD5Hash(InputStream is) throws NoSuchAlgorithmException, IOException {
        BufferedInputStream bis = new BufferedInputStream(is);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[16384];
            int bytesRead = -1;
            while ((bytesRead = bis.read(buffer, 0, buffer.length)) != -1) {
                messageDigest.update(buffer, 0, bytesRead);
            }
            return messageDigest.digest();
        } finally {
            try {
                bis.close();
            } catch (Exception e) {
                System.err.println("Unable to close input stream of hash candidate: " + e);
            }
        }
    }

    public static byte[] computeMD5Hash(byte[] data) throws NoSuchAlgorithmException, IOException {
        return computeMD5Hash(new ByteArrayInputStream(data));
    }
}
