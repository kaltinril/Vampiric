# Vampiric

A forge minecraft mod

## How to edit or compile this mod

### 1. Install GIT

### 2. Install IntelliJ Idea

### 3. Install Java JDK 8 (1.8.* )

### 4. Clone Repository

### 5. Import project using Gradle import in IntelliJ

1. Open **IntelliJ**
2. Click **Import Project**
3. Select your **build.gradle** file and click **OK**.
4. Wait.... 

### 6. Create Intellij runs

1. Open a command prompt: (start menu > type: CMD > press enter)
2. Change directory to the project: cd c:\path\to\cloned\repo\
3. Run the command: gradlew genIntellijRuns

### 7. Run Client


## Errors and Resolutions:

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
