# UI Automation Testing - OrangeHRM Web Application

## Project Overview
This project automates UI testing for the [OrangeHRM demo site](https://opensource-demo.orangehrmlive.com/) using:
- **Selenium WebDriver** (with option for Playwright or Shaft Engine)
- **Page Object Model (POM)** design pattern
- **TestNG** test framework

Key features:
- Modular and maintainable code structure
- Proper element synchronization with explicit waits
- Comprehensive assertions for validation
- Cross-browser support (Chrome/Firefox)
## 🎬 Demo Video

Watch the automation demo here:  
https://drive.google.com/file/d/12Qvup6U9UhgXC_Nx5r2-wkcE0admYoF4/view?usp=sharing

## Prerequisites

### Tools
- Java JDK 8+ ([Download](https://www.oracle.com/java/technologies/javase-downloads.html))
- Maven 3+ ([Download](https://maven.apache.org/download.cgi))
- IDE (IntelliJ, Eclipse, or VS Code with Java support)
- Google Chrome or Firefox

### Dependencies
Managed via Maven (`pom.xml`):
- Selenium WebDriver
- TestNG
- WebDriverManager (for automatic driver management)
- Hamcrest (for advanced assertions)

## Project Structure
```text
src/
├── main/
│   └── java/
│       ├── pages/        # Page Object classes
│       └── utils/        # Helper classes and utilities
└── test/
    └── java/
        └── tests/       # TestNG test classes
### Setup Instructions
1. Clone the repository
    git clone <repository-url>
    cd <project-folder>

2. Install dependencies
    mvn clean install

3. Configure WebDriver

    If not using WebDriverManager, download the browser driver (chromedriver/geckodriver) and add it to PATH

### How to Run the Tests
Using TestNG

    1.Open testng.xml in your IDE
    
    2.Run as TestNG Suite

Using Maven CLI
    mvn test

Using Maven with TestNG XML
    mvn test -DsuiteXmlFile=testng.xml
