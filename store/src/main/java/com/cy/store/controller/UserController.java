package com.cy.store.controller;

import com.cy.store.controller.ex.FileEmptyException;
import com.cy.store.controller.ex.FileSizeException;
import com.cy.store.controller.ex.FileTypeException;
import com.cy.store.controller.ex.FileUploadException;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import com.cy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.impl.FileUploadIOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController // @Controller + @ResponseBody
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    //接受数据方式1: 请求数据方法的参数列表设置为pojo类型来接受前端的数据
    //SprintBoot会将前端的url地址中的参数名和pojo类的属性名进行比较
    //如果两者类相同那么会自动注入到pojo类的属性上
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        JsonResult<Void> result = new JsonResult<>();
        userService.reg(user);
        return new JsonResult<>(OK);
    }

    //接受数据方法2: 请求处理方法的参数列表设置为非pojo类型
    //SpringBoot会直接将请求的参数名和方法的参数名进行比较，如果名称相同则自动注入
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data = userService.login(username,password);
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<User>(OK, data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword,newPassword);
        return new JsonResult<>(OK);
    }
    //在个人资料页中直接展示现有的数据
    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        User data = userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(OK, data);
    }
    //在个人资料页中点击修改个人资料(电话,邮箱等等)
    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session){
        //user对象中有四部分数据:username, phone, email, gender
        // uid数据需要再次封装到user对象中
        userService.changeInfo(getuidFromSession(session), getUsernameFromSession(session), user);
        return new JsonResult<Void>(OK);
    }

    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    static{
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }
    /**
    @RequestParams表示前端中哪一个字符替换到后端？
     MultipartFile接口是springMVC提供的几口，这个接口为我们包装了
     获取文件类型的数据(任何类型的file都可以接受),SpringBoot它有整个了
     SpringMVC,只需要在处理请求的方法参数列表上声明一个参数类型为Multipart
     的参数, 然后SpringBoot自然将传递给服务的文件数据赋值给这个参数
    **/
     @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, @RequestParam("file") MultipartFile file) throws IOException {
        //前端文件数据都在file中
        if(file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        if(file.getSize() > AVATAR_MAX_SIZE){
            throw new FileSizeException("文件超出限制");
        }
        //判断文件类型是否使我们规定的后缀类型
         String contentType = file.getContentType();
        if(!AVATAR_TYPE.contains(contentType)){
            throw new FileTypeException("文件类型不支持");
        }
        //上传的文件放在(第一次找不到) .../upload/文件
         String parent = session.getServletContext().getRealPath("upload");
        //File对象指向这个路径，File是否存在,不存在就创建一个新的
         File dir = new File(parent);
         if(!dir.exists()){ //检测目录是否存在
             dir.mkdirs(); //创建当前目录
         }
         // 更改用户上传的文件名称，使用uuid生成新的字符串作为文件名(几个用户上传同一个名字的图片)
         //例: avatar.png
         String originalFileName = file.getOriginalFilename();
         System.out.println("OrginialFilename=" + originalFileName);
         int index = originalFileName.lastIndexOf(".");
         String suffix = originalFileName.substring(index);
         String filename = UUID.randomUUID().toString().toUpperCase() + suffix;

         File dest = new File(dir, filename); //在哪个目录存在哪个文件
        // 将参数file数据写入到这个空文件中
         file.transferTo(dest); //将file文件中数据写入到dest文件中

         Integer uid = getuidFromSession(session);
         String username = getUsernameFromSession(session);
         //返回头像路径 /upload/test.png
         String avatar = "/upload/" + filename;
         userService.changeAvatar(uid, avatar, username);
        //返回用户头像路径给前端页面，将来用于头像展示使用
         return new JsonResult<>(OK, avatar);
     }
}
