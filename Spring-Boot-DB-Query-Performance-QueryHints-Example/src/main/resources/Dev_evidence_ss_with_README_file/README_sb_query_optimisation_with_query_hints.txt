

steps to test spring boot with query optimisation with query hints functionality:-
1>
Make sure the scheme created and employee table created
First start the app uncommented main class code to create 2 lac employee entries.
2>
Now comment the main class as of now.
First start the app with commented query hint code.

GET API call :-
http://localhost:9191/employees/salary/50000

Response time -:
974 ms 24.74 MB


3>
Now uncomment query hint code.
Now make a same get api calls


GET API call :-
http://localhost:9191/employees/salary/50000

Response time -:
428 ms 24.74 MB



All the required screen shot attached in the same location.
