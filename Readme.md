# Entities
### USER API
 
| Field | Type |
| ------ | ------ |
| userId | String |
| userName | String |
| accountId | String |
```bash 

 > getUser(UUID id) --> String // GET
 > getUser() --> List<User> // GET
 > createUser(User user) --> String // POST
```
### Account API
| Field | Type |
| ------ | ------ |
|id| String|
|User| User|
|Balance| int|
```bash
Transactions : List<Transaction> // GET
createAccount(Account) --> id //POST
getAccount(userId) --> Account // GET
```

## Transaction API

| Field | Type |
| ------ | ------ |
|TransId| UUID|
|Amount|long|
|TransDate|Date|
|Status|String|
|Currency|String|
```bash

getTransaction(transId) --> Transaction // GET
createTransaction(Transaction, userId) --> TransId // POST
   -- updateAccountBalance(userId, Amount)
```
### Notification API 
- User events trigger Notification which gets stored in the notifications table which later gets processed by Spring batch
- CallerService, ResponseModel, Inter-Service Communication, RESTTemplate
- Call Notification Service is used as a Singleton
- All the Components are initialized as Singleton by default


| Field | Type |
| ------ | ------ |
|NotificationId | Long|
|UserId| String|
|EmailId| String|
|EventType| Enum|
|Message|String|
|Time| Date|
|Status| String|
```bash
createNotification(Notification n) --> NotificationId  //POST
getNotification(userId) --> List<Notification> //GET
```

## Authentication using JWT
 - create user in auth db
```bash
### User
createUser(UserEntity user) --> void
getUser(userName) --> UserEntity
```
| Field | Type |
| ------ | ------ |
|username| String|
|password| String|

    authUser(username, password) -->
        1. Validation -- userService getUser should get User
        2. TokenGenerate -- return token
    validateToken(token) -->
        return boolean based on validation logic
        3. Expire --> Logout

- Monitor health of the UserAuthentication at- > localhost:8087/actuator > localhost:8087/actuator/health

```bash
---------------------- Spring Batch ----------------------------------
 -- ItemReader --> Reads the notification from the notification db, on corresponding time intervals
 -- ItemProcessor --> sends email notification to the entity
 -- ItemWriter -->  mark the db record as done
```
```bash
------------------------- Done ---------------------------------------
Eureka
Transaction Rollback on failure, Commit, -- used @Transactional on ServiceImpl
Logger -- executed on individual classes
Filter(Authentication) -- on conditions
```
```bash
--------------------------- To do --------------------------------------------

-- Jenkins
-- Spring Batch
-- RabitMQ (
	prioritize message, 
	notifications with status failed should be delivered by message or something)
	
				       EMailService (create user event, status update)
			(_mail queue_)/ 
			/
notification publish->(exchange)
			\
			(_sms queue_)\
				 	Sms Service( transaction event, ) 

Interceptor(used for header validations) // before Controller is invoked
Custom query
ExceptionHandler (Response), RequestValidation(Request), DatabaseValidation(Not null, username different )
Swagger(OpenAPI documentation)
Notification(Messaging)
Lazy Initialization
Transaction +,-

```
> insert into NOTIFICATION_TABLE values(1, 'rverma2@stevens.edu', 'created', 'Create Notification', 'Pending',  CURRENT_TIMESTAMP(), 123)
