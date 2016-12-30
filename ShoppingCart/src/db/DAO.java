package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.TransientObjectException;
import controller.HibernateUtilities;
import model.Products;
import model.Shopping;
import model.User;

public class DAO {

	private Connection con = null;
	ConnectionPoolManager ConnectionPoolManager;
	public DAO() throws SQLException {									
//		ConnectionPoolManager = new ConnectionPoolManager();
//		con = ConnectionPoolManager.getConnectionFromPool();
//		System.out.println("Connected to DB");
	}
	
	public void HibernateSQLclose() throws SQLException
	{
		Session session = HibernateUtilities.getsSessionFactory().openSession();
		session.beginTransaction();		
		HibernateUtilities.getsSessionFactory().close();
		System.out.println("HibernateUtilities Closed...");
	}	

	public boolean ReplaceSingleItem(Shopping s1)
	{
		boolean b1=false;
		Session session = HibernateUtilities.getsSessionFactory().openSession();
		session.beginTransaction();

		String queryString = "from Products where PrName = :prname and Type = :type";
		  org.hibernate.Query query = session.createQuery(queryString);
		  query.setString("prname", s1.getPrName());						  
		  query.setString("type", s1.getType());						  
		  Object queryResult = query.uniqueResult();
		  Products p1 = (Products)queryResult;
		  System.out.println(p1.getQA());
		  p1.setQA(p1.getQA()+s1.getQN());
		  System.out.println("new= "+p1.getQA());
		  session.delete(s1);
		  session.update(p1);
		  b1=true;
		session.getTransaction().commit();
		session.close();
		System.out.println("Replace Item..");
		
		return b1;
	}
	
	public void ShoppingTruncate() throws SQLException
	{
		Session session = HibernateUtilities.getsSessionFactory().openSession();
		session.beginTransaction();

		org.hibernate.Query queryResult = session.createQuery("from Shopping");
		 java.util.List allUsers = queryResult.list(); 
		for (int i = 0; i < allUsers.size(); i++) {
			   Shopping user = (Shopping) allUsers.get(i);
			   session.delete(user);
		}
		session.getTransaction().commit();
		session.close();
		System.out.println("Truncate");
	}
	
	public ArrayList<Shopping> shoppingtable() throws SQLException
	{
		ArrayList<Shopping> a1 = new ArrayList<Shopping>();
		
		Session session = HibernateUtilities.getsSessionFactory().openSession();
		session.beginTransaction();

		org.hibernate.Query queryResult = session.createQuery("from Shopping");
		java.util.List allUsers;
		allUsers = queryResult.list(); 
		for (int i = 0; i < allUsers.size(); i++) {
			   Shopping user = (Shopping) allUsers.get(i);
			   System.out.println(user.getPrName());
			   a1.add(user);
		}
		session.getTransaction().commit();
		session.close();
		System.out.println("Show Shopping");	
		return a1;
	}
	
	
	public Shopping Commander(int n,int a,double p,String ft,String i,String u,double total) throws IOException {
		
		Shopping T = new Shopping();
		Session session = HibernateUtilities.getsSessionFactory().openSession();
		session.beginTransaction();
		try {
				if(n > 0) {
					if(n <= a) {
						total = total + (p * n);
						a-=n;
						String queryString = "from Products where PrName = :prname and Price = :price and Type = :type";
						  org.hibernate.Query query = session.createQuery(queryString);
						  query.setString("prname", i);						  
						  query.setDouble("price", p);
						  query.setString("type", ft);
						  Object queryResult = query.uniqueResult();

						  Products p1 = (Products)queryResult;
						  System.out.println(p1.getQA());
						  p1.setQA(a);
						  System.out.println(p1.getQA());
						  session.update(p1);
						  System.out.println("Product updated...");  
							try {
								  Shopping s1= new Shopping();
								  query = session.createQuery("from Shopping where PrName = :prname and Type = :type");
									java.util.List allUsers;			  
									query.setString("prname", i);
									query.setString("type", ft);
									allUsers = query.list();
									for (int j = 0; j < allUsers.size(); j++) {
										s1 = (Shopping) allUsers.get(j);
										System.out.println("shopping table item: "+s1.getPrName());
									}
									if(s1.getPrName() != null) {
										s1.setPrName(i);
										s1.setQN(n+s1.getQN());
										s1.setPrice(p);
										s1.setType(ft);
										s1.setAmt((p*n)+s1.getAmt());
										session.update(s1);
										System.out.println("Update in shopping Table..");							
									} else {
										  s1.setPrName(i);
										  s1.setQN(n);
										  s1.setPrice(p);
										  s1.setType(ft);
										  s1.setAmt(p*n);
										  System.out.println(s1.getAmt());
										  session.save(s1);
										  System.out.println("Inserted Shop Item...\n");
									}						  
							}catch(TransientObjectException e){
								  Shopping s1= new Shopping();
								  s1.setPrName(i);
								  s1.setQN(n);
								  s1.setPrice(p);
								  s1.setType(ft);
								  s1.setAmt(p*n);
								  System.out.println(s1.getAmt());
								  session.save(s1);
								  System.out.println("Inserted Shop Item by QuerySyntaxException catch clause... \n" + e);
							}									  
							session.getTransaction().commit();
							session.close();
						
					}else{
//						Pw.println(i + ": " + n + " But now here available only this QtA ("+ a +")");
					}
				}else if(a==0) {
//					Pw.println(i+": Already SOLD-OUT");
				}else {
//					Pw.println(i+": " + n + " is Invalid, Please enter in-bet(0-" + a + ")");			
				}
				T.setTotal(total);
				return T;
		}catch (Exception e) {
		// TODO Auto-generated catch block
			System.out.println("sagar pawar " + e);
			e.printStackTrace();
		}
		return T;	
	}	
	
	
	
	
	public String ItemInserting(String prname,String type,int qta,String price) throws SQLException {
		String status=null;
		try {
			Products p1= null;
			Session session = HibernateUtilities.getsSessionFactory().openSession();
			session.beginTransaction();
			org.hibernate.Query queryResult = session.createQuery("from Products where PrName = :prname and type = :type");
			java.util.List allUsers;			  
			queryResult.setString("prname", prname);
			queryResult.setString("type", type);
			allUsers = queryResult.list(); 
			for (int i = 0; i < allUsers.size(); i++) {
				p1 = (Products) allUsers.get(i);
				System.out.println(p1.getId());
			}
			if(p1 != null) {
				p1.setQA(qta + p1.getQA());
				p1.setPrice(Double.parseDouble(price));
				session.update(p1);
				status="UPDATE";
				System.out.println(status);							
			} else {
				ConnectionPoolManager = new ConnectionPoolManager();
				con = ConnectionPoolManager.getConnectionFromPool();

				System.out.println("Connected to DB");
				
				Statement stmt = con.createStatement();
				String query="INSERT INTO products (`prname`, `qa`, `price`, `type`) VALUES ('" + prname + "', '" + qta + "', '" + price + "', '" + type + "');";
				stmt.executeUpdate(query);			
				status="INSERT";
				System.out.println(status);			

//				p1.setPrName(prname);
//				p1.setQA(qta);
//				p1.setPrice(Double.parseDouble(price));
//				p1.setType(type);
//				session.save(p1);

				status="INSERT";
				System.out.println(status);			
			}
			session.getTransaction().commit();
			session.close();

			return status;
		} catch (Exception e) {
			// TODO: handle exception
			return status;
		}
	}

	
	public boolean ItemUpdating(String id,String prname,String type,String qta,String price) throws SQLException {
		boolean status = false;
		try {
			Session session = HibernateUtilities.getsSessionFactory().openSession();
			session.beginTransaction();

			Products p1= new Products();
			p1.setId(Integer.parseInt(id));
			p1.setPrName(prname);
			p1.setQA(Integer.parseInt(qta));
			p1.setPrice(Double.parseDouble(price));
			p1.setType(type);
			session.update(p1);
			status=true;
			System.out.println("Updated..");
			session.getTransaction().commit();
			session.close();
		
			return status;
		} catch(Exception e) {
			return status;	
		}		
	}

	
	public boolean ItemDeletion(String id) {
		boolean status=false;
		try {
			Session session = HibernateUtilities.getsSessionFactory().openSession();
			session.beginTransaction();
			
			String queryString = "from Products where Id = :id";
			  org.hibernate.Query query = session.createQuery(queryString);
			  query.setString("id", id);
			  Object queryResult = query.uniqueResult();
			  Products p1 = (Products)queryResult;
			  System.out.println(p1.getPrName());
			  session.delete(p1);
			  System.out.println("Deleted Item...");
				status=true;

				session.getTransaction().commit();
				session.close();
			  
			return status;
		} catch (Exception e) {
			// TODO: handle exception
			return status;		
		}
	}
	
	
	public boolean SectionDeletion(String type) {
		boolean status=false;
		try {
			Session session = HibernateUtilities.getsSessionFactory().openSession();
			session.beginTransaction();
			
			Query queryResult = session.createQuery("FROM Products WHERE Type = :type");
			queryResult.setString("type", type);
			java.util.List allPr;
			allPr = queryResult.list(); 
			for (int i = 0; i < allPr.size(); i++) {
				Products p1 = (Products) allPr.get(i);
				System.out.println(p1);
				session.delete(p1);
			}
			status=true;
			
			System.out.println("Deleted Section...");

			session.getTransaction().commit();
			session.close();
			
			return status;
		} catch (Exception e) {
			// TODO: handle exception
			return status;		
		}
	}

	
	
	
	public ArrayList<Products> DPSections() throws SQLException {
		ArrayList<Products> a1 = new ArrayList<Products>();
		
		Session session = HibernateUtilities.getsSessionFactory().openSession();
		session.beginTransaction();
		org.hibernate.Query queryResult = session.createQuery("from Products group by type order by id");
		java.util.List allUsers;
		allUsers = queryResult.list(); 
		for (int i = 0; i < allUsers.size(); i++) {
			Products user = (Products) allUsers.get(i);
			   System.out.println(user.getType());
			   a1.add(user);
		}
		session.getTransaction().commit();
		session.close();
		
		return a1;
	}

	public boolean RShopItemUpdating(int id,String prname,String type,int qta,double price) throws SQLException {
		boolean status = false;
		try {
			Session session = HibernateUtilities.getsSessionFactory().openSession();
			session.beginTransaction();

			Products p1=new Products();
			p1.setId(id);
			p1.setPrName(prname);
			p1.setQA(qta);
			p1.setPrice(price);
			p1.setType(type);
			
			session.update(p1);
			status=true;			
	
			session.getTransaction().commit();
			session.close();
//			HibernateUtilities.getsSessionFactory().close();
		} catch (Exception e) {
			// TODO: handle exception
			status = false;
		}

		return status;	
	}

	
	public boolean ReplaceItems(Shopping s1) throws SQLException {
		ArrayList<Products> a1 = new ArrayList<Products>(); 
		boolean status = false;
		a1=SectonItemsListfR(s1.getType());
		Products[] p = new Products[a1.size()];
		for (int i = 0; i < a1.size(); i++) {
			if(a1.get(i)!=null) {
				p[i] = new Products();
				p[i] = a1.get(i);
				if(s1.getPrName().equals(p[i].getPrName())){
					status=RShopItemUpdating(p[i].getId(), s1.getPrName(), s1.getType(), p[i].getQA()+s1.getQN(), p[i].getPrice());
					return status;
				}
			}
		}
		con.close();
		return status;	
	}	
	
	public ArrayList<Products> SectonItemsListfR(String type) throws SQLException {
		ArrayList<Products> a1 = new ArrayList<Products>(); 
		
		Session session = HibernateUtilities.getsSessionFactory().openSession();
		session.beginTransaction();
		org.hibernate.Query queryResult = session.createQuery("from Products where type = :type");
		java.util.List allUsers;			  
		queryResult.setString("type", type);
		allUsers = queryResult.list(); 
		for (int i = 0; i < allUsers.size(); i++) {
			Products user = (Products) allUsers.get(i);
			   a1.add(user);
		}
		session.getTransaction().commit();
		session.close();
		return a1;
	}	
	
	
	public ArrayList<Products> SectonItemsList(String type) throws SQLException {
		ArrayList<Products> a1 = new ArrayList<Products>(); 

		Session session = HibernateUtilities.getsSessionFactory().openSession();
		session.beginTransaction();
		org.hibernate.Query queryResult = session.createQuery("from Products where type = :type");
		java.util.List allUsers;			  
		queryResult.setString("type", type);
		allUsers = queryResult.list(); 
		for (int i = 0; i < allUsers.size(); i++) {
			Products user = (Products) allUsers.get(i);
			   System.out.println(user.getPrName());
			   a1.add(user);
		}
		return a1;
	}	
	

	public User SingupDAO(User u1) {
		try {
			Session session = HibernateUtilities.getsSessionFactory().openSession();
			session.beginTransaction();
			
			session.save(u1);
			
			session.getTransaction().commit();
			session.close();
//			HibernateUtilities.getsSessionFactory().close();		
		} catch (Exception e) {
			e.printStackTrace();
			u1=null;
		}finally {
//			HibernateUtilities.getsSessionFactory().close();
			System.out.println("HibernateUtilities closed from SingupDAO function by finally");
		}
		return u1;
	}
	
	public User  validateUser(User u1) {			

		try {		
			Session session = HibernateUtilities.getsSessionFactory().openSession();
			session.beginTransaction();
			
			String queryString = "from User where username = :username and password = :pass";
			org.hibernate.Query query = session.createQuery(queryString);
			query.setString("username", u1.getUsername());
			query.setString("pass", u1.getPassword());
			Object queryResult = query.uniqueResult();
			u1 = (User)queryResult;	
			System.out.println("validateUser..");
			session.getTransaction().commit();
			session.close();
//			HibernateUtilities.getsSessionFactory().close();
			return u1;
		} catch (Exception e) {
			e.printStackTrace();
			u1=null;
		}finally {
//			HibernateUtilities.getsSessionFactory().close();
//			System.out.println("HibernateUtilities closed from validate-user function by finally");
		}
		return u1;
	}
}