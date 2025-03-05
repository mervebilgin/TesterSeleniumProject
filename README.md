# Tester Selenium Project

## 📌 Overview
This project is an **automated test framework** for validating the **Insider Careers page functionality** using **TestNG and Selenium WebDriver**. It follows the **Page Object Model (POM)** to ensure modularity and maintainability.

## 🛠 Tech Stack
- **Language:** Java  
- **Test Framework:** TestNG  
- **Automation Tools:** Selenium WebDriver, AShot (for screenshots)  
- **Dependency Management:** Maven  
- **Configuration Management:** Configuration files (`configuration.properties`)  
- **WebDriver Management:** WebDriverManager  
- **Supported Browsers:** Chrome, Firefox, Edge, Internet Explorer  

## 📂 Project Structure

<img src="https://github.com/user-attachments/assets/3b33be22-6cd8-4d7d-b904-7a2771d87cf3" alt="insider" width="500" height="470"/>

---

- **configuration.properties:** Test configuration settings.
- **custom:** Custom classes (ConfigReader, TestContext)
- **enums/:** Enum classes for driver and environment types
- **listeners/:** TestNG Listeners (Screenshot on failure)
- **managers/:** WebDriver & Page Object Managers
- **pages/:** Page Object Model (POM) classes
- **testcases/:** Test cases using TestNG
- **testng.xml:** TestNG test suite configuration
- **pom.xml:** Maven dependencies

## ⚡ Features
✅ **Page Object Model (POM) for maintainability**  
✅ **Automated browser setup with WebDriverManager**  
✅ **Screenshot capturing on test failure (TestNG Listener)**  
✅ **Parameterized test execution using `configuration.properties`**  
✅ **Tests include UI navigation, job filtering, and application submission validation**  

## 🚀 Test Scenarios
### 🔹 `T01_HomeToCareerPageTest`
- Navigates from **Home Page → Careers Page**.
- Verifies page navigation and content.

### 🔹 `T02_QAJobFilteringAndApplicationTest`
- Opens the **QA Jobs page**.
- Filters jobs by **Quality Assurance & Istanbul, Türkiye**.
- Ensures **job listings are correctly filtered**.
- Opens **application form** and verifies its content.

### 🔹 `T03_FailTest`
- **Intentionally fails** to trigger the **screenshot listener** for debugging.

## 🔧 Setup & Execution
### Prerequisites:
- **Java 11+** installed
- **Maven** installed
- **Chrome/Firefox/Edge drivers** (auto-managed via WebDriverManager)

## 📜 Configuration
Modify `configuration.properties` to set:
```ini
browser=chrome
implicitWait=30
environment=local
INSIDER_URL=https://useinsider.com/
QA_JOBS_URL=https://useinsider.com/careers/quality-assurance/
```

