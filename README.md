El País Article Scraper and Analyzer
Overview
This project is a web scraping and analysis tool for the Spanish news outlet El País. It navigates the website, scrapes article data from the Opinion section, translates article headers to English, and analyzes the translated headers for repeated words. Additionally, the project incorporates cross-browser testing using BrowserStack to ensure compatibility across various platforms.

Features
Text Verification:

Ensures that the website's text is displayed in Spanish.
Article Scraping:

Navigates to the Opinion section of the El País website.
Fetches the first five articles, including:
Article titles.
Article content.
Cover images (downloaded locally if available).
Translation:

Translates the article headers from Spanish to English using a translation API.
Header Analysis:

Identifies words that are repeated more than twice across the translated headers.
Displays each repeated word along with its frequency.

Technologies Used
Programming Language: Java
Web Automation: Selenium
Testing Framework: TestNG
Cross-Browser Testing: BrowserStack
Translation API: (e.g., Google Translate API or Rapid Translate Multi Traduction API)
Build Tool: Maven or Gradle
Dependencies: Selenium, TestNG, and others as listed in the pom.xml or build.gradle

Setup Instructions
Prerequisites
Java Development Kit (JDK) installed on your system.
Maven or Gradle for dependency management.
A BrowserStack account for cross-browser testing (if testing on BrowserStack).
API key or credentials for the translation service.
