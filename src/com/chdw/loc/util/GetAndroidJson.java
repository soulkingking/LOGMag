package com.chdw.loc.util;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * Created by CaoBin on 2016/3/28.
 */
public class GetAndroidJson {

    public static String getAndroidJson(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        StringBuffer sb = new StringBuffer() ;
        InputStream is = null;
        try {
            is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            String str =sb.toString();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
