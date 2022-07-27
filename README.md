# Places-in-Loo
Hasan (MZ), Kabir, Shahyan (MK), Faisal (MN), Jack, Jackson, Nabeel

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
 
Once you have the following extensions installed, you will need to add the .jar file in the "driver" folder as a reference library
To do this in Visual Studio Code, click on the project. You should see a section called "Java Projects" appear

![image](https://user-images.githubusercontent.com/53016334/181165663-a4de7066-f89b-40d1-9529-6400facd50dd.png)


Scroll down untill you see Reference Libraries. Click the pluss sign icon and add the .jar file fouind in the "driver" folder of the project.

# Running the app
To run the app, run the Start.java file

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

Features to add if there is extra time
- Message function

# Changed features
In the app, a sublessor can also be a subtenant as well, so we are no longer differentiating between the two.
Due to the removal of the rating system, we no longer require the User class since the User attributes are stored in the database.

# Removed Features
Due to time constraints, we have decided not to implement the following features listed in the Software Design Document
- Rating system
