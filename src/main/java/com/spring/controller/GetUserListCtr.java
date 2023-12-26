package com.spring.controller;
import com.spring.dao.Users;
import com.spring.domain.SqlTable.UserInfo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class GetUserListCtr {
    private Users users;
    @Autowired
    public void setUsers(Users users){
        this.users = users;
    }

    @GetMapping("/userList")
    public List<UserInfo> getUserList() {
        List<UserInfo> userlist = users.getUserList();
        System.out.println(userlist);
        return userlist;
    }
}
