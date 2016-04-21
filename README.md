# selenium project
<h4>Project Description</h4>
     Building a Selenium test suite in Java using Selenium WebDriver
> 1) Log in to Pardot (https://pi.pardot.com)<br>
> 2) Create a list with a random name (Marketing > Segmentation > Lists)<br>
> 3) Attempt to create another list with that same name and ensure the system correctly gives a validation failure<br>
> 4) Rename the original list<br>
> 5) Ensure the system allows the creation of another list with the original name now that the original list is renamed<br>
> 6) Create a new prospect (Prospect > Prospect List)<br>
> 7) Add your new prospect to the newly created list<br>
> 8) Ensure the new prospect is successfully added to the list upon save<br>
> 9) Send a text only email to the list (Marketing > Emails)  *Please note, email is disabled in this account so you will not actually be      able to send the email.  This is okay.<br>
> 10) Log out<br>

<h4>prerequisite</h4>
> Install maven in command line terminal
 
<h4> Executing project</h4>
> 1) open terminal<br>
> 2) clone git( git clone https://github.com/sushmaalluri/Testing.git)<br>
> 3) cd Testing/pardot(go to folder)<br>
> 4) mvn clean<br>
> 5) mvn install<br>
> 6) mvn test<br>


<h4>Description</h4>
•My main test class is in PardotTest.java file.

•	The LoginPage.java file contains a method called "login", which is used to Login into the webpage Pardot.com (https://pi.pardot.com, Username: pardot.applicant@pardot.com, Password: Applicant2012).

•	The SideMenu.java contains all the side menu selection operations.   

     This filecontains"gotoMarkSegLists","gotoProProspectList","gotoEmailNewEmail" and “logout" methods.
     The "gotoMarkSegLists" is used to navigate through page and select lists (Marketing-->Segmentation--->Lists). 
     The "gotoProProspectList" is used to navigate through goto prospect-->prospectlist page. 
     The gotoEmailNewEmail method is used to navigate through Marketing-->Emails-->New Email and select the New Email.
     The logout method is used to navigate through logout icon and select sign out option.

•	The ListPage.java file contains"addList" and "renameList" methods. The addList method is used to create a list with random name. The renameList method is used to rename the already existing list.


•	The ProspectPage.java file contains "addProspect" and "addProspectToList" methods. The addProspect method is used to create a prospect. The addProspectToList method is used to add the new prospect to the newly created list.


•	The EmailPage.java file contains a method "sendEmail", which is used to send the text only emails to the list.

 


