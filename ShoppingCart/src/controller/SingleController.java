package controller;

import java.io.*;
import java.sql.SQLException;
import db.*;
import model.*;
import rest.*;
import java.util.*;
import java.util.stream.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

@WebServlet("/SingleController")
public class SingleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int hitCount; 
	private Properties prop;
    
	public void init() 
     { 
	     // Reset hit counter.
	     hitCount = 0;
	     try {
	  		this.prop = new Properties();
	  		InputStream in = new FileInputStream("C:\\Users\\sagar\\git\\ShoppingCart\\ShoppingCart\\ShoppingCart.properties");
	  		this.prop.load(in);
	  		in.close();
	 	} catch (Exception e) {
	 		System.out.println("Properties file not found at located.. "+e);
	 	}
     } 
     

    public SingleController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sess = request.getSession();
		
		if(sess.isNew()) {
			response.sendRedirect("SingleController?page=Logout");
		}else{
			System.out.println("Hits= "+ (++hitCount));
			
			String page = request.getParameter("page");
			System.out.println("page= " + page);
			DAO d1 = null;
			ArrayList<Products> a1;			
			ArrayList<Shopping> a2;			

			User u1= new User();	
			
			switch (page) {		
			
			case "filterP":
			{
				try {
					String prname = request.getParameter("prname");
					List<Products> a5 = (new DAO().getProducts()).stream().filter(p->p.getPrName().equalsIgnoreCase(prname)).collect(Collectors.toList());
					sess.setAttribute("asi", a5);
					request.setAttribute("type", a5.get(0).getType());
					request.getRequestDispatcher("UserProducts.jsp").forward(request, response);
				} catch (Exception e1) {
					System.out.println("Exception for UserProducts= "+ e1);
					d1.closeSession();
					request.getRequestDispatcher("Wel.jsp").include(request, response);
				}

				break;
			}
			
			case "searchAJAX":
			{
				String term = request.getParameter("term");
				if (term!="" && term!=null) {
					try {
		                String searchList = new Gson().toJson((new DAO().getProducts()).stream().filter(p->p.getPrName().toLowerCase().contains(term.toLowerCase())).map(Products::getPrName).limit(4).collect(Collectors.toList()));
		                response.getWriter().write(searchList);
					} catch (SQLException e) {
		                response.getWriter().write(" ");					
						System.out.println(e);
					}					
				} else {
	                response.getWriter().write(" ");					
				}
				break;
			}

			case "removeItem":
			{
				try {
					Shopping T = (Shopping) sess.getAttribute("total");
					String prname=request.getParameter("prname");
					ArrayList<Shopping> a3= new ArrayList<Shopping>();
					if((T.getTotal())!=0){
						for (Shopping s2:(ArrayList<Shopping>) sess.getAttribute("shopping")) {
							if(prname.equals(s2.getPrName())){
								boolean status=(new DAO()).ReplaceSingleItem(s2);
								if(status){
									T.setTotal(T.getTotal()-s2.getAmt());
									sess.setAttribute("total", T);
									System.out.println("Repalced Item= "+s2.getPrName());
								}								
							} else {
								a3.add(s2);
							}
						}
						sess.setAttribute("shopping", a3);
					}
					response.sendRedirect("Pay.jsp");
				} catch (Exception e) {
					d1.closeSession();
					System.out.println("Exception at Replacing Item case" +e);
					request.getRequestDispatcher("Wel.jsp").include(request, response);
				}
				break;
			}
			
			case "sortList":
			{
				try {
					String type = request.getParameter("type");			
					String var = request.getParameter("var");
					String from = request.getParameter("from");
					System.out.println("Sorted By " + var+" "+type+" "+from);
					a1 = new ArrayList<Products>();
					a1 = (ArrayList<Products>) sess.getAttribute("asi");
					switch (var) {
						case "id":
							Collections.sort(a1);
							break;
						case "name":
							Collections.sort(a1, (p1, p2)->p1.getPrName().compareToIgnoreCase(p2.getPrName()));
							break;
						case "qty":
							Collections.sort(a1, new QaComparator());
							break;
						case "price":
							Collections.sort(a1, new PriceComparator());
							break;
						default:
							break;
					}
					sess.setAttribute("asi", a1);				
					request.setAttribute("type", type);
					if(from.equals("admin")){
						request.getRequestDispatcher("Products.jsp").forward(request, response);
					} else{
						request.getRequestDispatcher("UserProducts.jsp").forward(request, response);
					}
				} catch (Exception e) {
					d1.closeSession();
					System.out.println("Exception at SortList Item case" +e);
					request.getRequestDispatcher("Wel.jsp").include(request, response);
				}
				break;
			}
			
			
			case "ShoppingClear":
			{
				try {
					Shopping T = (Shopping) sess.getAttribute("total");
					if((T.getTotal())!=0){
						for (Shopping s1:(ArrayList<Shopping>) sess.getAttribute("shopping")) {
							boolean status = (new DAO()).ReplaceItems(s1);
							if(status==true){System.out.println("Repalced Item= "+s1);}
						}
					}
					
					(new DAO()).ShoppingTruncate();

					T.setTotal(0.0d);
					sess.setAttribute("total", T);

					a2=new ArrayList<Shopping>();
					a2=(new DAO()).shoppingtable();
					sess.setAttribute("shopping", a2);						

					response.sendRedirect("Pay.jsp");
				} catch (Exception e) {
					System.out.println("Exception at ShoppingClear case"+e);
					d1.closeSession();
					request.getRequestDispatcher("Wel.jsp").include(request, response);
				}
				break;
			}
			
			case "LatestCommander":
			{
				u1 = (User) sess.getAttribute("u1");
				String u = u1.getUsername();
		   		Shopping T = (Shopping) sess.getAttribute("total");
				int i;
				try {
					for (i = 0; i < (((ArrayList<Products>) sess.getAttribute("asi")).size()); i++) {
						System.out.println("qtn= "+request.getParameter("qtn[" + i + "]"));
						if (request.getParameter("qtn[" + i + "]") != null 
								&& request.getParameter("qtn[" + i + "]") != "") {
							
							int n = Integer.parseInt(request.getParameter("qtn[" + i + "]"));
							int a = Integer.parseInt(request.getParameter("qta[" + i + "]"));
							double p = Double.parseDouble(request.getParameter("itp[" + i + "]"));
							String itname = request.getParameter("itname[" + i + "]");
							String ft = request.getParameter("scnm[" + i + "]");
							System.out.println("n= "+n+"\t"+"a= "+a+"\t"+"p= "+p+"\t"+"ft= " + ft + "\t"+"itname= "+itname+"\t"+"u= "+u);

							T = (new DAO()).Commander(n, a, p, ft, itname, u, T.getTotal());		
							sess.setAttribute("total", T);
							
							a2=new ArrayList<Shopping>();
							a2=(new DAO()).shoppingtable();
							a2.forEach(System.out::println);;
							sess.setAttribute("shopping", a2);						
						}
					}
					response.sendRedirect("Pay.jsp");
				} catch (SQLException | NullPointerException | NumberFormatException ex) {
					System.out.println(ex + " sagar pawar");
					d1.closeSession();
					request.getRequestDispatcher("Wel.jsp").include(request, response);
				} catch (Exception e) {
					response.sendRedirect("error.jsp");
				}
				break;
			}
			
			
			case "SectionItemsListToUser":
			{
				String type = request.getParameter("type");
				try {
					List<Products> a5 = (new DAO().getProducts()).stream().filter(p->p.getType().equals(type)).sorted((h1, h2)->h1.getPrName().compareToIgnoreCase(h2.getPrName())).collect(Collectors.toList());
					sess.setAttribute("asi", a5);
					request.setAttribute("type", type);
					request.getRequestDispatcher("UserProducts.jsp").forward(request, response);
				} catch (Exception e1) {
					System.out.println("Exception for UserProducts= "+ e1);
					d1.closeSession();
					request.getRequestDispatcher("Wel.jsp").include(request, response);
				}
				break;
			}
			
			
			case "DisplayProductSectionsUserHome":
			{
				try {			
					Set<String> s1 =(new DAO().getProducts()).stream().sorted((f1, f2)->f1.getPrName().compareToIgnoreCase(f2.getPrName())).map(Products::getType).collect(Collectors.toSet());	
					sess.setAttribute("sc", s1);
					response.sendRedirect("UserHome.jsp");
				} catch (SQLException e) {
					System.out.println("SQLException at Home for sections= "+e);
					response.sendRedirect("SingleController?page=Logout");
				}
				break;
			}
			
			
			case "ItemUpdating":
			{
				String id = request.getParameter("a1");
				String prname = request.getParameter("a2");
				String type = request.getParameter("a3");
				String qta = request.getParameter("a4");
				String price = request.getParameter("a5");
				System.out.println("id= "+id+"\t"+"prname= "+prname+"\t"+"type= "+type+"\t"+"qta= "+qta+"\t"+"price= "+price);
				try {
					if((new DAO()).ItemUpdating(id,prname,type,qta,price)) {
						System.out.println("Updated Product");
						response.sendRedirect("UpdateItem.jsp?msg=Updated Product= "+prname);
					}else{
						System.out.println("Updation Failure");
						response.sendRedirect("UpdateItem.jsp?msg=Updation Failure Product= "+prname);
					}																	
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Updation Failure");
					response.sendRedirect("UpdateItem.jsp?msg=Updation Failure Product= "+prname);					
				}
				break;
			}

			
			case "ItemDeletion":
			{	
					String id = request.getParameter("a1");
					try {
						d1 = new DAO();
						if(d1.ItemDeletion(id)) {
							System.out.println("Deleted Product");
							response.sendRedirect("DeleteItem.jsp?msg=Deleted Product ID= "+id);
						}else{
							System.out.println("Deletion Failure");
							response.sendRedirect("DeleteItem.jsp?msg=Deletion Failure ID= "+id);
						}												
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("Deletion Failure");
						response.sendRedirect("DeleteItem.jsp?msg=Deletion Failure ID= "+id);
					}
					break;
			}			

			case "LinkItemDeletion":
			{	
					String id = request.getParameter("id");
					try {
						d1 = new DAO();
						if(d1.ItemDeletion(id)) {
							System.out.println("Deleted Product");
							response.sendRedirect("Homepage.jsp?msg=Deleted Product ID= "+id);
						}else{
							System.out.println("Deletion Failure");
							response.sendRedirect("DeleteItem.jsp?msg=Deletion Failure ID= "+id);
						}												
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("Deletion Failure");
						response.sendRedirect("DeleteItem.jsp?msg=Deletion Failure ID= "+id);
					}
					break;
			}			
			
			case "LinkSectionDeletion":
			{	
				String type = request.getParameter("type");
				try {
					d1 = new DAO();
					if(d1.SectionDeletion(type)) {
						System.out.println("Deleted Section");
						response.sendRedirect("Homepage.jsp?msg=Deleted Section= "+type);
					}else{
						System.out.println("Deletion Failure");
						response.sendRedirect("DeleteItem.jsp?msg=Deletion Failure Section= "+type);
					}						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Deletion Failure");
					response.sendRedirect("DeleteItem.jsp?msg=Deletion Failure Section= "+type);
				}
				break;
			}


			
			case "SectionDeletion":
			{	
				String type = request.getParameter("a2");
				try {
					d1 = new DAO();
					if(d1.SectionDeletion(type)) {
						System.out.println("Deleted Section");
						response.sendRedirect("DeleteItem.jsp?msg=Deleted Section= "+type);
					}else{
						System.out.println("Deletion Failure");
						response.sendRedirect("DeleteItem.jsp?msg=Deletion Failure Section= "+type);
					}						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Deletion Failure");
					response.sendRedirect("DeleteItem.jsp?msg=Deletion Failure Section= "+type);
				}
				break;
			}
			
			
			case "ItemInserting":
			{
				String type = request.getParameter("a1");
				String prname = request.getParameter("a2");
				int qta = Integer.parseInt(request.getParameter("a3")) ;
				String price = request.getParameter("a4");
				System.out.println(prname+"\t"+qta+"\t"+price+"\t"+type);
				try {
					d1 = new DAO();
					String status = d1.ItemInserting(prname,type,qta,price);
					if(status == "INSERT") {
						System.out.println("Inserted NEW Product");
						response.sendRedirect("InsertItem.jsp?msg=Inserted NEW Product= "+prname);
					}else if(status == "UPDATE") {
						System.out.println("Updated/Inserted Product");
						response.sendRedirect("InsertItem.jsp?msg=Updated/Inserted Product= "+prname);						
					} else{
						System.out.println("Insertion Failure");
						response.sendRedirect("InsertItem.jsp?msg=Insertion Failure Product= "+prname);
					}	
				} catch (Exception e) {
					System.out.println("Insertion Failure");
					response.sendRedirect("InsertItem.jsp?msg=Insertion Failure Product= "+prname);
				}
				break;
			}
							
			
			case "SectionItemsList":
			{
				try {
					String type = request.getParameter("type");
					List<Products> a5 = (new DAO().getProducts()).stream().filter(p->p.getType().equals(type)).sorted().collect(Collectors.toList());
					sess.setAttribute("asi", a5);
					request.setAttribute("type", type);
					request.getRequestDispatcher("Products.jsp").forward(request, response);
				} catch (Exception e1) {
					System.out.println("Exception at Home for sections= "+ e1);
					request.getRequestDispatcher("Wel.jsp").include(request, response);
				}
				break;
			}

			
			case "DisplayProductSections":
			{
				try {
					Set<String> s1 =(new DAO().getProducts()).stream().sorted((f1, f2)->f1.getPrName().compareToIgnoreCase(f2.getPrName())).map(Products::getType).collect(Collectors.toSet());
					sess.setAttribute("sc", s1);
					response.sendRedirect("Home.jsp");
				} catch (Exception e) {
					System.out.println("SQLException at DisplayProduct= "+e);
					response.sendRedirect("SingleController?page=Logout");
				}
				break;
			}
			
			case "AJAX":
			{
				String user = request.getParameter("usname");
				String loginpass = request.getParameter("pass");
				System.out.println(user+"\tAJAX\t"+loginpass);
				u1.setUsername(user);
				u1.setPassword(loginpass);
				try {
					d1 = new DAO();
					u1 = d1.validateUser(u1);
					if(u1.getUsername()!=null) {
						System.out.println("AJAX-if");
						
//						response.sendRedirect("Login.jsp?msg=Validate User AJAX");
					} else {
						System.out.println("AJAX-else");
	//					response.sendRedirect("Login.jsp?msg=Invalid User AJAX");

					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
			case "AdminLogin":
			{
				try {
					String user = request.getParameter("usname");
					String loginpass = request.getParameter("pass");
					String msg = "Invalid Admin";
					System.out.println(user+"\t"+loginpass);
					if (user.equals("admin")&&loginpass.equals("test")) {
						sess.setAttribute("session", "login");
						response.sendRedirect("AdminHomePage.jsp");//jsp	
					} else {
						request.setAttribute("msg", msg);
						request.getRequestDispatcher("AdminLoginPage.jsp").forward(request, response);
					}					
					user="";
					loginpass="";
				} catch(Exception e){
					System.out.println(e);
					response.sendRedirect("AdminLoginPage.jsp");
				}
				break;
			}
			
			case "MyController":
			{
				try {
					String user = request.getParameter("usname");
					String loginpass = request.getParameter("pass");
					String msg = "Invalid User";
					System.out.println(user+"\t"+loginpass);
					u1.setUsername(user);
					u1.setPassword(loginpass);
					u1 = (new DAO()).validateUser(u1);
					if(u1!=null) {
						double total=0.0;
						Shopping T = new Shopping();	 
						T.setTotal(total);
						sess.setAttribute("total", T);

						(new DAO()).ShoppingTruncate();
						
						sess.setAttribute("u1", u1);
						sess.setAttribute("session", "login");
						response.sendRedirect("welcome.jsp");//jsp	
					} else {
						request.setAttribute("msg", msg);
						request.getRequestDispatcher("Login.jsp").forward(request, response);
					}
					user="";
					loginpass="";					
				} catch (Exception e) {
					System.err.println(e);
					response.sendRedirect("Login.jsp");
				}		
				break;
			}
			
			
			case "SignupCntl":
			{					
				String name = request.getParameter("txt1");
				String password = request.getParameter("txt2");
				String city=request.getParameter("txt3");
			
				u1 = new User(name, password, city);
			
				try {
					if(u1.getUsername()!=null) {
						u1=(new DAO()).SingupDAO(u1);
						request.getRequestDispatcher("Login.jsp").forward(request, response);
					} else {
						request.getRequestDispatcher("Sign-up.jsp").include(request, response);
					}
				} catch (SQLException e) {
					System.out.println(e);
					request.getRequestDispatcher("Sign-up.jsp").include(request, response);
				}
				break;
			}	

			
			case "Logout":
			{
				try {			
					Shopping T = (Shopping) sess.getAttribute("total");
					if((T.getTotal())!=0){
						a2=(ArrayList<Shopping>) sess.getAttribute("shopping");
						for (Shopping s1 : a2) {
							if((new DAO()).ReplaceItems(s1))
								System.out.println("Repalced Item= "+s1);
							else 
								System.out.println("NOT Repalced Item= "+s1);
						}
					}

					(new DAO()).ShoppingTruncate();

		//			d1.HibernateSQLclose();
					sess.setAttribute("session", "logout");
					
					Cookie c = new Cookie("name", "");
					c.setMaxAge(0);
					
					sess=request.getSession();
					sess.invalidate();
					
					Runtime.getRuntime().runFinalization();
					System.gc();
					System.out.println("logout");		
					
					response.sendRedirect("Login.jsp");
				} catch (Exception e) {
					try {
						(new DAO()).ShoppingTruncate();

	//					d1.HibernateSQLclose();;
						sess.setAttribute("session", "logout");

						sess=request.getSession();
						sess.invalidate();

						System.out.println("Exception= "+e);
						
						response.sendRedirect("Login.jsp");
					} catch (SQLException e1) {
						sess.setAttribute("session", "logout");
						System.out.println("Exception= "+e1);
						response.sendRedirect("Login.jsp");
					}
				}				
				break;
			}
			
			
			case "Print-Bill":
			{
				try {				
					User u2 = (User) sess.getAttribute("u1");
			   		File Fb = new File(this.prop.getProperty("Bill.File.Location") + File.separator + u2.getUsername() + ".txt");
					FileWriter Fwb = new FileWriter(Fb);			   		
//					FileWriter Fwb = new FileWriter(Fb,true);
					BufferedWriter Bwb = new BufferedWriter(Fwb);
					PrintWriter Pwb = new PrintWriter(Bwb);

					Shopping T = (Shopping) sess.getAttribute("total");
		   		
					if((T.getTotal())==0){
						Pwb.println("\n\t\t\tShopping-Cart\n\n");
						Pwb.println("\tNot Purchased any thing (0:-Item purchase)\n\n");
						Pwb.println("\t\t\tTOTAL AMT is:=" + T.getTotal());
					} else {
						a2=(ArrayList<Shopping>) sess.getAttribute("shopping");
						Pwb.println("\n\t\t\t\tShopping-Cart\n\n");
						Pwb.println("\tQtN\t*\tPrice\t=\tAmount\t=>\tItem\n");						
						for (Shopping s: a2) {
							Pwb.println("\t"+s.getQN() + "\t*\t" + s.getPrice() + "\t=\t " + s.getAmt() +"\t=>\t"+s.getPrName());
						}
						Pwb.println("\n\n\t\t\tTOTAL AMT is:=" + T.getTotal());						
					}
					Pwb.close();
					
					sess.setAttribute("total", 0.0d);

					System.out.println("logout");		
					response.sendRedirect("SingleController?page=Logout");
				} catch (Exception e) {
					System.out.println("Exception= "+e);
					response.sendRedirect("SingleController?page=Logout");
				}	
				break;
			}
			
			
			default:
				
				break;
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}