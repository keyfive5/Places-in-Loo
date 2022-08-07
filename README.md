# Places-in-Loo
Hasan (MZ), Kabir, Shahyan (MK), Faisal (MN), Jack, Jackson, Nabeel, Wrenen

# Installation
Pre-requisites:
This application was mainly developed using Visual Studio Code (tested primarily on Windows), and some functions may not work without it.
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


Scroll down until you see Reference Libraries. Click the plus sign icon and add the .jar file found in the **driver** folder of the project.

To check if you completed the steps correctly, run the **ConnectionExample.java** file to see if you are getting an error. You should get the following output from the console if it is working properly.

![image](https://user-images.githubusercontent.com/53016334/183272847-b69f0dcb-3a2d-4447-8d37-82b3e2170c6f.png)

If you are receiving an error, follow the **Troubleshooting** section found at the end of this README

# How to use
To run the app, run the **Start.java** file by either right clicking the Start.java file, then select "Run Java" (recommended method), or by clicking the Play button at the top right of the editor.

## Login
When you first open the app, you will be directed to the login screen. If you made an account previously, simply enter your username and password and click login. 
You can also use this dummy account used for testing:
- Username: caix3600
- Password: password

Otherwise, you can click the "Create Account" button to create an account.

## Create Account
Simply fill in the fields and click "Submit". Because we only want students from Wilfrid Laurier University and University of Waterloo using our app, we have made a special restriction where you must use an email ending in "mylaurier.ca" or "uwaterloo.ca". Usernames must also be unique, meaning if a username already exists in the database then the app will not allow you to use the same username.

## Main Menu
Once you have logged in, you will have access to 4 different options. You can click either of the buttons to go straight to each option. There is also a "Logout" button at the bottom right corner to logout and login with a different account. The 4 main options will be covered in the next sections. 

## Post a sublet
The main feature of our app is to be able to "Post a sublet". You can enter the details of your sublet and press submit, and a posting will be generated under your name. If you wish to go back to the Main Menu, simply click on the "Home" icon in the top left corner.

## Rent a sublet
Here, you will be able to see all available sublets. However, since there is no point in subletting your own sublet, you cannot see your own sublet postings in this function. If you wish to see your own sublet postings, head over to the next section. You can click on the name of the location you want to sublet and press the "Get Post" button on the bottom to display the sublet details. If the details are satisfactory, you can click on the "Rent" button at the bottom to rent the sublet. We currently only have the sublet posting disappear on clicking the "Rent" button but it does not notify the sublessor, which is for future implementation. **Note**: No postings will show up if there are no available postings. If you wish to go back to the Main Menu, simply click on the "Home" icon in the top left corner.

## Cancel a sublet
Here, you can view your own sublets that you have posted. If you have been reached out by someone asking to sublet and you agree, you can remove your sublet here from available postings so that it will no longer show up under "Rent a sublet". To cancel a posting, click on the posting you wish to remove and click the Cancel button. **Note**: No postings will show up if you do not have an available posting. If you wish to go back to the Main Menu, simply click in the "Home" icon on the top left corner.

## Rate a sublet
Here, you can rate any sublessor that has previously made a posting. You can click on the sublessors name to select which one you want to rate, then click on the stars to select a rating and finally select the "Submit" button. This will update the average ratings of that sublessor in the database, where their ratings can be seen when you click on any of the sublessors' postings in the "Rent a sublet" section. **Note**: you cannot rate yourself, which is why your own name will not appear in the rating section. If you wish to go back to the Main Menu, simply click on the "Home" icon in the top left corner.

# Features that we have completed
- Create account
  - Email validation
  - Unique username
- Login 
  - Credential Validation
- Post a sublet
- Rent a sublet
  - Cannot see your own sublets
  - Can see the ratings of a user who is subletting
- Cancel a sublet
  - Can only see your own sublets
- Rate a sublet
  - Cannot rate yourself
  - Calculates and the updates average rating of a user


# External tools used
SQLite JDBC connector (located in the driver folder)

# Changed features from SDD
During our implementation, we noticed some details from the SDD were not practical and have made the following changes:
- No longer distinguishing between Subtenant and Sublessor, a User of the app can be both at the same time and should have access to both features
- Added "available" field to "Posting" class to keep track of when a posting is still available
- Added "rating" and "total_ratings" to keep track of ratings on a User and be able to update the ratings of a User
- "Rating" class was removed since it will just be stored as a field in the User class


# Removed Features
Due to time constraints, we have decided not to implement the following features listed in the Software Design Document
- Message Function, currently displays a message but does not message anyone
- Message class therefore not implemented since this feature was not implemented

# File explanation
All the files in the SQL folder are the files that ran to create and modify the initial database. They are only there to show how they were created and are not needed to run the actual implementation.

Start.java - The main java file, runs the LoginGUI class
<br>
LoginGUI.java - The file that creates the login page and deals with the login page backend
<br>
CreateAccount.java - The file that creates the create account page and deals with the create account backend
<br>
MainMenu.java - The file that displays the different pages and provides links to them
<br>
NewPost.java - The file that allows the user to add a new posting, and connects to the backend to add the posting to the database
<br>
Rent.java - The file that queries the database to display a list of available postings
<br>
Rate.java - The file that allows users to rate their previous sublet accommodation on a scale from 1-5 and add an optional review
<br>
Cancel.java - The file that allows users to cancel their current sublet
<br>
User.java - The file that contains the User class and is used to pass data on the current user in the system
<br>
ConnectionExample.java - Test file used to test the database connection. Important the database connection is working for other features to work. Not part of the actual implementation, just to make sure everything is working properly.
<br>
data.db - The main database that stores all the data related to the app


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
