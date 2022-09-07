package com.example.springcloud.service.impl;

import com.example.springcloud.dao.storageDao;
import com.example.springcloud.service.StorageService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    storageDao storageDao;
    @Override
    public Integer reduce(Long produceId, Integer count) {
      return   storageDao.reduceStorageByProduceidAnd_count(produceId,count);
    }
}
