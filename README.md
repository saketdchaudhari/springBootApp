# springBootApp
Spring boot application.
Read this file to understand how to setup the application on local and execute curl command to call the endpoints.

# Steps to setup the application and run maven build.

  1. Import the code on local drive.
  2. Open cmd prompt on local and cd to the path to the directory where code is imported.
  3. run the following command to build the application and run the tests.
          mvn clean install
  4. Once the build finishes successfully run the below command to start the application
          java -jar ***************************
  5. Once the server starts successfully the application should be up and running on port 9090 (default configured port in               application.properties)

# ########## Below are curl commands to call different endpoints ###########
 
# Curl command for Greeting endpoint

curl http://localhost:9090/api/rest/greeting

# Curl command to call external REST webservice endpoint

curl -H "Accept:application/json" http://localhost:9090/api/rest/jsonPlaceHolder/posts

# Curl command to call find word count endpoint 

curl -H "Accept: application/json" -G -X GET http://localhost:9090/api/rest/paragraph/wordCount --data-urlencode "paragraph=A-word1 D-word2 C-word3 C-word3 D-word4     G-word5 1-word1 ##$$%% ##$$%%"

# Curl command to call create thread endpoint

curl -H "Accept: application/json" -X POST http://localhost:9090/api/rest/threads/create

# Curl command call check thread deadlock status endpoint

curl -X GET url

**Note: Please use url from the location response header of above endpoint(/threads/create) .

# Curl command to call create employee record endpoint

curl -H "Content-Type: application/json"  "Accept: application/json" -d '{"name":"Name 1", "salary":2000}' -X POST http://localhost:9090/api/rest/employees

# Curl command to call find employee record endpoint

curl -H "Accept: application/json" -X GET http://localhost:9090/api/rest/employees/{id}

# Curl command to call find all employee record endpoint

curl -H "Accept: application/json" -X GET http://localhost:9090/api/rest/employees

# Curl command to call delete employee record endpoint

curl -X DELETE http://localhost:9090/api/rest/employees/{id}
