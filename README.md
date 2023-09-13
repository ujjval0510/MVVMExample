# MVVMExample: Android MVVM Architecture 
## Topic Covered in this example
1. MVVM architecture flow.
2. API call using Retrofit.
3. Used Extension function.
4. Setup Room Database
5. Coroutine for Async api calling
6. Handling API Exceptions using Generic functions
7. Redirect user to Home screen if it's already logged in using Observer. 
8. Handling No Internet using interceptor in Retrofit.
9. Custom Exception Handling.
10. Dependency Injection :
    - It is a design pattern and it is independent from android.
    - basically means injecting the dependencies
    - It can be used anywhere while developing any project.
    - DI design pattern says :
      -> Class should not create object inside it.
      -> But Object required should be supplied from outside.

## Problem : Creating the object of the class but User class it depending on the AppDatabase class. 
that we will resolve this issue using dependencies injection.
    class User {
        private val appDB = AppDatabase();
    }

## Solution : we will pass object of AppDatabase class using constructor injection like below
    class User (
        private val appDB = AppDatabase() // it is called constructor injection.  
    ) {
        // rest of the code.
    }
## ADVANTAGE :
    1. Loosely coupled.
    2. Easy testing.

For achieve this we need some service that can project object on run time to solve this solution
## Kodein Framework (Kotlin dependencies Injection)
    - Kodein is very simple and yet very useful dependecies retrival container.
    - It is very easy to use and configure. 