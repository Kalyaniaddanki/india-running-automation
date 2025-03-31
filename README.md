Automated the test cases using Selenium with Java, TestNG and followed Page Object Model
Tech Stack : Programming Language : Java 
            Automation Framework : Selenium WebDriver
            Test Runner : TestNG
            Design Pattern : Page Object Model
            Reporting Tool : Extent Reports

Test Scenarios Automated:
Postive Test Cases : 1. Verify user can login with valid email
                     2. Create an event with valid data
                     3. Event displays in India Running Website
Negative Test Cases : 1. User login with Invalid Email
                      2. User try to login with Invalid Otp
                      3. User try to create event without enetring some fields
          
Project Structure : 
📂 india Running
 ├── 📂 src/test/java
 │   ├── 📂 pages        # Page Object Model classes
 │   ├── 📂 tests        # Test cases
 ├── 📂 reports          # Test execution reports
 ├── pom.xml             # Maven dependencies
 ├── README.md           # Project documentation

Depedencies Used : 1. selenium Java
                   2. Testng
                   3. extent reports for report generation.

Parllel Execution : executed test cases on Firefox and Chrome Browser

 

 





