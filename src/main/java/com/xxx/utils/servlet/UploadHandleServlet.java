package com.xxx.utils.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.xxx.misc.json.ResponseJsonUtils;

@WebServlet("/utils/Upload")
public class UploadHandleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
		File tmpFile = new File(tempPath);
		if (!tmpFile.exists()) {
			tmpFile.mkdir();
		}
		File saveFile = new File(savePath);
		if (!saveFile.exists()) {
			saveFile.mkdir();
		}

		String message = "";
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024 * 100);
			factory.setRepository(tmpFile);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setProgressListener(new ProgressListener() {
				public void update(long pBytesRead, long pContentLength, int arg2) {
					System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
				}
			});
			upload.setHeaderEncoding("UTF-8");
			if (!ServletFileUpload.isMultipartContent(request)) {
				return;
			}

			upload.setFileSizeMax(1024 * 1024 * 10);
			upload.setSizeMax(1024 * 1024 * 10 * 10);
			@SuppressWarnings("unchecked")
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					// value = new String(value.getBytes("iso8859-1"),"UTF-8");
					System.out.println(name + "=" + value);
				} else {
					String filename = item.getName();
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					System.out.println(filename);
					filename = filename.substring(filename.lastIndexOf("/") + 1);
					InputStream in = item.getInputStream();
					String saveFilename = filename;
					FileOutputStream out = new FileOutputStream(savePath + "/" + saveFilename);
					byte buffer[] = new byte[1024];
					int len = 0;
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					item.delete();
					message = "success";
				}
			}
		} catch (FileUploadBase.FileSizeLimitExceededException e) {
			e.printStackTrace();
			message = e.toString();
		} catch (FileUploadBase.SizeLimitExceededException e) {
			e.printStackTrace();
			message = e.toString();
			return;
		} catch (Exception e) {
			e.printStackTrace();
			message = e.toString();
		}

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("msg", message);
		ResponseJsonUtils.json(response, data);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}