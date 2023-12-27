package com.spring.controller;

import com.spring.dao.Combo;
import com.spring.domain.SqlTable.ComboTable;
import com.spring.websocket.BroadcastComboInfo;
import jakarta.websocket.EncodeException;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.http.WebSocket;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@Slf4j
public class UpgradeComboCtr {
    private BroadcastComboInfo ws;
    private Combo combo;

    @Autowired
    public void setWs(BroadcastComboInfo ws) {
        this.ws = ws;
    }

    @Autowired
    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    @PostMapping("/upgradeCombo")
    public ResponseEntity<?> upgradeCombo(@RequestBody List<ComboTable> comboList) throws EncodeException, IOException {
        try {
            for (ComboTable item : comboList) {
                combo.upgradeItem(item.getName(), item.getPrice_now(), item.getPrice_origin());
            }
        } catch (Exception e) {
            log.error("更新套餐信息失败,数据库出错");
            return new ResponseEntity<>("更新套餐信息失败,数据库出错", HttpStatusCode.valueOf(500));
        }
        try {
            ws.sendCombo(1);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Websocket Session already Expired", HttpStatusCode.valueOf(401));
        }
        return new ResponseEntity<>("ok", HttpStatusCode.valueOf(200));
    }
}
