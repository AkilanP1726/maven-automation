# ⚙️ Maven CI/CD Automation Pipeline

<div align="center">

![Java](https://img.shields.io/badge/Java-8%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Apache_Maven-3.8%2B-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-WebDriver-43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![CI/CD](https://img.shields.io/badge/CI%2FCD-Pipeline-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)
![Status](https://img.shields.io/badge/Status-Archived-lightgrey?style=for-the-badge)

**A Maven-powered CI/CD automation pipeline integrating Selenium WebDriver for automated build,  
test execution, and deployment workflows — built from scratch as a hands-on DevOps learning project.**

[Overview](#-overview) · [Tech Stack](#-tech-stack) · [Pipeline Flow](#-pipeline-flow) · [Getting Started](#-getting-started) · [Maven Commands](#-maven-commands) · [What I Learned](#-what-i-learned)

</div>

---

> 🗄️ **Archived Repository** — This project was built ~2 years ago as a self-driven exploration into  
> CI/CD concepts using Maven as the backbone. It is no longer actively maintained, but preserved  
> as a portfolio reference and learning milestone.

---

## 🧭 Overview

This project demonstrates how **Apache Maven** can serve as the engine of a CI/CD pipeline — going far beyond just a build tool. The focus was on automating the full software delivery lifecycle:

```
Code Change → Build → Test (Selenium) → Package → Deploy
```

The goal was to understand how teams ship software reliably and repeatedly — without manual steps — by wiring together Maven's lifecycle phases with automated browser testing via **Selenium WebDriver**.

This was one of my earliest deep dives into the DevOps mindset: *"if it can be automated, automate it."*

---

## 🛠️ Tech Stack

| Layer | Technology | Role |
|---|---|---|
| 🏗️ **Build & Orchestration** | Apache Maven 3.8+ | Lifecycle management, dependency resolution, plugin execution |
| ☕ **Language** | Java 8+ | Core automation scripts and test logic |
| 🌐 **Browser Automation** | Selenium WebDriver | Automated UI/smoke testing as part of the pipeline |
| 📦 **Packaging** | Maven Assembly / JAR Plugin | Artifact creation for deployment stage |
| 🔌 **Plugins** | Maven Surefire, Failsafe, Resources Plugin | Test execution, integration testing, resource filtering |
| 📋 **Config** | `pom.xml` | Single source of truth for dependencies, profiles, and phases |

---

## 🔄 Pipeline Flow

The pipeline is driven entirely through **Maven's build lifecycle phases**, chained together to simulate a real CI/CD process:

```
┌─────────────┐    ┌─────────────┐    ┌──────────────┐    ┌─────────────┐    ┌──────────────┐
│   validate  │───▶│   compile   │───▶│     test     │───▶│   package   │───▶│    deploy    │
│             │    │             │    │  (Selenium)  │    │  (JAR/WAR)  │    │              │
│ Check POM   │    │ Compile     │    │ Run browser  │    │ Bundle      │    │ Push to      │
│ structure   │    │ Java source │    │ automation   │    │ artifact    │    │ destination  │
└─────────────┘    └─────────────┘    └──────────────┘    └─────────────┘    └──────────────┘
```

### Key Maven Phases Used

| Phase | What Happens |
|---|---|
| `validate` | Confirms `pom.xml` is well-formed and all required info is present |
| `compile` | Compiles Java source code into bytecode |
| `test` | Executes Selenium WebDriver tests via Surefire Plugin |
| `package` | Bundles compiled code into a deployable JAR/WAR artifact |
| `verify` | Runs integration tests (Failsafe Plugin) to validate the packaged artifact |
| `install` | Installs the artifact into local Maven repository |
| `deploy` | Ships the artifact to a remote repository or target environment |

---

## 📁 Project Structure

```
maven-automation-main/
│
├── src/
│   ├── main/
│   │   └── java/                   # Core automation/pipeline logic
│   │       └── com/automation/
│   │           └── pipeline/
│   │
│   └── test/
│       └── java/                   # Selenium WebDriver test suites
│           └── com/automation/
│               └── tests/
│
├── .github/
│   └── workflows/                  # (Optional) GitHub Actions CI trigger
│
├── pom.xml                         # ← Heart of the project: all config lives here
└── README.md
```

### The `pom.xml` — The Real Star

The `pom.xml` in this project is more than just a dependency list. It configures:
- **Build profiles** for different environments (dev, staging, prod)
- **Plugin bindings** to specific lifecycle phases
- **Surefire Plugin** to run Selenium tests automatically during `mvn test`
- **Failsafe Plugin** for integration test gates before packaging

---

## ✅ Prerequisites

| Requirement | Version | Link |
|---|---|---|
| Java JDK | 8 or 11+ | [Adoptium](https://adoptium.net/) |
| Apache Maven | 3.6+ | [maven.apache.org](https://maven.apache.org/download.cgi) |
| Google Chrome | Latest | [google.com/chrome](https://www.google.com/chrome/) |
| ChromeDriver | Match Chrome version | [chromedriver.chromium.org](https://chromedriver.chromium.org/) |

Verify your environment:
```bash
java -version
mvn -version
```

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/AkilanP1726/maven-automation.git
cd maven-automation
```

### 2. Install All Dependencies

```bash
mvn clean install -DskipTests
```

> This pulls all dependencies from `pom.xml` without running tests — great for a first-time setup check.

### 3. Set ChromeDriver Path *(if not using WebDriverManager)*

```bash
# Mac / Linux
export CHROME_DRIVER_PATH=/path/to/chromedriver

# Windows
set CHROME_DRIVER_PATH=C:\path\to\chromedriver.exe
```

---

## ▶️ Maven Commands

### Run the Full Pipeline (end-to-end)
```bash
mvn clean verify
```

### Individual Phase Execution

```bash
# Compile only
mvn clean compile

# Run Selenium tests
mvn clean test

# Package into artifact
mvn clean package

# Full pipeline including integration tests
mvn clean verify

# Install to local repo
mvn clean install

# Deploy (requires repository config in pom.xml)
mvn clean deploy
```

### Skip Tests When Needed
```bash
mvn clean package -DskipTests
```

### Run with a Specific Build Profile
```bash
mvn clean verify -P staging
mvn clean verify -P production
```

### Useful Debug Flags
```bash
mvn clean test -X           # Full debug output
mvn clean test -e           # Show full stack traces on errors
mvn dependency:tree         # Visualize all resolved dependencies
mvn help:effective-pom      # See the fully merged, resolved POM
```

---

## 🧪 Selenium WebDriver in the Pipeline

Selenium WebDriver was integrated as the **automated testing gate** — tests execute during the `test` phase and must pass before Maven proceeds to `package`. This mirrors real-world CI/CD pipelines where:

- ✅ Tests pass → pipeline continues → artifact is packaged and deployed
- ❌ Tests fail → pipeline stops → no broken code reaches the next stage

```java
// Selenium WebDriver wired into Maven test phase
WebDriver driver = new ChromeDriver();
driver.get("https://target-application-url.com");
// assertions run here...
driver.quit();
```

The **Maven Surefire Plugin** picks up test classes automatically based on naming conventions:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.0.0</version>
    <configuration>
        <includes>
            <include>**/*Test.java</include>
        </includes>
    </configuration>
</plugin>
```

---

## 💡 What I Learned

This project was a turning point in how I understood software delivery. Here's what actually stuck:

**Maven & Build Lifecycle**
- Maven isn't just a dependency downloader — it's a **pipeline orchestrator**
- Understanding the difference between lifecycle phases, goals, and plugins changed how I read any CI system
- `pom.xml` is a declarative contract for how your software gets built, tested, and shipped

**CI/CD Fundamentals**
- The power of **automated quality gates** — if tests fail, nothing ships, period
- Why environment **build profiles** matter (dev vs staging vs prod configs differ in real projects)
- How artifact management (`install` / `deploy`) fits into team-wide workflows

**Selenium in a Pipeline Context**
- Running browser tests in a CI context vs. just locally — headless mode matters here
- Why proper driver teardown (`driver.quit()`) is critical when tests run unattended
- The difference between fast unit tests (Surefire) and slower integration tests (Failsafe) and *why you separate them*

**The DevOps Mindset**
- Automation isn't about the tools — it's about removing human error from every repeatable step
- A pipeline is only as strong as its slowest, most fragile manual handoff
- Documentation is *part of the automation* — without it, the knowledge lives only in your head

---

## 🗄️ Why It's Archived (And Why It's Still Here)

**Archived, not deleted — because there's a difference.**

This project is preserved as a snapshot of where my DevOps and automation journey began. The dependency versions may be two years old, but the concepts — Maven lifecycle, test-gated pipelines, Selenium integration — are still foundational to understanding modern CI/CD.

If you're just getting started with Maven-based pipelines, this repo might give you a useful mental model. Just update the dependency versions in `pom.xml` before running anything.

---

## 🔮 If I Were to Rebuild This Today

| Then | Now |
|---|---|
| Manual ChromeDriver setup | WebDriverManager (auto-handles driver binaries) |
| Maven-only local pipeline | GitHub Actions / Jenkins full CI integration |
| Basic Surefire HTML report | Allure Report for rich, visual test reporting |
| Single environment config | Docker-based environment isolation per stage |
| Selenium 3 | Selenium 4 (W3C standard, native relative locators) |

---

## 👨‍💻 Author

**Akilan Pandiyan**
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-0077B5?style=flat&logo=linkedin)](https://linkedin.com/in/akilanpandiyan)
[![GitHub](https://img.shields.io/badge/GitHub-Follow-181717?style=flat&logo=github)](https://github.com/AkilanP1726)

---

<div align="center">

*Started with a `mvn clean install`. Ended with understanding why CI/CD exists.* ⚙️🚀

</div>
