# Library

## Application assumptions

The application must have modeled entities and related tables:

* readers, containing the reader's ID, name, surname and account creation date
* titles, containing title id, title, author and year of publication
* copies of books, containing the copy ID, title ID and status (e.g. in circulation, destroyed, lost)
* which includes the item ID, reader ID, borrowing date and return date

If there are 5 Catcher in the Rye books in the library, then our tables will have one entry in the titles table and 5 entries in the copies table.
Thanks to this, each piece is identifiable in the database, but we do not duplicate information such as title or author.

In addition to modeling the tables, you will also need to create REST services. Basic services are:

* adding a reader
* adding a title
* adding a copy
* change of item status
* checking the number of copies of a given title available for rent
* borrowing a book
* book return

For simplicity, we can assume that if a reader loses a book, the book is returned and the status of the copy is changed when the cost of the book is paid.

