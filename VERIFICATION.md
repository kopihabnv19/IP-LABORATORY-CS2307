# Verification

Checks performed on 7 October 2026:

- Confirmed folders Exercise-01 through Exercise-13 and all referenced local HTML assets, stylesheets, scripts, form targets and fragment links.
- Parsed both XML data files successfully.
- Passed JavaScript syntax checks for all inline scripts and `orders.js`.
- Passed PHP syntax checks for all five PHP files.
- Compiled all four Java servlet classes against the Jakarta Servlet API.
- Compiled declarations, scriptlets and expressions from both JSP pages inside Java wrappers. This checks embedded Java; full Tomcat JSP translation and deployment were not run.
- Executed Exercise 10 with valid and invalid registration values and confirmed success/error output.
- Executed Exercise 12 and confirmed all three book titles appear.
- Generated the local JPG illustrations and successfully encoded the three-second H.264 MP4 video.

MySQL was not available for a connection during verification. SQL setup, JDBC inserts and PHP database inserts/display still need a running MySQL instance and the configured local credentials. Servlet/JSP browser flows, geolocation permissions, drag/drop and AJAX browser interaction were not tested in a running browser. Setup and expected results are documented in README.md.
