package com.demo.springboot_quickstart.dao;

import com.demo.springboot_quickstart.entity.SystemCache;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by luyan on 2017/4/16.
 */
public interface SystemCacheDao extends CrudRepository<SystemCache,String>{

	public List<SystemCache> findAll();

}
