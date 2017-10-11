# JPA -> QueryDSL
Many projects start out with JPA. It's great. Easy to get going. You can move onto the more interesting problems.

Inevitably those more interesting problems lead to more interesting questions about your data. This is where JPA
becomes more of a burden. The API is harder to understand, the annotations become more arcane, and the code suffers.

This projects attempts use JPA for what it is good for. Then in situations where JPA is weak, it tries to bring QueryDSL
for just the next needed task. It then proceeds through several tasks.

## QueryDSL
QueryDSL seems to be a viable supplement to JPA. It provides a very intuitive API. It is easy to setup. Finally, as
this project demonstrates, it can be added to a code base as needed.

QueryDSL can shine where JPA doesn't:
* Making partial updates to database records (ie updating a single column for a single/many rows)
* Allows you to hydrate POJOs without annotations easily
* Easily construct conditions for SQL queries
* By building the queries yourself, optimization of queries is simple
* Can easily add eager loading (via `fetchJoin`) on a *per query basis*. Avoiding the need to eager 
  load a resource on *every* call
