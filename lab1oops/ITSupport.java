package lab1oops;
import java.security.SecureRandom;
import java.util.Scanner;

class Employee {
	String firstName, lastName;
	
	String deptName,companyName;
	
	public Employee(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public String getDeptName() {
		return this.deptName;
	}
	public String getCompanyName() {
		return this.companyName;
	}
	
	
	
}

class CredentialService {
	
	Employee emp;
	public CredentialService (Employee emp) {
		this.emp = emp;
	}
	public String generateRandomPassword() {
		int length = 8; // Adjust the length of the password as needed
        String password = generatePassword(length);
        return password;
	}
	
	public void displayGeneratedCredentials() {
		System.out.println("Dear " + emp.firstName + " your generated credentials are as follows");
		System.out.println("Email \t\t --> \t " + this.generateEmail());
		System.out.println("Password \t\t --> \t " + this.generateRandomPassword());
	}
	public String generateEmail () {
//		Generate an email with the following syntax
		return emp.getFirstName() + emp.getLastName() + "@" + emp.getDeptName() +  "." + emp.getCompanyName() + ".com" ;
		
	}
	
	private static String generatePassword(int length) {
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?";

        String allCharacters = uppercaseLetters + lowercaseLetters + numbers + specialCharacters;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Ensure at least one character from each category
        password.append(getRandomCharacter(uppercaseLetters, random));
        password.append(getRandomCharacter(lowercaseLetters, random));
        password.append(getRandomCharacter(numbers, random));
        password.append(getRandomCharacter(specialCharacters, random));

        // Fill the rest of the password length with random characters
        for (int i = 4; i < length; i++) {
            password.append(getRandomCharacter(allCharacters, random));
        }

        // Shuffle the characters to make the password more random
        char[] passwordArray = password.toString().toCharArray();
        for (int i = passwordArray.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = passwordArray[index];
            passwordArray[index] = passwordArray[i];
            passwordArray[i] = temp;
        }

        return new String(passwordArray);
    }

    private static char getRandomCharacter(String characterSet, SecureRandom random) {
        int randomIndex = random.nextInt(characterSet.length());
        return characterSet.charAt(randomIndex);
    }

	
	
}

public class ITSupport {

	public ITSupport() {
		// TODO Auto-generated constructor stub

	}
	
	public static void main(String args[]) {
		String dept[] = new String[] {"Technical", "Admin", "Human Resource", "Legal"};
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the department name : ");
		System.out.println("1. Technical \n2. Admin\n3. Human Resource\n4. Legal");
		
		int deptIndex = sc.nextInt();
		
		Employee emp = new Employee("Harshit", "Sharma");
		emp.setDeptName(dept[deptIndex - 1]);
		emp.setCompanyName("CompanyName");
		
		CredentialService cs = new CredentialService(emp);
		
		cs.displayGeneratedCredentials();
		
	}

}
