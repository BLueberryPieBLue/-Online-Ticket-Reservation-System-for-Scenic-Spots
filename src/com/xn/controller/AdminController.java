package com.xn.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xn.entity.Message;
import com.xn.entity.Order;
import com.xn.entity.PageBean;
import com.xn.entity.Sight;
import com.xn.entity.Ticket;
import com.xn.entity.User;
import com.xn.service.MessageService;
import com.xn.service.OrderService;
import com.xn.service.SightService;
import com.xn.service.TicketService;
import com.xn.service.UserService;

@RestController
public class AdminController {
	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private TicketService ticketServiceImpl;
	@Autowired
	private SightService sightServiceImpl;
	@Autowired
	private OrderService orderServiceImpl;
	@Autowired
	private MessageService messageServiceImpl;

	// 获取用户列表
	@RequestMapping(value = "/GetAllUserList")
	public List<User> GetAllUserList() {
		List<User> user = userServiceImpl.GetAllUserList();
		return user;
	}

	// 获取门票列表
	@RequestMapping(value = "/GetAllTicketList")
	public List<Ticket> GetAllTicketList() {
		List<Ticket> tickets = ticketServiceImpl.GetAllTicketList();
		return tickets;
	}

	// 获取景点列表
	@RequestMapping(value = "/GetAllSightList")
	public List<Sight> GetAllSightList() {
		List<Sight> sight = sightServiceImpl.GetAllSightList();
		return sight;
	}

	// 获取订单列表
	@RequestMapping(value = "/GetAllOrder")
	public List<Order> GetAllOrderList() {
		List<Order> order = orderServiceImpl.GetAllOrderList();
		return order;
	}

	// 添加用户
	@RequestMapping(value = "/AddUser")
	public int AddUser(String u_name, String u_tel, String u_password, String isroot) {
		userServiceImpl.addUser(u_name, u_tel, u_password, isroot);
		return 1;
	}

	// 添加门票
	@RequestMapping(value = "/AddTicketBySId")
	public int AddTicket(String s_id, String t_price, String t_status, String t_num, String t_time_start,
			String t_time_end, String t_category, @RequestParam(value = "file") MultipartFile file,
			HttpServletRequest request) throws IllegalStateException, IOException {
		String realPath = request.getSession().getServletContext().getRealPath("/main/images");
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
		ticketServiceImpl.addTicket(s_id, t_price, t_status, t_num, newFilename, t_time_start, t_time_end, t_category);
		return 1;
	}

	// 添加景点
	@RequestMapping(value = "/AddSight")
	public int AddSight(String s_name, String s_info, @RequestParam(value = "file") MultipartFile file,
			HttpServletRequest request) throws IllegalStateException, IOException {
		String realPath = request.getSession().getServletContext().getRealPath("/main/images");
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
		sightServiceImpl.addSight(s_name, newFilename, s_info);
		return 1;
	}

	// 添加订单
	@RequestMapping(value = "/AddOrder")
	public int AddOrder(String u_id, String t_id, String t_num) {
		orderServiceImpl.addOrder(u_id, t_id, t_num);
		return 1;
	}

	// 删除用户
	@RequestMapping(value = "/DeleteUser")
	public int DeleteUser(String u_id) {
		userServiceImpl.deleteUser(u_id);
		return 1;
	}

	// 删除门票
	@RequestMapping(value = "/DeleteTicket")
	public int DeleteTicket(String t_id) {
		ticketServiceImpl.deleteTicket(t_id);
		return 1;
	}

	// 删除景点
	@RequestMapping(value = "/DeleteSight")
	public int DeleteSight(String s_id) {
		sightServiceImpl.deleteSight(s_id);
		return 1;
	}

	// 删除订单
	@RequestMapping(value = "/DeleteOrder")
	public int DeleteOrder(String o_id) {
		orderServiceImpl.deleteOrder(o_id);
		return 1;
	}

	// 更新用户
	@RequestMapping(value = "/UpdateUser")
	public int UpdateUser(String u_id, String u_name, String u_tel, String u_password, String isroot) {
		userServiceImpl.UpdateUserA(u_id, u_name, u_tel, u_password, isroot);
		return 1;
	}

	// 更新门票
	@RequestMapping(value = "/UpdateTicket")
	public int UpdateTicket(String t_id, String s_id, String t_price, String t_status, String t_num,
			String t_time_start, String t_time_end, String t_category, @RequestParam(value = "file") MultipartFile file,
			HttpServletRequest request) throws IllegalStateException, IOException {
		String realPath = request.getSession().getServletContext().getRealPath("/main/images");
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
		ticketServiceImpl.UpdateTicket(t_id, s_id, t_price, t_status, t_num, newFilename, t_time_start, t_time_end,
				t_category);
		return 1;
	}

	// 更新景点
	@RequestMapping(value = "/UpdateSight")
	public int UpdateSight(String s_id, String s_name, String s_info, @RequestParam(value = "file") MultipartFile file,
			HttpServletRequest request) throws IllegalStateException, IOException {
		String realPath = request.getSession().getServletContext().getRealPath("/main/images");
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
		sightServiceImpl.UpdateSight(s_id, s_name, newFilename, s_info);
		return 1;
	}

	// 更新订单
	@RequestMapping(value = "/UpdateOrder")
	public int UpdateOrder(String o_id, String u_id, String t_id, String o_num, String order_state, String pay_state,
			String use_state) {
		orderServiceImpl.UpdateOrder(o_id, u_id, t_id, o_num, order_state, pay_state, use_state);
		return 1;
	}

	// 模糊查询用户By用户名
	@RequestMapping(value = "/SelectUser")
	public List<User> SelectUser(String str) {
		List<User> user = userServiceImpl.SelectUser(str);
		return user;
	}

	// 模糊查询门票By景点名
	@RequestMapping(value = "/SelectTicket")
	public List<Ticket> SelectTicket(String str) {
		List<Ticket> list = ticketServiceImpl.SelectTicket(str);
		return list;
	}

	// 模糊查询景点By景点名
	@RequestMapping(value = "/SelectSight")
	public List<Sight> SelectSight(String str) {
		List<Sight> list = sightServiceImpl.SelectSight(str);
		return list;
	}

	// 模糊查询订单By用户名
	@RequestMapping(value = "/SelectOrder")
	public List<Order> SelectOrder(String str) {
		List<Order> list = orderServiceImpl.SelectOrder(str);
		return list;
	}

	// 查询用户ByUname
	@RequestMapping(value = "/SelectUserByUname")
	public User SelectUserByUname(String Uname) {
		User user = userServiceImpl.SelectUIdByUname(Uname);
		return user;
	}

	// 查询用户ByUId
	@RequestMapping(value = "/SelectUserByUId")
	public User SelectUserByUId(String UId) {
		User user = userServiceImpl.SelectUIdByUId(UId);
		return user;
	}

	// 查询景点BySname
	@RequestMapping(value = "/SelectSightBySname")
	public List<Sight> SelectSightBySname(String Sname) {
		List<Sight> s = sightServiceImpl.SelectSightBySname(Sname);
		return s;
	}

	// 查询景点BySId
	@RequestMapping(value = "/SelectSightBySId")
	public Sight SelectSightBySId(String SId) {
		Sight s = sightServiceImpl.SelectSightBySId(SId);
		return s;
	}

	// 查询门票ByTId
	@RequestMapping(value = "/SelectTickectByTId")
	public Ticket SelectTickectByTId(String TId) {
		Ticket s = ticketServiceImpl.SelectTickectByTId(TId);
		return s;
	}

	// 查询订单ByOid
	@RequestMapping(value = "/SelectOrderByOid")
	public Order SelectOrderByOid(String Oid) {
		Order o = orderServiceImpl.SelectOrderByOid(Oid);
		return o;
	}

	// 上传文件 测试
	@RequestMapping(value = "/UploadFile")
	public String upload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request)
			throws IOException {
		String realPath = request.getSession().getServletContext().getRealPath("/main/images");
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
		ticketServiceImpl.addTicket("1", "1", "1", "1", newFilename, "1", "1", "1");
		return newFilename;
	}

	// getNum 门票售出量
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getNum1")
	public List getNum() {
		List o = orderServiceImpl.getNum();
		return o;
	}

	// Map t_id,s_name
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getTidSname")
	public List getTidSname() {
		List o = sightServiceImpl.getTidSname();
		return o;
	}

	// 折线图 getMan
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getMan")
	public List getMan() {
		List o = orderServiceImpl.getMan();
		return o;
	}

	// 获取留言
	@RequestMapping(value = "/GetAllMessageList")
	public List<Message> GetAllMessageList() {
		List<Message> o = messageServiceImpl.GetAllMessageList();
		return o;
	}

	// 增加留言
	@RequestMapping(value = "/addMessage")
	public int addMessage(String u_id, String m_title, String m_info, @RequestParam(value = "file") MultipartFile file,
			HttpServletRequest request) throws IllegalStateException, IOException {
		String realPath = request.getSession().getServletContext().getRealPath("/main/images");
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
		messageServiceImpl.addMessage(u_id, m_title, m_info, newFilename);
		return 1;
	}

	// 删除留言
	@RequestMapping(value = "/deleteMessage")
	public int deleteMessage(String m_id) {
		messageServiceImpl.deleteMessage(m_id);
		return 1;
	}

	// 更新留言
	@RequestMapping(value = "/updateMessage")
	public int updateMessage(String m_id, String m_title, String m_info,
			@RequestParam(value = "file") MultipartFile file, HttpServletRequest request)
			throws IllegalStateException, IOException {
		String realPath = request.getSession().getServletContext().getRealPath("/main/images");
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
		messageServiceImpl.updateMessage(m_id, m_title, m_info, newFilename);
		return 1;
	}

	// 用户注册
	@RequestMapping(value = "/regist")
	public int regist(String uname, String password) {
		userServiceImpl.regist(uname, password);
		return 1;
	}

	// 用户退出登录
	@RequestMapping(value = "/logout")
	@ResponseBody
	public int logout(HttpSession session) {
		session.removeAttribute("u_session");
		return 1;
	}

	// 添加订单
	@RequestMapping(value = "/UserAddOrder")
	public int UserAddOrder(String u_id, String t_id, String t_num, String d) {
		orderServiceImpl.UseraddOrder(u_id, t_id, t_num, d);
		return 1;
	}

	// 分页 User
	@RequestMapping(value = "/UserInfo")
	public PageBean<User> UserInfo(int currentPage, int pageSize) {
		PageBean<User> o = userServiceImpl.findByPage(currentPage, pageSize);
		return o;
	}

	// 分页 Ticket
	@RequestMapping(value = "/TicketInfo")
	public PageBean<Ticket> TicketInfo(int currentPage, int pageSize) {
		PageBean<Ticket> o = ticketServiceImpl.findByPage(currentPage, pageSize);
		return o;
	}

	// 分页 Sight
	@RequestMapping(value = "/SightInfo")
	public PageBean<Sight> SightInfo(int currentPage, int pageSize) {
		PageBean<Sight> o = sightServiceImpl.findByPage(currentPage, pageSize);
		return o;
	}

	// 分页 Order
	@RequestMapping(value = "/OrderInfo")
	public PageBean<Order> OrderInfo(int currentPage, int pageSize) {
		PageBean<Order> o = orderServiceImpl.findByPage(currentPage, pageSize);
		return o;
	}

	// 分页 Message
	@RequestMapping(value = "/MessageInfo")
	public PageBean<Message> MessageInfo(int currentPage, int pageSize) {
		PageBean<Message> o = messageServiceImpl.findByPage(currentPage, pageSize);
		return o;
	}
}
