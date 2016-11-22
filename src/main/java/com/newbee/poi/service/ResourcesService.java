package com.newbee.poi.service;

import com.newbee.poi.dao.ResourcesMapper;
import com.newbee.poi.entity.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Major on 2016/11/22.
 */
@Service
public class ResourcesService {
    @Autowired
    private ResourcesMapper resourcesMapper;

    /**
     * 根据类型查询资源集合，type：0表示目录，1表示菜单，2表示按钮
     *
     * @param type
     * @return
     */
    public List<Resources> query(Integer type, Integer parentId) {

        ArrayList<Resources> resources = new ArrayList<>();
        recursion(0, type, resources);
        return resources;
    }

    /**
     * 遍历菜单
     *
     * @param parentId
     * @param type
     * @param resourcess
     */
    public void recursion(Integer parentId, int type, ArrayList<Resources> resourcess) {
        HashSet<Resources> list = resourcesMapper.queryByType(type, parentId);
        for (Resources resources : list) {
            Integer parent = resources.getId();
            HashSet<Resources> childs = resourcesMapper.queryByType(type, parent);
            if (childs.size() > 0) {
                resources.setChilds(childs);
                resourcess.add(resources);
                recursion(type, parent, resourcess);
            }

        }
    }

    /**
     * 删除资源
     *
     * @param id
     */
    public void delete(Integer id) {
        resourcesMapper.delete(id);
    }

    /**
     * 保存
     *
     * @param resources
     */
    public void save(Resources resources) {
        resourcesMapper.save(resources);
    }

    /**
     * 修改
     *
     * @param resources
     */
    public void update(Resources resources) {
        resourcesMapper.update(resources);
    }
}
