package com.xn.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xn.entity.Order;
import com.xn.entity.Sight;
import com.xn.entity.Ticket;
import com.xn.entity.User;
import com.xn.service.OrderService;
import com.xn.service.SightService;
import com.xn.service.TicketService;
import com.xn.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private TicketService ticketServiceImpl;
	@Autowired
	private OrderService orderServiceImpl;
	@Autowired
	private SightService sightServiceImpl;

	// 编辑用户信息
	@RequestMapping(value = "/editinfo")
	public boolean editinfo(String u_id, String u_name, String u_tel, String u_password) {
		userServiceImpl.updateUser(u_id, u_name, u_tel, u_password);
		return true;
	}

	// 用户登录
	@RequestMapping(value = "/key")
	@ResponseBody
	public User findUser(String u_name, String u_password, Model model, HttpSession session) {
		User user = userServiceImpl.findUser(u_name, u_password);
		if (user != null) {
			session.setAttribute("u_session", user);
		}
		// List<User> list = new ArrayList<User>();
		// list.add(user);
		return user;
	}

	// 用户登录
	@RequestMapping(value = "/getKey")
	@ResponseBody
	public User getKey(HttpSession session) {

		User user = (User) session.getAttribute("u_session");
		return user;
	}

	// 查看指定用户的订单
	@RequestMapping(value = "/getOrderByUId")
	@ResponseBody
	public List<Order> getOrderByUId(String u_id, Integer pageNum, Integer pageSize) {
		int pageStr = pageNum * pageSize - pageSize;
		List<Order> heheda = orderServiceImpl.getOrderByUId(u_id, 0, 0);
		int conunt = heheda.size();
		List<Order> historys = orderServiceImpl.getOrderByUId(u_id, pageStr, pageSize);
		return historys;

	}

	@RequestMapping(value = "/getA")
	@ResponseBody
	public int getA(String u_id) {
		List<Order> heheda = orderServiceImpl.getOrderByUId(u_id, 0, 0);
		int conunt = heheda.size();
		return conunt;
	}

	// 添加订单
	@RequestMapping(value = "/addOrder")
	@ResponseBody
	public void addOrder(String u_id, String t_id, String o_num) {
		orderServiceImpl.addOrder(u_id, t_id, o_num);
	}

	// 删除订单
	@RequestMapping(value = "/deleteOrder")
	@ResponseBody
	public int deleteorder(String o_id, HttpServletRequest request) {
		String url = request.getServletContext().getRealPath("static/upload");

		orderServiceImpl.deleteOrder(o_id);
		return 1;
	}

	// 查看景区
	@RequestMapping(value = "/getAllSight")
	public List<Sight> getAllSight() {
		List<Sight> sights = sightServiceImpl.getAllSight();
		return sights;
	}

	// 景点推荐

	// @RequestMapping(value = "/getRecentSight")
	// public List<Sight> getRecentSight() {
	// List<Sight> sights = new ArrayList<Sight>();
	// List<Order> orders = orderServiceImpl.getRecentTickets();
	// for (int i = 0; i < orders.size(); i++) {
	// Order order = orders.get(i);
	// String s_id = ticketServiceImpl.ufindSbT(order.getT_id());
	// Sight sight = sightServiceImpl.SelectSightBySId(s_id);
	// sights.add(sight);
	// }
	// return sights;
	// }
	@RequestMapping(value = "/getRecentSight")
	@ResponseBody
	public Map<String, Object> getRecentSight() {
		Map<String, Object> map = new HashMap<String, Object>();

		List<Sight> sights = new ArrayList<Sight>();

		List<Order> orders = orderServiceImpl.getRecentTickets();
		for (int i = 0; i < orders.size(); i++) {
			List<Object> list = new ArrayList<Object>();
			Order order = orders.get(i);
			String s_id = ticketServiceImpl.ufindSbT(order.getT_id());
			Sight sight = sightServiceImpl.SelectSightBySId(s_id);
			int ticket1 = ticketServiceImpl.SelectTickectByTId(String.valueOf(order.getT_id())).getT_num();
			int ticket2 = order.getO_num();
			list.add(sight);
			list.add(ticket1);
			list.add(ticket2);
			map.put(String.valueOf(i), list);

		}
		// JSONArray json = new JSONArray();
		// json.add(map);

		// return json.toString();
		return map;
	}
	// 根据门票查看景区
	// @RequestMapping(value="/UserfindsBt")
	// public

	// 查看门票剩余量
	@RequestMapping(value = "/findLastTickets")
	public Map<Integer, Object> findLastTickets() {
		List<Ticket> tickets1 = ticketServiceImpl.GetAllTicketList();
		List<Order> tickets2 = orderServiceImpl.findAllTicketsSaled();
		int len1 = tickets1.size();
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		for (int i = 0; i < len1; i++) {
			Ticket ticket1 = tickets1.get(i);
			for (Order order : tickets2) {
				if (order.getT_id() == ticket1.getT_id()) {
					List<Object> list = new ArrayList<Object>();
					list.add(order);
					list.add(ticket1);
					map.put(ticket1.getT_id(), list);
					// List<Object> ss = (List<Object>) map.get(1);//data.1
					// ss.get(1);//data.1[1]
				}
			}

		}
		return map;
		// Ticket ticket = ticketServiceImpl.findTicketsAll(s_id);
		// int ticket1 = ticket.getT_num();
		// int ticket2 = orderServiceImpl.findTicketsSaled(ticket.getT_id());
		// int b[] = new int[3];
		// int ticket3 = ticket1 - ticket2;
		// b[1] = ticket3;// 剩余门票
		// b[2] = ticket2;// 已售出门票
		// return b;
	}

	// 查看指定用户门票未支付的订单，组成购物车
	@RequestMapping(value = "/findPsatateByU")
	public List findPsatateByU(String heheda, HttpServletRequest request) {
		String a = (String) request.getAttribute("heheda");

		List orders = userServiceImpl.findPsatateByU(heheda);
		return orders;
	}

	// 立即取票，用户查询支付状态和出票状态，订单状态都为1
	@RequestMapping(value = "/findTrueByU")
	public List findTrueByU(String u_id) {
		List orders = orderServiceImpl.findTrueByU(u_id);
		return orders;
	}

	//
	// 申请办理退款
	@RequestMapping(value = "/tuikuan")
	public int updateOrder(String o_id) {
		orderServiceImpl.updateOrder(o_id);
		return 1;
	}

	// 使用状态
	@RequestMapping(value = "/use")
	public int updateUse(String o_id) {
		orderServiceImpl.updateUse(o_id);
		return 1;
	}

	// 支付状态
	@RequestMapping(value = "/pay")
	public boolean updatePay(String o_id) {
		orderServiceImpl.updatePay(o_id);
		return true;
	}

	// 根据t_id找风景名字
	@RequestMapping(value = "/findSnameBt")
	public String findSnameBt(String t_id) {
		String s_id = ticketServiceImpl.ufindSbT(Integer.valueOf(t_id));
		String s_name = sightServiceImpl.SelectSightBySId(s_id).getS_name();
		return s_name;
	}

	// 根据u_id找票的图片，景点名字，票的预定时间，风景的名字
	@RequestMapping(value = "/findWhole")
	public List findWhole(String u_id) {
		List<Map> list = userServiceImpl.findWhole(u_id);
		int len = list.size();
		for (int i = 0; i < len; i++) {
			Date date = (Date) list.get(i).get("o_u_time");
			if (date == null) {
				continue;
			}
			SimpleDateFormat fromat = new SimpleDateFormat("yyyy-MM-dd");
			System.out.print(date);
			list.get(i).put("o_u_time", fromat.format(date));
		}
		return list;
	}

	// 更换头像
	@RequestMapping(value = "/changeTou")
	public int changeTou(String u_id, @RequestParam(value = "file") MultipartFile file, HttpServletRequest request)
			throws IllegalStateException, IOException {
		String realPath = request.getSession().getServletContext().getRealPath("/user/images");
		System.out.println("文件上传的路径" + realPath);
		String filename = file.getOriginalFilename();
		// 获取文件后缀名
		String extensionname = filename.substring(filename.lastIndexOf(".") + 1);
		String newFilename = String.valueOf(System.currentTimeMillis()) + "." + extensionname;
		File dir = new File(realPath, newFilename);
		// 如果dir代表的文件不存在，则创建它，
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 如果存在则直接执行下面操作
		file.transferTo(dir);// 将上传的实体文件复制到指定目录下
		userServiceImpl.changeTou(u_id, newFilename);
		return 1;
	}
}
