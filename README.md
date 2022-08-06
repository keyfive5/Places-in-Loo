# Places-in-Loo
Hasan (MZ), Kabir, Shahyan (MK), Faisal (MN), Jack, Jackson, Nabeel, Wrenen

# Installation
Pre-requisites:
This application was mainly developed using Visual Studio Code (tested primarly on Windows), and some functions may not work without it.
It can be downloaded here: https://code.visualstudio.com/download

To install, you can clone the repository using: git clone https://github.com/keyfive5/Places-in-Loo.git 
<br>
If git is not installed, you can download the ZIP file and unzip it.

Next, there are 2 important Visual Studio Code extensions that are required in order to run the app.
  1. Extension Pack for Java by Microsoft
  2. SQLite by alexcvzz
 
 For steps on how to install Visual Studio extensions, follow https://code.visualstudio.com/docs/editor/extension-marketplace
 
Once you have the following extensions installed, you will need to add the .jar file in the **driver** folder as a Reference Library.
To do this in Visual Studio Code, click on the project. Then click on any .java file. You should see a section called **Java Projects** appear at the bottom.

![image](https://user-images.githubusercontent.com/53016334/181165663-a4de7066-f89b-40d1-9529-6400facd50dd.png)


Scroll down untill you see Reference Libraries. Click the plus sign icon and add the .jar file fouind in the **driver** folder of the project.

To check if you completed the steps correctly, run the **ConnectionExample.java** file to see if you are getting an error.

If you are receiving an error , follow the **Troubleshooting** section found at the end of this README

# Running the app
To run the app, run the **Start.java** file by either right clicking the Start.java file, then select "Run Java" (recommended method), or by clicking the Play button at the top right of the editor.

# Features that we have completed completed
- Create account
  - Email validation
  - Unique username
- Login 

# External tools used
SQLite JDBC connector (located in the driver folder)

# To-do
- Add comments
- Keep track of current user
- Cancel sublet, allow to view postings
- Add ratings when viewing posting
- Add rating to user

# Changed features from SDD
During our implementation, we noticed some details from the SDD were not practical and have made the following changes:
- No longer distinguishing between Subtenant and Sublessor, a User of the app can be both at the same time and should have access to both features
- Added "available" field to "Posting" class to keep track of when a posting is still available
- Added "rating" and "total_ratings" to keep track of ratings on a User and be able to update the ratings of a User
- "Rating" class removed, since it will just be stored as a field in the User class


# Removed Features
Due to time constraints, we have decided not to implement the following features listed in the Software Design Document
- Message Function, currently displays a message but does not message anyone
- Message class therefore not implemented since this feature was not implemented

# File explaination
All the files in the sql folder are the files ran to create and modify the initial database. They are only there to show how they were created and are not needed to run the actual implementation.

Start.java - The main java file, runs the LoginGUI class
<br>
LoginGUI.java - The file that creates the login page and deals with the login page backend
<br>
CreateAccount.java - The file that creates the create account page and deals with the create account backend
<br>
MainMenu.java - The file that displays the different pages and provides links to them
<br>
NewPost.java - The file that allows user to add a new posting, and connects to the backend to add the posting to the database
<br>
Rent.java - The file that queries the database to display a list of available postings
<br>
Rate.java - The file that allows users to rate their previous sublet accomodation out of a scale from 1-5 and add an optional review
<br>
Cancel.java - The file that allows users to cancel their current sublet
<br>
User.java - The file that contains the User class and deals with the back-end


# Troubleshooting
Conflicting Extensions:
<br>
There are a few conflicting extensions that may make the database connection not work. Check if you have an extension called Code Runner and make sure you have the Code Runner extension ***uninstalled***, then restart VS Code.
Try running the java file by right clicking the .java file and selecting "Run Java". If this option is not available, it means you do not have the **Extension Pack for Java** installed
 mentioned above
 
Visual Studio Code has a very weird bug where depending on how you open the folder, you will get a different path.
<br>
This means that the file may not be able to find the database. We can first try to configure the Classpath. To do this, click on any .java file
to open the **Java Projects** section. Right click "Places-in-Loo" under **Java Projects**, then select **Configure Classpath**.

![image](https://user-images.githubusercontent.com/53016334/181340293-6b7aaf1d-56cd-4821-9999-362a18109923.png)

Click on the "Select the project folder" and select "Places-in-Loo".

![image](https://user-images.githubusercontent.com/53016334/181340242-d6688e71-046d-40e5-9b38-51de3d7d53da.png)

If you are still having difficulties connecting to the database, you may have to hardcode the path to the database (data.db file). To do this, open the **DatabaseConnection.java** file
<br>
![image](https://user-images.githubusercontent.com/53016334/181295495-1ec27a46-b57c-4f08-a3a5-66663c6baaaa.png)

In the DatabaseConnection constructor, uncomment out: //this.database = "enter-full-path-to-data.db-file-here";
<br>
then replace the right side of the equation with the full path to the **data.db** file.
