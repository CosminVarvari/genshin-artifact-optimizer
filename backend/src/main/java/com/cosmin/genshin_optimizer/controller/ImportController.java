package com.cosmin.genshin_optimizer.controller;

import com.cosmin.genshin_optimizer.service.EnkaImportService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/import")
public class ImportController {
    
    @Autowired
    private EnkaImportService enkaImportService;

    @GetMapping("/{uid}")
    public JsonNode importFromEnka(@PathVariable String uid) {
        try {
            return enkaImportService.fetchCharacterData(uid);
        } catch (Exception e) {
             e.printStackTrace();
            return null;
        }
    }
}
