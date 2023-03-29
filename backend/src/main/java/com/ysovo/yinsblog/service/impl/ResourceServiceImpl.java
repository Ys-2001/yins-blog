package com.ysovo.yinsblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysovo.yinsblog.dao.ResourceDao;
import com.ysovo.yinsblog.entity.Resource;
import com.ysovo.yinsblog.service.ResourceService;
import org.springframework.stereotype.Service;

/**
 * (Resource)表服务实现类
 *
 * @author ys
 * @since 2023-03-26 13:47:13
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceDao, Resource> implements ResourceService {

}

