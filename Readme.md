 -- User
 userId
 emailId
 UserName : String
 accountId: String
 getUser(id) -- string
 getUser() -- List<User>
 createUser(User) -- string

 -- Account
id: String
User: User
Balance: int
Transactions : List<Transaction>
createAccount(Account) --> id //POST
getAccount(userId) --> Account // get


-- Transaction
TransId
Amount
TransDate
Status
Currency

getTransaction(transId) --> Transaction // Get

createTransaction(Transaction, userId) --> TransId // POST
    -- updateAccountBalance(userId, Amount) -->

--------------------------------- Notification API ----------------------------------------
-- User events trigger Notification which gets stored

-- Notification
NotificationId : Long
UserId: String
EmailId: String
EventType: Enum
Message:String
Time: Date
Status: String

createNotification(Notification n) --> NotificationId  //POST
getNotification(userId) --> List<Notification> //GET

-- CallerService, ResponseModel, interService Communication, RESTTemplate

-- Call Notification Service is used as a Singleton
All the Components are initialized as Singleton by default

--------------------------------- Authentication using JWT ----------------------------------------
create user in auth db

-- User
createUser(UserEntity user) --> void
getUser(userName) --> UserEntity

-- Authentication
username: String
password: String
authUser(username, password) -->
        1. Validation -- userService getUser should get User
        2. TokenGenerate -- return token
    validateToken(token) -->
        return boolean based on validation logic
        3. Expire --> Logout

--Monitor health of the UserAuthentication at -
localhost:8087/actuator
localhost:8087/actuator/health

---------------------- Spring Batch ----------------------------------
 -- ItemReader --> Reads the notification from the notification db, on corresponding time intervals
 -- ItemProcessor --> sends email notification to the entity
 -- ItemWriter -->  mark the db record as done


------------------------- Done ---------------------------------------
Eureka
Transaction Rollback on failure, Commit, -- used @Transactional on ServiceImpl
Logger -- executed on individual classes
Filter(Authentication) -- on conditions

--------------------------- To do --------------------------------------------

-- Jenkins
-- Spring Batch
-- RabitMQ

Interceptor(used for header validations) // before Controller is invoked
Custom query
ExceptionHandler (Response), RequestValidation(Request), DatabaseValidation(Not null, username different )
Swagger(OpenAPI documentation)
Notification(Messaging)
Lazy Initialization
Transaction +,-


insert into NOTIFICATION_TABLE values(1, 'rverma2@stevens.edu', 'created', 'Create Notification', 'Pending',  CURRENT_TIMESTAMP(), 123)
