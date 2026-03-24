# 📱 GoKhana Mobile Automation Framework

> **AI-Assisted Mobile Test Automation** — Built using Claude AI with MCP Server integration directly inside IntelliJ IDEA IDE

---

## 🤖 AI Integration Highlight

This project was developed using **Claude AI (Anthropic)** integrated directly into **IntelliJ IDEA** via the **MCP (Model Context Protocol) Server**. The AI assisted in:

- 🏗️ Designing the entire framework architecture
- 🔍 Identifying and fixing Appium locators in real-time
- 🐛 Debugging test failures from terminal output
- 📝 Writing and refactoring Java code inside the IDE
- 🔄 Reading, editing and creating files directly through the IDE
- 🧪 Building test cases and page object models
- ⚙️ Setting up parallel execution and reporting

> The MCP Server allowed Claude AI to directly read files, edit code, run builds and fix errors inside IntelliJ IDEA — making the entire development process conversational and AI-driven.

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
| Claude AI | Sonnet 4.6 | AI pair programming |
| MCP Server | - | IDE ↔ AI integration |

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

## 🤝 AI-Assisted Development

This project demonstrates modern **AI-augmented engineering** in test automation:

- **Claude AI (Anthropic)** was integrated into **IntelliJ IDEA** via **MCP (Model Context Protocol) Server**
- The AI could directly read/write files, run builds, fix compilation errors and debug test failures in real-time inside the IDE
- All framework architecture, locator strategies, debugging and code were pair-programmed conversationally with AI
- This approach significantly reduced development time while maintaining production-quality code standards

> 💡 This is a showcase of how AI tools like Claude AI with MCP Server integration can transform the way automation engineers build frameworks — faster, smarter and more efficiently.

---

## 👨‍💻 Author

**Ambar**
- Mobile Automation Engineer
- Framework built with AI pair programming using Claude AI + MCP Server + IntelliJ IDEA
