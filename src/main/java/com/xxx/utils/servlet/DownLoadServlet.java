package com.xxx.utils.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxx.misc.json.ResponseJsonUtils;

@WebServlet("/utils/DownLoad")
public class DownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String requestEncoding = request.getCharacterEncoding();
			String responseEncoding = response.getCharacterEncoding();
			if (requestEncoding == null) {
				requestEncoding = "UTF-8";
			}
			requestEncoding = "ISO8859-1";
			String fileName = request.getParameter("filename");
			// ISO8859-1 ? UTF-8
			fileName = new String(fileName.getBytes(requestEncoding), "UTF-8");
			String fileSaveRootPath = this.getServletContext().getRealPath("/WEB-INF");
			String path = fileSaveRootPath + "/upload";
			path = fileSaveRootPath + "/result";
			String filepath = path + "/" + fileName;
			File file = new File(filepath);
			System.out.println(filepath);
			if (!file.exists()) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("message", "资源不存在");
				ResponseJsonUtils.json(response, data);
				return;
			}
			String realname = fileName.substring(fileName.indexOf("_") + 1);
			realname = fileName;
			response.setCharacterEncoding("UTF-8");
			response.setContentType("");
			//response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
			response.setHeader( "Content-Disposition", "attachment;filename=" + new String( realname.getBytes("UTF-8"), responseEncoding)); 
			FileInputStream in = new FileInputStream(path + "/" + fileName);
			OutputStream out = response.getOutputStream();
			byte buffer[] = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			in.close();
			out.close();
		} catch (Exception e) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("error", e.toString());
			ResponseJsonUtils.json(response, data);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}