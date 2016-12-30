package controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import model.Products;
import model.Shopping;
import model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


class StringRev
{
	
	public StringRev(String str)
	{
		String rev="", rev1="";
		for(int i=(str.length()-1);i>=0;i--){
			rev+=(str.charAt(i));
		}
		System.out.println("StringRev(reverse char's)= "+rev.replaceAll("\\s", "").trim().replace("a", "A"));
		System.out.println("SubString(After 5 char's)= "+rev.substring(5));
		
		rev=" ";
		int i=0;
		while(str.charAt(i)!=' '){
			rev+=(str.charAt(i++));
		}
		i++;
		for(;i<str.length();i++){
			rev1+=str.charAt(i);	
		}
		System.out.println(rev1+rev);
	
	}
	
	public void strTOint(String a){
		int j=0;
		for(int i=0;i<a.length();i++){
			j*=10;
			j+=((a.charAt(i))-48);
		}
		System.out.println(j+1);
	}
	
	public void ASCII()
	{
		int j;
		for(char i='0'; i<='9';i++){
			j=i;
			System.out.println(i+"= "+j);			
		}
		System.out.println();		
		for(char i='A'; i<='Z';i++){
			j=i;
			System.out.println(i+"= "+j);
		}
		System.out.println();
		for(char i='a'; i<='z';i++){
			j=i;
			System.out.println(i+"= "+j);			
		}
		
		char i=' ';
		j=i;
		System.out.println("\nspace= "+j);
	}
	
	
	
	public static void  main1(String[] args) {	
		StringRev s1= new StringRev("sagar pawar");			//str Rev	
		
		s1.strTOint("1023");								//str TO int
		s1.ASCII();
	}
}
class abc
{
}

interface IAG
{
	int j=10;
	void draw(); 
	int draw1();	
}
interface IAD
{
	int j=10;
	void draw(); 
	int draw1();	
}

interface IAC extends IAD, IAG
{
	int i=10;
	void draw(); 
	int draw1();	
}
abstract class Inf extends abc implements IAC, IAD
{

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("draw");
		
	}

	@Override
	public int draw1() {
		// TODO Auto-generated method stub
		System.out.println("draw1");		

		return i;
	}	
}
class Idea extends Inf
{
	public static void main(String [] args) {
		
		Inf c=new Idea();
		c.draw();
		System.out.println(c.draw1());	
		
		boolean b=true;
		int i=1;
		String str="";
		char c1=' ';
		System.out.println(c1);
		while(b){System.out.println("1"); b=false;}
		
		if(c1==32){
			System.out.println("Hi");			
		}else{
			System.out.println("Bye");
		}	
	}

}


class Mythread extends Thread {

	public void run() {
		System.out.println(" r1 ");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println(" r2 ");
	}

	public Connection getCon() throws SQLException{
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/test";
			con=DriverManager.getConnection(url,"root","root");
			System.out.println("connected..\n");
			return con;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}


public class Test {

	public static void main16(String[] args) throws SQLException {

		Mythread m1=new Mythread();
		Connection con=m1.getCon();
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from user");
		while(rs.next()){
			System.out.println(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3));
		}
	}
	
	
	public static void main3(String[] args) {
	
		Mythread mt1 = new Mythread();
		Mythread mt2 = new Mythread();
		
		mt1.start();
		try {
			mt1.join(500);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}

		mt2.start();

		
//		System.out.println(mt1.isAlive());
//		System.out.println(mt2.isAlive());
		
		
		
		//		Thread t=new Thread(mt);
//		t.start();
//		System.out.println("Name of the Thread is " + t);
				
	}
		
	public static void main161(String[] args) {

		String str=" Sagar Pawar ";

		String st1=new String(" Sagar Pawar ");
		boolean s2=st1.equals(str);
		System.out.println("equals= "+s2);
		
		boolean s1=str.equals(str);
		System.out.println("equals= "+s1);
		
		char r8=str.charAt(5);
		System.out.println("charAt= "+r8);

		int r7=str.length();
		System.out.println("length= "+r7);
		
		String r6=str.toUpperCase();
		System.out.println("toUpperCase= "+r6);

		int s8=r6.compareTo(str);
		System.out.println("compareTo= "+s8);

		String r5=str.toString();
		System.out.println("toString= "+r5);
		
		String r4=str.toLowerCase();
		System.out.println("toLowerCase= "+r4);
		
		String r3=str.substring(7);
		System.out.println("substring= "+r3);
		
		String r2=str.concat(str);
		System.out.println("concat= "+r2);
		
		String r1=str.replace("g", "G");
		System.out.println("replace= "+r1);
		
		str=" I ask to something you ";
		str=str.replaceAll("\\s", "").trim();
		System.out.println("replaceAll&trim= "+str);
					
}
	public static void main2(String[] args) {

		Session session = HibernateUtilities.getsSessionFactory().openSession();
		session.beginTransaction();
		
			
//		User loadedUser=(User)session.get(User.class, "monali");
			//get and load function are same 
			//the only difference is get() returns null if data not found
			//the load() returns exception if data not found
		
		//System.out.println(session.getClass());
			
//		Query q1= (Query) session.createQuery("from user");
//		List<User> li= ((org.hibernate.Query) q1).list();
//		Iterator<User> It=li.iterator();
//		while(It.hasNext()) {
//			System.out.println(It.next().getCity());			
//		}
		
		//		ArrayList<User> a1=new ArrayList<User>(q1.hashCode());
//		System.out.println("User: "+0);
//		System.out.println(a1.get(0).getUsername());
//		System.out.println(a1.get(0).getPassword());
//		System.out.println(a1.get(0).getCity());

//		while(session.getClass()!=null){
//			session.g
//						a1.add(session.getClass());
//		}
//		
//		for(int i=0; i<li.size(); i++) {
//			System.out.println("User: "+i);
//			System.out.println(li.get(i).getUsername());
//			System.out.println(li.get(i).getPassword());
//			System.out.println(li.get(i).getCity());
//		}
				
//		System.out.println(loadedUser.getUsername());
//		System.out.println(loadedUser.getPassword());
//		System.out.println(loadedUser.getCity());
			
		session.getTransaction().commit();
		session.close();
		HibernateUtilities.getsSessionFactory().close();
	}

	
	public static void main(String[] args) {

		ArrayList<Shopping> a1 = new ArrayList<Shopping>();		
		System.out.println("Show Shopping table");

		Session session = HibernateUtilities.getsSessionFactory().openSession();
		session.beginTransaction();

		List<Products> allUsers = session.createQuery("from Products group by type").list();
		for (Products user:allUsers) {
			   System.out.println(user.getType());
			   //a1.add(user);
		}
		session.getTransaction().commit();
		session.close();
		HibernateUtilities.getsSessionFactory().close();
		System.out.println("Show Shopping");

		
		
//		Session session = HibernateUtilities.getsSessionFactory().openSession();
//		session.beginTransaction();
//		
//		Shopping loadedUser=(Shopping)session.get(Shopping.class, 15);
//
////		System.out.println(loadedUser.getId());			
//		System.out.println(loadedUser.getPrName());
//		System.out.println(loadedUser.getQA());
//		System.out.println(loadedUser.getPrice());
//		System.out.println(loadedUser.getType());
//			
//		session.getTransaction().commit();
//		session.close();
//		HibernateUtilities.getsSessionFactory().close();
	}

	
	public static void main1(String[] args) {

		try {
			Session session = HibernateUtilities.getsSessionFactory().openSession();
			session.beginTransaction();

//			Query queryResult = (Query) session.createQuery("from User");
//			  java.util.List allUsers;
//			  allUsers = ((org.hibernate.Query) queryResult).list();
//			  for (int i = 0; i < allUsers.size(); i++) {
//			   User user = (User) allUsers.get(i);
//			   System.out.println(user);
//			  }
//			 System.out.println("Database contents delivered..."); 

			
//				User loadedUser=(User)session.get(User.class, "sagar");
				//get and load function are same 
				//the only difference is get() returns null if data not found
				//the load() returns exception if data not found
//			  String queryString = "from User where id = :id";

			
			
//			String queryString = "from Products where PrName =medimix and Price =40 and Type =soap";
//			  org.hibernate.Query query = session.createQuery(queryString);
//			  query.setString("prname", "medimix");
//			  query.setString("price", "40");
//			  query.setString("type", "soap");
//			  Object queryResult = query.uniqueResult();
//			  Products u1 = (Products)queryResult;
//			  System.out.println(u1.getQA());
//			  u1.setQA(u1.getQA()-2);
//			  System.out.println(u1.getQA());
//			   session.update(u1);

//			  Shopping s1= new Shopping();
//			  s1.setPrName("medimix");
//			  s1.setQN(2);
//			  s1.setPrice(40);
//			  s1.setType("soap");
//			  s1.setAmt(80);
//			  System.out.println(s1.getAmt());
//			  session.save(s1);
			

//			Products p1=new Products();
//			p1.setId(37);
//			p1.setPrName("medimix");
//			p1.setQA(20);
//			p1.setPrice(40);
//			p1.setType("soap");
//		   session.update(p1);
//		   "UPDATE products SET `qa` = " + a + " WHERE `prname` = '" + i + "' and `type` = '" + ft + "' and `price` = '" + p + "';";

			
//			"SELECT * FROM shopping_cart.products where prname='"+prname+"' and type='"+type+"';");
//			"UPDATE products SET `qa`='"+(qta+rs.getInt(3))+"', `price`='"+price+"' WHERE `id`='"+rs.getInt(1)+"';";
//			query="UPDATE `shopping_cart`.`products` SET `qa`='"+(qta+rs.getInt(3))+"', `price`='"+price+"' WHERE `id`='"+rs.getInt(1)+"';";
//			stmt.executeUpdate(query);											

			Products user=null;
			org.hibernate.Query queryResult = session.createQuery("from Products where PrName = :prname and type = :type");
			java.util.List allUsers;			  
			queryResult.setString("prname", "develop");
			queryResult.setString("type", "Sagar");
			allUsers = queryResult.list(); 
			for (int i = 0; i < allUsers.size(); i++) {
				user = (Products) allUsers.get(i);
				   System.out.println(user.getId());
				   //a1.add(user);
			}
			if(user!=null){
				System.out.println(user.getId());
			}else {
				System.out.println(" user null else...");
			}
			   
			
			
//			Shopping loadedUser=(Shopping)session.get(Shopping.class, 15);
//			
//					System.out.println(loadedUser.getId());			
//					System.out.println(loadedUser.getPrName());
//					System.out.println(loadedUser.getQA());
//					System.out.println(loadedUser.getPrice());
//					System.out.println(loadedUser.getType());

			
//			 org.hibernate.Query queryResult = session.createQuery("from Products");
//			  java.util.List allUsers;
//			  allUsers = queryResult.list();
//			  for (int i = 0; i < allUsers.size(); i++) {
//			   Shopping user = (Shopping) allUsers.get(i);
//			   System.out.println(user.getPrName());
//			  }
//			 System.out.println("Database contents delivered...");		
			
			
			
//			List<Shopping> stlist=(List<Shopping>) ((SharedSessionContract) session.getSessionFactory()).createSQLQuery("from products").list();
//			System.out.println(stlist.get(0).getPrName());

//				Query q1= (Query) session.createQuery("select * from user");
//				List<Shopping> li= q1.list();
//				Iterator< Shopping> It=li.iterator();
//				while(It.hasNext()) {
//					System.out.println(It.next());			
//				}
//				
				
				
//				System.out.println(loadedUser.getUsername());
//				System.out.println(loadedUser.getPassword());
//				System.out.println(loadedUser.getCity());
				
			session.getTransaction().commit();
			session.close();
			HibernateUtilities.getsSessionFactory().close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			HibernateUtilities.getsSessionFactory().close();			
		}
	}
	
	
	public static void main8(String[] args) {
		// TODO Auto-generated method stub

		//get the sesion and open it 
		Session session = HibernateUtilities.getsSessionFactory().openSession();

		//transaction must be started 
		session.beginTransaction();

		//creating object for transaction
		User u1 = new User();
		//setting needed values for object
		u1.setUsername("uxyz");
		u1.setPassword("pxyz");
		u1.setCity("cxyz");

		//persisting the object in database
		session.save(u1);

		//commit db
		session.getTransaction().commit();
		
		//release the resources 
		session.close();
		HibernateUtilities.getsSessionFactory().close();
	
	}
}