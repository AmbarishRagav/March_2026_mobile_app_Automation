# 📱 GoKhana Mobile Automation Framework

> **Mobile Test Automation Framework** — End-to-end Android automation built with Appium, Java and TestNG using the Page Object Model design pattern.

---

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 22 | Programming language |
| Appium | 9.2.0 | Mobile automation |
| Selenium | 4.18.1 | WebDriver base |
| TestNG | 7.12.0 | Test framework |
| Maven | 3.x | Build tool |
| ExtentReports | 5.1.1 | HTML reporting |

---

## 📁 Project Structure

```
2026NEW_Mobile_App/
├── src/
│   └── test/
│       └── java/
│           ├── bases/
│           │   └── Base.java              # Driver setup, Appium auto-start, Login
│           ├── constants/
│           │   └── Locators.java          # Central locator repository
│           ├── pages/
│           │   ├── LoginPage.java         # Login flow
│           │   ├── SearchPage.java        # Search functionality
│           │   ├── AccountPage.java       # Account management
│           │   └── FoodOutletPage.java    # Food outlet interactions
│           ├── tests/
│           │   ├── LoginTest.java
│           │   ├── SearchTest.java
│           │   ├── search/
│           │   ├── account/
│           │   └── foodOutlets/
│           └── utils/
│               ├── TestListener.java      # Screenshots on failure
│               └── ExtentReportManager.java # HTML reports
│       └── resources/
│           ├── testng.xml                 # Main test suite
│           ├── testng-parallel.xml        # Parallel execution suite
│           └── config.template.properties # Config template (safe to share)
├── APP_Resource/                          # APK files (gitignored)
├── screenshots/                           # Auto-generated on failure
├── reports/                               # ExtentReports HTML output
└── pom.xml
```

---

## ✨ Features

- ✅ **Page Object Model** — clean separation of locators and actions
- ✅ **Central Locator Repository** — all XPaths in `Locators.java`
- ✅ **Appium Auto-Start/Stop** — no manual server management needed
- ✅ **Device Switching** — toggle between emulator and physical device via config
- ✅ **Parallel Execution** — run on emulator and physical device simultaneously
- ✅ **Smart Keyboard Handling** — back button for physical, tap for emulator
- ✅ **Screenshot on Failure** — auto-captured for login and test failures
- ✅ **ExtentReports HTML** — beautiful dark-themed test reports
- ✅ **APK Auto-Install** — installs and verifies app before tests run
- ✅ **Config Template** — safe credential management for team use

---

## 🚀 Getting Started

### Prerequisites
- Java 22
- Maven 3.x
- Node.js + Appium (`npm install -g appium`)
- Android SDK + ADB
- IntelliJ IDEA

### Setup

1. **Clone the repo**
```bash
git clone https://github.com/YOUR_USERNAME/2026NEW_Mobile_App.git
```

2. **Create your config**
```bash
cp src/test/resources/config.template.properties src/test/resources/config.properties
# Fill in your device details and credentials
```

3. **Add your APK**
```
Place your APK in: APP_Resource/
Update apkPath in config.properties
```

4. **Run tests**
```bash
# Via Maven
mvn clean test

# Via IntelliJ — right-click testng.xml → Run
```

---

## 📊 Test Coverage

| Module | Tests |
|---|---|
| Login | OTP login flow with keyboard handling |
| Search | Exact, partial, uppercase, lowercase search |
| Account | Add/Edit details, Corporate code, Favourites, Address, Delete |
| Food Outlets | Increase/Decrease quantity, View cart, Counter page |

---

## 📸 Reports & Screenshots

- **HTML Report**: `reports/GoKhana_Report_[timestamp].html`
- **Failure Screenshots**: `screenshots/[testName]_[timestamp].png`
- **Login Failure**: `screenshots/LOGIN_FAILED_[timestamp].png`

---

## 🔧 Configuration

Switch between devices in `config.properties`:
```properties
# Use 'physical' or 'emulator'
targetDevice=physical
```

---

## 🤝 About

This project demonstrates a production-quality mobile automation framework built on industry-standard practices:

- Designed and developed independently as a showcase of mobile test automation skills
- Covers real-world scenarios including login, search, account management and food outlet interactions
- Follows clean code principles with maintainable, scalable architecture

---

## 👨‍💻 Author

**Ambar**
- Mobile Automation Engineer
