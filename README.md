# springBootApp
Spring boot application

# Endpoint for Greeting 

curl http://localhost:9090/api/rest/greeting

# Endpoint to call external REST webservice 

curl -H "Accept:application/json" http://localhost:9090/api/rest/jsonPlaceHolder/posts

# Endpoint to find word count

curl -H "Accept: application/json" -G -X GET http://localhost:9090/api/rest/paragraph/wordCount --data-urlencode "paragraph=A-word1 D-word2 C-word3 C-word3 D-word4     G-word5 1-word1 ##$$%% ##$$%%"

# Endpoint to create thread

curl -H "Accept: application/json" -X POST http://localhost:9090/api/rest/threads/create

# Endpoint to check thread deadlock

curl -X GET url

Note: Please use url from the location response header of above (/threads/create) endpoint.

# Endpoint to create employee record

curl -H "Content-Type: application/json"  "Accept: application/json" -d '{"name":"Name 1", "salary":2000}' -X POST http://localhost:9090/api/rest/employees

# Endpoint to find employee record

curl -H "Accept: application/json" -X GET http://localhost:9090/api/rest/employees/{id}

# Endpoint to find all employee record

curl -H "Accept: application/json" -X GET http://localhost:9090/api/rest/employees

# Endpoint to delete employee record

curl -X DELETE http://localhost:9090/api/rest/employees/{id}
