# TKWRN-Microservices-CRM

Refactoring monolith CRM SPring project into microservices structure.
--------------------------------------------------------------------
Original project: https://github.com/EN-IH-WDPT-JUN21/Stolen-Name-LBL-Trucking_Company_Homework-3

# Services

## discovery-service 
Implements Eureka server

## gateway
Implements Gateway

## salesrep-service 
Generates, stores and updates SalesRep objects

| Endpoint | Method | Description | Path Params
| :--- | :--- | :--- | :--- 
| /api/v1/sales-reps | `GET` | Get all SalesReps | None
| /api/v1/sales-reps/{id} | `GET` | Get SalesRep by id | `id=[long]`
| /api/v1/sales-reps/new | `POST` | Add new SalesRep | None
| /api/v1/sales-reps/update/{id} | `PUT` | Update SalesRep name |  `id=[long]`
| /api/v1/sales-rep/delete/{id} | `DELETE` | Delete SalesRep | `id=[long]`
| /api/v1/sales-rep/populate | `POST` | Populate SalesRep database

## lead-service
Generates, stores, updates and converts Lead objects into Opportunity objects

| Endpoint | Method | Description | Path Params
| :--- | :--- | :--- | :--- 
| /api/v1/leads | `GET` | Get all Leads | None
| /api/v1/leads/{id} | `GET` | Get Lead by id | `id=[long]`
| /api/v1/leads/new | `POST` | Add new Lead | None
| /api/v1/leads/{id}/convert | `GET` | Convert Lead into Opportunity |  `id=[long]`
| /api/v1/leads/delete/{id} | `DELETE` | Delete Lead | `id=[long]`

## contact-service
Generates, stores and updates Contact objects

| Endpoint | Method | Description | Path Params
| :--- | :--- | :--- | :--- 
| /api/v1/contacts | `GET` | Get all Contacts | None
| /api/v1/contacts/{id} | `GET` | Get Contact by id | `id=[long]`
| /api/v1/contacts/new | `POST` | Add new Contact | None
| /api/v1/contacts/change-account/{id} | `PUT` | Update Contact accountId |  `id=[long]`

## opportunity-service
Generates, stores and updates Opportunity objects

| Endpoint | Method | Description | Path Params
| :--- | :--- | :--- | :--- 
| /api/v1/opps//get | `GET` | Get all Opportunities | None
| /api/v1/opps/get/{id} | `GET` | Get Opportunity by id | `id=[long]`
| /api/v1/opps/get-by-account/{id} | `GET` | Get Opportunity by accountId | `id=[long]`
| /api/v1/opps/new | `POST` | Add new Opportunity | None
| /api/v1/opps/update/{id} | `PUT` | Update Opportunity | `id=[long]` 
| /api/v1/opps/change-account/{id} | `PUT` | Update Opportunity accountId |  `id=[long]` 
| /api/v1/opps/delete/{id} | `DELETE` | Delete Opportunity | `id=[long]`

## account-service
Generates, stores and updates Account objects

| Endpoint | Method | Description | Path Params
| :--- | :--- | :--- | :--- 
| /api/v1/accounts | `GET` | Get all Accounts | None
| /api/v1/accounts/{id} | `GET` | Get Account by id | `id=[long]`
| /api/v1/accounts/reports/MEAN-EMPLOYEE-COUNT | `GET` | Get mean employee count | None
| /api/v1/accounts/reports/MEDIAN-EMPLOYEE-COUNT" | `GET` | Get median employee count | None
| /api/v1/accounts/reports/MAX-EMPLOYEE-COUNT | `GET` | Get max employee count | None
| /api/v1/accounts/reports/MIN-EMPLOYEE-COUNT | `GET` | Get min employee count | None
| /api/v1/accounts | `POST` | Add new Account | None
| /api/v1/accounts/new | `POST` | Create new Account | None
| /api/v1/accounts/change/{id} | `PUT` | Update Account opportunityId | `id=[long]`

## menu-service
Populates database, stores exceptions, anums and variables

## report-service
| Endpoint | Method | Description | Path Params
| :--- | :--- | :--- | :--- 
| /api/v1/account-report/mean-employee-count | `GET` | Get mean employee count | None
| /api/v1/account-report/median-employee-count" | `GET` | Get median employee count | None
| /api/v1account-report/max-employee-count | `GET` | Get max employee count | None
| /api/v1/account-report/min-employee-count | `GET` | Get min employee count | None

### How to use

1. Clone the repository
```
git clone https://github.com/EN-IH-WDPT-JUN21/TKWRN-Microservices-CRM
```
2. Run the application class of each service starting with discovery-service 

3. Mysql database connection credentials
```
   JDBC = jdbc:mysql://localhost:3306/ironhackhomework4
   username = ironhacker
   password = 1r0nH@ck3r
```

4. Gateway security credentials
```
name = user
password = user
```

### Service ports
| Port | Service
| :--- | :--- 
| 8761 | discovery-service
| 8000 | gateway
| 8100 | lead-service
| 8200 | account-service
| 8300 | opportunity-service
| 8400 | salesrep-service
| 8500 | report-service
| 8600 | contact-service
| 8700 | menu-service
