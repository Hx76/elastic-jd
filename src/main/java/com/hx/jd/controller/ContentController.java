package com.hx.jd.controller;

import com.hx.jd.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class ContentController {
    @Resource
    private ContentService service;

    @GetMapping("/parse/{key}")
    public boolean parse(@PathVariable("key") String key) throws IOException {
        return service.parseContent(key);
    }

    @GetMapping("/search/{key}/{currentPage}/{pageSize}")
    public List<Map<String,Object>> search(@PathVariable("key") String key,@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize) throws IOException {
        return service.searchPage(key,currentPage,pageSize);
    }

}
