package com.javaclimb.shopping.common.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/*
所有service接口的基础接口
用到了JDK8的函数式接口注解，
 */

public interface BaseService<E,ID extends Serializable> {
    /*
    对应的市mapper对象
     */
    BaseMapper<E> getRepository();

    /*
    根据主键ID获取对象
     */
    default E get(ID id){
        return getRepository().selectById(id);
    }
    /**
     * 获取整个列表的所有记录
     *
     */
    default List<E> getAll(){
        return getRepository().selectList(null);
    }
    /**
     * 添加一条记录
     */
    default E insert(E entity){
        getRepository().insert(entity);
        return entity;
    }
    /**
     * 修改一条记录
     */
    default E update(E entity){
        getRepository().updateById(entity);
        return entity;
    }
    /**
     * 添加或更新
     *
     */
    default E insertOrUpdate(E entity){
        try {
            Object id = entity.getClass().getMethod("getId").invoke(entity);
            if(id == null){
                //没有id说明需要插入
                insert(entity);
            }else{
                //如果有id说明需要更高
                update(entity);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return entity;
    }
    /**
     * 根据主键ID删除一条记录
     */
    default int delete(ID id){
        return getRepository().deleteById(id);
    }
    /**
     * 批量删除
     */
    default void batchDelete(List<ID> ids){
        getRepository().deleteBatchIds(ids);
    }
    /**
     * 分页查询
     */
    default Page<E> findAll(Page<E> page){
        return (Page<E>) getRepository().selectPage(page,null);
    }
    /**
     * 获取查询器
     */
    QueryWrapper<E> getQueryWrapper(E e);
    /**
     * 获取带参数查询器
     */
    QueryWrapper<E> getQueryWrapper(Map<String,Object> condition);
    /**
     * 根据查询条件分页查询
     */
    default Page<E> findAll(Page<E>page,E e){
        QueryWrapper<E> queryWrapper = getQueryWrapper(e);
        return (Page<E>) getRepository().selectPage(page,queryWrapper);
    }
    /**
     * 根据查询条件分页查询
     */
    default Page<E> findAll(Page<E>page,Map<String,Object> condition){
        QueryWrapper<E> queryWrapper = getQueryWrapper(condition);
        return (Page<E>) getRepository().selectPage(page,queryWrapper);
    }
}
