package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import db.DAO;
import model.Products;
import model.Shopping;
import model.User;

/**
 * Servlet implementation class SingleController
 */
@WebServlet("/SingleController")
public class SingleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private int hitCount; 
     public void init() 
     { 
     // Reset hit counter.
     hitCount = 0;
     } 
     

    public SingleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sess = request.getSession();

		if(sess.isNew()) {
			response.sendRedirect("SingleController?page=Logout");
		}else{
			System.out.println("Hits= "+ (++hitCount));
			
			String page = request.getParameter("page");
			System.out.println("page= "+page);
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
								if(status==true){
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
					// TODO Auto-generated catch block
					System.out.println("Exception at Replacing Item case");
					e.printStackTrace();
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
					// TODO Auto-generated catch block
					System.out.println("Exception at ShoppingClear case");
					e.printStackTrace();
				}
				break;
			}
			
			case "LatestCommander":
			{
				u1 = (User) sess.getAttribute("u1");
				String u = u1.getUsername();
		   		Shopping T = (Shopping) sess.getAttribute("total");
				int i;
				a1 = (ArrayList<Products>) sess.getAttribute("asi");
				int size=a1.size();
				try {
					for (i = 0; i < size; i++) {
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
					/*  Multiple Exceptions handled by single catch block from java 1.7  */
					// TODO: handle exception
					System.out.println(ex + " sagar pawar");
					response.sendRedirect("Wel.jsp");
					//e.printStackTrace();
				} 
				break;
			}
			
			
			case "SectionItemsListToUser":
			{
				String type = request.getParameter("type");			
				try {
					a1 = new ArrayList<Products>();
					d1=new DAO();
					//a1=d1.SectonItemsList(type);
					a1=(ArrayList<Products>) d1.getProducts();
					a1=(ArrayList<Products>) a1.stream()
					  .filter(prt->prt.getType().equals(type))
					  .collect(Collectors.toList());
					Collections.sort(a1,(h1,h2)->h1.getPrName().compareToIgnoreCase(h2.getPrName()));
					a1.forEach(System.out::println);					
					sess.setAttribute("asi", a1);
					System.out.println("get p");
					response.sendRedirect("UserProducts.jsp?type="+type);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("SQLException for UserProducts= "+ e1);
					response.sendRedirect("Wel.jsp");
				}
				break;
			}
			
			
			case "DisplayProductSectionsUserHome":
			{
				try {	
					Set<String> s1=(new DAO()).getProducts().stream().map(Products::getType).collect(Collectors.toSet());
					sess.setAttribute("sc", s1);
					response.sendRedirect("UserHome.jsp");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("SQLException at Home for sections= "+e);
					response.sendRedirect("Login.jsp");
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
					boolean status = d1.ItemUpdating(id,prname,type,qta,price);
					if(status == true) {
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
						boolean status = d1.ItemDeletion(id);
						if(status == true) {
							System.out.println("Deleted Product");
							response.sendRedirect("DeleteItem.jsp?msg=Deleted Product ID= "+id);
						}else{
							System.out.println("Deletion Failure");
							response.sendRedirect("DeleteItem.jsp?msg=Deletion Failure ID= "+id);
						}												
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Deletion Failure");
						response.sendRedirect("DeleteItem.jsp?msg=Deletion Failure ID= "+id);
					}
					break;
			}			
			
			
			case "SectionDeletion":
			{	
				String type = request.getParameter("a2");
				try {
					d1 = new DAO();
					boolean status = d1.SectionDeletion(type);
					if(status == true) {
						System.out.println("Deleted Section");
						response.sendRedirect("DeleteItem.jsp?msg=Deleted Section= "+type);
					}else{
						System.out.println("Deletion Failure");
						response.sendRedirect("DeleteItem.jsp?msg=Deletion Failure Section= "+type);
					}						
				} catch (SQLException e) {
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
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Insertion Failure");
					response.sendRedirect("InsertItem.jsp?msg=Insertion Failure Product= "+prname);
				}
				break;
			}
							
			
			case "SectionItemsList":
			{
				String type = request.getParameter("type");
				try {
					a1 = new ArrayList<Products>();
					d1 = new DAO();
					
					a1 = d1.SectonItemsList(type);
					
					sess.setAttribute("asi", a1);
					response.sendRedirect("Products.jsp?type="+type);
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("SQLException at Home for sections= "+ e1);
					response.sendRedirect("AdminHome.jsp");
				}
				break;
			}

			
			case "DisplayProductSections":
			{
				try {	
			
					a1 = new ArrayList<Products>();
					d1 = new DAO();
					a1 = d1.DPSections();
					sess.setAttribute("sc", a1);
					response.sendRedirect("Home.jsp");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("SQLException at DisplayProduct= "+e);
					response.sendRedirect("Wel.jsp");
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
					if(u1!=null) {
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
			
			case "MyController":
			{
				try {
					String user = request.getParameter("usname");
					String loginpass = request.getParameter("pass");
					System.out.println(user+"\t"+loginpass);
					u1.setUsername(user);
					u1.setPassword(loginpass);
					d1 = new DAO();	
					u1 = d1.validateUser(u1);
					if(u1.getUsername()!=null) {
						double total=0.0;
						Shopping T = new Shopping();	 
						T.setTotal(total);
						sess.setAttribute("total", T);

						d1=new DAO();
						d1.ShoppingTruncate();
						
						sess.setAttribute("u1", u1);
						response.sendRedirect("welcome.jsp");//jsp	
					} else {
						response.sendRedirect("Login.jsp?msg=Invalid User");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendRedirect("Login.jsp?msg=Invalid User");
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
					if(u1!=null) {
						u1=d1.SingupDAO(u1);
						RequestDispatcher re=request.getRequestDispatcher("Login.jsp");
						re.forward(request, response);
					} else {
						RequestDispatcher re=request.getRequestDispatcher("Sign-up.jsp");
						re.forward(request, response);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}	

			
			case "Logout":
			{
				try {			
					Shopping T = (Shopping) sess.getAttribute("total");
					if((T.getTotal())!=0){
						a2=(ArrayList<Shopping>) sess.getAttribute("shopping");
						Shopping[] p = new Shopping[a2.size()];
						for (Shopping s1 : a2) {
							d1=new DAO();
							boolean status=d1.ReplaceItems(s1);
							if(status==true){System.out.println("Repalced Item= "+s1);}
						}
					}
					
					d1=new DAO();
					d1.ShoppingTruncate();

//					d1.HibernateSQLclose();;
					
					sess=request.getSession();
					sess.invalidate();

					System.out.println("logout");		
					response.sendRedirect("Login.jsp");
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					try {
						d1=new DAO();
						d1.ShoppingTruncate();

//						d1.HibernateSQLclose();;

						sess=request.getSession();
						sess.invalidate();

						System.out.println("SQLException/NullPointerException= "+e);
						response.sendRedirect("Login.jsp");

					} catch (SQLException e1) {
						// TODO Auto-generated catch block

						e1.printStackTrace();
					}
				}				
				break;
			}
			
			
			case "Print-Bill":
			{
				try {				
					User u2 = (User) sess.getAttribute("u1");
			   		File Fb = new File("C:\\New folder\\Practice Section\\Advanced Java\\ShoppingCart\\Bills\\" + u2.getUsername() + ".txt");
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
						Shopping[] p = new Shopping[a2.size()];
						for (int i = 0; i < a2.size(); i++) {
							p[i] = new Shopping();
							p[i] = a2.get(i);
						}
						Pwb.println("\n\t\t\t\tShopping-Cart\n\n");
						Pwb.println("\tQtN\t*\tPrice\t=\tAmount\t=>\tItem\n");						
						for (int i = 0; i < a2.size(); i++) {
							Pwb.println("\t"+p[i].getQN() + "\t*\t" + p[i].getPrice() + "\t=\t " + p[i].getAmt() +"\t=>\t"+p[i].getPrName());
						}
						Pwb.println("\n\n\t\t\tTOTAL AMT is:=" + T.getTotal());						
					}
					Pwb.close();

					d1=new DAO();
					d1.ShoppingTruncate();

//					d1.HibernateSQLclose();;

					sess=request.getSession();
					sess.invalidate();

					System.out.println("logout");		
					response.sendRedirect("Login.jsp");
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("SQLException/NullPointerException= "+e);

					response.sendRedirect("Login.jsp");
				}
				break;
			}
			
			
			default:
				
				break;
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
