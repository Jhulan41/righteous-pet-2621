package com.studyon.usecases;

import java.util.List;
import java.util.Scanner;

import com.studyon.dao.AdminDao;
import com.studyon.dao.AdminDaoImpl;
import com.studyon.dao.BatchDao;
import com.studyon.dao.BatchDaoImpl;
import com.studyon.dao.CourseDao;
import com.studyon.dao.CourseDaoImpl;
import com.studyon.dao.StudentDao;
import com.studyon.dao.StudentDaoImpl;
import com.studyon.exceptions.AdminException;
import com.studyon.exceptions.BatchException;
import com.studyon.exceptions.CourseException;
import com.studyon.exceptions.StudentException;
import com.studyon.model.Batch;
import com.studyon.model.Course;
import com.studyon.model.CourseDetails;
import com.studyon.model.Student;

public class StudyOnApp {

	public static void main(String[] args) {

		 boolean choice = true;
		 
		 while(choice != false){
			 System.out.println("\nWelcome to STUDYON");
			 System.out.println("Please Enter a Choice : ");
			 
			 System.out.println("\n 1: Login as Admin \n 2: Login as Student \n 3: New student SignUp \n 4: Exit");
			 int option = 0; 
			 Scanner sc = new Scanner(System.in);
			 option = sc.nextInt();
			 switch(option) {
			 
			 case 1:
				  try {
					adminLogin();
				} catch (AdminException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				 break;
				 
			 case 2:
				 try {
					studentLogin();
				} catch (StudentException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				 break;
				 
				 
			 case 3:
				 studentSignUp();
				 break;
				 
				 
			 case 4:
				 System.out.println("\n Thank you");
				 choice = false;
			 
			 }
			 
		 }
		 

	}

	private static void studentSignUp() {
		 
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Your Name :");
		String Name = sc.next();
		
		System.out.println("Enter Your City Name:");
		String address = sc.next();
		
		System.out.println("Enter Your email (Use it as a username) :");
		String email = sc.next();
		System.out.println("Enter Password :");
		String password = sc.next();
		
		StudentDao st = new StudentDaoImpl();
		
		try {
			String str = st.studentSignUp(Name, address, email, password);
			System.out.println(str);
		} catch (StudentException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
		
		
	}

	 

	private static void studentLogin() throws StudentException {
		String userName ="";
		String password = "";
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter UserName :");
		userName = sc.next();
		System.out.println("Enter Password :");
		password = sc.next();
		
		StudentDao s = new StudentDaoImpl();
		
		Student student = s.checkStudentAuthenticity(userName, password);
		if(student!=null) {
			System.out.println("\n Login successful");
			studentMenu(student);
		}
	}
		 

		

	
private static void studentMenu(Student s) {
	boolean option = true;
	String studentName=s.getName();  
	while(option) {
		
		System.out.println("\n Welocome "+studentName);
		System.out.println("\n Enter an option: ");
		
		System.out.println("\n 1.Update Your Profile  \n 2: Register for a course  \n 3: Checkout the Available Courses with remaining seats  \n 4:Exit");
		
		 Scanner sc = new Scanner(System.in);
		 int choice = sc.nextInt();
		 switch(choice) {
		 
		  case 1:
			 updateStudentProfile(s);
			 break;
			 
		  case 2:
			 registerInaCourse(s);
			 break;
		  case 3:
			 availableCourses();
			 break;
		  case 4:
			  System.out.println("\n Thank You");
			  option=false;
			 
		 }	 
		
	}
	
}

private static void updateStudentProfile(Student s) {
	 System.out.println("Select 1 To update name ");
	 System.out.println("Select 2 To update address ");
	 System.out.println("Select 3 To update email ");
	 System.out.println("Select 4 To update password ");
	 System.out.println("Select 5 To Return to Main Menu");
	 
	 Scanner sc = new Scanner(System.in);
	 int x = sc.nextInt();
	 StudentDao st1 = new StudentDaoImpl();
	 
	 switch(x) {
	 case 1:
		 System.out.println("Enter new name ");
		 String newName = sc.next();
		 try {
			String str1= st1.updateStudentName(s, newName);
			System.out.println(str1);
		} catch (StudentException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		 break;
	 case 2:
		 System.out.println("Enter new address ");
		 String newAddress = sc.next();
		 try {
			String str2= st1.updateStudentAddress(s, newAddress);
			System.out.println(str2);
		} catch (StudentException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		 break;
	 case 3:
		 System.out.println("Enter new email ");
		 String newEmail = sc.next();
		 try {
			String str3= st1.updateStudentEmail(s, newEmail);
			System.out.println(str3);
		} catch (StudentException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		  
		 break;
	 case 4:
		 System.out.println("Enter new password ");
		 String newPass = sc.next();
		 try {
			String str4= st1.updateStudentEmail(s, newPass);
			System.out.println(str4);
		} catch (StudentException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		 break;
	 case 5:
		 return;
		 
	 }
	 
}

private static void registerInaCourse(Student s) {
	System.out.println("\n You con find courseID and BatchID in course Details");
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter Course id: ");
	int x = sc.nextInt();
	 
	System.out.println("Enter Batch id: ");
	int y = sc.nextInt();
	
	StudentDao student = new StudentDaoImpl();
	
	try {
		String str = student.studentRegistration(s, x, y);
		System.out.println(str);
		System.out.println("\n You will be informed after confirmation by the Administration");
	} catch (StudentException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	}
	
}

private static void availableCourses() {
	 CourseDao st = new CourseDaoImpl();
	 BatchDao bs = new BatchDaoImpl();
	 
	  
	try {
		List<Course> li = st.getAllcoursedetails();
		 li.forEach(s->System.out.println(s));
		
		 List<Batch> bi = bs.getAllbatchdetails();
		 bi.forEach(s->System.out.println(s));
	} catch (CourseException | BatchException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	}
	
	
}

//	Admin Authenticity check
	
	private static void adminLogin() throws AdminException {
		
		String userName ="";
		String password = "";
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter UserName :");
		userName = sc.next();
		System.out.println("Enter Password :");
		password = sc.next();
		
		AdminDao dao = new AdminDaoImpl();
		
		if(dao.checkAdminAuthenticity(userName, password)) {
			
//			res = true;
			
			System.out.println("Login successful");
			adminmanu();
		}
		else {
			System.out.println("Invalid UserName or Password");
		}

		
 	 
		
	}

//	Admin menu bar
	private static void adminmanu() {

		
		
		 boolean choice = true;
		 
		 while(choice != false){
			 System.out.println("\nWelcome to Admin panel ");
			 System.out.println("Please Enter a Choice : ");
			 
			 System.out.println("\n 1: To see All Course Details \n 2: Update Fee of a course \n 3: Delete a course  \n 4: Search information about a course \n 5: Create a batch under a course \n 6: Allocate student in a batch under a course  \n 7: View the students of a particular batch \n 8: Exit");
			 int option = 0; 
			 Scanner sc = new Scanner(System.in);
			 option = sc.nextInt();
			 switch(option) {
			 
			 case 1:
				 allCourseDetails();
				 break;
				 
			 case 2:
				 updateFeeOf_A_Course();
				 break;
				 
				 
			 case 3:
				 deleteACourse();
				 break;
				 
				 
			 case 4: 
				 searchACourse();
				 break;
				 
			 
			 case 5:
				 createABatchUnderACourse();
				 break;
				 
				 
			 case 6:
				 allocateAStudentToABatchUnderACourse();
				 break;
				 
				 
			 case 7:  
				 viewAllStudentsFromAbatch();
				 break;
			 
			 case 8: 
				 System.out.println("Log out Successfully");
				 choice = false;
			 
			 
			 
			 }
			 
		 }
		 
		
	}
private static void viewAllStudentsFromAbatch() {
	Scanner sc = new Scanner(System.in);
	System.out.println("\n Enter the BatchID : ");
	int batchID = sc.nextInt();
	
	BatchDao bd = new BatchDaoImpl();
	
	try {
		List<Student> li=bd.StudentFromAbatch(batchID);
		li.forEach(s-> System.out.println(s));
	} catch (StudentException e) {
		// TODO Auto-generated catch block
		 System.out.println(e.getMessage());
	}
	
	
}

private static void allocateAStudentToABatchUnderACourse() {
	Scanner sc = new Scanner(System.in);
	System.out.println("\n Enter the courseID of the course under which you want to allocate : ");
	int courseID = sc.nextInt(); 
	System.out.println("\n Enter the BatchID of the course under which you want to allocate : ");
	int batchID = sc.nextInt();
	System.out.println("\n Enter the Roll Number of the student  ");
	int roll = sc.nextInt();
	
	CourseDao cdo = new CourseDaoImpl();
	
	try {
		String str = cdo.allocateAStudentToABatchUnderACourse(courseID, batchID, roll);
		System.out.println(str);
	} catch (CourseException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	} catch (StudentException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	} catch (BatchException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	}
}

private static void createABatchUnderACourse() {
	Scanner sc = new Scanner(System.in);
	System.out.println("\n Enter the courseID for for which you want to create a batch: ");
	int courseID = sc.nextInt(); 
	System.out.println("\n Enter the number of seats required for the batch: ");
	int seats = sc.nextInt();
	
	BatchDao bd = new BatchDaoImpl();
	
	try {
		String str = bd.createBatch(courseID, seats);
		System.out.println(str);
	} catch (BatchException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	}
	
	
	
}

//Search a particular course by course id	
private static void searchACourse() {
	Scanner sc = new Scanner(System.in);
	System.out.println("\n Enter the courseID for its course details");
	int courseID = sc.nextInt(); 
	CourseDao cdao = new CourseDaoImpl();
	
	try {
		Course c = cdao.searchACourse(courseID);
		System.out.println(c);
	} catch (CourseException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	}
	
}

private static void deleteACourse() {
	Scanner sc = new Scanner(System.in);
	System.out.println("\n It will affect batches and students");
	System.out.println("\n Enter 1 to Confirm ");
	System.out.println("\n Enter 2 to Cancel ");
	int i = sc.nextInt();
	if(i==2) {
		return ;
	}
	else if(i==1) {
	
		System.out.println("\n Enter the courseID of the course to delete :");
		int courseID = sc.nextInt(); 
		CourseDao cdao = new CourseDaoImpl();
		try {
			String str = cdao.deleteACourse(courseID);
			System.out.println(str);
		} catch (CourseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	
	}
	
	
	else {
		System.out.println("Invalid Input");
		deleteACourse();
	}
	
	
}

//	Update fees

private static void updateFeeOf_A_Course() {
	
	Scanner sc = new Scanner(System.in);
	
	System.out.println("Enter the courseID of the course :");
	int courseID = sc.nextInt();
	System.out.println("Enter the new fee amount");
	int amount = sc.nextInt();
	
	CourseDao cdao = new CourseDaoImpl();
	
	try {
		String str = cdao.updateFeeOfACourse(courseID, amount);
		System.out.println(str);
	} catch (CourseException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	}
	
}

private static void allCourseDetails() {
	
	CourseDao csDao = new CourseDaoImpl();
	
	try {
		List<Course> courses = csDao.getAllcoursedetails();
		courses.forEach(c -> System.out.println(c));
	} catch (CourseException e) {
		 
		System.out.println(e.getMessage());
	}
	
	
}

	}

