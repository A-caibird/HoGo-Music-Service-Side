package com.spring.controller;

import com.spring.websocket.UpgradeComboWs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class UpgradeComboCtr {
    private UpgradeComboWs ws;

    @Autowired
    public void setUpgradeComboWs(UpgradeComboWs ws) {
        this.ws = ws;
    }

    @PostMapping("/upgradeCombo")
    public ResponseEntity<?> upgradeCombo() {
        return new ResponseEntity<>("ok", HttpStatusCode.valueOf(200));
    }
}
