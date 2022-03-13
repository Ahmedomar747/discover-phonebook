## Getting Started

This is a phonebook simulation on a small scale. This project relies on a self-contained database 'phonebook.db' using a sqlite.  

## Folder Structure

The workspace contains two folders and two files, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies
- `ddsl.sql`: the file to maintain data definition(i.e. database name and tables) for the database.
- `phonebook.db`: the file to maintain the self-contained database.

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

Before running the application, please unzip the sqlite jar file  found in lib/ directory and copy the org and native folders and paste them to bin/ directory to prevent errors. 

## Dependency Management

This projects relies on the sqlite jdbc dependecy version:3.7.2

## Operating the Applicaiton

Interaction with the application require passing arguments throught CLI for example:
- `java Main getAllContacts`: shows all contacts.

The Operations available below:
- `getFavoriteContacts`: shows all favorite contacts.
- `getContactsByName [name]`: shows all contacts starting with placeholder `[name]`.
- `getContactsByPhone [phone]`: shows all contacts with place `[phone]`.
- `getAllContacts`: shows all contacts.
- `insertContact [name] [phone1] [phone1type] [phone2] [phone2type] .....`: registers new contact with placeholder `[name]` and atleast one placeholder for both `[phone]` and `[phone2type]`.
- `updateContact [name] [phone1] [phone1type] [phone2] [phone2type] .....`: updates existing contact with placeholder `[name]` and atleast one placeholder for both `[phone]` and `[phone2type]`.
- `deleteContact [name]`: deletes contact with matching placeholder `[name]`.
- `markAsFavorite [name]`: marks contact as favorite with matching placeholder `[name]`.
