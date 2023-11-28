package upload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @projectName: git
 * @package: upload
 * @className: Upload
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/11/28 20:24
 * @version: 1.0
 */
@WebServlet("/uploadFile")
@MultipartConfig
public class Upload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       参数是上传文件标签的name属性的值
        Part uploadFile = req.getPart("uploadFile");
//        getSubmittedFileName获取文件名
        String fileName = uploadFile.getSubmittedFileName();
        System.out.println(uploadFile.getSubmittedFileName());

//        获取上传文件的大小
        System.out.println( uploadFile.getSize());
        String path = req.getServletContext().getRealPath("uploadFile");
//        D:\Java_GuoXinAn\git\out\artifacts\servlet_Web_exploded下面


        File file = new File(path);
        System.out.println(file.exists());

        if(!file.exists()){
            file.mkdir();
        }

        String finalPath = path+"\\"+ UUID.randomUUID() +fileName;

        uploadFile.write(finalPath);

        System.out.println("文件上传成功");
    }
}
