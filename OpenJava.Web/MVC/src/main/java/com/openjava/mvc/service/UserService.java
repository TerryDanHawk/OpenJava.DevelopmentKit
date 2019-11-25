package com.openjava.mvc.service;

import com.openjava.mvc.mapper.UserMapper;
import com.openjava.mvc.model.UserModel;
import com.openjava.mvc.util.Base64Operator;
import com.openjava.mvc.util.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class UserService {

    private final UserMapper udao;

    @Autowired
    public UserService(UserMapper _udao) {

        this.udao = _udao;
    }

    public String Login(String username,String password) throws UnsupportedEncodingException {
        String result="";
        username=username.replace("'","");
        password= Base64Operator.Decode(password);
        password= Encryptor.generateMD5(password);
        UserModel user=this.udao.Login(username,password);
        if(user==null)
        {
            result="Invalid username or password!";
        }
        return  result;
    }


}
