# [Vampiric]

A forge minecraft mod.

For information on what items, blocks, and entities exist, please read the [WIKI](https://github.com/kaltinril/Vampiric/wiki).

## I. How to use this mod

This mod has no publicly built jar file yet.  So you will need to follow the "How to edit or compile this mod" section instead.  However, once built, you simply place the mod.jar file into your minecraft mods folder.

## II. How to edit or compile this mod

This document is assuming a windows user.  If you are a mac or linux user you will need to find the equivlant instructions for that OS.  Additionally this is assuming you are using IntelliJ Idea.  If you are using Eclipse or some other tool, you are on your own.

### 1. Install GIT

1. Download [GIT](https://git-scm.com/)
1. Install GIT
1. Open Git Bash: Right-click in an empty Explorer window and chose ```git bash here```
1. Set your ```global Username```: git config --global user.name "FIRST_NAME LAST_NAME"
1. Set your ```global Email```: git config --global user.email "MY_NAME@example.com" <br>
  (_**NOTE:** You want this to match your github email_)

### 2. Install Java JDK 8 (1.8.* )

1. Download [64 bit Java JDK 8](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
1. Run the downloaded installer and except defaults.

### 3. Install IntelliJ Idea

1. Download [IntelliJ Idea Community Edition](https://www.jetbrains.com/idea/)
1. Install IntelliJ
1. Open IntelliJ
1. Configure Settings to use Java JDK8:
   1. Go to `File > Settings > Build, Execution, Deployment > Build Tools >  Mavern > Importing > JDK for Importer`
   1. Select  your JDK 8 install

### 4. Clone This Repository

1. From the main Code Tab click on the ```Clone or Download``` button
1. Copy the URL
1. Open a ```Git Bash``` window
1. Change directory to where you want to clone (Download) the mod to: cd c:\git\minecraft\
1. Clone the repo: ```git clone https://github.com/kaltinril/Vampiric.git```

### 5. Import project using Gradle import in IntelliJ

1. Open **IntelliJ**
1. Click **Import Project**
1. Select your **build.gradle** file and click **OK**.
1. Wait.... 

### 6. Create Intellij runs

1. Open a command prompt: (start menu > type: CMD > press enter)
1. Change directory to the project: cd c:\path\to\cloned\repo\
1. Run the command: gradlew genIntellijRuns

### 7. Run Client


## III. Errors and Resolutions:

### Import Error

Item    | Info 
------- | -----
**Message** | Cannot determine classpath for resource
**Error** | Cannot determine classpath for resource `'java/sql/SQLException.class'` from location `'jrt:/java.sql/java/sql/SQLException.class'.`<br> The project uses Gradle 2.14 which is incompatible with IDEA running on Java 10 or newer. <br> See details at https://github.com/gradle/gradle/issues/8431 <br>Possible solution: <br> - Upgrade Gradle wrapper to 4.8.1 version and re-import the project
**Description** | While importing the build.gradle or opening the project and clicking Import Changes this error appears.
**Cause** | The wrong version of Java is selected for the Settings
**Resolution** | 1. Go to `File > Settings > Build, Execution, Deployment > Build Tools >  Mavern > Importing > JDK for Importer` <br> 2. Select  your JDK 8 install


### Gradle Build Errors
1. Error during Gradle: Try starting over with gradlew --clean 
