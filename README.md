Electricity Cost Calculator

📌 Project Description

The Electricity Cost Calculator** is a Java-based desktop application that allows users to calculate the **daily, monthly, and yearly electricity cost** of household appliances.

Users input the appliance name, wattage, number of hours used per day, and the electricity rate (PHP per kWh). The program then automatically computes energy consumption and corresponding costs.

This project demonstrates the application of **Java Swing GUI**, basic **mathematical formulas**, and **real-life problem solving** related to electricity consumption.

---

## 🎯 Objectives

* To apply Java programming concepts in a real-world scenario
* To practice creating a graphical user interface using Java Swing
* To understand how electricity consumption and cost are calculated
* To develop logical and mathematical problem-solving skills

---

## 🧮 Formulas Used

### Energy Consumption

```
kWh/day = (Wattage × Hours Used per Day) ÷ 1000
```

### Cost Computation

```
Daily Cost   = kWh/day × Rate per kWh
Monthly Cost = Daily Cost × 30
Yearly Cost  = Daily Cost × 365
```

---

## 🖥️ Program Features

* Input electricity rate (PHP per kWh)
* Add appliances with wattage and hours of use
* Automatic calculation of:

  * kWh per day
  * Daily cost
  * Monthly cost
  * Yearly cost
* Appliance list displayed using a table
* Delete selected appliance
* Clear all appliance entries
* Displays total cost summary

---

## 📊 Sample Output

### Sample Input

* Electricity Rate: **PHP 13.50 per kWh**
* Appliances:

  * Fan (32 W, 20 hours/day)
  * LED Bulb (20 W, 18 hours/day)
<img width="1094" height="865" alt="Screenshot 2025-12-29 130753" src="https://github.com/user-attachments/assets/bcf02c46-f1d3-449e-a6ff-88d0e6108761" />
<img width="1126" height="846" alt="Screenshot 2025-12-29 130643" src="https://github.com/user-attachments/assets/575fdf7a-4a11-45ea-86d0-2fd240bfeb5a" />

### Sample Result

* Total Daily Usage: **1.00 kWh**
* Total Daily Cost: **PHP 13.50**
* Total Monthly Cost: **PHP 405.00**
* Total Yearly Cost: **PHP 4,927.50**

---

## 🛠️ Tools and Technologies Used

* Java Programming Language
* Java Swing (GUI)
* JTable for appliance list display

🛠️HOW TO RUN THE PROGRAM

Step 1: Install Java Development Kit (JDK)
Before running the program, make sure that Java JDK 8 or later is installed on your computer.
Download the JDK from the official Java website
Follow the installation instructions
After installation, open Command Prompt and type:
java -version
If the Java version appears, it means Java is installed correctly.

Step 2: Locate the Program File
Ensure that the file ElectricityCostCalculator.java is saved in a known location on your computer, such as the Desktop or a specific project folder.

Step 3: Open Command Prompt
Press Windows + R
Type cmd
Press Enter
This will open the Command Prompt window.

Step 4: Navigate to the Program Folder
Use the cd command to go to the folder where the Java file is located.
Example (if the file is on the Desktop):
cd Desktop
Press Enter after typing the command.

Step 5: Compile the Program
To compile the Java source code, type the following command and press Enter:
javac ElectricityCostCalculator.java
If there are no error messages, the program has compiled successfully.

Step 6: Run the Program
After successful compilation, run the program by typing:
java ElectricityCostCalculator
Press Enter, and the Electricity Cost Calculator window will appear.

Step 7: Use the Program
Enter the electricity rate (PHP per kWh)
Input appliance name, wattage, and hours used per day
Click Add to include the appliance in the list
View the daily, monthly, and yearly electricity cost summary

#Notes
Make sure the file name and class name match exactly
Do not include .java when running the program
If an error occurs, recheck the file location and Java installation
  
 📝 Conclusion
The Electricity Cost Calculator successfully demonstrates how Java programming can be used to solve practical problems. Through this project, the student gains experience in GUI development, event handling, and applying mathematical formulas in software applications.

