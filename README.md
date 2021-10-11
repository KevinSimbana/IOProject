# __SpartaProject:__ IO Application :computer: :books:

### Introduction
The main aim of the project was how to deal with data. Creating an application that is able to *__read data__* from a .CSV file and populating the data into *__OOP objects__* then also in a *__collection__*.
Again with other projects, being able to demonstrate good programming practices:

* SOLID Principles
* Model-View-Controller
* Desin patterns
* Testing
* Logging
* __Regex__ :weary:
* __Threading__

Second part to the project after creating the objects with the data from a file, is being able to write the data from the objects to a *__relational database using JDBC__* (In this case, using SQLite).

#### Code Implementation

###### Reading data from a .CSV file

```Java
try (BufferedReader in = new BufferedReader((new FileReader("EmployeeRecordsLarge.csv")))) {
            String line = in.readLine();
            while((line = in.readLine())!= null) {
                String[] attributes = line.split(",");
                if (!DataChecker.isDataValid(attributes)) {
                    Employee employee = EmployeeCreator.tryCreateEmployee(attributes);
                    if (employeeList.contains(employee)) {
                        specialEmployeeList.add(employee);
                    }
                    employeeList.add(employee);
                }
            }
```
>The following snippet of code deals with reading data from a given file and then parsing it into a OOP object. This deals with reading each individual record in the file (line) and performing validator check, if it passes then an object is allowed to be created of that given record. A final check is performed so that the object can get added to its respective list.


###### Adding to database

```Java
PreparedStatement preparedStatement =
    connection.prepareStatement("INSERT INTO EMPLOYEES " +
    "(ID, PREFIX, FIRST_NAME, MIDDLE_INITIAL, LAST_NAME, GENDER, EMAIL, DOB, DOJ, SALARY)" +
    "VALUES(?,?,?,?,?,?,?,?,?,?)");
````
>Making use of PreparedStatement to add an employee object to a given database. Allowing to finish the implementation of the statement with unknown values that will be added.
