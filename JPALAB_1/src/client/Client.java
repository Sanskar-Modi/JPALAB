package client;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.Author;
import entity.Greet;

public class Client {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-PU");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Greet greet = new Greet();
		greet.setMessage("Welcome to JPA!");

		em.persist(greet);

		System.out.println("Added greeting to database.");

		em.getTransaction().commit();

		while (true) {
			System.out.println("-----------Author details Application-----------------");
			System.out.println("1 to Insert Author details");
			System.out.println("2 to Update");
			System.out.println("3 to Delete ");
			System.out.println("4 to Show Details");
			System.out.println("5 to Exit");
			System.out.println("Enter Choice");
			int choice = sc.nextInt();

			switch (choice) {
			// insert
			case 1:
				
				System.out.println("Enter author Id");
				int authId = sc.nextInt();

				System.out.println("Enter First Name");
				String fName = sc.next();
				fName += sc.nextLine();

				System.out.println("Enter Middle Name");
				String midName = sc.nextLine();

				System.out.println("Enter Last Name");
				String lName = sc.nextLine();

				System.out.println("Enter phone Number");
				long phoneNo = sc.nextLong();

				Author auth = new Author();

				auth.setAuthorId(authId);
				auth.setFirstName(fName);
				auth.setMiddleName(midName);
				auth.setLastName(lName);
				auth.setPhoneNo(phoneNo);

				em.getTransaction().begin();
				em.persist(auth);
				System.out.println("Added autor details into database");
				em.getTransaction().commit();

				break;

			case 2:
				// update

				System.out.println("Enter author Id to update ");
				authId = sc.nextInt();

				em.getTransaction().begin();
				auth = (Author) em.find(Author.class, authId);
				System.out.println("------Before Updating---------");
				System.out.println("Author Id : "+auth.getAuthorId());
				System.out.println("Author First Name : "+auth.getFirstName());
				System.out.println("Author Middle Name : "+auth.getMiddleName());
				System.out.println("Author Last Name : "+auth.getLastName());
				System.out.println("Author Phone No : "+auth.getPhoneNo());
				if (auth != null) {
					System.out.println("1 to update first name");
					System.out.println("2 to update middle name");
					System.out.println("3 to update last name");
					System.out.println("4 to update phone");
					System.out.println("Enter choice");
					choice = sc.nextInt();

					switch (choice) {

					case 1:
						System.out.println("Enter new first name");
						String name = sc.next();
						auth.setFirstName(name);
						em.persist(auth);
						System.out.println("Updated");
						em.getTransaction().commit();
						break;
					case 2:
						System.out.println("Enter new middle name");
						name = sc.next();
						auth.setMiddleName(name);
						em.persist(auth);
						System.out.println("Updated");
						em.getTransaction().commit();
						break;
					case 3:
						System.out.println("Enter new last name");
						name = sc.next();
						auth.setLastName(name);
						em.persist(auth);
						System.out.println("Updated");
						em.getTransaction().commit();
						break;
					case 4:
						System.out.println("Enter new phone no");
						long no = sc.nextLong();
						auth.setPhoneNo(no);
						em.persist(auth);
						System.out.println("Updated");
						em.getTransaction().commit();
						break;
					}
				}
				else if(auth==null) {
					System.out.println("No record found");
				}

				break;

			case 3:
				// delete

				System.out.println("Enter author Id to delete ");
				authId = sc.nextInt();

				em.getTransaction().begin();
				auth = (Author) em.find(Author.class, authId);
				em.remove(auth);
				em.getTransaction().commit();
				break;

			case 4:
				// to show details
				System.out.println("Enter author Id to get details ");
				authId = sc.nextInt();

				em.getTransaction().begin();
				auth = (Author) em.find(Author.class, authId);
				System.out.println("------Author Details---------");
				System.out.println("Author Id : "+auth.getAuthorId());
				System.out.println("Author First Name : "+auth.getFirstName());
				System.out.println("Author Middle Name : "+auth.getMiddleName());
				System.out.println("Author Last Name : "+auth.getLastName());
				System.out.println("Author Phone No : "+auth.getPhoneNo());
				break;
				
				
			case 5 :
				// exit
				System.out.println("Thank You for Using Application");
				System.exit(0);
				
				return;
				
			}
		}
	}
}
