# IP LABORATORY

Simple college lab programs for Exercises 1–13. Each exercise is independent.

| Exercise | Topic | Start page | Requirements |
| --- | --- | --- | --- |
| 01 | Ticket reservation using HTML | `Exercise-01/index.html` | Browser |
| 02 | HTML5 shopping, media, image map, drag/drop, canvas, location | `Exercise-02/index.html` | Browser, localhost |
| 03 | Inline, internal, external CSS; selectors and box model | `Exercise-03/index.html` | Browser |
| 04 | Job portal registration validation with JavaScript and `match()` | `Exercise-04/index.html` | Browser |
| 05 | Online examination: two MCQs and one-word answer | `Exercise-05/exam.html` | JDK, Tomcat |
| 06 | Ticket booking using Servlet, JDBC and MySQL | `Exercise-06/booking.html` | JDK, Tomcat, MySQL, JDBC driver |
| 07 | Hidden fields, URL rewriting and visitor count | `Exercise-07/index.html` | JDK, Tomcat |
| 08 | Registration details using JSP | `Exercise-08/register.html` | Tomcat |
| 09 | Shopping orders using JSP, JDBC and MySQL | `Exercise-09/order.html` | Tomcat, MySQL, JDBC driver |
| 10 | Server-side registration validation using PHP regex | `Exercise-10/register.html` | PHP, Apache |
| 11 | Shopping orders using PHP and MySQL | `Exercise-11/index.html` | PHP mysqli, Apache, MySQL |
| 12 | Read and display book XML using PHP | `Exercise-12/display.php` | PHP SimpleXML, Apache |
| 13 | Load order XML asynchronously with XMLHttpRequest | `Exercise-13/index.html` | Browser, localhost |

## HTML, JavaScript, PHP and AJAX

Copy the repository folder to `C:\xampp\htdocs\IP-LABORATORY` and start Apache in XAMPP.
Open `http://localhost/IP-LABORATORY/Exercise-01/index.html`, replacing the exercise folder and page using the table above.
Exercises 1, 3 and 4 also work by opening their HTML files directly. Use localhost for Exercises 2 and 13. Geolocation requires browser permission and a secure context (localhost is accepted); it shows the user's coordinates.

The static ticket and shopping forms demonstrate HTML controls; they do not save bookings. Exercise 4 validates in the browser and displays an alert without storing a registration. The Buy Now buttons in Exercise 3 demonstrate hover styling.

Exercise 2 includes original laptop/category illustrations and a three-second MP4 slideshow of the laptop. Exercise 3 includes laptop and phone illustrations. All media is local; no external image or video service is needed.

## MySQL setup

Start MySQL. Import these scripts through phpMyAdmin's Import tab or the MySQL client:

```text
Exercise-06/setup.sql  -> ticketdb
Exercise-09/setup.sql  -> shoppingjspdb
Exercise-11/setup.sql  -> shoppingdb
```

For example, in the MySQL client run `SOURCE C:/xampp/htdocs/IP-LABORATORY/Exercise-11/setup.sql;`.
Each script creates its database and table without deleting existing records. Exercise 9 uses a separate database to avoid interference with Exercise 11.
Local lab credentials are `root` with an empty password. Update them in `Exercise-06/TicketServlet.java`, `Exercise-09/order.jsp` and `Exercise-11/db.php` for your installation. PHP requires the mysqli and SimpleXML extensions.

Exercise 11: submit an order, then choose View Orders. Exercise 12: expect three books. Exercise 13: choose Load Orders to display three orders without refreshing the page.

## Java Servlet and JSP setup

Use JDK 17 or later and Tomcat 10.1 or later. These sources use `jakarta.servlet`; older Tomcat 9 uses `javax.servlet` and requires changing the imports. Do not mix the namespaces.

Deploy each exercise as its own web application. For Exercise 5, for example:

```text
TOMCAT_HOME/webapps/Exercise-05/
    exam.html
    WEB-INF/classes/ExamServlet.class
```

Copy the exercise's HTML files to its deployment folder. Compile its servlet sources into `WEB-INF/classes` with Tomcat's servlet API on the classpath. Example in PowerShell from the repository root (set `$tomcatPath` to your Tomcat installation):

```powershell
$tomcatPath = 'C:\apache-tomcat-10.1'
$appPath = Join-Path $tomcatPath 'webapps\Exercise-05'
New-Item -ItemType Directory -Force "$appPath\WEB-INF\classes" | Out-Null
Copy-Item Exercise-05\exam.html $appPath
javac -encoding UTF-8 -cp "$tomcatPath\lib\servlet-api.jar" -d "$appPath\WEB-INF\classes" Exercise-05\ExamServlet.java
```

Repeat for Exercise 6 with `booking.html` and `TicketServlet.java`, and Exercise 7 with `index.html`, `FirstServlet.java` and `SecondServlet.java`. Both Exercise 7 classes must be compiled into the same application. Annotation mappings provide the servlet routes; no `web.xml` is needed.

For Exercises 8 and 9, copy the HTML and JSP files directly into their respective folders under `webapps`. Tomcat compiles JSP pages automatically.
For Exercises 6 and 9, place a MySQL Connector/J JAR in each application's `WEB-INF/lib` folder. The driver is not bundled in this repository. Import the SQL before submitting the forms.

Start or restart Tomcat, then open the table's start page, for example `http://localhost:8080/Exercise-05/exam.html`. XAMPP's Apache serves PHP; Tomcat serves Servlet/JSP applications.

## Expected results and checks

- Exercise 4 rejects invalid names, emails, phone numbers, short passwords and unsupported resume extensions; accepts PDF/DOC/DOCX extensions.
- Exercise 5: Paris, 4 and CSS produce 3/3. Lowercase `css` and surrounding spaces are accepted. Other answers reduce the score.
- Exercise 6 stores a booking and displays stored tickets.
- Exercise 7 passes the name by hidden field and URL parameter, and counts each session once. The count resets when the application restarts; a new browser session counts as another visitor. Session URLs are encoded for browsers without cookies.
- Exercise 8 displays the submitted fields. Use dummy passwords and card numbers for the demonstration.
- Exercise 9 stores an order and displays orders; Exercise 11 performs the PHP equivalent.
- Exercise 10 requires a password of at least six characters, 16 card digits, an email format and 10 phone digits. This is format validation only.
- Exercise 12 reads three book entries; Exercise 13 asynchronously displays three orders.

See `VERIFICATION.md` for checks performed on this copy and runtime limitations.
