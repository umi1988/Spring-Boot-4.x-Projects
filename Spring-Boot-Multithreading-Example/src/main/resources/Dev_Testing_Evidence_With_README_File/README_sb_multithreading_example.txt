Spring boot multithreading example Doc:

1> First we need to execute sync project to check the timing taken by the api. All the three service 
 returning the consolidated result synchronously by singe thread. It will take a lot of time as the load of an
 api operation increased.


GET Url :-

http://localhost:9191/api/v1/products/2/sync

api response time
6.48 s for 332 byte response data


2> Second we need to execute async project to check the timing taken by the api. All the three service 
 returning the consolidated result asynchronously by three different thread using CompletableFuture. 
 steps:-
  a> fetch all async CompletableFutures separatelely.
  b> wait for all CompletableFutures to complete.
  c> combine the result of all the CompletableFutures by join method.
  d> now build and return finally.
  
 It will not take much time as the load of an api operation increased as it executing it by three threads.



GET Url :-

http://localhost:9191/api/v1/products/2/async

api response time
2.07 s for 332 byte response data