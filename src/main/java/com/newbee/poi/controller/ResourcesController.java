package com.newbee.poi.controller;

import com.newbee.poi.entity.Resources;
import com.newbee.poi.service.ResourcesService;
import com.newbee.poi.utils.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Major on 2016/11/22.
 */
@RestController
@RequestMapping("/resources")
public class ResourcesController extends BaseController<Resources> {

    @Autowired
    private ResourcesService resourcesService;

    @RequestMapping(value = "/query/{type}", method = RequestMethod.GET)
    public ResponseEntity<Resources> query(@PathVariable("type") Integer type) {
        List<Resources> resources = resourcesService.query(type, 0);
        return responseEntity.response(FLAG_OK, QUERY_OK, resources, null);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Resources> save(@RequestBody Resources resources) {
        resourcesService.save(resources);
        return responseEntity.response(FLAG_OK, SAVE_OK, null, HttpStatus.OK);
    }
}
