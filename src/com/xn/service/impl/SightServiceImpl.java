package com.xn.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xn.dao.SightDAO;
import com.xn.entity.PageBean;
import com.xn.entity.Sight;
import com.xn.service.SightService;

@Service
public class SightServiceImpl implements SightService {
	@Autowired
	private SightDAO sightDAO;

	@Override
	public List<Sight> GetAllSightList() {
		List<Sight> sight = sightDAO.GetAllSightList();
		return sight;
	}

	@Override
	public Sight findSbyName(String s_name) {
		return null;
	}

	@Override
	public void addSight(String s_name, String s_imgurl, String s_info) {
		sightDAO.addSight(s_name, s_imgurl, s_info);

	}

	@Override
	public void deleteSight(String s_id) {
		sightDAO.deleteSight(s_id);

	}

	@Override
	public void UpdateSight(String s_id, String s_name, String s_imgurl, String s_info) {
		sightDAO.updateSight(s_id, s_name, s_imgurl, s_info);

	}

	@Override
	public List<Sight> SelectSight(String str) {
		return sightDAO.SelectSight(str);

	}

	@Override
	public Sight SelectSightBySId(String sId) {
		return sightDAO.getSightById(sId);
	}

	@Override
	public List<Sight> SelectSightBySname(String sname) {
		return sightDAO.getSightBySName(sname);
	}

	@Override
	public List<Sight> getAllSight() {
		List<Sight> sights = sightDAO.getAllSight();
		return sights;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getTidSname() {
		List o = sightDAO.getTidSname();
		return o;
	}

	@Override
	public PageBean<Sight> findByPage(int currentPage, int pageSize) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		PageBean<Sight> pageBean = new PageBean<Sight>();

		// 封装当前页数
		pageBean.setCurrPage(currentPage);

		// 每页显示的数据
		// int pageSize = 5;
		pageBean.setPageSize(pageSize);

		// 封装总记录数
		int totalCount = sightDAO.selectCount();
		pageBean.setTotalCount(totalCount);

		// 封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);// 向上取整
		pageBean.setTotalPage(num.intValue());

		map.put("start", (currentPage - 1) * pageSize);
		map.put("size", pageBean.getPageSize());
		// 封装每页显示的数据
		List<Sight> lists = sightDAO.findByPage(map);
		pageBean.setLists(lists);

		return pageBean;
	}

}
