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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

			case "removeItem":
			{
				try {
					Shopping T = (Shopping) sess.getAttribute("total");
					String prname=request.getParameter("prname");
					ArrayList<Shopping> a3= new ArrayList<Shopping>();
					if((T.getTotal())!=0){
						for (Shopping s2:(ArrayList<Shopping>) sess.getAttribute("shopping")) {
							if(prname.equals(s2.getPrName())){
								d1=new DAO();
								boolean status=d1.ReplaceSingleItem(s2);
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
					RequestDispatcher rd = request.getRequestDispatcher("Wel.jsp");
					rd.include(request, response);
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
						RequestDispatcher rd = request.getRequestDispatcher("Products.jsp");
						rd.forward(request, response);
					} else{
						RequestDispatcher rd = request.getRequestDispatcher("UserProducts.jsp");
						rd.forward(request, response);
					}
				} catch (Exception e) {
					d1.closeSession();
					System.out.println("Exception at SortList Item case" +e);
					RequestDispatcher rd = request.getRequestDispatcher("Wel.jsp");
					rd.include(request, response);
				}
				break;
			}
			
			
			case "ShoppingClear":
			{
				try {
					Shopping T = (Shopping) sess.getAttribute("total");
					if((T.getTotal())!=0){
						for (Shopping s1:(ArrayList<Shopping>) sess.getAttribute("shopping")) {
							d1=new DAO();
							boolean status=d1.ReplaceItems(s1);
							if(status==true){System.out.println("Repalced Item= "+s1);}
						}
					}
					
					d1=new DAO();
					d1.ShoppingTruncate();

					double total=0.0;
					T.setTotal(total);
					sess.setAttribute("total", T);

					a2=new ArrayList<Shopping>();
					d1=new DAO();
					a2=d1.shoppingtable();
					sess.setAttribute("shopping", a2);						

					response.sendRedirect("Pay.jsp");
				} catch (Exception e) {
					System.out.println("Exception at ShoppingClear case"+e);
					d1.closeSession();
					RequestDispatcher rd = request.getRequestDispatcher("Wel.jsp");
					rd.include(request, response);
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

							d1=new DAO();
							T = d1.Commander(n, a, p, ft, itname, u, T.getTotal());		
							sess.setAttribute("total", T);
							
							a2=new ArrayList<Shopping>();
							d1=new DAO();
							a2=d1.shoppingtable();
							a2.forEach(System.out::println);;
							sess.setAttribute("shopping", a2);						
						}
					}
					response.sendRedirect("Pay.jsp");
				} catch (SQLException | NullPointerException | NumberFormatException ex) {
					System.out.println(ex + " sagar pawar");
					d1.closeSession();
					RequestDispatcher rd = request.getRequestDispatcher("Wel.jsp");
					rd.include(request, response);
				} catch (Exception e) {
					response.sendRedirect("error.jsp");
				}
				break;
			}
			
			
			case "SectionItemsListToUser":
			{
				String type = request.getParameter("type");
				ProductForm prodList = new ProductForm();
				try {
					a1 = new ArrayList<Products>();
					d1=new DAO();
					//a1=d1.SectonItemsList(type);
					a1=(ArrayList<Products>) d1.getProducts();
					a1=(ArrayList<Products>) a1.stream()
					  .filter(prt->prt.getType().equals(type))
					  .collect(Collectors.toList());
					Collections.sort(a1,(h1,h2)->h1.getPrName().compareToIgnoreCase(h2.getPrName()));
					sess.setAttribute("asi", a1);
					request.setAttribute("type", type);
					RequestDispatcher rd = request.getRequestDispatcher("UserProducts.jsp");
					rd.forward(request, response);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Exception for UserProducts= "+ e1);
					d1.closeSession();
					RequestDispatcher rd = request.getRequestDispatcher("Wel.jsp");
					rd.include(request, response);
				}
				break;
			}
			
			
			case "DisplayProductSectionsUserHome":
			{
				try {	
					Set<String> s1 = new TreeSet<String>();
					s1.addAll((new DAO()).getProducts().stream().map(Products::getType).collect(Collectors.toSet()));
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
					d1 = new DAO();
					if(d1.ItemUpdating(id,prname,type,qta,price)) {
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
					a1 = new ArrayList<Products>();
					d1 = new DAO();
					a1=(ArrayList<Products>) d1.getProducts();
					a1=(ArrayList<Products>) a1.stream().filter(prt->prt.getType().equals(request.getParameter("type"))).collect(Collectors.toList());
					Collections.sort(a1);
					a1.forEach(System.out::println);					
					sess.setAttribute("asi", a1);
					request.setAttribute("type", request.getParameter("type"));
					RequestDispatcher rd = request.getRequestDispatcher("Products.jsp");
					rd.forward(request, response);
				} catch (Exception e1) {
					System.out.println("Exception at Home for sections= "+ e1);
					RequestDispatcher rd = request.getRequestDispatcher("Wel.jsp");
					rd.include(request, response);
				}
				break;
			}

			
			case "DisplayProductSections":
			{
				try {
					Set<String> s1 = new TreeSet<String>();
					s1.addAll((new DAO()).getProducts().stream().map(Products::getType).collect(Collectors.toSet()));
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
						RequestDispatcher rd = request.getRequestDispatcher("AdminLoginPage.jsp");
						rd.forward(request, response);
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
					d1 = new DAO();	
					u1 = d1.validateUser(u1);
					if(u1!=null) {
						double total=0.0;
						Shopping T = new Shopping();	 
						T.setTotal(total);
						sess.setAttribute("total", T);

						d1=new DAO();
						d1.ShoppingTruncate();
						
						sess.setAttribute("u1", u1);
						sess.setAttribute("session", "login");
						response.sendRedirect("welcome.jsp");//jsp	
					} else {
						request.setAttribute("msg", msg);
						RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
						rd.forward(request, response);
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
					d1 = new DAO();
					if(u1.getUsername()!=null) {
						u1=d1.SingupDAO(u1);
						RequestDispatcher re=request.getRequestDispatcher("Login.jsp");
						re.forward(request, response);
					} else {
						RequestDispatcher re=request.getRequestDispatcher("Sign-up.jsp");
						re.include(request, response);
					}
				} catch (SQLException e) {
					System.out.println(e);
					RequestDispatcher re=request.getRequestDispatcher("Sign-up.jsp");
					re.include(request, response);
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
							d1=new DAO();
							if(d1.ReplaceItems(s1))
								System.out.println("Repalced Item= "+s1);
							else 
								System.out.println("NOT Repalced Item= "+s1);
						}
					}

					d1=new DAO();
					d1.ShoppingTruncate();

		//			d1.HibernateSQLclose();
					sess.setAttribute("session", "logout");
					
					sess=request.getSession();
					sess.invalidate();

					System.out.println("logout");		
					
					response.sendRedirect("Login.jsp");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					try {
						d1=new DAO();
						d1.ShoppingTruncate();

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}