package com.xn.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xn.entity.Sight;

public interface SightDAO {

	public List<Sight> GetAllSightList();

	public Sight findSbyName(@Param("s_name") String s_name, @Param("s_imgurl") String s_imgurl,
			@Param("s_info") String s_info);

	public Sight getSightById(@Param("s_id") String s_id);

	public List<Sight> getSightBySName(@Param("s_name") String s_name);

	public void deleteSight(@Param("s_id") String s_id);

	public void addSight(@Param("s_name") String s_name, @Param("s_imgurl") String s_imgurl,
			@Param("s_info") String s_info);

	public void updateSight(@Param("s_id") String s_id, @Param("s_name") String s_name,
			@Param("s_imgurl") String s_imgurl, @Param("s_info") String s_info);

	// 模糊查询景点By景点名
	public List<Sight> SelectSight(@Param("str") String str);

	public List<Sight> getAllSight();

	@SuppressWarnings("rawtypes")
	public List getTidSname();

	List<Sight> findByPage(HashMap<String, Object> map);

	int selectCount();
}
