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
To run the app, run the **Start.java** file

# Features that we have completed completed
Create account
  - Email validation
  - Unique username
Login 

# External tools used
SQLite JDBC connector (located in the driver folder)

# To-do
- Main Menu -> Add "View My Postings" options (to view your current postings, add a "Mark as rented" button to change availability of sublet)
- Backend for View Available Postings (only show "available" Postings)
- Posting class (from SDD)
- Add "Back" button to all GUI
- Add "Logout" button in Main menu that goes back to Login page

Features to add if there is extra time
- Message function

# Changed features
In the app, a sublessor can also be a subtenant as well, so we are no longer differentiating between the two.
Due to the removal of the rating system, we no longer require the User class since the User attributes are stored in the database.

# Removed Features
Due to time constraints, we have decided not to implement the following features listed in the Software Design Document
- Rating system

# Troubleshooting
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
