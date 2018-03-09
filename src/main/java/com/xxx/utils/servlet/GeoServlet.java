package com.xxx.utils.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxx.misc.json.ResponseJsonUtils;

@WebServlet("/utils/geo")
public class GeoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5829187605425893648L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	String func = request.getParameter("func");

    	Object obj = "{}";
    	if (func != null) {
	    	switch(func) {
	    	case "getAddress":
	    		obj = getAddress(request, response);
	    		break;
	    	case "getLatitude":
	    		obj = getLatitude(request, response);
	    		break;
	    	}
    	}
		
	    ResponseJsonUtils.json(response, obj);
    }
	
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    private Object getAddress(HttpServletRequest request, HttpServletResponse response)
    {
    	Object ret = null;
    	
    	String latitude = null;
    	String longitude = null;
    	
    	latitude = "31.2325992433";
    	longitude = "121.5045631813";
    	
    	String location = request.getParameter("location");
    	
    	if (location != null) {
    		String strs[] = location.split("\\,");
    		latitude = strs[0];
    		longitude = strs[1];
    	}
    	
		// http://api.map.baidu.com/geocoder/v2/?ak=GXCGGwxBhmKoy7lNguj6DDAT&location=31.2325992433,121.5045631813&output=json
		String url = "http://api.map.baidu.com/geocoder/v2/?ak=GXCGGwxBhmKoy7lNguj6DDAT&location=" + latitude + ","
				+ longitude + "&output=json";
    			
    	ret = readUrl(url);
    	
    	return ret;
    }
    
    private Object getLatitude(HttpServletRequest request, HttpServletResponse response)
    {
    	Object ret = null;
    	
    	String address = request.getParameter("address");
    	if (address != null) {
    		address = "上海市";
    	}
    	
		String url = "http://api.map.baidu.com/geocoder/v2/?ak=GXCGGwxBhmKoy7lNguj6DDAT&address=" + address + "&output=json";
    			
    	ret = readUrl(url);
    	
    	return ret;
    }
	
    public String readUrl(String url)
    {
    	BufferedReader in = null;
        URL tirc;
        
        try {
			tirc = new URL(url);

			in = new BufferedReader(new InputStreamReader(tirc.openStream(), "UTF-8"));
			String res;
			StringBuilder sb = new StringBuilder("");

			while ((res = in.readLine()) != null) {
				sb.append(res.trim());
			}
			String str = sb.toString();
			System.out.println("url request:" + url);
			System.out.println("url respone:" + str);

			return str;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return "";
    }
}