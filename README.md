# [Vampiric]

A forge minecraft mod.

This mod has 3 main categories:
1. Vampires
1. Werewolves
1. Mummies

Each category adds items specific to that ```EntityType```.  There are some items that are shared between all three.

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

### 4. Clone This Repository

1. From the main Code Tab click on the ```Clone or Download``` button
1. Copy the URL
1. Open a ```Git Bash``` window
1. Change directory to where you want: ```cd c:\git\minecraft\```
1. Clone the repo: ```git clone https://github.com/kaltinril/Vampiric.git```

### 5. Import project using Gradle import in IntelliJ

1. Open **IntelliJ**
1. Click **Import Project**
1. Select your **build.gradle** file and click **OK**.
1. Wait.... 

### 6. Create Intellij runs
This section will build the Run Configuration settings for runClient, runServer, and 
1. Open a command prompt: ```start menu > type: CMD > press enter```
1. Change directory to the project: ```cd c:\path\to\cloned\repo\```
1. Create the run settings: ```gradlew genIntellijRuns```

### 7. Run Client

1. Use the command window from #6 above (This assumes you are in the repo project directory)
1. Build and start client: ```gradlew runClient```
1. Wait...

### 7. Run Server

1. Use the command window from #6 above (This assumes you are in the repo project directory)
1. Build and start client: ```gradlew runServer```
1. Wait...

## III. Updating Forge Version

1. Open the build.gradle file
1. Under ```dependencies```, change the ```minecraft``` line: ```minecraft 'net.minecraftforge:forge:1.15.2-31.0.16'```
   1. Pick which latest build you want by going to the [Forge Website](https://files.minecraftforge.net/)
1. After changing this value, IntelliJ will notice the change and ask you if you want to ```Import Changes```.  You do, click it.
1. You may need to fix code now if there was a substantial enough jump in version.

## IV. Updateing MCP (Mappings) Version.

1. Open the build.gradle file
1. At the top Under ```minecraft```, change the ```mappings``` line: ```mappings channel: 'snapshot', version: '20190719-1.14.3'```
   1. Pick which latest mapping you want by going to the [MCPBot Website](http://export.mcpbot.bspk.rs/)
1. After changing this value, IntelliJ will notice the change and ask you if you want to ```Import Changes```.  You do, click it.
1. You may need to change Class, Method (Function), and Variable names after this update.  Fix any errors by looking up the correct mappings for any items.  

## V. Errors and Resolutions:

### Import Error

Item    | Info 
------- | -----
**Error** | Cannot determine classpath for resource `'java/sql/SQLException.class'` from location `'jrt:/java.sql/java/sql/SQLException.class'.`<br> The project uses Gradle 2.14 which is incompatible with IDEA running on Java 10 or newer. <br> See details at https://github.com/gradle/gradle/issues/8431 <br>Possible solution: <br> - Upgrade Gradle wrapper to 4.8.1 version and re-import the project
**Description** | While importing the build.gradle or opening the project and clicking Import Changes this error appears.
**Cause** | These instructions cover 1.15 and may not work for 1.14 or 1.12
**Resolution** | Unknown, did not try to resolve


### Gradle Build Errors

Item    | Info 
------- | -----
**Error** | ???
**Description** | Any error during initial first gradle build
**Cause** | Unknown specific error
**Resolution** | Try resetting Gradle (which doesn't impact your code)<br> 1. Remove all downloaded gradle information for this project: ```gradlew clean``` <br> 2. Refresh all dependencies: ```gradlew --refresh-dependencies```<br> 3. Build the project: ```gradlew build``` <br> 4. Test the client: ```gradlew runClient```
