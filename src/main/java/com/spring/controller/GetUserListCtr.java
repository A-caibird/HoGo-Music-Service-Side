package com.spring.controller;
import com.spring.dao.GetUerList;
import com.spring.domain.SqlTable.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@CrossOrigin(origins = "*")
public class GetUserListCtr {
    private GetUerList getUerList;

    @Autowired
    public void setGetUerList(GetUerList getUerList) {
        this.getUerList = getUerList;
    }

    @GetMapping("/userList")
    public List<UserInfo> getUserList() {
        List<UserInfo> userlist = getUerList.getUserList();
        System.out.println(userlist);
        return userlist;
    }
}
