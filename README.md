Overview
FuelReports is a software module that reads petrol stations data from different files, saves it in a database and provides more complex reporting functionality.

Specification
FuelReports is a command-line interface that deserializes XML files, stores data in a MySQL database and provides reporting commands. It must provide daily, monthly and yearly  average price reports for a given city, petrol station and/or fuel type. The XML files are shared through sftp.

sftp Credentials:
Host: fe.ddns.protal.biz
Post: 22
Username: sftpuser
Password:  hyperpass
Data folder: /xml-data

Technology Stack
Java
MySQL
JCommander

Milestones
Parse data from sample XML
sftp downloader
Store data from XML to database
Command-line interface
