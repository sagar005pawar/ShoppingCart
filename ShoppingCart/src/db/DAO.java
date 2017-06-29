package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransientObjectException;
import controller.HibernateUtilities;
import model.Products;
import model.Shopping;
import model.User;

public class DAO {

	private Connection con = null;
	private Session session = null;
	private ConnectionPoolManager ConnectionPoolManager;
	

	public DAO() throws SQLException {									
//		ConnectionPoolManager = new ConnectionPoolManager();
//		con = ConnectionPoolManager.getConnectionFromPool();
//		System.out.println("Connected to DB");
	}
	
	public void closeSession(){
		if(this.session.isConnected()){
			this.session.getTransaction().commit();
			this.session.close();
			System.out.println("Session closed..");
		}else{
			System.out.println("Session already Disabled..");
		}
		
	}
	
	public void exceptional(){
		if(this.session.isConnected()){
			this.session.getTransaction().commit();
			this.session.close();
			System.out.println("Session closed..");
			HibernateUtilities.getsSessionFactory().close();
			System.out.println("Hibernate SessionFactory closed..");
		} else {
			HibernateUtilities.getsSessionFactory().close();
			System.out.println("Hibernate SessionFactory closed..");
		}
	}
	
	public void HibernateSQLclose() throws SQLException
	{
		try {
			session = HibernateUtilities.getsSessionFactory().openSession();
			session.beginTransaction();
		} catch (Exception e) {
			exceptional();
			System.err.println(e);
		} finally {
			closeSession();
		}		
	}	

	public boolean ReplaceSingleItem(Shopping s1)
	{
		boolean b1=false;
		session = HibernateUtilities.getsSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
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
			System.out.println("Replace Item..");
		} catch (Exception e) {
			exceptional();
			System.err.println(e);
		} finally {
			closeSession();
		}		
		return b1;
	}
	
	public void ShoppingTruncate() throws SQLException
	{
		try {
			session = HibernateUtilities.getsSessionFactory().openSession();
			session.beginTransaction();
	
			org.hibernate.Query queryResult = session.createQuery("from Shopping");
			List<Shopping> allUsers = queryResult.list(); 
			for (Shopping user : allUsers) {
				   session.delete(user);
			}
			System.out.println("Truncate");
		} catch (Exception e) {
			exceptional();
			System.err.println(e);
		} finally {
			closeSession();
		}		
	}
	
	public ArrayList<Shopping> shoppingtable() throws SQLException
	{
		ArrayList<Shopping> a1 = new ArrayList<Shopping>();
		
		try {
			session = HibernateUtilities.getsSessionFactory().openSession();
			session.beginTransaction();
	
			org.hibernate.Query queryResult = session.createQuery("from Shopping");
			a1 = (ArrayList<Shopping>) queryResult.list(); 
			System.out.println("Show Shopping");	
		} catch (Exception e) {
			exceptional();
			System.err.println(e);
		} finally {
			closeSession();
		}		
		return a1;
	}
	
	
	public Shopping Commander(int n,int a,double p,String ft,String i,String u,double total) throws IOException {
		
		Shopping T = new Shopping();
		session = HibernateUtilities.getsSessionFactory().openSession();
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
		} catch (Exception e) {
			exceptional();
			System.err.println(e);
		} finally {
			closeSession();
		}		
		return T;	
	}	
	
	
	
	
	public String ItemInserting(String prname,String type,int qta,String price) throws SQLException {
		String status=null;
		try {
			Products p1= null;
			session = HibernateUtilities.getsSessionFactory().openSession();
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
		} catch (Exception e) {
			exceptional();
			System.err.println(e);
		} finally {
			closeSession();
		}		
		return status;
	}

	
	public boolean ItemUpdating(String id,String prname,String type,String qta,String price) throws SQLException {
		boolean status = false;
		try {
			session = HibernateUtilities.getsSessionFactory().openSession();
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
		} catch (Exception e) {
			exceptional();
			System.err.println(e);
		} finally {
			closeSession();
		}		
		return status;
	}

	
	public boolean ItemDeletion(String id) {
		boolean status=false;
		try {
			session = HibernateUtilities.getsSessionFactory().openSession();
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
		} catch (Exception e) {
			exceptional();
			System.err.println(e);
		} finally {
			closeSession();
		}		
		return status;
	}
	
	
	public boolean SectionDeletion(String type) {
		boolean status=false;
		try {
			session = HibernateUtilities.getsSessionFactory().openSession();
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
		} catch (Exception e) {
			exceptional();
			System.err.println(e);
		} finally {
			closeSession();
		}		
		return status;
	}

	
	
	
	public ArrayList<Products> DPSections() throws SQLException {
		ArrayList<Products> a1 = new ArrayList<Products>();
		try {		
			session = HibernateUtilities.getsSessionFactory().openSession();
			session.beginTransaction();
			a1.addAll((ArrayList<Products>)(session.createQuery("from Products group by type order by id")).list());
		} catch (Exception e) {
			exceptional();
			System.err.println(e);
		} finally {
			closeSession();
		}		
		return a1;
	}

	public boolean RShopItemUpdating(int id,String prname,String type,int qta,double price) throws SQLException {
		boolean status = false;
		try {
			session = HibernateUtilities.getsSessionFactory().openSession();
			session.beginTransaction();
			Products p1=new Products(id, prname, qta, price, type);
			session.update(p1);
			status=true;				
		} catch (Exception e) {
			exceptional();
			System.err.println(e);
		} finally {
			closeSession();
		}		
		return status;	
	}

	
	public boolean ReplaceItems(Shopping s1) throws SQLException {
		ArrayList<Products> a1 = new ArrayList<Products>(); 
		boolean status = false;
		for (Products p:SectonItemsListfR(s1.getType())) {
			if(p!=null) {
				if(s1.getPrName().equals(p.getPrName())){
					status=RShopItemUpdating(p.getId(), s1.getPrName(), s1.getType(), p.getQA()+s1.getQN(), p.getPrice());
					return status;
				}
			}
		}
		con.close();
		return status;	
	}	
	
	public ArrayList<Products> SectonItemsListfR(String type) throws SQLException {
		ArrayList<Products> a1 = new ArrayList<Products>(); 
		try{
			session = HibernateUtilities.getsSessionFactory().openSession();
			session.beginTransaction();
			org.hibernate.Query queryResult = session.createQuery("from Products where type = :type");
			queryResult.setString("type", type);
			a1 = (ArrayList<Products>) queryResult.list(); 			
		} catch (Exception e) {
			exceptional();
			System.err.println(e);
		} finally {
			closeSession();
		}				
		return a1;
	}	
	
	
	public ArrayList<Products> SectonItemsList(String type) throws SQLException {
		ArrayList<Products> a1 = new ArrayList<Products>(); 
		try {
			session = HibernateUtilities.getsSessionFactory().openSession();
			session.beginTransaction();
			org.hibernate.Query queryResult = session.createQuery("from Products where type = :type");
			queryResult.setString("type", type);
			a1 = (ArrayList<Products>) queryResult.list(); 			
		} catch (Exception e) {
			exceptional();
			System.err.println(e);
		} finally {
			closeSession();
		}		
		return a1;
	}	
	

	public User SingupDAO(User u1) {
		try {
			session = HibernateUtilities.getsSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(u1);;			
		} catch (Exception e) {
			exceptional();
			System.err.println(e);
		} finally {
			closeSession();
		}		
		return u1;
	}
	
	public User  validateUser(User u1) {			
		try {		
			session = HibernateUtilities.getsSessionFactory().openSession();
			session.beginTransaction();			
			String queryString = "from User where username = :username and password = :pass";
			org.hibernate.Query query = session.createQuery(queryString);
			query.setString("username", u1.getUsername());
			query.setString("pass", u1.getPassword());
			Object queryResult = query.uniqueResult();
			System.out.println("validateUser..");
			return ((User)queryResult);
		} catch (Exception e) {
			exceptional();
			System.err.println(e);
		} finally {
			closeSession();
		}		
		return u1;
	}
	
	
	public List<Products> getProducts() {
		ArrayList<Products> a1 = new ArrayList<Products>();
		try {
			session = HibernateUtilities.getsSessionFactory().openSession();
			session.beginTransaction();
			org.hibernate.Query queryResult = session.createQuery("from Products");
			a1 = (ArrayList<Products>) queryResult.list(); 
		} catch (Exception e) {
			exceptional();
			System.err.println(e);
		} finally {
			closeSession();
		}		
		return a1;		
	}
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public ConnectionPoolManager getConnectionPoolManager() {
		return ConnectionPoolManager;
	}

	public void setConnectionPoolManager(ConnectionPoolManager connectionPoolManager) {
		ConnectionPoolManager = connectionPoolManager;
	}

	
}