package com.spring.controller;

import com.spring.dao.Combo;
import com.spring.diyAnnotation.interfaces.AutowiredField;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
//@AutowiredField
public class GetComboInfoCtr {
    private Combo combo;

    @Autowired
    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    @GetMapping("/getComboInfo")
    public ResponseEntity<?> getComboInfo() {
        return new ResponseEntity<>(combo.getComboInfo(), HttpStatusCode.valueOf(200));
    }
}
