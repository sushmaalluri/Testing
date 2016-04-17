# selenium project
<h4>prerequisite<h4>
 <p>Install maven in command line terminal</p>
 
<h4> Executing project</h4>
> open terminal
> clone git( git clone https://github.com/sushmaalluri/Testing.git)
> cd Testing/pardot(go to folder)
> mvn clean
> mvn install
> mvn test


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

 


